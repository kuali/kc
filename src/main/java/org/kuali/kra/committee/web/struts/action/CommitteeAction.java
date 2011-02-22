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
package org.kuali.kra.committee.web.struts.action;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.impl.KraDocumentServiceImpl;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * The CommitteeAction is the base class for all Committee actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action classes handle
 * the user requests for a particular tab (web page).
 */
public abstract class CommitteeAction extends KraTransactionalDocumentActionBase {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response); 
        return actionForward;
    }
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {    
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeDocument doc = committeeForm.getCommitteeDocument();
        
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, doc.getCommittee());
        if (isAuthorized(task)) {
            if (isValidSave(committeeForm)) {
                actionForward = super.save(mapping, form, request, response);
            }
        }

        return actionForward;
    }
    
    /**
     * Can the committee be saved?  This method is normally overridden by
     * a subclass in order to invoke business rules to verify that the
     * committee can be saved.
     * @param committeeForm the Committee Form
     * @return true if the committee can be saved; otherwise false
     */
    protected boolean isValidSave(CommitteeForm committeeForm) {
        return true;
    }

    /**
     * 
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#close(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        doProcessingAfterPost(committeeForm, request);   
        /*
        ActionForward actionForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);

        // only want to prompt them to save if they already can save
        if (canSave(committeeForm)) {
            Object question = getQuestion(request);
            // logic for close question
            if (question == null) {
                // ask question if not already asked
                return this.performQuestionWithoutInput(mapping, form, request, response,
                        KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, getKualiConfigurationService().getPropertyString(
                                RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), KNSConstants.CONFIRMATION_QUESTION,
                        KNSConstants.MAPPING_CLOSE, "");
            } else {
                Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
                if ((KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question))
                        && ConfirmationQuestion.YES.equals(buttonClicked)) {
                    // if yes button clicked - save the doc
                    getKraDocumentService().saveDocument(committeeForm.getDocument());
                }
                // else go to close logic below
            }
        } else {
            actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);                    
        }

        return actionForward;
        */
        
        return super.close(mapping, committeeForm, request, response);
    }

    private KraDocumentServiceImpl getKraDocumentService() {
        return ((KraDocumentServiceImpl) KraServiceLocator.getService("kraDocumentService"));

    }

    /**
     * We override this method to add in support for multi-lookups.
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);

        CommitteeForm committeeForm = (CommitteeForm) form;

        // KNS UI hook for lookup resultset, check to see if we are coming back from a lookup
        if (Constants.MULTIPLE_VALUE.equals(committeeForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = committeeForm.getLookupResultsSequenceNumber();

            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {

                Class lookupResultsBOClass = Class.forName(committeeForm.getLookupResultsBOClassName());
                String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
                LookupResultsService service = KraServiceLocator.getService(LookupResultsService.class);
                Collection<PersistableBusinessObject> selectedBOs = service.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, principalId);

                processMultipleLookupResults(committeeForm, lookupResultsBOClass, selectedBOs);
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method must be overridden by a derived class if that derived class has a field that requires a 
     * Lookup that returns multiple values.  The derived class should first check the class of the selected BOs.
     * Based upon the class, the Committee can be updated accordingly.  This is necessary since there may be
     * more than one multi-lookup on a web page.
     * 
     * @param committeeForm the Committee Form
     * @param lookupResultsBOClass the class of the BOs that are returned by the Lookup
     * @param selectedBOs the selected BOs
     */
    @SuppressWarnings("unchecked")
    protected void processMultipleLookupResults(CommitteeForm committeeForm, Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        // do nothing
    }
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        CommitteeForm committeeForm = (CommitteeForm) form;
        String command = committeeForm.getCommand();
        
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            committeeForm.setDocument(retrievedDocument);
            request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            forward = mapping.findForward(Constants.MAPPING_BASIC);
            forward = new ActionForward(forward.getPath()+ "?" + KNSConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else if ("committeeActions".equals(command)) {
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            committeeForm.setDocument(retrievedDocument);
            loadDocument(committeeForm);
            request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }

        if (KEWConstants.INITIATE_COMMAND.equals(committeeForm.getCommand())) {
            committeeForm.getCommitteeDocument().initialize();
        } else {
            committeeForm.initialize();
        }
        
        if ("committeeActions".equals(command)) {
            forward = committeeActions(mapping, committeeForm, request, response);
        }
        
        return forward;
    }
    
    /**
     * Go to the committee tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward committee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((CommitteeForm) form).getCommitteeHelper().prepareView();
        return mapping.findForward("committee");
    }

    /**
     * Go to the committeeMembership tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward committeeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((CommitteeForm) form).getCommitteeHelper().prepareView();
        ((CommitteeForm) form).getCommitteeHelper().flagInactiveMembers();
        return mapping.findForward("committeeMembership");
    }

    /**
     * Go to the committeeSchedule tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward committeeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // if 'submit' in async and with 'merging' delete. so, there is latency issue.  it is needed to refresh the schedules,
        // if user goes to schedule/maintenance directly immediately after 'submit'.
        KualiWorkflowDocument workflowDocument = ((CommitteeForm) form).getCommitteeDocument().getDocumentHeader().getWorkflowDocument();
        if (workflowDocument.stateIsEnroute() || workflowDocument.stateIsFinal()) {
            ((CommitteeForm) form).getCommitteeDocument().getCommittee().refreshReferenceObject("committeeSchedules");
        }
        ((CommitteeForm) form).getCommitteeHelper().prepareView();
        return mapping.findForward("committeeSchedule");
    }

    /**
     * Go to the committeeActions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward committeeActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((CommitteeForm)form).getCommitteeHelper().prepareView();
        return mapping.findForward("committeeActions");
    }

    /**
     * Get the Kuali Rule Service.
     * @return the Kuali Rule Service
     */
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward forward = super.route(mapping, form, request, response);
        
        Long routeHeaderId = Long.parseLong(((CommitteeForm) form).getCommitteeDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, "committeeActions", "CommitteeDocument");
        
        //ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(forward, forward, holdingPageForward, returnLocation);
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = super.blanketApprove(mapping, form, request, response);
        Long routeHeaderId = Long.parseLong(((CommitteeForm) form).getCommitteeDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, "committeeActions", "CommitteeDocument");
        
        //ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(forward, forward, holdingPageForward, returnLocation);
    }

}
