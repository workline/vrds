package vrds.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SequenceGenerator(name = "stringValueIdSequenceGenerator", sequenceName = "SEQ_STRING_VALUE_ID", initialValue = 1, allocationSize = 1000)
public class StringValue implements IValue<String>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stringValueIdSequenceGenerator")
	private Long id;
	private String value;
	@ManyToOne
	private Attribute ownerAttribute;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@JsonIgnore
	public Attribute getOwnerAttribute() {
		return ownerAttribute;
	}

	public void setOwnerAttribute(Attribute attribute) {
		this.ownerAttribute = attribute;
	}

	@Override
	public String toString() {
		return "StringValue [id=" + id + ", value=" + value
				+ ", ownerAttributeId="
				+ (ownerAttribute == null ? "N/A" : ownerAttribute.getId())
				+ "]";
	}

}
