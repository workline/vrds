package vrds.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class RepoDefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ownerRepoDefinition")
	@JsonIgnore
	private Set<RepoAttributeDefinition> repoAttributeDefinitions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RepoAttributeDefinition> getRepoAttributeDefinitions() {
		return repoAttributeDefinitions;
	}

	public void setRepoAttributeDefinitions(
			Set<RepoAttributeDefinition> attributeDefinitions) {
		this.repoAttributeDefinitions = attributeDefinitions;
		for (RepoAttributeDefinition attributeDefinition : attributeDefinitions) {
			attributeDefinition.setOwnerRepoDefinition(this);
		}
	}

	@Override
	public String toString() {
		return "RepoDefinition [id=" + id + ", name=" + name
				+ ", repoAttributeDefinitions=" + repoAttributeDefinitions
				+ "]";
	}

}
