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
package org.kuali.kra.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.replace;
import static org.kuali.RiceConstants.CONFIRMATION_QUESTION;
import static org.kuali.RiceConstants.EMPTY_STRING;
import static org.kuali.RiceConstants.QUESTION_CLICKED_BUTTON;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.exceptions.AuthorizationException;
import org.kuali.core.question.ConfirmationQuestion;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.notification.util.NotificationConstants;

import edu.iu.uis.eden.clientapp.IDocHandler;

// TODO : should move this class to org.kuali.kra.web.struts.action
public class KraTransactionalDocumentActionBase extends KualiTransactionalDocumentActionBase {
    private static final Log LOG = LogFactory.getLog(KraTransactionalDocumentActionBase.class);

    /**
     * By overriding the dispatchMethod(), we can check the user's authorization to
     * perform the given action/task.  
     * @see org.apache.struts.actions.DispatchAction#dispatchMethod(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
     */
    @Override
    protected ActionForward dispatchMethod(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           String taskName) throws Exception {
        
        ActionForward actionForward = null;
        if (!isTaskAuthorized(taskName, form)) {
            actionForward = processAuthorizationViolation(taskName, mapping, form, request, response);
        } 
        else {
            actionForward = super.dispatchMethod(mapping, form, request, response, taskName);
        }
        return actionForward;
    }

    @Override
    /**
     * Overriding headerTab to customize how clearing tab state works on PDForm.
     */
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ((KualiForm) form).setTabStates(new HashMap());

