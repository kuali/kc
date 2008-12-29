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
package org.kuali.kra.budget.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.RiceKeyConstants;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.ValidCeJobCode;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.KNSServiceLocator;

public class BudgetPersonnelRule {

    public BudgetPersonnelRule() {
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
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
            if (StringUtils.isBlank(budgetPerson.getJobCode()) || StringUtils.isBlank(budgetPerson.getAppointmentTypeCode()) || budgetPerson.getCalculationBase().isLessThan(BudgetDecimal.ZERO) || budgetPerson.getEffectiveDate() == null) {
                errorMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_IMCOMPLETE_PERSON_ENTRIES);
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
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        Map qMap = new HashMap();
        qMap.put("proposalNumber", budgetDocument.getProposalNumber());
        qMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
        qMap.put("personId", budgetPerson.getPersonId());
        qMap.put("personSequenceNumber", budgetPerson.getPersonSequenceNumber());
        Collection budgetPersonnelDetails = KraServiceLocator.getService(BusinessObjectService.class).findMatching(BudgetPersonnelDetails.class, qMap);
        if (CollectionUtils.isNotEmpty(budgetPersonnelDetails)) {
                // just try to make sure key is on budget personnel tab
                errorMap.putError("document.budgetPersons[0].personNumber", KeyConstants.ERROR_DELETE_PERSON_WITH_PERSONNEL_DETAIL, budgetPerson.getPersonName());
                    valid = false;
        }
                    
        return valid;
    }

//    public boolean processCheckBaseSalaryFormat(BudgetDocument budgetDocument) {
//        boolean valid = true;
//        
//        ErrorMap errorMap = GlobalVariables.getErrorMap();
//        int i = 0;
//        List <BudgetPerson> budgetPersons = (List <BudgetPerson>)ObjectUtils.deepCopy((Serializable)budgetDocument.getBudgetPersons());
//        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
//            if (budgetPerson.getCalculationBase() == null) {
//                errorMap.putError("document.budgetPerson["+i+"].calculationBase", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Base Salary"});
//                    valid = false;
//            } else if (budgetPerson.getCalculationBase().isNegative()) {
//                errorMap.putError("document.budgetPerson["+i+"].calculationBase", KeyConstants.ERROR_NEGATIVE_AMOUNT, new String[] { "Base Salary"});
//                valid = false;
//            }
//            for (BudgetPerson dupBudgetPerson : budgetPersons) {
//                if (dupBudgetPerson.getPersonId() != null && dupBudgetPerson.getPersonId().equals(budgetPerson.getPersonId()) && dupBudgetPerson.getPersonSequenceNumber().intValue() < budgetPerson.getPersonSequenceNumber().intValue()
//                        && dupBudgetPerson.getJobCode().equals(budgetPerson.getJobCode()) && dupBudgetPerson.getEffectiveDate().compareTo(budgetPerson.getEffectiveDate()) == 0) 
//                {
//                    errorMap.putError("document.budgetPerson["+i+"].dupkey", KeyConstants.ERROR_DUPLICATE_PERSON, new String[] { ""});
//                    valid = false;
//                }
//                if (dupBudgetPerson.getRolodexId() != null && dupBudgetPerson.getRolodexId().equals(budgetPerson.getRolodexId()) && dupBudgetPerson.getPersonSequenceNumber().intValue() < budgetPerson.getPersonSequenceNumber().intValue()
//                        && dupBudgetPerson.getJobCode().equals(budgetPerson.getJobCode()) && dupBudgetPerson.getEffectiveDate().compareTo(budgetPerson.getEffectiveDate()) == 0) 
//                {
//                    errorMap.putError("document.budgetPerson["+i+"].dupkey", KeyConstants.ERROR_DUPLICATE_PERSON, new String[] { ""});
//                    valid = false;
//                }
//            }
//            i++;
//        }
//                    
//        return valid;
//    }

    public boolean processCheckBaseSalaryFormat(BudgetDocument budgetDocument) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i = 0;
        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
            if (budgetPerson.getCalculationBase() == null) {
                errorMap.putError("document.budgetPerson["+i+"].calculationBase", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Base Salary"});
                    valid = false;
            } else if (budgetPerson.getCalculationBase().isNegative()) {
                errorMap.putError("document.budgetPerson["+i+"].calculationBase", KeyConstants.ERROR_NEGATIVE_AMOUNT, new String[] { "Base Salary"});
                valid = false;
            }
            i++;
        }
                    
