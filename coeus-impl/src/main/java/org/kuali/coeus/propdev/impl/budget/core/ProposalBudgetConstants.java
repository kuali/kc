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
package org.kuali.coeus.propdev.impl.budget.core;

public class ProposalBudgetConstants {

	public static class KradConstants {
		public static final String BUDGET_DEFAULT_VIEW = "PropBudget-DefaultView";
		public static final String PERSONNEL_PAGE_ID = "PropBudget-ProjectPersonnelPage";
		public static final String PERIODS_AND_TOTALS_PAGE_ID = "PropBudget-PeriodsPage";
		public static final String SUBAWARDS_COLLECTION = "PropBudget-SubawardsPage-CollectionGroup";
		public static final String SUBAWARDS_ADD_DIALOG = "PropBudget-SubawardsPage-CollectionGroup-AddDialog";
        public static final String RETURN_TO_PROPOSAL_LINK = "PropBudget-Menu-ReturnToProposal";
        public static final String COST_SHARING_PAGE_ID = "PropBudget-CostSharingPage";
        public static final String UNRECOVERED_PAGE_ID = "PropBudget-UnrecoveredFandAPage";
        public static final String SUMMARY_PAGE_ID = "PropBudget-SummaryPage";
        public static final String RATES_PAGE_ID = "PropBudget-RatesPage";
        public static final String ASSIGN_PERSONNEL_PAGE_ID = "PropBudget-AssignPersonnelToPeriodsPage";
        public static final String NON_PERSONNEL_PAGE_ID = "PropBudget-NonPersonnelCostsPage";
        public static final String SPE_PAGE_ID = "PropBudget-SinglePointEntryPage";
        public static final String SUBAWARD_PAGE_ID = "PropBudget-SubawardsPage";
        public static final String PROJECT_INCOME_PAGE_ID = "PropBudget-ProjectIncomePage";
        public static final String MODULAR_PAGE_ID = "PropBudget-ModularPage";
        public static final String NOTES_PAGE_ID = "PropBudget-NotesPage";
        public static final String COMPLETE_CONFIRMATION_DIALOG = "PropDev-CompleteBudgetConfirmationDialog";
		public static final String EDIT_NONPERSONNEL_PERIOD_DIALOG_ID = "PropBudget-NonPersonnelCostsPage-EditNonPersonnel-Dialog";
		public static final String EDIT_NONPERSONNEL_PARTICIPANT_DIALOG_ID = "PropBudget-NonPersonnelCostsPage-EditParticipantSupport-Dialog";
		public static final String CONFIRM_PERIOD_CHANGES_DIALOG_ID = "PropBudget-ConfirmPeriodChangesDialog";
		public static final String ADD_NONPERSONNEL_PERIOD_DIALOG_ID = "PropBudget-NonPersonnelCostsPage-AddNonPersonnel-Dialog";
		public static final String CONFIRM_SYNC_TO_PERIOD_COST_LIMIT_DIALOG_ID = "PropBudget-NonPersonnelCosts-SyncToPeriodCostLimit";
		public static final String CONFIRM_SYNC_TO_DIRECT_COST_LIMIT_DIALOG_ID = "PropBudget-NonPersonnelCosts-SyncToDirectCostLimit";
		public static final String PROP_BUDGET_FORMULATED_COST_EDIT_DETAILS = "PropBudget-FormulatedCost-EditDetails";
		public static final String PROP_BUDGET_FORMULATED_COST_DELETE_CONFIRM = "PropBudget-FormulatedCost-DeleteConfirm";
		public static final String PROP_DEV_CLOSE_BUDGET_DIALOG = "PropDev-CloseBudget-Dialog";
		public static final String PROP_BUDGET_NON_PERSONNEL_COSTS_APPLY_TO_LATER_PERIODS = "PropBudget-NonPersonnelCosts-ApplyToLaterPeriods";
		static final String CONFIRM_RATE_CHANGES_DIALOG_ID = "PropBudget-BudgetSettings-ChangeRateDialog";
		static final String BUDGET_SETTINGS_DIALOG_ID = "PropBudget-BudgetSettings-Dialog";
		static final String ACTIVITY_RATE_CHANGE_DIALOG_ID = "PropBudget-ActivityTypeChanged-Dialog";
		static final String NO_RATES_DIALOG_ID = "PropBudget-NoRates-Dialog";
		public static final String EDIT_NONPERSONNEL_PERIOD_DIALOG_ID_SPE= "PropBudget-SinglePointEntryPage-EditNonPersonnel-Dialog";
		public static final String EDIT_NONPERSONNEL_PARTICIPANT_DIALOG_ID_SPE = "PropBudget-SinglePointEntryPage-EditParticipantSupport-Dialog";
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
