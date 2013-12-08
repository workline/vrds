package vrds.model.attributetype;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.IValueWrapper;
import vrds.model.RepoItemAttribute;
import vrds.model.meta.CAST;

public abstract class AttributeValueHandler<T, W extends IValueWrapper<T>> {
    public abstract EAttributeType getEAttributeType();

    @CAST
    public T getValue(Attribute attribute) {
        T value;

        if (attribute == null) {
            value = null;
        } else {
            @SuppressWarnings("unchecked")
            T typedValue = (T) attribute.getValue();

            value = typedValue;
        }

        return value;
    }

    public Set<T> getValues(RepoItemAttribute attribute) {
        Set<T> values = new HashSet<>();

        if (attribute != null) {
            Set<W> valueWrappers = getValueWrappers(attribute);
            for (W valueWrapper : valueWrappers) {
                values.add(valueWrapper.getValue());
            }
        }

        return values;
    }

    @CAST
    public void setValue(@SuppressWarnings("rawtypes") IValueWrapper valueWrapper, Attribute attribute) {
        @SuppressWarnings("unchecked")
        W typedWrapper = (W) valueWrapper;

        setValue(attribute, typedWrapper);
    }

    public void setValue(Attribute attribute, W valueWrapper) {
        Set<W> valueWrappers = getValueWrappers(attribute);

        if (valueWrappers == null) {
            valueWrappers = new HashSet<>();
            valueWrappers.add(valueWrapper);
        }

        valueWrappers.clear();
        valueWrappers.add(valueWrapper);
    }

    // TODO MINOR Change to Collection<W>?
    public abstract void setValues(Attribute attribute, Set<W> valueWrappers);

    public void addValue(@SuppressWarnings("rawtypes") IValueWrapper valueWrapper, Attribute attribute) {
        @SuppressWarnings("unchecked")
        W typedWrapper = (W) valueWrapper;

        addValue(attribute, typedWrapper);
    }

    public void addValue(Attribute attribute, W valueWrapper) {
        Set<W> valueWrappers = getValueWrappers(attribute);

        valueWrappers.add(valueWrapper);
    }

    @CAST
    public void setSimpleValue(Object value, Attribute attribute) {
        // TODO check and throw appropriate exception maybe
        @SuppressWarnings("unchecked")
        T typedValue = (T) value;

        setSimpleValue(attribute, typedValue);
    }

    public void setSimpleValue(Attribute attribute, T value) {
        W valueWrapper = getNewValueWrapper();
        valueWrapper.setValue(value);
        valueWrapper.setOwnerAttribute(attribute);

        setValue(attribute, valueWrapper);
    }

    public void setSimpleValues(Attribute attribute, Collection<T> values) {
        HashSet<W> valueWrappers = new HashSet<>();

        for (T value : values) {
            W valueWrapper = getNewValueWrapper();
            valueWrapper.setValue(value);

            valueWrappers.add(valueWrapper);
        }

        setValues(attribute, valueWrappers);
    }

    @CAST
    public IValueWrapper<?> addSimpleValue(Object value, Attribute attribute) {
        @SuppressWarnings("unchecked")
        T typedValue = (T) value;

        IValueWrapper<?> valueWrapper = addSimpleValue(attribute, typedValue);

        return valueWrapper;
    }

    public IValueWrapper<?> addSimpleValue(Attribute attribute, T value) {
        W newValueWrapper = getNewValueWrapper();
        newValueWrapper.setValue(value);
        newValueWrapper.setOwnerAttribute(attribute);

        getValueWrappers(attribute).add(newValueWrapper);

        return newValueWrapper;
    }

    protected abstract W getNewValueWrapper();

    protected abstract Set<W> getValueWrappers(Attribute attribute);
}
