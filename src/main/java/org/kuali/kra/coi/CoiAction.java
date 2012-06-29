/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.actions.CoiDisclosureActionService;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.coi.notification.CoiNotificationContext;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krms.service.KrmsRulesExecutionService;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;

public abstract class CoiAction extends KraTransactionalDocumentActionBase {
    protected static final String MASTER_DISCLOSURE = "masterDisclosure";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        if (coiDisclosureForm.isAuditActivated()){
            coiDisclosureForm.setUnitRulesMessages(getUnitRulesMessages(coiDisclosureForm.getCoiDisclosureDocument()));
        }
        if(KNSGlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally(coiDisclosureForm);
        }
        
        return forward;
    }
    
    public ActionForward disclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((CoiDisclosureForm)form).getDisclosureHelper().prepareView();
        // this is a hook to make sure coidisclprojects's detail is populated before returning to main page
        // once the table relation is normalized, then this is not necessary
        // kccoi-110
         CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        /*
        if (coiDisclosure.isManualEvent() && "headerTab".equals(coiDisclosureForm.getMethodToCall()) 
                   && "disclosure".equals(coiDisclosureForm.getNavigateTo()) && !coiDisclosureForm.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isSaved()
                   && !coiDisclosureForm.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isInitiated()) {
            coiDisclosure.getCoiDisclProjects().get(0).setCoiDiscDetails(coiDisclosure.getCoiDiscDetails());
        }
        */
        
        // initialize the questionnaire data
        coiDisclosureForm.getDisclosureQuestionnaireHelper().prepareView(false);
        // initialize the permissions for notes and attachments helper
        coiDisclosureForm.getCoiNotesAndAttachmentsHelper().prepareView();
        return mapping.findForward("disclosure");
    }
    public ActionForward committee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("committee");
    }

    public ActionForward certification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("certification");
    }
    public ActionForward noteAndAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper().prepareView();
        return mapping.findForward("noteAndAttachment");
    }
    public ActionForward disclosureActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("disclosureActions");
    }


    protected CoiDisclosureService getCoiDisclosureService() {
        return KraServiceLocator.getService(CoiDisclosureService.class);
    }

    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
    protected CoiDisclosureActionService getCoiDisclosureActionService() {
        return KraServiceLocator.getService(CoiDisclosureActionService.class);
    }

    protected boolean checkRule(KraDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }


    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForward forward, 
                                          CoiDisclosureForm coiDisclosureForm, CoiNotificationContext context) {       
        if (coiDisclosureForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            coiDisclosureForm.getNotificationHelper().initializeDefaultValues(context);
            forward = mapping.findForward("coiDisclosureNotificationEditor");

        } else {
            getNotificationService().sendNotification(context);
        }
        
        return forward;
    }

    protected KcNotificationService getNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    @SuppressWarnings("deprecation")
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    protected List<String> getUnitRulesMessages(CoiDisclosureDocument coiDisclosureDoc) {
        KrmsRulesExecutionService rulesService = KraServiceLocator.getService(KrmsRulesExecutionService.class);
        return rulesService.processUnitValidations(coiDisclosureDoc.getCoiDisclosure().getLeadUnitNumber(), coiDisclosureDoc);
    }
    
    /**
     * 
     * This method is to activate data validation
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        coiDisclosureForm.setUnitRulesMessages(getUnitRulesMessages(coiDisclosureForm.getCoiDisclosureDocument()));
        return new AuditActionHelper().setAuditMode(mapping, coiDisclosureForm, true);
    }

    /**
     * 
     * This method is to deactivate data validation
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ((CoiDisclosureForm) form).clearUnitRulesMessages();
        return new AuditActionHelper().setAuditMode(mapping, (CoiDisclosureForm) form, false);
    }


}
