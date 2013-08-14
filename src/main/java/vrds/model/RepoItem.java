package vrds.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SequenceGenerator(name = "repoItemIdSequenceGenerator", sequenceName = "SEQ_REPO_ITEM_ID", initialValue = 1, allocationSize = 1000)
public class RepoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repoItemIdSequenceGenerator")
    private Long id;

    @ManyToOne
    private RepoDefinition definition;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "repoItem")
    private Set<RepoItemAttribute> repoItemAttributes;

    public RepoItemAttribute getAttribute(String attributeDefinitionName) {
        RepoItemAttribute repoItemAttribute;

        if (attributeDefinitionName == null || "".equals(attributeDefinitionName.trim())) {
            repoItemAttribute = null;
        } else {
            repoItemAttribute = null;

            boolean found = false;
            Iterator<RepoItemAttribute> attributesIterator = repoItemAttributes.iterator();

            while(!found && attributesIterator.hasNext()) {
                RepoItemAttribute currentAttribute = attributesIterator.next();
                String currentAttributeDefinitionName = currentAttribute.getDefinition().getName();

                if (attributeDefinitionName.equals(currentAttributeDefinitionName)) {
                    repoItemAttribute = currentAttribute;
                    found = true;
                }
            }
        }

        return repoItemAttribute;
    }

    public Object getValue(String attributeDefinitionName) {
        Object attributeValue = getAttribute(attributeDefinitionName).getValue();

        return attributeValue;
    }

    public Set<IValue<Object>> getValues(String attributeDefinitionName) {
        Set<IValue<Object>> attributeValues = getAttribute(attributeDefinitionName).getValues();

        return attributeValues;
    }

    @SuppressWarnings("unchecked")
    public <T> T getTypedValue(String attributeDefinitionName) {
        return (T) getValue(attributeDefinitionName);
    }

    public void setValue(String attributeDefinitionName, Object value) {
        for (RepoItemAttribute currentAttribute : repoItemAttributes) {
            String currentAttributeDefinitionName = currentAttribute.getDefinition().getName();
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

    @JsonIgnore
    public RepoDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(RepoDefinition repoDefinition) {
        this.definition = repoDefinition;
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
        return "RepoItem [id=" + id + ", definitionName=" + (definition == null ? "N/A" : definition.getName()) + ", repoItemAttributes=" + repoItemAttributes
                + "]";
    }

}
