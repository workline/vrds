package vrds.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance
@DiscriminatorColumn(name = "ATTRIBUTE_TYPE")
@SequenceGenerator(name = "attributeIdSequenceGenerator", sequenceName = "SEQ_ATTRIBUTE_ID", initialValue = 1, allocationSize = 1000)
public abstract class Attribute implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attributeIdSequenceGenerator")
    protected Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<MetaAttribute> metaAttributes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "attribute")
    protected Set<StringValue> stringValues;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<RepoItemValue> repoItemValues;

    public abstract AttributeDefinition getDefinition();

    // TODO Add rest of the dependencies

    public Object getValue() {
        Object value;

        @SuppressWarnings("unchecked")
        Set<IValue<Object>>[] valueSets = new Set[] { stringValues, repoItemValues };

        value = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSets.length; listIndex++) {
            Set<IValue<Object>> valueSet = valueSets[listIndex];
            value = getValue(valueSet);

            if (value != null) {
                found = true;
            }
        }

        return value;
    }

    public void setValue(Object value) {
        if (value != null) {
            if (value instanceof String) {
                _setStringValue((String) value);
            } else if (value instanceof StringValue) {
                _setStringValue((StringValue) value);
            } else if (value instanceof RepoItem) {
                _setRepoItemValue((RepoItem) value);
            } else if (value instanceof RepoItemValue) {
                _setRepoItemValue((RepoItemValue) value);
            } else {
                // TODO Have an appropriate exception
                throw new IllegalArgumentException();
            }
        }
    }

    public Set<IValue<Object>> getValues() {
        Set<IValue<Object>> values;

        @SuppressWarnings("unchecked")
        Set<IValue<Object>>[] valueSets = new Set[] { stringValues, repoItemValues };

        values = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSets.length; listIndex++) {
            Set<IValue<Object>> valueSet = valueSets[listIndex];

            if (valueSet != null && !valueSet.isEmpty()) {
                values = valueSet;
                found = true;
            }
        }

        return values;
    }

    public MetaAttribute getMetaAttribute(String attributeDefinitionName) {
        MetaAttribute metaAttribute;

        if (attributeDefinitionName == null || "".equals(attributeDefinitionName.trim())) {
            metaAttribute = null;
        } else {
            metaAttribute = null;

            boolean found = false;
            Iterator<MetaAttribute> attributesIterator = metaAttributes.iterator();

            while(!found && attributesIterator.hasNext()) {
                MetaAttribute currentMetaAttribute = attributesIterator.next();
                String currentMetaAttributeDefinitionName = currentMetaAttribute.getDefinition().getName();

                if (attributeDefinitionName.equals(currentMetaAttributeDefinitionName)) {
                    metaAttribute = currentMetaAttribute;
                    found = true;
                }
            }
        }

        return metaAttribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            stringValue.setAttribute(this);
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

    private <T, V extends IValue<T>> T getValue(Set<V> set) {
        T value;

        if (set != null && !set.isEmpty()) {
            value = set.iterator().next().getValue();
        } else {
            value = null;
        }

        return value;
    }

    // TODO getValues();

    private void _setStringValue(String value) {
        StringValue stringValue = new StringValue();
        stringValue.setValue(value);
        stringValue.setAttribute(this);

        _setStringValue(stringValue);
    }

    private void _setStringValue(StringValue stringValue) {
        if (stringValues == null) {
            Set<StringValue> stringValues = new HashSet<StringValue>();
            stringValues.add(stringValue);
            setStringValues(stringValues);
        } else {
            stringValues.clear();
            stringValues.add(stringValue);
        }
    }

    private void _setRepoItemValue(RepoItem repoItem) {
        RepoItemValue repoItemValue = new RepoItemValue();
        repoItemValue.setValue(repoItem);
        repoItemValue.setOwnerAttribute(this);

        _setRepoItemValue(repoItemValue);
    }

    private void _setRepoItemValue(RepoItemValue repoItemValue) {
        if (repoItemValues == null) {
            Set<RepoItemValue> repoItemValues = new HashSet<RepoItemValue>();
            repoItemValues.add(repoItemValue);
            setRepoItemValues(repoItemValues);
        } else {
            repoItemValues.clear();
            repoItemValues.add(repoItemValue);
        }
    }

    @Override
    public String toString() {
        return "Attribute [id=" + id + ", metaAttributes=" + metaAttributes + ", stringValues=" + stringValues + ", repoItemValues=" + repoItemValues + "]";
    }

}
