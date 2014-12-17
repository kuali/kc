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
