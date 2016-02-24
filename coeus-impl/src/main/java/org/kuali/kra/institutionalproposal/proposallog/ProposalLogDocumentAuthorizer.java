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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
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
        boolean retVal = this.isAuthorized(new ProposalLog(), Constants.INSTITUTIONAL_PROPOSAL_NAMESPACE,  "Create Proposal Log", user.getPrincipalId());        
        return retVal;
    }   
    
    public boolean canOpen(ProposalLog proposalLog, Person user) {
    	return isAuthorized(proposalLog, Constants.INSTITUTIONAL_PROPOSAL_NAMESPACE, "Open Proposal Log", user.getPrincipalId());
    }
}
