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
package org.kuali.kra.coi;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.controller.AuditActionHelper;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.actions.CoiDisclosureActionService;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.coi.disclosure.CoiGroupedMasterDisclosureBean;
import org.kuali.kra.coi.disclosure.DisclosureHelper;
import org.kuali.kra.coi.disclosure.MasterDisclosureBean;
import org.kuali.kra.coi.notification.CoiNotification;
import org.kuali.kra.coi.notification.CoiNotificationContext;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class CoiAction extends KcTransactionalDocumentActionBase {
    protected static final String MASTER_DISCLOSURE = "masterDisclosure";
    protected static final String UPDATE_DISCLOSURE = "updateDisclosure";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        if (coiDisclosureForm.isAuditActivated()){
            coiDisclosureForm.setUnitRulesMessages(getUnitRulesMessages(coiDisclosureForm.getCoiDisclosureDocument()));
        }
        if(KNSGlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally(coiDisclosureForm);
        }
                
        return super.execute(mapping, form, request, response);
    }    
    
    public ActionForward disclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        prepareDisclosure(coiDisclosureForm);
        return mapping.findForward("disclosure"); 
    }
    
    public ActionForward coiDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        prepareDisclosure(coiDisclosureForm);
        return getDisclosureActionForward(coiDisclosureForm, mapping); 
    }
    
    private void prepareDisclosure(CoiDisclosureForm coiDisclosureForm) {
        coiDisclosureForm.getDisclosureHelper().prepareView();
        // initialize the questionnaire data
        coiDisclosureForm.getDisclosureQuestionnaireHelper().prepareView(false);
        coiDisclosureForm.getScreeningQuestionnaireHelper().prepareView(false);
        // initialize the permissions for notes and attachments helper
        coiDisclosureForm.getCoiNotesAndAttachmentsHelper().prepareView();
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
        return KcServiceLocator.getService(CoiDisclosureService.class);
    }

    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    }
    
    protected CoiDisclosureActionService getCoiDisclosureActionService() {
        return KcServiceLocator.getService(CoiDisclosureActionService.class);
    }

    protected boolean checkRule(KcDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }


    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    }
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForward forward, 
                                          CoiDisclosureForm coiDisclosureForm, CoiNotificationContext context) {       
        if (coiDisclosureForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            coiDisclosureForm.getNotificationHelper().initializeDefaultValues(context);
            forward = mapping.findForward("coiDisclosureNotificationEditor");

        } else {
            getNotificationService().sendNotificationAndPersist(context, new CoiNotification(), coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure());
        }
        
        return forward;
    }

    protected KcNotificationService getNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    @SuppressWarnings("deprecation")
    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    protected List<String> getUnitRulesMessages(CoiDisclosureDocument coiDisclosureDoc) {
        KrmsRulesExecutionService rulesService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
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
        new AuditActionHelper().setAuditMode(mapping, coiDisclosureForm, true);
        return getDisclosureActionForward(coiDisclosureForm, mapping);
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
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        coiDisclosureForm.clearUnitRulesMessages();
        new AuditActionHelper().setAuditMode(mapping, coiDisclosureForm, false);
        return getDisclosureActionForward(coiDisclosureForm, mapping);
    }
    
    protected ActionForward getDisclosureActionForward(CoiDisclosureForm coiDisclosureForm, ActionMapping mapping) {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
            actionForward = mapping.findForward(UPDATE_DISCLOSURE);
        }
        return actionForward;
    }


    public ActionForward viewProjectDisclosuresByFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        MasterDisclosureBean masterDisclosureBean = disclosureHelper.getMasterDisclosureBean();
        getCoiDisclosureService().createDisclosuresGroupedByFinancialEntity(coiDisclosure, masterDisclosureBean);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewProjectDisclosuresByEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        MasterDisclosureBean masterDisclosureBean = disclosureHelper.getMasterDisclosureBean();
        getCoiDisclosureService().createDisclosuresGroupedByEvent(masterDisclosureBean);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    public ActionForward viewUndisclosedProjectsByFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        setUndisclosedProjects(false, form);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewUndisclosedProjectsByEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        setUndisclosedProjects(true, form);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private void setUndisclosedProjects(boolean isGroupedByEvent, ActionForm form) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        coiDisclosureForm.getDisclosureHelper().setDisclosureGroupedByEvent(isGroupedByEvent);
        List<CoiGroupedMasterDisclosureBean> groupedUndisclosedProjects = new ArrayList<CoiGroupedMasterDisclosureBean>(); 
        if(isGroupedByEvent) {
            groupedUndisclosedProjects = getCoiDisclosureService().getUndisclosedProjectsGroupedByEvent(getCoiDisclosureProjects(coiDisclosureForm)); 
        }else {
            groupedUndisclosedProjects = getCoiDisclosureService().getUndisclosedProjectsGroupedByFinancialEntity(getCoiDisclosureProjects(coiDisclosureForm)); 
        }
        coiDisclosureForm.getDisclosureHelper().setAllDisclosuresGroupedByProjects(groupedUndisclosedProjects);
    }
    
    protected List<CoiDisclProject> getCoiDisclosureProjects(CoiDisclosureForm coiDisclosureForm) {
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)coiDisclosureForm.getDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        return coiDisclosure.getCoiDisclProjects();
    }

}
