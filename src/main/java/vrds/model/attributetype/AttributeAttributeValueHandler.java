package vrds.model.attributetype;

import java.util.Set;

import vrds.model.Attribute;
import vrds.model.AttributeValue;
import vrds.model.EAttributeType;
import vrds.model.meta.TODO;
import vrds.model.meta.TODOTag;

@TODO(tags = { TODOTag.SINGLETON })
public class AttributeAttributeValueHandler extends AttributeValueHandler<Attribute, AttributeValue> {
    private AttributeAttributeValueHandler() {
    }

    private static final AttributeAttributeValueHandler instance = new AttributeAttributeValueHandler();

    public static AttributeAttributeValueHandler getInstance() {
        return instance;
    }

    @Override
    public EAttributeType getEAttributeType() {
        return EAttributeType.ATTRIBUTE;
    }

    @Override
    public void setValues(Attribute attribute, Set<AttributeValue> valueWrappers) {
        attribute.setAttributeValues(valueWrappers);
    }

    @Override
    protected Set<AttributeValue> getValueWrappers(Attribute attribute) {
        return attribute.getAttributeValues();
    }

    @Override
    protected AttributeValue getNewValueWrapper() {
        return new AttributeValue();
    }
}
