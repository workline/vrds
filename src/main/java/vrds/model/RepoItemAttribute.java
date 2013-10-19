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
    private RepoItem repoItem;

    @JsonIgnore
    public RepoItem getRepoItem() {
        return repoItem;
    }

    public void setRepoItem(RepoItem repoItem) {
        this.repoItem = repoItem;
    }

    @Override
    protected String getInheritedToString() {
        return "repoItemId=" + (repoItem == null ? "N/A" : repoItem.getId());
    }
}
