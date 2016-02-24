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
package org.kuali.coeus.common.budget.impl.personnel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.AwardBudgetSaveEvent;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.ApplyToPeriodsBudgetEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.AddPersonnelBudgetEvent;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetSavePersonnelEvent;
import org.kuali.coeus.common.budget.framework.personnel.BudgetSaveProjectPersonnelEvent;
import org.kuali.coeus.common.budget.framework.personnel.DeleteBudgetPersonEvent;
import org.kuali.coeus.common.budget.framework.personnel.JobCode;
import org.kuali.coeus.common.budget.framework.personnel.JobCodeService;
import org.kuali.coeus.common.budget.framework.personnel.ValidCeJobCode;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.common.framework.ruleengine.KcEventResult;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.CompoundKey;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@KcBusinessRule("budgetPersonnelRule")
public class BudgetPersonnelRule {

    private static final String BUDGET_PERSONS_FIELD_NAME_START = "budgetPersons[";
    private static final String BUDGET_PERSONS_FIELD_NAME_JOBCODE = "].jobCode";
    private static final String BUDGET_PERSONS_FIELD_NAME_PERSON_NUMBER = "].personNumber";
    private static final String BUDGET_PERSONS_FIELD_NAME_CALC_BASE = "].calculationBase";
    
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService boService;
    
    @Autowired
    @Qualifier("parameterService")
    private ParameterService paramService;
    
    @Autowired
    @Qualifier("budgetPersonService")
    private  BudgetPersonService budgetPersonService;
    
    @Autowired
    @Qualifier("dictionaryValidationService")
    private DictionaryValidationService dictionaryValidationService;
    
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	@Autowired
	@Qualifier("jobCodeService")
	private JobCodeService jobCodeService;

    /**
     * 
     * This method to check the 'selected' person to delete is not associate with budget personnel details
     * @return
     */
    @KcEventMethod
    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(DeleteBudgetPersonEvent event) {
        boolean valid = true;
        String errorPath = event.getErrorPath();
        // User  may delete person before the deleted detail is persisted
        if (isPersonDetailsFound (event.getBudget(), event.getBudgetPerson())) {
                final MessageMap messageMap = GlobalVariables.getMessageMap();
                messageMap.putError(errorPath, KeyConstants.ERROR_DELETE_PERSON_WITH_PERSONNEL_DETAIL, event.getBudgetPerson().getPersonName());
                valid = false;
        }
        return valid;
    }
    
