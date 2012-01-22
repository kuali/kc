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
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * Action class for ProtocolCorrespondenceTemplate.
 */
public class ProtocolCorrespondenceTemplateAction extends KualiDocumentActionBase {

    // signifies that a response has already be handled therefore forwarding to obtain a response is not needed. 
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        // Check and initialize permissions
        if (getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(PermissionConstants.MODIFY_CORRESPONDENCE_TEMPLATE)) {
            ((ProtocolCorrespondenceTemplateForm) form).setReadOnly(false);
        } else if (getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(PermissionConstants.VIEW_CORRESPONDENCE_TEMPLATE)) {
            ((ProtocolCorrespondenceTemplateForm) form).setReadOnly(true);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        // initialize form on initial page load and on page reload to erase any old user data
        if (StringUtils.equals(request.getParameter("init"), "true")
                || StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.reload.y")) {
            ProtocolCorrespondenceTemplateForm templateForm = new ProtocolCorrespondenceTemplateForm();
            ((ProtocolCorrespondenceTemplateForm) form).setCorrespondenceTypes(templateForm.getCorrespondenceTypes());
            ((ProtocolCorrespondenceTemplateForm) form).setNewDefaultCorrespondenceTemplates(templateForm.getNewDefaultCorrespondenceTemplates());
            ((ProtocolCorrespondenceTemplateForm) form).setNewCorrespondenceTemplates(templateForm.getNewCorrespondenceTemplates());
            ((ProtocolCorrespondenceTemplateForm) form).setDeletedCorrespondenceTemplates(templateForm.getDeletedCorrespondenceTemplates());
            ((ProtocolCorrespondenceTemplateForm) form).setTabStates(new HashMap<String, String>());
        }
        
        return super.execute(mapping, form, request, response);
    }
    
    /**
     * 
     * This method is called when adding a default correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward addDefaultCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getNewDefaultCorrespondenceTemplates().get(typeIndex);

        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().addDefaultProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
            correspondenceTemplateForm.resetForm();
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when adding a committee specific correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward addCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getNewCorrespondenceTemplates().get(typeIndex);

        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().addCommitteeProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
            correspondenceTemplateForm.resetForm();
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when viewing a default correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward viewDefaultCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
        
        this.streamToResponse(correspondenceTemplate.getCorrespondenceTemplate(), correspondenceTemplate.getFileName(), 
                Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1, response);

        return RESPONSE_ALREADY_HANDLED;
    }
    
    /**
     * 
     * This method is called when viewing a committee specific correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward viewCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        int templateIndex = getSelectedCorrespondenceTemplate(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(templateIndex);
        
        this.streamToResponse(correspondenceTemplate.getCorrespondenceTemplate(), correspondenceTemplate.getFileName(), 
                Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1, response);

        return RESPONSE_ALREADY_HANDLED;
    }
    
    /**
     * 
     * This method is called when deleting a default correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward deleteDefaultCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
        
        // Add correspondence template to database deletion list
        correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(correspondenceTemplate);
        
        correspondenceType.getProtocolCorrespondenceTemplates().remove(correspondenceTemplate);
        correspondenceTemplateForm.resetForm();

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when deleting a committee specific correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward deleteCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        int templateIndex = getSelectedCorrespondenceTemplate(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(templateIndex);
        
        // Add correspondence template to database deletion list
        correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(correspondenceTemplate);
        
        correspondenceType.getProtocolCorrespondenceTemplates().remove(correspondenceTemplate);
        correspondenceTemplateForm.resetForm();

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when replacing a default correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward replaceDefaultCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getNewDefaultCorrespondenceTemplates().get(typeIndex);

        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex);
        if (rulePassed) {
            // Add correspondence template to database deletion list
            ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
            correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(correspondenceTemplate);
        
            correspondenceType.getProtocolCorrespondenceTemplates().remove(correspondenceTemplate);
            getProtocolCorrespondenceTemplateService().addDefaultProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
            correspondenceTemplateForm.resetForm();
        }
        

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when replacing a committee specific correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward replaceCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        int templateIndex = getSelectedCorrespondenceTemplate(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate oldCorrespondenceTemplate = correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(templateIndex);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getReplaceCorrespondenceTemplates()
                .get(typeIndex).getList().get(templateIndex);

        newCorrespondenceTemplate.setCommitteeId(oldCorrespondenceTemplate.getCommitteeId());
        
        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processReplaceProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex, templateIndex);
        if (rulePassed) {
            // Add correspondence template to database deletion list
            correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(oldCorrespondenceTemplate);
        
            correspondenceType.getProtocolCorrespondenceTemplates().remove(oldCorrespondenceTemplate);
            getProtocolCorrespondenceTemplateService().addCommitteeProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
            correspondenceTemplateForm.resetForm();
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is to get the protocol correspondence template service.
     * @return ProtocolCorrespondenceTemplateService
     */
    private ProtocolCorrespondenceTemplateService getProtocolCorrespondenceTemplateService() {
        return (ProtocolCorrespondenceTemplateService) KraServiceLocator.getService("protocolCorrespondenceTemplateService");
    }
    
