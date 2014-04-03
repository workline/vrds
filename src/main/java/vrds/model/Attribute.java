package vrds.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import vrds.model.attributetype.AttributeValueHandler;
import vrds.model.meta.Coupling;
import vrds.model.meta.CouplingTag;
import vrds.util.Util;

@Coupling(tags = { CouplingTag.ATTRIBUTE_TYPE }, value = "The number of Set<...> ...Values should correspond to the number of attribute types.")
@Entity
@Inheritance
@DiscriminatorColumn(name = "ATTRIBUTE_TYPE")
@SequenceGenerator(name = "attributeIdSequenceGenerator", sequenceName = "SEQ_ATTRIBUTE_ID", initialValue = 1, allocationSize = 1000)
public abstract class Attribute implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String VALUE_SETS = "VALUE_SETS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attributeIdSequenceGenerator")
    protected Long id;

    protected String name;
    @Enumerated(EnumType.STRING)
    protected EAttributeType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<MetaAttribute> metaAttributes = new HashSet<>();

    @Coupling(tags = { CouplingTag.INNER }, value = VALUE_SETS + ": Add all Set<...> ...Values to the gathering result!")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<StringValue> stringValues = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<IntegerValue> integerValues = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<RepoItemValue> repoItemValues = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<AttributeValue> attributeValues = new HashSet<>();

    // TODO Add rest of the dependencies

    public void clearValues() {
        List<Set<IValueWrapper<Object>>> valueSetList = gatherValueSets();

        for (Set<IValueWrapper<Object>> set : valueSetList) {
            if (set != null) {
                set.clear();
            }
        }
    }

    public void setNameAndType(String name, EAttributeType type) {
        setName(name);
        setType(type);
    }

    public Object getValue() {
        Object value;

        List<Set<IValueWrapper<Object>>> valueSetList = gatherValueSets();

        value = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSetList.size(); listIndex++) {
            Set<IValueWrapper<Object>> valueSet = valueSetList.get(listIndex);
            value = _getValue(valueSet);

            if (value != null) {
                found = true;
            }
        }

        return value;
    }

    public <T, W extends IValueWrapper<T>> T getValue(AttributeValueHandler<T, W> attributeValueHandler) {
        return attributeValueHandler.getValue(this);
    }

    public Set<IValueWrapper<Object>> getValues() {
        Set<IValueWrapper<Object>> values;

        List<Set<IValueWrapper<Object>>> valueSetList = gatherValueSets();

        values = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSetList.size(); listIndex++) {
            Set<IValueWrapper<Object>> valueSet = valueSetList.get(listIndex);

            if (valueSet != null && !valueSet.isEmpty()) {
                values = valueSet;
                found = true;
            }
        }

        return values;
    }

    public void setValue(Object value) {
        if (value == null) {
            clearValues();
        } else {
            if (value instanceof IValueWrapper) {
                @SuppressWarnings("rawtypes")
                IValueWrapper valueWrapper = (IValueWrapper) value;
                type.getAttributeValueHandler().setValue(valueWrapper, this);
            } else {
                type.getAttributeValueHandler().setSimpleValue(value, this);
            }
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public IValueWrapper<?> addValue(Object value) {
        IValueWrapper<?> valueWrapper;

        if (value != null) {
            if (value instanceof IValueWrapper) {
                valueWrapper = (IValueWrapper) value;
                type.getAttributeValueHandler().addValue(valueWrapper, this);
            } else {
                valueWrapper = type.getAttributeValueHandler().addSimpleValue(value, this);
            }
        } else {
            valueWrapper = null;
        }

        return valueWrapper;
    }

    public MetaAttribute getMetaAttribute(String attributeName) {
        return Util.getAttribute(attributeName, metaAttributes);
    }

    // TODO Add rest of the dependencies

    public <T, W extends IValueWrapper<T>> T getMetaAttributeValue(String attributeName, AttributeValueHandler<T, W> attributeValueHandler) {
        return attributeValueHandler.getValue(getMetaAttribute(attributeName));
    }

    // TODO Add rest of the dependencies

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EAttributeType getType() {
        return type;
    }

    public void setType(EAttributeType type) {
        this.type = type;
    }

    public Set<MetaAttribute> getMetaAttributes() {
        return metaAttributes;
    }

    public void setMetaAttributes(Set<MetaAttribute> metaAttributes) {
        this.metaAttributes = metaAttributes;
        for (MetaAttribute metaAttribute : metaAttributes) {
            metaAttribute.setOwnerAttribute(this);
        }
    }

    public Set<StringValue> getStringValues() {
        return stringValues;
    }

    public void setStringValues(Set<StringValue> stringValues) {
        this.stringValues = stringValues;
        for (StringValue stringValue : stringValues) {
            stringValue.setOwnerAttribute(this);
        }
    }

    public Set<IntegerValue> getLongValues() {
        return integerValues;
    }

    public void setLongValues(Set<IntegerValue> integerValues) {
        this.integerValues = integerValues;
        for (IntegerValue integerValue : integerValues) {
            integerValue.setOwnerAttribute(this);
        }
    }

    public Set<RepoItemValue> getRepoItemValues() {
        return repoItemValues;
    }

    public void setRepoItemValues(Set<RepoItemValue> repoItemValues) {
        this.repoItemValues = repoItemValues;
        for (RepoItemValue repoItemValue : repoItemValues) {
            repoItemValue.setOwnerAttribute(this);
        }
    }

    public Set<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Set<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
        for (AttributeValue attributeValue : attributeValues) {
            attributeValue.setOwnerAttribute(this);
        }
    }

    @Coupling(tags = { CouplingTag.INNER }, value = VALUE_SETS + ": Add all Set<...> ...Values!")
    private List<Set<IValueWrapper<Object>>> gatherValueSets() {
        @SuppressWarnings("unchecked")
        Set<IValueWrapper<Object>>[] valueSets = new Set[] { stringValues, integerValues, repoItemValues, attributeValues };

        List<Set<IValueWrapper<Object>>> valueSetList = Arrays.asList(valueSets);

        return valueSetList;
    }

    private <T, V extends IValueWrapper<T>> T _getValue(Set<V> set) {
        T value;

        if (set != null && !set.isEmpty()) {
            value = set.iterator().next().getValue();
        } else {
            value = null;
        }

        return value;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = initToString();

        addInheritedToString(toStringBuilder);

        addValuesToString(toStringBuilder);

        finishToString(toStringBuilder);

        return toStringBuilder.toString();
    }

    private StringBuilder initToString() {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append(this.getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", " + "type=" + type);

        return toStringBuilder;
    }

    private void addInheritedToString(StringBuilder toStringBuilder) {
        String inheritedToStringPart = getInheritedToStringPart();

        if (inheritedToStringPart == null || "".equals(inheritedToStringPart.trim())) {
            // Do nothing
        } else {
            toStringBuilder.append(", " + inheritedToStringPart);
        }
    }

    private void addValuesToString(StringBuilder toStringBuilder) {
        List<Set<IValueWrapper<Object>>> valueSetList = gatherValueSets();

        int setIndex = 0;
        for (Set<IValueWrapper<Object>> set : valueSetList) {
            toStringBuilder.append(", values" + setIndex + "=" + set);
            setIndex++;
        }
    }

    private void finishToString(StringBuilder toStringBuilder) {
        toStringBuilder.append("]");
    }

    protected String getInheritedToStringPart() {
        return "";
    }
}
