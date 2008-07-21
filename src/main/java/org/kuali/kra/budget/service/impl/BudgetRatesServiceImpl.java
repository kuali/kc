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
package org.kuali.kra.budget.service.impl;

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
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.AbstractBudgetRate;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetRatesService;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public class BudgetRatesServiceImpl implements BudgetRatesService {
    private static final String SPACE = " ";
    public static final String UNIT_NUMBER_KEY = "unitNumber";
    public static final String ACTIVITY_TYPE_CODE_KEY = "activityTypeCode";
    
    private BusinessObjectService _businessObjectService;
    private static final String PERIOD_SEARCH_SEPARATOR = "|";
    private static final String PERIOD_DISPLAY_SEPARATOR = ",";
    private static final Log LOG = LogFactory.getLog(BudgetRatesServiceImpl.class);
    
    /**
     * @see org.kuali.kra.budget.service.BudgetRatesService#resetAllBudgetRates(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void resetAllBudgetRates(BudgetDocument budgetDocument) {
        resetAbstractBudgetApplicableRatesToInstituteRates(budgetDocument.getBudgetProposalRates());
        resetAbstractBudgetApplicableRatesToInstituteRates(budgetDocument.getBudgetProposalLaRates());
    }
    
    /**
     * reset budget rates for a panel
     * each panel is based on rate class type 
     *
     * @see org.kuali.kra.budget.service.BudgetRatesService#resetBudgetRatesForRateClassType(java.lang.String, org.kuali.kra.budget.document.BudgetDocument)
     */
    public void resetBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument) {
        List<RateClass> rateClasses = budgetDocument.getRateClasses();
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budgetDocument.getBudgetProposalRates());
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budgetDocument.getBudgetProposalLaRates());        
    }
    
    /**
     * @see org.kuali.kra.budget.service.BudgetRatesService#syncAllBudgetRates(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void syncAllBudgetRates(BudgetDocument budgetDocument) {
        List<InstituteRate> allInstituteRates = new ArrayList<InstituteRate>(getInstituteRates(budgetDocument));
        List<InstituteLaRate> allInstituteLaRates = new ArrayList<InstituteLaRate>(getInstituteLaRates(budgetDocument));
        
        if(isOutOfSync(budgetDocument)) {
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budgetDocument.getBudgetProposalRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budgetDocument.getBudgetProposalLaRates());
            
            budgetDocument.getBudgetProposalRates().clear();
            budgetDocument.getBudgetProposalLaRates().clear();
            budgetDocument.getRateClasses().clear();
            
            getBudgetRates(budgetDocument, allInstituteRates);
            getBudgetLaRates(budgetDocument, allInstituteLaRates);
            
            syncVersionNumber(mapOfExistingBudgetProposalRates, budgetDocument.getBudgetProposalRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budgetDocument.getBudgetProposalLaRates());
        } else {
            syncBudgetRates(budgetDocument.getBudgetProposalRates(), allInstituteRates);
            syncBudgetRates(budgetDocument.getBudgetProposalLaRates(), allInstituteLaRates);
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
    public void viewLocation(String viewLocation, Integer budgetPeriod, BudgetDocument budgetDocument) {
        viewLocation(viewLocation, budgetPeriod, budgetDocument.getBudgetProposalRates());
        viewLocation(viewLocation, budgetPeriod, budgetDocument.getBudgetProposalLaRates());        
    }
    
    /* sync budget rates for a panel
     * each panel is based on rate class type 
     * */
    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument) {
        if(isOutOfSync(budgetDocument)) {
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budgetDocument.getBudgetProposalRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budgetDocument.getBudgetProposalLaRates());
            replaceRateClassesForRateClassType(rateClassType, budgetDocument, budgetDocument.getInstituteRates());
            replaceRateClassesForRateClassType(rateClassType, budgetDocument, budgetDocument.getInstituteLaRates());
            replaceBudgetRatesForRateClassType(rateClassType, budgetDocument, budgetDocument.getBudgetProposalRates(), budgetDocument.getInstituteRates());
            replaceBudgetRatesForRateClassType(rateClassType, budgetDocument, budgetDocument.getBudgetProposalLaRates(), budgetDocument.getInstituteLaRates());
            syncVersionNumber(mapOfExistingBudgetProposalRates, budgetDocument.getBudgetProposalRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budgetDocument.getBudgetProposalLaRates());
        } else {
            List<RateClass> rateClasses = budgetDocument.getRateClasses();            
            syncBudgetRatesForRateClassType(rateClasses, rateClassType, getInstituteRates(budgetDocument), budgetDocument.getBudgetProposalRates());
            syncBudgetRatesForRateClassType(rateClasses, rateClassType, getInstituteLaRates(budgetDocument), budgetDocument.getBudgetProposalLaRates());
        }
    }    
    
    /**
     * 
     * @see org.kuali.kra.budget.service.BudgetRatesService#getBudgetRates(java.util.List, org.kuali.kra.budget.document.BudgetDocument)
     */
    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
        getBudgetRates(rateClassTypes, budgetDocument, getInstituteRates(budgetDocument));
    }

    /* verify and add activity type prefix if required for rate class type description
     * 
     * */
    public void checkActivityPrefixForRateClassTypes(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
        //String activityTypeDescription = budgetDocument.getProposal().getActivityType().getDescription().concat(SPACE);
        String activityTypeDescription = getActivityTypeDescription(budgetDocument);
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
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
        if (budgetDocument.isRateSynced() || !KraServiceLocator.getService(BudgetService.class).checkActivityTypeChange(budgetDocument.getProposal(), budgetDocument.getBudgetVersionNumber().toString())) {
            return budgetDocument.getProposal().getActivityType().getDescription().concat(SPACE);
        } else {
            ProposalDevelopmentDocument pdDoc = budgetDocument.getProposal();
            String activityTypeCode=null;
            if (CollectionUtils.isNotEmpty(budgetDocument.getBudgetProposalRates())) {
                activityTypeCode = ((BudgetProposalRate)budgetDocument.getBudgetProposalRates().get(0)).getActivityTypeCode();
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
     * @see org.kuali.kra.budget.service.BudgetRatesService#getBudgetPeriods()
     */
    public List<BudgetPeriod> getBudgetPeriods(){
        BudgetForm budgetForm = (BudgetForm) GlobalVariables.getKualiForm();
        BudgetDocument budgetDocument  = budgetForm.getBudgetDocument();
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
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
    private void updateRatesForEachPeriod(BudgetDocument budgetDocument) {
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();

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
        Map rateFilterMap = getRateFilterMapWithActivityTypeCode(budgetDocument);
        return (Collection<InstituteRate>) getAbstractInstituteRates(budgetDocument, InstituteRate.class, rateFilterMap);
    }

    /* get all institute rates - based on 
     * and unit number 
     * */
    @SuppressWarnings("unchecked")
    private Collection<InstituteLaRate> getInstituteLaRates(BudgetDocument budgetDocument) {
        return (Collection<InstituteLaRate>) getAbstractInstituteRates(budgetDocument, InstituteLaRate.class, getRateFilterMap(budgetDocument));
    }
    
    @SuppressWarnings("unchecked")
    private Collection getAbstractInstituteRates(BudgetDocument budgetDocument, Class rateType, Map rateFilterMap) {
        ProposalDevelopmentDocument proposal = budgetDocument.getProposal(); 
        String unitNumber = proposal.getOwnedByUnitNumber();                               
        Collection abstractInstituteRates = getFilteredInstituteRates(rateType, unitNumber, proposal.getOwnedByUnit(), rateFilterMap);        
        
        return abstractInstituteRates.size() > 0 ? abstractInstituteRates : new ArrayList();
    }

    private Map<String, String> getRateFilterMap(BudgetDocument budgetDocument) {        
        Map<String, String> rateFilterMap = new HashMap<String, String>();
        rateFilterMap.put(UNIT_NUMBER_KEY, budgetDocument.getProposal().getOwnedByUnitNumber());
        return rateFilterMap;
    }
    
    private Map<String, String> getRateFilterMapWithActivityTypeCode(BudgetDocument budgetDocument) {
        Map<String, String> rateFilterMap = getRateFilterMap(budgetDocument);
        rateFilterMap.put(ACTIVITY_TYPE_CODE_KEY, budgetDocument.getProposal().getActivityTypeCode());
        return rateFilterMap;
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

    /* get all rates within project start and end date range 
     *  
     * */
    @SuppressWarnings("unchecked")
    private void getRatesForProjectDates(BudgetDocument budgetDocument, Collection allRates, Collection filteredRates) {
        List<AbstractInstituteRate> dateFilteredRates = (List<AbstractInstituteRate>) filteredRates;
        List<AbstractInstituteRate> allAbstractInstituteRates = (List<AbstractInstituteRate>) allRates;
        for(AbstractInstituteRate rate : allAbstractInstituteRates) {
            Date rateStartDate = rate.getStartDate();
            if(rateStartDate.compareTo(budgetDocument.getStartDate()) >= 0 && rateStartDate.compareTo(budgetDocument.getEndDate()) <=0 ) {
                dateFilteredRates.add(rate);
            }
        }
    }
    
    /* get applicable rates before project start date  
     * get the latest 
     * */
    @SuppressWarnings("unchecked")
    private void getApplicableRates(BudgetDocument budgetDocument, Collection allRates, Collection filteredRates) {
        List<AbstractInstituteRate> allAbstractInstituteRates = (List<AbstractInstituteRate>) allRates;
        Map<String, AbstractInstituteRate> instRates = new HashMap<String, AbstractInstituteRate>();
        for(AbstractInstituteRate instituteRate : allAbstractInstituteRates) {
            Date rateStartDate = instituteRate.getStartDate();
            if(rateStartDate.before(budgetDocument.getStartDate())) {
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
    private void filterRates(BudgetDocument budgetDocument, Collection allAbstractInstituteRates, Collection filteredAbstractInstituteRates) {
        filteredAbstractInstituteRates.clear();
        getRatesForProjectDates(budgetDocument, allAbstractInstituteRates, filteredAbstractInstituteRates);
        getApplicableRates(budgetDocument, allAbstractInstituteRates, filteredAbstractInstituteRates);
    }
    
    private boolean isOutOfSync(BudgetDocument budgetDocument) {
        return isOutOfSync(budgetDocument.getInstituteRates(), budgetDocument.getBudgetProposalRates()) || 
                isOutOfSync(budgetDocument.getInstituteLaRates(), budgetDocument.getBudgetProposalLaRates());
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
        getBudgetRates(budgetDocument.getRateClassTypes(), budgetDocument, allInstituteRates);
    }
    
    /* get budget rates applicable for the proposal - based on activity type
     * and unit number 
     * */
    private void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument, Collection<InstituteRate> allInstituteRates) {
        List<InstituteRate> instituteRates = budgetDocument.getInstituteRates();        
        filterRates(budgetDocument, allInstituteRates, instituteRates);        
        List<BudgetProposalRate> budgetRates = budgetDocument.getBudgetProposalRates();
        
        syncBudgetRateCollections(rateClassTypes, budgetDocument, instituteRates, budgetRates);
        
        getBudgetLaRates(rateClassTypes, budgetDocument);
        checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
    }
    
    private void getBudgetLaRates(BudgetDocument budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        getBudgetLaRates(budgetDocument.getRateClassTypes(), budgetDocument, allInstituteLaRates);
    }

    private void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
        getBudgetLaRates(rateClassTypes, budgetDocument, new ArrayList<InstituteLaRate>(getInstituteLaRates(budgetDocument)));
    }
    
    /**
     * Get budget LA rates applicable for the proposal - based on unit number
     * @param rateClassTypes
     * @param budgetDocument
     * @param allInstituteLaRates
     */
    private void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        List<InstituteLaRate> instituteLaRates = budgetDocument.getInstituteLaRates();        
        filterRates(budgetDocument, allInstituteLaRates, instituteLaRates);        
        List<BudgetProposalLaRate> budgetRates = budgetDocument.getBudgetProposalLaRates();
        
        syncBudgetRateCollections(rateClassTypes, budgetDocument, instituteLaRates, budgetRates);   
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return _businessObjectService;
    }
    
    @SuppressWarnings("unchecked")
    private void syncBudgetRateCollections(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument,
                                                List abstractInstituteRates, List budgetRates) {

        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates; 
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) abstractInstituteRates;
        
        syncAllRateClasses(budgetDocument, instituteRates);
        syncAllRateClassTypes(budgetDocument, rateClassTypes, instituteRates);
        if(budgetRates.size() == 0) {
            syncAllBudgetRatesForInstituteRateType(budgetDocument, abstractBudgetRates, instituteRates);
        }
    }

    @SuppressWarnings("unchecked")
    private void syncAllBudgetRatesForInstituteRateType(BudgetDocument budgetDocument, List<AbstractBudgetRate> budgetRates, List<AbstractInstituteRate> instituteRates) {
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                budgetRates.add(generateBudgetRate(budgetDocument, abstractInstituteRate));
            }
        }
            
        updateRatesForEachPeriod(budgetDocument);
        Collections.sort(budgetRates);
    }

    @SuppressWarnings("unchecked")
    private void replaceRateClassesForRateClassType(String rateClassType, BudgetDocument budgetDocument, List rates) {
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) rates;
        List<RateClass> budgetRateClasses = budgetDocument.getRateClasses();        

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
        
        updateRatesForEachPeriod(budgetDocument);
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
    
    private void syncAllRateClasses(BudgetDocument budgetDocument, List<AbstractInstituteRate> instituteRates) {
        Map<String, RateClass> rateClassMap = new HashMap<String, RateClass>();
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                String rateClassCode = abstractInstituteRate.getRateClassCode();
                if(rateClassMap.get(rateClassCode) == null) {
                    rateClassMap.put(rateClassCode, abstractInstituteRate.getRateClass());
                }
            }
        }
        
        budgetDocument.getRateClasses().addAll(rateClassMap.values());
    }

    private void syncAllRateClassTypes(BudgetDocument budgetDocument, List<RateClassType> rateClassTypes, List<AbstractInstituteRate> instituteRates) {
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
        return new BudgetProposalRate(budgetDocument.getProposal().getOwnedByUnitNumber(), instituteRate);
    }
    
    private AbstractBudgetRate generateBudgetProposalLaRate(BudgetDocument budgetDocument, InstituteLaRate instituteLaRate) {
        return new BudgetProposalLaRate(budgetDocument.getProposal().getOwnedByUnitNumber(), instituteLaRate);
    }

    private AbstractBudgetRate generateBudgetRate(BudgetDocument budgetDocument, AbstractInstituteRate abstractInstituteRate) {
        AbstractBudgetRate abstractBudgetRate = (abstractInstituteRate instanceof InstituteRate) ?
                        generateBudgetProposalRate(budgetDocument, (InstituteRate) abstractInstituteRate) :
                        generateBudgetProposalLaRate(budgetDocument, (InstituteLaRate) abstractInstituteRate);
        return abstractBudgetRate;
    }
}
/* Pre-Refactoring Code
 
    public void syncAllBudgetRates(BudgetDocument budgetDocument) {
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        Collection<InstituteRate> allInstituteRates = getInstituteRates(budgetDocument);
        Collection<InstituteLaRate> allInstituteLaRates = getInstituteLaRates(budgetDocument);
        HashMap instRateMap = getInstituteRateMap(allInstituteRates);
        HashMap instLaRateMap = getInstituteLaRateMap(allInstituteLaRates);
        for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
            String instRateClassCode = budgetProposalRate.getRateClassCode();
            String instRateTypeCode = budgetProposalRate.getRateTypeCode();
            String onOffFlag = budgetProposalRate.getOnOffCampusFlag() ? Constants.ON_CAMUS_FLAG :Constants.OFF_CAMUS_FLAG;
            String startDate = budgetProposalRate.getStartDate().toString();
            String hKey = instRateClassCode + instRateTypeCode + startDate + onOffFlag;
            InstituteRate instituteRate = (InstituteRate)instRateMap.get(hKey);
            budgetProposalRate.setInstituteRate(instituteRate.getInstituteRate()); 
            budgetProposalRate.setApplicableRate(instituteRate.getInstituteRate()); 
        }
        
        for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
            String instRateClassCode = budgetProposalLaRate.getRateClassCode();
            String instRateTypeCode = budgetProposalLaRate.getRateTypeCode();
            String onOffFlag = budgetProposalLaRate.getOnOffCampusFlag() ? Constants.ON_CAMUS_FLAG :Constants.OFF_CAMUS_FLAG;
            String startDate = budgetProposalLaRate.getStartDate().toString();
            String hKey = instRateClassCode + instRateTypeCode + startDate + onOffFlag;
            InstituteLaRate instituteLaRate = (InstituteLaRate)instLaRateMap.get(hKey);
            budgetProposalLaRate.setInstituteRate(instituteLaRate.getInstituteRate()); 
            budgetProposalLaRate.setApplicableRate(instituteLaRate.getInstituteRate()); 
        }
    }
    
    public void resetAllBudgetRates(BudgetDocument budgetDocument) {
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
            budgetProposalRate.setApplicableRate(budgetProposalRate.getInstituteRate()); 
        }
        
        for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
            budgetProposalLaRate.setApplicableRate(budgetProposalLaRate.getInstituteRate()); 
        }
    }
    
    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }
    
    public void resetBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument) {
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<RateClass> rateClasses = budgetDocument.getRateClasses();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        for(RateClass rateClass: rateClasses) {
            if(rateClass.getRateClassType().equalsIgnoreCase(rateClassType)) {
                for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
                    if(budgetProposalRate.getRateClassCode().equalsIgnoreCase(rateClass.getRateClassCode())) {
                        budgetProposalRate.setApplicableRate(budgetProposalRate.getInstituteRate()); 
                    }
                }
                
                for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
                    if(budgetProposalLaRate.getRateClassCode().equalsIgnoreCase(rateClass.getRateClassCode())) {
                        budgetProposalLaRate.setApplicableRate(budgetProposalLaRate.getInstituteRate()); 
                    }
                }
            }
        }
    }
    
    public void viewLocation(String viewLocation, Integer budgetPeriod, BudgetDocument budgetDocument) {
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
            boolean displayRate = true;

            // check view location
            String onOffCampusFlag = budgetProposalRate.getOnOffCampusFlag() ? Constants.ON_CAMUS_FLAG : Constants.OFF_CAMUS_FLAG;
            if(viewLocation == null || (viewLocation.equalsIgnoreCase(onOffCampusFlag))) {
                displayRate = true; 
            }else { 
                displayRate  = false;
            }
            
            // check budget Period
            if(displayRate && budgetPeriod != null) {
                String trackAffectedPeriod = budgetProposalRate.getTrackAffectedPeriod();
                String formattedBudgetPeriod = PERIOD_SEARCH_SEPARATOR + budgetPeriod + PERIOD_SEARCH_SEPARATOR;
                if(trackAffectedPeriod == null || (trackAffectedPeriod.indexOf(formattedBudgetPeriod) < 0)) {
                    displayRate  = false;
                }
            }
            budgetProposalRate.setDisplayLocation(displayRate); 
        }
        
        // la rates
        for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
            boolean displayRate = true;

            // check view location
            String onOffCampusFlag = budgetProposalLaRate.getOnOffCampusFlag() ? Constants.ON_CAMUS_FLAG : Constants.OFF_CAMUS_FLAG;
            if(viewLocation == null || (viewLocation.equalsIgnoreCase(onOffCampusFlag))) {
                displayRate = true; 
            }else { 
                displayRate  = false;
            }
            
            // check budget Period 
            if(displayRate && budgetPeriod != null) {
                String trackAffectedPeriod = budgetProposalLaRate.getTrackAffectedPeriod();
                String formattedBudgetPeriod = PERIOD_SEARCH_SEPARATOR + budgetPeriod + PERIOD_SEARCH_SEPARATOR;
                if(trackAffectedPeriod == null || (trackAffectedPeriod.indexOf(formattedBudgetPeriod) < 0)) {
                    displayRate  = false;
                }
            }
            budgetProposalLaRate.setDisplayLocation(displayRate); 
        }
    
    }
    
    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument) {
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        List<RateClass> rateClasses = budgetDocument.getRateClasses();
        Collection<InstituteRate> allInstituteRates = getInstituteRates(budgetDocument);
        Collection<InstituteLaRate> allInstituteLaRates = getInstituteLaRates(budgetDocument);
        HashMap instRateMap = getInstituteRateMap(allInstituteRates);
        HashMap instLaRateMap = getInstituteLaRateMap(allInstituteLaRates);
        for(RateClass rateClass: rateClasses) {
            if(rateClass.getRateClassType().equalsIgnoreCase(rateClassType)) {
                for(BudgetProposalRate budgetProposalRate: budgetProposalRates) {
                    if(budgetProposalRate.getRateClassCode().equalsIgnoreCase(rateClass.getRateClassCode())) {
                        String hKey = generateFourPartMapKey(budgetProposalRate);
                        InstituteRate instituteRate = (InstituteRate)instRateMap.get(hKey);
                        budgetProposalRate.setInstituteRate(instituteRate.getInstituteRate()); 
                        budgetProposalRate.setApplicableRate(instituteRate.getInstituteRate()); 
                    }
                }
                // la rates
                for(BudgetProposalLaRate budgetProposalLaRate: budgetProposalLaRates) {
                    if(budgetProposalLaRate.getRateClassCode().equalsIgnoreCase(rateClass.getRateClassCode())) {
                        String instRateClassCode = budgetProposalLaRate.getRateClassCode();
                        String instRateTypeCode = budgetProposalLaRate.getRateTypeCode();
                        String onOffFlag = budgetProposalLaRate.getOnOffCampusFlag() ? Constants.ON_CAMUS_FLAG :Constants.OFF_CAMUS_FLAG;
                        String startDate = budgetProposalLaRate.getStartDate().toString();
                        String hKey = instRateClassCode + instRateTypeCode + startDate + onOffFlag;
                        InstituteLaRate instituteLaRate = (InstituteLaRate)instLaRateMap.get(hKey);
                        budgetProposalLaRate.setInstituteRate(instituteLaRate.getInstituteRate()); 
                        budgetProposalLaRate.setApplicableRate(instituteLaRate.getInstituteRate()); 
                    }
                }
            }
        }
    }

    private List<InstituteRate> getApplicableLaRates(BudgetDocument budgetDocument, Collection<InstituteRate> allInstituteRates) {
        List<InstituteRate> instituteRates = new ArrayList<InstituteRate>();
        HashMap instRates = new HashMap();
        for(InstituteLaRate instituteRate : allInstituteRates) {
            Date rateStartDate = instituteRate.getStartDate();
            if(rateStartDate.before(budgetDocument.getStartDate())) {
                String instRateClassCode = instituteRate.getRateClassCode();
                String instRateTypeCode = instituteRate.getRateTypeCode();
                String onOffFlag = getLocationFlagAsString(instituteRate.getOnOffCampusFlag());
                String hKey = instRateClassCode + instRateTypeCode + onOffFlag;
                InstituteRate instRate = (InstituteRate)instRates.get(hKey);
                if((instRate != null) && (instRate.getStartDate().compareTo(rateStartDate) <= 0)) {
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
        instituteRates.addAll(instRates.values());
        return instituteRates;
    }

    private List<InstituteLaRate> getApplicableLaRates(BudgetDocument budgetDocument, Collection<InstituteLaRate> allInstituteRates) {
        List<InstituteLaRate> instituteRates = new ArrayList();
        HashMap instRates = new HashMap();
        for(InstituteLaRate instituteRate : allInstituteRates) {
            Date rateStartDate = instituteRate.getStartDate();
            if(rateStartDate.before(budgetDocument.getStartDate())) {
                String instRateClassCode = instituteRate.getRateClassCode();
                String instRateTypeCode = instituteRate.getRateTypeCode();
                String onOffFlag = getLocationFlagAsString(instituteRate.getOnOffCampusFlag());
                String hKey = instRateClassCode + instRateTypeCode + onOffFlag;
                InstituteLaRate instRate = (InstituteLaRate)instRates.get(hKey);
                if((instRate != null) && (instRate.getStartDate().compareTo(rateStartDate) <= 0)) {
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
        instituteRates.addAll(instRates.values());
        return instituteRates;
    }

private void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument, Collection<InstituteRate> allInstituteRates) {
        String unitNumber = budgetDocument.getProposal().getOwnedByUnitNumber();
        String activityTypeCode = budgetDocument.getProposal().getActivityTypeCode();
        List<RateClass> rateClasses = budgetDocument.getRateClasses();
        List<InstituteRate> instituteRates = budgetDocument.getInstituteRates();
        List<BudgetProposalRate> budgetProposalRates = budgetDocument.getBudgetProposalRates();
        boolean isBudgetProposalRatesEmpty = true;
        Map<String, RateClass> rateClassMap = new HashMap<String, RateClass>();
        
        Map<String, RateClassType> existingRateClassTypeMap = new HashMap<String, RateClassType>();        
        for(RateClassType rateClassType: rateClassTypes) {
            existingRateClassTypeMap.put(rateClassType.getRateClassType(), rateClassType);
        }
        Map<String, RateClassType> rateClassTypeMap = new HashMap<String, RateClassType>();
        
        // check budget proposal rates exists. If not get institute rates to initialize
        // proposal rates.
        //
        if(budgetProposalRates.size() > 0) {
            isBudgetProposalRatesEmpty = false;
        }
        instituteRates.clear();
        instituteRates = getFilteredInstituteRates(budgetDocument, allInstituteRates);
        for(InstituteRate instituteRate: instituteRates) {
            String rateClassCode = instituteRate.getRateClassCode();
            if(instituteRate.getRateClass()==null){
                continue;
            }
            String rateClassType = instituteRate.getRateClass().getRateClassType();
            
            // Add applicable rate class
            if(rateClassMap.get(rateClassCode) == null) {
                rateClassMap.put(rateClassCode, instituteRate.getRateClass());
            }
            // Add applicable rate class types
            if(existingRateClassTypeMap.get(rateClassType) == null) {
                rateClassTypeMap.put(rateClassType, instituteRate.getRateClass().getRateClassTypeT());
            }
            if(isBudgetProposalRatesEmpty) {
                // initialize budget proposal rates
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
                budgetProposalRate.setBudgetVersionNumber(budgetDocument.getBudgetVersionNumber());
                budgetProposalRate.setProposalNumber(budgetDocument.getProposalNumber());
                budgetProposalRate.setOldApplicableRate(instituteRate.getInstituteRate());
                budgetProposalRates.add(budgetProposalRate);                
            }
        }
        rateClasses.addAll(rateClassMap.values());          
        rateClassTypes.addAll(rateClassTypeMap.values());        

        updateRatesForEachPeriod(budgetDocument);
        Collections.sort(budgetDocument.getBudgetProposalRates());
        getBudgetLaRates(rateClassTypes, budgetDocument);
        checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
    }
    
    public void getBudgetLaRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument, List<InstituteLaRate> allInstituteLaRates) {
        List<InstituteLaRate> instituteLaRates = budgetDocument.getInstituteLaRates();
        String unitNumber = budgetDocument.getProposal().getOwnedByUnitNumber();
        List<RateClass> rateClasses = budgetDocument.getRateClasses();
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        boolean isBudgetProposalRatesEmpty = true;
        Map<String, RateClass> rateClassMap = new HashMap<String, RateClass>();
        
        Map<String, RateClassType> existingRateClassTypeMap = new HashMap<String, RateClassType>();        
        for(RateClassType rateClassType: rateClassTypes) {
            existingRateClassTypeMap.put(rateClassType.getRateClassType(), rateClassType);
        }
        Map<String, RateClassType> rateClassTypeMap = new HashMap<String, RateClassType>();
        
        // check budget proposal LA rates exists. If not get institute rates to initialize
        // proposal rates.
        //
        if(budgetProposalLaRates.size() > 0) {
            isBudgetProposalRatesEmpty = false;
        }
        instituteLaRates.clear();
        instituteLaRates = getFilteredInstituteLaRates(budgetDocument, allInstituteLaRates);
        for(InstituteLaRate instituteRate: instituteLaRates) {
            String rateClassCode = instituteRate.getRateClassCode();
            String rateClassType = instituteRate.getRateClass().getRateClassType();
            // Add applicable rate class
            if(rateClassMap.get(rateClassCode) == null) {
                rateClassMap.put(rateClassCode, instituteRate.getRateClass());
            }
            // Add applicable rate class types
            if(existingRateClassTypeMap.get(rateClassType) == null) {
                rateClassTypeMap.put(rateClassType, instituteRate.getRateClass().getRateClassTypeT());
            }
            if(isBudgetProposalRatesEmpty) {
                // initialize budget proposal rates
                BudgetProposalLaRate budgetProposalLaRate = new BudgetProposalLaRate();
                budgetProposalLaRate.setApplicableRate(instituteRate.getInstituteRate());
                budgetProposalLaRate.setFiscalYear(instituteRate.getFiscalYear());
                budgetProposalLaRate.setInstituteRate(instituteRate.getInstituteRate());
                budgetProposalLaRate.setOnOffCampusFlag(instituteRate.getOnOffCampusFlag());
                budgetProposalLaRate.setRateClass(instituteRate.getRateClass());
                budgetProposalLaRate.setRateClassCode(rateClassCode);
                budgetProposalLaRate.setRateType(instituteRate.getRateType());
                budgetProposalLaRate.setRateTypeCode(instituteRate.getRateTypeCode());
                budgetProposalLaRate.setStartDate(instituteRate.getStartDate());
                budgetProposalLaRate.setUnitNumber(unitNumber);
                budgetProposalLaRate.setOldApplicableRate(instituteRate.getInstituteRate());
                budgetProposalLaRate.setBudgetVersionNumber(budgetDocument.getBudgetVersionNumber());
                budgetProposalLaRate.setProposalNumber(budgetDocument.getProposalNumber());
                budgetProposalLaRates.add(budgetProposalLaRate);
            }
        }
        rateClasses.addAll(rateClassMap.values());
        rateClassTypes.addAll(rateClassTypeMap.values());
        updateRatesForEachPeriod(budgetDocument);
        Collections.sort(budgetDocument.getBudgetProposalLaRates());
        
    }
    
    private List<InstituteLaRate> getApplicableLaRates(BudgetDocument budgetDocument, Collection<InstituteLaRate> allInstituteRates, List filteredRates) {
        List<InstituteLaRate> instituteRates = new ArrayList();
        HashMap instRates = new HashMap();
        for(InstituteLaRate instituteRate : allInstituteRates) {
            Date rateStartDate = instituteRate.getStartDate();
            if(rateStartDate.before(budgetDocument.getStartDate())) {
                String instRateClassCode = instituteRate.getRateClassCode();
                String instRateTypeCode = instituteRate.getRateTypeCode();
                String onOffFlag = getLocationFlagAsString(instituteRate.getOnOffCampusFlag());
                String hKey = instRateClassCode + instRateTypeCode + onOffFlag;
                InstituteLaRate instRate = (InstituteLaRate)instRates.get(hKey);
                if((instRate != null) && (instRate.getStartDate().compareTo(rateStartDate) <= 0)) {
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
        instituteRates.addAll(instRates.values());
        return instituteRates;
    }
    
    //get all institute rates - based on activity type 
    //and unit number 
    //
    private Collection<InstituteRate> getInstituteRates(BudgetDocument budgetDocument) {
        String unitNumber = budgetDocument.getProposal().getOwnedByUnitNumber();
        String activityTypeCode = budgetDocument.getProposal().getActivityTypeCode();
        Unit currentUnit = budgetDocument.getProposal().getOwnedByUnit();
        Collection<InstituteRate> allInstituteRates = new ArrayList();
        do {
            Map rateFilterMap = new HashMap();
            rateFilterMap.put(UNIT_NUMBER_KEY, unitNumber);
            rateFilterMap.put(ACTIVITY_TYPE_CODE_KEY, activityTypeCode);
            allInstituteRates = getBusinessObjectService().findMatching(InstituteRate.class, rateFilterMap);
            currentUnit = currentUnit.getParentUnit();
            if(currentUnit != null) {
                unitNumber = currentUnit.getUnitNumber();
            }
        }while(allInstituteRates.size() == 0);

        return allInstituteRates;
    }

    // get all institute rates - based on 
    // and unit number 
    //
    @SuppressWarnings("unchecked")
    private Collection<InstituteLaRate> getInstituteLaRates(BudgetDocument budgetDocument) {
        String unitNumber = budgetDocument.getProposal().getOwnedByUnitNumber();
        Unit currentUnit = budgetDocument.getProposal().getOwnedByUnit();
        
        Collection<InstituteLaRate> allInstituteLaRates = new ArrayList<InstituteLaRate>();
        do {
            Map rateFilterMap = new HashMap();
            rateFilterMap.put(UNIT_NUMBER_KEY, unitNumber);
            allInstituteLaRates = getBusinessObjectService().findMatching(InstituteLaRate.class, rateFilterMap);
            currentUnit = currentUnit.getParentUnit();
            if(currentUnit != null) {
                unitNumber = currentUnit.getUnitNumber();
            }
        }while(allInstituteLaRates.size() == 0);
        return allInstituteLaRates;
    }
*/