/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import javax.persistence.*;

@Entity
@Table(name = "BUDGET_DOCUMENT")
public class BudgetDocumentVersion extends KcPersistableBusinessObjectBase implements Comparable<BudgetDocumentVersion> {

    private static final String BUDGET_COMPLETE = "1";

    private static final Log LOG = LogFactory.getLog(BudgetDocumentVersion.class);

    private static final long serialVersionUID = -2143813153034264031L;
    
    @Id
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "PARENT_DOCUMENT_KEY")
    private String parentDocumentKey;

    @Column(name = "PARENT_DOCUMENT_TYPE_CODE")
    private String parentDocumentTypeCode;

    @OneToOne(mappedBy="budgetDocumentVersion", cascade=CascadeType.ALL)
    private BudgetVersionOverview budgetVersionOverview;

    @Transient
    private DocumentHeader documentHeader;

    @Override
    protected void postLoad() {
        super.postLoad();
        documentHeader = KRADServiceLocatorWeb.getDocumentHeaderService().getDocumentHeaderById(documentNumber);
    }

    public BudgetVersionOverview getBudgetVersionOverview() {
        if(budgetVersionOverview == null) {
            budgetVersionOverview = new BudgetVersionOverview();
        }

        return budgetVersionOverview;
    }

    public void setBudgetVersionOverview(BudgetVersionOverview budget) {
        this.budgetVersionOverview = budget;
    }

    public String getParentDocumentKey() {
        return parentDocumentKey;
    }

    public void setParentDocumentKey(String parentDocumentNumber) {
        this.parentDocumentKey = parentDocumentNumber;
    }

    public Budget getFinalBudget() {
        BudgetVersionOverview budgetVersionOverview = this.getBudgetVersionOverview();
        if (budgetVersionOverview != null && budgetVersionOverview.getBudgetStatus() != null && budgetVersionOverview.getBudgetStatus().equals(BUDGET_COMPLETE) && budgetVersionOverview.isFinalVersionFlag()) {
            Budget result = findBudget();
            if (result != null) {
                return (Budget) result;
            }
        }
        return null;
    }

    public boolean isBudgetComplete() {
        if (getBudgetVersionOverview() != null) {
            return BUDGET_COMPLETE.equals(getBudgetVersionOverview().getBudgetStatus());
        }
        return false;
    }

    public Budget findBudget() {
        DocumentService docService = KcServiceLocator.getService(DocumentService.class);
        try {
            BudgetDocument budgetDoc = (BudgetDocument) docService.getByDocumentHeaderId(getDocumentNumber());
            return budgetDoc.getBudget();
        } catch (WorkflowException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public int compareTo(BudgetDocumentVersion otherVersion) {
        return getBudgetVersionOverview().getBudgetVersionNumber().compareTo(otherVersion.getBudgetVersionOverview().getBudgetVersionNumber());
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentHeader getDocumentHeader() {
        return documentHeader;
    }

    public void setDocumentHeader(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    public String getParentDocumentTypeCode() {
        return parentDocumentTypeCode;
    }

    public void setParentDocumentTypeCode(String parentDocumentTypeCode) {
        this.parentDocumentTypeCode = parentDocumentTypeCode;
    }
}
