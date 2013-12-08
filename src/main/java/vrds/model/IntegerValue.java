package vrds.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class IntegerValue implements IValueWrapper<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private Long value;
    @ManyToOne
    @JoinColumn(name = "owner_attribute_id")
    private Attribute ownerAttribute;
    @ManyToOne
    @JoinColumn(name = "benefactor_repo_item_id")
    private RepoItem benefactor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public void setValue(Long value) {
        this.value = value;
    }

    @JsonIgnore
    @Override
    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    @Override
    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @JsonIgnore
    @Override
    public RepoItem getBenefactor() {
        return benefactor;
    }

    @Override
    public void setBenefactor(RepoItem benefactor) {
        this.benefactor = benefactor;
    }

    @Override
    public String toString() {
        return "StringValue [id=" + id + ", value=" + value + ", attributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", benefactorId="
                + (benefactor == null ? "N/A" : benefactor.getId()) + "]";
    }

}
