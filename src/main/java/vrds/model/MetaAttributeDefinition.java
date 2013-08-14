package vrds.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("META")
public class MetaAttributeDefinition extends AttributeDefinition {
    private static final long serialVersionUID = 1L;

    @ManyToMany(mappedBy = "metaAttributeDefinitions")
    private Set<AttributeDefinition> ownerAttributeDefinitions;

    public Set<AttributeDefinition> getOwnerAttributeDefinitions() {
        return ownerAttributeDefinitions;
    }

    @Override
    public String toString() {
        return "MetaAttributeDefinition [ownerAttributeDefinitionNr=" + (ownerAttributeDefinitions == null ? "N/A" : ownerAttributeDefinitions.size())
                + ", id=" + id + ", name=" + name + ", type=" + type + ", valueRepoType=" + valueRepoType + ", multiValue=" + multiValue + ", mandatory="
                + mandatory + ", metaAttributeDefinitions=" + metaAttributeDefinitions + "]";
    }

}
