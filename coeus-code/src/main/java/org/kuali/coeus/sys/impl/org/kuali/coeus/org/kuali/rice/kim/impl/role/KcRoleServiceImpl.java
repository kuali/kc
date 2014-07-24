package org.kuali.coeus.sys.impl.org.kuali.coeus.org.kuali.rice.kim.impl.role;

import org.kuali.rice.kim.impl.role.RoleServiceImpl;
import org.kuali.rice.core.api.criteria.Predicate;
import java.util.Map;

/**
 * Created by gathreya on 7/18/14.
 */
public class KcRoleServiceImpl extends RoleServiceImpl {

    @Override
    protected Predicate getRoleQualificationPredicate(Map<String, String> qualification) {
        return null;
    }
}
