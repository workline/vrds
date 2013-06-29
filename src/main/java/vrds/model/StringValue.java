package vrds.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class StringValue implements IValue<String> {
    @Id
    @GeneratedValue
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
