package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@DiscriminatorValue("META")
public class MetaAttribute extends Attribute {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Attribute ownerAttribute;

    @JsonIgnore
    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @Override
    protected String getInheritedToString() {
        return "ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId().toString());
    }
}
