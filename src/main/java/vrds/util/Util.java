package vrds.util;

import java.util.Iterator;
import java.util.Set;

import vrds.meta.LambdaCandidate;
import vrds.meta.LambdaCandidateTag;
import vrds.model.Attribute;

public final class Util {
    private Util() {
    }

    @LambdaCandidate(tags = { LambdaCandidateTag.GET_SET_ATTRIBUTE_VALUE })
    public static <T extends Attribute> T getAttribute(String queryAttributeName, Set<T> attributes) {
        T attribute;

        if (queryAttributeName == null || "".equals(queryAttributeName.trim())) {
            attribute = null;
        } else {
            attribute = null;

            boolean found = false;
            Iterator<T> attributesIterator = attributes.iterator();

            while(!found && attributesIterator.hasNext()) {
                T currentAttribute = attributesIterator.next();
                String currentAttributeName = currentAttribute.getName();

                if (queryAttributeName.equals(currentAttributeName)) {
                    attribute = currentAttribute;
                    found = true;
                }
            }
        }

        return attribute;
    }
}
