/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks the budget limits on the Budgets tab to make sure they are valid.
 */
public class AwardBudgetLimitsAuditRule implements DocumentAuditRule {

    protected String AWARD_BUDGET_LIMITS_AUDIT_ERRORS = "awardBudgetLimitAuditErrors";
    
    @Override
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
            GlobalVariables.getAuditErrorMap().put(AWARD_BUDGET_LIMITS_AUDIT_ERRORS, new AuditCluster("Budget Limits",
                                                                                          auditErrors, Constants.AUDIT_WARNINGS));
        }
    }
}