        return super.headerTab(mapping, form, request, response);
    }

    
    public ActionForward updateTextArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
        
        // parse out the important strings from our methodToCall parameter
        String fullParameter = (String) request.getAttribute(RiceConstants.METHOD_TO_CALL_ATTRIBUTE);

        // parse textfieldname:htmlformaction
        String parameterFields = StringUtils.substringBetween(fullParameter, RiceConstants.METHOD_TO_CALL_PARM2_LEFT_DEL, RiceConstants.METHOD_TO_CALL_PARM2_RIGHT_DEL);
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "fullParameter: " + fullParameter );
            LOG.debug( "parameterFields: " + parameterFields );
        }
        String[] keyValue = null;
        if (StringUtils.isNotBlank(parameterFields)) {
            String[] textAreaParams = parameterFields.split(RiceConstants.FIELD_CONVERSIONS_SEPERATOR);
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "lookupParams: " + textAreaParams );
            }
            for (int i = 0; i < textAreaParams.length; i++) {
                keyValue = textAreaParams[i].split(RiceConstants.FIELD_CONVERSION_PAIR_SEPERATOR);

                if ( LOG.isDebugEnabled() ) {
                    LOG.debug( "keyValue[0]: " + keyValue[0] );
                    LOG.debug( "keyValue[1]: " + keyValue[1] );
                }
            }
        }
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_NAME, keyValue[0]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.HTML_FORM_ACTION,keyValue[1]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_LABEL,keyValue[2]);
        if (form instanceof KualiForm && StringUtils.isNotEmpty(((KualiForm) form).getAnchor())) {
            request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_ANCHOR,((KualiForm) form).getAnchor());
        }

        return mapping.findForward("updateTextArea");

    }
    public ActionForward postTextAreaToParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("basic");
    }

    /**
     * Initiates a Confirmation. Part of the Question Framework for handling confirmations where a "yes" or "no" answer is required. <br/>
     * <br/>
     * A <code>yesMethodName</code> is provided as well as a <code>noMethodName</code>. These are callback methods for handling "yes" or "no"
     * responses.
     * 
     * @param question a bean containing question information for the delegated <code>{@link #performQuestionWithoutInput(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse, String, String, String, String, String)}</code> method.
     * @param yesMethodName "yes" response callback
     * @param noMethodName "no" response callback
     * @return
     * @throws Exception can be thrown as a result of a problem during dispatching 
     * @see https://test.kuali.org/confluence/x/EoFXAQ
     */
    public ActionForward confirm(StrutsConfirmation question, String yesMethodName, String noMethodName) throws Exception {
        // Figure out what the caller is. We want the direct caller of confirm()
        question.setCaller(((KualiForm) question.getForm()).getMethodToCall());
        LOG.info("Caller is " + question.getCaller());
        LOG.info("Setting caller from stacktrace " + Arrays.asList(new Throwable().getStackTrace()));
        LOG.info("Current action is " + getClass());
        
        if (question.hasQuestionInstAttributeName()) {
            Object buttonClicked = question.getRequest().getParameter(QUESTION_CLICKED_BUTTON);
            if (ConfirmationQuestion.YES.equals(buttonClicked) && isNotBlank(yesMethodName)) {
                return dispatchMethod(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(), yesMethodName);
            }
            else if (isNotBlank(noMethodName)) {
                return dispatchMethod(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(), noMethodName);
             }
        }
        else {
            return this.performQuestionWithoutInput(question, EMPTY_STRING);
        }

        return question.getMapping().findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Generically creates a <code>{@link StrutsConfirmation}</code> instance while deriving the question from a resource bundle.<br/>
     * <br/>
     * In this case, the question in the resource bundle is expected to be parameterized. This method takes this into account, and passes
     * parameters and replaces tokens in the question with the parameters.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the confirmation question
     * @throws Exception
     */
    protected StrutsConfirmation buildParameterizedConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String questionId, String configurationId, String ... params) throws Exception {
        StrutsConfirmation retval = new StrutsConfirmation();
        retval.setMapping(mapping);
        retval.setForm(form);
        retval.setRequest(request);
        retval.setResponse(response);
        retval.setQuestionId(questionId);
        retval.setQuestionType(CONFIRMATION_QUESTION);
        
        
        KualiConfigurationService kualiConfiguration = getService(KualiConfigurationService.class);
        String questionText = kualiConfiguration.getPropertyString(configurationId);
        
        for (int i = 0; i < params.length; i++) {
            questionText = replace(questionText, "{" + i + "}", params[i]);
        }
        retval.setQuestionText(questionText);
   
        return retval;
        
    }    
    
    /**
     * Wrapper around <code>{@link performQuestionWithoutInput(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)}</code> using
     * <code>{@link StrutsConfirmation}</code>  
     * 
     * @param question StrutsConfirmation
     * @param context
     * @return ActionForward
     * @throws Exception
     */
    protected ActionForward performQuestionWithoutInput(StrutsConfirmation question, String context) throws Exception {
        return this.performQuestionWithoutInput(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(), 
                                                question.getQuestionId(), question.getQuestionText(), question.getQuestionType(),
                                                question.getCaller(), context);
    }
    
    /**
     * Takes a routeHeaderId for a particular document and constructs the URL to forward to that document
     * 
     * @param routeHeaderId
     * @return String
     */
    protected String buildForwardUrl(Long routeHeaderId) {
        ResearchDocumentService researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        } else {
            forward += "&";
        }
        forward += IDocHandler.ROUTEHEADER_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + IDocHandler.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + IDocHandler.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getNetworkId();
        }
        return forward;
    }

    /**
     * Check the authorization for executing a task.  A task corresponds to a Struts action.
     * The name of a task always corresponds to the name of the Struts action method. 
     * @param form the submitted form
     * @param request the HTTP request
     * @throws AuthorizationException
     */
    private boolean isTaskAuthorized(String taskName, ActionForm form) {
        TaskAuthorizationService taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        String actionName = getActionName();
        Task task = buildTask(actionName, taskName, form);
        boolean isAuthorized = taskAuthorizationService.isAuthorized( username, task );
        if (!isAuthorized) {
            LOG.error("User not authorized to perform " + taskName + " for document: " + ((KualiDocumentFormBase)form).getDocument().getClass().getName() );
        }
        return isAuthorized;
    }
    
    /**
     * Get the name of the action.  This method is normally overridden.
     * @return the action's name
     */
    protected String getActionName() {
        return getClass().getSimpleName();
    }
    
    /**
     * Build a Task.  Subclasses may override this method to create a
     * different type of Task, e.g. one for proposals.
     * @param actionName the name of the action
     * @param taskName the name of the task
     * @param form the Form
     * @return the Task
     */
    protected Task buildTask(String actionName, String taskName, ActionForm form) {
        return new Task(actionName, taskName);
    }
    
    /**
     * Process an Authorization Violation.
     * @param mapping the Action Mapping
     * @param form the form
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the next action to go to
     * @throws Exception
     */
    protected ActionForward processAuthorizationViolation(String taskName, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        errorMap.putErrorWithoutFullErrorPath(Constants.TASK_AUTHORIZATION, KeyConstants.AUTHORIZATION_VIOLATION);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}