        return valid;
    }

    private List<ValidCeJobCode> getMappedCostElements(BudgetPerson person) {
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        List<ValidCeJobCode> validCostElements = null;
        try {
            validCostElements = budgetService.getApplicableCostElements(person.getProposalNumber(), person.getBudgetVersionNumber().toString(), person.getPersonSequenceNumber().toString());
        }
        catch (Exception e) {
            throw new RuntimeException("Exception occurred while obtaining mapped Cost Elements for Job code");
        }
        return validCostElements;
    }
    
    public boolean processCheckForJobCodeChange(BudgetDocument budgetDocument, int viewBudgetPeriod) {
        boolean valid = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        List<Integer> budgetPersonSequences = new ArrayList<Integer>();
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        Map<Integer, BudgetPerson> budgetPersonsMap = new HashMap<Integer, BudgetPerson>();
        
        int selectedBudgetPeriodIndex = viewBudgetPeriod-1;
        BudgetPeriod selectedBudgetPeriod = budgetDocument.getBudgetPeriod(selectedBudgetPeriodIndex);
        
        for(BudgetLineItem budgetLineItem : selectedBudgetPeriod.getBudgetLineItems()) {
            for(BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                budgetPersonSequences.add(budgetPersonnelDetails.getPersonSequenceNumber());
            }
        }
        
        Map queryMap = new HashMap();
        queryMap.put("proposalNumber", budgetDocument.getProposalNumber());
        queryMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
        List<ValidCeJobCode> validCostElements = null;
        
        if(budgetPersonSequences.size() > 0) {
             int i = 0;
             for(BudgetPerson person : budgetDocument.getBudgetPersons()) {
                 if(budgetPersonSequences.contains(person.getPersonSequenceNumber())) {
                     validCostElements = getMappedCostElements(person);
                     if(CollectionUtils.isNotEmpty(validCostElements)) {
                         queryMap.put("personSequenceNumber", person.getPersonSequenceNumber());
                         BudgetPerson personCopy = (BudgetPerson) boService.findByPrimaryKey(BudgetPerson.class, queryMap);
                         if(!person.isDuplicatePerson(personCopy)) {
                             if (!StringUtils.equals(person.getJobCode(), personCopy.getJobCode())) {
                                 errorMap.putError("document.budgetPerson["+i+"].jobCode", KeyConstants.ERROR_PERSON_JOBCODE_CHANGE, person.getPersonName());
                                 valid = false;
                             }
                         }
                         queryMap.remove("personSequenceNumber");
                     } else {
                         budgetPersonsMap.put(person.getPersonSequenceNumber(), person);
                     }
                     validCostElements = null;
                 }
                 i++;
             }
             
             //Update BudgetPerson References
             for(BudgetLineItem budgetLineItem : selectedBudgetPeriod.getBudgetLineItems()) {
                 for(BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                     BudgetPerson documentBudgetPersonCopy = budgetPersonsMap.get(budgetPersonnelDetails.getPersonSequenceNumber());
                     if(documentBudgetPersonCopy != null) {
                         budgetPersonnelDetails.setJobCode(documentBudgetPersonCopy.getJobCode());
                     }
                 }
             }
        }
        
        return valid;
    }
    
    private List<ValidCeJobCode> getApplicableCostElements(BudgetDocument budgetDocument, BudgetPersonnelDetails newBudgetPersonnelDetails, boolean save) {
        List<ValidCeJobCode> validCostElements = null;
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
    
        try {
            if(save) {
                String jobCodeValidationEnabledInd = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                        Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT,
                        Constants.BUDGET_JOBCODE_VALIDATION_ENABLED).getParameterValue();
                
                Map fieldValues = new HashMap();
                BudgetPerson budgetPerson = null;
                
                if(StringUtils.isNotEmpty(jobCodeValidationEnabledInd) && jobCodeValidationEnabledInd.equals("Y")) { 
                    List<BudgetPerson> budgetPersons = budgetDocument.getBudgetPersons();
                    for(BudgetPerson tmpBudgetPerson : budgetDocument.getBudgetPersons()) {
                        if(tmpBudgetPerson.getPersonSequenceNumber().intValue() == newBudgetPersonnelDetails.getPersonSequenceNumber().intValue()) {
                            budgetPerson = tmpBudgetPerson;
                            break;
                        }
                    }
                    if(budgetPerson != null && StringUtils.isNotEmpty(budgetPerson.getJobCode())) {
                        fieldValues.put("jobCode", budgetPerson.getJobCode().toUpperCase());
                        validCostElements = (List<ValidCeJobCode>) boService.findMatching(ValidCeJobCode.class, fieldValues);
                    }
                }
                
            } else {
                validCostElements = budgetService.getApplicableCostElements(budgetDocument.getProposalNumber(), budgetDocument.getBudgetVersionNumber().toString(), newBudgetPersonnelDetails.getPersonSequenceNumber().toString());
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Exception Occurred while calling BudgetService getApplicableCostElements");
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
