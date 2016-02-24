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
package org.kuali.kra.award.commitments;

import org.apache.commons.lang3.StringUtils;
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

public class AwardCostShareAuditRule implements DocumentAuditRule {
    private static final String AUDIT_CLUSTER = "costShareAuditErrors";
    
    private List<AuditError> auditErrors;
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean retval = true;
        AwardDocument awardDocument = (AwardDocument)document;
        Award award = awardDocument.getAward();
        retval &= validateCostShareDoesNotViolateUniqueConstraint(award.getAwardCostShares());
        if (!retval) {
            reportAndCreateAuditCluster();            
        }
        return retval;
    }
    
    /**
     * This method tests that the Cost Shares do not violate unique constraint on Database table.
     * @param awardCostShareRuleEvent
     * @param awardCostShare
     * @return
     */
    public boolean validateCostShareDoesNotViolateUniqueConstraint (List<AwardCostShare> awardCostShares) {
        boolean valid = true;
        for (AwardCostShare costShare1 : awardCostShares) {
            for (AwardCostShare costShare2 : awardCostShares) {
                if (costShare1 == costShare2) {
                    continue;
                } else if (StringUtils.equals(costShare1.getProjectPeriod(), costShare2.getProjectPeriod()) &&
                        costShare1.getCostShareTypeCode().equals(costShare2.getCostShareTypeCode()) &&
                            StringUtils.equalsIgnoreCase(costShare1.getSource(), costShare2.getSource()) &&
                                StringUtils.equalsIgnoreCase(costShare1.getDestination(), costShare2.getDestination())) {
                    valid = false;
                    addAuditError(new AuditError("document.awardList[0].awardCostShares["+awardCostShares.indexOf(costShare1)+"].fiscalYear", 
                            KeyConstants.ERROR_DUPLICATE_ENTRY,
                            Constants.MAPPING_AWARD_COMMITMENTS_PAGE+"."+Constants.COST_SHARE_PANEL_ANCHOR));
                }
            }
        }
        return valid;
    }

    private void addAuditError(AuditError auditError) {
        if(auditErrors == null) {
            auditErrors = new ArrayList<AuditError>();            
        }
        auditErrors.add(auditError);
    }


    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(AUDIT_CLUSTER, new AuditCluster(Constants.COST_SHARE_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
}
