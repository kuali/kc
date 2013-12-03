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
package org.kuali.kra.proposaldevelopment.rules;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_ANCHOR;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class ProposalDevelopmentSubmitToSponsorRule extends ResearchDocumentRuleBase implements DocumentAuditRule {

    //TODO: Decouple? i.e. KeyPersonnelSubmitToSponsorRule, AbstractsAttachmentsSubmitToSponsorRule
    public static final String ATTACHMENTS_AUDIT_CLUSTER_KEY = "proposalAttachmentsAuditWarnings";

    private static final String AUDIT_PARM = ProposalDevelopmentUtils.AUDIT_INCOMPLETE_PROPOSAL_ATTATCHMENTS_PARM;
    private static final String AUDIT_PARAMETER_VALUE_NO = "N";
    private static final String BEFORE_APPROVE = "BA";

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAttachmentsAuditRule.class);
    

    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocument) document).getDevelopmentProposal();

        valid &= validateIncompleteAttachments(developmentProposal);
        valid &= validateYesNoQuestions(developmentProposal);
        return valid;
    }
    
    //Validate attachments are complete during a submit to sponsor.
    private boolean validateIncompleteAttachments(DevelopmentProposal developmentProposal) {
        boolean valid = true;
        Parameter attachmentAuditParam = getParameterService().getParameter(ProposalDevelopmentDocument.class, 
                AUDIT_PARM);
        if(attachmentAuditParam == null) {
            LOG.warn("System parameter AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS is missing or invalid");
            return validateIncompleteAttachments(developmentProposal);
        }

        String validateIncompleteAttachments = attachmentAuditParam.getValue();
        if(validateIncompleteAttachments.equals(AUDIT_PARAMETER_VALUE_NO)) {
            /* 
             * note when the parameter is set to 'Y', ProposalDevelopmentProposalAttachmentsAuditRule
             * will always return an error for incomplete attachments. However, when set to 'N', this param
             * will cause that audit rule to pass, only warning the user of incomplete attachments.
             */
            List<Narrative> narratives = developmentProposal.getNarratives();
            int narrativeIndex = 0;
            for(Narrative narrative : narratives) {
                if(narrative.getModuleStatusCode().equals("I")) {
                    getAuditErrors(ATTACHMENTS_AUDIT_CLUSTER_KEY,Constants.ABSTRACTS_AND_ATTACHMENTS_PANEL).add(new AuditError("document.developmentProposalList[0].narrative[" + narrativeIndex + "].moduleStatusCode", ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, Constants.ATTACHMENTS_PAGE));
                    valid &= false;
                }
                narrativeIndex++;
            }
        }

        return valid;
    }
    
    private boolean validateYesNoQuestions(DevelopmentProposal developmentProposal) {
        boolean retval = true;
        String questionnaireAuditDeferral = ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.KEY_PERSON_CERTIFICATION_DEFERRAL_PARM);

        if(questionnaireAuditDeferral == null) {
            LOG.warn("System parameter KEY_PERSON_CERTIFICATION_DEFERRAL is missing or invalid");
            throw new RuntimeException("System parameter KEY_PERSON_CERTIFICATION_DEFERRAL is missing or invalid");
        }
        if(questionnaireAuditDeferral.equals(BEFORE_APPROVE)){
    
            int count = 0;
            AuditError error = null;
            final String errorStarter = "proposalPersonQuestionnaireHelpers[";
            final String errorFinish = "].answerHeaders[0].answers[0].answer";
            final String errorLink = KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR;
            for (ProposalPerson person : developmentProposal.getProposalPersons()) {
                if (shouldValidateYesNoQuestions(person) && !validateYesNoQuestions(person)) {
                    String errorKey = errorStarter + count + errorFinish;
    
                    //Displays the error within the audit log.
                    error = new AuditError(errorKey, ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE,
                            errorLink, new String[]{person.getFullName()});
                    getAuditErrors("keyPersonnelAuditErrors",Constants.KEY_PERSONNEL_PANEL_NAME).add(error);
    
                    //Displays the error on the applicable tab.
                    reportError(errorKey, ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE,
                            new String[]{person.getFullName()});
                    retval = false;
                }
                count++;
            }
        }
        return retval;
    }

    //Duplicates code in KeyPersonnelAuditRule
    private boolean shouldValidateYesNoQuestions(ProposalPerson person) {
        boolean retval = false;
        if (person.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE)
                || person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)) {
            retval = true;
        }
        else if (person.getProposalPersonRoleId().equals(KEY_PERSON_ROLE) && isNotBlank(person.getOptInCertificationStatus())
                && person.getOptInCertificationStatus().equals("Y")) {
            retval = true;
        }
        return retval;
    }
    
    
    /**
     * Yes/No questions have to be submitted to Grants.gov on document route. If the submitter has not completed the certifications,
     * errors should be displayed in audit mode.<br/> 
     * <br/>
     * This method differs from <code>{@link #validateYesNoQuestions(ProposalDevelopmentDocument)}</code> that it refers to a specific person.
     * If any one of the Yes/No Questions is not completed, then this check will fail.
     * 
     * 
     * @param investigator Proposal Investigator
     * @return true if the given PI's Yes/No Questions are completed
     */
    //Duplicates code in KeyPersonnelAuditRule
    private boolean validateYesNoQuestions(ProposalPerson investigator) {
        boolean retval = true;
        
        ProposalPersonModuleQuestionnaireBean bean = new ProposalPersonModuleQuestionnaireBean(investigator.getDevelopmentProposal(), investigator);
        List<AnswerHeader> headers = KraServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(bean);
        
        for (AnswerHeader head : headers) {
            retval &= head.getCompleted();
        }
        /*
        for (ProposalPersonYnq question : investigator.getProposalPersonYnqs()) {
           
            retval &= isNotBlank(question.getAnswer());
        }
        */
               
        return retval;
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getAuditErrors(String auditClusterKey, String label) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(auditClusterKey)) {
            KNSGlobalVariables.getAuditErrorMap().put(auditClusterKey, 
                    new AuditCluster(label, auditErrors, Constants.AUDIT_ERRORS));
        } else {
            auditErrors = ((AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(auditClusterKey)).getAuditErrorList();
                }
        
        return auditErrors;
            }

}
