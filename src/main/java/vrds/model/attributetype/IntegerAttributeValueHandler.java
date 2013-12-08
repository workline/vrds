package vrds.model.attributetype;

import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.IntegerValue;
import vrds.model.meta.TODO;
import vrds.model.meta.TODOTag;

@TODO(tags = { TODOTag.SINGLETON })
public class IntegerAttributeValueHandler extends AttributeValueHandler<Long, IntegerValue> {
    private IntegerAttributeValueHandler() {
    }

    private static final IntegerAttributeValueHandler instance = new IntegerAttributeValueHandler();

    public static IntegerAttributeValueHandler getInstance() {
        return instance;
    }

    @Override
    public EAttributeType getEAttributeType() {
        return EAttributeType.STRING;
    }

    @Override
    public void setValues(Attribute attribute, Set<IntegerValue> valueWrappers) {
        attribute.setLongValues(valueWrappers);
    }

    @Override
    protected Set<IntegerValue> getValueWrappers(Attribute attribute) {
        return attribute.getLongValues();
    }

    @Override
    protected IntegerValue getNewValueWrapper() {
        return new IntegerValue();
    }
}
