/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.WebAuthorizationService;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.document.CommonCommitteeDocument;
import org.kuali.kra.iacuc.committee.web.struts.form.IacucCommitteeForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.coeus.common.framework.custom.CustomDataDocumentForm;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.MessageList;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.exception.UnknownDocumentIdException;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.rice.krad.util.KRADConstants.*;


public class KcTransactionalDocumentActionBase extends KualiTransactionalDocumentActionBase {

    private static final Log LOG = LogFactory.getLog(KcTransactionalDocumentActionBase.class);
    
    private static final String DEFAULT_TAB = "Versions";
    private static final String ALTERNATE_OPEN_TAB = "Parameters";

    private static final String ONE_ADHOC_REQUIRED_ERROR_KEY = "error.adhoc.oneAdHocRequired";
    private static final String DOCUMENT_RELOAD_QUESTION="DocReload";
    public static final String KRAD_PORTAL_URL = "/kc-krad/landingPage?viewId=Kc-LandingPage-RedirectView";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        /*
         * If the document is being opened in view only mode, mark the form.  We will also
         * mark the document, but it should be mentioned that a reload will cause a new 
         * document instance to be placed into the form.  When the form's setDocument() is
         * invoked, the document's view only flag is set according to the form's view only flag.
         */
        KcTransactionalDocumentFormBase kcForm = (KcTransactionalDocumentFormBase) form;
        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("displayDocSearchView") && StringUtils.isNotBlank(request.getParameter("viewDocument"))) {
            if (request.getParameter("viewDocument").equals("true")) {
                kcForm.setViewOnly(true);
                ((KcTransactionalDocumentBase)kcForm.getDocument()).setViewOnly(kcForm.isViewOnly());
            }
        }
        
        /*
         * Restore messages passed through the holding page
         */
        MessageList messageList = (MessageList) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_MESSAGES);
        if (messageList != null) {
            KNSGlobalVariables.getMessageList().addAll(messageList);
            GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_MESSAGES);
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

        if (form instanceof KcTransactionalDocumentFormBase) {
            ((KcTransactionalDocumentFormBase)form).setNavigateTo(getHeaderTabNavigateTo(request));
        }
        ((KualiForm) form).setTabStates(new HashMap());
        return super.headerTab(mapping, form, request, response);
    }  

    /**
     * Initiates a Confirmation. Part of the Question Framework for handling confirmations where a "yes" or "no" answer is required.
     * A <code>yesMethodName</code> is provided as well as a <code>noMethodName</code>. These are callback methods
     * for handling "yes" or "no" responses.
     * 
     * @param question a bean containing question information for the delegated
     *        <code>{@link #performQuestionWithoutInput(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse, String, String, String, String, String)}</code>
     *        method.
     * @param yesMethodName "yes" response callback
     * @param noMethodName "no" response callback
     * @return
     * @throws Exception can be thrown as a result of a problem during dispatching
     * @see <a href="https://test.kuali.org/confluence/x/EoFXAQ">https://test.kuali.org/confluence/x/EoFXAQ</a>
     */
    public ActionForward confirm(StrutsConfirmation question, String yesMethodName, String noMethodName) throws Exception {
        // Figure out what the caller is. We want the direct caller of confirm()
        question.setCaller(((KualiForm) question.getForm()).getMethodToCall());

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
     * Generically creates a <code>{@link StrutsConfirmation}</code> instance while deriving the question from a resource bundle.
     * In this case, the question in the resource bundle is expected to be parameterized. This method takes this into account,
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


        ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();
        String questionText = kualiConfiguration.getPropertyValueAsString(configurationId);

        for (int i = 0; i < params.length; i++) {
            questionText = replace(questionText, "{" + i + "}", params[i]);
        }
        retval.setQuestionText(questionText);

        return retval;

    }

    /**
     * Wrapper around
     * <code>{@link #performQuestionWithoutInput(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, String, String, String, String, String)}</code> using
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
    protected String buildForwardUrl(String routeHeaderId) {
        DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
    }
    
    /**
     * Builds the forward URL for the given routeHeaderId.
     * 
     * @param routeHeaderId the document id to forward to
     * @param actionTabName the tab to navigate to
     * @param documentTypeName the type name of the document
     * @return the forward URL for the given routeHeaderId
     */
    protected String buildActionUrl(String routeHeaderId, String actionTabName, String documentTypeName) {
        String returnLocation = buildForwardUrl(routeHeaderId);
        returnLocation = returnLocation.replaceFirst(NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW, actionTabName);
        returnLocation += "&" + KRADConstants.DOCUMENT_TYPE_NAME + "=" + documentTypeName;
        returnLocation += "&" + "viewDocument=false";
        return returnLocation;
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
        WebAuthorizationService webAuthorizationService = KcServiceLocator.getService(WebAuthorizationService.class);
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        ((KcTransactionalDocumentFormBase) form).setActionName(getClass().getSimpleName());
        boolean isAuthorized = webAuthorizationService.isAuthorized(userId, this.getClass(), methodName, form, request);
        if (!isAuthorized) {
            LOG.error("User not authorized to perform " + methodName + " for document: " +
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
        
        TaskAuthorizationService authorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        boolean isAuthorized = authorizationService.isAuthorized(currentUser, task);
        if (!isAuthorized) {
            LOG.error("User not authorized to perform " + task.getTaskName());
            MessageMap errorMap = GlobalVariables.getMessageMap();
            errorMap.putErrorWithoutFullErrorPath(Constants.TASK_AUTHORIZATION, KeyConstants.AUTHORIZATION_VIOLATION);
        }
        return isAuthorized;
    }
        
    /**
     * ProcessDefinitionDefinitionDefinition an Authorization Violation.
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

    @Override
    protected String generatePessimisticLockMessage(PessimisticLock lock) {
        String descriptor = (lock.getLockDescriptor() != null) ? lock.getLockDescriptor() : "";
        String message = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(KeyConstants.LOCKED_DOCUMENT_MESSAGE);

        descriptor = getDocumentType(descriptor);
        message = message.replace("{DOCUMENT_TYPE}", descriptor);
        message = message.replace("{LOCKED_BY}", lock.getOwnedByUser().getPrincipalName());
        message = message.replace("{TIMESTAMP}", org.kuali.rice.core.api.util.RiceConstants.getDefaultTimeFormat().format(lock.getGeneratedTimestamp()));
        message = message.replace("{DATESTAMP}", org.kuali.rice.core.api.util.RiceConstants.getDefaultDateFormat().format(lock.getGeneratedTimestamp()));
        
        return message;
    }

    private String getDocumentType(String descriptor) {
        String result="document";
        if (StringUtils.isNotEmpty(descriptor)) {
            String[] resultArray = descriptor.split("-");
            if (resultArray.length > 2) {
                if (resultArray.length > 3 && StringUtils.equalsIgnoreCase(resultArray[2], "award")) {
                    result = "Award";
                } else {
                    if (StringUtils.equalsIgnoreCase(result, "coidisclosure")) {
                        result = "Disclosure";
                    } else if (StringUtils.equalsIgnoreCase(result, "subaward")) {
                        result = "Subaward";
                    } else if (StringUtils.equalsIgnoreCase(result, "protocol")) {
                        result = "Protocol";
                    } else if (StringUtils.equalsIgnoreCase(result, "iacuc_protocol")) {
                        result = "IACUC Protocol";
                    } else if (StringUtils.equalsIgnoreCase(result, "negotiation")) {
                        result = "Negotiation";
                    } else if (StringUtils.equalsIgnoreCase(result, "proposal development")) {
                        result = "Proposal";
                    }
                }
            }
        }
        return result;
    }
    
    private List<PessimisticLock> findMatchingLocksWithGivenDescriptor(String lockDescriptor) {
        DataObjectService dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        Map fieldValues = new HashMap();
        fieldValues.put("lockDescriptor", lockDescriptor);
        List<PessimisticLock> matchingLocks = dataObjectService.findMatching(PessimisticLock.class, QueryByCriteria.Builder.andAttributes(fieldValues).build()).getResults();
        return matchingLocks;
    }
    
    @Override
    protected void releaseLocks(Document document, String methodToCall) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(
                KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        GlobalVariables.getUserSession().removeObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        PessimisticLockService lockService = KRADServiceLocatorWeb.getPessimisticLockService();
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
    
    @SuppressWarnings("unchecked")
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        
        if (formBase.isFormDocumentInitialized()) {
            KcTransactionalDocumentFormBase kcFormBase = (KcTransactionalDocumentFormBase) formBase;
            KcTransactionalDocumentBase document = (KcTransactionalDocumentBase) formBase.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();
            KcTransactionalDocumentAuthorizerBase documentAuthorizer = (KcTransactionalDocumentAuthorizerBase) getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> editModes = new HashSet<String>();
            
            KcTransactionalDocumentFormBase kraFormBase = (KcTransactionalDocumentFormBase) formBase;
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
                    Set<String> handledLockDescriptors = new HashSet<String>();
                    for(PessimisticLock lock: document.getPessimisticLocks()) {
                        if(StringUtils.isNotEmpty(lock.getLockDescriptor()) && StringUtils.isNotEmpty(activeLockRegion) && !lock.getLockDescriptor().contains(activeLockRegion)
                                && !handledLockDescriptors.contains(lock.getLockDescriptor())) {
                            getPessimisticLockService().releaseAllLocksForUser(document.getPessimisticLocks(), user, lock.getLockDescriptor());
                            handledLockDescriptors.add(lock.getLockDescriptor());
                        }
                    }
                    // do not generate locks if doc is in initiated state
                    if (!document.getDocumentHeader().getWorkflowDocument().isInitiated()) {
                        editMode = getPessimisticLockService().establishLocks(document, editMode, user);
                    }
                    //ensure locks are current
                    document.refreshPessimisticLocks();
                     
                    //Task Authorizers should key off the document viewonly flag to determine
                    //if the document is available for writing or if its locked.
                    if (editMode.containsKey(AuthorizationConstants.EditMode.VIEW_ONLY)) {
                        document.setViewOnly(true);
                        //if budget document we need to set the parent document view only as well for authorization consistency.
                        if (document instanceof AwardBudgetDocument) {
                            AwardBudgetDocument budgetDoc = (AwardBudgetDocument)document;
                            budgetDoc.getBudget().getBudgetParent().getDocument().setViewOnly(true);
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
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {    
         
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        
        preDocumentSave(docForm);
        String originalStatus = getDocumentStatus(docForm.getDocument());
        ActionForward actionForward;
        if ((form instanceof CommitteeForm) || (form instanceof IacucCommitteeForm)) {
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

        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);
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
    	try {
    		return doc.getDocumentHeader().getWorkflowDocument().getStatus().getLabel();
    	} catch (RiceRuntimeException e) {
    		LOG.error("Could not find doc.getDocumentNumber(): " + doc.getDocumentNumber() + ":" + 
    				doc.getDocumentHeader().getDocumentNumber() + ":" + doc.getDocumentHeader().getOrganizationDocumentNumber(), e);
    		return "NOT FOUND";
    	}
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
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        
        // only want to prompt them to save if they already can save
        if (canSave(docForm)) {
            
            Object question = getQuestion(request);
            // logic for close question
            if (question == null) {
                // KULRICE-7306: Unconverted Values not carried through during a saveOnClose action.
                // Stash the unconverted values to populate errors if the user elects to save
                saveUnconvertedValuesToSession(request, docForm);
                
                // ask question if not already asked
                forward = performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, 
                        getKualiConfigurationService().getPropertyValueAsString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), 
                        Constants.KC_CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            } else {
                // otherwise attempt to save and close
                Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                
                // KULRICE-7306: Unconverted Values not carried through during a saveOnClose action.
                // Side effecting in that it clears the session attribute that holds the unconverted values.
                Map<String, Object> unconvertedValues = restoreUnconvertedValuesFromSession(request, docForm);
                
                if ((KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && KcConfirmationQuestion.YES.equals(buttonClicked)) {
                    // if yes button clicked - save the doc

                    // KULRICE-7306: Unconverted Values not carried through during a saveOnClose action.
                    // If there were values that couldn't be converted, we attempt to populate them so that the
                    // the appropriate errors get set on those fields
                    if (MapUtils.isNotEmpty(unconvertedValues)) for (Map.Entry<String, Object> entry : unconvertedValues.entrySet()) {
                        docForm.populateForProperty(entry.getKey(), entry.getValue(), unconvertedValues);
                    }
                    
                    forward = saveOnClose(mapping, form, request, response);
                } else if ((KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && KcConfirmationQuestion.CANCEL.equals(buttonClicked)) {
                    forward = mapping.findForward(Constants.MAPPING_BASIC);
                } else {
                    forward = super.close(mapping, docForm, request, response);
                }
            }
        } else {
            forward = returnToSender(request, mapping, docForm);
        }

        return forward;
    }
    
    // stash unconvertedValues in the session
    private void saveUnconvertedValuesToSession(HttpServletRequest request, KualiDocumentFormBase docForm) {
        if (MapUtils.isNotEmpty(docForm.getUnconvertedValues())) {
            request.getSession().setAttribute(getUnconvertedValuesSessionAttributeKey(docForm), new HashMap(docForm.getUnconvertedValues()));
        }
    }

    // SIDE EFFECTING: clears out unconverted values from the Session and restores them to the form
    private Map<String, Object> restoreUnconvertedValuesFromSession(HttpServletRequest request,
            KualiDocumentFormBase docForm) {// first restore unconvertedValues and clear out of session
        Map<String, Object> unconvertedValues =
            (Map<String, Object>)request.getSession().getAttribute(getUnconvertedValuesSessionAttributeKey(docForm));
        if (MapUtils.isNotEmpty(unconvertedValues)) {
            request.getSession().removeAttribute(getUnconvertedValuesSessionAttributeKey(docForm));
            docForm.setUnconvertedValues(unconvertedValues); // setting them here just for good measure
        }
        return unconvertedValues;
    }

    // create the key based on docId for stashing/retrieving unconvertedValues in the session
    private String getUnconvertedValuesSessionAttributeKey(KualiDocumentFormBase docForm) {
        return "preCloseUnconvertedValues." + docForm.getDocId();
    }

    /**
     * Subclass can override this method in order to perform
     * any operations when the document is saved on a close action.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase documentForm = (KualiDocumentFormBase) form;
        
        if (isInitialSave(getDocumentStatus(documentForm.getDocument()))) {
            initialDocumentSave(documentForm); 
        }
        
        return super.close(mapping, form, request, response);
    }

    /**
     * For committee document, Bos will be populated from xml content
     */
    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        if (kualiDocumentFormBase instanceof CommitteeForm) {
            loadCommitteeDocument(kualiDocumentFormBase);
        }
        else if(kualiDocumentFormBase instanceof IacucCommitteeForm) {
            loadIacucCommitteeDocument(kualiDocumentFormBase);
        }
        else {
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
        WorkflowDocument workflowDocument = doc.getDocumentHeader().getWorkflowDocument();
        
        if ( workflowDocument != doc.getDocumentHeader().getWorkflowDocument() ) {
            LOG.warn( "Workflow document changed via canOpen check" );
            doc.getDocumentHeader().setWorkflowDocument(workflowDocument);
        }
        kualiDocumentFormBase.setDocument(doc);
        WorkflowDocument workflowDoc = doc.getDocumentHeader().getWorkflowDocument();
        kualiDocumentFormBase.setDocTypeName(workflowDoc.getDocumentTypeName());
        String content = KcServiceLocator.getService(RouteHeaderService.class).getContent(workflowDoc.getDocumentId()).getDocumentContent();
        if (doc instanceof CommitteeDocument && !workflowDoc.getStatus().getCode().equals(KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
            Committee committee = (Committee)populateCommitteeFromXmlDocumentContents(content);
            ((CommitteeDocument)doc).getCommitteeList().add(committee);
            committee.setCommitteeDocument((CommitteeDocument) doc);
        }
        if (!getDocumentHelperService().getDocumentAuthorizer(doc).canOpen(doc, GlobalVariables.getUserSession().getPerson())) {
            throw buildAuthorizationException("open", doc);
        }
        KNSServiceLocator.getSessionDocumentService().addDocumentToUserSession(GlobalVariables.getUserSession(), workflowDoc);
    }
    
    
    /*
     * This method is specifically to load committee BOs from wkflw doc content.
     */
    // TODO delete this method after committee backfitting 
    private void loadIacucCommitteeDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        String docId = kualiDocumentFormBase.getDocId();
        Document doc = null;
        doc = getDocumentService().getByDocumentHeaderId(docId);
        if (doc == null) {
            throw new UnknownDocumentIdException("Document no longer exists.  It may have been cancelled before being saved.");
        }
        WorkflowDocument workflowDocument = doc.getDocumentHeader().getWorkflowDocument();
        
        if ( workflowDocument != doc.getDocumentHeader().getWorkflowDocument() ) {
            LOG.warn( "Workflow document changed via canOpen check" );
            doc.getDocumentHeader().setWorkflowDocument(workflowDocument);
        }
        kualiDocumentFormBase.setDocument(doc);
        WorkflowDocument workflowDoc = doc.getDocumentHeader().getWorkflowDocument();
        kualiDocumentFormBase.setDocTypeName(workflowDoc.getDocumentTypeName());
        String content = KcServiceLocator.getService(RouteHeaderService.class).getContent(workflowDoc.getDocumentId()).getDocumentContent();
        if (doc instanceof CommonCommitteeDocument && !workflowDoc.getStatus().getCode().equals(KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
            IacucCommittee committee = (IacucCommittee)populateIacucCommitteeFromXmlDocumentContents(content);
            ((CommonCommitteeDocument)doc).getCommitteeList().add(committee);
            committee.setCommitteeDocument((CommonCommitteeDocument) doc);
        }
        if (!getDocumentHelperService().getDocumentAuthorizer(doc).canOpen(doc, GlobalVariables.getUserSession().getPerson())) {
            throw buildAuthorizationException("open", doc);
        }
        KNSServiceLocator.getSessionDocumentService().addDocumentToUserSession(GlobalVariables.getUserSession(), workflowDoc);
    }

    /*
     * Add a hook to route committee
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        if (form instanceof CommitteeForm) {
            forward = routeCommittee(mapping, form, request, response);
        }
        else if(form instanceof IacucCommitteeForm) {
            forward = routeIacucCommittee(mapping, form, request, response);
        }
        else {
            forward = super.route(mapping, form, request, response);            
        }
        
        // Only forward to Portal if it will eventually go to the holding page
        if (form instanceof InstitutionalProposalForm || form instanceof AwardForm || form instanceof IacucProtocolForm
            || form instanceof ProtocolForm || form instanceof CommitteeForm || form instanceof IacucCommitteeForm || form instanceof TimeAndMoneyForm || form instanceof SubAwardForm) {
            ActionForward basicForward = mapping.findForward(Constants.MAPPING_BASIC);
            if (StringUtils.equals(forward.getPath(), basicForward.getPath())) {
                setupDocumentExit();
                forward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
            }
        }
        
        return forward;
    }

    /*
     * This method is specifically to route committee because committee's BOs will be persisted at route.
     */
    private ActionForward routeCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;

        committeeForm.setDerivedValuesOnForm(request);
        ActionForward preRulesForward = promptBeforeValidation(mapping, form, request, response);
        if (preRulesForward != null) {
            return preRulesForward;
        }

        CommitteeDocument committeeDocument = (CommitteeDocument) committeeForm.getCommitteeDocument();

        getKraDocumentService().routeDocument(committeeDocument, committeeForm.getAnnotation(), combineAdHocRecipients(committeeForm));
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_SUCCESSFUL);
        committeeForm.setAnnotation("");

        return createSuccessfulSubmitRedirect("Committee", committeeDocument.getCommittee().getCommitteeId(), request, mapping, committeeForm);
    }
    
    
    
    /*
     * This method is specifically to route committee because committee's BOs will be persisted at route.
     */
    // TODO delete this method after committee backfitting 
    private ActionForward routeIacucCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucCommitteeForm committeeForm = (IacucCommitteeForm) form;

        committeeForm.setDerivedValuesOnForm(request);
        ActionForward preRulesForward = promptBeforeValidation(mapping, form, request, response);
        if (preRulesForward != null) {
            return preRulesForward;
        }

        CommonCommitteeDocument committeeDocument = (CommonCommitteeDocument) committeeForm.getCommitteeDocument();

        getKraDocumentService().routeDocument(committeeDocument, committeeForm.getAnnotation(), combineAdHocRecipients(committeeForm));
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_SUCCESSFUL);
        committeeForm.setAnnotation("");

        return createSuccessfulSubmitRedirect("IacucCommittee", committeeDocument.getCommittee().getCommitteeId(), request, mapping, committeeForm);
    }
    
    
    /**
     * Creates a redirect to the sender after a successful route (submit).
     * 
     * @param submissionType The name of the type of document routed (i.e. Protocol, Committee)
     * @param refId The user-readable number created for the document
     * @param request
     * @param mapping
     * @param form
     * @return the redirect back to the sender (most likely the portal page)
     */
    protected ActionForward createSuccessfulSubmitRedirect(String submissionType, String refId, HttpServletRequest request, ActionMapping mapping, 
            KualiDocumentFormBase form) {
        
        ActionForward forward = returnToSender(request, mapping, form);
        
        Properties parameters = new Properties();
        parameters.put("successfulSubmission", Boolean.TRUE.toString());
        parameters.put("submissionType", submissionType);
        parameters.put("refId", refId);
        
        ActionRedirect redirect = new ActionRedirect(forward);
        for (Map.Entry<Object, Object> parameter : parameters.entrySet()) {
            redirect.addParameter(parameter.getKey().toString(), parameter.getValue());
        }
        
        return redirect;
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
    
    
    /*
     * This is pretty much a copy from MaintenanceDocumentBase's populateMaintainablesFromXmlDocumentContents.
     * Since committee is not persisted in DB util it is approved, so we need this to populate
     * Committee and its collection from xmldoccontent
     */
    // TODO delete this method after committee backfitting 
    private PersistableBusinessObject populateIacucCommitteeFromXmlDocumentContents(String xmlDocumentContents) {
        PersistableBusinessObject bo = null;
        if (!StringUtils.isEmpty(xmlDocumentContents)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                org.w3c.dom.Document xmlDocument = builder.parse(new InputSource(new StringReader(xmlDocumentContents)));
                bo = getBusinessObjectFromXML(xmlDocumentContents, IacucCommittee.class.getName());

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
    
    protected void streamToResponse(KcFile attachmentDataSource,
            HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getData();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);

            WebUtils
                    .saveMimeOutputStreamAsFile(response, attachmentDataSource
                            .getType(), baos, attachmentDataSource
                            .getName());

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
        PersistableBusinessObject businessObject = (PersistableBusinessObject) KRADServiceLocator.getXmlObjectSerializerService().fromXml(objXml);
        return businessObject;
    }

    private DocumentService getKraDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }

    @Override
    protected ActionForward returnToSender(HttpServletRequest request, ActionMapping mapping, KualiDocumentFormBase form) {
        if (StringUtils.isEmpty(form.getBackLocation())) {
            form.setBackLocation(KRAD_PORTAL_URL);
        }
        //call this first so it will call setupDocumentExit before we try to return
        ActionForward superForward = super.returnToSender(request, mapping, form);
        if (form instanceof KcTransactionalDocumentFormBase) {
            KcTransactionalDocumentFormBase kraForm = (KcTransactionalDocumentFormBase)form;
            if (kraForm.isMedusaOpenedDoc()) {
                return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
            }
        }
        
        if(form.isReturnToActionList()) {
            // temporary fix to unload block ui in embedded mode
            // here we are forcing to route through holding page when an action is performed through action list
            // and we need to send the user back to action list.
            GlobalVariables.getUserSession().addObject(Constants.FORCE_HOLDING_PAGE_FOR_ACTION_LIST, true);
            return routeActionListToHoldingPage(mapping, superForward);
        }else {
            
            return superForward;
        }
        
    }
    
    private ActionForward routeActionListToHoldingPage(ActionMapping mapping, ActionForward actionForward) {
        String returnLocation = actionForward.getPath();
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
    }
    
    /**
     * Optional path to send certain documents to the holding page.
     * @param forward Forward following the basic or portal mapping
     * @param returnForward Forward calculated by returnToSender
     * @param holdingPageForward Forward going to the holding page
     * @return
     */
    protected ActionForward routeToHoldingPage(ActionForward forward, ActionForward returnForward, ActionForward holdingPageForward, String returnLocation) {
        return routeToHoldingPage(Collections.singletonList(forward), returnForward, holdingPageForward, returnLocation);
    }
    
    /**
     * Optional path to send certain documents to the holding page.
     * @param forwards Forward following the basic or portal mapping
     * @param returnForward Forward calculated by returnToSender
     * @param holdingPageForward Forward going to the holding page
     * @return
     */
    protected ActionForward routeToHoldingPage(List<ActionForward> forwards, ActionForward returnForward, ActionForward holdingPageForward, String returnLocation) {
        boolean knownForward = false;
        for (ActionForward forward : forwards) {
            if (StringUtils.equals(forward.getPath(), returnForward.getPath())) {
                knownForward = true;
            }
        }
        if (!knownForward) {
            return returnForward;
        } else {
            GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_MESSAGES, KNSGlobalVariables.getMessageList());
            GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_RETURN_LOCATION, (Object) returnLocation);
            return holdingPageForward;
        }
    }    

    public ActionForward sendAdHocRequests(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KcTransactionalDocumentFormBase dform = (KcTransactionalDocumentFormBase) form;
        Document document = dform.getDocument();
        if( dform.getAdHocRoutePersons().size() > 0 || dform.getAdHocRouteWorkgroups().size() > 0) {
            document.prepareForSave();
            return super.sendAdHocRequests(mapping, dform, request, response);
        } else {
            GlobalVariables.getMessageMap().putError("newAdHocRoutePerson.id", ONE_ADHOC_REQUIRED_ERROR_KEY);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    /**
     * Quotes a string that follows RFC 822 and is valid to include in an http header.
     * 
     * <p>
     * This really should be a part of {@link org.kuali.rice.kns.util.WebUtils WebUtils}.
     * <p>
     * 
     * For example: without this method, file names with spaces will not show up to the client correctly.
     * 
     * <p>
     * This method is not doing a Base64 encode just a quoted printable character otherwise we would have
     * to set the encoding type on the header.
     * <p>
     * 
     * @param s the original string
     * @return the modified header string
     */
    protected static String getValidHeaderString(String s) {
        return MimeUtility.quote(s, HeaderTokenizer.MIME);
    }    

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        if (canSave(docForm)) {
            Object question = getQuestion(request);
            if (question == null) {
                return this.performQuestionWithoutInput(mapping, form, request, response, 
                        DOCUMENT_RELOAD_QUESTION, 
                        getKualiConfigurationService().getPropertyValueAsString(KeyConstants.WARNING_DOCUMENT_RELOAD_CONFIRMATION),
                        KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
            } else {
                Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                if(DOCUMENT_RELOAD_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                    forward = super.reload(mapping, docForm, request, response);
                }
            }
        } else {
            forward = super.reload(mapping, docForm, request, response);
        }
        return forward;
    }

    public ActionForward reloadWithoutWarning(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.reload(mapping, form, request, response);
    }
    
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.docHandler(mapping, form, request, response);
        if (form instanceof CustomDataDocumentForm) {
            ((CustomDataDocumentForm) form).getCustomDataHelper().prepareCustomData();
        }
        return forward;
    }

    @Override
    public ActionForward recall(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward forward = super.recall(mapping, form, request, response);       
        ActionForward basicForward = mapping.findForward(Constants.MAPPING_BASIC);
        //if recall is returning back to basic path then we should return to the portal to avoid
        //problems with workflow routing changes to the document. This should eventually return to the holding page,
        //but currently waiting on KCINFR-760.
        if (StringUtils.equals(basicForward.getPath(), forward.getPath())) {
            return mapping.findForward(KRADConstants.MAPPING_PORTAL);
        } else {
            return forward;
        }
    }
    

}
