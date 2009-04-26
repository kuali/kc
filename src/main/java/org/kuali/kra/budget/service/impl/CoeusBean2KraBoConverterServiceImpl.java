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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.CoeusBean2KraBoConverterService;

import edu.mit.coeus.budget.bean.BudgetDetailBean;
import edu.mit.coeus.budget.bean.BudgetDetailCalAmountsBean;
import edu.mit.coeus.budget.bean.BudgetInfoBean;
import edu.mit.coeus.budget.bean.BudgetPeriodBean;
import edu.mit.coeus.budget.bean.BudgetPersonnelCalAmountsBean;
import edu.mit.coeus.budget.bean.BudgetPersonnelDetailsBean;

/**
 * This class...
 */
public class CoeusBean2KraBoConverterServiceImpl implements CoeusBean2KraBoConverterService {

    private static final Logger LOG = Logger.getLogger(CoeusBean2KraBoConverterServiceImpl.class);
    

    private void convert(Object des, Object src){
        try {
            BeanUtils.copyProperties(des, src);
        }
        catch (Exception e) {}
        
    }
    public BudgetPeriodBean convert(BudgetPeriod budgetPeriod) {
        BudgetPeriodBean budgetPeriodBean = new BudgetPeriodBean();
        convert(budgetPeriodBean, budgetPeriod);
        budgetPeriodBean.setVersionNumber(budgetPeriod.getBudgetVersionNumber());
        return budgetPeriodBean;
    }

    public BudgetDetailBean convert(BudgetLineItem budgetLineItem) {
        BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
        convert(budgetDetailBean,budgetLineItem);
        budgetDetailBean.setLineItemStartDate(budgetLineItem.getStartDate());
        budgetDetailBean.setLineItemEndDate(budgetLineItem.getEndDate());
        budgetDetailBean.setVersionNumber(budgetLineItem.getBudgetVersionNumber());
        budgetDetailBean.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
        budgetDetailBean.setLineItemDescription(budgetLineItem.getLineItemDescription());
        if(budgetLineItem.getCostElementBO()==null) budgetLineItem.refreshNonUpdateableReferences();
        budgetDetailBean.setCostElementDescription(budgetLineItem.getCostElementBO().getDescription());




        return budgetDetailBean;
    }

    public BudgetDetailCalAmountsBean convert(BudgetLineItemCalculatedAmount budgetLineItemCalcAmount) {
        BudgetDetailCalAmountsBean coeusBean = new BudgetDetailCalAmountsBean();
        
        if(budgetLineItemCalcAmount.getRateClass()==null) budgetLineItemCalcAmount.refreshNonUpdateableReferences();
        convert(coeusBean,budgetLineItemCalcAmount);
        coeusBean.setRateClassType(budgetLineItemCalcAmount.getRateClass().getRateClassType());
        coeusBean.setRateClassDescription(budgetLineItemCalcAmount.getRateClass().getDescription());
        coeusBean.setRateTypeDescription(budgetLineItemCalcAmount.getRateType().getDescription());
        coeusBean.setVersionNumber(budgetLineItemCalcAmount.getBudgetVersionNumber());

        return coeusBean;
    }

    public BudgetPersonnelDetailsBean convert(BudgetPersonnelDetails budgetPersonnelDetails) {
        BudgetPersonnelDetailsBean coeusBean = new BudgetPersonnelDetailsBean();
        convert(coeusBean,budgetPersonnelDetails);
        if(budgetPersonnelDetails.getBudgetPerson()==null) budgetPersonnelDetails.refreshNonUpdateableReferences();
        coeusBean.setFullName(budgetPersonnelDetails.getBudgetPerson().getPersonName());
        coeusBean.setPeriodType(budgetPersonnelDetails.getPeriodTypeCode());
        coeusBean.setVersionNumber(budgetPersonnelDetails.getBudgetVersionNumber());
        
        return coeusBean;
    }

    public BudgetPersonnelCalAmountsBean convert(BudgetPersonnelCalculatedAmount budgetPersonnelCalAmount) {
        BudgetPersonnelCalAmountsBean coeusBean = new BudgetPersonnelCalAmountsBean();
        if(budgetPersonnelCalAmount.getRateClass()==null) budgetPersonnelCalAmount.refreshNonUpdateableReferences();
        convert(coeusBean,budgetPersonnelCalAmount);
        coeusBean.setRateClassType(budgetPersonnelCalAmount.getRateClass().getRateClassType());
        coeusBean.setRateClassDescription(budgetPersonnelCalAmount.getRateClass().getDescription());
        coeusBean.setRateTypeDescription(budgetPersonnelCalAmount.getRateType().getDescription());
        coeusBean.setVersionNumber(budgetPersonnelCalAmount.getBudgetVersionNumber());
        return coeusBean;
    }
    public BudgetInfoBean convert(BudgetDocument budgetDocument) {
        BudgetInfoBean budgetInfoBean = new BudgetInfoBean();
        convert(budgetInfoBean, budgetDocument);
        budgetInfoBean.setVersionNumber(budgetDocument.getBudgetVersionNumber());
        return budgetInfoBean;
        
    }
    
}
