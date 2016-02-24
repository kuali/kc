/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.protocol;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;

import java.util.ArrayList;
import java.util.List;

public class ProtocolAuthorizationServiceImpl implements ProtocolAuthorizationService {

    private KcAuthorizationService kcAuthorizationService;
    private IdentityService identityService;

    @Override
    public List<RolePersons> getAllRolePersons(Permissionable permissionable) {
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();

        if(permissionable != null) {
            List<String> roleNames = permissionable.getRoleNames();

            for (String roleName : roleNames) {
                List<String> usernames = toUserNames(kcAuthorizationService.getPrincipalsInRole(roleName, permissionable));
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

    protected List<String> toUserNames(List<String> principalIds) {
        final List<String> userNames = new ArrayList<>();
        for (String id : principalIds) {
            Principal p = identityService.getPrincipal(id);
            if (p != null) {
                userNames.add(p.getPrincipalName());
            }
        }

        return userNames;
    }

    public KcAuthorizationService getKcAuthorizationService() {
        return kcAuthorizationService;
    }

    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
}
