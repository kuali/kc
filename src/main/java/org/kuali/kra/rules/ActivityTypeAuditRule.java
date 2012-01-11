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
package org.kuali.kra.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class to check whether activity type has been changed for PD or Award, and budget is not sync'ed.  
 */
public class ActivityTypeAuditRule  implements DocumentAuditRule{

    private static final Log LOG = LogFactory.getLog(ActivityTypeAuditRule.class);

    
    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        BudgetDocument budgetDocument = (BudgetDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (isActivityTypeChanged(budgetDocument)) {
            if (budgetDocument.getParentDocument() instanceof AwardDocument) {
                auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY.replace("developmentProposalList", "awardList"),
                    KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED, Constants.MAPPING_AWARD_HOME_PAGE + "." + "detailsDate"));
            } else {
                auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY, KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED,
                    Constants.PROPOSAL_PAGE + "." + Constants.REQUIRED_FIELDS_PANEL_ANCHOR));
            }
            GlobalVariables.getAuditErrorMap().put("activityTypeAuditWarnings",
                    new AuditCluster("Activity type changed", auditErrors, Constants.AUDIT_WARNINGS));
            valid = false;
        }
        return valid;
    }

    private boolean isActivityTypeChanged(BudgetDocument budgetDocument) {
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
        boolean syncRate = false;
        Collection<BudgetRate> allBudgetRates = KraServiceLocator.getService(BudgetService.class).getSavedBudgetRates(budgetDocument.getBudget());
        syncRate = getBudgetService().checkActivityTypeChange(allBudgetRates,
                                    parentDocument.getBudgetParent().getActivityTypeCode());
//        
//        
//        if (parentDocument instanceof AwardDocument) {
//            syncRate = getBudgetService().checkActivityTypeChange(allBudgetRates, 
//                    ((AwardDocument) parentDocument).getBudgetParent().getActivityTypeCode());
//        } else {
//            syncRate = getBudgetService().checkActivityTypeChange(allBudgetRates, 
//                    ((ProposalDevelopmentDocument) parentDocument).getDevelopmentProposal().getActivityTypeCode());            
//        }
        return syncRate;
    }

    private BudgetService getBudgetService() {
        return KraServiceLocator.getService(BudgetService.class);
    }


}
