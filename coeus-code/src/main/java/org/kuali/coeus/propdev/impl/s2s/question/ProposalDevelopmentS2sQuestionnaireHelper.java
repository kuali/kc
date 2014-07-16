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
package org.kuali.coeus.propdev.impl.s2s.question;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

import java.util.List;

public class ProposalDevelopmentS2sQuestionnaireHelper extends ProposalDevelopmentQuestionnaireHelper {

    private static final long serialVersionUID = 8595107639632039291L;
    private transient ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService;
    
    
    public ProposalDevelopmentS2sQuestionnaireHelper(ProposalDevelopmentDocumentForm form) {
        super(form);
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() { 
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentS2sModuleQuestionnaireBean(getDocument().getDevelopmentProposal());
        return moduleQuestionnaireBean;
    }
  
    @SuppressWarnings("unchecked")
    
    /**
     * 
     * This method get/setup questionnaire answers when 'questionnaire' page is clicked.
     */
    public void populateAnswers() {
        List<AnswerHeader> headers = getProposalDevelopmentS2sQuestionnaireService().getProposalS2sAnswerHeaders(getDocument().getDevelopmentProposal());
        setAnswerHeaders(headers);
        resetHeaderLabels();
    }
    
  

    /**
     * Gets the proposalDevelopmentS2sQuestionnaireService attribute. 
     * @return Returns the proposalDevelopmentS2sQuestionnaireService.
     */
    public ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        if (proposalDevelopmentS2sQuestionnaireService == null) {
            this.proposalDevelopmentS2sQuestionnaireService = KcServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
        }
        return proposalDevelopmentS2sQuestionnaireService;
    }


}
