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
package org.kuali.coeus.propdev.impl.hierarchy;


public interface ProposalHierarchyKeyConstants {
    public static final String MESSAGE_LINK_SUCCESS = "message.hierarchy.link.success";
    public static final String MESSAGE_CREATE_SUCCESS = "message.hierarchy.create.success";
    public static final String MESSAGE_SYNC_SUCCESS = "message.hierarchy.sync.success";
    public static final String MESSAGE_REMOVE_SUCCESS = "message.hierarchy.remove.success";
    public static final String MESSAGE_ACTION_CANCEL = "message.hierarchy.action.cancel";

    public static final String QUESTION_REMOVE_CONFIRM = "question.hierarchy.remove.confirm";
    public static final String QUESTION_EXTEND_PROJECT_DATE_CONFIRM = "question.hierarchy.extendDate.confirm";

    public static final String WARNING_LINK_NO_FINAL_BUDGET = "warning.hierarchy.link.noFinalBudget";
    public static final String WARNING_LINK_DIFFERENT_SPONSOR = "warning.hierarchy.link.differentSponsor";
    
    public static final String ERROR_LINK_ALREADY_MEMBER = "error.hierarchy.link.alreadyHierarchyMember";
    public static final String ERROR_LINK_NOT_PARENT = "error.hierarchy.link.notParent";
    public static final String ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR = "error.hierarchy.link.noPrincipleInvestigator";
    public static final String ERROR_LINK_NO_BUDGET_VERSION = "error.hierarchy.link.noBudgetVersion";
    public static final String ERROR_LINK_PARENT_BUDGET_COMPLETE = "error.hierarchy.link.parentBudgetComplete";
    public static final String ERROR_REMOVE_PARENT_BUDGET_COMPLETE = "error.hierarchy.remove.parentBudgetComplete";
    public static final String ERROR_UNEXPECTED = "error.hierarchy.unexpected";
    public static final String ERROR_BUDGET_CHILD_STATUSES_NOT_COMPLETE = "error.hierarchy.budget.childStatusesNotComplete";
    public static final String ERROR_SYNC_NO_PRINCIPLE_INVESTIGATOR = "error.hierarchy.sync.noPrincipleInvestigator";
    public static final String ERROR_BUDGET_START_DATE_INCONSISTENT = "error.hierarchy.budget.startDateInconsistent";
    public static final String ERROR_BUDGET_PERIOD_DURATION_INCONSISTENT = "error.hierarchy.budget.periodDurationInconsistent";
    public static final String ERROR_PROPOSAL_DOES_NOT_EXIST = "error.hierarchy.proposal.not.found";
    public static final String ERROR_PROPOSAL_NOT_HIERARCHY_PARENT = "error.hierarchy.proposal.not.hierarchy.parent";
    public static final String ERROR_NOT_HIERARCHY_CHILD = "error.hierarchy.proposal.not.hierarchy.child";
    public static final String ERROR_DIFFERENT_SPONSORS = "error.child.and.parent.have.different.sponsors";
    public static final String ERROR_NOT_PARENT_AGGREGATOR = "error.hierarchy.not.parent.aggregator";
    public static final String ERROR_NOT_CHILD_AGGREGATOR = "error.hierarchy.not.child.aggregator";
    public static final String ERROR_PARENT_LOCK = "error.hierarchy.parent.locked";

    public static final String FIELD_GENERIC = "newHierarchyProposal.x";
    public static final String FIELD_PARENT_NUMBER = "newHierarchyProposalNumber";
    public static final String FIELD_CHILD_NUMBER = "newHierarchyChildProposalNumber";
    public static final String FIELD_PARENT_BUDGET_STATUS = "document.budgetDocumentVersion[0].budgetVersionOverview.budgetStatus";

    public static final String PARAMETER_NAME_DIRECT_COST_ELEMENT = "proposalHierarchySubProjectDirectCostElement";
    public static final String PARAMETER_NAME_INDIRECT_COST_ELEMENT = "proposalHierarchySubProjectIndirectCostElement";
    public static final String PARAMETER_NAME_INSTITUTE_NARRATIVE_TYPE_GROUP = "instituteNarrativeTypeGroup";
    
    public static final String HIERARCHY_CONFIRMATION_DIALOG = "PropDev-SyncConfirmationDialog";

}
