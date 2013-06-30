package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("REPO")
public class RepoItemAttribute extends Attribute {
    @ManyToOne
    private RepoAttributeDefinition definition;
    @ManyToOne
    private RepoItem repoItem;

    @Override
    public RepoAttributeDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(RepoAttributeDefinition definition) {
        this.definition = definition;
    }

    public RepoItem getRepoItem() {
        return repoItem;
    }

    public void setRepoItem(RepoItem repoItem) {
        this.repoItem = repoItem;
    }

    @Override
    public String toString() {
        return "RepoItemAttribute [definitionName=" + (definition == null ? "N/A" : definition.getName()) + ", repoItemId="
                + (repoItem == null ? "N/A" : repoItem.getId()) + ", id=" + id + ", stringValues=" + stringValues + ", repoItemValues=" + repoItemValues + "]";
    }

}
