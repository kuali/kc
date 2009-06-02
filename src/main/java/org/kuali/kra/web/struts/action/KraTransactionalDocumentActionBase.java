/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.kns.util.KNSConstants.CONFIRMATION_QUESTION;
import static org.kuali.rice.kns.util.KNSConstants.EMPTY_STRING;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.authorization.WebAuthorizationService;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.exception.AuthorizationException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;

// TODO : should move this class to org.kuali.kra.web.struts.action
public class KraTransactionalDocumentActionBase extends KualiTransactionalDocumentActionBase {
    private static final Log LOG = LogFactory.getLog(KraTransactionalDocumentActionBase.class);
    
    private static final String DEFAULT_TAB = "budgetVersions";
    private static final String ALTERNATE_OPEN_TAB = "budgetSummary";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (((KualiDocumentFormBase) form).getErrorMapFromPreviousRequest() == null) {
            ((KualiDocumentFormBase) form).setErrorMapFromPreviousRequest(new ErrorMap());
        }
        ActionForward returnForward = mapping.findForward(Constants.MAPPING_BASIC);
        returnForward = super.execute(mapping, form, request, response);
        
        // TODO This section was added to make Rice 1.1 work.  It should happen in KualiRequestProcessor.process but that code doesn't work.
        KualiDocumentFormBase formBase = (KualiDocumentFormBase) form;
        Document document = formBase.getDocument();
        UserSession userSession = (UserSession) request.getSession().getAttribute(KNSConstants.USER_SESSION_KEY);
        if (document instanceof SessionDocument) {
            String formKey = formBase.getFormKey();
            if (StringUtils.isBlank(formBase.getFormKey()) || userSession.retrieveObject(formBase.getFormKey()) == null) {
        // generate doc form key here if it does not exist
                formKey = GlobalVariables.getUserSession().addObject(form);
                formBase.setFormKey(formKey);
        //}  else {
           // GlobalVariables.getUserSession().addObject(formBase.getFormKey(),form);                    
            }
        }
        // End Rice 1.1 hack
        
