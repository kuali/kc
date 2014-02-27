/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureAmountsInDateRangeRule;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryService;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpendituresDataGenerationForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@SuppressWarnings("deprecation")
public class SubcontractingExpendituresDataGenerationAction  extends KualiAction {
    
    
    // invoked only on the initial visit from the portal, all subsequent calls will be to other methods
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward regenerateExpenditureDataForAllDates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        getExpenditureCategoryServiceImpl().populateAllAvailableCategoryExpenses();
        // add the successful regeneration message for display 
        KNSGlobalVariables.getMessageList().add(KeyConstants.EXPENDITURE_DATA_REGENERATED_ALL_DATES);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward regenerateExpenditureDataInDateRange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubcontractingExpendituresDataGenerationForm expendituresDataGenerationForm = (SubcontractingExpendituresDataGenerationForm) form;
        Date rangeStartDate = expendituresDataGenerationForm.getRangeStartDate();
        Date rangeEndDate = expendituresDataGenerationForm.getRangeEndDate();
        if( (new SubcontractingExpenditureAmountsInDateRangeRule()).validateDateRange(rangeStartDate, rangeEndDate) ) {
            getExpenditureCategoryServiceImpl().populateCategoryExpensesInDateRange(rangeStartDate, rangeEndDate);
            // add the successful regeneration message for display 
            KNSGlobalVariables.getMessageList().add(KeyConstants.EXPENDITURE_DATA_REGENERATED_IN_RANGE, new String[]{rangeStartDate.toString(), rangeEndDate.toString()});
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private SubcontractingExpenditureCategoryService getExpenditureCategoryServiceImpl() {
        return KcServiceLocator.getService(SubcontractingExpenditureCategoryService.class);
    } 
    

}
