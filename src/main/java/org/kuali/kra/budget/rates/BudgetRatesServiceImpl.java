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
package org.kuali.kra.budget.rates;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

public class BudgetRatesServiceImpl<T extends BudgetParent> implements BudgetRatesService<T> {
    private static final String SPACE = " ";
    public static final String UNIT_NUMBER_KEY = "unitNumber";
    public static final String ACTIVITY_TYPE_CODE_KEY = "activityTypeCode";
    public static final String BUDGET_ID_KEY = "budgetId";
    
    private BusinessObjectService _businessObjectService;
    private static final String PERIOD_SEARCH_SEPARATOR = "|";
    private static final String PERIOD_DISPLAY_SEPARATOR = ",";
    private static final Log LOG = LogFactory.getLog(BudgetRatesServiceImpl.class);
    private static final String BUDGET_RATE_AUDIT_WARNING_KEY = "budgetRateAuditWarnings";

    /**
     * @see org.kuali.kra.budget.rates.BudgetRatesService#resetAllBudgetRates(org.kuali.kra.budget.core.Budget)
     */
    public void resetAllBudgetRates(Budget budget) {
        resetAbstractBudgetApplicableRatesToInstituteRates(budget.getBudgetRates());
        resetAbstractBudgetApplicableRatesToInstituteRates(budget.getBudgetLaRates());
    }
    
