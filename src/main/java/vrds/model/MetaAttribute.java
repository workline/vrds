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
    private MetaAttributeDefinition definition;
    @ManyToOne
    private Attribute ownerAttribute;

    @JsonIgnore
    @Override
    public MetaAttributeDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(MetaAttributeDefinition definition) {
        this.definition = definition;
    }

    @JsonIgnore
    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @Override
    protected String getToStringPrefix() {
        return "MetaAttribute [definitionName=" + (definition == null ? "N/A" : definition.getName()) + ", " + "ownerAttributeId="
                + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", id=" + id;
    }
}
