/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.personnel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class implements methods specified by <code>{@link BudgetPersonService}</code> interface
 */
public class BudgetPersonServiceImpl implements BudgetPersonService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetPersonServiceImpl.class);
    
    private KualiConfigurationService kualiConfigurationService;
    private BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.kra.budget.personnel.BudgetPersonService#populateBudgetPersonData(org.kuali.kra.budget.core.Budget, org.kuali.kra.budget.personnel.BudgetPerson)
     */
    public void populateBudgetPersonData(Budget budget, BudgetPerson budgetPerson) {
        
//        budgetPerson.setProposalNumber(budget.getProposalNumber());
//        budgetPerson.setBudgetVersionNumber(budget.getBudgetVersionNumber());
        budgetPerson.setBudgetId(budget.getBudgetId());
        budgetPerson.setPersonSequenceNumber(budget.getHackedDocumentNextValue(Constants.PERSON_SEQUENCE_NUMBER));
        
        populatePersonDefaultDataIfEmpty(budget, budgetPerson);
    }
    
    /**
     * @see org.kuali.kra.budget.personnel.BudgetPersonService#populateDefaultDataIfEmpty(org.kuali.kra.budget.core.Budget, org.kuali.kra.budget.personnel.BudgetPerson)
     */
    public void populateBudgetPersonDefaultDataIfEmpty(Budget budget) {
        for (BudgetPerson budgetPerson: budget.getBudgetPersons()) {
            populatePersonDefaultDataIfEmpty(budget, budgetPerson);
        }
    }
    
    /**
     * @see org.kuali.kra.budget.personnel.BudgetPersonService#synchBudgetPersonsToProposal(org.kuali.kra.budget.core.Budget)
     */
    public void synchBudgetPersonsToProposal(Budget budget) {
        budget.refreshReferenceObject("documentNextvalues");
        BudgetParent budgetParent = budget.getBudgetDocument().getParentDocument().getBudgetParent();
        for (PersonRolodex proposalPerson: budgetParent.getPersonRolodexList()) {
            if (!proposalPerson.isOtherSignificantContributorFlag()) {
                boolean present = false;
                for (BudgetPerson budgetPerson: budget.getBudgetPersons()) {
                    if (proposalPerson.getPersonId() != null && proposalPerson.getPersonId().equals(budgetPerson.getPersonId())) {
                        present = true;
                        break;
                    } else if (proposalPerson.getRolodexId() != null && proposalPerson.getRolodexId().equals(budgetPerson.getRolodexId())) {
                        present = true;
                        break;
                    }
                }
                if (!present) {
                    BudgetPerson newBudgetPerson = new BudgetPerson(proposalPerson);
                    populateBudgetPersonData(budget, newBudgetPerson);
                    budget.addBudgetPerson(newBudgetPerson);
                }
            }
        }
    }
    
    private void populatePersonDefaultDataIfEmpty(Budget budget, BudgetPerson budgetPerson) {
        BudgetParent proposal = budget.getBudgetDocument().getParentDocument().getBudgetParent();
        if (proposal != null && ObjectUtils.isNull(budgetPerson.getEffectiveDate())) {
            budgetPerson.setEffectiveDate(proposal.getRequestedStartDateInitial());
        }
        
        if (ObjectUtils.isNull(budgetPerson.getCalculationBase())) {
            budgetPerson.setCalculationBase(new BudgetDecimal(kualiConfigurationService.getParameterValue(
                    Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_PERSON_DEFAULT_CALCULATION_BASE)));
        }
        
        if (StringUtils.isBlank(budgetPerson.getAppointmentTypeCode())) {
            budgetPerson.setAppointmentTypeCode(kualiConfigurationService.getParameterValue(
                    Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_PERSON_DEFAULT_APPOINTMENT_TYPE));
        }
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    @SuppressWarnings("unchecked") 
    public BudgetPerson findBudgetPerson(BudgetPersonnelDetails budgetPersonnelDetails) {
        Map queryMap = new HashMap();
//        queryMap.put("proposalNumber", budgetPersonnelDetails.getProposalNumber());
//        queryMap.put("budgetVersionNumber", budgetPersonnelDetails.getBudgetVersionNumber());
        queryMap.put("budgetId", budgetPersonnelDetails.getBudgetId());
        queryMap.put("personSequenceNumber", budgetPersonnelDetails.getPersonSequenceNumber());
        return (BudgetPerson)businessObjectService.findByPrimaryKey(BudgetPerson.class, queryMap);
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
}
