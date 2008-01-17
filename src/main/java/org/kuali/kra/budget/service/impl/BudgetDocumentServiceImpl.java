/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.service.DocumentService;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetDocumentService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import edu.iu.uis.eden.exception.WorkflowException;

public class BudgetDocumentServiceImpl implements BudgetDocumentService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetDocumentServiceImpl.class);
    
    private DocumentService documentService;
    
    public BudgetDocument getNewBudgetVersion(ProposalDevelopmentDocument pdDoc, String documentDescription) {
        
        BudgetDocument budgetDocument;
        
        try {
            budgetDocument = (BudgetDocument) documentService.getNewDocument(BudgetDocument.class);
            budgetDocument.setProposalNumber(pdDoc.getProposalNumber());
            List<BudgetVersionOverview> budgetVersions = pdDoc.getBudgetVersionOverviews();
            Integer newBudgetVersion;
            if (!budgetVersions.isEmpty()) {
                newBudgetVersion = budgetVersions.get(budgetVersions.size() - 1).getBudgetVersionNumber() + 1;
            } else {
                newBudgetVersion = 1;
            }
            budgetDocument.setBudgetVersionNumber(newBudgetVersion);
            budgetDocument.getDocumentHeader().setFinancialDocumentDescription(documentDescription);
            budgetDocument.setStartDate(pdDoc.getRequestedStartDateInitial());
            budgetDocument.setEndDate(pdDoc.getRequestedEndDateInitial());
            budgetDocument.setOhRateClassCode(1);
            budgetDocument.setUrRateClassCode(1);
            budgetDocument.setModularBudgetFlag("N");
            documentService.saveDocument(budgetDocument);
            documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        } catch (WorkflowException e) {
            LOG.error("Exception setting up new Budget Version: " + e);
            throw new RuntimeException(e);
        }
        
        return budgetDocument;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
}
