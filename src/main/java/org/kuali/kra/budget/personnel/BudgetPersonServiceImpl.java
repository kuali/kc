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
package org.kuali.kra.budget.personnel;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.PersonAppointment;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class implements methods specified by <code>{@link BudgetPersonService}</code> interface
 */
public class BudgetPersonServiceImpl implements BudgetPersonService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetPersonServiceImpl.class);
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    
    
    /**
     * 
     * @see org.kuali.kra.budget.personnel.BudgetPersonService#addBudgetPerson(org.kuali.kra.budget.core.Budget, org.kuali.kra.budget.personnel.BudgetPerson)
     */
    public void addBudgetPerson(Budget budget, BudgetPerson budgetPerson) {
        if (budgetPerson.getPersonId() != null) {
            //add budget person or adds new budget persons for each appointment the
            //employee has
            addBudgetEmployee(budget, budgetPerson);
        } else {
            populateBudgetPersonData(budget, budgetPerson);
            budget.addBudgetPerson(budgetPerson);
        }
    }
    
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
        budget.getBudgetDocument().refreshReferenceObject("documentNextvalues");
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
                    if (proposalPerson.getPersonId() != null) {
                        addBudgetEmployee(budget, new BudgetPerson(proposalPerson));
                    } else {
                        BudgetPerson newBudgetPerson = new BudgetPerson(proposalPerson);
                        populateBudgetPersonData(budget, newBudgetPerson);
                        budget.addBudgetPerson(newBudgetPerson);                        
                    }
                    //
                }
            }
        }
    }
    
    protected void addBudgetEmployee(Budget budget, BudgetPerson budgetPerson) {
        //if its a person, get all available appointments
        //and add each appointment as a separate BudgetPerson
        KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(budgetPerson.getPersonId());
        List<PersonAppointment> appointments = kcPerson.getExtendedAttributes().getPersonAppointments();
        boolean added = false;
        for (PersonAppointment appointment : appointments) {
            if (isAppointmentApplicableToBudget(budget, appointment)) {
                BudgetPerson newBudgetPerson = new BudgetPerson();
                newBudgetPerson.setPersonId(budgetPerson.getPersonId());
                newBudgetPerson.setPersonName(budgetPerson.getPersonName());
                newBudgetPerson.setNonEmployeeFlag(budgetPerson.getNonEmployeeFlag());
                newBudgetPerson.setJobCode(appointment.getJobCode());
                newBudgetPerson.setJobTitle(appointment.getJobTitle());
                newBudgetPerson.setCalculationBase(appointment.getSalary());
                // use budget start date instead of appointment start date to prepopulate effective date
                BudgetParent proposal = budget.getBudgetDocument().getParentDocument().getBudgetParent();
                budgetPerson.setEffectiveDate(proposal.getRequestedStartDateInitial());
                newBudgetPerson.setAppointmentType(appointment.getAppointmentType());
                newBudgetPerson.setAppointmentTypeCode(appointment.getTypeCode());
                populateBudgetPersonData(budget, newBudgetPerson);
                budget.addBudgetPerson(newBudgetPerson);
                added = true;
            }
        }
        //if we didn't find an appointment that was applicable, add
        //person without appointment information
        if (!added) {
            populateBudgetPersonData(budget, budgetPerson);
            budget.addBudgetPerson(budgetPerson);
        }
    }
    
    /**
     * 
     * Determines if an appointment is applicable to the current budget, currently
     * based soley on whether the budget period matches some part of the appointment
     * period
     * @param budget
     * @param appointment
     * @return true if the appointment start or end date is inside the budget period
     */
    protected boolean isAppointmentApplicableToBudget(Budget budget, PersonAppointment appointment) {
        Calendar budgetStart = Calendar.getInstance();
        Calendar budgetEnd = Calendar.getInstance();
        Calendar apptStart = Calendar.getInstance();
        Calendar apptEnd = Calendar.getInstance();
        budgetStart.setTime(budget.getStartDate());
        budgetEnd.setTime(budget.getEndDate());
        if (appointment.getStartDate() != null) {
            apptStart.setTime(appointment.getStartDate());
        } else {
            apptStart.setTime(budget.getStartDate());
        }
        if (appointment.getEndDate() != null) {
            apptEnd.setTime(appointment.getEndDate());
        } else {
            apptEnd.setTime(budget.getEndDate());
        }
        if (budgetStart.before(apptEnd) && budgetEnd.after(apptStart)) {
            return true;
        } else {
            return false;
        }
    }
    
    protected void populatePersonDefaultDataIfEmpty(Budget budget, BudgetPerson budgetPerson) {
        BudgetParent proposal = budget.getBudgetDocument().getParentDocument().getBudgetParent();
        if (proposal != null && ObjectUtils.isNull(budgetPerson.getEffectiveDate())) {
            budgetPerson.setEffectiveDate(proposal.getRequestedStartDateInitial());
        }
        
        if (ObjectUtils.isNull(budgetPerson.getCalculationBase())) {
            budgetPerson.setCalculationBase(new BudgetDecimal(this.parameterService.getParameterValueAsString(
                    BudgetDocument.class, Constants.BUDGET_PERSON_DEFAULT_CALCULATION_BASE)));
        }
        
        
        
        if (StringUtils.isBlank(budgetPerson.getAppointmentTypeCode())) {
            budgetPerson.setAppointmentTypeCode(this.parameterService.getParameterValueAsString(
                    BudgetDocument.class, Constants.BUDGET_PERSON_DEFAULT_APPOINTMENT_TYPE));
        }
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
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

    protected KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
}