    /**
     * reset budget rates for a panel
     * each panel is based on rate class type 
     *
     * @see org.kuali.kra.budget.rates.BudgetRatesService#resetBudgetRatesForRateClassType(java.lang.String, org.kuali.kra.budget.core.Budget)
     */
    public void resetBudgetRatesForRateClassType(String rateClassType, Budget budget) {
        List<RateClass> rateClasses = budget.getRateClasses();
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budget.getBudgetRates());
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budget.getBudgetLaRates());        
    }
    
    /**
     * @see org.kuali.kra.budget.rates.BudgetRatesService#syncAllBudgetRates(org.kuali.kra.budget.core.Budget)
     */
    public void syncAllBudgetRates(BudgetDocument<T> budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        List<InstituteRate> allInstituteRates = new ArrayList<InstituteRate>(getInstituteRates(budgetDocument));
        List<InstituteLaRate> allInstituteLaRates = new ArrayList<InstituteLaRate>(getInstituteLaRates(budgetDocument));
        
        if(isOutOfSync(budget)) {
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budget.getBudgetRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budget.getBudgetLaRates());
            
            budget.getBudgetRates().clear();
            budget.getBudgetLaRates().clear();
            budget.getRateClasses().clear();
            
            // since different rate schedules can change UnrecoveredFandA, clear here
            budget.getBudgetUnrecoveredFandAs().clear();
            
            getBudgetRates(budgetDocument, allInstituteRates);
            getBudgetLaRates(budgetDocument, allInstituteLaRates);
            
            syncVersionNumber(mapOfExistingBudgetProposalRates, budget.getBudgetRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budget.getBudgetLaRates());
        } else {
            syncBudgetRates(budget.getBudgetRates(), allInstituteRates);
            syncBudgetRates(budget.getBudgetLaRates(), allInstituteLaRates);
        }
    }

    @SuppressWarnings("unchecked")
    protected void syncVersionNumber(Map<String, AbstractInstituteRate> oldRateMap, List rates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) rates;
        for(AbstractBudgetRate budgetRate: abstractBudgetRates) {
            AbstractInstituteRate oldRate = oldRateMap.get(budgetRate.getRateKeyAsString());
            if(oldRate != null) {
                budgetRate.setVersionNumber(oldRate.getVersionNumber());
            }
        }
    }
    
    /* update view - location
     *  
     * */
    public void viewLocation(String viewLocation, Integer budgetPeriod, Budget budget) {
        viewLocation(viewLocation, budgetPeriod, budget.getBudgetRates());
        viewLocation(viewLocation, budgetPeriod, budget.getBudgetLaRates());        
    }
    
    /**
     * 
     * Does nothing. Placeholder for Award Budget
     * @param budgetDocument
     */
    public void syncParentDocumentRates(BudgetDocument<T> budgetDocument) {
    }
    
    /* sync budget rates for a panel
     * each panel is based on rate class type 
     * */
    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument<T> budgetDocument) {
        Budget budget = budgetDocument.getBudget();
            populateInstituteRates(budgetDocument);
            
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budget.getBudgetRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budget.getBudgetLaRates());
            replaceRateClassesForRateClassType(rateClassType, budget, budget.getInstituteRates());
            replaceRateClassesForRateClassType(rateClassType, budget, budget.getInstituteLaRates());
            replaceBudgetRatesForRateClassType(rateClassType, budgetDocument, budget.getBudgetRates(), budget.getInstituteRates());
            replaceBudgetRatesForRateClassType(rateClassType, budgetDocument, budget.getBudgetLaRates(), budget.getInstituteLaRates());
            syncVersionNumber(mapOfExistingBudgetProposalRates, budget.getBudgetRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budget.getBudgetLaRates());
    }    
    
    /**
     * 
     * @see org.kuali.kra.budget.rates.BudgetRatesService#getBudgetRates(java.util.List, org.kuali.kra.budget.core.Budget)
     */
    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument) {
        getBudgetRates(rateClassTypes, budgetDocument, getInstituteRates(budgetDocument));
    }

    /* verify and add activity type prefix if required for rate class type description
     * 
     * */
    protected void checkActivityPrefixForRateClassTypes(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument) {
        //String activityTypeDescription = budget.getProposal().getActivityType().getDescription().concat(SPACE);
        Budget budget = budgetDocument.getBudget();
        String activityTypeDescription = getActivityTypeDescription(budgetDocument);
        List<BudgetRate> budgetRates = budget.getBudgetRates();
        List<BudgetLaRate> budgetLaRates = budget.getBudgetLaRates();
        for(RateClassType rateClassType : rateClassTypes) {
            if(rateClassType.getPrefixActivityType()) {
                String newRateClassTypeDescription = activityTypeDescription.concat(rateClassType.getDescription()); 
                rateClassType.setDescription(newRateClassTypeDescription);
                rateClassType.setPrefixActivityType(false);
                /* set in proposal rates reference */
                for(BudgetRate budgetRate: budgetRates) {
                    RateClassType BPRateClassType = budgetRate.getRateClass().getRateClassTypeT();
                    if(rateClassType.getRateClassType().equalsIgnoreCase(BPRateClassType.getRateClassType())) {
                        BPRateClassType.setDescription(newRateClassTypeDescription);
                    }
                }
                /* set in proposal LA rates reference */
                for(BudgetLaRate budgetLaRate: budgetLaRates) {
                    RateClassType BPLRateClassType = budgetLaRate.getRateClass().getRateClassTypeT();
                    if(rateClassType.getRateClassType().equalsIgnoreCase(BPLRateClassType.getRateClassType())) {
                        BPLRateClassType.setDescription(newRateClassTypeDescription);
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    protected String getActivityTypeDescription(BudgetDocument<T> budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        
        if (budget.isRateSynced() || !KraServiceLocator.getService(BudgetService.class).
                checkActivityTypeChange(getBudgetParentDocument(budget), budget)) {
            if(budgetParent.getActivityType()!= null){
                return budgetParent.getActivityType().getDescription().concat(SPACE);
            }
            else
            {
                return "";
            }
        } else {
            String activityTypeCode=null;
            if (CollectionUtils.isNotEmpty(budget.getBudgetRates())) {
                activityTypeCode = ((BudgetRate)budget.getBudgetRates().get(0)).getActivityTypeCode();
            }

            if (activityTypeCode != null) {
                Map pkMap = new HashMap();
                pkMap.put("activityTypeCode",activityTypeCode);
                ActivityType activityType = (ActivityType)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(ActivityType.class, pkMap);
                if (activityType == null) {
                    return "";
                } else {
                    return activityType.getDescription().concat(SPACE);
                }
            } else {
                return "";
            }
        }
    }
    
    /**
     * @see org.kuali.kra.budget.rates.BudgetRatesService#getBudgetPeriods()
     */
    @SuppressWarnings("unchecked")
    public List<BudgetPeriod> getBudgetPeriods(){
        BudgetForm budgetForm = (BudgetForm) KNSGlobalVariables.getKualiForm();
        BudgetDocument<T> budgetDocument  = budgetForm.getBudgetDocument();
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        return budgetPeriods;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this._businessObjectService = businessObjectService;
    }
 
    /**
     * Build rates for each period.
     * @return .
     */
    protected void updateRatesForEachPeriod(Budget budget) {
        List<BudgetRate> budgetRates = budget.getBudgetRates();
        List<BudgetLaRate> budgetLaRates = budget.getBudgetLaRates();
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();

        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            for(BudgetRate budgetRate: budgetRates) {
                if(budgetRate.getStartDate().compareTo(budgetPeriod.getEndDate()) <= 0) {
                    String dispBudgetPeriod = budgetPeriod.getBudgetPeriod().toString();
                    String formattedPeriod = dispBudgetPeriod.concat(PERIOD_SEARCH_SEPARATOR);
                    String currBudgetPeriod = budgetRate.getTrackAffectedPeriod(); //(String)ratesForEachPeriod.get(budgetPeriod.getBudgetPeriod());
                    if(currBudgetPeriod == null) {
                        currBudgetPeriod = PERIOD_SEARCH_SEPARATOR.concat(formattedPeriod);
                        budgetRate.setTrackAffectedPeriod(currBudgetPeriod);
                    }else {
                        if(currBudgetPeriod.indexOf(formattedPeriod) < 0) {
                            currBudgetPeriod = currBudgetPeriod.concat(formattedPeriod); 
                            budgetRate.setTrackAffectedPeriod(currBudgetPeriod);
                        }
                    }
                    budgetRate.setAffectedBudgetPeriod(getFormattedAffectedBudgetPeriod(currBudgetPeriod));
                }
            }
            for(BudgetLaRate budgetLaRate: budgetLaRates) {
                if(budgetLaRate.getStartDate().compareTo(budgetPeriod.getEndDate()) <= 0) {
                    String dispBudgetPeriod = budgetPeriod.getBudgetPeriod().toString();
                    String formattedPeriod = dispBudgetPeriod.concat(PERIOD_SEARCH_SEPARATOR);
                    String currBudgetPeriod = budgetLaRate.getTrackAffectedPeriod(); //(String)ratesForEachPeriod.get(budgetPeriod.getBudgetPeriod());
                    if(currBudgetPeriod == null) {
                        currBudgetPeriod = PERIOD_SEARCH_SEPARATOR.concat(formattedPeriod);
                        budgetLaRate.setTrackAffectedPeriod(currBudgetPeriod);
                    }else {
                        if(currBudgetPeriod.indexOf(formattedPeriod) < 0) {
                            currBudgetPeriod = currBudgetPeriod.concat(formattedPeriod); 
                            budgetLaRate.setTrackAffectedPeriod(currBudgetPeriod);
                        }
                    }
                    budgetLaRate.setAffectedBudgetPeriod(getFormattedAffectedBudgetPeriod(currBudgetPeriod));
                }
            }
        }
    }
    
    /**
     * This method load institute rates to hashmap
     * @param rates
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Map<String, AbstractInstituteRate> mapRatesToKeys(Collection rates) {
        Collection<AbstractInstituteRate> abstractInstituteRates = (Collection<AbstractInstituteRate>) rates;  
        Map<String, AbstractInstituteRate> rateMap = new HashMap<String, AbstractInstituteRate>();
        for(AbstractInstituteRate abstractInstituteRate: abstractInstituteRates) {
            rateMap.put(abstractInstituteRate.getRateKeyAsString(), abstractInstituteRate);
        }
        return rateMap;
    }
    
    /* get all institute rates - based on activity type
     * and unit number 
     * */
    @SuppressWarnings("unchecked")
    protected Collection<InstituteRate> getInstituteRates(BudgetDocument<T> budgetDocument) {
        //get first unit number in hierarchy with rates then select appropriate rates
        Unit firstUnit = findFirstUnitWithRates(budgetDocument.getParentDocument().getBudgetParent().getUnit(), InstituteRate.class);
        if (firstUnit == null) {
            return new ArrayList();
        }
        Collection abstractRates = getActiveInstituteRates(InstituteRate.class, firstUnit, budgetDocument.getParentDocument().getBudgetParent().getActivityTypeCode());
        return (Collection<InstituteRate>)abstractRates;
    }
    
    @SuppressWarnings("unchecked")
    protected Unit findFirstUnitWithRates(Unit leadUnit, Class rateType) {
        Unit currentUnit = leadUnit;
        Map<String, String> currentSearchMap = new HashMap<String, String>();
        Collection currentRates = null;
        while (currentUnit != null) {
            currentSearchMap.put(UNIT_NUMBER_KEY, currentUnit.getUnitNumber());            
            currentRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, currentSearchMap));
            if (currentRates != null && !currentRates.isEmpty()) {
                break;
            }
            currentUnit = currentUnit.getParentUnit();
        }
        return currentUnit;
    }
    
    @SuppressWarnings("unchecked")
    protected Collection<AbstractInstituteRate> getActiveInstituteRates(Class rateType, Unit unit, String activityTypeCode) {
        Map<String, String> searchMap = new HashMap<String, String>();
        searchMap.put(UNIT_NUMBER_KEY, unit.getUnitNumber());
        searchMap.put(ACTIVITY_TYPE_CODE_KEY, activityTypeCode);
        return filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, searchMap));
    }

    /* get all institute rates - based on 
     * and unit number 
     * */
    @SuppressWarnings("unchecked")
    protected Collection<InstituteLaRate> getInstituteLaRates(BudgetDocument<T> budgetDocument) {
//        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent(); 
        String unitNumber = budgetParent.getUnitNumber();                               
        Collection abstractInstituteRates = getFilteredInstituteLaRates(InstituteLaRate.class, unitNumber, budgetParent.getUnit(), getRateFilterMap(budgetDocument));
        abstractInstituteRates = abstractInstituteRates.size() > 0 ? abstractInstituteRates : new ArrayList();
        return (Collection<InstituteLaRate>)abstractInstituteRates ;
    }

    /**
     * This method...
     * @param budget
     * @return
     */
    @SuppressWarnings("unchecked")
    protected BudgetParentDocument<T> getBudgetParentDocument(Budget budget) {
        BudgetDocument<T> budgetDocument = budget.getBudgetDocument();
        if(budgetDocument==null){
            budget.refreshReferenceObject("budgetDocument");
            budgetDocument = budget.getBudgetDocument();
        }
//        ProposalDevelopmentDocument proposal = (ProposalDevelopmentDocument)budgetDocument.getParentDocument();
        return budgetDocument.getParentDocument();
    }

    protected Map<String, String> getRateFilterMap(BudgetDocument<T> budgetDocument) {        
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent(); 
        Map<String, String> rateFilterMap = new HashMap<String, String>();
        rateFilterMap.put(UNIT_NUMBER_KEY, budgetParent.getUnitNumber());
        return rateFilterMap;
    }

    @SuppressWarnings("unchecked")
    protected Collection getFilteredInstituteLaRates(Class rateType, String unitNumber, Unit currentUnit, Map<String, String> rateFilterMap) {
        Collection abstractInstituteRates;
        abstractInstituteRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, rateFilterMap));
        return abstractInstituteRates;
    }

    @SuppressWarnings("unchecked")
    protected Collection getFilteredInstituteRates(Class rateType, String unitNumber, Unit currentUnit, Map<String, String> rateFilterMap) {
        Collection abstractInstituteRates;
        do {
            abstractInstituteRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, rateFilterMap));
            currentUnit = makeParentUnitAsCurrentUnit(currentUnit, rateFilterMap);
        } while(abstractInstituteRates.size() == 0 && currentUnit != null);
        
        return abstractInstituteRates;
    }

    @SuppressWarnings("unchecked")
    protected Collection filterForActiveRatesOnly(Collection abstractInstituteRates) {        
        List filteredList = new ArrayList();
        for(AbstractInstituteRate rate: (Collection<AbstractInstituteRate>) abstractInstituteRates) {
            if(rate.isActive()) {
                filteredList.add(rate);
            } else {
                if(LOG.isDebugEnabled()) {
                    LOG.debug("Filtering inactive rate: " + rate.getObjectId());
                }
            }
        }
        return filteredList;
    }

    protected Unit makeParentUnitAsCurrentUnit(Unit currentUnit, Map<String, String> rateFilterMap) {
        Unit parentUnit = currentUnit==null?null:currentUnit.getParentUnit();
        if(parentUnit != null) {
            rateFilterMap.put(UNIT_NUMBER_KEY, parentUnit.getUnitNumber());
        }
        return parentUnit;
    }


    /* Rate effective date is between project start and end dates.
     * But if budget persons are defined and the earliest salary effective
     * date is prior to project start date, Inflation rates are retrieved from
     * that date on (salary effective date).  
     * This date is used to fetch inflation rates  
     * 
     * */
    protected Date getRateEffectiveStartDate(Budget budget, AbstractInstituteRate rate, Date personEffectiveDate) {
        Date effectiveDate = budget.getStartDate();
        if(rate.getRateClass().getRateClassType().equalsIgnoreCase(Constants.RATE_CLASS_TYPE_FOR_INFLATION) 
                && personEffectiveDate != null 
                && personEffectiveDate.compareTo(effectiveDate) < 0) {
            effectiveDate = personEffectiveDate;
        }
        return effectiveDate;
    }



    /* Look for budget persons salary effective date and return the 
     * earliest effective date 
     * This date is used to fetch/calculate inflation rates  
     * 
     * */
    @SuppressWarnings("unchecked")
    protected Date getBudgetPersonSalaryEffectiveDate(Budget budget) {
        Map queryMap = new HashMap();
        queryMap.put("budgetId", budget.getBudgetId());
        Collection<BudgetPerson> budgetPersons =  getBusinessObjectService().findMatching(BudgetPerson.class, queryMap);
        Date effectiveDate = null;
        for(BudgetPerson budgetPerson : budgetPersons) {
            if(effectiveDate == null || budgetPerson.getEffectiveDate().compareTo(effectiveDate) < 0) {
                effectiveDate = budgetPerson.getEffectiveDate();
            }
        }
        return effectiveDate;
    }
    
    
    /* get all rates within project start and end date range 
     *  
     * */
    @SuppressWarnings("unchecked")
    protected void getRatesForProjectDates(Budget budget, Collection allRates, Collection filteredRates, Date personSalaryEffectiveDate) {
        List<AbstractInstituteRate> dateFilteredRates = (List<AbstractInstituteRate>) filteredRates;
        List<AbstractInstituteRate> allAbstractInstituteRates = (List<AbstractInstituteRate>) allRates;
        for(AbstractInstituteRate rate : allAbstractInstituteRates) {
            Date rateStartDate = rate.getStartDate();
            Date rateEffectiveDate = getRateEffectiveStartDate(budget, rate, personSalaryEffectiveDate);
            
            if(rateStartDate.compareTo(rateEffectiveDate) >= 0 && rateStartDate.compareTo(budget.getEndDate()) <=0 ) {
                dateFilteredRates.add(rate);
            }
        }
    }
    
    /* get applicable rates before project start date  
     * get the latest 
     * */
    @SuppressWarnings("unchecked")
    protected void getApplicableRates(Budget budget, Collection allRates, Collection filteredRates, Date personSalaryEffectiveDate) {
        List<AbstractInstituteRate> allAbstractInstituteRates = (List<AbstractInstituteRate>) allRates;
        Map<String, AbstractInstituteRate> instRates = new HashMap<String, AbstractInstituteRate>();
        for(AbstractInstituteRate instituteRate : allAbstractInstituteRates) {
            Date rateStartDate = instituteRate.getStartDate();
            Date rateEffectiveDate = getRateEffectiveStartDate(budget, instituteRate, personSalaryEffectiveDate);
            if(rateStartDate.before(rateEffectiveDate)) {
                String hKey = generateThreePartKey(instituteRate);
                AbstractInstituteRate instRate = instRates.get(hKey);
                if((instRate != null) && (instRate.getStartDate().compareTo(rateStartDate) <=0 )) {
                    Date currentStartDate = instRate.getStartDate();
                    if(currentStartDate.compareTo(rateStartDate) <= 0) {
                        instRates.remove(hKey);
                    }
                }
                if (!instRates.keySet().contains(hKey)) {
                    instRates.put(hKey, instituteRate);
                }
            }
            
        }
        filteredRates.addAll(instRates.values());
    }

    protected String generateThreePartKey(AbstractInstituteRate instituteRate) {
        return new StringBuilder(instituteRate.getRateClassCode())
                .append(instituteRate.getRateTypeCode())
                .append(getLocationFlagAsString(instituteRate.getOnOffCampusFlag()))
                .toString();
    }
    
    /* filter institute rates - get rates applicable for 
     * the project 
     * */
    @SuppressWarnings("unchecked")
    protected void filterRates(Budget budget, Collection allAbstractInstituteRates, Collection filteredAbstractInstituteRates) {
        filteredAbstractInstituteRates.clear();
        Date personSalaryEffectiveDate = getBudgetPersonSalaryEffectiveDate(budget);
        getRatesForProjectDates(budget, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
        getApplicableRates(budget, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
    }
    
    protected boolean isOutOfSync(Budget budget) {
        return isOutOfSync(budget.getInstituteRates(), budget.getBudgetRates()) || 
                isOutOfSync(budget.getInstituteLaRates(), budget.getBudgetLaRates());
    }
    
    @SuppressWarnings("unchecked")
    protected boolean isOutOfSync(List instituteRates, List budgetRates) {
        boolean outOfSync = areNumbersOfBudgetRatesOutOfSyncWithInstituteRates(instituteRates, budgetRates);
        if(!outOfSync) {
            outOfSync = areBudgetRatesOutOfSyncWithInsttituteRates(instituteRates, budgetRates);
        }
        
        return outOfSync;
    }

    @SuppressWarnings("unchecked")
    protected boolean areNumbersOfBudgetRatesOutOfSyncWithInstituteRates(List instituteRates, List budgetRates) {
        return instituteRates.size() != budgetRates.size();
    }

    @SuppressWarnings("unchecked")
    protected boolean areBudgetRatesOutOfSyncWithInsttituteRates(List instituteRates, List budgetRates) {        
        Set<String> instituteRateKeys = storeAllKeys((List<AbstractInstituteRate>) instituteRates);
        Set<String> budgetRateKeys = storeAllKeys((List<AbstractInstituteRate>) budgetRates);
        
        return !instituteRateKeys.containsAll(budgetRateKeys);
    }
    
    protected Set<String> storeAllKeys(List<AbstractInstituteRate> rates) {
        Set<String> keys = new HashSet<String>(rates.size(), 1.0f);
        for(AbstractInstituteRate rate: rates) {
            keys.add(rate.getRateKeyAsString());
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    protected void resetAbstractBudgetApplicableRatesToInstituteRates(List budgetRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            abstractBudgetRate.setApplicableRate(abstractBudgetRate.getInstituteRate()); 
        }
    }
    
    @SuppressWarnings("unchecked")
    protected void resetBudgetRatesForRateClassType(List<RateClass> rateClasses, String rateClassType, List budgetRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        
        for(RateClass rateClass: rateClasses) {
            if(rateClass.getRateClassType().equalsIgnoreCase(rateClassType)) {
                for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
                    if(abstractBudgetRate.getRateClassCode().equalsIgnoreCase(rateClass.getRateClassCode())) {
                        abstractBudgetRate.setApplicableRate(abstractBudgetRate.getInstituteRate()); 
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    protected void syncBudgetRates(List budgetRates, Collection abstractIntituteRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        Map<String, AbstractInstituteRate> instRateMap = mapRatesToKeys(abstractIntituteRates);
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            String hKey = abstractBudgetRate.getRateKeyAsString();
            AbstractInstituteRate abstractInstituteRate = instRateMap.get(hKey);
            abstractBudgetRate.setInstituteRate(abstractInstituteRate.getInstituteRate()); 
            abstractBudgetRate.setApplicableRate(abstractInstituteRate.getInstituteRate()); 
        }
    }
    
    protected String getFormattedAffectedBudgetPeriod(String periodAffected) {
        String budgetPeriodAffected = periodAffected;
        if(budgetPeriodAffected != null) {
            budgetPeriodAffected = budgetPeriodAffected.trim();
            budgetPeriodAffected = budgetPeriodAffected.replace(PERIOD_SEARCH_SEPARATOR, PERIOD_DISPLAY_SEPARATOR);
            budgetPeriodAffected = budgetPeriodAffected.substring(1, budgetPeriodAffected.length()- 1);
        }
        return budgetPeriodAffected;
    }
    
    @SuppressWarnings("unchecked")
    protected void viewLocation(String viewLocation, Integer budgetPeriod, List rates) {
        List<AbstractBudgetRate> budgetRates = (List<AbstractBudgetRate>) rates;
        
        for(AbstractBudgetRate budgetRate: budgetRates) {
            String onOffCampusFlag = getLocationFlagAsString(budgetRate.getOnOffCampusFlag());
            boolean displayRate = (viewLocation == null || (viewLocation.equalsIgnoreCase(onOffCampusFlag)));
            
            /* check budget Period */
            if(displayRate && budgetPeriod != null) {
                String trackAffectedPeriod = budgetRate.getTrackAffectedPeriod();
                String formattedBudgetPeriod = getSeparatedBudgetPeriod(budgetPeriod);
                if(trackAffectedPeriod == null || (trackAffectedPeriod.indexOf(formattedBudgetPeriod) < 0)) {
                    displayRate  = false;
                }
            }
            budgetRate.setDisplayLocation(displayRate); 
        }
    }
    
    protected String getLocationFlagAsString(boolean onOffCampusFlag) {
        return onOffCampusFlag ? Constants.ON_CAMUS_FLAG : Constants.OFF_CAMUS_FLAG;
    }
    
    protected String getSeparatedBudgetPeriod(Integer budgetPeriod) {
        return new StringBuilder(PERIOD_SEARCH_SEPARATOR).append(budgetPeriod).append(PERIOD_SEARCH_SEPARATOR).toString();
    }
    
    @SuppressWarnings("unchecked")
    protected void syncBudgetRatesForRateClassType(List<RateClass> rateClasses, String rateClassType, Collection abstractInstituteRates, List budgetRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        Map<String, AbstractInstituteRate> instRateMap = mapRatesToKeys(abstractInstituteRates);
        for(RateClass rateClass: rateClasses) {
            if(rateClass.getRateClassType().equalsIgnoreCase(rateClassType)) {
                for(AbstractBudgetRate budgetRate: abstractBudgetRates) {
                    if(budgetRate.getRateClassCode().equalsIgnoreCase(rateClass.getRateClassCode())) {
                        String hKey = budgetRate.getRateKeyAsString();
                        InstituteRate instituteRate = (InstituteRate)instRateMap.get(hKey);
                        budgetRate.setInstituteRate(instituteRate.getInstituteRate()); 
                        budgetRate.setApplicableRate(instituteRate.getInstituteRate()); 
                    }
                }
            }
        }
    }
    
    protected Map<String, RateClassType> populateExistingRateClassTypeMap(List<RateClassType> rateClassTypes) {
        Map<String, RateClassType> existingRateClassTypeMap = new HashMap<String, RateClassType>();        
        for(RateClassType rateClassType: rateClassTypes) {
            existingRateClassTypeMap.put(rateClassType.getRateClassType(), rateClassType);
        }
        return existingRateClassTypeMap;
    }
    
    protected void getBudgetRates(BudgetDocument<T> budgetDocument, Collection<InstituteRate> allInstituteRates) {
        Budget budget = budgetDocument.getBudget();
        getBudgetRates(budget.getRateClassTypes(), budgetDocument, allInstituteRates);
    }

    /* get budget rates applicable for the proposal - based on activity type
     * and unit number 
     * */
    protected void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument, Collection<InstituteRate> allInstituteRates) {
        Budget budget = budgetDocument.getBudget();
        List<InstituteRate> instituteRates = budget.getInstituteRates();        
        filterRates(budget, allInstituteRates, instituteRates);        
        List<BudgetRate> budgetRates = budget.getBudgetRates();
        
        syncBudgetRateCollections(rateClassTypes, budgetDocument, instituteRates, budgetRates);
        
        getBudgetLaRates(rateClassTypes, budgetDocument);
        checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
    }
    
    protected void getBudgetLaRates(BudgetDocument<T> budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        Budget budget = budgetDocument.getBudget();
        getBudgetLaRates(budget.getRateClassTypes(), budgetDocument, allInstituteLaRates);
    }

    protected void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument) {
        getBudgetLaRates(rateClassTypes, budgetDocument, new ArrayList<InstituteLaRate>(getInstituteLaRates(budgetDocument)));
    }
    
    /**
     * Get budget LA rates applicable for the proposal - based on unit number
     * @param rateClassTypes
     * @param budget
     * @param allInstituteLaRates
     */
    protected void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        Budget budget = budgetDocument.getBudget();
        List<InstituteLaRate> instituteLaRates = budget.getInstituteLaRates();        
        filterRates(budget, allInstituteLaRates, instituteLaRates);        
        List<BudgetLaRate> budgetRates = budget.getBudgetLaRates();
        
        syncBudgetRateCollections(rateClassTypes, budgetDocument, instituteLaRates, budgetRates);   
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return _businessObjectService;
    }
    
    @SuppressWarnings("unchecked")
    protected void syncBudgetRateCollections(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument,
                                                List abstractInstituteRates, List budgetRates) {

        Budget budget = budgetDocument.getBudget();
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates; 
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) abstractInstituteRates;
        
        syncAllRateClasses(budget, instituteRates);
        syncAllRateClassTypes(budget, rateClassTypes, instituteRates);
        if(budgetRates.size() == 0) {
            syncAllBudgetRatesForInstituteRateType(budgetDocument, abstractBudgetRates, instituteRates);
        }
    }
    
  @SuppressWarnings("unchecked")
  public void syncBudgetRateCollectionsToExistingRates(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument) {
      Budget budget = budgetDocument.getBudget();

      syncAllRateClasses(budget, (List) budget.getBudgetRates());
      syncAllRateClassTypes(budget, rateClassTypes, (List) budget.getBudgetRates());
      
      syncAllRateClasses(budget, (List) budget.getBudgetLaRates());
      syncAllRateClassTypes(budget, rateClassTypes, (List) budget.getBudgetLaRates());

      checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
  }

    protected void syncAllBudgetRatesForInstituteRateType(BudgetDocument<T> budgetDocument, List<AbstractBudgetRate> budgetRates, List<AbstractInstituteRate> instituteRates) {
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                budgetRates.add(generateBudgetRate(budgetDocument, abstractInstituteRate));
            }
        }
        
        updateRatesForEachPeriod(budgetDocument.getBudget());
        Collections.sort(budgetRates);
    }

    @SuppressWarnings("unchecked")
    protected void replaceRateClassesForRateClassType(String rateClassType, Budget budget, List rates) {
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) rates;
        List<RateClass> budgetRateClasses = budget.getRateClasses();        

        removeAllPreviouslyRegisteredRateClassesForRateClassType(rateClassType, budgetRateClasses);
        addRateClassesForRateClassType(rateClassType, instituteRates);        
    }

    protected void removeAllPreviouslyRegisteredRateClassesForRateClassType(String rateClassType, List<RateClass> budgetRateClasses) {
        Iterator<RateClass> iter = budgetRateClasses.iterator();
        while(iter.hasNext()) {
            RateClass rateClass = iter.next();
            if(rateClassType.equals(rateClass.getRateClassType())) {
                iter.remove();
            }
        }
    }

    protected void addRateClassesForRateClassType(String rateClassType, List<AbstractInstituteRate> instituteRates) {
        Map<String, RateClass> mapOfMatchingRateClasses = new HashMap<String, RateClass>();
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateType() != null) {
                RateClass rateClass = abstractInstituteRate.getRateType().getRateClass();
                if(rateClass==null) abstractInstituteRate.getRateType().refreshNonUpdateableReferences();
                rateClass = abstractInstituteRate.getRateType().getRateClass();
                if(rateClass.getRateClassType().equals(rateClassType) && mapOfMatchingRateClasses.get(rateClass.getRateClassCode()) == null) {
                    mapOfMatchingRateClasses.put(rateClass.getRateClassCode(), rateClass);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    protected void replaceBudgetRatesForRateClassType(String rateClassType, BudgetDocument<T> budgetDocument, List existingBudgetRates, List rates) {
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) rates; 
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) existingBudgetRates;
        
        Map<String, AbstractBudgetRate> existingBudgetRateMap = preservePersistedBudgetRatesForRateClassType(rateClassType, abstractBudgetRates);        
        removeRegisteredBudgetRatesForRateClassType(rateClassType, abstractBudgetRates);
        
        Map<String, AbstractBudgetRate> newBudgetRateMap = generateNewAndUpdatedBudgetRates(rateClassType, budgetDocument, instituteRates, existingBudgetRateMap);
        
        registerNewAndUpdatedBudgetRates(abstractBudgetRates, newBudgetRateMap);
        
        updateRatesForEachPeriod(budgetDocument.getBudget());
        Collections.sort(abstractBudgetRates);
    }

    protected void registerNewAndUpdatedBudgetRates(List<AbstractBudgetRate> abstractBudgetRates, Map<String, AbstractBudgetRate> newBudgetRateMap) {
        abstractBudgetRates.addAll(newBudgetRateMap.values());
    }

    protected Map<String, AbstractBudgetRate> generateNewAndUpdatedBudgetRates(String rateClassType, BudgetDocument<T> budgetDocument, 
                                                                             List<AbstractInstituteRate> instituteRates,
                                                                             Map<String, AbstractBudgetRate> existingBudgetRateMap) {
        Map<String, AbstractBudgetRate> newBudgetRateMap = new HashMap<String, AbstractBudgetRate>(); 
        
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateType() != null) {
                RateClass rateClass = abstractInstituteRate.getRateType().getRateClass();                
                if(rateClassType.equals(rateClass.getRateClassType())) {
                    AbstractBudgetRate newBudgetRate = generateBudgetRate(budgetDocument, abstractInstituteRate);
                    String hKey = abstractInstituteRate.getRateKeyAsString();
                    AbstractBudgetRate existingBudgetRate = existingBudgetRateMap.get(hKey);
                    if(existingBudgetRate != null) {
                        newBudgetRate.setVersionNumber(existingBudgetRate.getVersionNumber());
                    }
                    newBudgetRateMap.put(hKey, newBudgetRate);
                }
            }
        }
        return newBudgetRateMap;
    }

    protected void removeRegisteredBudgetRatesForRateClassType(String rateClassType, List<AbstractBudgetRate> abstractBudgetRates) {
        Iterator<AbstractBudgetRate> iter = abstractBudgetRates.iterator();
        while(iter.hasNext()) {
            AbstractBudgetRate budgetRate = iter.next();
            if(rateClassType.equals(budgetRate.getRateClass().getRateClassType())) {
                iter.remove();
            }
        }
    }

    protected Map<String, AbstractBudgetRate> preservePersistedBudgetRatesForRateClassType(String rateClassType, List<AbstractBudgetRate> abstractBudgetRates) {
        Map<String, AbstractBudgetRate> existingBudgetRateMap = new HashMap<String, AbstractBudgetRate>();        
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            if(rateClassType.equals(abstractBudgetRate.getRateClass().getRateClassType())) {
                existingBudgetRateMap.put(abstractBudgetRate.getRateKeyAsString(), abstractBudgetRate);
            }
        }
        return existingBudgetRateMap;
    }
    
    protected void syncAllRateClasses(Budget budget, List<AbstractInstituteRate> instituteRates) {
        Map<String, RateClass> rateClassMap = new HashMap<String, RateClass>();
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                String rateClassCode = abstractInstituteRate.getRateClassCode();
                if(rateClassMap.get(rateClassCode) == null) {
                    rateClassMap.put(rateClassCode, abstractInstituteRate.getRateClass());
                }
            }
        }
        
        budget.getRateClasses().addAll(rateClassMap.values());
    }

    protected void syncAllRateClassTypes(Budget budget, List<RateClassType> rateClassTypes, List<AbstractInstituteRate> instituteRates) {
        Map<String, RateClassType> existingRateClassTypeMap = populateExistingRateClassTypeMap(rateClassTypes);
        Map<String, RateClassType> rateClassTypeMap = new HashMap<String, RateClassType>();
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                String rateClassType = abstractInstituteRate.getRateClass().getRateClassType();
                if(existingRateClassTypeMap.get(rateClassType) == null) {
                    rateClassTypeMap.put(rateClassType, abstractInstituteRate.getRateClass().getRateClassTypeT());
                }
            }
        }
        
        rateClassTypes.addAll(rateClassTypeMap.values());
    }

    protected AbstractBudgetRate generateBudgetProposalRate(BudgetDocument<T> budgetDocument, InstituteRate instituteRate) {
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        return new BudgetRate(budgetParent.getUnitNumber(), instituteRate);
    }
    
    protected AbstractBudgetRate generateBudgetProposalLaRate(BudgetDocument<T> budgetDocument, InstituteLaRate instituteLaRate) {
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        return new BudgetLaRate(budgetParent.getUnitNumber(), instituteLaRate);
    }

    protected AbstractBudgetRate generateBudgetRate(BudgetDocument<T> budgetDocument, AbstractInstituteRate abstractInstituteRate) {
        Budget budget = budgetDocument.getBudget();
        AbstractBudgetRate abstractBudgetRate = (abstractInstituteRate instanceof InstituteRate) ?
                        generateBudgetProposalRate(budgetDocument, (InstituteRate) abstractInstituteRate) :
                        generateBudgetProposalLaRate(budgetDocument, (InstituteLaRate) abstractInstituteRate);
        abstractBudgetRate.setBudgetId(budget.getBudgetId());                
        abstractBudgetRate.setBudget(budget);
        return abstractBudgetRate;
    }

    /**
     * Searches for persisted {@link RateClass} instances based on the given <code>rateClassType</code>. Uses the {@link BusinessObjectService}
     * to grab appropriate {@link RateClass} instances since {@link RateClass} is a {@link BusinessObject}
     *
     * @param rateClassType to use for retrieving {@link RateClass} instances
     * @returns a List of {@link RateClass} instances
     */
    @SuppressWarnings("unchecked")
    public Collection<RateClass> getBudgetRateClasses(String rateClassType) {
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("rateClassType", rateClassType);
        
        return getBusinessObjectService().findMatching(RateClass.class, queryMap);
    }

    /**
     * Retrieves {@link RateClass} instances as a {@link Map} keyed from the <code>rateTypeCode</code>. This makes it easy for
     * classes (particularly in the UI) to grab {@link RateClass} information via rateTypeCode
     *
     * @param rateClassType to use for {@link RateClass} instances to be retrieved
     * @return a {@link Map} keyed on rateTypeCode containing {@link RateClass} instances
     */
    public Map<String, RateClass> getBudgetRateClassMap(String rateClassType) {
        Map<String, RateClass> retval = new HashMap<String, RateClass>();

        for (RateClass rateClass : getBudgetRateClasses(rateClassType)) {
            retval.put(rateClass.getRateClassCode(), rateClass);
        }

        return retval;
    }
    
    @SuppressWarnings("unchecked")
    protected void populateInstituteRates(BudgetDocument<T> budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        List instituteRates = (List) getInstituteRates(budgetDocument);
        filterRates(budget, instituteRates, budget.getInstituteRates()); 
        List instituteLaRates = (List) getInstituteLaRates(budgetDocument);
        filterRates(budget, instituteLaRates, budget.getInstituteLaRates()); 
    }
    
    public boolean isOutOfSyncForRateAudit(BudgetDocument<T> budgetDocument) {
        populateInstituteRates(budgetDocument);
        Budget budget = budgetDocument.getBudget();
        return isOutOfSyncForRateAudit(budget.getInstituteRates(), budget.getBudgetRates()) || 
                isOutOfSyncForRateAudit(budget.getInstituteLaRates(), budget.getBudgetLaRates());
    }
    
    /**
     * 
     * This method is to check to the class type level
     * @param instituteRates
     * @param budgetRates
     * @return
     */
    @SuppressWarnings("unchecked")
    protected boolean isOutOfSyncForRateAudit(List instituteRates, List budgetRates) {
        boolean outOfSync = false;
        outOfSync = !isRatesMatched(instituteRates,budgetRates) || outOfSync;
        outOfSync = !isRatesMatched(budgetRates, instituteRates) || outOfSync;
        
        return outOfSync;
    }
    
    protected boolean isRatesMatched(List<AbstractInstituteRate> fromRates, List<AbstractInstituteRate> toRates) {
        boolean matched = true;
        for (Object rate : fromRates) {
            AbstractInstituteRate budgetRate = (AbstractInstituteRate)rate;
            boolean isRateMatched = false;
            for (Object rate1 : toRates) {
                AbstractInstituteRate instituteRate = (AbstractInstituteRate)rate1;
                if ((instituteRate.getRateKeyAsString()+instituteRate.getInstituteRate()).equals(budgetRate.getRateKeyAsString()+budgetRate.getInstituteRate())) {
                    if (instituteRate instanceof InstituteRate) {
                        if (((InstituteRate)instituteRate).getActivityTypeCode().equals(((BudgetRate)budgetRate).getActivityTypeCode())) {
                            isRateMatched = true;
                            break;                            
                        }
                    } else {
                        isRateMatched = true;
                        break;
                    }
                }
            }
        
            if (!isRateMatched) {
                matched = false;
                String rateClassType = budgetRate.getRateClass().getRateClassTypeT().getDescription();
                String errorPath = "document.budgetProposalRate[" + rateClassType + "]";
                boolean isNewError = true;
                for (AuditError auditError : getAuditErrors()) {
                    if (auditError.getErrorKey().equals(errorPath) && auditError.getMessageKey().equals(KeyConstants.AUDIT_WARNING_RATE_OUT_OF_SYNC) && auditError.getLink().equals(Constants.BUDGET_RATE_PAGE + "." + rateClassType)) {
                        isNewError = false;
                        break;
                    }
                }
                if (isNewError) {
                    getAuditErrors().add(new AuditError(errorPath, KeyConstants.AUDIT_WARNING_RATE_OUT_OF_SYNC, Constants.BUDGET_RATE_PAGE + "." + rateClassType));
                }

            }
            
        }
        return matched;

    }
    
    @SuppressWarnings("unchecked")
    protected List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(BUDGET_RATE_AUDIT_WARNING_KEY)) {
           KNSGlobalVariables.getAuditErrorMap().put(BUDGET_RATE_AUDIT_WARNING_KEY, new AuditCluster(Constants.BUDGET_RATE_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(BUDGET_RATE_AUDIT_WARNING_KEY)).getAuditErrorList();
        }
        
        return auditErrors;
    }

    @SuppressWarnings("unchecked")
    protected boolean isOutOfSyncForRateAudit_org(List instituteRates, List budgetRates) {
        boolean outOfSync = areNumbersOfBudgetRatesOutOfSyncWithInstituteRates(instituteRates, budgetRates);
        if(!outOfSync) {
            outOfSync = areBudgetRatesOutOfSyncWithInsttituteRatesForRateAudit(instituteRates, budgetRates);
        }
        
        return outOfSync;
    }

    @SuppressWarnings("unchecked")
    protected boolean areBudgetRatesOutOfSyncWithInsttituteRatesForRateAudit(List instituteRates, List budgetRates) {        
        Set<String> instituteRateKeys = storeAllKeysWithRate((List<AbstractInstituteRate>) instituteRates);
        Set<String> budgetRateKeys = storeAllKeysWithRate((List<AbstractInstituteRate>) budgetRates);
        
        return !instituteRateKeys.containsAll(budgetRateKeys);
    }
    
    protected Set<String> storeAllKeysWithRate(List<AbstractInstituteRate> rates) {
        Set<String> keys = new HashSet<String>(rates.size(), 1.0f);
        for(AbstractInstituteRate rate: rates) {
            keys.add(rate.getRateKeyAsString()+rate.getInstituteRate());
        }
        return keys;
    }

    public void populateBudgetRatesForNewVersion(BudgetDocument<T> budgetDocument) {
        getBudgetRates(new ArrayList<RateClassType>(), budgetDocument);
    }

    /**
     * By default it does not have to perform sync
     * @see org.kuali.kra.budget.rates.BudgetRatesService#performSyncFlag()
     */
    public boolean performSyncFlag(BudgetDocument<T> budgetDocument) {
        return false;
    }


}
