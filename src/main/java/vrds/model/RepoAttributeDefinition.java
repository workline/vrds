package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@DiscriminatorValue("REPO")
public class RepoAttributeDefinition extends AttributeDefinition {
    @ManyToOne
    private RepoDefinition ownerRepoDefinition;

    @JsonIgnore
    public RepoDefinition getOwnerRepoDefinition() {
        return ownerRepoDefinition;
    }

    public void setOwnerRepoDefinition(RepoDefinition repoDefinition) {
        this.ownerRepoDefinition = repoDefinition;
    }

    @Override
    public String toString() {
        return "RepoAttributeDefinition [ownerRepoDefinitionName=" + (ownerRepoDefinition == null ? "N/A" : ownerRepoDefinition.getName()) + ", id=" + id
                + ", name=" + name + ", type=" + type + ", valueRepoType=" + valueRepoType + ", multiValue=" + multiValue + ", mandatory=" + mandatory
                + ", metaAttributeDefinitions=" + metaAttributeDefinitions + "]";
    }

}
