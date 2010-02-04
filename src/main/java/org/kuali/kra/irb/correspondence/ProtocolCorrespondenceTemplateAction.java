/*
 * Copyright 2006-2010 The Kuali Foundation
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
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;

/**
 * 
 * Action class for ProtocolCorrespondenceTemplate.
 */
public class ProtocolCorrespondenceTemplateAction extends KualiDocumentActionBase {

    // signifies that a response has already be handled therefore forwarding to obtain a response is not needed. 
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // initialize form on initial page load and on page reload to erase any old user data
    	 Object attr = request.getAttribute("methodToCallAttribute");
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
            getProtocolCorrespondenceTemplateService().addProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
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
                Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE, response);

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
                Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE, response);

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
        
        // Add correspondence template to database deletion list
        ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getDefaultProtocolCorrespondenceTemplate();
        correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(correspondenceTemplate);
        
        getProtocolCorrespondenceTemplateService().deleteDefaultProtocolCorrespondenceTemplate(correspondenceType);
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
        
        // Add correspondence template to database deletion list
        ProtocolCorrespondenceTemplate correspondenceTemplate = correspondenceType.getProtocolCorrespondenceTemplates().get(templateIndex);
        correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(correspondenceTemplate);
        
        getProtocolCorrespondenceTemplateService().deleteProtocolCorrespondenceTemplate(correspondenceType, templateIndex);
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
        
            getProtocolCorrespondenceTemplateService().deleteDefaultProtocolCorrespondenceTemplate(correspondenceType);
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
        ProtocolCorrespondenceTemplate oldCorrespondenceTemplate = correspondenceType.getProtocolCorrespondenceTemplates().get(templateIndex);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getReplaceCorrespondenceTemplates().get(typeIndex).getList().get(templateIndex);

        newCorrespondenceTemplate.setCommitteeId(oldCorrespondenceTemplate.getCommitteeId());
        
        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processReplaceProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex, templateIndex);
        if (rulePassed) {
            // Add correspondence template to database deletion list
            correspondenceTemplateForm.getDeletedCorrespondenceTemplates().add(oldCorrespondenceTemplate);
        
            getProtocolCorrespondenceTemplateService().deleteProtocolCorrespondenceTemplate(correspondenceType, templateIndex);
            getProtocolCorrespondenceTemplateService().addProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
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
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
        
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes, 
                correspondenceTemplateForm.getDeletedCorrespondenceTemplates());
            correspondenceTemplateForm.setDeletedCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplate>());
            GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);
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
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_RELOADED);
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
        if (StringUtils.equals(request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME), KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION)) {
        	if (StringUtils.equals(request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
        	    // Validate document
                ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
                List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
                boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
                if (!rulePassed) {
            		// Reload document if errors exist 
            		return mapping.findForward(RiceConstants.MAPPING_BASIC);                	
                }

                // Save document
                getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes, 
                    correspondenceTemplateForm.getDeletedCorrespondenceTemplates());
                correspondenceTemplateForm.setDeletedCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplate>());
        	}

            // Return to portal
       	    return mapping.findForward(KNSConstants.MAPPING_PORTAL);
        }
        
        // Ask question whether to save before close
    	return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, 
    			getKualiConfigurationService().getPropertyString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), 
    			KNSConstants.CONFIRMATION_QUESTION, KNSConstants.MAPPING_CLOSE, "");
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
        if (StringUtils.equals(request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME), KNSConstants.DOCUMENT_CANCEL_QUESTION)) {
        	if (StringUtils.equals(request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
        		// Cancel document and return to portal if cancel has been confirmed
        		return mapping.findForward(KNSConstants.MAPPING_PORTAL);
        	} else {
        		// Reload document if cancel has been aborted 
        		return mapping.findForward(RiceConstants.MAPPING_BASIC);
        	}
        }

        // Ask question to confirm cancel
        return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_CANCEL_QUESTION, 
        		getKualiConfigurationService().getPropertyString("document.question.cancel.text"), KNSConstants.CONFIRMATION_QUESTION, 
        		KNSConstants.MAPPING_CANCEL, "");
    }
}
