package vrds.model;

import java.util.HashSet;
import java.util.Set;

import vrds.meta.Coupling;
import vrds.meta.CouplingTag;

@Coupling(tags = { CouplingTag.ATTRIBUTE_TYPE })
public enum EAttributeType {
    STRING {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            String valueAsString = (String) value;

            StringValue stringValue = new StringValue();
            stringValue.setValue(valueAsString);
            stringValue.setOwnerAttribute(attribute);

            setValue(attribute, stringValue);
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            StringValue stringValue = (StringValue) value;

            Set<StringValue> stringValues = attribute.getStringValues();
            if (stringValues == null) {
                stringValues = new HashSet<StringValue>();
                stringValues.add(stringValue);
                attribute.setStringValues(stringValues);
            } else {
                stringValues.clear();
                stringValues.add(stringValue);
            }
        }
    },
    INTEGER {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }
    },
    DECIMAL {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }
    },
    DATE {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }
    },
    TIME {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }
    },
    DATETIME {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            // TODO Implement
            throw new UnsupportedOperationException();
        }
    },
    REPO_ITEM {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            RepoItem valueAsRepoItem = (RepoItem) value;

            RepoItemValue repoItemValue = new RepoItemValue();
            repoItemValue.setValue(valueAsRepoItem);
            repoItemValue.setOwnerAttribute(attribute);

            setValue(attribute, repoItemValue);
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            RepoItemValue repoItemValue = (RepoItemValue) value;

            Set<RepoItemValue> repoItemValues = attribute.getRepoItemValues();
            if (repoItemValues == null) {
                repoItemValues = new HashSet<RepoItemValue>();
                repoItemValues.add(repoItemValue);
                attribute.setRepoItemValues(repoItemValues);
            } else {
                repoItemValues.clear();
                repoItemValues.add(repoItemValue);
            }
        }
    },
    ATTRIBUTE {
        @Override
        protected void setSimpleValue(Attribute attribute, Object value) {
            Attribute valueAsAttribute = (Attribute) value;

            AttributeValue attributeValue = new AttributeValue();
            attributeValue.setValue(valueAsAttribute);
            attributeValue.setOwnerAttribute(attribute);

            setValue(attribute, attributeValue);
        }

        @Override
        protected void setValue(Attribute attribute, IValue<?> value) {
            AttributeValue attributeValue = (AttributeValue) value;

            Set<AttributeValue> attributeValues = attribute.getAttributeValues();
            if (attributeValues == null) {
                attributeValues = new HashSet<AttributeValue>();
                attributeValues.add(attributeValue);
                attribute.setAttributeValues(attributeValues);
            } else {
                attributeValues.clear();
                attributeValues.add(attributeValue);
            }
        }
    };

    public static void setValue(Object value, Attribute attribute) {
        if (value != null) {
            if (value instanceof String) {
                STRING.setSimpleValue(attribute, value);
            } else if (value instanceof StringValue) {
                STRING.setValue(attribute, (IValue<?>) value);
            } else if (value instanceof RepoItem) {
                REPO_ITEM.setSimpleValue(attribute, value);
            } else if (value instanceof RepoItemValue) {
                REPO_ITEM.setValue(attribute, (IValue<?>) value);
            } else if (value instanceof Attribute) {
                ATTRIBUTE.setSimpleValue(attribute, value);
            } else if (value instanceof AttributeValue) {
                ATTRIBUTE.setValue(attribute, (IValue<?>) value);
            } else {
                // TODO Have an appropriate exception
                throw new IllegalArgumentException();
            }
        } else {
            attribute.clearValues();
        }
    }

    protected abstract void setSimpleValue(Attribute attribute, Object value);

    protected abstract void setValue(Attribute attribute, IValue<?> value);
}
