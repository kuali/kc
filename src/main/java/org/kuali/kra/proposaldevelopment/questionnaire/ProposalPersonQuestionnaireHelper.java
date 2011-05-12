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
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

/**
 * This class...
 */
public class ProposalPersonQuestionnaireHelper extends QuestionnaireHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5090730280279711495L;
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonQuestionnaireHelper.class);
    
    private ProposalPerson proposalPerson;
    
    private ProposalDevelopmentForm proposalDevelopmentForm;

    /**
     * Constructs a ProposalPersonQuestionnaireHelper.java.
     * @param form
     */
    public ProposalPersonQuestionnaireHelper(ProposalDevelopmentForm form, ProposalPerson proposalPerson) {
        this.proposalDevelopmentForm = form;
        this.setProposalPerson(proposalPerson);
        this.populateAnswers();
    }    
    
    
    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }



    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }



    /**
     * 
     * @see org.kuali.kra.questionnaire.QuestionnaireHelperBase#getModuleQnBean()
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

    @Override
    public String getModuleCode() {
        return CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
    }
    
    /**
     * 
     * This method returns the ProposalDevelopmentDocument if it is valid, otherwise throws an illegal argument exception
     * @return
     */
    protected ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
        if (document == null || document.getDevelopmentProposal() == null) {
            throw new IllegalArgumentException("invalid (null) ProposalDevelopmentDocument in ProposalDevelopmentForm");
        }
        return document;
    }
    
    /**
     * Gets the proposalDevelopmentForm attribute. 
     * @return Returns the proposalDevelopmentForm.
     */
    public ProposalDevelopmentForm getProposalDevelopmentForm() {
        return proposalDevelopmentForm;
    }

    /**
     * Sets the proposalDevelopmentForm attribute value.
     * @param proposalDevelopmentForm The proposalDevelopmentForm to set.
     */
    public void setProposalDevelopmentForm(ProposalDevelopmentForm proposalDevelopmentForm) {
        this.proposalDevelopmentForm = proposalDevelopmentForm;
    }
}