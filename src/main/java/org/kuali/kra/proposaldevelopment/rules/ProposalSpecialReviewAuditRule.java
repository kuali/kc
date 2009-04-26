/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProposalSpecialReviewAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelAuditRule.class);
    
    /**
     * 
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) document;
        boolean retval = true;
        List <ProposalSpecialReview> proposalSpecialReviews = ((ProposalDevelopmentDocument) document).getPropSpecialReviews();
        int i = 0;
        for (ProposalSpecialReview  proposalSpecialReview: proposalSpecialReviews) {
            if (proposalSpecialReview.getExpirationDate() != null && proposalSpecialReview.getExpirationDate().before(new java.sql.Date(new java.util.Date().getTime()))) {
                retval = false;
                getAuditErrors().add(new AuditError("document.propSpecialReview[" + i + "].expirationDate", KeyConstants.ERROR_EXPIRATION_DATE_PAST, Constants.SPECIAL_REVIEW_PAGE + "." + Constants.SPECIAL_REVIEW_PANEL_ANCHOR));
            }
            i++;
        }
                    
        
        return retval;

    }
       
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     *  TODO : should this method move up to parent class
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!GlobalVariables.getAuditErrorMap().containsKey("specialReviewAuditWarnings")) {
           GlobalVariables.getAuditErrorMap().put("specialReviewAuditWarnings", new AuditCluster(Constants.SPECIAL_REVIEW_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get("specialReviewAuditWarnings")).getAuditErrorList();
        }
        
        return auditErrors;
    }

}
