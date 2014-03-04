/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;

import java.util.Map;

public class ProposalLogDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase implements MaintenanceDocumentAuthorizer {
    
    @Override
    protected void addRoleQualification(Object primaryBusinessObjectOrDocument, Map<String, String> attributes) {
        super.addRoleQualification(primaryBusinessObjectOrDocument, attributes);
        ProposalLog proposalLog;
        if (primaryBusinessObjectOrDocument instanceof MaintenanceDocument) {
            MaintenanceDocument maintenanceDocument = (MaintenanceDocument) primaryBusinessObjectOrDocument;
            proposalLog = (ProposalLog) maintenanceDocument.getDocumentBusinessObject();
        } else {
            proposalLog = (ProposalLog) primaryBusinessObjectOrDocument;
        }
        
        if (!StringUtils.isBlank(proposalLog.getLeadUnit()) && proposalLog.isPersisted()) {
            attributes.put(KcKimAttributes.UNIT_NUMBER, proposalLog.getLeadUnit());
        } else {
            attributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        }
        attributes.put("piId", proposalLog.getPiId());
    }
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        boolean retVal = this.isAuthorized(new ProposalLog(), "KC-IP",  "Create Proposal Log", user.getPrincipalId());        
        return retVal;
    }   
}