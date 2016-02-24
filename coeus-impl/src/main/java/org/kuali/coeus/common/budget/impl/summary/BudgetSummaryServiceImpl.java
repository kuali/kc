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
package org.kuali.coeus.common.budget.impl.summary;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonService;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonServiceFactory;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonSalaryDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.OnOffCampusFlagConstants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Component("budgetSummaryService")
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private static final String BUDGET_DATE_CHANGE_WARNING_MSG = "Changing the budget period dates will result in changes being made to line item Expenses & recalculation of the budget, Do you want to proceed? ";
    private static final String BUDGET_DATE_CHANGE_AND_DELETE_WARNING_MSG = "Changing the budget period dates will result in changes being made to line item Expenses & recalculation of the budget, and one or more periods to be deleted have expense line items that will be deleted. Are you sure you want to proceed? ";
    private static final String BUDGET_PERIOD_DELETE_WARNING_MSG = "One or more periods to be deleted have expense line items that will be deleted. Are you sure you want to proceed?";

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;
    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    public void setDateTimeService (DateTimeService dateTimeService){this.dateTimeService = dateTimeService;}
    protected DateTimeService getDateTimeService (){return dateTimeService;}


    @Override
    public void generateAllPeriods(Budget budget) {
        // calculate first period - only period 1 exists at this point 
        calculateBudget(budget);
    	
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();

        /* get all period one line items */

        List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        int period1Duration=0;
        BudgetPeriod budgetPeriod1=null;
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            Integer budPeriod = budgetPeriod.getBudgetPeriod();
            Long budgetPeriodId = budgetPeriod.getBudgetPeriodId();
            int lineDuration = 0;
            int currentPeriodDuration = 0;
            int gap=0; 
            List <Date> startEndDates = new ArrayList<Date>();
            switch(budPeriod) {
                case 1 :
                    // get line items for first period
                    budgetPeriod1 = budgetPeriod;
                    budgetLineItems = budgetPeriod.getBudgetLineItems();
                    period1Duration = getDateTimeService().dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                    break;
                default :
                    budgetPeriod.setNumberOfParticipants(budgetPeriod1.getNumberOfParticipants());
                    /* add line items for following periods */
                    for(BudgetLineItem periodLineItem: budgetLineItems) {
                    	BudgetLineItem budgetLineItem = getDataObjectService().copyInstance(periodLineItem, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER, CopyOption.RESET_OBJECT_ID );
                    	budgetLineItem.setBudgetId(budget.getBudgetId());
                        budgetLineItem.getBudgetCalculatedAmounts().clear();
                        budgetLineItem.setBudgetPeriod(budPeriod);
                        budgetLineItem.setBudgetPeriodId(budgetPeriodId);
                        budgetLineItem.setBudgetPeriodBO(budgetPeriod);
                        boolean isLeapDateInPeriod = isLeapDaysInPeriod(budgetLineItem.getStartDate(), budgetLineItem.getEndDate()) ;
                        gap= getDateTimeService().dateDiff(budgetPeriod1.getStartDate(), budgetLineItem.getStartDate(), false);
                        boolean isLeapDayInGap = isLeapDaysInPeriod(budgetPeriod1.getStartDate(), budgetLineItem.getStartDate());
                        lineDuration= getDateTimeService().dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                        currentPeriodDuration = getDateTimeService().dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                        if (period1Duration == lineDuration || lineDuration > currentPeriodDuration) {
                            budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                            budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                        } else {
                            startEndDates.add(0, budgetPeriod.getStartDate());
                            startEndDates.add(1, budgetPeriod.getEndDate());
                            List <Date> dates = getNewStartEndDates(startEndDates, gap, lineDuration, budgetLineItem.getStartDate(), isLeapDateInPeriod, isLeapDayInGap);
                            budgetLineItem.setStartDate(dates.get(0));
                            budgetLineItem.setEndDate(dates.get(1));
                        }
                        budgetLineItem.setBasedOnLineItem(budgetLineItem.getLineItemNumber());
                        lineDuration= getDateTimeService().dateDiff(periodLineItem.getStartDate(), periodLineItem.getEndDate(), false);
                        int personnelDuration = 0;
                        /* add personnel line items */
                        List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                        for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                            budgetPersonnelDetail.setBudgetPersonnelLineItemId(null);
                            budgetPersonnelDetail.getBudgetCalculatedAmounts().clear();
                            personnelDuration= getDateTimeService().dateDiff(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate(), false);
                            budgetPersonnelDetail.setBudgetPeriod(budPeriod);
                            budgetPersonnelDetail.setBudgetPeriodId(budgetPeriodId);
                            gap= getDateTimeService().dateDiff(periodLineItem.getStartDate(), budgetPersonnelDetail.getStartDate(), false);
                            isLeapDayInGap = isLeapDaysInPeriod(periodLineItem.getStartDate(), budgetPersonnelDetail.getStartDate());
                            if (period1Duration == personnelDuration || personnelDuration >= lineDuration) {
                                budgetPersonnelDetail.setStartDate(budgetLineItem.getStartDate());
                                budgetPersonnelDetail.setEndDate(budgetLineItem.getEndDate());
                            } else {
                                startEndDates.add(0, budgetLineItem.getStartDate());
                                startEndDates.add(1, budgetLineItem.getEndDate());
                                isLeapDateInPeriod = isLeapDaysInPeriod(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate()) ;
                                List <Date> dates = getNewStartEndDates(startEndDates, gap, personnelDuration, budgetPersonnelDetail.getStartDate(), isLeapDateInPeriod, isLeapDayInGap);
                                budgetPersonnelDetail.setStartDate(dates.get(0));
                                budgetPersonnelDetail.setEndDate(dates.get(1));
                            }
                        }
                        budgetPeriod.getBudgetLineItems().add(budgetLineItem);

                    }
            }
        }
        
        BudgetPeriod firstPeriod = budgetPeriods.get(0);
        for (BudgetLineItem budgetLineItem : new ArrayList<>(firstPeriod.getBudgetLineItems())) {
            budgetCalculationService.applyToLaterPeriods(budget, firstPeriod, budgetLineItem);
        }
        
        //now we have generated all periods, calculate all periods
        calculateBudget(budget);
        // reset the old start/end date
        setupOldStartEndDate(budget, true);
    }

    @Override
    public List<BudgetPeriod> generateBudgetPeriods(Budget budget) {
    	List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        Date projectStartDate = budget.getStartDate();
        Date projectEndDate = budget.getEndDate();
        boolean budgetPeriodExists = true;
        
        Calendar cl = Calendar.getInstance();
        
        Date periodStartDate = projectStartDate;
        int budgetPeriodNum = 1;
        while(budgetPeriodExists) {
            cl.setTime(periodStartDate);
            cl.add(Calendar.YEAR, 1);
            Date nextPeriodStartDate = new Date(cl.getTime().getTime());
            cl.add(Calendar.DATE, -1);
            Date periodEndDate = new Date(cl.getTime().getTime());
            /* check period end date gt project end date */
            switch(periodEndDate.compareTo(projectEndDate)) {
                case 1:
                    periodEndDate = projectEndDate;
                case 0:
                    budgetPeriodExists = false;
                    break;
            }
            BudgetPeriod budgetPeriod = budget.getNewBudgetPeriod();
            budgetPeriod.setBudgetPeriod(budgetPeriodNum);
            budgetPeriod.setStartDate(periodStartDate);
            budgetPeriod.setEndDate(periodEndDate);
            budgetPeriod.setBudget(budget);

            budgetPeriods.add(budgetPeriod);
            periodStartDate = nextPeriodStartDate;
            budgetPeriodNum++;
        }
        return budgetPeriods;
    }

    @Override
    public void defaultBudgetPeriods(Budget budget) {
        //get a list of default periods to match the current periods to
        List<BudgetPeriod> newPeriods = generateBudgetPeriods(budget);
        
        //remove any existing periods beyond the number of default periods
        while (budget.getBudgetPeriods().size() > newPeriods.size()) {
            deleteBudgetPeriod(budget, budget.getBudgetPeriods().size()-1);
        }
        //loop through the new periods and correct the dates to match the default set
        //or add a new period if one does not exist
        for (int i = 0; i < newPeriods.size(); i++) {
            BudgetPeriod newPeriod = newPeriods.get(i);
            if (i < budget.getBudgetPeriods().size()) {
                BudgetPeriod curPeriod = budget.getBudgetPeriod(i);
                curPeriod.setStartDate(newPeriod.getStartDate());
                curPeriod.setEndDate(newPeriod.getEndDate());
                curPeriod.setCostSharingAmount(null);
                curPeriod.setDirectCostLimit(null);
                curPeriod.setExpenseTotal(null);
                curPeriod.setTotalCost(null);
                curPeriod.setTotalCostLimit(null);
                curPeriod.setTotalDirectCost(null);
                curPeriod.setTotalIndirectCost(null);
                curPeriod.setUnderrecoveryAmount(null);
            } else {
                budget.getBudgetPeriods().add(newPeriod);
            }
        }
        //correct line item detail dates
        adjustStartEndDatesForLineItems(budget);
        calculateBudget(budget);
    }
    @Override
    public boolean budgetLineItemExists(Budget budget, Integer budgetPeriod) {
        boolean lineItemExists = false;
        
        List<BudgetLineItem> budgetLineItems =  budget.getBudgetPeriod(budgetPeriod).getBudgetLineItems();
        
        /* check budget line item */
        for(BudgetLineItem periodLineItem: budgetLineItems) {
            Integer lineItemPeriod = periodLineItem.getBudgetPeriod();
            if(budgetPeriod+1 == lineItemPeriod) {
                lineItemExists = true;
                break;
            }
            List<BudgetPersonnelDetails> budgetPersonnelDetailsList = periodLineItem.getBudgetPersonnelDetailsList();
            /* check personnel line item */
            for(BudgetPersonnelDetails periodPersonnelLineItem: budgetPersonnelDetailsList) {
                lineItemPeriod = periodPersonnelLineItem.getBudgetPeriod();
                if(budgetPeriod+1 == lineItemPeriod) {
                    lineItemExists = true;
                    break;
                }
            }
        }
        return lineItemExists;
    }

    protected void updateBudgetPeriods(Budget budget, List<BudgetPeriod> budgetPeriods, int checkPeriod, boolean deletePeriod) {
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            Integer budPeriod = budgetPeriod.getBudgetPeriod();
            if(budPeriod >= checkPeriod) {
                int newPeriod = 0;
                if(deletePeriod) {
                    newPeriod = budPeriod - 1;
                }else {
                    newPeriod = budPeriod + 1;
                }
                budgetPeriod.setBudgetPeriod(newPeriod);
                List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
                for(BudgetLineItem periodLineItem: budgetLineItems) {
                    periodLineItem.setBudgetPeriod(newPeriod);
                    List<BudgetPersonnelDetails> budgetPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
                    for(BudgetPersonnelDetails budgetPersonnelDetail : budgetPersonnelDetails) {
                        budgetPersonnelDetail.setBudgetPeriod(newPeriod);
                    }
                }
            }
        }
        for (BudgetSubAwards subAward: budget.getBudgetSubAwards()) {
            for (BudgetSubAwardPeriodDetail detail : subAward.getBudgetSubAwardPeriodDetails()) {
                if (detail.getBudgetPeriod() >= checkPeriod) {
                    if (deletePeriod) {
                        detail.setBudgetPeriod(detail.getBudgetPeriod()-1);
                    } else {
                        detail.setBudgetPeriod(detail.getBudgetPeriod()+1);
                    }
                }
            }
        }
    }


    /* call budget calculation service to calculate budget */
    @Override
    public void calculateBudget(Budget budget) {
        getBudgetCommonService(budget.getBudgetParent()).recalculateBudget(budget);
    }

    protected BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParent parentBudget) {
        return BudgetCommonServiceFactory.createInstance(parentBudget);
    }
    @Override
    public void deleteBudgetPeriod(Budget budget, int delPeriod) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        BudgetPeriod deletedPeriod = budgetPeriods.remove(delPeriod);
        deleteSubAwardPeriodDetails(deletedPeriod);
        updateBudgetPeriods(budget, budgetPeriods, delPeriod+1, true);
    }
    
    protected void deleteSubAwardPeriodDetails(BudgetPeriod deletedPeriod) {
        Budget budget = deletedPeriod.getBudget();
        for (BudgetSubAwards subAward : budget.getBudgetSubAwards()) {
            Iterator<BudgetSubAwardPeriodDetail> iter = subAward.getBudgetSubAwardPeriodDetails().iterator();
            while (iter.hasNext()) {
                BudgetSubAwardPeriodDetail detail = iter.next();
                if (ObjectUtils.equals(detail.getBudgetPeriod(), deletedPeriod.getBudgetPeriod())) {
                    iter.remove();
                }
            }
        }
    }

    @Override
    public void addBudgetPeriod(Budget budget, BudgetPeriod newBudgetPeriod) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        Integer newPeriodIndex = newBudgetPeriod.getBudgetPeriod();
        int totalPeriods = budgetPeriods.size();
        if(newPeriodIndex > totalPeriods) {
            budgetPeriods.add(newBudgetPeriod);
        }else {
            updateBudgetPeriods(budget, budgetPeriods, newPeriodIndex, false);
            budgetPeriods.add(newPeriodIndex-1, newBudgetPeriod);
        }
        
    }
    
    public final BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public final void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    @Override
    public void updateOnOffCampusFlag(Budget budget, String onOffCampusFlag) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            /* update line items */
            if (budgetPeriod.getBudgetLineItems() != null) {
                for(BudgetLineItem periodLineItem: budgetPeriod.getBudgetLineItems()) {
                    if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
                        if (periodLineItem.getCostElementBO() == null) {
                            periodLineItem.refreshReferenceObject("costElementBO");
                        }
                        periodLineItem.setOnOffCampusFlag(periodLineItem.getCostElementBO().getOnOffCampusFlag()); 
                    } else {
                        periodLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
                    }
                    for(BudgetPersonnelDetails periodPersonnelDetail: periodLineItem.getBudgetPersonnelDetailsList()) {
                        if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
                            if (periodLineItem.getCostElementBO() == null) {
                                periodLineItem.refreshReferenceObject("costElementBO");
                            }
                            periodPersonnelDetail.setOnOffCampusFlag(periodLineItem.getCostElementBO().getOnOffCampusFlag()); 
                        } else {
                            periodPersonnelDetail.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
                        }
                    }
                }
            }
        }                            
    }
    
    /**
     * KRACOEUS-1376
     * @see BudgetSummaryService#adjustStartEndDatesForLineItems(org.kuali.coeus.common.budget.framework.core.Budget)
     */
    @Override
    public void adjustStartEndDatesForLineItems(Budget budget) {
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
        	adjustStartEndDatesForLineItems(budgetPeriod);
        }
    }

    public void adjustStartEndDatesForLineItems(BudgetPeriod budgetPeriod) {
        if ( (budgetPeriod.getOldStartDate() != null && budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0) || 
                (budgetPeriod.getOldEndDate() != null && budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldEndDate()) != 0) ) {
            List <BudgetLineItem > budgetLineItems = budgetPeriod.getBudgetLineItems();
            setupOldStartEndDate(budgetLineItems);
            for(BudgetLineItem budgetLineItem: budgetLineItems) {
                Date newStartDate;
                Date newEndDate;
                List <Date> startEndDates = new ArrayList<Date>();
                startEndDates.add(0, budgetLineItem.getStartDate());
                startEndDates.add(1, budgetLineItem.getEndDate());
                
                getNewStartEndDates(budgetPeriod.getStartDate(), budgetPeriod.getOldStartDate(), budgetPeriod.getEndDate(), budgetPeriod.getOldEndDate(), startEndDates);
                newStartDate=startEndDates.get(0);
                newEndDate=startEndDates.get(1);
                
                budgetLineItem.setStartDate(newStartDate);
                budgetLineItem.setEndDate(newEndDate);
                budgetLineItem.setBasedOnLineItem(budgetLineItem.getLineItemNumber());
            }
            adjustStartEndDatesForPersonnelLineItems(budgetLineItems);
       }       
        // set old start/end date - rollback may be needed if rule is failed
        budgetPeriod.setOldStartDate(budgetPeriod.getStartDate());
        budgetPeriod.setOldEndDate(budgetPeriod.getEndDate());
    }


    protected void adjustStartEndDatesForPersonnelLineItems(List <BudgetLineItem > budgetLineItems) {

        for(BudgetLineItem budgetLineItem: budgetLineItems) {            
            if ( (budgetLineItem.getOldStartDate() != null && budgetLineItem.getStartDate().compareTo(budgetLineItem.getOldStartDate()) != 0) || 
                    (budgetLineItem.getOldEndDate() != null && budgetLineItem.getEndDate().compareTo(budgetLineItem.getOldEndDate()) != 0) ) {
                List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                    Date newStartDate = budgetPersonnelDetail.getStartDate();
                    Date newEndDate = budgetPersonnelDetail.getEndDate();
                    List <Date> startEndDates = new ArrayList<Date>();
                    startEndDates.add(0, budgetPersonnelDetail.getStartDate());
                    startEndDates.add(1, budgetPersonnelDetail.getEndDate());
                    
                    getNewStartEndDates(budgetLineItem.getStartDate(), budgetLineItem.getOldStartDate(), budgetLineItem.getEndDate(), budgetLineItem.getOldEndDate(), startEndDates);
                    newStartDate=startEndDates.get(0);
                    newEndDate=startEndDates.get(1);
                    budgetPersonnelDetail.setStartDate(newStartDate);
                    budgetPersonnelDetail.setEndDate(newEndDate);
                }
            }
            budgetLineItem.setOldStartDate(budgetLineItem.getStartDate());
            budgetLineItem.setOldEndDate(budgetLineItem.getEndDate());
        }

    }
    
    /**
     * 
     * This method hold the old start/end date, so it can be used for comparison upon save.
     * 
     * @param budget
     */
    @Override
    public void setupOldStartEndDate (Budget budget, boolean resetAll) {
        for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getOldStartDate() == null || budgetPeriod.getOldEndDate() == null || resetAll) {
                budgetPeriod.setOldStartDate(budgetPeriod.getStartDate());
                budgetPeriod.setOldEndDate(budgetPeriod.getEndDate());
            }
        }

    }

    protected void setupOldStartEndDate (List <BudgetLineItem > budgetLineItems) {
        for(BudgetLineItem budgetLineItem: budgetLineItems) {
            if (budgetLineItem.getOldStartDate() == null || budgetLineItem.getOldEndDate() == null) {
                budgetLineItem.setOldStartDate(budgetLineItem.getStartDate());
                budgetLineItem.setOldEndDate(budgetLineItem.getEndDate());
            }
        }

    }

    protected Date add(Date date, int days) {
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(new java.util.Date(date.getTime()));
        c1.add(Calendar.DATE,days);
        return new java.sql.Date(c1.getTime().getTime());
        
    }

    /**
     * 
     * This method is to be shared by adjusting dates for budgetperiod.lineitem and lineitem.personnellineitem
     * refer to jira-1376 for rules
     */
    protected List<Date> getNewStartEndDates(Date parentStartDate, Date oldStartDate, Date parentEndDate, Date oldEndDate, List<Date> startEndDates) {
        Date startDate = startEndDates.get(0);
        Date endDate = startEndDates.get(1);
        Date newStartDate = startDate;
        Date newEndDate = endDate;
        if (startDate.compareTo(oldStartDate) == 0 && endDate.compareTo(oldEndDate) == 0) {
            // if initiall, both are matching, then keep matching.
            newStartDate = parentStartDate;
            newEndDate = parentEndDate;
        } else {
            // duration has priority over child start date relative to parent start date
            if (parentStartDate.compareTo(oldStartDate) != 0) {
                // keep the gap between child start date and parent start date
                newStartDate=add(newStartDate, getDateTimeService().dateDiff(oldStartDate, parentStartDate, false));
                if (newStartDate.after(parentEndDate)) {
                    newStartDate = parentStartDate;
                } else {                       
                    if (newStartDate.after(parentStartDate)) {
                        // keep the duration, but the item start date relative to period start date is not maintained.
                        int parentDuration = getDateTimeService().dateDiff(parentStartDate, parentEndDate, false);
                        int duration = getDateTimeService().dateDiff(startDate, endDate, false);
                        int daysTOEndDate = getDateTimeService().dateDiff(newStartDate, parentEndDate, false);
                        if (daysTOEndDate < duration) {
                            if (parentDuration > duration) {
                                newEndDate = parentEndDate;
                                newStartDate = add(newEndDate,duration * (-1));
                            } else {
                                // can't keep duration because parent duration is smaller than child initial duration
                                newStartDate = parentStartDate;
                            }
                        }   
                    }
                }
                newEndDate=add(newStartDate, getDateTimeService().dateDiff(startDate, endDate, false));
                if (newEndDate.after(parentEndDate)) {
                    newEndDate = parentEndDate;
                }
            } else {
                // end date changed
                if (parentEndDate.compareTo(oldStartDate) != 0 &&  parentEndDate.before(endDate)) {
                    if (parentEndDate.after(startDate) && parentEndDate.before(endDate)) {
                        newEndDate = parentEndDate;
                        // try to keep duration
                        newStartDate = add(newEndDate, getDateTimeService().dateDiff(endDate, startDate, false));
                        if (newStartDate.before(parentStartDate)) {
                            newStartDate = parentStartDate;
                        }
                    } else {
                        if (parentEndDate.before(startDate)) {
                            newStartDate=parentStartDate;
                            newEndDate=add(newStartDate, getDateTimeService().dateDiff(startDate, endDate, false));
                            if (newEndDate.after(parentEndDate)) {
                                newEndDate = parentEndDate;
                            }
                        }
                    }
                }
            }
        }
        startEndDates.clear();
        startEndDates.add(0, newStartDate);
        startEndDates.add(1, newEndDate);
        return startEndDates;
    }

    @Override
    public List<Date> getNewStartEndDates(List<Date> startEndDates, int gap, int duration, Date prevDate, boolean leapDayInPeriod,  boolean leapDayInGap) {
        Date startDate = startEndDates.get(0);
        Date newStartDate = add(startDate, gap);
        Date newEndDate = add(newStartDate,duration);

        boolean isLeapDayInNewPeriod = isLeapDaysInPeriod(startDate, newEndDate);
        boolean isLeapDayInNewGap = isLeapDaysInPeriod(startDate,newStartDate);
        boolean isLeapDayInInitialPeriod = leapDayInPeriod || leapDayInGap;

        if (isLeapDayInInitialPeriod && !isLeapDayInNewPeriod) {
            newEndDate = add(newEndDate, -1);
        } else if (!isLeapDayInInitialPeriod && isLeapDayInNewPeriod) {
            newEndDate = add(newEndDate, 1);
        }

        if (!isLeapDayInNewGap && leapDayInGap) {
            newStartDate = add(newStartDate, -1);
        } else if (isLeapDayInNewGap && !leapDayInGap) {
            newStartDate = add(newStartDate, 1);
        }

        List<Date> newStartEndDates = new ArrayList<>();
        newStartEndDates.add(0,newStartDate);
        newStartEndDates.add(1,newEndDate);
        return newStartEndDates;
    }

    protected boolean isLeapYear(Date date) {
        int year = getYear(date);
              
        return isLeapYear(year);
    }

    protected boolean isLeapYear(int year) {
        boolean isLeapYear;

        isLeapYear = (year % 4 == 0);
        isLeapYear = isLeapYear && (year % 100 != 0);
        isLeapYear = isLeapYear || (year % 400 == 0);        
        return isLeapYear;

    }
    
    protected int getYear(Date date) {
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(new java.util.Date(date.getTime()));
        return c1.get(Calendar.YEAR);

    }

    @Override
    public boolean isLeapDaysInPeriod(Date sDate, Date eDate) {
        Date leapDate;
        int sYear = getYear(sDate);
        int eYear = getYear(eDate);
        if (isLeapYear(sDate)) {            
            Calendar c1 = Calendar.getInstance(); 
            c1.clear();
            c1.set(sYear, 1, 29);
            leapDate = new java.sql.Date(c1.getTime().getTime());
            // start date is before 2/29 & enddate >= 2/29
            if (sDate.before(leapDate)) {
                if (eDate.compareTo(leapDate) >= 0) {
                    return true;
                }           
            } else if (sDate.equals(leapDate)) {
                return true;
            }
        } else if (isLeapYear(eDate)) {
            Calendar c1 = Calendar.getInstance(); 
            c1.set(eYear, 1, 29);
            leapDate = new java.sql.Date(c1.getTime().getTime());
            if (eDate.compareTo(leapDate) >= 0) {
                return true;
            }
        } else {
            sYear++;
            while (eYear > sYear) {
                if (isLeapYear(sYear)) {
                    return true;
                }
                sYear++;
            }
        }
        return false;
    }
    
    /* get onoffcampus flag description */
    @Override
    public String getOnOffCampusFlagDescription(String onOffCampusFlag) {
        String retValue = null;
        for (OnOffCampusFlagConstants onOffCampusFlagConstants : OnOffCampusFlagConstants.values()) {
            if(onOffCampusFlagConstants.getCode().equalsIgnoreCase(onOffCampusFlag)) {
                retValue =  onOffCampusFlagConstants.getDescription();
                break;
            }
        }
        return retValue;
    }

    @Override
    public String defaultWarningMessage(Budget budget) {
        List<BudgetPeriod> budgetPeriods = generateBudgetPeriods(budget);
        boolean dateChanged = false;
        boolean deletePeriodWithLineItem = false;
        
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetPeriod() <= budgetPeriods.size()) {
                if (CollectionUtils.isNotEmpty(budgetPeriod.getBudgetLineItems()) && (budgetPeriod.getStartDate().compareTo(budgetPeriods.get(budgetPeriod.getBudgetPeriod() - 1).getStartDate()) != 0 ||
                     budgetPeriod.getEndDate().compareTo(budgetPeriods.get(budgetPeriod.getBudgetPeriod() - 1).getEndDate()) != 0)) {
                    dateChanged = true;
                }
            } else {
                if (CollectionUtils.isNotEmpty(budgetPeriod.getBudgetLineItems())) {
                    deletePeriodWithLineItem = true;
                }
            }            
        }
        
        if (dateChanged) {
            if (deletePeriodWithLineItem) {
                return BUDGET_DATE_CHANGE_AND_DELETE_WARNING_MSG;
            } else {
                return BUDGET_DATE_CHANGE_WARNING_MSG;
            }
        } else {
            if (deletePeriodWithLineItem) {
                return BUDGET_PERIOD_DELETE_WARNING_MSG;
            } else {
                return Constants.EMPTY_STRING;
            }
            
        }
        
    }
    
    public void syncBudgetPersonSalaryDetails(Budget budget) {
        for(BudgetPerson budgetPerson: budget.getBudgetPersons()) {
    		HashSet<Integer> existingBudgetPeriods = new HashSet<Integer>();
        	for(BudgetPersonSalaryDetails budgetPersonSalaryDetails : budgetPerson.getBudgetPersonSalaryDetails()) {
        		existingBudgetPeriods.add(budgetPersonSalaryDetails.getBudgetPeriod());
        	}
        	addSalaryDetailsForNewBudgetPeriod(budget, budgetPerson, existingBudgetPeriods);
        }
    }
    
    public void addSalaryDetailsForNewBudgetPeriod(Budget budget, BudgetPerson budgetPerson, HashSet<Integer> existingBudgetPeriods) {
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
        	if(!existingBudgetPeriods.contains(budgetPeriod.getBudgetPeriod())) {
        		budgetPerson.getBudgetPersonSalaryDetails().add(budgetPerson.getNewBudgetPersonSalaryDetails(budgetPeriod));
        	}
        }    	
    }
    
	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
