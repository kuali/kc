/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.impl.rate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.query.operator.*;
import org.kuali.coeus.common.budget.framework.rate.*;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.budget.framework.rate.AbstractInstituteRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteLaRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.util.*;

import javax.persistence.EntityManager;

public abstract class BudgetRatesServiceImpl<T extends BudgetParent> implements BudgetRatesService<T> {
    private static final String SPACE = " ";
    public static final String UNIT_NUMBER_KEY = "unitNumber";
    public static final String ACTIVITY_TYPE_CODE_KEY = "activityTypeCode";

    private static final String PERIOD_SEARCH_SEPARATOR = "|";
    private static final String PERIOD_DISPLAY_SEPARATOR = ",";
    private static final Log LOG = LogFactory.getLog(BudgetRatesServiceImpl.class);

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("fiscalYearMonthService")
    private FiscalYearMonthService fiscalYearMonthService;
    
    @Autowired
    @Qualifier("kcEntityManager")
    private EntityManager entityManager;    

    @Override
    public void resetAllBudgetRates(Budget budget) {
        resetAbstractBudgetApplicableRatesToInstituteRates(budget.getBudgetRates());
        resetAbstractBudgetApplicableRatesToInstituteRates(budget.getBudgetLaRates());
    }
    
    /**
     * reset budget rates for a panel
     * each panel is based on rate class type 
     *
     */
    @Override
    public void resetBudgetRatesForRateClassType(String rateClassType, Budget budget) {
        List<RateClass> rateClasses = budget.getRateClasses();
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budget.getBudgetRates());
        resetBudgetRatesForRateClassType(rateClasses, rateClassType, budget.getBudgetLaRates());        
    }

    @Override
    public void syncAllBudgetRates(Budget budget) {
        List<InstituteRate> allInstituteRates = new ArrayList<InstituteRate>(getInstituteRates(budget));
        List<InstituteLaRate> allInstituteLaRates = new ArrayList<InstituteLaRate>(getInstituteLaRates(budget));
        
        if(isOutOfSync(budget)) {
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budget.getBudgetRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budget.getBudgetLaRates());
            
            budget.getBudgetRates().clear();
            budget.getBudgetLaRates().clear();
            budget.getRateClasses().clear();
            
            // since different rate schedules can change UnrecoveredFandA, clear here
            budget.getBudgetUnrecoveredFandAs().clear();
            
            getBudgetRates(budget, allInstituteRates);
            getBudgetLaRates(budget, allInstituteLaRates);
            
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
     */
    @Override
    public void viewLocation(String viewLocation, Integer budgetPeriod, Budget budget) {
        viewLocation(viewLocation, budgetPeriod, budget.getBudgetRates());
        viewLocation(viewLocation, budgetPeriod, budget.getBudgetLaRates());        
    }
    
    /**
     * 
     * Does nothing. Placeholder for Award Budget
     */
    @Override
    public void syncParentDocumentRates(Budget budget) {
    }
    
    /* sync budget rates for a panel
     * each panel is based on rate class type 
     */
    @Override
    public void syncBudgetRatesForRateClassType(String rateClassType, Budget budget) {
            populateInstituteRates(budget);
            
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalRates = mapRatesToKeys(budget.getBudgetRates()); 
            Map<String, AbstractInstituteRate> mapOfExistingBudgetProposalLaRates = mapRatesToKeys(budget.getBudgetLaRates());
            replaceRateClassesForRateClassType(rateClassType, budget, budget.getInstituteRates());
            replaceRateClassesForRateClassType(rateClassType, budget, budget.getInstituteLaRates());
            replaceBudgetRatesForRateClassType(rateClassType, budget, budget.getBudgetRates(), budget.getInstituteRates());
            replaceBudgetRatesForRateClassType(rateClassType, budget, budget.getBudgetLaRates(), budget.getInstituteLaRates());
            syncVersionNumber(mapOfExistingBudgetProposalRates, budget.getBudgetRates());
            syncVersionNumber(mapOfExistingBudgetProposalLaRates, budget.getBudgetLaRates());
    }

    @Override
    public void getBudgetRates(List<RateClassType> rateClassTypes, Budget budget) {
        getBudgetRates(rateClassTypes, budget, getInstituteRates(budget));
    }

    /* verify and add activity type prefix if required for rate class type description
     * 
     * */
    protected void checkActivityPrefixForRateClassTypes(List<RateClassType> rateClassTypes, Budget budget) {
        String activityTypeDescription = getActivityTypeDescription(budget);
        List<BudgetRate> budgetRates = budget.getBudgetRates();
        List<BudgetLaRate> budgetLaRates = budget.getBudgetLaRates();
        for(RateClassType rateClassType : rateClassTypes) {
            if(rateClassType.getPrefixActivityType()) {
            	//making changes to the DO here, need to detach to make sure these changes aren't persisted.
            	entityManager.detach(rateClassType);
                String newRateClassTypeDescription = activityTypeDescription.concat(rateClassType.getDescription()); 
                rateClassType.setDescription(newRateClassTypeDescription);
                rateClassType.setPrefixActivityType(false);
                /* set in proposal rates reference */
                for(BudgetRate budgetRate: budgetRates) {
                    RateClassType BPRateClassType = budgetRate.getRateClass().getRateClassType();
                    if(rateClassType.getCode().equalsIgnoreCase(BPRateClassType.getCode())) {
                        BPRateClassType.setDescription(newRateClassTypeDescription);
                    }
                }
                /* set in proposal LA rates reference */
                for(BudgetLaRate budgetLaRate: budgetLaRates) {
                    RateClassType BPLRateClassType = budgetLaRate.getRateClass().getRateClassType();
                    if(rateClassType.getCode().equalsIgnoreCase(BPLRateClassType.getCode())) {
                        BPLRateClassType.setDescription(newRateClassTypeDescription);
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    protected String getActivityTypeDescription(Budget budget) {
        BudgetParent budgetParent = budget.getBudgetParent();

        if (budget.isRateSynced() || !checkActivityTypeChange(budget)) {
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
                activityTypeCode = budget.getBudgetRates().get(0).getActivityTypeCode();
            }

            if (activityTypeCode != null) {
                Map pkMap = new HashMap();
                pkMap.put("code",activityTypeCode);
                ActivityType activityType = getBusinessObjectService().findByPrimaryKey(ActivityType.class, pkMap);
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


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
 
    /**
     * Build rates for each period.
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
                    String currBudgetPeriod = budgetRate.getTrackAffectedPeriod();
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
                    String currBudgetPeriod = budgetLaRate.getTrackAffectedPeriod();
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
    protected Collection<InstituteRate> getInstituteRates(Budget budget) {
        //get first unit number in hierarchy with rates then select appropriate rates
        Unit firstUnit = findFirstUnitWithRates(budget.getBudgetParent().getUnit(), InstituteRate.class);
        if (firstUnit == null) {
            return new ArrayList();
        }
        Collection abstractRates = getActiveInstituteRates(InstituteRate.class, firstUnit, budget.getBudgetParent().getActivityTypeCode());
        return (Collection<InstituteRate>)abstractRates;
    }
    
    @SuppressWarnings("unchecked")
    protected Unit findFirstUnitWithRates(Unit leadUnit, Class rateType) {
        Unit currentUnit = leadUnit;
        Map<String, String> currentSearchMap = new HashMap<String, String>();
        while (currentUnit != null) {
            currentSearchMap.put(UNIT_NUMBER_KEY, currentUnit.getUnitNumber());
            Collection currentRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, currentSearchMap));
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
    protected Collection<InstituteLaRate> getInstituteLaRates(Budget budget) {
        BudgetParent budgetParent = budget.getBudgetParent(); 
        String unitNumber = budgetParent.getUnitNumber();                               
        Collection abstractInstituteRates = getFilteredInstituteLaRates(InstituteLaRate.class, getRateFilterMap(budget));
        abstractInstituteRates = abstractInstituteRates.size() > 0 ? abstractInstituteRates : new ArrayList();
        return (Collection<InstituteLaRate>)abstractInstituteRates ;
    }


    @SuppressWarnings("unchecked")
    protected BudgetParentDocument getBudgetParentDocument(Budget budget) {
        return budget.getBudgetParent().getDocument();
    }

    protected Map<String, String> getRateFilterMap(Budget budget) {        
        BudgetParent budgetParent = budget.getBudgetParent(); 
        Map<String, String> rateFilterMap = new HashMap<String, String>();
        rateFilterMap.put(UNIT_NUMBER_KEY, budgetParent.getUnitNumber());
        return rateFilterMap;
    }

    @SuppressWarnings("unchecked")
    protected Collection getFilteredInstituteLaRates(Class rateType, Map<String, String> rateFilterMap) {
        Collection abstractInstituteRates;
        abstractInstituteRates = filterForActiveRatesOnly(getBusinessObjectService().findMatching(rateType, rateFilterMap));
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

    /* Rate effective date is between project start and end dates.
     * But if budget persons are defined and the earliest salary effective
     * date is prior to project start date, Inflation rates are retrieved from
     * that date on (salary effective date).  
     * This date is used to fetch inflation rates  
     * 
     * */
    protected Date getRateEffectiveStartDate(Budget budget, AbstractInstituteRate rate, Date personEffectiveDate) {
        Date effectiveDate = budget.getStartDate();
        if(rate.getRateClass().getRateClassTypeCode().equalsIgnoreCase(Constants.RATE_CLASS_TYPE_FOR_INFLATION)
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
    
    /* get applicable rates before project start date  
     * get the latest 
     * */
    protected void filterInstituteRates(Budget budget, Collection<AbstractInstituteRate> allRates, Collection<AbstractInstituteRate> filteredRates, Date personSalaryEffectiveDate) {
        List<String> addedList = new ArrayList<String>();
        QueryList<AbstractInstituteRate> instituteRates = new QueryList<AbstractInstituteRate>(allRates);
        for (AbstractInstituteRate instituteRate : allRates) {
            String hKey = generateThreePartKey(instituteRate);
            if(!addedList.contains(hKey)){
                addedList.add(hKey);
                Equals eqRateClassCode = new Equals("rateClassCode",instituteRate.getRateClassCode());
                Equals eqRateTypeCode = new Equals("rateTypeCode",instituteRate.getRateTypeCode());
                Equals eqCampusFlag = new Equals("onOffCampusFlag",instituteRate.getOnOffCampusFlag());
                And rateClassAndRateType = new And(eqRateClassCode,eqRateTypeCode);
                And rcRtCampus = new And(rateClassAndRateType,eqCampusFlag);
                QueryList<AbstractInstituteRate> tempFilteredRates = instituteRates.filter(rcRtCampus);
                Date effectiveStartDate = getRateEffectiveStartDate(budget, instituteRate, personSalaryEffectiveDate);
                Equals eqEndDate = new Equals("startDate",budget.getEndDate());
                LesserThan ltEndDate = new LesserThan("startDate",budget.getEndDate());
                Or ltEqEndDate = new Or(eqEndDate,ltEndDate);
                tempFilteredRates = tempFilteredRates.filter(ltEqEndDate);
                GreaterThan gtStartDate = new GreaterThan("startDate",effectiveStartDate);
                QueryList<AbstractInstituteRate> rateWithinProjectPeriod = tempFilteredRates.filter(gtStartDate);
                filteredRates.addAll(rateWithinProjectPeriod);
                tempFilteredRates.removeAll(rateWithinProjectPeriod);
                if(!tempFilteredRates.isEmpty()){
                    tempFilteredRates.sort("startDate",false);
                    filteredRates.add(tempFilteredRates.get(0));
                }
            }
            
        }
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
        filterInstituteRates(budget, allAbstractInstituteRates, filteredAbstractInstituteRates, personSalaryEffectiveDate);
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
            abstractBudgetRate.setApplicableRate(abstractBudgetRate.getExternalApplicableRate()); 
        }
    }
    
    @SuppressWarnings("unchecked")
    protected void resetBudgetRatesForRateClassType(List<RateClass> rateClasses, String rateClassType, List budgetRates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        
        for(RateClass rateClass: rateClasses) {
            if(rateClass.getRateClassTypeCode().equalsIgnoreCase(rateClassType)) {
                for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
                    if(abstractBudgetRate.getRateClassCode().equalsIgnoreCase(rateClass.getCode())) {
                        abstractBudgetRate.setApplicableRate(abstractBudgetRate.getExternalApplicableRate()); 
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
            abstractBudgetRate.setApplicableRate(abstractInstituteRate.getExternalApplicableRate()); 
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
    
    protected Map<String, RateClassType> populateExistingRateClassTypeMap(List<RateClassType> rateClassTypes) {
        Map<String, RateClassType> existingRateClassTypeMap = new HashMap<String, RateClassType>();        
        for(RateClassType rateClassType: rateClassTypes) {
            existingRateClassTypeMap.put(rateClassType.getCode(), rateClassType);
        }
        return existingRateClassTypeMap;
    }
    
    protected void getBudgetRates(Budget budget, Collection<InstituteRate> allInstituteRates) {
        getBudgetRates(budget.getRateClassTypes(), budget, allInstituteRates);
    }

    /* get budget rates applicable for the proposal - based on activity type
     * and unit number 
     * */
    protected void getBudgetRates(List<RateClassType> rateClassTypes, Budget budget, Collection<InstituteRate> allInstituteRates) {
        List<InstituteRate> instituteRates = budget.getInstituteRates();        
        filterRates(budget, allInstituteRates, instituteRates);        
        List<BudgetRate> budgetRates = budget.getBudgetRates();
        
        syncBudgetRateCollections(rateClassTypes, budget, instituteRates, budgetRates);
        
        getBudgetLaRates(rateClassTypes, budget);
        checkActivityPrefixForRateClassTypes(rateClassTypes, budget);
    }
    
    protected void getBudgetLaRates(Budget budget, List<InstituteLaRate> allInstituteLaRates) {
        getBudgetLaRates(budget.getRateClassTypes(), budget, allInstituteLaRates);
    }

    protected void getBudgetLaRates(List<RateClassType> rateClassTypes, Budget budget) {
        getBudgetLaRates(rateClassTypes, budget, new ArrayList<InstituteLaRate>(getInstituteLaRates(budget)));
    }
    
    /**
     * Get budget LA rates applicable for the proposal - based on unit number
     */
    protected void getBudgetLaRates(List<RateClassType> rateClassTypes, Budget budget, List<InstituteLaRate> allInstituteLaRates) {
        List<InstituteLaRate> instituteLaRates = budget.getInstituteLaRates();        
        filterRates(budget, allInstituteLaRates, instituteLaRates);        
        List<BudgetLaRate> budgetRates = budget.getBudgetLaRates();
        
        syncBudgetRateCollections(rateClassTypes, budget, instituteLaRates, budgetRates);   
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    @SuppressWarnings("unchecked")
    protected void syncBudgetRateCollections(List<RateClassType> rateClassTypes, Budget budget,
                                                List abstractInstituteRates, List budgetRates) {

        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates; 
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) abstractInstituteRates;
        
        syncAllRateClasses(budget, instituteRates);
        syncAllRateClassTypes(budget, rateClassTypes, instituteRates);
        if(budgetRates.size() == 0) {
            syncAllBudgetRatesForInstituteRateType(budget, abstractBudgetRates, instituteRates);
        }
    }
    
  @SuppressWarnings("unchecked")
  @Override
  public void syncBudgetRateCollectionsToExistingRates(List<RateClassType> rateClassTypes, Budget budget) {

      syncAllRateClasses(budget, (List) budget.getBudgetRates());
      syncAllRateClassTypes(budget, rateClassTypes, (List) budget.getBudgetRates());
      
      syncAllRateClasses(budget, (List) budget.getBudgetLaRates());
      syncAllRateClassTypes(budget, rateClassTypes, (List) budget.getBudgetLaRates());

      checkActivityPrefixForRateClassTypes(rateClassTypes, budget);
  }

    protected void syncAllBudgetRatesForInstituteRateType(Budget budget, List<AbstractBudgetRate> budgetRates, List<AbstractInstituteRate> instituteRates) {
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateClass() != null) {
                budgetRates.add(generateBudgetRate(budget, abstractInstituteRate));
            }
        }
        
        updateRatesForEachPeriod(budget);
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
            if(rateClassType.equals(rateClass.getRateClassTypeCode())) {
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
                if(rateClass.getRateClassTypeCode().equals(rateClassType) && mapOfMatchingRateClasses.get(rateClass.getCode()) == null) {
                    mapOfMatchingRateClasses.put(rateClass.getCode(), rateClass);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    protected void replaceBudgetRatesForRateClassType(String rateClassType, Budget budget, List existingBudgetRates, List rates) {
        List<AbstractInstituteRate> instituteRates = (List<AbstractInstituteRate>) rates; 
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) existingBudgetRates;
        
        Map<String, AbstractBudgetRate> existingBudgetRateMap = preservePersistedBudgetRatesForRateClassType(rateClassType, abstractBudgetRates);        
        removeRegisteredBudgetRatesForRateClassType(rateClassType, abstractBudgetRates);
        
        Map<String, AbstractBudgetRate> newBudgetRateMap = generateNewAndUpdatedBudgetRates(rateClassType, budget, instituteRates, existingBudgetRateMap);
        
        registerNewAndUpdatedBudgetRates(abstractBudgetRates, newBudgetRateMap);
        
        updateRatesForEachPeriod(budget);
        Collections.sort(abstractBudgetRates);
    }

    protected void registerNewAndUpdatedBudgetRates(List<AbstractBudgetRate> abstractBudgetRates, Map<String, AbstractBudgetRate> newBudgetRateMap) {
        abstractBudgetRates.addAll(newBudgetRateMap.values());
    }

    protected Map<String, AbstractBudgetRate> generateNewAndUpdatedBudgetRates(String rateClassType, Budget budget, 
                                                                             List<AbstractInstituteRate> instituteRates,
                                                                             Map<String, AbstractBudgetRate> existingBudgetRateMap) {
        Map<String, AbstractBudgetRate> newBudgetRateMap = new HashMap<String, AbstractBudgetRate>(); 
        
        for(AbstractInstituteRate abstractInstituteRate: instituteRates) {
            if(abstractInstituteRate.getRateType() != null) {
                RateClass rateClass = abstractInstituteRate.getRateType().getRateClass();                
                if(rateClassType.equals(rateClass.getRateClassTypeCode())) {
                    AbstractBudgetRate newBudgetRate = generateBudgetRate(budget, abstractInstituteRate);
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
            if(rateClassType.equals(budgetRate.getRateClass().getRateClassTypeCode())) {
                iter.remove();
            }
        }
    }

    protected Map<String, AbstractBudgetRate> preservePersistedBudgetRatesForRateClassType(String rateClassType, List<AbstractBudgetRate> abstractBudgetRates) {
        Map<String, AbstractBudgetRate> existingBudgetRateMap = new HashMap<String, AbstractBudgetRate>();        
        for(AbstractBudgetRate abstractBudgetRate: abstractBudgetRates) {
            if(rateClassType.equals(abstractBudgetRate.getRateClass().getRateClassTypeCode())) {
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
                String rateClassType = abstractInstituteRate.getRateClass().getRateClassTypeCode();
                if(existingRateClassTypeMap.get(rateClassType) == null) {
                    rateClassTypeMap.put(rateClassType, abstractInstituteRate.getRateClass().getRateClassType());
                }
            }
        }
        
        rateClassTypes.addAll(rateClassTypeMap.values());
    }

    protected AbstractBudgetRate generateBudgetProposalRate(Budget budget, InstituteRate instituteRate) {
        BudgetParent budgetParent = budget.getBudgetParent();
        return new BudgetRate(budgetParent.getUnitNumber(), instituteRate);
    }
    
    protected AbstractBudgetRate generateBudgetProposalLaRate(Budget budget, InstituteLaRate instituteLaRate) {
        BudgetParent budgetParent = budget.getBudgetParent();
        return new BudgetLaRate(budgetParent.getUnitNumber(), instituteLaRate);
    }

    protected AbstractBudgetRate generateBudgetRate(Budget budget, AbstractInstituteRate abstractInstituteRate) {
        AbstractBudgetRate abstractBudgetRate = (abstractInstituteRate instanceof InstituteRate) ?
                        generateBudgetProposalRate(budget, (InstituteRate) abstractInstituteRate) :
                        generateBudgetProposalLaRate(budget, (InstituteLaRate) abstractInstituteRate);
        abstractBudgetRate.setBudgetId(budget.getBudgetId());                
        abstractBudgetRate.setBudget(budget);
        return abstractBudgetRate;
    }

    /**
     * Searches for persisted {@link RateClass} instances based on the given <code>rateClassType</code>. Uses the {@link BusinessObjectService}
     * to grab appropriate {@link RateClass} instances since {@link RateClass} is a {@link BusinessObject}
     *
     * @param rateClassType to use for retrieving {@link RateClass} instances
     * @return a List of {@link RateClass} instances
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<RateClass> getBudgetRateClasses(String rateClassType) {
        return getDataObjectService().findMatching(RateClass.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("rateClassTypeCode", rateClassType)).build()).getResults();
    }

    /**
     * Retrieves {@link RateClass} instances as a {@link Map} keyed from the <code>rateTypeCode</code>. This makes it easy for
     * classes (particularly in the UI) to grab {@link RateClass} information via rateTypeCode
     *
     * @param rateClassType to use for {@link RateClass} instances to be retrieved
     * @return a {@link Map} keyed on rateTypeCode containing {@link RateClass} instances
     */
    @Override
    public Map<String, RateClass> getBudgetRateClassMap(String rateClassType) {
        Map<String, RateClass> retval = new HashMap<String, RateClass>();

        for (RateClass rateClass : getBudgetRateClasses(rateClassType)) {
            retval.put(rateClass.getCode(), rateClass);
        }

        return retval;
    }
    
    @SuppressWarnings("unchecked")
    public void populateInstituteRates(Budget budget) {
        List instituteRates = (List) getInstituteRates(budget);
        filterRates(budget, instituteRates, budget.getInstituteRates()); 
        List instituteLaRates = (List) getInstituteLaRates(budget);
        filterRates(budget, instituteLaRates, budget.getInstituteLaRates()); 
    }

    /**
     * By default it does not have to perform sync
     *
     */
    @Override
    public boolean performSyncFlag(Budget budget) {
        return false;
    }

    @Override
    public ScaleTwoDecimal getUnitFormulatedCost(String unitNumber,String formulatedTypeCode) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("formulatedTypeCode", formulatedTypeCode);
        List<UnitFormulatedCost> unitFormulatedCosts = (List<UnitFormulatedCost>) getBusinessObjectService().findMatchingOrderBy(UnitFormulatedCost.class, param, "unitNumber", true);
        List<Unit> unitHierarchy = unitService.getUnitHierarchyForUnit(unitNumber);
        for (Unit unit : unitHierarchy) {
            for (UnitFormulatedCost unitFormulatedCost : unitFormulatedCosts) {
                if(unit.getUnitNumber().equals(unitFormulatedCost.getUnitNumber())){
                    return unitFormulatedCost.getUnitCost();
                }
            }
        }
        return ScaleTwoDecimal.ZERO;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<BudgetRate> getSavedBudgetRates(Budget budget) {
        Map<String,Long> qMap = new HashMap<String, Long>();
        qMap.put("budgetId",budget.getBudgetId());
        Collection<BudgetRate> rates = businessObjectService.findMatching(BudgetRate.class, qMap);
        for (BudgetRate rate : rates) {
            java.util.Calendar startDate = new java.util.GregorianCalendar();
            startDate.setTime(rate.getStartDate());
            Integer newFY = this.getFiscalYearMonthService().getFiscalYearFromDate(startDate);
            rate.setFiscalYear(newFY.toString());
        }
        return rates;
    }

    @Override
    public boolean checkActivityTypeChange(Budget budget) {
        return checkActivityTypeChange(getSavedBudgetRates(budget), budget.getBudgetParent().getActivityTypeCode());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean checkActivityTypeChange(Collection<BudgetRate> allPropRates, String activityTypeCode) {
        if (CollectionUtils.isNotEmpty(allPropRates)) {
            Equals equalsActivityType = new Equals("activityTypeCode", activityTypeCode);
            QueryList matchActivityTypePropRates = new QueryList(allPropRates).filter(equalsActivityType);
            if (CollectionUtils.isEmpty(matchActivityTypePropRates) || allPropRates.size() != matchActivityTypePropRates.size()) {
                return true;
            }
        }

        return false;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public FiscalYearMonthService getFiscalYearMonthService() {
        return fiscalYearMonthService;
    }

    public void setFiscalYearMonthService(FiscalYearMonthService fiscalYearMonthService) {
        this.fiscalYearMonthService = fiscalYearMonthService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
