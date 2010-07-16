/*
 * Copyright 2005-2010 The Kuali Foundation
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
import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.kra.logging.BufferedLogger.error;
import static org.kuali.rice.kns.util.KNSConstants.CONFIRMATION_QUESTION;
import static org.kuali.rice.kns.util.KNSConstants.EMPTY_STRING;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.service.impl.KraDocumentServiceImpl;
import org.kuali.kra.web.struts.authorization.WebAuthorizationService;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.exception.AuthorizationException;
import org.kuali.rice.kns.exception.UnknownDocumentIdException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.MessageMap;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// TODO : should move this class to org.kuali.kra.web.struts.action
public class KraTransactionalDocumentActionBase extends KualiTransactionalDocumentActionBase {
    
    private static final Log LOG = LogFactory.getLog(KraTransactionalDocumentActionBase.class);
    
    private static final String DEFAULT_TAB = "Versions";
    private static final String ALTERNATE_OPEN_TAB = "Parameters";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        /*
         * If the document is being opened in view only mode, mark the form.  We will also
         * mark the document, but it should be mentioned that a reload will cause a new 
         * document instance to be placed into the form.  When the form's setDocument() is
         * invoked, the document's view only flag is set according to the form's view only flag.
         */
        KraTransactionalDocumentFormBase kcForm = (KraTransactionalDocumentFormBase) form;
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("displayDocSearchView") && StringUtils.isNotBlank(request.getParameter("viewDocument"))) {
            if (request.getParameter("viewDocument").equals("true")) {
                kcForm.setViewOnly(true);
                ((ResearchDocumentBase)kcForm.getDocument()).setViewOnly(kcForm.isViewOnly());
            }
        }
        
        ActionForward returnForward = mapping.findForward(Constants.MAPPING_BASIC);
        returnForward = super.execute(mapping, form, request, response);
        
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

        if (form instanceof KraTransactionalDocumentFormBase) {
            ((KraTransactionalDocumentFormBase)form).setNavigateTo(getHeaderTabNavigateTo(request));
        }
        ((KualiForm) form).setTabStates(new HashMap());
        return super.headerTab(mapping, form, request, response);
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
        debug("Caller is ", question.getCaller());
        debug("Setting caller from stacktrace ", Arrays.asList(new Throwable().getStackTrace()));
        debug("Current action is ", getClass());

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
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        ((KraTransactionalDocumentFormBase) form).setActionName(getClass().getSimpleName());
        boolean isAuthorized = webAuthorizationService.isAuthorized(userId, this.getClass(), methodName, form, request);
        if (!isAuthorized) {
            error("User not authorized to perform ", methodName, " for document: ",
                     ((KualiDocumentFormBase) form).getDocument().getClass().getName());
        }
        return isAuthorized;
    }
    
    /**
     * Is the current user authorized to perform the given task?
     * @param task the task
     * @return true if authorized; otherwise false
     */
    protected boolean isAuthorized(Task task) {
        String currentUser = GlobalVariables.getUserSession().getPrincipalId();
        
        TaskAuthorizationService authorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        boolean isAuthorized = authorizationService.isAuthorized(currentUser, task);
        if (!isAuthorized) {
            LOG.error("User not authorized to perform " + task.getTaskName());
            MessageMap errorMap = GlobalVariables.getMessageMap();
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
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.putErrorWithoutFullErrorPath(Constants.TASK_AUTHORIZATION, KeyConstants.AUTHORIZATION_VIOLATION);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /** 
     * {@inheritDoc}
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#generatePessimisticLockMessage(org.kuali.rice.kns.document.authorization.PessimisticLock)
     */
    @Override
    protected String generatePessimisticLockMessage(PessimisticLock lock) {
        String descriptor = (lock.getLockDescriptor() != null) ? lock.getLockDescriptor() : "";

        if (StringUtils.isNotEmpty(descriptor)) {
            descriptor = StringUtils.capitalize(descriptor.substring(descriptor.indexOf("-") + 1).toLowerCase());
        }
        return new StringBuilder().append("This ").append(descriptor).append(" is locked for editing by ").append(lock.getOwnedByUser().getPrincipalName()).append(" as of ")
                .append(org.kuali.rice.core.util.RiceConstants.getDefaultTimeFormat().format(lock.getGeneratedTimestamp())).append(" on ")
                .append(org.kuali.rice.core.util.RiceConstants.getDefaultDateFormat().format(lock.getGeneratedTimestamp())).toString();
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
        Person loggedInUser = GlobalVariables.getUserSession().getPerson();

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
        
        //Code still here, but probably can be deleted. Copied to populateAuthorizationFields
        //as it looks like this code is only called when the document is being closed, not every request
        //Check the locks held by the user - detect user's navigation away from one lock region to another
        for(PessimisticLock lock: document.getPessimisticLocks()) {
            if(StringUtils.isNotEmpty(lock.getLockDescriptor()) && StringUtils.isNotEmpty(activeLockRegion) && !lock.getLockDescriptor().contains(activeLockRegion)) {
                List<PessimisticLock> otherLocks = findMatchingLocksWithGivenDescriptor(lock.getLockDescriptor());
                lockService.releaseAllLocksForUser(otherLocks, loggedInUser, lock.getLockDescriptor());
            }
        }  
    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase#populateAuthorizationFields(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        
        if (formBase.isFormDocumentInitialized()) {
            KraTransactionalDocumentFormBase kcFormBase = (KraTransactionalDocumentFormBase) formBase;
            ResearchDocumentBase document = (ResearchDocumentBase) formBase.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();
            KcTransactionalDocumentAuthorizerBase documentAuthorizer = (KcTransactionalDocumentAuthorizerBase) getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> editModes = new HashSet<String>();
            
            KraTransactionalDocumentFormBase kraFormBase = (KraTransactionalDocumentFormBase) formBase;
            kraFormBase.setupLockRegions();     
            String activeLockRegion = (String)GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
            
            if (!documentAuthorizer.canOpen(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            else {
                document.setViewOnly(kcFormBase.isViewOnly());
                
                /*
                 * Documents that require a pessimistic lock need to be treated differently.  If a user
                 * can edit the document, they need to obtain the lock, but it is possible that another
                 * user already has the lock.  So, we try to get the lock using FULL_ENTRY.  If the
                 * edit mode is downgraded to VIEW_ONLY, we flag the document as such.
                 */
                if (requiresLock(document) && documentAuthorizer.canEdit(document, user)) {
                    editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                            
                    Map<String, String> editMode = convertSetToMap(editModes);
                    
                    //Check the locks held by the user - detect user's navigation away from one lock region to another
                    //refresh locks as stale ones can exist in the document due to it being in the form
                    document.refreshPessimisticLocks();
                    for(PessimisticLock lock: document.getPessimisticLocks()) {
                        if(StringUtils.isNotEmpty(lock.getLockDescriptor()) && StringUtils.isNotEmpty(activeLockRegion) && !lock.getLockDescriptor().contains(activeLockRegion)) {
                            getPessimisticLockService().releaseAllLocksForUser(document.getPessimisticLocks(), user, lock.getLockDescriptor());
                        }
                    }
                    editMode = getPessimisticLockService().establishLocks(document, editMode, user);
                    //ensure locks are current
                    document.refreshPessimisticLocks();
                     
                    //Task Authorizers should key off the document viewonly flag to determine
                    //if the document is available for writing or if its locked.
                    if (editMode.containsKey(AuthorizationConstants.EditMode.VIEW_ONLY)) {
                        document.setViewOnly(true);
                        //if budget document we need to set the parent document view only as well for authorization consistency.
                        if (document instanceof BudgetDocument) {
                            BudgetDocument budgetDoc = (BudgetDocument)document;
                            budgetDoc.getParentDocument().setViewOnly(true);
                        }
                    }
                }
                editModes = documentAuthorizer.getEditModes(document, user, null);
                Set<String> documentActions = documentAuthorizer.getDocumentActions(document, user, null);
                
                formBase.setDocumentActions(convertSetToMap(documentActions));
            }
            formBase.setEditingMode(convertSetToMap(editModes));
        }
    }
    
    private boolean requiresLock(Document document) {
        return getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking();
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
        ActionForward actionForward;
        if (form instanceof CommitteeForm) {
            actionForward = saveCommitteeDocument(mapping, form, request, response);
        } else {
            actionForward = super.save(mapping, form, request, response);            
        }
        if (isInitialSave(originalStatus)) {
            initialDocumentSave(docForm); 
        }
        postDocumentSave(docForm);

        return actionForward;
    }
        
    /*
     * This is copied from KualiDocumentactionbase.save.  Use KraDocumentService instead of DocumentService
     * This is for CommitteeDocument handling; to save it in workflow not persisted BO until it is approved.
     */
    private ActionForward saveCommitteeDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {    
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        //get any possible changes to adHocWorkgroups
        refreshAdHocRoutingWorkgroupLookups(request, kualiDocumentFormBase);
        Document document = kualiDocumentFormBase.getDocument();

        // save in workflow
        getKraDocumentService().saveDocument(document);

        GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);
        kualiDocumentFormBase.setAnnotation("");

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
     
    
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
        return GlobalVariables.getMessageMap().hasNoErrors() &&
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
        if (isInitialSave(getDocumentStatus(form.getDocument()))) {
            initialDocumentSave(form); 
        } 
    }

    /**
     * For committee document, Bos will be populated from xml content
     */
    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        if (kualiDocumentFormBase instanceof CommitteeForm) {
            loadCommitteeDocument(kualiDocumentFormBase);
        } else {
            super.loadDocument(kualiDocumentFormBase);
        }
    }
    
    /*
     * This method is specifically to load committee BOs from wkflw doc content.
     */
    private void loadCommitteeDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        String docId = kualiDocumentFormBase.getDocId();
        Document doc = null;
        doc = getDocumentService().getByDocumentHeaderId(docId);
        if (doc == null) {
            throw new UnknownDocumentIdException("Document no longer exists.  It may have been cancelled before being saved.");
        }
        KualiWorkflowDocument workflowDocument = doc.getDocumentHeader().getWorkflowDocument();
        
        if ( workflowDocument != doc.getDocumentHeader().getWorkflowDocument() ) {
            LOG.warn( "Workflow document changed via canOpen check" );
            doc.getDocumentHeader().setWorkflowDocument(workflowDocument);
        }
        kualiDocumentFormBase.setDocument(doc);
        KualiWorkflowDocument workflowDoc = doc.getDocumentHeader().getWorkflowDocument();
        kualiDocumentFormBase.setDocTypeName(workflowDoc.getDocumentType());
        String content = KraServiceLocator.getService(RouteHeaderService.class).getContent(workflowDoc.getRouteHeaderId()).getDocumentContent();
        if (doc instanceof CommitteeDocument && !workflowDoc.getRouteHeader().getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_FINAL_CD)) {
            Committee committee = (Committee)populateCommitteeFromXmlDocumentContents(content);
            ((CommitteeDocument)doc).getCommitteeList().add(committee);
            committee.setCommitteeDocument((CommitteeDocument) doc);
        }
        if (!getDocumentHelperService().getDocumentAuthorizer(doc).canOpen(doc, GlobalVariables.getUserSession().getPerson())) {
            throw buildAuthorizationException("open", doc);
        }
        GlobalVariables.getUserSession().setWorkflowDocument(workflowDoc);
    }

    /*
     * Add a hook to route committee
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;
        if (form instanceof CommitteeForm) {
            actionForward = routeCommittee(mapping, form, request, response);
        } else {
            actionForward = super.route(mapping, form, request, response);            
        }
        return actionForward;
    }

    /*
     * This method is specifically to route committee because committee's BOs will be persisted at route.
     */
    private ActionForward routeCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;

        kualiDocumentFormBase.setDerivedValuesOnForm(request);
        ActionForward preRulesForward = promptBeforeValidation(mapping, form, request, response);
        if (preRulesForward != null) {
            return preRulesForward;
        }

        Document document = kualiDocumentFormBase.getDocument();

        getKraDocumentService().routeDocument(document, kualiDocumentFormBase.getAnnotation(), combineAdHocRecipients(kualiDocumentFormBase));
        GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_SUCCESSFUL);
        kualiDocumentFormBase.setAnnotation("");

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    /*
     * This is pretty much a copy from MaintenanceDocumentBase's populateMaintainablesFromXmlDocumentContents.
     * Since committee is not persisted in DB util it is approved, so we need this to populate
     * Committee and its collection from xmldoccontent
     */
    private PersistableBusinessObject populateCommitteeFromXmlDocumentContents(String xmlDocumentContents) {
        PersistableBusinessObject bo = null;
        if (!StringUtils.isEmpty(xmlDocumentContents)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                org.w3c.dom.Document xmlDocument = builder.parse(new InputSource(new StringReader(xmlDocumentContents)));
                bo = getBusinessObjectFromXML(xmlDocumentContents, Committee.class.getName());

            }
            catch (ParserConfigurationException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (SAXException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (IOException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }

        }
        return bo;
    }
    
    protected void streamToResponse(AttachmentDataSource attachmentDataSource,
            HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);

            WebUtils
                    .saveMimeOutputStreamAsFile(response, attachmentDataSource
                            .getContentType(), baos, attachmentDataSource
                            .getFileName());

        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                // LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }


    /**
     * Retrieves substring of document contents from maintainable tag name. Then use xml service to translate xml into a business
     * object.
     */
    private PersistableBusinessObject getBusinessObjectFromXML(String xmlDocumentContents, String objectTagName) {
        String objXml = StringUtils.substringBetween(xmlDocumentContents, "<" + objectTagName + ">", "</" + objectTagName + ">");
        objXml = "<" + objectTagName + ">" + objXml + "</" + objectTagName + ">";
        if (objXml.contains("itemDesctiption")) {
            objXml = objXml.replaceAll("itemDesctiption", "itemDescription");
        }
        PersistableBusinessObject businessObject = (PersistableBusinessObject) KNSServiceLocator.getXmlObjectSerializerService().fromXml(objXml);
        return businessObject;
    }

    private KraDocumentServiceImpl getKraDocumentService() {
        return ((KraDocumentServiceImpl)KraServiceLocator.getService("kraDocumentService"));
               
    }
    
    @Override
    protected ActionForward returnToSender(HttpServletRequest request, ActionMapping mapping, KualiDocumentFormBase form) {
        //call this first so it will call setupDocumentExit before we try to return
        ActionForward superForward = super.returnToSender(request, mapping, form);
        if (form instanceof KraTransactionalDocumentFormBase) {
            KraTransactionalDocumentFormBase kraForm = (KraTransactionalDocumentFormBase)form;
            if (kraForm.isMedusaOpenedDoc()) {
                return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
            }
        }
        return superForward;
    }
}
