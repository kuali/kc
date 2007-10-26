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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.rule.event.DocumentAuditEvent;
import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;

public class ProposalDevelopmentAction extends KraTransactionalDocumentActionBase {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String keywordPanelDisplay = KNSServiceLocator.getKualiConfigurationService().getParameterValue(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.KEYWORD_PANEL_DISPLAY);
        request.setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
        // TODO: not sure it's should be here - for audit error display. 
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.isAuditActivated()) {
            KNSServiceLocator.getBean(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
        }
        return super.execute(mapping, form, request, response);
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        return super.save(mapping, form, request, response);
    }

    public ActionForward proposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("proposal");
    }

    public ActionForward keyPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("keyPersonnel");
    }

    public ActionForward template(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("template");
    }

    public ActionForward notes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("notes");
    }

    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("specialReview");
    }

    public ActionForward abstractsAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // TODO temporarily to set up proposal person- remove this once keyperson is completed and htmlunit testing fine
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        if (doc.getProposalPersons().isEmpty()) {
            List proposalPersons = new ArrayList();
            ProposalPerson proposalPerson=new ProposalPerson();
            proposalPerson.setProposalNumber(doc.getProposalNumber());
            proposalPerson.setProposalPersonNumber(1);
            proposalPerson.setPersonId("000000001");
            proposalPerson.setProposalPersonRoleId("KP");
            proposalPerson.setFirstName("Terry");
            proposalPerson.setLastName("Durkin");
            proposalPerson.setFullName("Durkin,Terry");
            proposalPersons.add(proposalPerson);
            ProposalPerson proposalPerson2=new ProposalPerson();
            proposalPerson2.setProposalNumber(doc.getProposalNumber());
            proposalPerson2.setProposalPersonNumber(2);
            proposalPerson2.setProposalPersonRoleId("KP");
            proposalPerson2.setPersonId("000000003");
            proposalPerson2.setFirstName("Geoff");
            proposalPerson2.setLastName("McGregor");
            proposalPerson2.setFullName("McGregor,Geoff");
            proposalPersons.add(proposalPerson2);
            doc.setProposalPersons(proposalPersons);
        }
        return mapping.findForward("abstractsAttachments");
    }

    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("customData");
    }

    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("actions");
    }

    /**
     * This method processes an auditMode action request
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward to forward to ("auditMode")
     */
    public ActionForward auditMode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.isAuditActivated()) {
            KNSServiceLocator.getBean(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
        }
         return mapping.findForward("auditMode");
    }
}
