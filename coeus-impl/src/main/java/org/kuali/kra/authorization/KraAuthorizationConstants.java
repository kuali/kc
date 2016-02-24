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
package org.kuali.kra.authorization;

/**
 * Defines constants used in authorization-related code.
 * 
 * 
 */
public class KraAuthorizationConstants {
    public static final String KC_SYSTEM_NAMESPACE_CODE = "KC-SYS";
    
    public static final String ACTIVE_LOCK_REGION = "ACTIVE_LOCK_REGION";
    public static final String LOCK_REGION_CHANGE_IND = "ACTIVE_LOCK_REGION_CHANGED";
    
    public static final String LOCK_DESCRIPTOR_PROPOSAL = "PROPOSAL DEVELOPMENT";
    public static final String LOCK_DESCRIPTOR_BUDGET = "BUDGET";
    public static final String LOCK_DESCRIPTOR_AWARD = "AWARD";
    public static final String LOCK_DESCRIPTOR_TIME_AND_MONEY = "TIME_AND_MONEY";
    public static final String LOCK_DESCRIPTOR_PROTOCOL = "PROTOCOL";
    public static final String LOCK_DESCRIPTOR_IACUC_PROTOCOL = "IACUC_PROTOCOL";
    public static final String LOCK_DESCRIPTOR_COMMITTEE = "COMMITTEE";
    public static final String LOCK_DESCRIPTOR_INSTITUTIONAL_PROPOSAL = "INSTITUTIONAL_PROPOSAL";
    public static final String LOCK_DESCRIPTOR_COIDISCLOSURE = "COIDISCLOSURE";
    public static final String LOCK_NEGOTIATION = "NEGOTIATION";

    public static final String KC_AWARD_NAMESPACE = "KC-AWARD";
    public static final String PERMISSION_MODIFY_AWARD = "Modify Award";
    public static final String QUALIFICATION_UNIT_NUMBER = "unitNumber";
    public static final String CAN_MODIFY = "canModify";

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
        public static final String MODIFY_BUDGET_RATE = "modifyProposalBudgetRates";
    }
    
    public static final String PERMISSION_SUBMIT_PROPOSAL_LOG = "Submit Proposal Log";
  
 }
