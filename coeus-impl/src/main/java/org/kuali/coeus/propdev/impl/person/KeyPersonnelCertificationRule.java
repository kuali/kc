/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentProposalAttachmentsAuditRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.rules.rule.event.ApproveDocumentEvent;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE;

public class KeyPersonnelCertificationRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    public static final String KEY_PERSONNEL_AUDIT_CLUSTER_KEY = "keyPersonnelAuditErrors";

    private static final String BEFORE_APPROVE = "BA";
    private static final String BEFORE_SUBMIT = "BS";
    
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAttachmentsAuditRule.class);
    private QuestionnaireAnswerService questionnaireAnswerService;
    protected QuestionnaireAnswerService getQuestionnaireAnswerService(){
        if (questionnaireAnswerService == null)
            questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        return questionnaireAnswerService;
    }
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        if(getKeyPersonCertDeferralParm().equals(BEFORE_SUBMIT)) {
            valid &= this.validateAllCertificationsComplete((ProposalDevelopmentDocument) document);
        } else if(getKeyPersonCertDeferralParm().equals(BEFORE_APPROVE)) {
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
        PropAwardPersonRole personRole = person.getRole();
        if (personRole.getRoleCode().equals(Constants.CO_INVESTIGATOR_ROLE)
                || personRole.getRoleCode().equals(Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || (personRole.getRoleCode().equals(Constants.KEY_PERSON_ROLE) && person.getOptInCertificationStatus())) {
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
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(bean);
        
        for (AnswerHeader head : headers) {
            retval &= head.isCompleted();
        }
               
        return retval;
    }
    
    private void generateAuditError(int count, String personFullName) {
        final String errorStarter = "document.developmentProposal.proposalPersons[";
        final String errorFinish = "].questionnaireHelper.answerHeaders[0].questions";
        
        String errorKey = errorStarter + count + errorFinish;

        //Displays the error within the audit log.
        AuditError error = new AuditError(errorKey, ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE,
                ProposalDevelopmentDataValidationConstants.PERSONNEL_PAGE_ID, new String[]{personFullName});
        getAuditErrors().add(error);

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
        
        if (!GlobalVariables.getAuditErrorMap().containsKey(KEY_PERSONNEL_AUDIT_CLUSTER_KEY)) {
            GlobalVariables.getAuditErrorMap().put(KEY_PERSONNEL_AUDIT_CLUSTER_KEY, 
                    new AuditCluster(Constants.KEY_PERSONNEL_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        } else {
            auditErrors = ((AuditCluster) GlobalVariables.getAuditErrorMap().get(KEY_PERSONNEL_AUDIT_CLUSTER_KEY)).getAuditErrorList();
                }
        
        return auditErrors;
            }


    private String getKeyPersonCertDeferralParm() {
        return ProposalDevelopmentUtils
                .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.KEY_PERSON_CERTIFICATION_DEFERRAL_PARM);
    }
}
