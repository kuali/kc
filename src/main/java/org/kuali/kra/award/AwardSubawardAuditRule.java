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
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class...
 */
public class AwardSubawardAuditRule implements DocumentAuditRule {
    
    private static final String SUBAWARD_AUDIT_ERRORS = "subawardAuditErrors";
    private static final String SUBAWARD_AUDIT_WARNINGS = "subawardAuditWarnings";

    List<AwardApprovedSubaward> awardApprovedSubawards;
    private List<AuditError> auditErrors;
    private List<AuditError> auditWarnings;
    
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument) document;
        auditErrors = new ArrayList<AuditError>();
        auditWarnings = new ArrayList<AuditError>();
        Award award = awardDocument.getAward();
        this.awardApprovedSubawards = award.getAwardApprovedSubawards();
        if(!validateApprovedSubawardDuplicateOrganization(awardApprovedSubawards)){
            valid = false;
            auditErrors.add(new AuditError(Constants.SUBAWARD_AUDIT_RULES_ERROR_KEY, 
                    KeyConstants.ERROR_DUPLICATE_ORGANIZATION_NAME, 
                    Constants.MAPPING_AWARD_HOME_PAGE + "." + Constants.SUBAWARD_PANEL_ANCHOR,
                    new String[]{"Organization"}));
        }
        for (int i = 0; i < awardApprovedSubawards.size(); i++) {
            AwardApprovedSubaward subAward = awardApprovedSubawards.get(i);
            KualiDecimal amount = subAward.getAmount();
            if (amount == null) {
                valid = false;  // a "required field" error is already reported by the framework, so don't call reportError
            } else if(!amount.isGreaterThan(new KualiDecimal(0.00))) {
                valid = false;
                auditWarnings.add(new AuditError("document.awardList[0].awardApprovedSubawards[" + i + "].amount",
                        KeyConstants.ERROR_AMOUNT_IS_ZERO,
                        Constants.MAPPING_AWARD_HOME_PAGE + "." + Constants.SUBAWARD_PANEL_ANCHOR,
                        null));
            }
        }
        reportAndCreateAuditCluster();
        return valid;
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(SUBAWARD_AUDIT_ERRORS, new AuditCluster(Constants.SUBAWARD_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        } 
        if (auditWarnings.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(SUBAWARD_AUDIT_WARNINGS, new AuditCluster(Constants.SUBAWARD_PANEL_NAME,
                    auditWarnings, Constants.AUDIT_WARNINGS));            
        }
    }
    
    /**
    *
    * Test Approved Subawards for duplicate organizations
    * @return Boolean
    */
    protected boolean validateApprovedSubawardDuplicateOrganization(List<AwardApprovedSubaward> awardApprovedSubawards){
            boolean valid = true;
            int index = 0;
        test:
            for (AwardApprovedSubaward loopAwardApprovedSubaward : awardApprovedSubawards) {
                int innerIndex = 0;
                for(AwardApprovedSubaward testAwardApprovedSubaward : awardApprovedSubawards) {
                    if (innerIndex != index) {
                        if(testAwardApprovedSubaward.getOrganizationName().equals(loopAwardApprovedSubaward.getOrganizationName())){
                            valid = false;
                            break test;
                        }
                        innerIndex++;
                    }
                }
                index++;
            }
            return valid;
    }
    
}
