package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("REPO")
public class RepoItemAttribute extends Attribute {
    @ManyToOne
    private RepoAttributeDefinition repoAttributeDefinition;
    @ManyToOne
    private RepoItem repoItem;

    public RepoAttributeDefinition getRepoAttributeDefinition() {
        return repoAttributeDefinition;
    }

    public void setRepoAttributeDefinition(RepoAttributeDefinition repoAttributeDefinition) {
        this.repoAttributeDefinition = repoAttributeDefinition;
    }

    public RepoItem getRepoItem() {
        return repoItem;
    }

    public void setRepoItem(RepoItem repoItem) {
        this.repoItem = repoItem;
    }

    @Override
    public String toString() {
        return "RepoItemAttribute [repoAttributeDefinitionName=" + (repoAttributeDefinition == null ? "N/A" : repoAttributeDefinition.getName())
                + ", repoItemId=" + (repoItem == null ? "N/A" : repoItem.getId()) + ", id=" + id + ", stringValues=" + stringValues + ", repoItemValues="
                + repoItemValues + "]";
    }

}
