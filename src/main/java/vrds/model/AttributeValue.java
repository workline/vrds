package vrds.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class AttributeValue implements IValue<Attribute>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Attribute ownerAttribute;
    @ManyToOne
    private Attribute value;

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
    public Attribute getValue() {
        return value;
    }

    public void setValue(Attribute value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RepoItemValue [id=" + id + ", ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", valueId="
                + (value == null ? "N/A" : value.getId()) + "]";
    }

}
