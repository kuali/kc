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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * Business Rules for the BudgetPersonnel panel. 
 */
public class BudgetPersonnelRule {

    private static final String BUDGET_PERSONS_FIELD_NAME_START = "budgetPersons[";
    private static final String BUDGET_PERSONS_FIELD_NAME_JOBCODE = "].jobCode";
    private static final String BUDGET_PERSONS_FIELD_NAME_PERSON_NUMBER = "].personNumber";
    private static final String BUDGET_PERSONS_FIELD_NAME_CALC_BASE = "].calculationBase";
    
    private final BusinessObjectService boService;
    private final ParameterService paramService;
    private final BudgetService budgetService;
    
    /**
     * Creates a new BudgetPersonnelRule setting the required services using the
     * {@link KraServiceLocator KraServiceLocator}.
     */
    public BudgetPersonnelRule() {
        this(KraServiceLocator.getService(BusinessObjectService.class),
            KraServiceLocator.getService(ParameterService.class),
            KraServiceLocator.getService(BudgetService.class));
    }
    
    /**
     * Creates a new BudgetPersonnelRule setting the required services.
     * <p>
     * Default access for easy unit testing.
     * </p>
     * @param boService the BusinessObjectService
     * @param paramService the ParameterService
     * @param budgetService the BudgetService
     * @throws NullPointerException if any of the services are null.
     */
    public BudgetPersonnelRule(final BusinessObjectService boService,
        final ParameterService paramService,
        final BudgetService budgetService) {
        
        if (boService == null) {
            throw new NullPointerException("the boService is null");
        }
        
        if (paramService == null) {
            throw new NullPointerException("the paramService is null");
        }
        
        if (budgetService == null) {
            throw new NullPointerException("the budgetService is null");
        }
        
        this.boService = boService;
        this.paramService = paramService;
        this.budgetService = budgetService;
    }
    
    /**
     * 
     * This method is to give an error if the person drop down list is not complete because the person entries
     * is not complete.  ALso, let user know to go to person page to fix it.
     * This is more like a warning message.
     * @param budgetDocument
     * @return
     */
    
    public boolean processCheckCompleteEntriesBusinessRules(BudgetDocument budgetDocument) {
        boolean valid = true;
        
        MessageMap messageMap = GlobalVariables.getMessageMap();
        List<BudgetPerson> budgetPersons = budgetDocument.getBudget().getBudgetPersons();
        for (BudgetPerson budgetPerson : budgetPersons) {
            if (StringUtils.isBlank(budgetPerson.getJobCode()) || StringUtils.isBlank(budgetPerson.getAppointmentTypeCode()) || budgetPerson.getCalculationBase().isLessThan(BudgetDecimal.ZERO) || budgetPerson.getEffectiveDate() == null) {
                messageMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_IMCOMPLETE_PERSON_ENTRIES);
                    valid = false;
            }
        }
                    
