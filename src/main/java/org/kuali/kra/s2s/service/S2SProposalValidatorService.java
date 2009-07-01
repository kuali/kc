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
package org.kuali.kra.s2s.service;

import java.util.List;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * 
 * This interface defines the service which is used to validate a proposal.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SProposalValidatorService {

    /**
     * 
     * This method receives a {@link ProposalDevelopmentDocument} and applies all Proposal validation rules on it, and returns back
     * the validation result. It also populates all the reasons for validation failure of rules in the List that is also passed as
     * argument
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument}
     * @param errors {@link List} List of validation error messages.
     * @return boolean true if validation succeeds false otherwise.
     */
    public boolean validate(ProposalDevelopmentDocument pdDoc, List<String> errors);
}
