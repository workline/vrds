package vrds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

@Entity
@Inheritance
@DiscriminatorColumn(name = "ATTRIBUTE_TYPE")
public abstract class Attribute {
    @Id
    @GeneratedValue
    protected Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<MetaAttribute> metaAttributes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "attribute")
    protected Set<StringValue> stringValues;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerAttribute")
    protected Set<RepoItemValue> repoItemValues;

    // TODO Add rest of the dependencies

    public Object getValue() {
        Object value;

        @SuppressWarnings("unchecked")
        Set<? extends IValue<Object>>[] valueSets = new Set[] { stringValues, repoItemValues };

        value = null;
        boolean found = false;
        for (int listIndex = 0; !found && listIndex < valueSets.length; listIndex++) {
            Set<? extends IValue<Object>> valueSet = valueSets[listIndex];
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
                setStringValue((String) value);
            } else if (value instanceof StringValue) {
                setStringValue((StringValue) value);
            } else if (value instanceof RepoItem) {
                setRepoItemValue((RepoItem) value);
            } else if (value instanceof RepoItemValue) {
                setRepoItemValue((RepoItemValue) value);
            } else {
                // TODO Have an appropriate exception
                throw new IllegalArgumentException();
            }
        }
    }

    // TODO getValues();

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

    private void setStringValue(String value) {
        StringValue stringValue = new StringValue();
        stringValue.setValue(value);
        stringValue.setAttribute(this);

        setStringValue(stringValue);
    }

    private void setStringValue(StringValue stringValue) {
        if (stringValues == null) {
            Set<StringValue> stringValues = new HashSet<StringValue>();
            stringValues.add(stringValue);
            setStringValues(stringValues);
        } else {
            stringValues.clear();
            stringValues.add(stringValue);
        }
    }

    private void setRepoItemValue(RepoItem repoItem) {
        RepoItemValue repoItemValue = new RepoItemValue();
        repoItemValue.setValue(repoItem);
        repoItemValue.setOwnerAttribute(this);

        setRepoItemValue(repoItemValue);
    }

    private void setRepoItemValue(RepoItemValue repoItemValue) {
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
