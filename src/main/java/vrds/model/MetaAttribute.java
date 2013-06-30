package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("META")
public class MetaAttribute extends Attribute {
    @ManyToOne
    private MetaAttributeDefinition definition;
    @ManyToOne
    private Attribute ownerAttribute;

    @Override
    public MetaAttributeDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(MetaAttributeDefinition definition) {
        this.definition = definition;
    }

    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @Override
    public String toString() {
        return "MetaAttribute [definitionName=" + (definition == null ? "N/A" : definition.getName()) + ", " + "ownerAttributeId="
                + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", id=" + id + ", stringValues=" + stringValues + ", repoItemValues="
                + repoItemValues + "]";
    }

}
