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

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.KimRoleTypeService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Performs matching logic for Principal Investigator derived role.
 */
public class ProposalLogPiDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase implements KimRoleTypeService {
    
    @Override
    public boolean hasApplicationRole(String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification) {
        String piId = qualification.get("piId");
        return piId != null && piId.equals(principalId);
    }
    
    @Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole( String namespaceCode, String roleName, AttributeSet qualification ) {
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        List<RoleMembershipInfo> roleMembers = new ArrayList<RoleMembershipInfo>();
        try {
            MaintenanceDocument doc = (MaintenanceDocument) docService.getByDocumentHeaderId(qualification.get("documentNumber"));
            ProposalLog pLog = (ProposalLog) doc.getDocumentBusinessObject();
            RoleMembershipInfo rmi = new RoleMembershipInfo(null, null, pLog.getPerson().getPersonId(), "P", null);
            roleMembers.add(rmi);
        } catch (WorkflowException ex) {
            
        }
        return roleMembers;
    }

}
