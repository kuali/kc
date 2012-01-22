/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.proposallog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;
import org.kuali.rice.krad.service.DocumentService;

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
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
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

}
