/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.personnel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.ValidCeJobCode;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.attr.PersonAppointment;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.impl.core.CostElementValuesFinder;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.data.CompoundKey;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements methods specified by <code>{@link org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService}</code> interface
 */
@Component("budgetPersonService")
public class BudgetPersonServiceImpl implements BudgetPersonService {
    
	public static final String APOINTMENT_TYPE_FIELD_NAME = "appointmentType";
	public static final String BUDGET_ID = "budgetId";
	public static final String PERSON_SEQUENCE_NUMBER = "personSequenceNumber";

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;
	@Autowired
	@Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
    
    
    @Override
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

    protected void populateBudgetPersonData(Budget budget, BudgetPerson budgetPerson) {
        budgetPerson.setBudgetId(budget.getBudgetId());
        budgetPerson.setBudget(budget);
        budgetPerson.setPersonSequenceNumber(budget.getNextValue(Constants.PERSON_SEQUENCE_NUMBER));
        populatePersonDefaultDataIfEmpty(budget, budgetPerson);
    }

    @Override
    public void populateBudgetPersonDefaultDataIfEmpty(Budget budget) {
        for (BudgetPerson budgetPerson: budget.getBudgetPersons()) {
            populatePersonDefaultDataIfEmpty(budget, budgetPerson);
        }
    }

