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
package org.kuali.kra.institutionalproposal.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class InstitutionalProposalRuleImpl extends ResearchDocumentRuleBase implements InstitutionalProposalRule {
    BusinessObjectService boService;

    /**
     * @see org.kuali.kra.institutionalproposal.rules.InstitutionalProposalRule#processInstitutionalProposalRules(org.kuali.kra.institutionalproposal.rules.InstitutionalProposalRuleEvent)
     */
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
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);       
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
