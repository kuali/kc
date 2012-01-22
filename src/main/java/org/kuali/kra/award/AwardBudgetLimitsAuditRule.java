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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * Checks the budget limits on the Budgets tab to make sure they are valid.
 */
public class AwardBudgetLimitsAuditRule implements DocumentAuditRule {

    protected String AWARD_BUDGET_LIMITS_AUDIT_ERRORS = "awardBudgetLimitAuditErrors";
    
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean result = true;
        Award award = ((AwardDocument) document).getAward();
        List<AuditError> auditWarnings = new ArrayList<AuditError>(); 
        if (award.getTotalCostBudgetLimit() != null) {
            if (!award.getTotalCostBudgetLimit().equals(award.getObligatedDistributableTotal())) {
                result = false;
                auditWarnings.add(new AuditError("document.award.totalCostBudgetLimit.limit",
                        KeyConstants.WARNING_AWARD_BUDGET_COSTLIMIT_NOTEQUAL_OBLIGATED,
                        Constants.MAPPING_AWARD_BUDGET_VERSIONS_PAGE + "." + "BudgetLimits",
                        new String[]{award.getAwardNumber()}));                
            }
        }
        reportAndCreateAuditCluster(auditWarnings);
        return result;
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster( List<AuditError> auditErrors ) {
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(AWARD_BUDGET_LIMITS_AUDIT_ERRORS, new AuditCluster("Budget Limits",
                                                                                          auditErrors, Constants.AUDIT_WARNINGS));
        }
    }
}
