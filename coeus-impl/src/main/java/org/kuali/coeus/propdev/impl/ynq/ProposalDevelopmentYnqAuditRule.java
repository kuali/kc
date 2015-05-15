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
package org.kuali.coeus.propdev.impl.ynq;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;

public class ProposalDevelopmentYnqAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {
   
    
    private static final String PROPOSAL_QUESTIONS_KEY="document.developmentProposalList[0].proposalYnq[%d].%s";
    private static final String PROPOSAL_QUESTIONS_PANEL_KEY="ynqAuditErrors%s";
    
    @Override
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
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getProposalYnqAuditErrorsByGroup(String groupName) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        String key = String.format( PROPOSAL_QUESTIONS_PANEL_KEY, groupName );
        
        if (!GlobalVariables.getAuditErrorMap().containsKey(key)) {
           GlobalVariables.getAuditErrorMap().put(key, new AuditCluster(groupName, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
        }
        
        return auditErrors;
    }

    
    
}
