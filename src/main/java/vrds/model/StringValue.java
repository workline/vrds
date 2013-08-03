package vrds.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SequenceGenerator(name = "stringValueIdSequenceGenerator", sequenceName = "SEQ_STRING_VALUE_ID", initialValue = 1, allocationSize = 1000)
public class StringValue implements IValue<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stringValueIdSequenceGenerator")
    private Long id;
    private String value;
    @ManyToOne
    private Attribute attribute;

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
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "StringValue [id=" + id + ", value=" + value + ", attributeId=" + (attribute == null ? "N/A" : attribute.getId()) + "]";
    }

}
