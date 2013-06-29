package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("META")
public class MetaAttribute extends Attribute {
    @ManyToOne
    private MetaAttributeDefinition metaAttributeDefinition;
    @ManyToOne
    private Attribute ownerAttribute;

    public MetaAttributeDefinition getMetaAttributeDefinition() {
        return metaAttributeDefinition;
    }

    public void setMetaAttributeDefinition(MetaAttributeDefinition repoAttributeDefinition) {
        this.metaAttributeDefinition = repoAttributeDefinition;
    }

    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @Override
    public String toString() {
        return "MetaAttribute [metaAttributeDefinitionName=" + (metaAttributeDefinition == null ? "N/A" : metaAttributeDefinition.getName()) + ", "
                + "ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", id=" + id + ", stringValues=" + stringValues
                + ", repoItemValues=" + repoItemValues + "]";
    }

}
