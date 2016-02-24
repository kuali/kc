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
package org.kuali.kra.institutionalproposal.rules;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalRuleImpl extends KcTransactionalDocumentRuleBase implements InstitutionalProposalRule {

    @Override
    public boolean processInstitutionalProposalRules(InstitutionalProposalRuleEvent institutionalProposalRuleEvent) {
        InstitutionalProposal proposal = institutionalProposalRuleEvent.getInstitutionalProposalForValidation();
        boolean valid = validateCurrentAwardNumberExists(proposal.getCurrentAwardNumber());
        
        // null is ok for rolodex ID
        if (proposal.getRolodexId() != null) {
            valid &= validateRolodexIdExists(proposal.getRolodexId());
        }
        return valid;
    }
    
    @SuppressWarnings("unchecked")
    private boolean validateCurrentAwardNumberExists(String currentAwardNumber) {
        boolean valid = true;
        if(!(currentAwardNumber == null)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("awardNumber", currentAwardNumber);
            BusinessObjectService businessObjectService =  KcServiceLocator.getService(BusinessObjectService.class);
            List<Award> sponsors = (List<Award>)businessObjectService.findMatching(Award.class, fieldValues);
            if(sponsors.size() == 0) {
                this.reportError("document.institutionalProposalList[0].currentAwardNumber", KeyConstants.ERROR_INVALID_AWARD_ID);
                valid = false;
            }
        }
       return valid;
        
    }

    private boolean validateRolodexIdExists(int rolodexId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("rolodexId", rolodexId);
        int count = getBusinessObjectService().countMatching(Rolodex.class, primaryKey);
        
        boolean valid = true;
        if (count <= 0) {
            valid = false;
            this.reportError("document.institutionalProposal.rolodexId", KeyConstants.ERROR_INVALID_ROLODEX_ID);
        }
        return valid;
    }
}
