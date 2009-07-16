/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.authorization;

/**
 * Defines constants used in authorization-related code.
 * 
 * 
 */
public class KraAuthorizationConstants {
    public static final String ACTIVE_LOCK_REGION = "ACTIVE_LOCK_REGION";
    public static final String LOCK_REGION_CHANGE_IND = "ACTIVE_LOCK_REGION_CHANGED";
    
    public static final String LOCK_DESCRIPTOR_PROPOSAL = "PROPOSAL";
    public static final String LOCK_DESCRIPTOR_BUDGET = "BUDGET";
    public static final String LOCK_DESCRIPTOR_NARRATIVES = "NARRATIVES";
    public static final String LOCK_DESCRIPTOR_AWARD = "AWARD";
    public static final String LOCK_DESCRIPTOR_TIME_AND_MONEY = "TIME_AND_MONEY";
    public static final String LOCK_DESCRIPTOR_PROTOCOL = "PROTOCOL";
    public static final String LOCK_DESCRIPTOR_COMMITTEE = "COMMITTEE";
    public static final String LOCK_DESCRIPTOR_INSTITUTIONAL_PROPOSAL = "INSTITUTIONAL_PROPOSAL";

    public static class ProposalEditMode {
        public static final String MODIFY_PROPOSAL = "modifyProposal";
        public static final String MODIFY_PERMISSIONS = "modifyPermissions";
        public static final String ADD_NARRATIVES = "addNarratives";
        public static final String VIEW_PROPOSAL = "viewProposal";
        public static final String VIEW_PERMISSIONS = "viewPermissions";
        public static final String VIEW_NARRATIVES = "downloadNarratives";
    }
    
    public static class BudgetEditMode {
        public static final String MODIFY_BUDGET = "modifyBudgets";
        public static final String VIEW_BUDGET = "viewBudgets";
    }
  
 }
