/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web.struts.form;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;

/**
 * This class contains methods common to ProposalDevelopment and Budget forms.
 */
public abstract class BudgetVersionFormBase extends KraTransactionalDocumentFormBase implements Auditable {
    
    private static final long serialVersionUID = -7013211193142134599L;
    private String newBudgetVersionName;
    private Integer finalBudgetVersion;
    private boolean auditActivated;
    private String activePanelName;
    private boolean saveAfterCopy;
    private boolean showAllBudgetVersions;

    private String proposalHierarchyIndirectObjectCode;

    
    /**
     * The type of result returned by the multi-value lookup
     *
     * TODO: to be persisted in the lookup results service instead? See https://test.kuali.org/confluence/display/KULRNE/Using+multiple+value+lookups
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
    public Integer getFinalBudgetVersion() {
        return finalBudgetVersion;
    }
    public void setFinalBudgetVersion(Integer finalBudgetVersion) {
        this.finalBudgetVersion = finalBudgetVersion;
    }
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
            date = budgetForm.getDocument().getParentDocument().getBudgetParent().getRequestedStartDateInitial();
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
            date = budgetForm.getDocument().getParentDocument().getBudgetParent().getRequestedEndDateInitial();
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

    /** {@inheritDoc} */
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    /** {@inheritDoc} */
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

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#getInactiveBudgetStatus()
     */
    public List<String> getAwardBudgetInactiveStatuses() {
        return KraServiceLocator.getService(AwardBudgetService.class).getInactiveBudgetStatus();
    }

    
}
