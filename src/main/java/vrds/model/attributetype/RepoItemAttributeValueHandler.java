package vrds.model.attributetype;

import java.util.Set;

import vrds.model.Attribute;
import vrds.model.EAttributeType;
import vrds.model.RepoItem;
import vrds.model.RepoItemValue;
import vrds.model.meta.TODO;
import vrds.model.meta.TODOTag;

@TODO(tags = { TODOTag.SINGLETON })
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
    public void setValues(Attribute attribute, Set<RepoItemValue> valueWrappers) {
        attribute.setRepoItemValues(valueWrappers);
    }

    @Override
    protected Set<RepoItemValue> getValueWrappers(Attribute attribute) {
        return attribute.getRepoItemValues();
    }

    @Override
    protected RepoItemValue getNewValueWrapper() {
        return new RepoItemValue();
    }
}
