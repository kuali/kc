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
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.calculator.BudgetPeriodCalculator;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.infrastructure.Constants;

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
            switch(budPeriod) {
                case 1 :
                    // get line items for first period
                    budgetLineItems = budgetPeriod.getBudgetLineItems();
                    break;
                default :    
                    /* add line items for following periods */
                    for(BudgetLineItem periodLineItem: budgetLineItems) {
                        BudgetLineItem budgetLineItem = (BudgetLineItem)ObjectUtils.deepCopy(periodLineItem);
                        budgetLineItem.setBudgetPeriod(budPeriod);
                        budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                        budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                        budgetLineItem.setBasedOnLineItem(budgetLineItem.getLineItemNumber());
                        budgetPeriod.getBudgetLineItems().add(budgetLineItem);
                        /* add personnel line items */
                        List<BudgetPersonnelDetails> budgetPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
                        for(BudgetPersonnelDetails periodOnePersonnelDetail: budgetPersonnelDetails) {
                            BudgetPersonnelDetails budgetPersonnelDetail = (BudgetPersonnelDetails)ObjectUtils.deepCopy(periodOnePersonnelDetail);
                            budgetPersonnelDetail.setBudgetPeriod(budPeriod);
                            budgetPersonnelDetail.setStartDate(budgetPeriod.getStartDate());
                            budgetPersonnelDetail.setEndDate(budgetPeriod.getEndDate());
                            periodLineItem.getBudgetPersonnelDetailsList().add(budgetPersonnelDetail);
                        }
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
        List<BudgetPeriod> newBudgetPeriods = new ArrayList<BudgetPeriod>();
        List<BudgetPeriod> delBudgetPeriods = new ArrayList<BudgetPeriod>();
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            Integer budPeriod = budgetPeriod.getBudgetPeriod();
            if(budPeriod >= checkPeriod) {
                /* remove budget period */
                delBudgetPeriods.add(budgetPeriod);
                int newPeriod = 0;
                boolean addNewPeriod = true;
                /* check action - if it is a DELETE or ADD budget period */ 
                if(deletePeriod) {
                    newPeriod = budPeriod - 1;
                    /* budget period removed - do not add this to new list */
                    if(budPeriod == checkPeriod) {
                        addNewPeriod = false;
                    }
                }else {    
                    newPeriod = budPeriod + 1;
                }
                /* build new budget period list */
                if(addNewPeriod) {
                    BudgetPeriod nBudgetPeriod = (BudgetPeriod)ObjectUtils.deepCopy(budgetPeriod);  
                    nBudgetPeriod.setBudgetPeriod(newPeriod);
                    newBudgetPeriods.add(nBudgetPeriod);
                }
            }
        }
        //budgetPeriods.removeAll(delBudgetPeriods);
        for(BudgetPeriod budgetPeriod: delBudgetPeriods) {
            budgetPeriods.remove(budgetPeriod);
        }
        //budgetPeriods.addAll(newBudgetPeriods);
        for(BudgetPeriod budgetPeriod: newBudgetPeriods) {
            budgetPeriods.add(budgetPeriod);
        }
    }

    private void updateBudgetLineItems(List<BudgetLineItem> budgetLineItems, int checkPeriod, boolean deletePeriod) {
        List<BudgetLineItem> newBudgetLineItems = new ArrayList<BudgetLineItem>();
        List<BudgetLineItem> delBudgetLineItems = new ArrayList<BudgetLineItem>();
        for(BudgetLineItem budgetLineItem: budgetLineItems) {
            Integer budPeriod = budgetLineItem.getBudgetPeriod();
            if(budPeriod >= checkPeriod) {
                /* remove budget period */
                delBudgetLineItems.add(budgetLineItem);
                int newPeriod = 0;
                boolean addNewPeriod = true;
                /* check action - if it is a DELETE or ADD budget line item */ 
                if(deletePeriod) {
                    newPeriod = budPeriod - 1;
                    /* budget period removed - do not add this to new list */
                    if(budPeriod == checkPeriod) {
                        addNewPeriod = false;
                    }
                }else {    
                    newPeriod = budPeriod + 1;
                }
                /* build new budget line items list */
                if(addNewPeriod) {
                    BudgetLineItem nBudgetLineItem = (BudgetLineItem)ObjectUtils.deepCopy(budgetLineItem); 
                    nBudgetLineItem.setBudgetPeriod(newPeriod);
                    newBudgetLineItems.add(nBudgetLineItem);
                }
            }
        }
        budgetLineItems.removeAll(delBudgetLineItems);
        budgetLineItems.addAll(newBudgetLineItems);
    }
    
    private void updateBudgetPersonnelDetails(List<BudgetPersonnelDetails> budgetPersonnelDetails, int checkPeriod, boolean deletePeriod) {
        List<BudgetPersonnelDetails> newBudgetPersonnelDetails = new ArrayList<BudgetPersonnelDetails>();
        List<BudgetPersonnelDetails> delBudgetPersonnelDetails = new ArrayList<BudgetPersonnelDetails>();
        for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
            Integer budPeriod = budgetPersonnelDetail.getBudgetPeriod();
            if(budPeriod >= checkPeriod) {
                /* remove budget period */
                delBudgetPersonnelDetails.add(budgetPersonnelDetail);
                int newPeriod = 0;
                boolean addNewPeriod = true;
                /* check action - if it is a DELETE or ADD budget line item */ 
                if(deletePeriod) {
                    newPeriod = budPeriod - 1;
                    /* budget period removed - do not add this to new list */
                    if(budPeriod == checkPeriod) {
                        addNewPeriod = false;
                    }
                }else {    
                    newPeriod = budPeriod + 1;
                }
                /* build new budget personnel line items list */
                if(addNewPeriod) {
                    BudgetPersonnelDetails nBudgetPersonnelDetail = (BudgetPersonnelDetails)ObjectUtils.deepCopy(budgetPersonnelDetail); 
                    nBudgetPersonnelDetail.setBudgetPeriod(newPeriod);
                    newBudgetPersonnelDetails.add(nBudgetPersonnelDetail);
                }
            }
        }
        budgetPersonnelDetails.removeAll(delBudgetPersonnelDetails);
        budgetPersonnelDetails.addAll(newBudgetPersonnelDetails);
    }

    /* call budget calculation service to calculate budget */
    public void calculateBudget(BudgetDocument budgetDocument) {
        getBudgetCalculationService().calculateBudget(budgetDocument);
    }

    public void deleteBudgetPeriod(BudgetDocument budgetDocument, int delPeriod) {
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        updateBudgetPeriods(budgetPeriods, delPeriod+1, true);
        //List<BudgetLineItem> budgetLineItems = budgetDocument.getBudgetLineItems();

        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            updateBudgetLineItems(budgetLineItems, delPeriod+1, true);
            for(BudgetLineItem periodLineItem: budgetLineItems) {
                List<BudgetPersonnelDetails> budgetPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
                updateBudgetPersonnelDetails(budgetPersonnelDetails, delPeriod+1, true);
            }
        }
            
        /*    
        List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetDocument.getBudgetPersonnelDetailsList();
        
        updateBudgetPeriods(budgetPeriods, delPeriod+1, true);
        updateBudgetLineItems(budgetLineItems, delPeriod+1, true);
        updateBudgetPersonnelDetails(budgetPersonnelDetails, delPeriod+1, true);
        */
    }
    
    public void addBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod newBudgetPeriod) {
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        //List<BudgetLineItem> budgetLineItems = budgetDocument.getBudgetLineItems();
        //List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetDocument.getBudgetPersonnelDetailsList();
        Integer newPeriodIndex = newBudgetPeriod.getBudgetPeriod();
        int totalPeriods = budgetPeriods.size();
        if(newPeriodIndex > totalPeriods) {
            budgetPeriods.add(newBudgetPeriod);
        }else {
            updateBudgetPeriods(budgetPeriods, newPeriodIndex, false);
            for(BudgetPeriod budgetPeriod: budgetPeriods) {
                List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
                updateBudgetLineItems(budgetLineItems, newPeriodIndex, true);
                for(BudgetLineItem periodLineItem: budgetLineItems) {
                    List<BudgetPersonnelDetails> budgetPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
                    updateBudgetPersonnelDetails(budgetPersonnelDetails, newPeriodIndex, true);
                }
            }
            //updateBudgetLineItems(budgetLineItems, newPeriodIndex, false);
            //updateBudgetPersonnelDetails(budgetPersonnelDetails, newPeriodIndex, false);
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

}
