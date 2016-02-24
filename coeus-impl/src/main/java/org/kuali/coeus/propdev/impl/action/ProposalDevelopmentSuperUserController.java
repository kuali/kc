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
package org.kuali.coeus.propdev.impl.action;


import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentSuperUserController extends ProposalDevelopmentControllerBase {


    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserTakeActions")
    public ModelAndView superUserTakeActions(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if(!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            form.setEvaluateFlagsAndModes(true);
            return getTransactionalDocumentControllerService().superUserTakeActions(form);
        }
        else{
            return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserApprove")
    public ModelAndView superUserApprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if(!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            form.setEvaluateFlagsAndModes(true);
            return getTransactionalDocumentControllerService().superUserApprove(form);
        }
        else{
            return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserDisapprove")
    public ModelAndView superUserDisapprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        form.setEvaluateFlagsAndModes(true);
        return getTransactionalDocumentControllerService().superUserDisapprove(form);
    }
}
