package vrds.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import vrds.model.attributetype.AttributeValueHandler;
import vrds.model.meta.LambdaCandidate;
import vrds.model.meta.LambdaCandidateTag;
import vrds.model.meta.TODO;
import vrds.util.Util;

@Entity
@Table(name = "repo_item")
@SequenceGenerator(name = "repoItemIdSequenceGenerator", sequenceName = "SEQ_REPO_ITEM_ID", initialValue = 1, allocationSize = 1000)
public class RepoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repoItemIdSequenceGenerator")
    private Long id;

    private String repoName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "repoItem")
    private Set<RepoItemAttribute> repoItemAttributes;

    public RepoItemAttribute getAttribute(String attributeName) {
        return Util.getAttribute(attributeName, repoItemAttributes);
    }

    public Object getValue(String attributeName) {
        Object attributeValue = getAttribute(attributeName).getValue();

        return attributeValue;
    }

    public <T, W extends IValue<T>> T getValue(String attributeName, AttributeValueHandler<T, W> attributeValueHandler) {
        return attributeValueHandler.getValue(getAttribute(attributeName));
    }

    public Set<IValue<Object>> getValues(String attributeName) {
        Set<IValue<Object>> attributeValues = getAttribute(attributeName).getValues();

        return attributeValues;
    }

    @SuppressWarnings("unchecked")
    public <T> T getTypedValue(String attributeName) {
        return (T) getValue(attributeName);
    }

    @TODO("Maybe should throw an exception when the attribute doesn't exist or access is not granted.")
    @LambdaCandidate(tags = { LambdaCandidateTag.GET_SET_ATTRIBUTE_VALUE })
    public boolean setValue(String attributeName, Object value) {
        boolean valueHasBeenSet = false;

        for (RepoItemAttribute currentAttribute : repoItemAttributes) {
            String currentAttributeName = currentAttribute.getName();
            if (attributeName.equals(currentAttributeName)) {
                currentAttribute.setValue(value);
                valueHasBeenSet = true;
                break;
            }
        }

        return valueHasBeenSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Set<RepoItemAttribute> getRepoItemAttributes() {
        return repoItemAttributes;
    }

    public void setRepoItemAttributes(Set<RepoItemAttribute> attributes) {
        this.repoItemAttributes = attributes;
        for (RepoItemAttribute attribute : attributes) {
            attribute.setRepoItem(this);
        }
    }

    @Override
    public String toString() {
        return "RepoItem [id=" + id + ", repoName=" + repoName + ", repoItemAttributes=" + repoItemAttributes + "]";
    }

}
