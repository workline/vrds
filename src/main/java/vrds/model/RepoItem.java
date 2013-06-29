package vrds.model;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RepoItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private RepoDefinition repoDefinition;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "repoItem")
    private Set<RepoItemAttribute> repoItemAttributes;

    @SuppressWarnings("unchecked")
    public <T> T getAttributeTypedValue(String attributeDefinitionName) {
        return (T) getRepoItemAttributeValue(attributeDefinitionName);
    }

    public Object getRepoItemAttributeValue(String attributeDefinitionName) {
        Object attributeValue;

        if (attributeDefinitionName == null || "".equals(attributeDefinitionName.trim())) {
            attributeValue = null;
        } else {
            attributeValue = null;

            boolean found = false;
            Iterator<RepoItemAttribute> attributesIterator = repoItemAttributes.iterator();

            while(!found && attributesIterator.hasNext()) {
                RepoItemAttribute currentAttribute = attributesIterator.next();
                String currentAttributeDefinitionName = currentAttribute.getRepoAttributeDefinition().getName();

                if (attributeDefinitionName.equals(currentAttributeDefinitionName)) {
                    attributeValue = currentAttribute.getValue();
                    found = true;
                }
            }
        }

        return attributeValue;
    }

    public void setRepoItemAttributeValue(String attributeDefinitionName, Object value) {
        for (RepoItemAttribute currentAttribute : repoItemAttributes) {
            String currentAttributeDefinitionName = currentAttribute.getRepoAttributeDefinition().getName();
            if (attributeDefinitionName.equals(currentAttributeDefinitionName)) {
                currentAttribute.setValue(value);
                break;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RepoDefinition getRepoDefinition() {
        return repoDefinition;
    }

    public void setRepoDefinition(RepoDefinition repoDefinition) {
        this.repoDefinition = repoDefinition;
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
        return "RepoItem [id=" + id + ", repoDefinitionName=" + (repoDefinition == null ? "N/A" : repoDefinition.getName()) + ", repoItemAttributes="
                + repoItemAttributes + "]";
    }

}
