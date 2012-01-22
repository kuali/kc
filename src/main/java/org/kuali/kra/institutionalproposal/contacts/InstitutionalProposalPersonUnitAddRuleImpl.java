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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.kra.bo.Unit;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class...
 */
public class InstitutionalProposalPersonUnitAddRuleImpl implements InstitutionalProposalPersonUnitAddRule {

    /**
     * @see org.kuali.kra.institutionalProposal.contacts.InstitutionalProposalPersonUnitAddRule
     */
    public boolean processAddInstitutionalProposalPersonUnitBusinessRules(InstitutionalProposalPersonUnitRuleAddEvent event) {
        return checkForDuplicateUnits(event.getProjectPerson(), event.getNewPersonUnit());
    }
        
    boolean checkForDuplicateUnits(InstitutionalProposalPerson projectPerson, InstitutionalProposalPersonUnit newInstitutionalProposalPersonUnit) {
        boolean valid = true;
        for(InstitutionalProposalPersonUnit apu: projectPerson.getUnits()) {
            valid = !apu.getUnit().equals(newInstitutionalProposalPersonUnit.getUnit());
            if(!valid) {
                break;
            }
        }
        
        if(!valid) {
            Unit dupeUnit = newInstitutionalProposalPersonUnit.getUnit();
            GlobalVariables.getMessageMap().putError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, 
                                                        ERROR_PROPOSAL_PROJECT_PERSON_DUPLICATE_UNITS, 
                                                        dupeUnit.getUnitName(), dupeUnit.getUnitNumber(),
                                                        projectPerson.getFullName());
        }
        
        return valid;
    }

}
