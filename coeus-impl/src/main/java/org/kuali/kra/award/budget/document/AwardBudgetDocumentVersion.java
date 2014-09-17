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
package org.kuali.kra.award.budget.document;

import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class AwardBudgetDocumentVersion extends KcPersistableBusinessObjectBase implements Comparable<AwardBudgetDocumentVersion> {

    private static final long serialVersionUID = -9022861120797972660L;
    
    private BudgetParentDocument parentDocument;

    private String documentNumber;

    private String parentDocumentKey;

    private String parentDocumentTypeCode;
    
    private BudgetVersionOverview budgetVersionOverview;

    public BudgetParentDocument getParentDocument() {
        return parentDocument;
    }

    public void setParentDocument(BudgetParentDocument parentDocument) {
        this.parentDocument = parentDocument;
    }

	public BudgetVersionOverview getBudgetVersionOverview() {
		return budgetVersionOverview;
	}

	public void setBudgetVersionOverview(BudgetVersionOverview budgetVersionOverview) {
		this.budgetVersionOverview = budgetVersionOverview;
	}

	@Override
	public int compareTo(AwardBudgetDocumentVersion o) {
        return getBudgetVersionOverview().getBudgetVersionNumber().compareTo(o.getBudgetVersionOverview().getBudgetVersionNumber());
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getParentDocumentKey() {
		return parentDocumentKey;
	}

	public void setParentDocumentKey(String parentDocumentKey) {
		this.parentDocumentKey = parentDocumentKey;
	}

	public String getParentDocumentTypeCode() {
		return parentDocumentTypeCode;
	}

	public void setParentDocumentTypeCode(String parentDocumentTypeCode) {
		this.parentDocumentTypeCode = parentDocumentTypeCode;
	}

}
