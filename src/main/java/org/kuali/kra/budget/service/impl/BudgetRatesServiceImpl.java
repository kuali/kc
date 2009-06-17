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
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.AbstractBudgetRate;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetRatesService;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class BudgetRatesServiceImpl implements BudgetRatesService {
    private static final String SPACE = " ";
    public static final String UNIT_NUMBER_KEY = "unitNumber";
    public static final String ACTIVITY_TYPE_CODE_KEY = "activityTypeCode";
    public static final String BUDGET_VERSION_NUMBER_KEY = "budgetVersionNumber";
    
    private BusinessObjectService _businessObjectService;
    private static final String PERIOD_SEARCH_SEPARATOR = "|";
    private static final String PERIOD_DISPLAY_SEPARATOR = ",";
    private static final Log LOG = LogFactory.getLog(BudgetRatesServiceImpl.class);
    private static final String BUDGET_RATE_AUDIT_WARNING_KEY = "budgetRateAuditWarnings";

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
            
            // since different rate schedules can change UnrecoveredFandA, clear here
            budgetDocument.getBudgetUnrecoveredFandAs().clear();
            
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
            populateInstituteRates(budgetDocument);
            
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
            if(budgetDocument.getProposal().getActivityType()!= null){
                return budgetDocument.getProposal().getActivityType().getDescription().concat(SPACE);
            }
            else
            {
                return "";
            }
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
        ProposalDevelopmentDocument proposal = budgetDocument.getProposal(); 
        String unitNumber = proposal.getOwnedByUnitNumber();                               
        Collection abstractInstituteRates = getFilteredInstituteLaRates(InstituteLaRate.class, unitNumber, proposal.getOwnedByUnit(), getRateFilterMap(budgetDocument));
        abstractInstituteRates = abstractInstituteRates.size() > 0 ? abstractInstituteRates : new ArrayList();
        return (Collection<InstituteLaRate>)abstractInstituteRates ;
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
    private Date getRateEffectiveStartDate(BudgetDocument budgetDocument, AbstractInstituteRate rate, Date personEffectiveDate) {
        Date effectiveDate = budgetDocument.getStartDate();
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
    private Date getBudgetPersonSalaryEffectiveDate(BudgetDocument budgetDocument) {
        Map queryMap = new HashMap();
        queryMap.put("proposalNumber", budgetDocument.getProposalNumber());
        queryMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
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
    private void getRatesForProjectDates(BudgetDocument budgetDocument, Collection allRates, Collection filteredRates, Date personSalaryEffectiveDate) {
        List<AbstractInstituteRate> dateFilteredRates = (List<AbstractInstituteRate>) filteredRates;
        List<AbstractInstituteRate> allAbstractInstituteRates = (List<AbstractInstituteRate>) allRates;
        for(AbstractInstituteRate rate : allAbstractInstituteRates) {
            Date rateStartDate = rate.getStartDate();
            Date rateEffectiveDate = getRateEffectiveStartDate(budgetDocument, rate, personSalaryEffectiveDate);
            
            if(rateStartDate.compareTo(rateEffectiveDate) >= 0 && rateStartDate.compareTo(budgetDocument.getEndDate()) <=0 ) {
                dateFilteredRates.add(rate);
            }
        }
    }
    
    /* get applicable rates before project start date  
     * get the latest 
     * */
    @SuppressWarnings("unchecked")
    private void getApplicableRates(BudgetDocument budgetDocument, Collection allRates, Collection filteredRates, Date personSalaryEffectiveDate) {
        List<AbstractInstituteRate> allAbstractInstituteRates = (List<AbstractInstituteRate>) allRates;
        Map<String, AbstractInstituteRate> instRates = new HashMap<String, AbstractInstituteRate>();
        for(AbstractInstituteRate instituteRate : allAbstractInstituteRates) {
            Date rateStartDate = instituteRate.getStartDate();
            Date rateEffectiveDate = getRateEffectiveStartDate(budgetDocument, instituteRate, personSalaryEffectiveDate);
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
    private void filterRates(BudgetDocument budgetDocument, Collection allAbstractInstituteRates, Collection filteredAbstractInstituteRates) {
        filteredAbstractInstituteRates.clear();
        Date personSalaryEffectiveDate = getBudgetPersonSalaryEffectiveDate(budgetDocument);
        getRatesForProjectDates(budgetDocument, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
        getApplicableRates(budgetDocument, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
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
    
    public BusinessObjectService getBusinessObjectService() {
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
  public void syncBudgetRateCollectionsToExistingRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument) {
      syncAllRateClasses(budgetDocument, (List) budgetDocument.getBudgetProposalRates());
      syncAllRateClassTypes(budgetDocument, rateClassTypes, (List) budgetDocument.getBudgetProposalRates());
      
      syncAllRateClasses(budgetDocument, (List) budgetDocument.getBudgetProposalLaRates());
      syncAllRateClassTypes(budgetDocument, rateClassTypes, (List) budgetDocument.getBudgetProposalLaRates());

      checkActivityPrefixForRateClassTypes(rateClassTypes, budgetDocument);
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
        List instituteRates = (List) getInstituteRates(budgetDocument);
        filterRates(budgetDocument, instituteRates, budgetDocument.getInstituteRates()); 
        List instituteLaRates = (List) getInstituteLaRates(budgetDocument);
        filterRates(budgetDocument, instituteLaRates, budgetDocument.getInstituteLaRates()); 
    }
    
    public boolean isOutOfSyncForRateAudit(BudgetDocument budgetDocument) {
        populateInstituteRates(budgetDocument);

        return isOutOfSyncForRateAudit(budgetDocument.getInstituteRates(), budgetDocument.getBudgetProposalRates()) || 
                isOutOfSyncForRateAudit(budgetDocument.getInstituteLaRates(), budgetDocument.getBudgetProposalLaRates());
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
