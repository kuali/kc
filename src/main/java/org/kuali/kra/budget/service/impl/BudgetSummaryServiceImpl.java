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
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.OnOffCampusFlagConstants;

public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private BusinessObjectService businessObjectService;
    private BudgetCalculationService budgetCalculationService;
    private static final String BUDGET_DATE_CHANGE_WARNING_MSG = "Changing the budget period dates will result in changes being made to line item Expenses & recalculation of the budget, Do you want to proceed? ";
    private static final String BUDGET_DATE_CHANGE_AND_DELETE_WARNING_MSG = "Changing the budget period dates will result in changes being made to line item Expenses & recalculation of the budget, and one or more periods to be deleted have expense line items that will be deleted. Are you sure you want to proceed? ";
    private static final String BUDGET_PERIOD_DELETE_WARNING_MSG = "One or more periods to be deleted have expense line items that will be deleted. Are you sure you want to proceed?";
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.BudgetSummaryService#getBudgetLineItemForPeriod()
     */
    public Collection<BudgetLineItem> getBudgetLineItemForPeriod(BudgetDocument budgetDocument, int budgetPeriodNumber) {
        Map budgetLineItemMap = new HashMap();
        Collection<BudgetLineItem> periodLineItems = new ArrayList();
        /* filter by budget period */
        budgetLineItemMap.put("budgetPeriod", budgetPeriodNumber);
        periodLineItems = businessObjectService.findMatching(BudgetLineItem.class, budgetLineItemMap);
        return periodLineItems;
        
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.BudgetSummaryService#getBudgetLineItemForPeriod()
     */
    public Collection<BudgetPersonnelDetails> getBudgetPersonnelDetailsForPeriod(BudgetDocument budgetDocument, int budgetPeriodNumber) {
        Map budgetLineItemMap = new HashMap();
        Collection<BudgetPersonnelDetails> periodPersonnelDetails = new ArrayList();
        /* filter by budget period */
        budgetLineItemMap.put("budgetPeriod", budgetPeriodNumber);
        periodPersonnelDetails = businessObjectService.findMatching(BudgetPersonnelDetails.class, budgetLineItemMap);
        return periodPersonnelDetails;
        
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.BudgetSummaryService#generateBudgetPeriods()
     */
    public void generateAllPeriods(BudgetDocument budgetDocument) {
        
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();

        /* get all period one line items */
        Map budgetLineItemMap = new HashMap();

        List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        List<BudgetLineItem> newLineItems = new ArrayList<BudgetLineItem>();
        List<BudgetPersonnelDetails> newPersonnelItems = new ArrayList<BudgetPersonnelDetails>();
        HashMap newBudgetLineItems = new HashMap();
        HashMap newBudgetPersonnelLineItems = new HashMap();
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
                    period1Duration = KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                    break;
                default :    
                    /* add line items for following periods */
                    for(BudgetLineItem periodLineItem: budgetLineItems) {
                        BudgetLineItem budgetLineItem = (BudgetLineItem)ObjectUtils.deepCopy(periodLineItem);
                        budgetLineItem.getBudgetCalculatedAmounts().clear();
                        budgetLineItem.setBudgetPeriod(budPeriod);
                        budgetLineItem.setBudgetPeriodId(budgetPeriodId);
                        boolean isLeapDateInPeriod = isLeapDaysInPeriod(budgetLineItem.getStartDate(), budgetLineItem.getEndDate()) ;
                        gap=KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod1.getStartDate(), budgetLineItem.getStartDate(), false);
                        boolean isLeapDayInGap = isLeapDaysInPeriod(budgetPeriod1.getStartDate(), budgetLineItem.getStartDate());
                        lineDuration=KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                        currentPeriodDuration = KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
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
                        lineDuration=KraServiceLocator.getService(DateTimeService.class).dateDiff(periodLineItem.getStartDate(), periodLineItem.getEndDate(), false);
                        int personnelDuration = 0;
                        /* add personnel line items */
                        List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                        for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                            budgetPersonnelDetail.getBudgetCalculatedAmounts().clear();
                            personnelDuration=KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate(), false);
                            budgetPersonnelDetail.setBudgetPeriod(budPeriod);
                            budgetPersonnelDetail.setBudgetPeriodId(budgetPeriodId);
                            gap=KraServiceLocator.getService(DateTimeService.class).dateDiff(periodLineItem.getStartDate(), budgetPersonnelDetail.getStartDate(), false);
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
        List<BudgetLineItem> firstPerLineItems = firstPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : firstPerLineItems) {
            budgetCalculationService.applyToLaterPeriods(budgetDocument, firstPeriod, budgetLineItem);
        }
        
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.BudgetSummaryService#generateBudgetPeriods()
     */
    public void generateBudgetPeriods(List<BudgetPeriod> budgetPeriods, Date projectStartDate, Date projectEndDate) {
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
            BudgetPeriod budgetPeriod = new BudgetPeriod();
            budgetPeriod.setBudgetPeriod(budgetPeriodNum);
            budgetPeriod.setStartDate(periodStartDate);
            budgetPeriod.setEndDate(periodEndDate);
            budgetPeriods.add(budgetPeriod);
            periodStartDate = nextPeriodStartDate;
            budgetPeriodNum++;
        }
    }
    
    
    public void defaultBudgetPeriods(BudgetDocument budgetDocument) {
        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        List<BudgetPeriod> budgetPeriodsToDelete = new ArrayList<BudgetPeriod>();
        
        generateBudgetPeriods(budgetPeriods, budgetDocument.getStartDate(), budgetDocument.getEndDate());
        
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetPeriod() <= budgetPeriods.size()) {
                budgetPeriod.setStartDate(budgetPeriods.get(budgetPeriod.getBudgetPeriod() - 1).getStartDate());
                budgetPeriod.setEndDate(budgetPeriods.get(budgetPeriod.getBudgetPeriod() - 1).getEndDate());
            } else {
                budgetPeriodsToDelete.add(budgetPeriod);
            }            
        }
        budgetDocument.getBudgetPeriods().removeAll(budgetPeriodsToDelete);
        if (budgetPeriods.size() > budgetDocument.getBudgetPeriods().size()) {
            int numOfPeriods = budgetDocument.getBudgetPeriods().size();
            while (numOfPeriods < budgetPeriods.size()) {
                BudgetPeriod budgetPeriod = budgetPeriods.get(numOfPeriods);
                budgetPeriod.setOldStartDate(budgetPeriod.getStartDate());
                budgetPeriod.setOldEndDate(budgetPeriod.getEndDate());
                budgetDocument.getBudgetPeriods().add(numOfPeriods, budgetPeriods.get(numOfPeriods));
                numOfPeriods++;
            }
        }
        
        
    }

    public boolean budgetLineItemExists(BudgetDocument budgetDocument, Integer budgetPeriod) {
        boolean lineItemExists = false;
        
        List<BudgetLineItem> budgetLineItems =  budgetDocument.getBudgetPeriod(budgetPeriod).getBudgetLineItems();
        
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

    private void updateBudgetPeriods(List<BudgetPeriod> budgetPeriods, int checkPeriod, boolean deletePeriod) {
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
    }


    /* call budget calculation service to calculate budget */
    public void calculateBudget(BudgetDocument budgetDocument) {
        getBudgetCalculationService().calculateBudget(budgetDocument);
    }

    public void deleteBudgetPeriod(BudgetDocument budgetDocument, int delPeriod) {
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        BudgetPeriod deletedPeriod = budgetPeriods.remove(delPeriod);
        updateBudgetPeriods(budgetPeriods, delPeriod+1, true);
    }

    
    public void addBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod newBudgetPeriod) {
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        Integer newPeriodIndex = newBudgetPeriod.getBudgetPeriod();
        int totalPeriods = budgetPeriods.size();
        if(newPeriodIndex > totalPeriods) {
            budgetPeriods.add(newBudgetPeriod);
        }else {
            updateBudgetPeriods(budgetPeriods, newPeriodIndex, false);
            budgetPeriods.add(newPeriodIndex-1, newBudgetPeriod);
        }
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

    public final BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public final void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public void updateOnOffCampusFlag(BudgetDocument budgetDocument, String onOffCampusFlag) {
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        //List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetDocument.getBudgetPersonnelDetailsList();
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            /* get all line items for each budget period */
            Collection<BudgetLineItem> periodLineItems = new ArrayList();
            Collection<BudgetPersonnelDetails> periodPersonnelDetails = new ArrayList();
            Map budgetLineItemMap = new HashMap();
            /* filter by budget period */
            // TODO : not sure how this personnel details list.  This is just copy from an existing method
            Integer budgetPeriodNumber = budgetPeriod.getBudgetPeriod();
            budgetLineItemMap.put("budgetPeriod", budgetPeriodNumber);
            periodLineItems = businessObjectService.findMatching(BudgetLineItem.class, budgetLineItemMap);
            periodPersonnelDetails = businessObjectService.findMatching(BudgetPersonnelDetails.class, budgetLineItemMap);
            /* update line items */
            if (budgetPeriod.getBudgetLineItems() != null) {
                for(BudgetLineItem periodLineItem: budgetPeriod.getBudgetLineItems()) {
                    if (onOffCampusFlag.equalsIgnoreCase(Constants.DEFALUT_CAMUS_FLAG)) {
                        if (periodLineItem.getCostElementBO() == null) {
                            periodLineItem.refreshReferenceObject("costElementBO");
                        }
                        periodLineItem.setOnOffCampusFlag(periodLineItem.getCostElementBO().getOnOffCampusFlag()); 
                    } else {
                        periodLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
                    }
                    for(BudgetPersonnelDetails periodPersonnelDetail: periodLineItem.getBudgetPersonnelDetailsList()) {
                        if (onOffCampusFlag.equalsIgnoreCase(Constants.DEFALUT_CAMUS_FLAG)) {
                            if (periodLineItem.getCostElementBO() == null) {
                                periodLineItem.refreshReferenceObject("costElementBO");
                            }
                            periodPersonnelDetail.setOnOffCampusFlag(periodPersonnelDetail.getCostElementBO().getOnOffCampusFlag()); 
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
     * @see org.kuali.kra.budget.service.BudgetSummaryService#adjustStartEndDatesForLineItems(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void adjustStartEndDatesForLineItems(BudgetDocument budgetDocument) {
        
        for(BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0 || budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldEndDate()) != 0 ) {
                List <BudgetLineItem >budgetLineItems = budgetPeriod.getBudgetLineItems();
                setupOldStartEndDate(budgetLineItems);
                for(BudgetLineItem budgetLineItem: budgetLineItems) {
                    Date newStartDate = budgetLineItem.getStartDate();
                    Date newEndDate = budgetLineItem.getEndDate();
                    List <Date> startEndDates = new ArrayList<Date>();
                    startEndDates.add(0, budgetLineItem.getStartDate());
                    startEndDates.add(1, budgetLineItem.getEndDate());
                    
                    getNewStartEndDates(budgetPeriod.getStartDate(), budgetPeriod.getOldStartDate(), budgetPeriod.getEndDate(), budgetPeriod.getOldEndDate(), startEndDates);
                    newStartDate=startEndDates.get(0);
                    newEndDate=startEndDates.get(1);
                    
                    budgetLineItem.setStartDate(newStartDate);
                    budgetLineItem.setEndDate(newEndDate);
                    budgetLineItem.setBasedOnLineItem(budgetLineItem.getLineItemNumber());
//                    List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
//                    for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
//                        budgetPersonnelDetail.setStartDate(newStartDate);
//                        budgetPersonnelDetail.setEndDate(newEndDate);
//                    }
                }
                adjustStartEndDatesForPersonnelLineItems(budgetLineItems);
           }       
            // set old start/end date - rollback may be needed if rule is failed
            budgetPeriod.setOldStartDate(budgetPeriod.getStartDate());
            budgetPeriod.setOldEndDate(budgetPeriod.getEndDate());
        }
    }
    
    
    public void adjustStartEndDatesForPersonnelLineItems(List <BudgetLineItem > budgetLineItems) {

        for(BudgetLineItem budgetLineItem: budgetLineItems) {            
            if (budgetLineItem.getStartDate().compareTo(budgetLineItem.getOldStartDate()) != 0 || budgetLineItem.getEndDate().compareTo(budgetLineItem.getOldEndDate()) != 0 ) {
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
     * @param budgetDocument
     */
    public void setupOldStartEndDate (BudgetDocument budgetDocument, boolean resetAll) {
        for(BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getOldStartDate() == null || budgetPeriod.getOldEndDate() == null || resetAll) {
                budgetPeriod.setOldStartDate(budgetPeriod.getStartDate());
                budgetPeriod.setOldEndDate(budgetPeriod.getEndDate());
            }
        }

    }
    
    public void setupOldStartEndDate (List <BudgetLineItem > budgetLineItems) {
        for(BudgetLineItem budgetLineItem: budgetLineItems) {
            if (budgetLineItem.getOldStartDate() == null || budgetLineItem.getOldEndDate() == null) {
                budgetLineItem.setOldStartDate(budgetLineItem.getStartDate());
                budgetLineItem.setOldEndDate(budgetLineItem.getEndDate());
            }
        }

    }

    private Date add(Date date, int days) {
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(new java.util.Date(date.getTime()));
        c1.add(Calendar.DATE,days);
        return new java.sql.Date(c1.getTime().getTime());
        
    }

    /**
     * 
     * This method is to be shared by adjusting dates for budgetperiod->lineitem and lineitem->personnellineitem
     * refer to jira-1376 for rules
     * @param parentStartDate
     * @param oldStartDate
     * @param parentEndDate
     * @param startEndDates
     * @return
     */
    private List<Date> getNewStartEndDates(Date parentStartDate, Date oldStartDate, Date parentEndDate, Date oldEndDate, List<Date> startEndDates) {
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
                newStartDate=add(newStartDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(oldStartDate, parentStartDate, false));
                if (newStartDate.after(parentEndDate)) {
                    newStartDate = parentStartDate;
                } else {                       
                    if (newStartDate.after(parentStartDate)) {
                        // keep the duration, but the item start date relative to period start date is not maintained.
                        int parentDuration = KraServiceLocator.getService(DateTimeService.class).dateDiff(parentStartDate, parentEndDate, false);
                        int duration = KraServiceLocator.getService(DateTimeService.class).dateDiff(startDate, endDate, false);
                        int daysTOEndDate = KraServiceLocator.getService(DateTimeService.class).dateDiff(newStartDate, parentEndDate, false);
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
                newEndDate=add(newStartDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(startDate, endDate, false));
                if (newEndDate.after(parentEndDate)) {
                    newEndDate = parentEndDate;
                }
            } else {
                // end date changed
                if (parentEndDate.compareTo(oldStartDate) != 0 &&  parentEndDate.before(endDate)) {
                    if (parentEndDate.after(startDate) && parentEndDate.before(endDate)) {
                        newEndDate = parentEndDate;
                        // try to keep duration
                        newStartDate = add(newEndDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(endDate, startDate,  false));
                        if (newStartDate.before(parentStartDate)) {
                            newStartDate = parentStartDate;
                        }
                    } else {
                        if (parentEndDate.before(startDate)) {
                            newStartDate=parentStartDate;
                            newEndDate=add(newStartDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(startDate, endDate, false));
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

    public List<Date> getNewStartEndDates(List<Date> startEndDates, int gap, int duration, Date prevDate, boolean leapDayInPeriod,  boolean leapDayInGap) {
        // duration is < (enddate - start date)
        Date startDate = startEndDates.get(0);
        Date endDate = startEndDates.get(1);
        Date newStartDate = startDate;
        Date newEndDate = endDate;
        boolean endDateAdjusted = false;
        if (gap == 0) {
            newEndDate = add(startDate,duration);;
        } else {
                // keep the gap between child start date and parent start date
            newStartDate=add(startDate,gap);
            newEndDate = add(newStartDate,duration);;
            if (newStartDate.after(endDate)) {
                newStartDate = startDate;
                newEndDate=add(startDate,duration);
            } else  if(newEndDate.after(endDate)) {
                endDateAdjusted = true;
                newEndDate=endDate;
                newStartDate = add(endDate,duration *(-1));                
            } 
        }
        boolean isLeapDayInNewGap = isLeapDaysInPeriod(startDate, newStartDate);

        startEndDates.clear();
        if (leapDayInGap && !endDateAdjusted) {
           if (newStartDate.after(startDate)) {
              // shift non-leap year date
              newStartDate = add(newStartDate, -1);                
              newEndDate = add(newEndDate, -1);                                
           }
        } else if (isLeapDayInNewGap) {
          if (newEndDate.before(endDate)) {
          // shift leap year date
              newStartDate = add(newStartDate, 1);                
              newEndDate = add(newEndDate, 1);                                
          }
      
        }
//        if (isLeapYear(prevDate) && prevDate.compareTo(getLeapDay(prevDate)) >= 0 && !isLeapYear(newStartDate)) {
//            if (newStartDate.after(startDate)) {
//                // shift non-leap year date
//                newStartDate = add(newStartDate, -1);                
//                newEndDate = add(newEndDate, -1);                                
//            }
//        } else if (isLeapYear(newStartDate) && newStartDate.compareTo(getLeapDay(newStartDate)) >= 0 && !isLeapYear(prevDate)) {
//            if (newEndDate.before(endDate)) {
//                // shift leap year date
//                newStartDate = add(newStartDate, 1);                
//                newEndDate = add(newEndDate, 1);                                
//            }
//            
//        }
        boolean isLeapDayInNewPeriod = isLeapDaysInPeriod(newStartDate, newEndDate);

        if (leapDayInPeriod && !isLeapDayInNewPeriod) {
           newEndDate = add(newEndDate, -1);
        } else if (!leapDayInPeriod && isLeapDayInNewPeriod) {
            if (endDate.after(newEndDate)) {
                newEndDate = add(newEndDate, 1);   
            } else if (startDate.before(newStartDate)) {
                newStartDate = add(newStartDate, 1);                
                         
            }
            
        }

        startEndDates.add(0, newStartDate);
        startEndDates.add(1, newEndDate);
        return startEndDates;
    }

    private boolean isLeapYear(Date date) {
        int year = getYear(date);
              
        return isLeapYear(year);
    }

    private boolean isLeapYear(int year) {
        boolean isLeapYear;

        isLeapYear = (year % 4 == 0);
        isLeapYear = isLeapYear && (year % 100 != 0);
        isLeapYear = isLeapYear || (year % 400 == 0);        
        return isLeapYear;

    }
    
    private int getYear(Date date) {
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(new java.util.Date(date.getTime()));
        return c1.get(Calendar.YEAR);

    }
    
    private Date getLeapDay(Date date) {
        Calendar c1 = Calendar.getInstance(); 
        c1.clear();
        c1.set(getYear(date), 1, 29);
        return new java.sql.Date(c1.getTime().getTime());

    }
    
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
    public String getOnOffCampusFlagDescription(String onOffCampusFlag) {
        String retValue = null;
        for (OnOffCampusFlagConstants onOffCampusFlagConstants : OnOffCampusFlagConstants.values()) {
            if(onOffCampusFlagConstants.code().equalsIgnoreCase(onOffCampusFlag)) {
                retValue =  onOffCampusFlagConstants.description();
                break;
            }
        }
        return retValue;
    }
 
    public String defaultWarningMessage(BudgetDocument budgetDocument) {
        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        boolean dateChanged = false;
        boolean deletePeriodWithLineItem = false;
        
        generateBudgetPeriods(budgetPeriods, budgetDocument.getStartDate(), budgetDocument.getEndDate());
        
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
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

}