        return returnForward;
    }

    /**
     * By overriding the dispatchMethod(), we can check the user's authorization to perform the given action/task.
     * 
     * @see org.apache.struts.actions.DispatchAction#dispatchMethod(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     *      java.lang.String)
     */
    @Override
    protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String methodName) throws Exception {

        ActionForward actionForward = null;
        if (!isTaskAuthorized(methodName, form, request)) {
            actionForward = processAuthorizationViolation(methodName, mapping, form, request, response);
        }
        else {
            actionForward = super.dispatchMethod(mapping, form, request, response, methodName);
        }
        return actionForward;
    }

    @Override
    /**
     * Overriding headerTab to customize how clearing tab state works on PDForm.
     */
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((KualiForm) form).setTabStates(new HashMap());
        return super.headerTab(mapping, form, request, response);
    }


    public ActionForward kraUpdateTextArea(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        // parse out the important strings from our methodToCall parameter
        String fullParameter = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);

        // parse textfieldname:htmlformaction
        String parameterFields = StringUtils.substringBetween(fullParameter, KNSConstants.METHOD_TO_CALL_PARM2_LEFT_DEL,
                KNSConstants.METHOD_TO_CALL_PARM2_RIGHT_DEL);
        if (LOG.isDebugEnabled()) {
            LOG.debug("fullParameter: " + fullParameter);
            LOG.debug("parameterFields: " + parameterFields);
        }
        String[] keyValue = null;
        if (StringUtils.isNotBlank(parameterFields)) {
            String[] textAreaParams = parameterFields.split(KNSConstants.FIELD_CONVERSIONS_SEPARATOR);
            if (LOG.isDebugEnabled()) {
                LOG.debug("lookupParams: " + textAreaParams);
            }
            for (int i = 0; i < textAreaParams.length; i++) {
                keyValue = textAreaParams[i].split(KNSConstants.FIELD_CONVERSION_PAIR_SEPARATOR);

                if (LOG.isDebugEnabled()) {
                    LOG.debug("keyValue[0]: " + keyValue[0]);
                    LOG.debug("keyValue[1]: " + keyValue[1]);
                }
            }
        }
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_NAME, keyValue[0]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.HTML_FORM_ACTION, keyValue[1]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_LABEL, keyValue[2]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.VIEW_ONLY, keyValue[3]);
        if (form instanceof KualiForm && StringUtils.isNotEmpty(((KualiForm) form).getAnchor())) {
            request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_ANCHOR, ((KualiForm) form).getAnchor());
        }

        return mapping.findForward("kraUpdateTextArea");

    }

    public ActionForward kraPostTextAreaToParent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return mapping.findForward("basic");
    }

    /**
     * Initiates a Confirmation. Part of the Question Framework for handling confirmations where a "yes" or "no" answer is required.
     * <br/> <br/> A <code>yesMethodName</code> is provided as well as a <code>noMethodName</code>. These are callback methods
     * for handling "yes" or "no" responses.
     * 
     * @param question a bean containing question information for the delegated
     *        <code>{@link #performQuestionWithoutInput(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse, String, String, String, String, String)}</code>
     *        method.
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
                return dispatchMethod(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(),
                        yesMethodName);
            }
            else if (isNotBlank(noMethodName)) {
                return dispatchMethod(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(),
                        noMethodName);
            }
        }
        else {
            return this.performQuestionWithoutInput(question, EMPTY_STRING);
        }

        return question.getMapping().findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Generically creates a <code>{@link StrutsConfirmation}</code> instance while deriving the question from a resource bundle.<br/>
     * <br/> In this case, the question in the resource bundle is expected to be parameterized. This method takes this into account,
     * and passes parameters and replaces tokens in the question with the parameters.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the confirmation question
     * @throws Exception
     */
    protected StrutsConfirmation buildParameterizedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String questionId, String configurationId, String... params)
            throws Exception {
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
     * Wrapper around
     * <code>{@link performQuestionWithoutInput(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)}</code> using
     * <code>{@link StrutsConfirmation}</code>
     * 
     * @param question StrutsConfirmation
     * @param context
     * @return ActionForward
     * @throws Exception
     */
    protected ActionForward performQuestionWithoutInput(StrutsConfirmation question, String context) throws Exception {
        return this.performQuestionWithoutInput(question.getMapping(), question.getForm(), question.getRequest(), question
                .getResponse(), question.getQuestionId(), question.getQuestionText(), question.getQuestionType(), question
                .getCaller(), context);
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
        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KEWConstants.ROUTEHEADER_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KEWConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KEWConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
    }

    /**
     * Check the authorization for executing a task. A task corresponds to a Struts action. The name of a task always corresponds to
     * the name of the Struts action method.
     * 
     * @param form the submitted form
     * @param request the HTTP request
     * @throws AuthorizationException
     */
    private boolean isTaskAuthorized(String methodName, ActionForm form, HttpServletRequest request) {
        WebAuthorizationService webAuthorizationService = KraServiceLocator.getService(WebAuthorizationService.class);
        Person person = GlobalVariables.getUserSession().getPerson();
        UniversalUser user = new UniversalUser(person);
        String username = user.getPersonUserIdentifier();
        ((KraTransactionalDocumentFormBase) form).setActionName(getClass().getSimpleName());
        boolean isAuthorized = webAuthorizationService.isAuthorized(username, this.getClass(), methodName, form, request);
        if (!isAuthorized) {
            LOG.error("User not authorized to perform " + methodName + " for document: "
                    + ((KualiDocumentFormBase) form).getDocument().getClass().getName());
        }
        return isAuthorized;
    }
    
    /**
     * Is the current user authorized to perform the given task?
     * @param task the task
     * @return true if authorized; otherwise false
     */
    protected boolean isAuthorized(Task task) {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        String username = user.getPersonUserIdentifier();
        
        TaskAuthorizationService authorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        boolean isAuthorized = authorizationService.isAuthorized(username, task);
        if (!isAuthorized) {
            LOG.error("User not authorized to perform " + task.getTaskName());
            ErrorMap errorMap = GlobalVariables.getErrorMap();
            errorMap.putErrorWithoutFullErrorPath(Constants.TASK_AUTHORIZATION, KeyConstants.AUTHORIZATION_VIOLATION);
        }
        return isAuthorized;
    }
        
    /**
     * Process an Authorization Violation.
     * 
     * @param mapping the Action Mapping
     * @param form the form
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the next action to go to
     * @throws Exception
     */
    public ActionForward processAuthorizationViolation(String taskName, ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        errorMap.putErrorWithoutFullErrorPath(Constants.TASK_AUTHORIZATION, KeyConstants.AUTHORIZATION_VIOLATION);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    @Override
    protected String generatePessimisticLockMessage(PessimisticLock lock) {
        String descriptor = (lock.getLockDescriptor() != null) ? lock.getLockDescriptor() : "";

        if (StringUtils.isNotEmpty(descriptor)) {
            descriptor = StringUtils.capitalize(descriptor.substring(descriptor.indexOf("-") + 1).toLowerCase());
        }
        return "This " + descriptor + " is locked for editing by " + lock.getOwnedByPrincipalIdentifier() + " as of "
                + org.kuali.rice.core.util.RiceConstants.getDefaultTimeFormat().format(lock.getGeneratedTimestamp()) + " on "
                + org.kuali.rice.core.util.RiceConstants.getDefaultDateFormat().format(lock.getGeneratedTimestamp());
    }

    @Override
    protected boolean exitingDocument() {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(
                KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        Boolean activeLockRegionChangedInd = (Boolean) GlobalVariables.getUserSession().retrieveObject(
                KraAuthorizationConstants.LOCK_REGION_CHANGE_IND);
        
        return super.exitingDocument() || StringUtils.isEmpty(activeLockRegion) || (activeLockRegionChangedInd != null && activeLockRegionChangedInd.booleanValue());
    }

    private List<PessimisticLock> findMatchingLocksWithGivenDescriptor(String lockDescriptor) {
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        Map fieldValues = new HashMap();
        fieldValues.put("lockDescriptor", lockDescriptor);
        List<PessimisticLock> matchingLocks = (List<PessimisticLock>) boService.findMatching(PessimisticLock.class, fieldValues);
        return matchingLocks;
    }
    
    @Override
    protected void releaseLocks(Document document, String methodToCall) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(
                KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        GlobalVariables.getUserSession().removeObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        PessimisticLockService lockService = KNSServiceLocator.getPessimisticLockService();
        UniversalUser loggedInUser = (UniversalUser) GlobalVariables.getUserSession().getPerson();
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();

        String budgetLockDescriptor = null;
        for(PessimisticLock lock: document.getPessimisticLocks()) {
            if(StringUtils.isNotEmpty(lock.getLockDescriptor()) && lock.getLockDescriptor().contains("BUDGET")) {
                budgetLockDescriptor = lock.getLockDescriptor();
                break;
            }
        }
        
        // first check if the method to call is listed as required lock clearing
        if (document.getLockClearningMethodNames().contains(methodToCall) || StringUtils.isEmpty(activeLockRegion)) {
            // find all locks for the current user and remove them
            lockService.releaseAllLocksForUser(document.getPessimisticLocks(), loggedInUser);
            
            if(StringUtils.isNotEmpty(activeLockRegion) && activeLockRegion.contains("BUDGET")) {
                //Add code here
                List<PessimisticLock> otherBudgetLocks = findMatchingLocksWithGivenDescriptor(budgetLockDescriptor); 
                lockService.releaseAllLocksForUser(otherBudgetLocks, loggedInUser, budgetLockDescriptor);
            }
        }
        
        //Check the locks held by the user - detect user's navigation away from one lock region to another
        for(PessimisticLock lock: document.getPessimisticLocks()) {
            if(StringUtils.isNotEmpty(lock.getLockDescriptor()) && StringUtils.isNotEmpty(activeLockRegion) && !lock.getLockDescriptor().contains(activeLockRegion)) {
                List<PessimisticLock> otherLocks = findMatchingLocksWithGivenDescriptor(lock.getLockDescriptor());
                lockService.releaseAllLocksForUser(otherLocks, loggedInUser, lock.getLockDescriptor());
            }
        }  
    }
    
    // TODO Temporary hack to overcome pessimistic lock bug
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase){
        if (formBase.isFormDocumentInitialized()) {
            Document document = formBase.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();
            DocumentPresentationController documentPresentationController = KNSServiceLocator.getDocumentHelperService().getDocumentPresentationController(document);
            DocumentAuthorizer documentAuthorizer = getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> documentActions =  documentPresentationController.getDocumentActions(document);
            documentActions = documentAuthorizer.getDocumentActions(document, user, documentActions);

//            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
//                documentActions = getPessimisticLockService().getDocumentActions(document, user, documentActions);
//            }

            //DocumentActionFlags flags = new DocumentActionFlags();
            KraTransactionalDocumentFormBase kraFormBase = (KraTransactionalDocumentFormBase) formBase;
            kraFormBase.setupLockRegions();
            formBase.setDocumentActions(convertSetToMap(documentActions));

        }
    }
    
    /**
     * Provide hooks for subclasses to perform additional tasks related to saving
     * the document.  The optional tasks are:
     *    1. Doing something right before the document is saved.
     *    2. Doing something after the document has been saved for the first time only.
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {    
         
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        
        preDocumentSave(docForm);
        String originalStatus = getDocumentStatus(docForm.getDocument());
        ActionForward actionForward = super.save(mapping, form, request, response);
        if (isInitialSave(originalStatus)) {
            initialDocumentSave(docForm); 
        }
        postDocumentSave(docForm);

        return actionForward;
    }
    
    /**
     * Any processing that must be performed before the save operation goes here.
     * Typically overridden by a subclass.
     * @param form the Form
     * @throws Exception
     */
    protected void preDocumentSave(KualiDocumentFormBase form) throws Exception {
        // do nothing
    }
    
    /**
     * Any processing that must be performed after the save operation goes here.
     * Typically overridden by a subclass.
     * @param form the Form
     * @throws Exception
     */
    protected void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        // do nothing
    }
    
    /**
     * Any processing that must occur only once after the document has been
     * initially saved is done here.  This method is typically overridden by
     * a subclass.
     * @param form the form
     * @throws Exception
     */
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
        // do nothing
    }

    /**
     * Get the current status of the document.  
     * @param doc the Protocol Document
     * @return the status (INITIATED, SAVED, etc.)
     */
    private String getDocumentStatus(Document doc) {
        return doc.getDocumentHeader().getWorkflowDocument().getStatusDisplayValue();
    }
    
    /**
     * Is this the initial save of the document?  If there are errors
     * in the document, it won't be saved and thus it cannot be initial
     * successful save.
     * @param status the original status before the save operation
     * @return true if the initial save; otherwise false
     */
    private boolean isInitialSave(String status) {
        return GlobalVariables.getErrorMap().isEmpty() &&
               StringUtils.equals("INITIATED", status);
    }
    
    
    /**
     * Close the document and take the user back to the index (portal page); 
     * only after asking the user if they want to save the document first.
     * Only users who have the "canSave()" permission are given this option.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;

        // only want to prompt them to save if they already can save
        if (docForm.getDocumentActions().containsKey(KNSConstants.KUALI_ACTION_CAN_SAVE)) {
            Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
            KualiConfigurationService kualiConfiguration = KNSServiceLocator.getKualiConfigurationService();

            // logic for close question
            if (question == null) {
                // ask question if not already asked
                return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, kualiConfiguration.getPropertyString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), KNSConstants.CONFIRMATION_QUESTION, KNSConstants.MAPPING_CLOSE, "");
            }
            else {
                Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
                if ((KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                    saveOnClose(docForm);
                }
            }
        }

        return super.close(mapping, form, request, response);
    }

    /**
     * Subclass can override this method in order to perform
     * any operations when the document is saved on a close action.
     * @param form
     * @throws Exception 
     */
    protected void saveOnClose(KualiDocumentFormBase form) throws Exception {
        // do nothing
    }
}
