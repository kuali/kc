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
package org.kuali.kra.budget.service;

import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;

import edu.mit.coeus.budget.bean.BudgetDetailBean;
import edu.mit.coeus.budget.bean.BudgetDetailCalAmountsBean;
import edu.mit.coeus.budget.bean.BudgetInfoBean;
import edu.mit.coeus.budget.bean.BudgetPeriodBean;
import edu.mit.coeus.budget.bean.BudgetPersonnelCalAmountsBean;
import edu.mit.coeus.budget.bean.BudgetPersonnelDetailsBean;

public interface CoeusBean2KraBoConverterService {
    public BudgetInfoBean convert(BudgetDocument budgetDocument);
    public BudgetPeriodBean convert(BudgetPeriod budgetPeriod);
    public BudgetDetailBean convert(BudgetLineItem budgetLineItem);
    public BudgetDetailCalAmountsBean convert(BudgetLineItemCalculatedAmount budgetLineItemCalcAmount);
    public BudgetPersonnelDetailsBean convert(BudgetPersonnelDetails  coeusBudgetPersonnelDetailsBean);
    public BudgetPersonnelCalAmountsBean convert(BudgetPersonnelCalculatedAmount coeusBudgetPersonnelCalAmount);
}
