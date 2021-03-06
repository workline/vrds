package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@DiscriminatorValue("META")
public class MetaAttribute extends Attribute {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "owner_attribute_id")
    private Attribute ownerAttribute;

    @JsonIgnore
    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @Override
    protected String getInheritedToStringPart() {
        return "ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId().toString());
    }
}
