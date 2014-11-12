package org.kuali.coeus.common.budget.framework.core;

public class BudgetConstants {
    public static final String BUDGET_PERSONNEL_NEW_GROUP_NAME = "Create New Group";
    public static final String DEFAULT_CAMPUS_FLAG = "D";
    public static final String BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT = "S";
    
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
}
