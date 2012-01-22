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
package org.kuali.kra.irb.actions.notification;

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
 * This is an action class for protocol action notification template maintenance.
 */
public class ProtocolNotificationTemplateAction extends KualiDocumentActionBase {
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Check and initialize permissions
        if (getProtocolNotificationTemplateAuthorizationService().hasPermission(PermissionConstants.MODIFY_NOTIFICATION_TEMPLATE)) {
            ((ProtocolNotificationTemplateForm) form).setReadOnly(false);
        }
        else if (getProtocolNotificationTemplateAuthorizationService()
                .hasPermission(PermissionConstants.VIEW_NOTIFICATION_TEMPLATE)) {
            ((ProtocolNotificationTemplateForm) form).setReadOnly(true);
        }
        else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), findMethodToCall(
                    form, request), this.getClass().getSimpleName());
        }

        // initialize form on initial page load and on page reload to erase any old user data
        if (StringUtils.equals(request.getParameter("init"), "true")
                || (StringUtils.isNotBlank((String) request.getAttribute("methodToCallAttribute")) && ((String) request
                        .getAttribute("methodToCallAttribute")).startsWith("methodToCall.reload."))) {
            ProtocolNotificationTemplateForm templateForm = (ProtocolNotificationTemplateForm) form;
            templateForm.setNotificationTemplates(templateForm.initNotificationTemplates());
        }

        return super.execute(mapping, form, request, response);
    }

    /**
     * 
     * This method is called when reloading the notification templates.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_RELOADED);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is called when closing the notification templates.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);

        if (getProtocolNotificationTemplateAuthorizationService().hasPermission(PermissionConstants.MODIFY_CORRESPONDENCE_TEMPLATE)) {
            if (!StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME),
                    KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION)) {
                // Ask question whether to save before close
                actionForward = this.performQuestionWithoutInput(mapping, form, request, response,
                        KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, getKualiConfigurationService().getPropertyValueAsString(
                                RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), KRADConstants.CONFIRMATION_QUESTION,
                        KRADConstants.MAPPING_CLOSE, "");
            }
            else if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Validate document
                ProtocolNotificationTemplateForm notificationTemplateForm = (ProtocolNotificationTemplateForm) form;
                getBusinessObjectService().save(notificationTemplateForm.getNotificationTemplates());
                actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
                // }
            }
        }

        return actionForward;
    }

    /**
     * save protocol notification template.
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Check modify permission
        if (!getProtocolNotificationTemplateAuthorizationService()
                .hasPermission(PermissionConstants.MODIFY_CORRESPONDENCE_TEMPLATE)) {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), findMethodToCall(
                    form, request), this.getClass().getSimpleName());
        }

        ProtocolNotificationTemplateForm notificationTemplateForm = (ProtocolNotificationTemplateForm) form;
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);
        getBusinessObjectService().save(notificationTemplateForm.getNotificationTemplates());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is called when canceling the notification templates.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;

        if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME),
                KRADConstants.DOCUMENT_CANCEL_QUESTION)) {
            if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Cancel document and return to portal if cancel has been confirmed
                actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
            }
            else {
                // Reload document if cancel has been aborted
                actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);
            }
        }
        else {
            // Ask question to confirm cancel
            actionForward = performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_CANCEL_QUESTION,
                    getKualiConfigurationService().getPropertyValueAsString("document.question.cancel.text"),
                    KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CANCEL, "");
        }

        return actionForward;
    }

    /**
     * 
     * This method replace the template with the uploaded one.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward replaceNotificationTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int templateIndex = getSelectedNotificationTemplate(request);
        ProtocolNotificationTemplateForm notificationTemplateForm = (ProtocolNotificationTemplateForm) form;
        ProtocolNotificationTemplate notificationTemplate = notificationTemplateForm.getNotificationTemplates().get(templateIndex);

        // check any business rules
        boolean rulePassed = new ProtocolNotificationTemplateRule().processReplaceProtocolNotificationTemplateRules(
                notificationTemplate, templateIndex);
        if (rulePassed) {
            notificationTemplate.setFileName(notificationTemplate.getTemplateFile().getFileName());
            notificationTemplate.setNotificationTemplate(notificationTemplate.getTemplateFile().getFileData());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method to view the selected notification template.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewNotificationTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int templateIndex = getSelectedNotificationTemplate(request);
        ProtocolNotificationTemplateForm notificationTemplateForm = (ProtocolNotificationTemplateForm) form;
        ProtocolNotificationTemplate notificationTemplate = notificationTemplateForm.getNotificationTemplates().get(templateIndex);

        this.streamToResponse(notificationTemplate.getNotificationTemplate(), notificationTemplate.getFileName(),
                Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1, response);

        return RESPONSE_ALREADY_HANDLED;
    }

    /*
     * the index of the notification template in the list.
     */
    private int getSelectedNotificationTemplate(HttpServletRequest request) {
        int index = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            index = Integer.parseInt(StringUtils.substringBetween(parameterName, "notificationTemplates[", "]"));
        }
        return index;
    }

    private ProtocolNotificationTemplateAuthorizationService getProtocolNotificationTemplateAuthorizationService() {
        return KraServiceLocator.getService(ProtocolNotificationTemplateAuthorizationService.class);
    }

}
