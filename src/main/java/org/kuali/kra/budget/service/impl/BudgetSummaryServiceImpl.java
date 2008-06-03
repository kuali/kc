/*
 * Copyright 2007 The Kuali Foundation.
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private BusinessObjectService businessObjectService;
    private BudgetCalculationService budgetCalculationService;
    
    
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
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            Integer budPeriod = budgetPeriod.getBudgetPeriod();
            Long budgetPeriodId = budgetPeriod.getBudgetPeriodId();
            switch(budPeriod) {
                case 1 :
                    // get line items for first period
                    budgetLineItems = budgetPeriod.getBudgetLineItems();
                    break;
                default :    
                    /* add line items for following periods */
                    for(BudgetLineItem periodLineItem: budgetLineItems) {
                        BudgetLineItem budgetLineItem = (BudgetLineItem)ObjectUtils.deepCopy(periodLineItem);
                        budgetLineItem.getBudgetCalculatedAmounts().clear();
                        budgetLineItem.setBudgetPeriod(budPeriod);
                        budgetLineItem.setBudgetPeriodId(budgetPeriodId);
                        budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                        budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                        budgetLineItem.setBasedOnLineItem(budgetLineItem.getLineItemNumber());
                        /* add personnel line items */
                        List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                        for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                            budgetPersonnelDetail.getBudgetCalculatedAmounts().clear();
                            budgetPersonnelDetail.setBudgetPeriod(budPeriod);
                            budgetPersonnelDetail.setBudgetPeriodId(budgetPeriodId);
                            budgetPersonnelDetail.setStartDate(budgetPeriod.getStartDate());
                            budgetPersonnelDetail.setEndDate(budgetPeriod.getEndDate());
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
        
//         for(BudgetPeriod budgetPeriod: budgetPeriods) {
//            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
//            updateBudgetLineItems(budgetLineItems, delPeriod+1, true);
//            for(BudgetLineItem periodLineItem: budgetLineItems) {
//                List<BudgetPersonnelDetails> budgetPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
//                updateBudgetPersonnelDetails(budgetPersonnelDetails, delPeriod+1, true);
//            }
//        }
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
                for(BudgetLineItem budgetLineItem: budgetLineItems) {
                    Date newStartDate = budgetLineItem.getStartDate();
                    Date newEndDate = budgetLineItem.getEndDate();
                    if (budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0) {
                        newStartDate=add(newStartDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod.getOldStartDate(), budgetPeriod.getStartDate(), false));
                        if (newStartDate.after(budgetPeriod.getEndDate())) {
                            newStartDate = budgetPeriod.getStartDate();
                        } else {                       
                            if (newStartDate.after(budgetPeriod.getStartDate())) {
                                // keep the duration, but the item start date relative to period start date is not maintained.
                                int budgetDuration = KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                                int lineItemDuration = KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                                int daysTOEndDate = KraServiceLocator.getService(DateTimeService.class).dateDiff(newStartDate, budgetPeriod.getEndDate(), false);
                                if (daysTOEndDate < lineItemDuration) {
                                    if (budgetDuration > lineItemDuration) {
                                        newEndDate = budgetPeriod.getEndDate();
                                        newStartDate = add(newEndDate,lineItemDuration * (-1));
                                    } else {
                                        newStartDate = budgetPeriod.getStartDate();
                                    }
                                }   
                            }
                        }
                        newEndDate=add(newStartDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false));
                        if (newEndDate.after(budgetPeriod.getEndDate())) {
                            newEndDate = budgetPeriod.getEndDate();
                        }
                    } else {
                        if (budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldStartDate()) != 0 &&  budgetPeriod.getEndDate().before(budgetLineItem.getEndDate())) {
                            if (budgetPeriod.getEndDate().after(budgetLineItem.getStartDate()) && budgetPeriod.getEndDate().before(budgetLineItem.getEndDate())) {
                                newEndDate = budgetPeriod.getEndDate();
                            } else {
                                if (budgetPeriod.getEndDate().before(budgetLineItem.getStartDate())) {
                                    newStartDate=budgetPeriod.getStartDate();
                                    newEndDate=add(newStartDate,KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false));
                                    if (newEndDate.after(budgetPeriod.getEndDate())) {
                                        newEndDate = budgetPeriod.getEndDate();
                                    }
                                }
                            }
                        }
                    }
                    budgetLineItem.setStartDate(newStartDate);
                    budgetLineItem.setEndDate(newEndDate);
                    budgetLineItem.setBasedOnLineItem(budgetLineItem.getLineItemNumber());
                    List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                    for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                        budgetPersonnelDetail.setStartDate(newStartDate);
                        budgetPersonnelDetail.setEndDate(newEndDate);
                    }
                }
           }       
            // set old start/end date - rollback may be needed if rule is failed
            budgetPeriod.setOldStartDate(budgetPeriod.getStartDate());
            budgetPeriod.setOldEndDate(budgetPeriod.getEndDate());
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
    
    private Date add(Date date, int days) {
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(new java.util.Date(date.getTime()));
        c1.add(Calendar.DATE,days);
        return new java.sql.Date(c1.getTime().getTime());
        
    }

}
