package org.kuali.kra.protocol;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.RoleConstants;

import java.util.ArrayList;
import java.util.List;

public class ProtocolAuthorizationServiceImpl implements ProtocolAuthorizationService {

    private KcAuthorizationService kcAuthorizationService;

    @Override
    public List<RolePersons> getAllRolePersons(Permissionable permissionable) {
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();

        if(permissionable != null) {
            List<String> roleNames = permissionable.getRoleNames();

            for (String roleName : roleNames) {
                List<String> usernames = kcAuthorizationService.getUserNames(permissionable, roleName);
                RolePersons rolePersons = new RolePersons();
                rolePersonsList.add(rolePersons);

                if(roleName.contains(RoleConstants.AGGREGATOR)) {
                    rolePersons.setAggregator(usernames);
                } else if(roleName.contains(RoleConstants.VIEWER)) {
                    rolePersons.setViewer(usernames);
                } else if(roleName.contains(RoleConstants.NARRATIVE_WRITER)) {
                    rolePersons.setNarrativewriter(usernames);
                } else if(roleName.contains(RoleConstants.BUDGET_CREATOR)) {
                    rolePersons.setBudgetcreator(usernames);
                }
            }
        }

        return rolePersonsList;
    }

    public KcAuthorizationService getKcAuthorizationService() {
        return kcAuthorizationService;
    }

    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }
}
