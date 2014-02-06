package vrds.model;

import java.io.Serializable;
import java.util.HashSet;
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
    private Set<RepoItemAttribute> repoItemAttributes = new HashSet<>();

    public RepoItemAttribute getAttribute(String attributeName) {
        return Util.getAttribute(attributeName, repoItemAttributes);
    }

    public boolean hasAttribute(String attributeName) {
        boolean hasAttribute;

        if (getAttribute(attributeName) == null) {
            hasAttribute = false;
        } else {
            hasAttribute = true;
        }

        return hasAttribute;
    }

    public Object getValue(String attributeName) {
        Object attributeValue = getAttribute(attributeName).getValue();

        return attributeValue;
    }

    public <T, W extends IValueWrapper<T>> T getValue(String attributeName, AttributeValueHandler<T, W> attributeValueHandler) {
        return attributeValueHandler.getValue(getAttribute(attributeName));
    }

    public <T, W extends IValueWrapper<T>> Set<T> getValues(String attributeName, AttributeValueHandler<T, W> attributeValueHandler) {
        Set<T> values = attributeValueHandler.getValues(getAttribute(attributeName));

        return values;
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

    public IValueWrapper<?> addValue(String attributeName, Object value) {
        IValueWrapper<?> valueWrapper = null;

        for (RepoItemAttribute currentAttribute : repoItemAttributes) {
            String currentAttributeName = currentAttribute.getName();
            if (attributeName.equals(currentAttributeName)) {
                valueWrapper = currentAttribute.addValue(value);
                break;
            }
        }

        return valueWrapper;
    }

    public IValueWrapper<Object> getValueWrapper(String attributeName, RepoItem benefactor) {
        IValueWrapper<Object> resultValueWrapper = null;

        Set<IValueWrapper<Object>> valueWrappers = getValueWrappers(attributeName);

        if (valueWrappers != null) {
            for (IValueWrapper<Object> valueWrapper : valueWrappers) {
                if (isValueOwnOrDoesBenefactorMatch(benefactor, valueWrapper)) {
                    resultValueWrapper = valueWrapper;
                }
            }
        }

        return resultValueWrapper;
    }

    public Set<IValueWrapper<Object>> getValueWrappers(String attributeName) {
        Set<IValueWrapper<Object>> values = getAttribute(attributeName).getValues();

        return values;
    }

    private boolean isValueOwnOrDoesBenefactorMatch(RepoItem benefactor, IValueWrapper<Object> valueWrapper) {
        RepoItem ourBenefactor = valueWrapper.getBenefactor();

        boolean isValueOwn = benefactor == null && ourBenefactor == null;
        boolean doesBenefactorMatch = benefactor != null && benefactor.equals(ourBenefactor);

        return isValueOwn || doesBenefactorMatch;
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
