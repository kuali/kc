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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * Action class for ProtocolCorrespondenceTemplateBase.
 */
public abstract class ProtocolCorrespondenceTemplateActionBase extends KualiDocumentActionBase {

    // signifies that a response has already be handled therefore forwarding to obtain a response is not needed. 
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        // Check and initialize permissions       
        if (getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(getModifyCorrespondenceTemplatePermissionNameHook())) {
            ((ProtocolCorrespondenceTemplateFormBase) form).setReadOnly(false);
        } else if (getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(getViewCorrespondenceTemplatePermissionNameHook())) {
            ((ProtocolCorrespondenceTemplateFormBase) form).setReadOnly(true);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        // initialize form on initial page load and on page reload to erase any old user data
        if (StringUtils.equals(request.getParameter("init"), "true")
                || StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.reload.y")) {
            ProtocolCorrespondenceTemplateFormBase templateForm = getNewProtocolCorrespondenceTemplateFormInstanceHook();
            ((ProtocolCorrespondenceTemplateFormBase) form).setCorrespondenceTypes(templateForm.getCorrespondenceTypes());
            ((ProtocolCorrespondenceTemplateFormBase) form).setNewDefaultCorrespondenceTemplates(templateForm.getNewDefaultCorrespondenceTemplates());
            ((ProtocolCorrespondenceTemplateFormBase) form).setNewCorrespondenceTemplates(templateForm.getNewCorrespondenceTemplates());
            ((ProtocolCorrespondenceTemplateFormBase) form).setDeletedCorrespondenceTemplates(templateForm.getDeletedCorrespondenceTemplates());
            ((ProtocolCorrespondenceTemplateFormBase) form).setTabStates(new HashMap<String, String>());
        }
        
        return super.execute(mapping, form, request, response);
    }
    
    
    protected abstract String getModifyCorrespondenceTemplatePermissionNameHook();
    
    protected abstract String getViewCorrespondenceTemplatePermissionNameHook();

    protected abstract ProtocolCorrespondenceTemplateFormBase getNewProtocolCorrespondenceTemplateFormInstanceHook();
    
    
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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate = correspondenceTemplateForm.getNewDefaultCorrespondenceTemplates().get(typeIndex);

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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate = correspondenceTemplateForm.getNewCorrespondenceTemplates().get(typeIndex);

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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
        
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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase correspondenceTemplate = correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(templateIndex);
        
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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
        
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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase correspondenceTemplate = correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(templateIndex);
        
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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate = correspondenceTemplateForm.getNewDefaultCorrespondenceTemplates().get(typeIndex);

        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex);
        if (rulePassed) {
            // Add correspondence template to database deletion list
            ProtocolCorrespondenceTemplateBase correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
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
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        ProtocolCorrespondenceTypeBase correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplateBase oldCorrespondenceTemplate = correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(templateIndex);
        ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate = correspondenceTemplateForm.getReplaceCorrespondenceTemplates()
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
        return KcServiceLocator.getService(getProtocolCorrespondenceTemplateServiceClassHook());
    }
    
    protected abstract Class<? extends ProtocolCorrespondenceTemplateService> getProtocolCorrespondenceTemplateServiceClassHook();
    
    
    

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
        if (!getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(getModifyCorrespondenceTemplatePermissionNameHook())) { 
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes, 
                correspondenceTemplateForm.getDeletedCorrespondenceTemplates());
            correspondenceTemplateForm.setDeletedCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplateBase>());
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
        if (getProtocolCorrespondenceTemplateAuthorizationService().hasPermission(getModifyCorrespondenceTemplatePermissionNameHook())) {
            if (!StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME), KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION)) {
                // Ask question whether to save before close
                actionForward = this.performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, 
                        getKualiConfigurationService().getPropertyValueAsString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), 
                        KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            } else if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Validate document
                ProtocolCorrespondenceTemplateFormBase correspondenceTemplateForm = (ProtocolCorrespondenceTemplateFormBase) form;
                List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
                boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
                if (!rulePassed) {
                    // Reload document if errors exist 
                    actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);                    
                } else {
                    // Save document
                    getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes, 
                        correspondenceTemplateForm.getDeletedCorrespondenceTemplates());
                    correspondenceTemplateForm.setDeletedCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplateBase>());
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
        return KcServiceLocator.getService(getProtocolCorrespondenceTemplateAuthorizationServiceClassHook());
    }

    protected abstract Class<? extends ProtocolCorrespondenceTemplateAuthorizationService> getProtocolCorrespondenceTemplateAuthorizationServiceClassHook();
    

}
