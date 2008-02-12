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
package org.kuali.kra.budget.calculator;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;

public class BudgetPeriodCalculator {
    private BudgetCalculationService budgetCalculationService;
    public BudgetPeriodCalculator(){
        budgetCalculationService = getService(BudgetCalculationService.class);
        
    }
    public void calculate(BudgetDocument bd, BudgetPeriod period){
        period.setTotalDirectCost(BudgetDecimal.ZERO);
        period.setTotalIndirectCost(BudgetDecimal.ZERO);
        period.setCostSharingAmount(BudgetDecimal.ZERO);
        period.setTotalCost(BudgetDecimal.ZERO);
        period.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        List<BudgetLineItem> cvLineItemDetails = period.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : cvLineItemDetails) {
            budgetCalculationService.calculateBudgetLineItem(bd, budgetLineItem);
            period.setTotalDirectCost(period.getTotalDirectCost().add(budgetLineItem.getDirectCost()));
            period.setTotalIndirectCost(period.getTotalIndirectCost().add(budgetLineItem.getIndirectCost()));
            period.setTotalCost(period.getTotalIndirectCost().add(period.getTotalDirectCost()));
        }
//        BudgetDetailBean budgetDetailBean;
//        QueryList cvPersonnelLineItems;
//        int lineItemNo = 0;
//        Equals equalsBudgetPeriod = new Equals("budgetPeriod", new Integer(budgetPeriod));
//        Equals equalsLineItem;
//        And eQBudgetPeriodAndeQLineItem;
//        //Loop through all the budget line item details & calculate
//        if (cvLineItemDetails != null && cvLineItemDetails.size() > 0) {
//            int size = cvLineItemDetails.size();
//            for (int index = 0; index < size; index++) {
//                budgetDetailBean = (BudgetDetailBean) cvLineItemDetails.get(index);
//                
//                //Enhancement ID : 709 Case 3 - Starts here
//                //Check whether the cost element is having UR Rate
//                setURRateValidForCE(checkURRateValidForCE(budgetDetailBean));
//                //Enhancement ID : 709 Case 3 - Ends here
//                
//                //Check whether personnel line items are present
//                lineItemNo = budgetDetailBean.getLineItemNumber();
//                equalsLineItem = new Equals("lineItemNumber", new Integer(lineItemNo));
//                eQBudgetPeriodAndeQLineItem = new And(equalsBudgetPeriod, equalsLineItem);
//                
//                cvPersonnelLineItems = queryEngine.getActiveData(key,
//                                                BudgetPersonnelDetailsBean.class, eQBudgetPeriodAndeQLineItem);
//                if (cvPersonnelLineItems != null && cvPersonnelLineItems.size() > 0) {
//                    
//                    //Enhancement ID : 709 Case 3 - Starts here
//                    //calculatePersonnelLineItem(budgetPeriod, lineItemNo);
//                    calculatePersonnelLineItem(budgetPeriod, lineItemNo,
//                        budgetDetailBean.getCostElement());
//                    // Case Id #1811 - start
//                    filterPersonnelRateAndBase();
//                    // Case Id 1811 - End
//                    //Enhancement ID : 709 Case 3 - Ends here
//                    
//                } else {
//                    //Its a non personnel line item
//                    lineItemCalculator.setURRateValidForCE(isURRateValidForCE()); //Enhancement ID : 709 Case 3
//                    lineItemCalculator.calculate(budgetDetailBean);
//                   
//                }
//            }
//            //Get the list of rates not available messages
//            //vecMessages = lineItemCalculator.getVecMessages();
//            if (lineItemCalculator.getVecMessages().size() > 0) {
//                vecMessages.addAll(lineItemCalculator.getVecMessages());
//                //Initialize the vec messages for the next period
//                vecMessages.removeAllElements();
//                //lineItemCalculator = new LineItemCalculator();
//            }
//        }
//        //Refresh Budget Period costs
//        syncBudgetTotals();
        
        
    }
}
