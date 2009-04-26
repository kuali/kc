/*
 * Copyright 2006-2008 The Kuali Foundation
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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.web.format.Formatter;

/**
 * This class contains methods common to ProposalDevelopment and Budget forms.
 */
public abstract class ProposalFormBase extends KraTransactionalDocumentFormBase implements Auditable {
    
    private String newBudgetVersionName;
    private Integer finalBudgetVersion;
    private boolean auditActivated;
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
    
    public ProposalFormBase() {
        super();
        Formatter.registerFormatter(BudgetDecimal.class, BudgetDecimalFormatter.class);
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
    }
    
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
            date = budgetForm.getBudgetDocument().getProposal().getRequestedStartDateInitial();
        } else if (this instanceof ProposalDevelopmentForm) {
            ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) this;
            date = pdForm.getProposalDevelopmentDocument().getRequestedStartDateInitial();
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
            date = budgetForm.getBudgetDocument().getProposal().getRequestedEndDateInitial();
        } else if (this instanceof ProposalDevelopmentForm) {
            ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) this;
            date = pdForm.getProposalDevelopmentDocument().getRequestedEndDateInitial();
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
}
