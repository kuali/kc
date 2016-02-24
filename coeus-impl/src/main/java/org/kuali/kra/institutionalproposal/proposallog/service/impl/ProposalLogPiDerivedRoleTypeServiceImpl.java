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
package org.kuali.kra.institutionalproposal.proposallog.service.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Performs matching logic for Principal Investigator derived role.
 */
public class ProposalLogPiDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase implements RoleTypeService {
    
    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification) {
        String piId = qualification.get("piId");
        return piId != null && piId.equals(principalId);
    }
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole( String namespaceCode, String roleName, Map<String,String> qualification ) {
        DocumentService docService = KcServiceLocator.getService(DocumentService.class);
        List<RoleMembership> roleMembers = new ArrayList<RoleMembership>();
        try {
            MaintenanceDocument doc = (MaintenanceDocument) docService.getByDocumentHeaderId(qualification.get("documentNumber"));
            ProposalLog pLog = (ProposalLog) doc.getNoteTarget();
            RoleMembership rmi = RoleMembership.Builder.create(null, null, pLog.getPerson().getPersonId(), MemberType.PRINCIPAL, null).build();
            roleMembers.add(rmi);
        } catch (WorkflowException ex) {
            
        }
        return roleMembers;
    }
    
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        return true;
    }    

}
