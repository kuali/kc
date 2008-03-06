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

import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public class BudgetServiceImpl implements BudgetService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetServiceImpl.class);
    
    private DocumentService documentService;
    private KualiConfigurationService kualiConfigurationService;
    
    /**
     * @see org.kuali.kra.budget.service.BudgetService#getNewBudgetVersion(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public BudgetDocument getNewBudgetVersion(ProposalDevelopmentDocument pdDoc, String documentDescription) throws WorkflowException {
        
        BudgetDocument budgetDocument;
        Integer budgetVersionNumber = pdDoc.getNextBudgetVersionNumber();
        
        budgetDocument = (BudgetDocument) documentService.getNewDocument(BudgetDocument.class);
        budgetDocument.setProposalNumber(pdDoc.getProposalNumber());
        budgetDocument.setBudgetVersionNumber(budgetVersionNumber);
        budgetDocument.getDocumentHeader().setFinancialDocumentDescription(documentDescription);
        budgetDocument.setStartDate(pdDoc.getRequestedStartDateInitial());
        budgetDocument.setEndDate(pdDoc.getRequestedEndDateInitial());
        budgetDocument.setOhRateClassCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budgetDocument.setUrRateClassCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budgetDocument.setModularBudgetFlag(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        
        // Copy in key personnel
        for (ProposalPerson proposalPerson: pdDoc.getProposalPersons()) {
            BudgetPerson budgetPerson = new BudgetPerson();
            budgetPerson.setProposalNumber(pdDoc.getProposalNumber());
            budgetPerson.setBudgetVersionNumber(budgetVersionNumber);
            if (proposalPerson.getPersonId() != null) {
                budgetPerson.setPersonId(proposalPerson.getPersonId());
                budgetPerson.setNonEmployeeFlag(proposalPerson.getPersonId()==null);
            } else {
                budgetPerson.setPersonId(proposalPerson.getRolodexId().toString());
                budgetPerson.setNonEmployeeFlag(proposalPerson.getRolodexId()==null);
            }
            budgetPerson.setPersonName(proposalPerson.getName());
            budgetPerson.setJobCode("0");
            budgetPerson.setAppointmentTypeCode("1");
            budgetPerson.setCalculationBase(BudgetDecimal.ZERO);
            budgetPerson.setEffectiveDate(pdDoc.getRequestedStartDateInitial());
            budgetDocument.addBudgetPerson(budgetPerson);
        }
        
        documentService.saveDocument(budgetDocument);
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        
        return budgetDocument;
    }
    
    /**
     * @see org.kuali.kra.budget.service.BudgetService#copyBudgetVersion(org.kuali.kra.budget.document.BudgetDocument)
     */
    public BudgetDocument copyBudgetVersion(BudgetDocument budgetDocument) throws WorkflowException {
        budgetDocument.toCopy();
        documentService.saveDocument(budgetDocument);
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        return budgetDocument;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    
}
