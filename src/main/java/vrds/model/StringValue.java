package vrds.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SequenceGenerator(name = "stringValueIdSequenceGenerator", sequenceName = "SEQ_STRING_VALUE_ID", initialValue = 1, allocationSize = 1000)
public class StringValue implements IValueWrapper<String>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stringValueIdSequenceGenerator")
    private Long id;
    @Column(length = 4000)
    private String value;

    @ManyToOne
    @JoinColumn(name = "owner_attribute_id")
    private Attribute ownerAttribute;
    @ManyToOne
    @JoinColumn(name = "inheritence_source_repo_item_id")
    private RepoItem inheritenceSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    @Override
    public Attribute getOwnerAttribute() {
        return ownerAttribute;
    }

    @Override
    public void setOwnerAttribute(Attribute attribute) {
        this.ownerAttribute = attribute;
    }

    @JsonIgnore
    @Override
    public RepoItem getInheritenceSource() {
        return inheritenceSource;
    }

    @Override
    public void setInheritenceSource(RepoItem inheritenceSource) {
        this.inheritenceSource = inheritenceSource;
    }

    @Override
    public String toString() {
        return "StringValue [id=" + id + ", ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", value=" + value
                + ", inheritenceSourceId=" + (inheritenceSource == null ? "N/A" : inheritenceSource.getId()) + "]";
    }

}
