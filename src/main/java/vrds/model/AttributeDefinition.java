package vrds.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Inheritance
@DiscriminatorColumn(name = "ATTRIBUTE_TYPE")
public abstract class AttributeDefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;
	protected String name;
	@Enumerated(EnumType.STRING)
	protected EAttributeType type;
	@ManyToOne
	protected RepoDefinition valueRepoType;
	@Column
	protected Integer multiValue = 0;
	@Column
	protected Integer mandatory = 0;
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "J_META_ATTR_DEF", joinColumns = { @JoinColumn(name = "ATTR_DEF_ID") }, inverseJoinColumns = { @JoinColumn(name = "META_ATTR_DEF_ID") })
	protected Set<MetaAttributeDefinition> metaAttributeDefinitions;

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

	public EAttributeType getType() {
		return type;
	}

	public void setType(EAttributeType type) {
		this.type = type;
	}

	@JsonIgnore
	public RepoDefinition getValueRepoType() {
		return valueRepoType;
	}

	public void setValueRepoType(RepoDefinition repoDefinition) {
		this.valueRepoType = repoDefinition;
	}

	public Integer getMultiValue() {
		return multiValue;
	}

	public void setMultiValue(Integer multiple) {
		this.multiValue = multiple;
	}

	public Integer getMandatory() {
		return mandatory;
	}

	public void setMandatory(Integer mandatory) {
		this.mandatory = mandatory;
	}

	public Set<MetaAttributeDefinition> getMetaAttributeDefinitions() {
		return metaAttributeDefinitions;
	}

	public void setMetaAttributeDefinitions(
			Set<MetaAttributeDefinition> metaAttributeDefinitions) {
		this.metaAttributeDefinitions = metaAttributeDefinitions;
	}

	@Override
	public String toString() {
		return "AttributeDefinition [id=" + id + ", name=" + name + ", type="
				+ type + ", valueRepoTypeName="
				+ (valueRepoType == null ? "N/A" : valueRepoType.getName())
				+ ", multiValue=" + multiValue + ", mandatory=" + mandatory
				+ ", metaAttributeDefinitions=" + metaAttributeDefinitions
				+ "]";
	}

}
