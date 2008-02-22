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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetRatesService;

public class BudgetRatesServiceImpl implements BudgetRatesService{
    private BusinessObjectService businessObjectService;

    /* get budget rate class types
     */
    public void getBudgetRateClassTypes(List<RateClassType> rateClassTypes) {
        Collection<RateClassType> allRateClassTypes = new ArrayList();
        allRateClassTypes = businessObjectService.findAll(RateClassType.class);
        for(RateClassType rateClassType: allRateClassTypes) {
            System.out.println(rateClassType.getDescription());
        }
        rateClassTypes.addAll(allRateClassTypes);
    }
    
    /* get budget rates applicable for the proposal - based on activity type
     * and unit number 
     * */
    public void getBudgetRates(String activityTypeCode, String unitNumber, List<RateClass> rateClasses, 
            List<InstituteRate> instituteRates, List<BudgetProposalRate> budgetProposalRates) {
        //String unitNumber = budgetDocument.getProposal().getOwnedByUnitNumber();
        //String activityTypeCode = budgetDocument.getProposal().getActivityTypeCode();
        System.out.println("---> getBudgetRates <---");
        Collection<InstituteRate> allInstituteRates = new ArrayList();
        Map rateFilterMap = new HashMap();
        rateFilterMap.put("unitNumber", unitNumber);
        rateFilterMap.put("activityTypeCode", activityTypeCode);
        allInstituteRates = businessObjectService.findMatching(InstituteRate.class, rateFilterMap);
        Map rateClassMap = new HashMap();
        for(InstituteRate instituteRate: allInstituteRates) {
            String rateClassCode = instituteRate.getRateClassCode();
            if(rateClassMap.get(rateClassCode) == null) {
                RateClass rateClass = instituteRate.getRateClass();
                rateClassMap.put(rateClassCode, instituteRate.getRateClass());
            }
            /* initialize budget proposal rates */
            System.out.println("---> " + instituteRate.getRateClass().getDescription());
            BudgetProposalRate budgetProposalRate = new BudgetProposalRate();
            budgetProposalRate.setActivityTypeCode(activityTypeCode);
            budgetProposalRate.setApplicableRate(instituteRate.getInstituteRate());
            budgetProposalRate.setFiscalYear(instituteRate.getFiscalYear());
            budgetProposalRate.setInstituteRate(instituteRate.getInstituteRate());
            budgetProposalRate.setOnOffCampusFlag(instituteRate.getOnOffCampusFlag());
            budgetProposalRate.setRateClass(instituteRate.getRateClass());
            budgetProposalRate.setRateClassCode(rateClassCode);
            budgetProposalRate.setRateType(instituteRate.getRateType());
            budgetProposalRate.setRateTypeCode(instituteRate.getRateTypeCode());
            budgetProposalRate.setStartDate(instituteRate.getStartDate());
            budgetProposalRate.setUnitNumber(unitNumber);
            budgetProposalRates.add(budgetProposalRate);
            //budgetDocument.getBudgetProposalRates().add(budgetProposalRate);
        }
        //List<InstituteRate> instRates = new ArrayList();
        //instRates.addAll(allInstituteRates);
        instituteRates.addAll(allInstituteRates);
        rateClasses.addAll(rateClassMap.values());
        //budgetDocument.setInstituteRates(instRates);
        //Collection<RateClass> rateClasses = new ArrayList();
        //List<RateClass> rates = new ArrayList();
        //rates.addAll(rateClassMap.values());
        //budgetDocument.setRateClasses(rates);
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
