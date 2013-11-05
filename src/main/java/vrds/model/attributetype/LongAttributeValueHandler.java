package vrds.model.attributetype;

import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.LongValue;

public class LongAttributeValueHandler extends AttributeValueHandler<Long, LongValue> {
    private LongAttributeValueHandler() {
    }

    private static final LongAttributeValueHandler instance = new LongAttributeValueHandler();

    public static LongAttributeValueHandler getInstance() {
        return instance;
    }

    @Override
    public EAttributeType getEAttributeType() {
        return EAttributeType.STRING;
    }

    @Override
    protected Set<LongValue> getValueWrappers(Attribute attribute) {
        return attribute.getLongValues();
    }

    @Override
    protected void setValues(Attribute attribute, Set<LongValue> valueWrappers) {
        attribute.setLongValues(valueWrappers);
    }

    @Override
    protected LongValue getNewValueWrapper() {
        return new LongValue();
    }
}
