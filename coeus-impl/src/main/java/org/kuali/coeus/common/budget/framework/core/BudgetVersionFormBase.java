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

import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyStatusConstants;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * This class contains methods common to ProposalDevelopment and Budget forms.
 */
public abstract class BudgetVersionFormBase extends KcTransactionalDocumentFormBase implements Auditable {
    
    private static final long serialVersionUID = -7013211193142134599L;
    private String newBudgetVersionName;
    private boolean auditActivated;
    private String activePanelName;
    private boolean saveAfterCopy;
    private boolean showAllBudgetVersions;
    private String proposalHierarchyIndirectObjectCode;

    
    /**
     * The type of result returned by the multi-value lookup
     *
     */
    private String lookupResultsBOClassName;
    
    /**
     * Used to indicate which result set we're using when refreshing/returning from a multi-value lookup
     */
    private String lookupResultsSequenceNumber;
    
    /**
     * Used to indicate which collection the lookup was being performed for
     */
    private String lookedUpCollectionName;
    
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
    }
    
    // Getters and setters

    public String getNewBudgetVersionName() {
        return newBudgetVersionName;
    }
    public void setNewBudgetVersionName(String newBudgetVersionName) {
        this.newBudgetVersionName = newBudgetVersionName;
    }
    
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }
    
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }
    
    // I put these here because using fmt:fmtDate in the tag didn't work b/c it's a parameter passed to tabTop.
    // This works for now but obviously isn't ideal so reengineer when possible.
    public String getFormattedStartDate() {
        Date date = null;
        if (this instanceof BudgetForm) {
            BudgetForm budgetForm = (BudgetForm) this;
            date = budgetForm.getBudgetDocument().getBudget().getBudgetParent().getRequestedStartDateInitial();
        } else {
            BudgetParentDocument parentDocument = (BudgetParentDocument)getDocument();
            date = parentDocument.getBudgetParent().getRequestedStartDateInitial();
        }
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern("MM/dd/yyyy");
            return dateFormat.format(date);
        }
        return "";
    }
    
    public String getFormattedEndDate() {
        Date date = null;
        if (this instanceof BudgetForm) {
            BudgetForm budgetForm = (BudgetForm) this;
            date = budgetForm.getBudgetDocument().getBudget().getBudgetParent().getRequestedEndDateInitial();
        } else {
            BudgetParentDocument parentDocument = (BudgetParentDocument)getDocument();
            date = parentDocument.getBudgetParent().getRequestedEndDateInitial();
        }
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern("MM/dd/yyyy");
            return dateFormat.format(date);
        }
        return "";
    }

    @Override
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    @Override
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    public String getActivePanelName() {
        return activePanelName;
    }

    public void setActivePanelName(String activePanelName) {
        this.activePanelName = activePanelName;
    }
    
    public boolean isSaveAfterCopy() {
        return saveAfterCopy;
    }

    public void setSaveAfterCopy(boolean val) {
        saveAfterCopy = val;
    }
    
    public String getHierarchyParentStatus() {
        return HierarchyStatusConstants.Parent.code();
    }
    public String getHierarchyNoneStatus() {
        return HierarchyStatusConstants.None.code();
    }
    public String getHierarchyChildStatus() {
        return HierarchyStatusConstants.Child.code();
    }

    /**
     * Sets the proposalHierarchyIndirectObjectCode attribute value.
     * @param proposalHierarchyIndirectObjectCode The proposalHierarchyIndirectObjectCode to set.
     */
    public void setProposalHierarchyIndirectObjectCode(String proposalHierarchyIndirectObjectCode) {
        this.proposalHierarchyIndirectObjectCode = proposalHierarchyIndirectObjectCode;
    }

    /**
     * Gets the proposalHierarchyIndirectObjectCode attribute. 
     * @return Returns the proposalHierarchyIndirectObjectCode.
     */
    public String getProposalHierarchyIndirectObjectCode() {
        return proposalHierarchyIndirectObjectCode;
    }

	public String getLookedUpCollectionName() {
		return lookedUpCollectionName;
	}

	public void setLookedUpCollectionName(String lookedUpCollectionName) {
		this.lookedUpCollectionName = lookedUpCollectionName;
	}

    public boolean isShowAllBudgetVersions() {
        return showAllBudgetVersions;
    }

    public void setShowAllBudgetVersions(boolean showAllBudgetVersions) {
        this.showAllBudgetVersions = showAllBudgetVersions;
    }

    public List<String> getAwardBudgetInactiveStatuses() {
        return KcServiceLocator.getService(AwardBudgetService.class).getInactiveBudgetStatus();
    }

}
