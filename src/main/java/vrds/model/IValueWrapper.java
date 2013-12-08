package vrds.model;

public interface IValueWrapper<T> {
    T getValue();

    void setValue(T value);

    Attribute getOwnerAttribute();

    void setOwnerAttribute(Attribute attribute);

    RepoItem getBenefactor();

    void setBenefactor(RepoItem benefactor);
}
