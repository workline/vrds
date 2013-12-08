package vrds.model;

import java.io.Serializable;

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
    private String value;

    @ManyToOne
    @JoinColumn(name = "owner_attribute_id")
    private Attribute ownerAttribute;
    @ManyToOne
    @JoinColumn(name = "benefactor_repo_item_id")
    private RepoItem benefactor;

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
    public RepoItem getBenefactor() {
        return benefactor;
    }

    @Override
    public void setBenefactor(RepoItem benefactor) {
        this.benefactor = benefactor;
    }

    @Override
    public String toString() {
        return "StringValue [id=" + id + ", ownerAttributeId=" + (ownerAttribute == null ? "N/A" : ownerAttribute.getId()) + ", value=" + value
                + ", benefactorId=" + (benefactor == null ? "N/A" : benefactor.getId()) + "]";
    }

}
