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

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentPersonQuestionnaireService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

/**
 * This class...
 */
public class ProposalPersonQuestionnaireHelper extends ProposalDevelopmentQuestionnaireHelper {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5090730280279711495L;
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonQuestionnaireHelper.class);
    
    private ProposalPerson proposalPerson;

    /**
     * Constructs a ProposalPersonQuestionnaireHelper.java.
     * @param form
     */
    public ProposalPersonQuestionnaireHelper(ProposalDevelopmentForm form, ProposalPerson proposalPerson) {
        super(form);
        setProposalPerson(proposalPerson);
        this.populateAnswers();
    }
    
    /**
     * 
     * @see org.kuali.kra.questionnaire.QuestionnaireHelperBase#populateAnswers()
     */
    @Override
    public void populateAnswers() {
        setAnswerHeaders(getProposalDevelopmentPersonQuestionnaireService().getAnswerHeaders(getProposalPerson()));
        resetHeaderLabels();
    }
    
    private ProposalDevelopmentPersonQuestionnaireService getProposalDevelopmentPersonQuestionnaireService() {
        return KraServiceLocator.getService(ProposalDevelopmentPersonQuestionnaireService.class);
    }
    
    
    
    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }



    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }



    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.questionnaire.ProposalDevelopmentQuestionnaireHelper#getModuleQnBean()
     */
    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        ProposalDevelopmentDocument propDevDoc = getProposalDevelopmentDocument(); 
        ProposalPersonModuleQuestionnaireBean moduleQuestionnaireBean = 
            new ProposalPersonModuleQuestionnaireBean(propDevDoc.getDevelopmentProposal(), getProposalPerson());
        return moduleQuestionnaireBean;
    }
    
    /**
     * 
     * This method is to set up things for questionnaire page to be displayed.
     */
    public void prepareView() {
        initializePermissions(this.getProposalDevelopmentDocument());
    }

    /*
     * authorization check.
     */
    private void initializePermissions(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        ProposalTask task = new ProposalTask(TaskName.CERTIFY, proposalDevelopmentDocument);
        setAnswerQuestionnaire(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
    }

}
