/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget.core;

public class ProposalBudgetConstants {

	public static class KradConstants {
		public static final String BUDGET_DEFAULT_VIEW = "PropBudget-DefaultView";
		public static final String PERSONNEL_PAGE_ID = "PropBudget-ProjectPersonnelPage";
		public static final String PERIODS_AND_TOTALS_PAGE_ID = "PropBudget-PeriodsPage";
		public static final String SUBAWARDS_COLLECTION = "PropBudget-SubawardsPage-CollectionGroup";
		public static final String SUBAWARDS_ADD_DIALOG = "PropBudget-SubawardsPage-CollectionGroup-AddDialog";
        public static final String RETURN_TO_PROPOSAL_LINK = "PropBudget-Menu-ReturnToProposal";
	}
	
	public static class AuthConstants {
		public static final String MODIFY_BUDGET_EDIT_MODE = "modifyBudget";
		public static final String VIEW_BUDGET_EDIT_MODE ="viewBudget";
		public static final String MODIFY_RATES_EDIT_MODE = "modifyBudgetRates";
		public static final String MAINTAIN_PROPOSAL_HIERARCHY = "maintainProposalHierarchy";
		public static final String PRINT_EDIT_MODE = "canPrint";
		public static final String ADD_BUDGET_EDIT_MODE = "addBudget";
		public static final String CHANGE_COMPLETE_STATUS = "canChangeCompleteStatus";
		public static final String MODIFY_HIERARCHY_PARENT_BUDGET = "modifyParentBudget";
	}
}