    /*
     * Check if this budget person has any budgetdetail set.  This is called before checking whether
     * this person can be deleted.  If retrieve from DB, then it might not be correct because
     * the deleted detail may have not been persisted before delete person is called.
     */
    private boolean isPersonDetailsFound (Budget budget, BudgetPerson budgetPerson) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                for (BudgetPersonnelDetails budgetPersonnelDetail : lineItem.getBudgetPersonnelDetailsList()) {
                    if (budgetPersonnelDetail.getPersonSequenceNumber().equals(budgetPerson.getPersonSequenceNumber())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @KcEventMethod
    public boolean processCheckBaseSalaryFormat(BudgetSaveEvent event) {
        boolean valid = true;
        
        MessageMap messageMap = GlobalVariables.getMessageMap();
        int i = 0;
        List<BudgetPerson> budgetPersons = event.getBudget().getBudgetPersons();
        for (BudgetPerson budgetPerson : budgetPersons) {
            if (budgetPerson.getCalculationBase() == null) {
                messageMap.putError(BUDGET_PERSONS_FIELD_NAME_START + i + BUDGET_PERSONS_FIELD_NAME_CALC_BASE,
                    RiceKeyConstants.ERROR_REQUIRED, new String[] {"Base Salary"});
                    valid = false;
            } else if (budgetPerson.getCalculationBase().isNegative()) {
                messageMap.putError(BUDGET_PERSONS_FIELD_NAME_START + i + BUDGET_PERSONS_FIELD_NAME_CALC_BASE,
                    KeyConstants.ERROR_NEGATIVE_AMOUNT, new String[] {"Base Salary"});
                valid = false;
            }
            i++;
        }
                    
        return valid;
    }

    private Collection<ValidCeJobCode> getMappedCostElements(BudgetPerson person) {
        return budgetPersonService.getApplicableCostElements(person.getBudget(), 
                person.getPersonSequenceNumber().toString());
    }
    
    @KcEventMethod
    public boolean processBudgetPersonnelBusinessRules(BudgetSaveEvent event) {
        boolean valid = true;

        List<BudgetPerson> budgetPersons = event.getBudget().getBudgetPersons();

        // not an error - ProposalHierarchy parents are allowed to have duplicate BudgetPersons
        BudgetParent budgetParent = event.getBudget().getBudgetParent().getDocument().getBudgetParent();
        if (budgetParent instanceof DevelopmentProposal && ((DevelopmentProposal)budgetParent).isParent()) {
            KcEventResult result =  isNotDuplicateBudgetPersons(budgetPersons);
            GlobalVariables.getMessageMap().merge(result.getMessageMap());
        }
        return valid;
    }

    protected KcEventResult isNotDuplicateBudgetPersons(List<BudgetPerson> budgetPersons) {
        KcEventResult result = new KcEventResult();
        MessageMap messages = new MessageMap();
        result.setSuccess(true);

        for (int i = 0; i < budgetPersons.size(); i++) {
            BudgetPerson budgetPerson = budgetPersons.get(i);
            for (int j = i + 1; j < budgetPersons.size(); j++) {
                BudgetPerson budgetPersonCompare = budgetPersons.get(j);
                if (budgetPerson.isDuplicatePerson(budgetPersonCompare)) {
                    messages.putError("budgetPersons[" + j + "].personName", KeyConstants.ERROR_DUPLICATE_BUDGET_PERSON, budgetPerson.getPersonName());
                    result.setSuccess(false);
                }
            }
        }

        result.setMessageMap(messages);
        return result;
    }

    /**
     * This method executes the job code change validation rules against the budget document
     * for a specific budget period.
     * @return true is valid false if not valid
     * @throws NullPointerException if the budgetDocument is null
     * @throws IllegalArgumentException if the viewBudgetPeriod < 1
     */
    @KcEventMethod
    public boolean processCheckForJobCodeChange(BudgetSavePersonnelEvent event) {
        if (event.getBudget() == null) {
            throw new NullPointerException("the budgetDocument is null");
        }
        
        if (event.getCurrentBudgetPeriod() == null) {
            throw new NullPointerException("the currentBudgetPeriod is invalid");
        }
        
        boolean valid = true;
        
        GlobalVariables.getMessageMap().addToErrorPath("document");

        final BudgetPeriod selectedBudgetPeriod = event.getCurrentBudgetPeriod();
        
        final Collection<Integer> budgetPersonSequences
            = this.getBudgetPersonSequencesFromPersonnelDetails(selectedBudgetPeriod.getBudgetLineItems());
        
        if (CollectionUtils.isNotEmpty(budgetPersonSequences)) {
            int personIndex = 0;
            List<BudgetPerson> budgetPersons = event.getBudget().getBudgetPersons();
            for (BudgetPerson person : budgetPersons) {
            	String errorKey = BUDGET_PERSONS_FIELD_NAME_START + personIndex + BUDGET_PERSONS_FIELD_NAME_JOBCODE;
                if (budgetPersonSequences.contains(person.getPersonSequenceNumber())) {
                    if(CollectionUtils.isNotEmpty(this.getMappedCostElements(person))) {
                        valid &= this.validateJobCodeChange(person, errorKey);
                    } else {
                        valid &= this.validateJobCodeValue(person, errorKey);
                        this.updateJobCodeOnDetailsFromPerson(selectedBudgetPeriod.getBudgetLineItems(), person);
                    }
                }
                personIndex++;
            }
        }
        
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        return valid;
    }
    
    @KcEventMethod
    public boolean addPersonnelCheck(AddPersonnelBudgetEvent event) {
    	boolean result = true;
    	Budget budget = event.getBudget();
    	BudgetPeriod budgetPeriod = event.getBudgetPeriod();
	    BudgetLineItem newBudgetLineItem = event.getBudgetLineItem();
	    BudgetPersonnelDetails budgetPersonDetails = event.getBudgetPersonnelDetails();
	    String errorKey = event.getErrorKey();
	    
	    GlobalVariables.getMessageMap().addToErrorPath(errorKey);
	    if (dictionaryValidationService.validate(newBudgetLineItem).getNumberOfErrors() > 0) {
	    	result = false;
	    }
	    GlobalVariables.getMessageMap().removeFromErrorPath(errorKey);
	    
	    if(budgetPeriod == null){
	        GlobalVariables.getMessageMap().putError("viewBudgetPeriod", KeyConstants.ERROR_BUDGET_PERIOD_NOT_SELECTED);
	        result = false;
	    }
	    if(newBudgetLineItem.getCostElement() == null || StringUtils.equalsIgnoreCase(newBudgetLineItem.getCostElement(), "")){
	        GlobalVariables.getMessageMap().putError(errorKey, KeyConstants.ERROR_COST_ELEMENT_NOT_SELECTED);
	        result = false;
	    }
	    if(budgetPersonDetails.getPersonSequenceNumber() == null){
	        GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_BUDGET_PERSONNEL_NOT_SELECTED);
	        result = false;
	    } 
	    if(!processCheckJobCodeObjectCodeCombo(budget, budgetPersonDetails, false)) {
	        GlobalVariables.getMessageMap().putError(errorKey, KeyConstants.ERROR_JOBCODE_COST_ELEMENT_COMBO_INVALID);
	        result = false;
	    }
	    return result;
    }
    
    /**
     * Validates if the job code is a valid change.
     * @param person the current person
     * @return true is valid false if not valid
     */
    private boolean validateJobCodeChange(final BudgetPerson person, String errorKey) {
        boolean valid = true;
        BudgetPerson personCopy = getOriginalBudgetPerson(person);
        if (personCopy != null && !person.isDuplicatePerson(personCopy)) {
            if (!StringUtils.equals(person.getJobCode(), personCopy.getJobCode())) {
                final MessageMap messageMap = GlobalVariables.getMessageMap();
                messageMap.putError(errorKey, KeyConstants.ERROR_PERSON_JOBCODE_CHANGE, person.getPersonName());
                valid = false;
            }
        }
        return valid;
    }
    
    protected BudgetPerson getOriginalBudgetPerson(BudgetPerson person) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("budgetId", person.getBudgetId());
        queryMap.put("personSequenceNumber", person.getPersonSequenceNumber());
        return getDataObjectService().find(BudgetPerson.class, new CompoundKey(queryMap));
    }
    
