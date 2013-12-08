package vrds.model.attributetype;

import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.StringValue;
import vrds.model.meta.TODO;
import vrds.model.meta.TODOTag;

@TODO(tags = { TODOTag.SINGLETON })
public class StringAttributeValueHandler extends AttributeValueHandler<String, StringValue> {
    private StringAttributeValueHandler() {
    }

    private static final StringAttributeValueHandler instance = new StringAttributeValueHandler();

    public static StringAttributeValueHandler getInstance() {
        return instance;
    }

    @Override
    public EAttributeType getEAttributeType() {
        return EAttributeType.STRING;
    }

    @Override
    public void setValues(Attribute attribute, Set<StringValue> valueWrappers) {
        attribute.setStringValues(valueWrappers);
    }

    @Override
    protected Set<StringValue> getValueWrappers(Attribute attribute) {
        return attribute.getStringValues();
    }

    @Override
    protected StringValue getNewValueWrapper() {
        return new StringValue();
    }
}