        return valid;
    }

    /**
     * 
     * This method to check the 'selected' person to delete is not associate with budget personnel details
     * @param budgetDocument
     * @param budgetPerson
     * @return
     */
    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(BudgetDocument budgetDocument, BudgetPerson budgetPerson) {
        boolean valid = true;
        final Map<String, Object> qMap = new HashMap<String, Object>();
        qMap.put("budgetId", budgetDocument.getBudget().getBudgetId());
        qMap.put("personId", budgetPerson.getPersonId());
        qMap.put("personSequenceNumber", budgetPerson.getPersonSequenceNumber());

        // User  may delete person before the deleted detail is persisted
        if (isPersonDetailsFound (budgetDocument.getBudget(), budgetPerson)) {
        //if (CollectionUtils.isNotEmpty(this.boService.findMatching(BudgetPersonnelDetails.class, qMap))) {
                // just try to make sure key is on budget personnel tab
                final MessageMap messageMap = GlobalVariables.getMessageMap();
                messageMap.putError(BUDGET_PERSONS_FIELD_NAME_START + "0" + BUDGET_PERSONS_FIELD_NAME_PERSON_NUMBER,
                    KeyConstants.ERROR_DELETE_PERSON_WITH_PERSONNEL_DETAIL, budgetPerson.getPersonName());
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
                    String personId = budgetPerson.getPersonId();
                    if(budgetPerson.getNonEmployeeFlag() && budgetPerson.getRolodexId() != null){
                        personId = budgetPerson.getRolodexId().toString();
                    }
                    if (budgetPersonnelDetail.getPersonId().equals(personId) && 
                            budgetPersonnelDetail.getPersonSequenceNumber().equals(budgetPerson.getPersonSequenceNumber())) {
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    public boolean processCheckBaseSalaryFormat(BudgetDocument budgetDocument) {
        boolean valid = true;
        
        MessageMap messageMap = GlobalVariables.getMessageMap();
        int i = 0;
        List<BudgetPerson> budgetPersons = budgetDocument.getBudget().getBudgetPersons();
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
        return this.budgetService.getApplicableCostElements(person.getBudgetId(), 
                person.getPersonSequenceNumber().toString());
    }
    
    public boolean processBudgetPersonnelBusinessRules(BudgetDocument budgetDocument) {
        boolean valid = true;
        
        MessageMap messageMap = GlobalVariables.getMessageMap();
        
        List<BudgetPerson> budgetPersons = budgetDocument.getBudget().getBudgetPersons();
        for (int i = 0; i < budgetPersons.size(); i++) {
            BudgetPerson budgetPerson = budgetPersons.get(i);
            for (int j = i + 1; j < budgetPersons.size(); j++) {
                BudgetPerson budgetPersonCompare = budgetPersons.get(j);
                if (budgetPerson.isDuplicatePerson(budgetPersonCompare)) {
                    BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
                    if (budgetParent instanceof DevelopmentProposal && ((DevelopmentProposal)budgetParent).isParent()) {
                        // not an error - ProposalHierarchy parents are allowed to have duplicate BudgetPersons
                    }
                    else {
                        messageMap.putError("budgetPersons[" + j + "].personName", KeyConstants.ERROR_DUPLICATE_BUDGET_PERSON, budgetPerson.getPersonName());
                        valid = false;
                    }
                }

            }
            
        }
        
        return valid;
    }

    /**
     * This method executes the job code change validation rules against the budget document
     * for a specific budget period.
     * @param budgetDocument the budget document
     * @param viewBudgetPeriod the budget period number.
     * @return true is valid false if not valid
     * @throws NullPointerException if the budgetDocument is null
     * @throws IllegalArgumentException if the viewBudgetPeriod < 1
     */
    public boolean processCheckForJobCodeChange(final BudgetDocument budgetDocument, final int viewBudgetPeriod) {
        if (budgetDocument == null) {
            throw new NullPointerException("the budgetDocument is null");
        }
        
        if (viewBudgetPeriod < 1) {
            throw new IllegalArgumentException("this viewBudgetPeriod: " + viewBudgetPeriod + " is invalid");
        }
        
        boolean valid = true;
        
        GlobalVariables.getMessageMap().addToErrorPath("document");
        valid &= this.processBudgetPersonnelBusinessRules(budgetDocument);
        
        if(valid) {
            final BudgetPeriod selectedBudgetPeriod = budgetDocument.getBudget().getBudgetPeriod(viewBudgetPeriod - 1);
            
            final Collection<Integer> budgetPersonSequences
                = this.getBudgetPersonSequencesFromPersonnelDetails(selectedBudgetPeriod.getBudgetLineItems());
            
            if (CollectionUtils.isNotEmpty(budgetPersonSequences)) {
                int i = 0;
                List<BudgetPerson> budgetPersons = budgetDocument.getBudget().getBudgetPersons();
                for (BudgetPerson person : budgetPersons) {
                    if (budgetPersonSequences.contains(person.getPersonSequenceNumber())) {
                        if(CollectionUtils.isNotEmpty(this.getMappedCostElements(person))) {
                            valid &= this.validateJobCodeChange(i, person);
                        } else {
                            valid &= this.validateJobCodeValue(i, person);
                            this.updateJobCodeOnDetailsFromPerson(selectedBudgetPeriod.getBudgetLineItems(), person);
                        }
                    }
                    i++;
                }
            }
        }
        
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        return valid;
    }
    
    /**
     * Validates if the job code is a valid change.
     * @param personNumber the current person number
     * @param person the current person
     * @return true is valid false if not valid
     */
    private boolean validateJobCodeChange(final int personNumber, final BudgetPerson person) {
        assert person != null : "the person is null";
        assert personNumber >= 0 : "the personNumber: " + personNumber + " is invalid";
        
        boolean valid = true;
        
        final Map<String, Object> queryMap = new HashMap<String, Object>();
//        queryMap.put("proposalNumber", person.getProposalNumber());
//        queryMap.put("budgetVersionNumber", person.getBudgetVersionNumber());
        queryMap.put("budgetId", person.getBudgetId());
        queryMap.put("personSequenceNumber", person.getPersonSequenceNumber());
        final BudgetPerson personCopy = (BudgetPerson) this.boService.findByPrimaryKey(BudgetPerson.class, queryMap);
        if (!person.isDuplicatePerson(personCopy)) {
            if (!StringUtils.equals(person.getJobCode(), personCopy.getJobCode())) {
                final MessageMap messageMap = GlobalVariables.getMessageMap();
                messageMap.putError(BUDGET_PERSONS_FIELD_NAME_START + personNumber + BUDGET_PERSONS_FIELD_NAME_JOBCODE,
                    KeyConstants.ERROR_PERSON_JOBCODE_CHANGE, person.getPersonName());
                valid = false;
            }
        }
        return valid;
    }
    
    /**
     * Validates if the job code is a valid value.
     * @param personNumber the current person number
     * @param person the current person
     * @return true is valid false if not valid
     */
    private boolean validateJobCodeValue(final int personNumber, final BudgetPerson person) {
        assert person != null : "the person is null";
        assert personNumber >= 0 : "the personNumber: " + personNumber + " is invalid";
        
        boolean valid = true;
        if (person.getJobCode() == null) {
            final MessageMap messageMap = GlobalVariables.getMessageMap();
            messageMap.putError(BUDGET_PERSONS_FIELD_NAME_START + personNumber + BUDGET_PERSONS_FIELD_NAME_JOBCODE,
                KeyConstants.ERROR_PERSON_JOBCODE_VALUE, person.getPersonName());
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
    private List<ValidCeJobCode> getApplicableCostElements(BudgetDocument budgetDocument, BudgetPersonnelDetails newBudgetPersonnelDetails, boolean save) {
        List<ValidCeJobCode> validCostElements = null;
    
        if (save) {
            String jobCodeValidationEnabledInd = this.paramService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_JOBCODE_VALIDATION_ENABLED);
            
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            BudgetPerson budgetPerson = null;
            
            if(StringUtils.isNotEmpty(jobCodeValidationEnabledInd) && jobCodeValidationEnabledInd.equals("Y")) { 

                List<BudgetPerson> budgetPersons = budgetDocument.getBudget().getBudgetPersons();
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
            validCostElements = this.budgetService.getApplicableCostElements(budgetDocument.getBudget().getBudgetId(), 
                        newBudgetPersonnelDetails.getPersonSequenceNumber().toString());
        }
         
        return validCostElements;
    }
    
    public boolean processCheckJobCodeObjectCodeCombo(BudgetDocument budgetDocument, BudgetPersonnelDetails newBudgetPersonnelDetails, boolean save) {
        List<ValidCeJobCode> validCostElements = null;
        boolean isValid = false;
        
        validCostElements = getApplicableCostElements(budgetDocument, newBudgetPersonnelDetails, save);
        
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
}
