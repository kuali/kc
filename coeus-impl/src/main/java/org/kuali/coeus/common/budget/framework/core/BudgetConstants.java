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
package org.kuali.coeus.common.budget.framework.core;

public class BudgetConstants {
    public static final String BUDGET_PERSONNEL_NEW_GROUP_NAME = "Create New Group";
    public static final String DEFAULT_CAMPUS_FLAG = "D";
    public static final String BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT = "S";
    public static final String ENABLE_BUDGET_CALCULATED_SALARY = "enableBudgetCalculatedSalary";

    public enum BudgetPerson {
        SUMMARYPERSON ("-1", "Summary");
        private final String personId;   
        private final String personName; 
        
        BudgetPerson(String personId, String personName) {
            this.personId = personId;
            this.personName = personName;
        }

        public String getPersonId() { 
            return personId; 
        }

        public String getPersonName() { 
            return personName; 
        }
        
        public Integer getPersonSequenceNumber() { 
            return Integer.parseInt(personId); 
        }
    }

    public enum BudgetAuditRules {
        ACTIVITY_TYPE ("activityTypeErrors", "activityTypeWarnings", "Proposal", "PropDev-DetailsPage"),
        BUDGET_SETTINGS ("budgetSettingsErrors", "budgetSettingsWarnings", "Budget Settings", "PropBudget-BudgetSettings-Dialog"),
        RATES ("ratesErrors", "ratesWarnings", "Rates", "PropBudget-RatesPage"),
        PERIODS_AND_TOTALS ("periodAndTotalErrors", "periodAndTotalWarnings", "Periods & Totals", "PropBudget-PeriodsPage"),
        PROJECT_PERSONNEL ("projectPersonnelErrors", "projectPersonnelWarnings", "Project Personnel", "PropBudget-ProjectPersonnelPage"),
        NON_PERSONNEL_COSTS ("nonPersonnelCostErrors", "nonPersonnelCostWarnings", "Non-Personnel Costs", "PropBudget-NonPersonnelCostsPage"),
        SPE_LINEITEM_COSTS ("speCostErrors", "speCostWarnings", "Budget Lineitem Costs", "PropBudget-SinglePointEntryPage"),
        PERSONNEL_COSTS ("personnelCostErrors", "personnelCostWarnings", "Assign Personnel to Periods", "PropBudget-AssignPersonnelToPeriodsPage"),
        COST_SHARING ("costSharingErrors", "costSharingWarnings", "Cost Sharing", "PropBudget-CostSharingPage"),
        UNRECOVERED_FA ("unrecoveredFAErrors", "unrecoveredFAWarnings", "Unrecovered F&A", "PropBudget-UnrecoveredFandAPage");
        
        private final String errorKey;   
        private final String warningKey; 
        private final String label; 
        private final String pageId; 
        
        BudgetAuditRules(String errorKey, String warningKey, String label, String pageId) {
            this.errorKey = errorKey;
            this.warningKey = warningKey;
            this.label = label;
            this.pageId = pageId;
        }

        public String getErrorKey() { 
            return errorKey; 
        }

        public String getWarningKey() { 
            return warningKey; 
        }
        
        public String getLabel() { 
            return label; 
        }

        public String getPageId() { 
            return pageId; 
        }
    }

}
