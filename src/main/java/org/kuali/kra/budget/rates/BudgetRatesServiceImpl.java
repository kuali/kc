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
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class BudgetRatesServiceImpl implements BudgetRatesService<ProposalDevelopmentDocument> {
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
        resetAbstractBudgetApplicableRatesToInstituteRates(budget.getBudgetProposalRates());
        resetAbstractBudgetApplicableRatesToInstituteRates(budget.getBudgetProposalLaRates());
    }
    
    /**
     * reset budget rates for a panel
     * each panel is based on rate class type 
     *
     * @see org.kuali.kra.budget.rates.BudgetRatesService#resetBudgetRatesForRateClassType(java.lang.String, org.kuali.kra.budget.core.Budget)
     */
    public void resetBudgetRatesForRateClassType(String rateClassType, Budget budget) {
        List<RateClass> rateClasses = budget.getRateClasses();
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budget.getBudgetProposalRates());
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budget.getBudgetProposalLaRates());        
    }
    
    /**
     * @see org.kuali.kra.budget.rates.BudgetRatesService#syncAllBudgetRates(org.kuali.kra.budget.core.Budget)
     */
    public void syncAllBudgetRates(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        List<InstituteRate> allInstituteRates = new ArrayList<InstituteRate>(getInstituteRates(budgetDocument));
        List<InstituteLaRate> allInstituteLaRates = new ArrayList<InstituteLaRate>(getInstituteLaRates(budgetDocument));
        
        if(isOutOfSync(budget)) {
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budget.getBudgetProposalRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budget.getBudgetProposalLaRates());
            
            budget.getBudgetProposalRates().clear();
            budget.getBudgetProposalLaRates().clear();
            budget.getRateClasses().clear();
            
            // since different rate schedules can change UnrecoveredFandA, clear here
            budget.getBudgetUnrecoveredFandAs().clear();
            
            getBudgetRates(budgetDocument, allInstituteRates);
            getBudgetLaRates(budgetDocument, allInstituteLaRates);
            
            syncVersionNumber(mapOfExistingBudgetProposalRates, budget.getBudgetProposalRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budget.getBudgetProposalLaRates());
        } else {
            syncBudgetRates(budget.getBudgetProposalRates(), allInstituteRates);
            syncBudgetRates(budget.getBudgetProposalLaRates(), allInstituteLaRates);
        }
    }

    @SuppressWarnings("unchecked")
    private void syncVersionNumber(Map<String, AbstractInstituteRate> oldRateMap, List rates) {
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
        viewLocation(viewLocation, budgetPeriod, budget.getBudgetProposalRates());
        viewLocation(viewLocation, budgetPeriod, budget.getBudgetProposalLaRates());        
    }
    
    /* sync budget rates for a panel
     * each panel is based on rate class type 
     * */
    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();

        if(isOutOfSync(budget)) {
            populateInstituteRates(budgetDocument);
            
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budget.getBudgetProposalRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budget.getBudgetProposalLaRates());
            replaceRateClassesForRateClassType(rateClassType, budget, budget.getInstituteRates());
            replaceRateClassesForRateClassType(rateClassType, budget, budget.getInstituteLaRates());
            replaceBudgetRatesForRateClassType(rateClassType, budgetDocument, budget.getBudgetProposalRates(), budget.getInstituteRates());
            replaceBudgetRatesForRateClassType(rateClassType, budgetDocument, budget.getBudgetProposalLaRates(), budget.getInstituteLaRates());
            syncVersionNumber(mapOfExistingBudgetProposalRates, budget.getBudgetProposalRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budget.getBudgetProposalLaRates());
        } else {
            List<RateClass> rateClasses = budget.getRateClasses();            
            syncBudgetRatesForRateClassType(rateClasses, rateClassType, getInstituteRates(budgetDocument), budget.getBudgetProposalRates());
            syncBudgetRatesForRateClassType(rateClasses, rateClassType, getInstituteLaRates(budgetDocument), budget.getBudgetProposalLaRates());
        }
    }    
    
    /**
     * 
     * @see org.kuali.kra.budget.rates.BudgetRatesService#getBudgetRates(java.util.List, org.kuali.kra.budget.core.Budget)
     */
    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
        getBudgetRates(rateClassTypes, budgetDocument, getInstituteRates(budgetDocument));
    }

    /* verify and add activity type prefix if required for rate class type description
     * 
     * */
    private void checkActivityPrefixForRateClassTypes(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
        //String activityTypeDescription = budget.getProposal().getActivityType().getDescription().concat(SPACE);
        Budget budget = budgetDocument.getBudget();
        String activityTypeDescription = getActivityTypeDescription(budgetDocument);
        List<BudgetProposalRate> budgetProposalRates = budget.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budget.getBudgetProposalLaRates();
        for(RateClassType rateClassType : rateClassTypes) {
            if(rateClassType.getPrefixActivityType()) {
                String newRateClassTypeDescription = activityTypeDescription.concat(rateClassType.getDescription()); 
                rateClassType.setDescription(newRateClassTypeDescription);
                rateClassType.setPrefixActivityType(false);
                /* set in proposal rates reference */
                for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
                    RateClassType BPRateClassType = budgetProposalRate.getRateClass().getRateClassTypeT();
                    if(rateClassType.getRateClassType().equalsIgnoreCase(BPRateClassType.getRateClassType())) {
                        BPRateClassType.setDescription(newRateClassTypeDescription);
                    }
                }
                /* set in proposal LA rates reference */
                for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
                    RateClassType BPLRateClassType = budgetProposalLaRate.getRateClass().getRateClassTypeT();
                    if(rateClassType.getRateClassType().equalsIgnoreCase(BPLRateClassType.getRateClassType())) {
                        BPLRateClassType.setDescription(newRateClassTypeDescription);
                    }
                }
            }
        }
    }
    
    private String getActivityTypeDescription(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument proposal = budgetDocument.getParentDocument();
        
        if (budget.isRateSynced() || !KraServiceLocator.getService(BudgetService.class).
                checkActivityTypeChange(getProposal(budget), budget)) {
            if(proposal.getActivityType()!= null){
                return proposal.getActivityType().getDescription().concat(SPACE);
            }
            else
            {
                return "";
            }
        } else {
            ProposalDevelopmentDocument pdDoc = getProposal(budget);
            String activityTypeCode=null;
            if (CollectionUtils.isNotEmpty(budget.getBudgetProposalRates())) {
                activityTypeCode = ((BudgetProposalRate)budget.getBudgetProposalRates().get(0)).getActivityTypeCode();
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
    public List<BudgetPeriod> getBudgetPeriods(){
        BudgetForm budgetForm = (BudgetForm) GlobalVariables.getKualiForm();
        BudgetDocument budgetDocument  = budgetForm.getBudgetDocument();
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
    private void updateRatesForEachPeriod(Budget budget) {
        List<BudgetProposalRate> budgetProposalRates = budget.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budget.getBudgetProposalLaRates();
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();

        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
                if(budgetProposalRate.getStartDate().compareTo(budgetPeriod.getEndDate()) <= 0) {
                    String dispBudgetPeriod = budgetPeriod.getBudgetPeriod().toString();
                    String formattedPeriod = dispBudgetPeriod.concat(PERIOD_SEARCH_SEPARATOR);
                    String currBudgetPeriod = budgetProposalRate.getTrackAffectedPeriod(); //(String)ratesForEachPeriod.get(budgetPeriod.getBudgetPeriod());
                    if(currBudgetPeriod == null) {
                        currBudgetPeriod = PERIOD_SEARCH_SEPARATOR.concat(formattedPeriod);
                        budgetProposalRate.setTrackAffectedPeriod(currBudgetPeriod);
                    }else {
                        if(currBudgetPeriod.indexOf(formattedPeriod) < 0) {
                            currBudgetPeriod = currBudgetPeriod.concat(formattedPeriod); 
                            budgetProposalRate.setTrackAffectedPeriod(currBudgetPeriod);
                        }
                    }
                    budgetProposalRate.setAffectedBudgetPeriod(getFormattedAffectedBudgetPeriod(currBudgetPeriod));
                }
            }
            for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
                if(budgetProposalLaRate.getStartDate().compareTo(budgetPeriod.getEndDate()) <= 0) {
                    String dispBudgetPeriod = budgetPeriod.getBudgetPeriod().toString();
                    String formattedPeriod = dispBudgetPeriod.concat(PERIOD_SEARCH_SEPARATOR);
                    String currBudgetPeriod = budgetProposalLaRate.getTrackAffectedPeriod(); //(String)ratesForEachPeriod.get(budgetPeriod.getBudgetPeriod());
                    if(currBudgetPeriod == null) {
                        currBudgetPeriod = PERIOD_SEARCH_SEPARATOR.concat(formattedPeriod);
                        budgetProposalLaRate.setTrackAffectedPeriod(currBudgetPeriod);
                    }else {
                        if(currBudgetPeriod.indexOf(formattedPeriod) < 0) {
                            currBudgetPeriod = currBudgetPeriod.concat(formattedPeriod); 
                            budgetProposalLaRate.setTrackAffectedPeriod(currBudgetPeriod);
                        }
                    }
                    budgetProposalLaRate.setAffectedBudgetPeriod(getFormattedAffectedBudgetPeriod(currBudgetPeriod));
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
    private Map<String, AbstractInstituteRate> mapRatesToKeys(Collection rates) {
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
    private Collection<InstituteRate> getInstituteRates(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        Map rateFilterMap = getRateFilterMapWithActivityTypeCode(budgetDocument);
        return (Collection<InstituteRate>) getAbstractInstituteRates(budgetDocument, InstituteRate.class, rateFilterMap);
    }

    /* get all institute rates - based on 
     * and unit number 
     * */
    @SuppressWarnings("unchecked")
    private Collection<InstituteLaRate> getInstituteLaRates(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument(); 
        String unitNumber = parentDocument.getUnitNumber();                               
        Collection abstractInstituteRates = getFilteredInstituteLaRates(InstituteLaRate.class, unitNumber, parentDocument.getUnit(), getRateFilterMap(budgetDocument));
        abstractInstituteRates = abstractInstituteRates.size() > 0 ? abstractInstituteRates : new ArrayList();
        return (Collection<InstituteLaRate>)abstractInstituteRates ;
    }
    
    @SuppressWarnings("unchecked")
    private Collection getAbstractInstituteRates(BudgetDocument budgetDocument, Class rateType, Map rateFilterMap) {
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument(); 
        String unitNumber = parentDocument.getUnitNumber();                               
        Collection abstractInstituteRates = getFilteredInstituteRates(rateType, unitNumber, parentDocument.getUnit(), rateFilterMap);        
        
        return abstractInstituteRates.size() > 0 ? abstractInstituteRates : new ArrayList();
    }

    /**
     * This method...
     * @param budget
     * @return
     */
    private ProposalDevelopmentDocument getProposal(Budget budget) {
        BudgetDocument budgetDocument = budget.getBudgetDocument();
        if(budgetDocument==null){
            budget.refreshReferenceObject("budgetDocument");
            budgetDocument = budget.getBudgetDocument();
        }
        ProposalDevelopmentDocument proposal = (ProposalDevelopmentDocument)budgetDocument.getParentDocument();
        return proposal;
    }

    private Map<String, String> getRateFilterMap(BudgetDocument budgetDocument) {        
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument(); 
        Map<String, String> rateFilterMap = new HashMap<String, String>();
        rateFilterMap.put(UNIT_NUMBER_KEY, parentDocument.getUnitNumber());
        return rateFilterMap;
    }
    
    private Map<String, String> getRateFilterMapWithActivityTypeCode(BudgetDocument budgetDocument) {
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument(); 
        Map<String, String> rateFilterMap = getRateFilterMap(budgetDocument);
        rateFilterMap.put(ACTIVITY_TYPE_CODE_KEY, parentDocument.getActivityTypeCode());
        return rateFilterMap;
    }

    @SuppressWarnings("unchecked")
    private Collection getFilteredInstituteLaRates(Class rateType, String unitNumber, Unit currentUnit, Map<String, String> rateFilterMap) {
        Collection abstractInstituteRates;
        abstractInstituteRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, rateFilterMap));
        return abstractInstituteRates;
    }

    @SuppressWarnings("unchecked")
    private Collection getFilteredInstituteRates(Class rateType, String unitNumber, Unit currentUnit, Map<String, String> rateFilterMap) {
        Collection abstractInstituteRates;
        do {
            abstractInstituteRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, rateFilterMap));
            currentUnit = makeParentUnitAsCurrentUnit(currentUnit, rateFilterMap);
        } while(abstractInstituteRates.size() == 0 && currentUnit != null);
        
        return abstractInstituteRates;
    }

    @SuppressWarnings("unchecked")
    private Collection filterForActiveRatesOnly(Collection abstractInstituteRates) {        
        List filteredList = new ArrayList();
        for(AbstractInstituteRate rate: (Collection<AbstractInstituteRate>) abstractInstituteRates) {
            if(rate.getActive()) {
                filteredList.add(rate);
            } else {
                if(LOG.isDebugEnabled()) {
                    LOG.debug("Filtering inactive rate: " + rate.getObjectId());
                }
            }
        }
        return filteredList;
    }

    private Unit makeParentUnitAsCurrentUnit(Unit currentUnit, Map<String, String> rateFilterMap) {
        Unit parentUnit = currentUnit.getParentUnit();
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
    private Date getRateEffectiveStartDate(Budget budget, AbstractInstituteRate rate, Date personEffectiveDate) {
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
    private Date getBudgetPersonSalaryEffectiveDate(Budget budget) {
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
    private void getRatesForProjectDates(Budget budget, Collection allRates, Collection filteredRates, Date personSalaryEffectiveDate) {
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
    private void getApplicableRates(Budget budget, Collection allRates, Collection filteredRates, Date personSalaryEffectiveDate) {
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

    private String generateThreePartKey(AbstractInstituteRate instituteRate) {
        return new StringBuilder(instituteRate.getRateClassCode())
                .append(instituteRate.getRateTypeCode())
                .append(getLocationFlagAsString(instituteRate.getOnOffCampusFlag()))
                .toString();
    }
    
    /* filter institute rates - get rates applicable for 
     * the project 
     * */
    @SuppressWarnings("unchecked")
    private void filterRates(Budget budget, Collection allAbstractInstituteRates, Collection filteredAbstractInstituteRates) {
        filteredAbstractInstituteRates.clear();
        Date personSalaryEffectiveDate = getBudgetPersonSalaryEffectiveDate(budget);
        getRatesForProjectDates(budget, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
        getApplicableRates(budget, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
    }
    
    private boolean isOutOfSync(Budget budget) {
        return isOutOfSync(budget.getInstituteRates(), budget.getBudgetProposalRates()) || 
                isOutOfSync(budget.getInstituteLaRates(), budget.getBudgetProposalLaRates());
    }
    
    @SuppressWarnings("unchecked")
    private boolean isOutOfSync(List instituteRates, List budgetRates) {
        boolean outOfSync = areNumbersOfBudgetRatesOutOfSyncWithInstituteRates(instituteRates, budgetRates);
        if(!outOfSync) {
            outOfSync = areBudgetRatesOutOfSyncWithInsttituteRates(instituteRates, budgetRates);
        }
        
        return outOfSync;
    }

    @SuppressWarnings("unchecked")
    private boolean areNumbersOfBudgetRatesOutOfSyncWithInstituteRates(List instituteRates, List budgetRates) {
        return instituteRates.size() != budgetRates.size();
    }

    @SuppressWarnings("unchecked")
    private boolean areBudgetRatesOutOfSyncWithInsttituteRates(List instituteRates, List budgetRates) {        
        Set<String> instituteRateKeys = storeAllKeys((List<AbstractInstituteRate>) instituteRates);
        Set<String> budgetRateKeys = storeAllKeys((List<AbstractInstituteRate>) budgetRates);
        
        return instituteRateKeys.containsAll(budgetRateKeys);
    }
    
    private Set<String> storeAllKeys(List<AbstractInstituteRate> rates) {
        Set<String> keys = new HashSet<String>(rates.size(), 1.0f);
        for(AbstractInstituteRate rate: rates) {
            keys.add(rate.getRateKeyAsString());
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    private void resetAbstractBudgetApplicableRatesToInstituteRates(List budgetRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            abstractBudgetRate.setApplicableRate(abstractBudgetRate.getInstituteRate()); 
        }
    }
    
    @SuppressWarnings("unchecked")
    private void resetBudgetRatesForRateClassType(List<RateClass> rateClasses, String rateClassType, List budgetRates) {
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
    private void syncBudgetRates(List budgetRates, Collection abstractIntituteRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        Map<String, AbstractInstituteRate> instRateMap = mapRatesToKeys(abstractIntituteRates);
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            String hKey = abstractBudgetRate.getRateKeyAsString();
            AbstractInstituteRate abstractInstituteRate = instRateMap.get(hKey);
            abstractBudgetRate.setInstituteRate(abstractInstituteRate.getInstituteRate()); 
            abstractBudgetRate.setApplicableRate(abstractInstituteRate.getInstituteRate()); 
        }
    }
    
    private String getFormattedAffectedBudgetPeriod(String periodAffected) {
        String budgetPeriodAffected = periodAffected;
        if(budgetPeriodAffected != null) {
            budgetPeriodAffected = budgetPeriodAffected.trim();
            budgetPeriodAffected = budgetPeriodAffected.replace(PERIOD_SEARCH_SEPARATOR, PERIOD_DISPLAY_SEPARATOR);
            budgetPeriodAffected = budgetPeriodAffected.substring(1, budgetPeriodAffected.length()- 1);
        }
        return budgetPeriodAffected;
    }
    
    @SuppressWarnings("unchecked")
    private void viewLocation(String viewLocation, Integer budgetPeriod, List rates) {
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
    
    private String getLocationFlagAsString(boolean onOffCampusFlag) {
        return onOffCampusFlag ? Constants.ON_CAMUS_FLAG : Constants.OFF_CAMUS_FLAG;
    }
    
    private String getSeparatedBudgetPeriod(Integer budgetPeriod) {
        return new StringBuilder(PERIOD_SEARCH_SEPARATOR).append(budgetPeriod).append(PERIOD_SEARCH_SEPARATOR).toString();
    }
    
    @SuppressWarnings("unchecked")
    private void syncBudgetRatesForRateClassType(List<RateClass> rateClasses, String rateClassType, Collection abstractInstituteRates, List budgetRates) {
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
    
    private Map<String, RateClassType> populateExistingRateClassTypeMap(List<RateClassType> rateClassTypes) {
        Map<String, RateClassType> existingRateClassTypeMap = new HashMap<String, RateClassType>();        
        for(RateClassType rateClassType: rateClassTypes) {
            existingRateClassTypeMap.put(rateClassType.getRateClassType(), rateClassType);
        }
        return existingRateClassTypeMap;
    }
    
    private void getBudgetRates(BudgetDocument budgetDocument, Collection<InstituteRate> allInstituteRates) {
        Budget budget = budgetDocument.getBudget();
        getBudgetRates(budget.getRateClassTypes(), budgetDocument, allInstituteRates);
    }

    /* get budget rates applicable for the proposal - based on activity type
     * and unit number 
     * */
    private void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument, Collection<InstituteRate> allInstituteRates) {
        Budget budget = budgetDocument.getBudget();
        List<InstituteRate> instituteRates = budget.getInstituteRates();        
        filterRates(budget, allInstituteRates, instituteRates);        
        List<BudgetProposalRate> budgetRates = budget.getBudgetProposalRates();
        
        syncBudgetRateCollections(rateClassTypes, budgetDocument, instituteRates, budgetRates);
        
        getBudgetLaRates(rateClassTypes, budgetDocument);
        checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
    }
    
    private void getBudgetLaRates(BudgetDocument budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        Budget budget = budgetDocument.getBudget();
        getBudgetLaRates(budget.getRateClassTypes(), budgetDocument, allInstituteLaRates);
    }

    private void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        getBudgetLaRates(rateClassTypes, budgetDocument, new ArrayList<InstituteLaRate>(getInstituteLaRates(budgetDocument)));
    }
    
    /**
     * Get budget LA rates applicable for the proposal - based on unit number
     * @param rateClassTypes
     * @param budget
     * @param allInstituteLaRates
     */
    private void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        Budget budget = budgetDocument.getBudget();
        List<InstituteLaRate> instituteLaRates = budget.getInstituteLaRates();        
        filterRates(budget, allInstituteLaRates, instituteLaRates);        
        List<BudgetProposalLaRate> budgetRates = budget.getBudgetProposalLaRates();
        
        syncBudgetRateCollections(rateClassTypes, budgetDocument, instituteLaRates, budgetRates);   
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return _businessObjectService;
    }
    
    @SuppressWarnings("unchecked")
    private void syncBudgetRateCollections(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument,
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
  public void syncBudgetRateCollectionsToExistingRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
      Budget budget = budgetDocument.getBudget();

      syncAllRateClasses(budget, (List) budget.getBudgetProposalRates());
      syncAllRateClassTypes(budget, rateClassTypes, (List) budget.getBudgetProposalRates());
      
      syncAllRateClasses(budget, (List) budget.getBudgetProposalLaRates());
      syncAllRateClassTypes(budget, rateClassTypes, (List) budget.getBudgetProposalLaRates());

      checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
  }

    @SuppressWarnings("unchecked")
    private void syncAllBudgetRatesForInstituteRateType(BudgetDocument budgetDocument, List<AbstractBudgetRate> budgetRates, List<AbstractInstituteRate> instituteRates) {
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                budgetRates.add(generateBudgetRate(budgetDocument, abstractInstituteRate));
            }
        }
        
        updateRatesForEachPeriod(budgetDocument.getBudget());
        Collections.sort(budgetRates);
    }

    @SuppressWarnings("unchecked")
    private void replaceRateClassesForRateClassType(String rateClassType, Budget budget, List rates) {
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) rates;
        List<RateClass> budgetRateClasses = budget.getRateClasses();        

        removeAllPreviouslyRegisteredRateClassesForRateClassType(rateClassType, budgetRateClasses);
        addRateClassesForRateClassType(rateClassType, instituteRates);        
    }

    private void removeAllPreviouslyRegisteredRateClassesForRateClassType(String rateClassType, List<RateClass> budgetRateClasses) {
        Iterator<RateClass> iter = budgetRateClasses.iterator();
        while(iter.hasNext()) {
            RateClass rateClass = iter.next();
            if(rateClassType.equals(rateClass.getRateClassType())) {
                iter.remove();
            }
        }
    }

    private void addRateClassesForRateClassType(String rateClassType, List<AbstractInstituteRate> instituteRates) {
        Map<String, RateClass> mapOfMatchingRateClasses = new HashMap<String, RateClass>();
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateType() != null) {
                RateClass rateClass = abstractInstituteRate.getRateType().getRateClass(); 
                if(rateClass.getRateClassType().equals(rateClassType) && mapOfMatchingRateClasses.get(rateClass.getRateClassCode()) == null) {
                    mapOfMatchingRateClasses.put(rateClass.getRateClassCode(), rateClass);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void replaceBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument, List existingBudgetRates, List rates) {
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) rates; 
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) existingBudgetRates;
        
        Map<String, AbstractBudgetRate> existingBudgetRateMap = preservePersistedBudgetRatesForRateClassType(rateClassType, abstractBudgetRates);        
        removeRegisteredBudgetRatesForRateClassType(rateClassType, abstractBudgetRates);
        
        Map<String, AbstractBudgetRate> newBudgetRateMap = generateNewAndUpdatedBudgetRates(rateClassType, budgetDocument, instituteRates, existingBudgetRateMap);
        
        registerNewAndUpdatedBudgetRates(abstractBudgetRates, newBudgetRateMap);
        
        updateRatesForEachPeriod(budgetDocument.getBudget());
        Collections.sort(abstractBudgetRates);
    }

    private void registerNewAndUpdatedBudgetRates(List<AbstractBudgetRate> abstractBudgetRates, Map<String, AbstractBudgetRate> newBudgetRateMap) {
        abstractBudgetRates.addAll(newBudgetRateMap.values());
    }

    private Map<String, AbstractBudgetRate> generateNewAndUpdatedBudgetRates(String rateClassType, BudgetDocument budgetDocument, 
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

    private void removeRegisteredBudgetRatesForRateClassType(String rateClassType, List<AbstractBudgetRate> abstractBudgetRates) {
        Iterator<AbstractBudgetRate> iter = abstractBudgetRates.iterator();
        while(iter.hasNext()) {
            AbstractBudgetRate budgetRate = iter.next();
            if(rateClassType.equals(budgetRate.getRateClass().getRateClassType())) {
                iter.remove();
            }
        }
    }

    private Map<String, AbstractBudgetRate> preservePersistedBudgetRatesForRateClassType(String rateClassType, List<AbstractBudgetRate> abstractBudgetRates) {
        Map<String, AbstractBudgetRate> existingBudgetRateMap = new HashMap<String, AbstractBudgetRate>();        
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            if(rateClassType.equals(abstractBudgetRate.getRateClass().getRateClassType())) {
                existingBudgetRateMap.put(abstractBudgetRate.getRateKeyAsString(), abstractBudgetRate);
            }
        }
        return existingBudgetRateMap;
    }
    
    private void syncAllRateClasses(Budget budget, List<AbstractInstituteRate> instituteRates) {
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

    private void syncAllRateClassTypes(Budget budget, List<RateClassType> rateClassTypes, List<AbstractInstituteRate> instituteRates) {
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

    private AbstractBudgetRate generateBudgetProposalRate(BudgetDocument budgetDocument, InstituteRate instituteRate) {
        BudgetParentDocument proposal = budgetDocument.getParentDocument();
        return new BudgetProposalRate(proposal.getUnitNumber(), instituteRate);
    }
    
    private AbstractBudgetRate generateBudgetProposalLaRate(BudgetDocument budgetDocument, InstituteLaRate instituteLaRate) {
        BudgetParentDocument proposal = budgetDocument.getParentDocument();
        return new BudgetProposalLaRate(proposal.getUnitNumber(), instituteLaRate);
    }

    private AbstractBudgetRate generateBudgetRate(BudgetDocument budgetDocument, AbstractInstituteRate abstractInstituteRate) {
//        DevelopmentProposal proposal = budgetDocument.getParentDocument().getDevelopmentProposal();
        Budget budget = budgetDocument.getBudget();
        AbstractBudgetRate abstractBudgetRate = (abstractInstituteRate instanceof InstituteRate) ?
                        generateBudgetProposalRate(budgetDocument, (InstituteRate) abstractInstituteRate) :
                        generateBudgetProposalLaRate(budgetDocument, (InstituteLaRate) abstractInstituteRate);
//        abstractBudgetRate.setProposalNumber(proposal.getProposalNumber());
//        abstractBudgetRate.setBudgetVersionNumber(budget.getBudgetVersionNumber());
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
    
    private void populateInstituteRates(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        List instituteRates = (List) getInstituteRates(budgetDocument);
        filterRates(budget, instituteRates, budget.getInstituteRates()); 
        List instituteLaRates = (List) getInstituteLaRates(budgetDocument);
        filterRates(budget, instituteLaRates, budget.getInstituteLaRates()); 
    }
    
    public boolean isOutOfSyncForRateAudit(BudgetDocument budgetDocument) {
        populateInstituteRates(budgetDocument);
        Budget budget = budgetDocument.getBudget();
        return isOutOfSyncForRateAudit(budget.getInstituteRates(), budget.getBudgetProposalRates()) || 
                isOutOfSyncForRateAudit(budget.getInstituteLaRates(), budget.getBudgetProposalLaRates());
    }
    
    /**
     * 
     * This method is to check to the class type level
     * @param instituteRates
     * @param budgetRates
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean isOutOfSyncForRateAudit(List instituteRates, List budgetRates) {
        boolean outOfSync = false;
        outOfSync = !isRatesMatched(instituteRates,budgetRates) || outOfSync;
        outOfSync = !isRatesMatched(budgetRates, instituteRates) || outOfSync;
        
        return outOfSync;
    }
    
    private boolean isRatesMatched(List<AbstractInstituteRate> fromRates, List<AbstractInstituteRate> toRates) {
        boolean matched = true;
        for (Object rate : fromRates) {
            AbstractInstituteRate budgetRate = (AbstractInstituteRate)rate;
            boolean isRateMatched = false;
            for (Object rate1 : toRates) {
                AbstractInstituteRate instituteRate = (AbstractInstituteRate)rate1;
                if ((instituteRate.getRateKeyAsString()+instituteRate.getInstituteRate()).equals(budgetRate.getRateKeyAsString()+budgetRate.getInstituteRate())) {
                    if (instituteRate instanceof InstituteRate) {
                        if (((InstituteRate)instituteRate).getActivityTypeCode().equals(((BudgetProposalRate)budgetRate).getActivityTypeCode())) {
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
    
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!GlobalVariables.getAuditErrorMap().containsKey(BUDGET_RATE_AUDIT_WARNING_KEY)) {
           GlobalVariables.getAuditErrorMap().put(BUDGET_RATE_AUDIT_WARNING_KEY, new AuditCluster(Constants.BUDGET_RATE_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get(BUDGET_RATE_AUDIT_WARNING_KEY)).getAuditErrorList();
        }
        
        return auditErrors;
    }

    @SuppressWarnings("unchecked")
    private boolean isOutOfSyncForRateAudit_org(List instituteRates, List budgetRates) {
        boolean outOfSync = areNumbersOfBudgetRatesOutOfSyncWithInstituteRates(instituteRates, budgetRates);
        if(!outOfSync) {
            outOfSync = areBudgetRatesOutOfSyncWithInsttituteRatesForRateAudit(instituteRates, budgetRates);
        }
        
        return outOfSync;
    }

    @SuppressWarnings("unchecked")
    private boolean areBudgetRatesOutOfSyncWithInsttituteRatesForRateAudit(List instituteRates, List budgetRates) {        
        Set<String> instituteRateKeys = storeAllKeysWithRate((List<AbstractInstituteRate>) instituteRates);
        Set<String> budgetRateKeys = storeAllKeysWithRate((List<AbstractInstituteRate>) budgetRates);
        
        return !instituteRateKeys.containsAll(budgetRateKeys);
    }
    
    private Set<String> storeAllKeysWithRate(List<AbstractInstituteRate> rates) {
        Set<String> keys = new HashSet<String>(rates.size(), 1.0f);
        for(AbstractInstituteRate rate: rates) {
            keys.add(rate.getRateKeyAsString()+rate.getInstituteRate());
        }
        return keys;
    }


}
