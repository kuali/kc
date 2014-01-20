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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_ANCHOR;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.rules.rule.event.ApproveDocumentEvent;
import org.kuali.rice.krad.util.GlobalVariables;

public class KeyPersonnelCertificationRule extends ResearchDocumentRuleBase implements DocumentAuditRule {

    public static final String KEY_PERSONNEL_AUDIT_CLUSTER_KEY = "keyPersonnelAuditErrors";

    private static final String BEFORE_APPROVE = "BA";
    private static final String BEFORE_SUBMIT = "BS";
    
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAttachmentsAuditRule.class);
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        if(getKeyPersonCertDeferralParm().equals(BEFORE_SUBMIT)) {
            valid &= this.validateAllCertificationsComplete((ProposalDevelopmentDocument) document);
        } else if(getKeyPersonCertDeferralParm().equals(BEFORE_APPROVE)) {
            //valid &= this.validateSpecificKeyPersonCertification((ProposalDevelopmentDocument) document);
        } else {
            LOG.warn("System parameter 'KEY_PERSON_CERTIFICATION_DEFERRAL' should be one of 'BA' or 'BS'.");
            return false;
        }
        
        return valid;
    }

    @Override
    public boolean processRouteDocument(Document document) {
        boolean isValid = true;

        if(getKeyPersonCertDeferralParm().equals(BEFORE_SUBMIT)) {
            //validation based on existence of ANY incomplete questionnaires.
            isValid &= this.validateAllCertificationsComplete((ProposalDevelopmentDocument) document);
        }
        
        return isValid;
    }

    @Override
    public boolean processApproveDocument(ApproveDocumentEvent approveEvent) {
        boolean isValid = true;

        if(getKeyPersonCertDeferralParm().equals(BEFORE_APPROVE)) {
            //validation based on session user existing as key person and possibly aggregator.
            isValid &= this.validateKeyPersonCertification((ProposalDevelopmentDocument) approveEvent.getDocument(),
                    GlobalVariables.getUserSession().getPerson());
        }
        
        return isValid;
    }    
    
    private boolean validateAllCertificationsComplete(ProposalDevelopmentDocument document) {
        boolean retval = true;
    
        int count = 0;

        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            if (hasCertification(person) && !validKeyPersonCertification(person)) {
                generateAuditError(count,person.getFullName());
                retval = false;
            }
            count++;
        }
        
        return retval;
    }
    
    /*
     * validates specifically the key person certification, if any, of the proposal person matching the
     * person in the user session
     */
    private boolean validateKeyPersonCertification(ProposalDevelopmentDocument document, Person user) {
        boolean retval = true;
    
        int count = 0;
        
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            if(StringUtils.equals(user.getPrincipalId(),person.getPersonId())
                    && hasCertification(person) && !validKeyPersonCertification(person)) {
                generateAuditError(count,person.getFullName());
                return false;
            }
            count++;
        }
        
        return retval;
    }
    
    private boolean validKeyPersonCertification(ProposalPerson person) {
        return validateYesNoQuestions(person);
    }

    private boolean hasCertification(ProposalPerson person) {
        
        //questionnaires should continue to be answerable only to the following approvers,
        //possibly as well as other roles. i.e. Aggregator.
        ProposalPersonRole personRole = person.getRole();
        if (personRole.getRoleCode().equals(Constants.CO_INVESTIGATOR_ROLE)
                || personRole.getRoleCode().equals(Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || (personRole.getRoleCode().equals(Constants.KEY_PERSON_ROLE) && StringUtils.isNotBlank(person.getOptInCertificationStatus())
                        && person.getOptInCertificationStatus().equals("Y"))) {
                return true;
        }
        
        return false;
    }
    
    /**
     * Yes/No questions have to be submitted to Grants.gov on document route. If the submitter has not completed the certifications,
     * errors should be displayed in audit mode.<br/> 
     * <br/>
     * This method differs from <code>{@link #validateKeyPersonCertification(ProposalDevelopmentDocument)}</code> that it refers to a specific person.
     * If any one of the Yes/No Questions is not completed, then this check will fail.
     * 
     * 
     * @param investigator Proposal Investigator
     * @return true if the given PI's Yes/No Questions are completed
     */
    private boolean validateYesNoQuestions(ProposalPerson investigator) {
        boolean retval = true;
        
        ProposalPersonModuleQuestionnaireBean bean = new ProposalPersonModuleQuestionnaireBean(investigator.getDevelopmentProposal(), investigator);
        List<AnswerHeader> headers = KraServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(bean);
        
        for (AnswerHeader head : headers) {
            retval &= head.getCompleted();
        }
               
        return retval;
    }
    
    private void generateAuditError(int count, String personFullName) {
        final String errorStarter = "proposalPersonQuestionnaireHelpers[";
        final String errorFinish = "].answerHeaders[0].answers[0].answer";
        final String errorLink = KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR;
        
        String errorKey = errorStarter + count + errorFinish;

        //Displays the error within the audit log.
        AuditError error = new AuditError(errorKey, ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE,
                errorLink, new String[]{personFullName});
        getAuditErrors().add(error);
        //Displays the error on the applicable tab.
        reportError(errorKey, ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE,
                new String[]{personFullName});

    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(KEY_PERSONNEL_AUDIT_CLUSTER_KEY)) {
            KNSGlobalVariables.getAuditErrorMap().put(KEY_PERSONNEL_AUDIT_CLUSTER_KEY, 
                    new AuditCluster(Constants.KEY_PERSONNEL_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        } else {
            auditErrors = ((AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(KEY_PERSONNEL_AUDIT_CLUSTER_KEY)).getAuditErrorList();
                }
        
        return auditErrors;
            }


    private String getKeyPersonCertDeferralParm() {
        return ProposalDevelopmentUtils
                .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.KEY_PERSON_CERTIFICATION_DEFERRAL_PARM);
    }
}
