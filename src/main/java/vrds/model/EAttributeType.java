package vrds.model;

import vrds.model.attributetype.AttributeAttributeValueHandler;
import vrds.model.attributetype.AttributeValueHandler;
import vrds.model.attributetype.LongAttributeValueHandler;
import vrds.model.attributetype.RepoItemAttributeValueHandler;
import vrds.model.attributetype.StringAttributeValueHandler;
import vrds.model.meta.Coupling;
import vrds.model.meta.CouplingTag;

@Coupling(tags = { CouplingTag.ATTRIBUTE_TYPE })
public enum EAttributeType {
    STRING(StringAttributeValueHandler.getInstance()), INTEGER(LongAttributeValueHandler.getInstance()), DECIMAL(null), DATE(null), TIME(null), DATETIME(null), REPO_ITEM(
            RepoItemAttributeValueHandler.getInstance()), ATTRIBUTE(AttributeAttributeValueHandler.getInstance());

    private final AttributeValueHandler<?, ?> attributeValueHandler;

    private EAttributeType(AttributeValueHandler<?, ?> attributeType) {
        this.attributeValueHandler = attributeType;
    }

    public AttributeValueHandler<?, ?> getAttributeValueHandler() {
        return attributeValueHandler;
    }
}
