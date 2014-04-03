package vrds.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class AttributeValue implements IValueWrapper<Attribute>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "value_attribute_id")
    private Attribute value;

    @ManyToOne
    @JoinColumn(name = "owner_attribute_id")
    private Attribute ownerAttribute;
    @ManyToOne
    @JoinColumn(name = "inheritence_source_repo_item_id")
    private RepoItem inheritenceSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public Attribute getValue() {
        return value;
    }

    @Override
    public void setValue(Attribute value) {
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
    public RepoItem getInheritenceSource() {
        return inheritenceSource;
    }

    @Override
    public void setInheritenceSource(RepoItem inheritenceSource) {
        this.inheritenceSource = inheritenceSource;
    }

    @Override
    public String toString() {
        return "RepoItemValue [id=" + id + ", ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", valueId="
                + (value == null ? "N/A" : value.getId()) + ", inheritenceSourceId=" + (inheritenceSource == null ? "N/A" : inheritenceSource.getId()) + "]";
    }

}
