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
package org.kuali.coeus.common.committee.impl.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

/**
 * The CommitteeActionBase is the base class for all CommitteeBase actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action classes handle
 * the user requests for a particular tab (web page).
 */
public abstract class CommitteeActionBase extends KcTransactionalDocumentActionBase {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeActionBase.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response); 
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {    
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeDocumentBase doc = committeeForm.getCommitteeDocument();

        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.MODIFY_COMMITTEE, doc.getCommittee());
        if (isAuthorized(task)) {
            if (isValidSave(committeeForm)) {
                actionForward = super.save(mapping, form, request, response);
            }
        }

        return actionForward;
    }
    
    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);

    
    /**
     * Can the committee be saved?  This method is normally overridden by
     * a subclass in order to invoke business rules to verify that the
     * committee can be saved.
     * @param committeeForm the CommitteeBase Form
     * @return true if the committee can be saved; otherwise false
     */
    protected boolean isValidSave(CommitteeFormBase committeeForm) {
        return true;
    }

    /**
     * 
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#close(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        doProcessingAfterPost(committeeForm, request);
        
        return super.close(mapping, committeeForm, request, response);
    }

    /**
     * We override this method to add in support for multi-lookups.
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);

        CommitteeFormBase committeeForm = (CommitteeFormBase) form;

        // KNS UI hook for lookup resultset, check to see if we are coming back from a lookup
        if (Constants.MULTIPLE_VALUE.equals(committeeForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = committeeForm.getLookupResultsSequenceNumber();

            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {

                Class lookupResultsBOClass = Class.forName(committeeForm.getLookupResultsBOClassName());
                String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
                LookupResultsService service = KcServiceLocator.getService(LookupResultsService.class);
                Collection<PersistableBusinessObject> selectedBOs = service.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, principalId);

                processMultipleLookupResults(committeeForm, lookupResultsBOClass, selectedBOs);
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method must be overridden by a derived class if that derived class has a field that requires a 
     * Lookup that returns multiple values.  The derived class should first check the class of the selected BOs.
     * Based upon the class, the CommitteeBase can be updated accordingly.  This is necessary since there may be
     * more than one multi-lookup on a web page.
     * 
     * @param committeeForm the CommitteeBase Form
     * @param lookupResultsBOClass the class of the BOs that are returned by the Lookup
     * @param selectedBOs the selected BOs
     */
    @SuppressWarnings("unchecked")
    protected void processMultipleLookupResults(CommitteeFormBase committeeForm, Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        // do nothing
    }
    
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        String command = committeeForm.getCommand();
        
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            committeeForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            forward = mapping.findForward(Constants.MAPPING_BASIC);
            forward = new ActionForward(forward.getPath()+ "?" + KRADConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else if ("committeeActions".equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            committeeForm.setDocument(retrievedDocument);
            loadDocument(committeeForm);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }

        if (KewApiConstants.INITIATE_COMMAND.equals(committeeForm.getCommand())) {
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
        ((CommitteeFormBase) form).getCommitteeHelper().prepareView();
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
        ((CommitteeFormBase) form).getCommitteeHelper().prepareView();
        ((CommitteeFormBase) form).getCommitteeHelper().flagInactiveMembers();
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
        WorkflowDocument workflowDocument = ((CommitteeFormBase) form).getCommitteeDocument().getDocumentHeader().getWorkflowDocument();
        if (workflowDocument.isEnroute() || workflowDocument.isFinal()) {
            ((CommitteeFormBase) form).getCommitteeDocument().getCommittee().refreshReferenceObject("committeeSchedules");
        }
        ((CommitteeFormBase) form).getCommitteeHelper().prepareView();
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
        ((CommitteeFormBase)form).getCommitteeHelper().prepareView();
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
    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward forward = super.route(mapping, form, request, response);
        
        String routeHeaderId = ((CommitteeFormBase) form).getCommitteeDocument().getDocumentNumber();

        String returnLocation = buildActionUrl(routeHeaderId, "committeeActions", getCommitteeDocumentTypeSimpleNameHook());

        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(forward, forward, holdingPageForward, returnLocation, routeHeaderId);
    }

    protected abstract String getCommitteeDocumentTypeSimpleNameHook();
    

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = super.blanketApprove(mapping, form, request, response);
        String routeHeaderId = ((CommitteeFormBase) form).getCommitteeDocument().getDocumentNumber();

        String returnLocation = buildActionUrl(routeHeaderId, "committeeActions", getCommitteeDocumentTypeSimpleNameHook());

        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);

        return routeToHoldingPage(forward, forward, holdingPageForward, returnLocation, routeHeaderId);
    }
    
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        super.populateAuthorizationFields(formBase);
        CommitteeFormBase committeeForm = (CommitteeFormBase) formBase;
        String command = committeeForm.getCommand();
        Map documentActions = formBase.getDocumentActions();
        if ("displayDocSearchView".equals(command)){
            if (documentActions.containsKey(KRADConstants.KUALI_ACTION_CAN_RELOAD)) {
                documentActions.remove(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            }
        }
    }

}
