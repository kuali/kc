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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.disclosure.AddDisclosureReporterUnitEvent;
import org.kuali.kra.coi.disclosure.SaveDisclosureReporterUnitEvent;
import org.kuali.kra.infrastructure.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 
 * This class is the main (fist) page of FE maintenance
 */
public class FinancialEntityManagementAction extends FinancialEntityAction {

    /**
     * 
     * This method is to add a reporter unit.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addFinancialEntityReporterUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        if (checkRule(new AddDisclosureReporterUnitEvent("financialEntityHelper.newFinancialEntityReporterUnit", financialEntityHelper.getNewFinancialEntityReporterUnit(),
            financialEntityHelper.getFinancialEntityReporter().getDisclosureReporterUnits()))) {
            getCoiDisclosureService().addDisclosureReporterUnit(
                    financialEntityHelper.getFinancialEntityReporter(),
                    financialEntityHelper.getNewFinancialEntityReporterUnit());
            financialEntityHelper.setNewFinancialEntityReporterUnit(new FinancialEntityReporterUnit());
            recordSubmitActionSuccess("Reporter Units add ");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to remove an unit from the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteFinancialEntityReporterUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        getCoiDisclosureService().deleteDisclosureReporterUnit(financialEntityHelper.getFinancialEntityReporter(), financialEntityHelper.getDeletedUnits(), getSelectedLine(request));
        recordSubmitActionSuccess("Reporter Units delete ");
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to save reporter units
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveFinancialEntityReporterUnits(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        getCoiDisclosureService().resetLeadUnit(financialEntityHelper.getFinancialEntityReporter());
        if (checkRule(new SaveDisclosureReporterUnitEvent("financialEntityHelper.financialEntityReporter",
            financialEntityHelper.getFinancialEntityReporter().getFinancialEntityReporterUnits()))) {
            if (!financialEntityHelper.getDeletedUnits().isEmpty()) {
                getBusinessObjectService().delete(financialEntityHelper.getDeletedUnits());
                financialEntityHelper.setDeletedUnits(new ArrayList<FinancialEntityReporterUnit>());
            }
            getBusinessObjectService().save(financialEntityHelper.getFinancialEntityReporter().getFinancialEntityReporterUnits());
            recordSubmitActionSuccess("Reporter Units save ");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method for 'close' button action.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {      
        return mapping.findForward(Constants.MAPPING_CLOSE);
    }

}
