/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.service.impl;

import java.util.List;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.service.S2SProposalValidatorService;
import org.kuali.kra.s2s.validator.S2SErrorMessages;

/**
 * 
 * This class is meant to validate whether a given proposal is complete and ready for submission to Grants.Gov. It applies different
 * rules on the Proposal and returns the result of the rule application. IN case of rule failure, it fetches appropriate message and
 * posts it back to user.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class S2SProposalValidatorServiceImpl implements S2SProposalValidatorService {
    private static final String OPPORTUNITY_ID_REQUIRED = "proposalvalidation.opportunityId_required";
    private static final String CFDA_NUMBER_REQUIRED = "proposalvalidation.cfdaNumber_required";

    /**
     *  This method receives a ProposalDevelopmentDocument and applies all Proposal validation rules on it, and returns back the
     * validation result. It also populates all the reasons for validation failure of rules in the List that is also passed as
     * argument
     * 
     * @param pdDoc ProposalDevelopmentDocument
     * @param errors List of validation error messages.
     * @return boolean true if validation succeeds false otherwise.
     * @see org.kuali.kra.s2s.service.S2SProposalValidatorService#validate(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List)
     */
    public boolean validate(ProposalDevelopmentDocument pdDoc, List<String> errors) {
        boolean validationStatus = true;
        pdDoc.getS2sOpportunity().refreshNonUpdateableReferences();
        if (pdDoc.getS2sOpportunity().getOpportunityId() == null) {
            errors.add(S2SErrorMessages.getProperty(OPPORTUNITY_ID_REQUIRED, OPPORTUNITY_ID_REQUIRED + " Is not found"));
            validationStatus = false;
        }
        if (pdDoc.getCfdaNumber() == null) {            
            errors.add(S2SErrorMessages.getProperty(CFDA_NUMBER_REQUIRED, CFDA_NUMBER_REQUIRED + " Is not found"));
            validationStatus = false;
        }
        // Add more validation rules here if and when required

        return validationStatus;
    }
}
