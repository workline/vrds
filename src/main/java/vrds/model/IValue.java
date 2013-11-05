package vrds.model;

public interface IValue<T> {
    T getValue();

    void setValue(T value);

    void setOwnerAttribute(Attribute attribute);
}
