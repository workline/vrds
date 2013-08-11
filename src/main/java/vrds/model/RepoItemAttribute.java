package vrds.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@DiscriminatorValue("REPO")
public class RepoItemAttribute extends Attribute {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    private RepoAttributeDefinition definition;
    @ManyToOne
    private RepoItem repoItem;

    @JsonIgnore
    @Override
    public RepoAttributeDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(RepoAttributeDefinition definition) {
        this.definition = definition;
    }

    @JsonIgnore
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