    /**
     * This method returns the index of the selected correspondence type.
     * @param request
     * @return index
     */
    protected int getSelectedCorrespondenceType(HttpServletRequest request) {
        int index = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            index = Integer.parseInt(StringUtils.substringBetween(parameterName, "correspondenceType[", "]"));
        }
        return index;
    }

    /**
     * This method returns the index of the selected correspondence template.
     * @param request
     * @return index
     */
    protected int getSelectedCorrespondenceTemplate(HttpServletRequest request) {
        int index = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            index = Integer.parseInt(StringUtils.substringBetween(parameterName, "correspondenceTemplate[", "]"));
        }
        return index;
    }

    /**
     * 
     * This method is called when saving the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        // Check modify permission
        if (!getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(PermissionConstants.MODIFY_CORRESPONDENCE_TEMPLATE)) {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes, 
                correspondenceTemplateForm.getDeletedCorrespondenceTemplates());
            correspondenceTemplateForm.setDeletedCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplate>());
            KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when reloading the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_RELOADED);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when closing the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        
        if (getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(PermissionConstants.MODIFY_CORRESPONDENCE_TEMPLATE)) {
            if (!StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME), KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION)) {
                // Ask question whether to save before close
                actionForward = this.performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, 
                        getKualiConfigurationService().getPropertyValueAsString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), 
                        KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            } else if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Validate document
                ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
                List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
                boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
                if (!rulePassed) {
                    // Reload document if errors exist 
                    actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);                    
                } else {
                    // Save document
                    getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes, 
                        correspondenceTemplateForm.getDeletedCorrespondenceTemplates());
                    correspondenceTemplateForm.setDeletedCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplate>());
                    actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
                }
            }
        }
        
        return actionForward;
    }
    
    /**
     * 
     * This method is called when canceling the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward;
        
        if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME), KRADConstants.DOCUMENT_CANCEL_QUESTION)) {
            if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Cancel document and return to portal if cancel has been confirmed
                actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
            } else {
                // Reload document if cancel has been aborted 
                actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);
            }
        } else {
            // Ask question to confirm cancel
            actionForward = performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_CANCEL_QUESTION, 
                    getKualiConfigurationService().getPropertyValueAsString("document.question.cancel.text"), KRADConstants.CONFIRMATION_QUESTION, 
                    KRADConstants.MAPPING_CANCEL, "");
        }

        return actionForward;
    }
    
    private ProtocolCorrespondenceTemplateAuthorizationService getProtocolCorrespondenceTemplateAuthorizationService() {
        return KraServiceLocator.getService(ProtocolCorrespondenceTemplateAuthorizationService.class);
    }

}
