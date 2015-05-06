/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.datavalidation;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.rice.krad.uif.UifParameters;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class ProposalDevelopmentDataValidationController extends ProposalDevelopmentControllerBase {


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=validateData")
    public ModelAndView validateData(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (form.isAuditActivated()) {
            form.setDataValidationItems(((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateDataValidation(form));
        }

        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=toggleValidation")
    public ModelAndView toggleValidation(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.setAuditActivated(!form.isAuditActivated());
        if(form.isAuditActivated()) {
            form.setDataValidationItems(((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateDataValidation(form));
        }
        return getRefreshControllerService().refresh(form);

    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=navigateToError")
    public ModelAndView navigateToError(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
        if (StringUtils.equals(pageId,ProposalDevelopmentDataValidationConstants.CREDIT_ALLOCATION_PAGE_ID)) {
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateCreditSplits(form);
        }

        if (StringUtils.equals(pageId,ProposalDevelopmentDataValidationConstants.QUESTIONNAIRE_PAGE_ID)) {
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateQuestionnaires(form);
        }

        if (StringUtils.equals(pageId,ProposalDevelopmentDataValidationConstants.SUPPLEMENTAL_PAGE_ID)) {
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateCustomData(form);
        }
        getAuditHelper().auditConditionally(form);
        form.setAjaxReturnType("update-page");
        return getNavigationControllerService().navigate(form);
    }
}
