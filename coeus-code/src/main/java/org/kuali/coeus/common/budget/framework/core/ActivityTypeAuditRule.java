/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * This class to check whether activity type has been changed for PD or Award, and budget is not sync'ed.  
 */
public class ActivityTypeAuditRule  implements DocumentAuditRule{

    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        BudgetDocument budgetDocument = (BudgetDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (isActivityTypeChanged(budgetDocument)) {
            if (budgetDocument.getBudget().getBudgetParent().getDocument() instanceof AwardDocument) {
                auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY.replace("developmentProposalList", "awardList"),
                    KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED, Constants.MAPPING_AWARD_HOME_PAGE + "." + "detailsDate"));
            } else {
                auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY, KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED,
                    Constants.PROPOSAL_PAGE + "." + Constants.REQUIRED_FIELDS_PANEL_ANCHOR));
            }
            KNSGlobalVariables.getAuditErrorMap().put("activityTypeAuditWarnings",
                    new AuditCluster("Activity type changed", auditErrors, Constants.AUDIT_WARNINGS));
            valid = false;
        }
        return valid;
    }

    private boolean isActivityTypeChanged(BudgetDocument budgetDocument) {
        BudgetParentDocument parentDocument = budgetDocument.getBudget().getBudgetParent().getDocument();
        boolean syncRate = false;
        Collection<BudgetRate> allBudgetRates = KcServiceLocator.getService(BudgetService.class).getSavedBudgetRates(budgetDocument.getBudget());
        syncRate = getBudgetService().checkActivityTypeChange(allBudgetRates,
                                    parentDocument.getBudgetParent().getActivityTypeCode());
        return syncRate;
    }

    private BudgetService getBudgetService() {
        return KcServiceLocator.getService(BudgetService.class);
    }


}
