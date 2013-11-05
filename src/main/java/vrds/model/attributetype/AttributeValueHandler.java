package vrds.model.attributetype;

import java.util.HashSet;
import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.IValue;
import vrds.model.meta.TODO;
import vrds.model.meta.TODOTag;

public abstract class AttributeValueHandler<T, W extends IValue<T>> {
    public abstract EAttributeType getEAttributeType();

    @TODO(tags = { TODOTag.CAST })
    public T getValue(Attribute attribute) {
        @SuppressWarnings("unchecked")
        T value = (T) attribute.getValue();

        return value;
    }

    @TODO(tags = { TODOTag.CAST })
    public void setValue(@SuppressWarnings("rawtypes") IValue valueWrapper, Attribute attribute) {
        @SuppressWarnings("unchecked")
        W typedWrapper = (W) valueWrapper;

        setValue(attribute, typedWrapper);
    }

    public void setValue(Attribute attribute, W valueWrapper) {
        Set<W> valueWrappers = getValueWrappers(attribute);
        if (valueWrappers == null) {
            valueWrappers = new HashSet<>();
            valueWrappers.add(valueWrapper);

            setValue(attribute, valueWrapper);
        } else {
            valueWrappers.clear();
            valueWrappers.add(valueWrapper);
        }
    }

    public void setSimpleValue(Attribute attribute, T value) {
        W valueWrapper = getNewValueWrapper();
        valueWrapper.setValue(value);
        valueWrapper.setOwnerAttribute(attribute);

        setValue(attribute, valueWrapper);
    }

    @TODO(tags = { TODOTag.CAST })
    public void setSimpleValue(Object value, Attribute attribute) {
        // TODO check and throw appropriate exception maybe
        @SuppressWarnings("unchecked")
        T typedValue = (T) value;

        setSimpleValue(attribute, typedValue);
    }

    protected abstract W getNewValueWrapper();

    protected abstract Set<W> getValueWrappers(Attribute attribute);

    protected abstract void setValues(Attribute attribute, Set<W> valueWrappers);
}
