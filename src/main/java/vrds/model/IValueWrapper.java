package vrds.model;

public interface IValueWrapper<T> {
    T getValue();

    void setValue(T value);

    Attribute getOwnerAttribute();

    void setOwnerAttribute(Attribute attribute);

    RepoItem getInheritenceSource();

    void setInheritenceSource(RepoItem inheritenceSource);
}
