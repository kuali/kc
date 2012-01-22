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
package org.kuali.kra.award.commitments;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

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
            KNSGlobalVariables.getAuditErrorMap().put(AUDIT_CLUSTER, new AuditCluster(Constants.COST_SHARE_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
}