    /**
     * Validates if the job code is a valid value.
     * @param person the current person
     * @return true is valid false if not valid
     */
    private boolean validateJobCodeValue(BudgetPerson person, String errorKey) {
        assert person != null : "the person is null";
        
        boolean valid = true;
        if (person.getJobCode() == null) {
            final MessageMap messageMap = GlobalVariables.getMessageMap();
            messageMap.putError(errorKey, KeyConstants.ERROR_PERSON_JOBCODE_VALUE, person.getPersonName());
            valid = false;
        }
        return valid;
    }
    
    /**
     * Gets a Collection of sequence numbers from every lines items personnel details
     * @param budgetLineItems the lines items
     * @return Collection of sequence numbers
     */
    private Collection<Integer> getBudgetPersonSequencesFromPersonnelDetails(final Collection<BudgetLineItem> budgetLineItems) {
        assert budgetLineItems != null : "the budgetLineItems is null";
        
        final Collection<Integer> budgetPersonSequences = new ArrayList<Integer>();
        
        for (final BudgetLineItem budgetLineItem : budgetLineItems) {
            for (final BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                budgetPersonSequences.add(budgetPersonnelDetails.getPersonSequenceNumber());
            }
        }
        return budgetPersonSequences;
    }
    
    /**
     * Updates personnel details job code from a person's job code.
     * @param budgetLineItems the lines items
     * @param person the person
     */
    private void updateJobCodeOnDetailsFromPerson(final Collection<BudgetLineItem> budgetLineItems, final BudgetPerson person) {
        assert budgetLineItems != null : "the budgetLineItems is null";
        assert person != null : "the person is null";
        
        if (person.getJobCode() == null) {
            return;
        }
        
        for (final BudgetLineItem budgetLineItem : budgetLineItems) {
            for (final BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                if (person.getPersonSequenceNumber().equals(budgetPersonnelDetails.getPersonSequenceNumber())) {
                    budgetPersonnelDetails.setJobCode(person.getJobCode());
                }  
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<ValidCeJobCode> getApplicableCostElements(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails, boolean save) {
        List<ValidCeJobCode> validCostElements = null;
    
        if (save) {
            String jobCodeValidationEnabledInd = this.paramService.getParameterValueAsString(Budget.class, Constants.BUDGET_JOBCODE_VALIDATION_ENABLED);
            
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            BudgetPerson budgetPerson = null;
            
            if(StringUtils.isNotEmpty(jobCodeValidationEnabledInd) && jobCodeValidationEnabledInd.equals("Y")) { 

                List<BudgetPerson> budgetPersons = budget.getBudgetPersons();
                for (BudgetPerson tmpBudgetPerson : budgetPersons) {
                    if(tmpBudgetPerson.getPersonSequenceNumber().intValue() == newBudgetPersonnelDetails.getPersonSequenceNumber().intValue()) {
                        budgetPerson = tmpBudgetPerson;
                        break;
                    }
                }
                if(budgetPerson != null && StringUtils.isNotEmpty(budgetPerson.getJobCode())) {
                    fieldValues.put("jobCode", budgetPerson.getJobCode().toUpperCase());
                    validCostElements = (List<ValidCeJobCode>) this.boService.findMatching(ValidCeJobCode.class, fieldValues);
                }
            }
            
        } else {
            validCostElements = budgetPersonService.getApplicableCostElements(budget, 
                        newBudgetPersonnelDetails.getPersonSequenceNumber().toString());
        }
         
        return validCostElements;
    }
    
    protected boolean processCheckJobCodeObjectCodeCombo(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails, boolean save) {
        List<ValidCeJobCode> validCostElements = null;
        boolean isValid = false;
        
        validCostElements = getApplicableCostElements(budget, newBudgetPersonnelDetails, save);
        
        if(CollectionUtils.isEmpty(validCostElements)) {
            isValid = true;
        } else {
            for(ValidCeJobCode validCeJobCode : validCostElements) {
                if(validCeJobCode.getCostElement().equalsIgnoreCase(newBudgetPersonnelDetails.getCostElement())) {
                    isValid = true;
                    break;
                }
            }
        }

        return isValid;
    }
    
    @KcEventMethod
    public boolean budgetPersonnelDetailsCheck(ApplyToPeriodsBudgetEvent event) {
    	return budgetPersonnelDetailsCheck(event.getBudget(), event.getBudgetLineItem(), event.getErrorPath());
    }
    
    private boolean budgetPersonnelDetailsCheck(Budget budget, BudgetLineItem budgetLineItem, String errorPath) {
        boolean valid = true;
        boolean validJobCodeCECombo = false;
        
        int k=0;
        for(BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
        	String detailsErrorPath = errorPath + ".budgetPersonnelDetailsList[" + k + "]";
            valid &= !(personnelDetailsCheck(budget, budgetLineItem, budgetPersonnelDetails, detailsErrorPath));
            
            validJobCodeCECombo = processCheckJobCodeObjectCodeCombo(budget, budgetPersonnelDetails, true);
            if(!validJobCodeCECombo)  {
                GlobalVariables.getMessageMap().putError(detailsErrorPath + ".personSequenceNumber", KeyConstants.ERROR_SAVE_JOBCODE_COST_ELEMENT_COMBO_INVALID);
            }
            valid &= validJobCodeCECombo;
            k++;
        }
        
        return valid;
    }

    @KcEventMethod
    public boolean budgetPersonnelDetailsCheck(AwardBudgetSaveEvent event) {
          boolean valid = true;
          List<BudgetPeriod> budgetPeriods = event.getBudget().getBudgetPeriods();
          List<BudgetLineItem> budgetLineItems;
          int i=0;
          int j=0;
          
          for(BudgetPeriod budgetPeriod: budgetPeriods){
              j=0;
              budgetLineItems = budgetPeriod.getBudgetLineItems();
              for(BudgetLineItem budgetLineItem: budgetLineItems){
                  if (budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equals("P")) {
                      valid &= budgetPersonnelDetailsCheck(event.getBudget(), budgetLineItem, event.getErrorPath() + "budgetPeriod[" + i + "].budgetLineItems[" + j + "]");
                  }
                  j++;
              }
              i++;
          }
          
          return valid;
    }
    
    @Deprecated
    private boolean personnelDetailsCheck(Budget budget, BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, String errorPath) {
        boolean errorFound = false;
        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        
        if(StringUtils.isEmpty(budgetPersonnelDetails.getPeriodTypeCode())) { 
            GlobalVariables.getMessageMap().putError("periodTypeCode", KeyConstants.ERROR_REQUIRED_PERIOD_TYPE);
            errorFound=true;
        }
        
        if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("percentEffort", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("percentCharged", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getMessageMap().putError("percentCharged", KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        errorFound = errorFound || personnelDatesCheck(budgetLineItem, budgetPersonnelDetails, errorPath);
        
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        return errorFound;
    }

    @Deprecated
    private boolean personnelDatesCheck(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, String errorPath) {
        boolean errorFound = false;
        
        if(budgetPersonnelDetails.getStartDate() == null) {
            GlobalVariables.getMessageMap().putError("startDate", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Start Date (Start Date)" });
            errorFound=true;
        }
        if(budgetPersonnelDetails.getEndDate() == null) {
            GlobalVariables.getMessageMap().putError("endDate", RiceKeyConstants.ERROR_REQUIRED, new String[] { "End Date (End Date)" });
            errorFound=true;
        }
        
        if(!errorFound) {
            if(budgetPersonnelDetails.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
                GlobalVariables.getMessageMap().putError("endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_DATES);
                errorFound=true;
            }
            if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getEndDate()) < 0) {
                GlobalVariables.getMessageMap().putError("endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be after", "end date"});
                errorFound=true;
            }
            if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getEndDate()) > 0) {
                GlobalVariables.getMessageMap().putError("endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be before", "start date"});
                errorFound=true;
            }
            if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getStartDate()) > 0) {
                GlobalVariables.getMessageMap().putError("startDate", KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be before", "start date"});
                errorFound=true;
            }
            if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
                GlobalVariables.getMessageMap().putError("startDate", KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be after", "end date"});
                errorFound=true;
            }
        }
        
        return errorFound;
    }    

    @KcEventMethod
    public KcEventResult validateProjectPersonnel(BudgetSaveProjectPersonnelEvent event) {
    	KcEventResult result = new KcEventResult();
    	result.getMessageMap().addToErrorPath(event.getErrorPath());
    	verifyJobCode(event, result);
    	result.getMessageMap().removeFromErrorPath(event.getErrorPath());
    	return result;
    }
    
    protected void verifyJobCode(BudgetSaveProjectPersonnelEvent event, KcEventResult result) {
    	List<BudgetLineItem> budgetLineItems = getBudgetLineItems(event);
    	BudgetPerson budgetPerson = event.getBudgetPerson();
    	if(isJobCodeChanged(budgetPerson) && isNewJobCodeValid(budgetPerson, result)) {
            if (isBudgetPersonExistsInPersonnelDetails(budgetLineItems, budgetPerson)) {
            	if(isJobCodeValid(budgetPerson)) {
                    updateJobCodeOnDetailsFromPerson(budgetLineItems, budgetPerson);
            	}else {
            		result.setSuccess(false);
            	}
            }
    	}
    }
    
    protected boolean isJobCodeValid(BudgetPerson budgetPerson) {
        if(CollectionUtils.isNotEmpty(getMappedCostElements(budgetPerson))) {
        	return validateJobCodeChange(budgetPerson, "jobCode");
        } else {
        	return validateJobCodeValue(budgetPerson, "jobCode");
        }
    }
    
    protected boolean isNewJobCodeValid(BudgetPerson budgetPerson, KcEventResult result) {
    	JobCode jobCode = getJobCodeService().findJobCodeRef(budgetPerson.getJobCode());
    	if(jobCode == null) {
            result.getMessageMap().putError("jobCode", KeyConstants.ERROR_PERSON_INVALID_JOBCODE_VALUE);
    		result.setSuccess(false);
    		return false;
    	}else {
    		return true;
    	}
    }
    
    protected boolean isJobCodeChanged(BudgetPerson budgetPerson) {
    	BudgetPerson originalBudgetPerson = getOriginalBudgetPerson(budgetPerson);
    	return originalBudgetPerson != null && !StringUtils.equalsIgnoreCase(originalBudgetPerson.getJobCode(), budgetPerson.getJobCode());
    }
    
    protected boolean isBudgetPersonExistsInPersonnelDetails(List<BudgetLineItem> budgetLineItems, BudgetPerson budgetPerson) {
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                if(budgetPersonnelDetails.getPersonSequenceNumber().equals(budgetPerson.getPersonSequenceNumber())){
                	return true;
                }
            }
        }
        return false;
    }
    
    protected List<BudgetLineItem> getBudgetLineItems(BudgetSaveProjectPersonnelEvent event) {
    	List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        for(BudgetPeriod budgetPeriod : event.getBudget().getBudgetPeriods()) {
        	budgetLineItems.addAll(budgetPeriod.getBudgetLineItems());
        }
        return budgetLineItems;
    }
    
	protected BudgetPersonService getBudgetPersonService() {
		return budgetPersonService;
	}

	protected BusinessObjectService getBoService() {
		return boService;
	}

	protected ParameterService getParamService() {
		return paramService;
	}

	protected DictionaryValidationService getDictionaryValidationService() {
		return dictionaryValidationService;
	}

	public void setBoService(BusinessObjectService boService) {
		this.boService = boService;
	}

	public void setParamService(ParameterService paramService) {
		this.paramService = paramService;
	}

	public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
		this.budgetPersonService = budgetPersonService;
	}

	public void setDictionaryValidationService(
			DictionaryValidationService dictionaryValidationService) {
		this.dictionaryValidationService = dictionaryValidationService;
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public JobCodeService getJobCodeService() {
		return jobCodeService;
	}

	public void setJobCodeService(JobCodeService jobCodeService) {
		this.jobCodeService = jobCodeService;
	}
}
