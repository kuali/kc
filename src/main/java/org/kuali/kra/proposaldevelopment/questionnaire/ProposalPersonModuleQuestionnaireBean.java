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
package org.kuali.kra.proposaldevelopment.questionnaire;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

/**
 * 
 * This class...
 */
public class ProposalPersonModuleQuestionnaireBean extends ModuleQuestionnaireBean {
    
    /**
     * 
     * Constructs a ProposalPersonModuleQuestionnaireBean.java.
     * @param developmentProposal
     * @param person
     */
    public ProposalPersonModuleQuestionnaireBean(DevelopmentProposal developmentProposal, ProposalPerson person) {
        super(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE, person.getUniqueId(), 
                CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION, "0", 
                developmentProposal.getProposalDocument().getDocumentHeader().getWorkflowDocument().isApproved());      
    }
}