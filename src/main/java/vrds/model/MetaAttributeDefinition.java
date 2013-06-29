package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("META")
public class MetaAttributeDefinition extends AttributeDefinition {
    @ManyToOne
    private AttributeDefinition ownerAttributeDefinition;

    public AttributeDefinition getOwnerAttributeDefinition() {
        return ownerAttributeDefinition;
    }

    public void setOwnerAttributeDefinition(AttributeDefinition ownerAttributeDefinition) {
        this.ownerAttributeDefinition = ownerAttributeDefinition;
    }

    @Override
    public String toString() {
        return "MetaAttributeDefinition [ownerAttributeDefinitionName=" + (ownerAttributeDefinition == null ? "N/A" : ownerAttributeDefinition.getName())
                + ", id=" + id + ", name=" + name + ", type=" + type + ", valueRepoType=" + valueRepoType + ", multiValue=" + multiValue + ", mandatory="
                + mandatory + ", metaAttributeDefinitions=" + metaAttributeDefinitions + "]";
    }

}
