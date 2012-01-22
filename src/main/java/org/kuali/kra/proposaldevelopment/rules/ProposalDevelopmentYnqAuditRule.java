/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class ProposalDevelopmentYnqAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
   
    
    private static final String PROPOSAL_QUESTIONS_KEY="document.developmentProposalList[0].proposalYnq[%d].%s";
    private static final String PROPOSAL_QUESTIONS_PANEL_KEY="ynqAuditErrors%s";
    
    /**
     * 
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
    
        boolean valid = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        
        for (int j = 0; j < proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs().size(); j++) {
            ProposalYnq proposalYnq = proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs().get(j);
            String groupName = proposalYnq.getYnq().getGroupName();
            String ynqAnswer = proposalYnq.getAnswer();
            HashMap<String,Integer> questionSerials = ProposalDevelopmentDocumentRule.getQuestionSerialNumberBasedOnGroup( proposalDevelopmentDocument );
            /* look for answers - required for routing */
            if (StringUtils.isBlank(proposalYnq.getAnswer())) {
                valid = false;
                getProposalYnqAuditErrorsByGroup(groupName).add(
                        new AuditError(String.format(PROPOSAL_QUESTIONS_KEY, j, "answer"), KeyConstants.ERROR_REQUIRED_ANSWER,
                                Constants.QUESTIONS_PAGE + "." + groupName, new String[] { questionSerials.get( proposalYnq.getQuestionId() ).toString(),groupName }));
            }
            /* look for date requried */
            String dateRequiredFor = proposalYnq.getYnq().getDateRequiredFor();
            if (dateRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && dateRequiredFor.contains(ynqAnswer) && proposalYnq.getReviewDate() == null) {
                    valid = false;
                    getProposalYnqAuditErrorsByGroup(groupName).add(
                            new AuditError(String.format(PROPOSAL_QUESTIONS_KEY, j, "reviewDate"),
                                KeyConstants.ERROR_REQUIRED_FOR_REVIEW_DATE,  Constants.QUESTIONS_PAGE + "." + groupName,
                                new String[] { questionSerials.get( proposalYnq.getQuestionId() ).toString(),groupName }));
                }
            }

            /* look for explanation requried */
            String explanationRequiredFor = proposalYnq.getYnq().getExplanationRequiredFor();
            if (explanationRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && explanationRequiredFor.contains(ynqAnswer)
                        && StringUtils.isBlank(proposalYnq.getExplanation())) {
                    valid = false;
                    getProposalYnqAuditErrorsByGroup(groupName).add(
                            new AuditError(String.format(PROPOSAL_QUESTIONS_KEY, j, "explanation"),
                                KeyConstants.ERROR_REQUIRED_FOR_EXPLANATION,  Constants.QUESTIONS_PAGE + "." + groupName,
                                new String[] { questionSerials.get( proposalYnq.getQuestionId() ).toString(),groupName }));
                }
            }
        }

        return valid;
    }
    
   
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getProposalYnqAuditErrorsByGroup(String groupName) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        String key = String.format( PROPOSAL_QUESTIONS_PANEL_KEY, groupName );
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(key)) {
           KNSGlobalVariables.getAuditErrorMap().put(key, new AuditCluster(groupName, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
        }
        
        return auditErrors;
    }

    
    
}
