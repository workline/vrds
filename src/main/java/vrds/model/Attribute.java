package vrds.model;

import java.io.Serializable;
import java.util.Arrays;
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

import vrds.meta.Coupling;
import vrds.meta.CouplingTag;
import vrds.util.Util;

@Coupling(tags = { CouplingTag.ATTRIBUTE_TYPE }, value = "The number of Set<...> ...Values should correspond to the number of attribute types.")
@Entity
@Inheritance
@DiscriminatorColumn(name = "ATTRIBUTE_TYPE")
@SequenceGenerator(name = "attributeIdSequenceGenerator", sequenceName = "SEQ_ATTRIBUTE_ID", initialValue = 1, allocationSize = 1000)
public abstract class Attribute implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String ATTRIBUTE_VALUES = "ATTRIBUTE_VALUES";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attributeIdSequenceGenerator")
    protected Long id;

    protected String name;
    @Enumerated(EnumType.STRING)
    protected EAttributeType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<MetaAttribute> metaAttributes;

    @Coupling(tags = { CouplingTag.INNER }, value = ATTRIBUTE_VALUES + ": Add all Set<...> ...Values to the gathering result!")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<StringValue> stringValues;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<RepoItemValue> repoItemValues;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<AttributeValue> attributeValues;

    // TODO Add rest of the dependencies

    public Object getValue() {
        Object value;

        List<Set<IValue<Object>>> valueSetList = gatherValueSets();

        value = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSetList.size(); listIndex++) {
            Set<IValue<Object>> valueSet = valueSetList.get(listIndex);
            value = getValue(valueSet);

            if (value != null) {
                found = true;
            }
        }

        return value;
    }

    public void setValue(Object value) {
        EAttributeType.setValue(value, this);
    }

    public Set<IValue<Object>> getValues() {
        Set<IValue<Object>> values;

        List<Set<IValue<Object>>> valueSetList = gatherValueSets();

        values = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSetList.size(); listIndex++) {
            Set<IValue<Object>> valueSet = valueSetList.get(listIndex);

            if (valueSet != null && !valueSet.isEmpty()) {
                values = valueSet;
                found = true;
            }
        }

        return values;
    }

    public MetaAttribute getMetaAttribute(String attributeName) {
        return Util.getAttribute(attributeName, metaAttributes);
    }

    // TODO Add rest of the dependencies

    public void clearValues() {
        List<Set<IValue<Object>>> valueSetList = gatherValueSets();

        for (Set<IValue<Object>> set : valueSetList) {
            if (set != null) {
                set.clear();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameAndType(String name, EAttributeType type) {
        setName(name);
        setType(type);
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

    @Coupling(tags = { CouplingTag.INNER }, value = ATTRIBUTE_VALUES + ": Add all Set<...> ...Values!")
    private List<Set<IValue<Object>>> gatherValueSets() {
        @SuppressWarnings("unchecked")
        Set<IValue<Object>>[] valueSets = new Set[] { stringValues, repoItemValues, attributeValues };

        List<Set<IValue<Object>>> valueSetList = Arrays.asList(valueSets);

        return valueSetList;
    }

    private <T, V extends IValue<T>> T getValue(Set<V> set) {
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
        String inheritedToString = getInheritedToString();

        if (inheritedToString == null || "".equals(inheritedToString.trim())) {
            // Do nothing
        } else {
            toStringBuilder.append(", " + inheritedToString);
        }
    }

    private void addValuesToString(StringBuilder toStringBuilder) {
        List<Set<IValue<Object>>> valueSetList = gatherValueSets();

        int setIndex = 0;
        for (Set<IValue<Object>> set : valueSetList) {
            toStringBuilder.append(", values" + setIndex + "=" + set);
            setIndex++;
        }
    }

    private void finishToString(StringBuilder toStringBuilder) {
        toStringBuilder.append("]");
    }

    protected String getInheritedToString() {
        return "";
    }
}
