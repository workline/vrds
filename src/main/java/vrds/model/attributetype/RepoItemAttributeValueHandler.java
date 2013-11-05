package vrds.model.attributetype;

import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.RepoItem;
import vrds.model.RepoItemValue;

public class RepoItemAttributeValueHandler extends AttributeValueHandler<RepoItem, RepoItemValue> {
    private RepoItemAttributeValueHandler() {
    }

    private static final RepoItemAttributeValueHandler instance = new RepoItemAttributeValueHandler();

    public static RepoItemAttributeValueHandler getInstance() {
        return instance;
    }

    @Override
    public EAttributeType getEAttributeType() {
        return EAttributeType.REPO_ITEM;
    }

    @Override
    protected Set<RepoItemValue> getValueWrappers(Attribute attribute) {
        return attribute.getRepoItemValues();
    }

    @Override
    protected void setValues(Attribute attribute, Set<RepoItemValue> valueWrappers) {
        attribute.setRepoItemValues(valueWrappers);
    }

    @Override
    protected RepoItemValue getNewValueWrapper() {
        return new RepoItemValue();
    }
}
