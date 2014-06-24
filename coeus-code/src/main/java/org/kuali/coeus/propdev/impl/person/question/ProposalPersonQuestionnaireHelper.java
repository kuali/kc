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
package org.kuali.coeus.propdev.impl.person.question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.ObjectUtils;


public class ProposalPersonQuestionnaireHelper extends QuestionnaireHelperBase {


    private static final long serialVersionUID = -5090730280279711495L;
    
    private static final Log LOG = LogFactory.getLog(ProposalPersonQuestionnaireHelper.class);

    private ProposalPerson proposalPerson;
    
    private QuestionnaireService questionnaireService;
    
    private ParameterService parameterService;
    
    private boolean canAnswerAfterRouting = false;
    
    /**
     * Constructs a ProposalPersonQuestionnaireHelper.java.
     * @param form
     */
    public ProposalPersonQuestionnaireHelper(ProposalPerson proposalPerson) {
        this.setProposalPerson(proposalPerson);
    }
    
    
    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }



    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        //ProposalDevelopmentDocument propDevDoc = getProposalDevelopmentDocument(); 
        ProposalPersonModuleQuestionnaireBean moduleQuestionnaireBean =
            new ProposalPersonModuleQuestionnaireBean(getProposalPerson().getDevelopmentProposal(), getProposalPerson());
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
        boolean canCertify = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        setAnswerQuestionnaire(canCertify);

        String keyPersonCertDeferral = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, "KEY_PERSON_CERTIFICATION_DEFERRAL");

        if(keyPersonCertDeferral.equals("BS") || ObjectUtils.isNull(getProposalDevelopmentDocument())) {
            setCanAnswerAfterRouting(false);
        } else if(keyPersonCertDeferral.equals("BA")) {
            
            boolean isEnroute = getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument().isEnroute();
            
            if(!isEnroute && !canCertify) {
                setCanAnswerAfterRouting(false);
            } else {
                //questionnaires should continue to be answerable only to the following approvers.
                PropAwardPersonRole personRole = proposalPerson.getRole();
                if (personRole.getRoleCode().equals(Constants.CO_INVESTIGATOR_ROLE)
                        || personRole.getRoleCode().equals(Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                        || personRole.getRoleCode().equals(Constants.KEY_PERSON_ROLE)) {
                    if(proposalPerson.getPerson().getPersonId().equals(getUserIdentifier())
                            || canCertify) {
                        setCanAnswerAfterRouting(true);
                    }
                }
            }
        } else {
            //KEY_PERSON_CERTIFICATION_DEFERRAL is set to an improper value.
            LOG.warn("System Parameter 'KEY_PERSON_CERTIFICATION_DEFERRAL' is not properly set. Must be one of 'BA' or 'BS'");
            setCanAnswerAfterRouting(false);
        }
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
        ProposalDevelopmentDocument document = getProposalPerson().getDevelopmentProposal().getProposalDocument();
        if (document == null || document.getDevelopmentProposal() == null) {
            throw new IllegalArgumentException("invalid (null) ProposalDevelopmentDocument in ProposalDevelopmentForm");
        }
        if (!document.getDocumentHeader().hasWorkflowDocument()) {
            try {
                document = (ProposalDevelopmentDocument) KcServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(document.getDocumentNumber());
            }
            catch (WorkflowException e) {
            }         
        }
        return document;
    }
    
    public String getNamespaceCd() {
        return Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
    }
    
    @Override
    public void populateAnswers() {
        super.populateAnswers();
        if (getAnswerHeaders().size() > 1) {
            //if we have more than 1 questionnaire make sure the first one is the one with the current usage.
            AnswerHeader mostCurrentHeader = getAnswerHeaders().get(0);
            for (AnswerHeader header : getAnswerHeaders()) {
                //should only be 1 header that is the current questionnaire and has the usage for certification
                if (getQuestionnaireService().isCurrentQuestionnaire(header.getQuestionnaire()) &&
                        header.getQuestionnaire().hasUsageFor(getModuleCode(), CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION)) {
                    mostCurrentHeader = header;
                }
            }
            getAnswerHeaders().remove(mostCurrentHeader);
            getAnswerHeaders().add(0, mostCurrentHeader);
        }
    }


    protected QuestionnaireService getQuestionnaireService() {
        if (questionnaireService == null) {
            questionnaireService = KcServiceLocator.getService(QuestionnaireService.class);
        }
        return questionnaireService;
    }


    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }
    
    protected ParameterService getParameterService() {
        if(parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public boolean getCanAnswerAfterRouting() {
        return canAnswerAfterRouting;
    }


    public void setCanAnswerAfterRouting(boolean canAnswerAfterRouting) {
        this.canAnswerAfterRouting = canAnswerAfterRouting;
    }
}