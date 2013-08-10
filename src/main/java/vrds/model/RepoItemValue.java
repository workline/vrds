package vrds.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class RepoItemValue implements IValue<RepoItem> {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Attribute ownerAttribute;
    @ManyToOne
    private RepoItem value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @JsonIgnore
    public RepoItem getValue() {
        return value;
    }

    public void setValue(RepoItem value) {
        this.value = value;
    }

    // TODO
    public Long getRepoItemValueId() {
        return value.getId();
    }

    @Override
    public String toString() {
        return "RepoItemValue [id=" + id + ", ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", valueId="
                + (value == null ? "N/A" : value.getId()) + "]";
    }

}
