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