    @Override
    public void synchBudgetPersonsToProposal(Budget budget) {
        BudgetParent budgetParent = budget.getBudgetParent();
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
                }
            }
        }
        reconcilePersonnelRoles(budget);
    }

    protected void reconcilePersonnelRoles(Budget budget) {
    	// Populate the person's proposal roles, if they exist
        List<BudgetPerson> budgetPersons = budget.getBudgetPersons();
        
        for (BudgetPerson budgetPerson: budgetPersons) {
        	PersonRolodex person = getBudgetPersonRolodex(budget,budgetPerson);
        	if(person != null) {
        		budgetPerson.setRole(person.getInvestigatorRoleDescription()); 
        		budgetPerson.setPersonRolodex(person);
        	}
        }
    }
    
    protected void addBudgetEmployee(Budget budget, BudgetPerson budgetPerson) {
        //if its a person, get all available appointments
        //and add each appointment as a separate BudgetPerson
        KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(budgetPerson.getPersonId());
        List<PersonAppointment> appointments = kcPerson.getExtendedAttributes().getPersonAppointments();
        boolean added = false;
        String defaultJobCode = this.parameterService.getParameterValueAsString("KC-B", "Document", Constants.BUDGET_PERSON_DEFAULT_JOB_CODE_PARAMETER);
        for (PersonAppointment appointment : appointments) {
            if (isAppointmentApplicableToBudget(budget, appointment)) {
                BudgetPerson newBudgetPerson = new BudgetPerson();
                newBudgetPerson.setPersonId(budgetPerson.getPersonId());
                newBudgetPerson.setPersonName(budgetPerson.getPersonName());
                newBudgetPerson.setNonEmployeeFlag(budgetPerson.getNonEmployeeFlag());
                if (StringUtils.isEmpty(appointment.getJobCode())) {
                    newBudgetPerson.setJobCode(defaultJobCode);
                } else {
                    newBudgetPerson.setJobCode(appointment.getJobCode());
                }
                newBudgetPerson.setJobTitle(appointment.getJobTitle());
                newBudgetPerson.setCalculationBase(appointment.getSalary());
                // use budget start date instead of appointment start date to prepopulate effective date
                BudgetParent proposal = budget.getBudgetParent();
                budgetPerson.setEffectiveDate(proposal.getRequestedStartDateInitial());
                newBudgetPerson.setAppointmentType(appointment.getAppointmentType());
                newBudgetPerson.setAppointmentTypeCode(appointment.getTypeCode());
                newBudgetPerson.setSalaryAnniversaryDate(kcPerson.getExtendedAttributes().getSalaryAnniversaryDate());
                populateBudgetPersonData(budget, newBudgetPerson);
                budget.addBudgetPerson(newBudgetPerson);
                added = true;
            }
        }
        //if we didn't find an appointment that was applicable, add
        //person without appointment information
        if (!added) {
            populateBudgetPersonData(budget, budgetPerson);
            budgetPerson.setJobCode(defaultJobCode);
            budgetPerson.setSalaryAnniversaryDate(kcPerson.getExtendedAttributes().getSalaryAnniversaryDate());
            budget.addBudgetPerson(budgetPerson);
        }
    }
    
    /**
     * 
     * Determines if an appointment is applicable to the current budget, currently
     * based solely on whether the budget period matches some part of the appointment
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
        BudgetParent proposal = budget.getBudgetParent();
        if (proposal != null && ObjectUtils.isNull(budgetPerson.getEffectiveDate())) {
            budgetPerson.setEffectiveDate(proposal.getRequestedStartDateInitial());
        }
        
        if (ObjectUtils.isNull(budgetPerson.getCalculationBase())) {
            budgetPerson.setCalculationBase(new ScaleTwoDecimal(this.parameterService.getParameterValueAsString(
                    Budget.class, Constants.BUDGET_PERSON_DEFAULT_CALCULATION_BASE)));
        }
        
        
        
        if (StringUtils.isBlank(budgetPerson.getAppointmentTypeCode())) {
            budgetPerson.setAppointmentTypeCode(this.parameterService.getParameterValueAsString(
                    Budget.class, Constants.BUDGET_PERSON_DEFAULT_APPOINTMENT_TYPE));
        }
  	    refreshPersonAppointmentType(budgetPerson);
    }

    protected void refreshPersonAppointmentType(BudgetPerson budgetPerson) {
		if(StringUtils.isNotEmpty(budgetPerson.getAppointmentTypeCode())) {
			getDataObjectService().wrap(budgetPerson).fetchRelationship(APOINTMENT_TYPE_FIELD_NAME);
		}
	}

    public ParameterService getParameterService() { return parameterService;}

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }


    @SuppressWarnings("unchecked")
    @Override
    public BudgetPerson findBudgetPerson(BudgetPersonnelDetails budgetPersonnelDetails) {
    	final Map queryMap = new HashMap();
    	queryMap.put(BUDGET_ID, budgetPersonnelDetails.getBudgetId());
    	queryMap.put(PERSON_SEQUENCE_NUMBER, budgetPersonnelDetails.getPersonSequenceNumber());
    	BudgetPerson budgetPerson = dataObjectService.find(BudgetPerson.class, new CompoundKey(queryMap));
        if (budgetPerson == null) {
        	//if the budgetPerson hasn't been persisted yet, just get it from the details object
        	budgetPerson = budgetPersonnelDetails.getBudgetPerson();
        }
        return budgetPerson;
    }

    /**
     * This method compares a proposal person with budget person. It checks whether the proposal person is from PERSON or ROLODEX
     * and matches the respective person ID with the person in {@link BudgetPersonnelDetails}. It returns true only if IDs are not
     * null and also matches each other.
     *
     * @param proposalPerson - key person from proposal
     * @param budgetPersonnelDetails person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     */
    @Override
    public boolean proposalPersonEqualsBudgetPerson(ProposalPersonContract proposalPerson, BudgetPersonnelDetailsContract budgetPersonnelDetails) {
        boolean equal = false;
        if (proposalPerson != null && budgetPersonnelDetails != null) {
            String budgetPersonId = budgetPersonnelDetails.getPersonId();
            if ((proposalPerson.getPersonId() != null && proposalPerson.getPersonId().equals(budgetPersonId))
                    || (proposalPerson.getRolodexId() != null && proposalPerson.getRolodexId().toString().equals(budgetPersonId))) {
                equal = true;
            }
        }
        return equal;
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

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
    
	public void refreshBudgetPerson(BudgetPerson budgetPerson) {
		refreshPersonAppointmentType(budgetPerson);
	}
	
    public PersonRolodex getBudgetPersonRolodex(Budget budget, BudgetPersonContract budgetPerson) {
        BudgetParent budgetParent = budget.getBudgetParent();
        PersonRolodex personRolodex = null;
        if (budgetParent != null) {
	        for (PersonRolodex person: budgetParent.getPersonRolodexList()) {
	            if (person.getPersonId() != null && person.getPersonId().equals(budgetPerson.getPersonId())) {
	            	personRolodex = person;
	            	break;
	            } else if (person.getRolodexId() != null && person.getRolodexId().equals(budgetPerson.getRolodexId())) {
	            	personRolodex = person;
	            	break;
	            }
	        }
        }
        return personRolodex;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ValidCeJobCode> getApplicableCostElements(Long budgetId, String personSequenceNumber) {
        List<ValidCeJobCode> validCostElements = null;

        String jobCodeValidationEnabledInd = this.parameterService.getParameterValueAsString(
                Budget.class, Constants.BUDGET_JOBCODE_VALIDATION_ENABLED);
        if(StringUtils.isNotEmpty(jobCodeValidationEnabledInd) && jobCodeValidationEnabledInd.equals("Y")) { 
            final Map fieldValues = new HashMap();
            fieldValues.put(BUDGET_ID, budgetId);
            fieldValues.put(PERSON_SEQUENCE_NUMBER, personSequenceNumber);
            BudgetPerson budgetPerson = dataObjectService.find(BudgetPerson.class, new CompoundKey(fieldValues));
            
            fieldValues.clear();
            if(budgetPerson != null && StringUtils.isNotEmpty(budgetPerson.getJobCode())) {
                fieldValues.put("jobCode", budgetPerson.getJobCode().toUpperCase());
                validCostElements = (List<ValidCeJobCode>) businessObjectService.findMatching(ValidCeJobCode.class, fieldValues);
            }
        }
        
        return validCostElements;
    } 
    
    @SuppressWarnings("unchecked")
    @Override
    public String getApplicableCostElementsForAjaxCall(Long budgetId,String personSequenceNumber, 
            String budgetCategoryTypeCode) {
        
        String resultStr = "";
        
        if(isAuthorizedToAccess(budgetCategoryTypeCode)){
            if (StringUtils.isNotBlank(budgetCategoryTypeCode) && budgetCategoryTypeCode.contains(Constants.COLON)) {
                budgetCategoryTypeCode = StringUtils.split(budgetCategoryTypeCode, Constants.COLON)[0];
            }

            List<ValidCeJobCode> validCostElements = getApplicableCostElements(budgetId, personSequenceNumber);

            if(CollectionUtils.isNotEmpty(validCostElements)) {
                for (ValidCeJobCode validCE : validCostElements) {
                    Map fieldValues = new HashMap();
                    fieldValues.put("costElement", validCE.getCostElement());
                    CostElement costElement = (CostElement) businessObjectService.findByPrimaryKey(CostElement.class, fieldValues);
                    resultStr += "," + validCE.getCostElement() + ";" + costElement.getDescription();
                }
                resultStr += ",ceLookup;false";
            } else {
                CostElementValuesFinder ceValuesFinder = new CostElementValuesFinder();
                ceValuesFinder.setBudgetCategoryTypeCode(budgetCategoryTypeCode);
                List<KeyValue> allPersonnelCostElements = ceValuesFinder.getKeyValues();
                for (KeyValue keyValue : allPersonnelCostElements) {
                    if(StringUtils.isNotEmpty(keyValue.getKey().toString())) {
                        resultStr += "," + keyValue.getKey() + ";" + keyValue.getValue();
                    }
                }
                resultStr += ",ceLookup;true";
            }
        }
        
        return resultStr;
    } 
    
    /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */
    
    private boolean isAuthorizedToAccess(String budgetCategoryTypeCode) {
        boolean isAuthorized = true;
        if(budgetCategoryTypeCode.contains(Constants.COLON)){
            if (globalVariableService.getUserSession() != null) {
                // jquery/ajax in rice 2.0
                String[] invalues = StringUtils.split(budgetCategoryTypeCode, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = globalVariableService.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof BudgetForm)) {
                        isAuthorized = false;
                    } else {
                        Map<String, String> editModes = ((BudgetForm)formObj).getEditingMode();
                        isAuthorized = BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                        || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY))
                        || BooleanUtils.toBoolean(editModes.get("modifyBudgtes"))
                        || BooleanUtils.toBoolean(editModes.get("viewBudgets"))
                        || BooleanUtils.toBoolean(editModes.get("addBudget"));
                    }
                }

            }
        }
        return isAuthorized;
    }

	protected GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}    

}
