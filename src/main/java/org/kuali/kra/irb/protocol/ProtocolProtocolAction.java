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
package org.kuali.kra.irb.protocol;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolDocumentRule;
import org.kuali.kra.irb.ProtocolEventBase;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.FundingSourceNotificationRenderer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.protocol.funding.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.protocol.funding.LookupProtocolFundingSourceEvent;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.irb.protocol.funding.SaveProtocolFundingSourceLinkEvent;
import org.kuali.kra.irb.protocol.location.AddProtocolLocationEvent;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.participant.AddProtocolParticipantEvent;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipantService;
import org.kuali.kra.irb.protocol.reference.AddProtocolReferenceEvent;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceBean;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceService;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceType;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The ProtocolProtocolAction corresponds to the Protocol tab (web page). It is responsible for handling all user requests from that
 * tab (web page).
 */
public class ProtocolProtocolAction extends ProtocolAction {
    
    private static final String CONFIRM_DELETE_PROTOCOL_FUNDING_SOURCE_KEY = "confirmDeleteProtocolFundingSource";

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);

        // Following is for protocol lookup - edit protocol
        ProtocolForm protocolForm = (ProtocolForm) form;
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);

        if (StringUtils.isNotBlank(commandParam) && commandParam.equals(KEWConstants.DOCSEARCH_COMMAND)
                && StringUtils.isNotBlank(request.getParameter("submissionId"))) {
            // protocolsubmission lookup
            for (ProtocolSubmission protocolSubmission : protocolForm.getDocument().getProtocol().getProtocolSubmissions()) {
                if (StringUtils.isNotBlank(request.getParameter("submissionId"))) {
                    protocolForm.getDocument().getProtocol().setNotifyIrbSubmissionId(Long.parseLong(request.getParameter("submissionId")));
                }
                if (request.getParameter("submissionId").equals(protocolSubmission.getSubmissionId().toString())) {
                    protocolForm.getDocument().getProtocol().setProtocolSubmission(protocolSubmission);
                    break;
                }
            }
        }

        protocolForm.getProtocolHelper().prepareView();

        return actionForward;
    }

    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProtocolForm protocolform = (ProtocolForm) form;

        String command = request.getParameter("command");
        String docId = request.getParameter("docId");

        if (StringUtils.isNotEmpty(command) && command.equals("displayDocSearchView") && StringUtils.isNotEmpty(docId)) {
            // copy link from protocol lookup - Copy Action
            Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
            protocolform.setDocument(retrievedDocument);
        }

        return super.headerTab(mapping, form, request, response);
    }

    @Override
    protected <T extends BusinessObject> void processMultipleLookupResults(ProtocolDocument protocolDocument,
            Class<T> lookupResultsBOClass, Collection<T> selectedBOs) {
        if (lookupResultsBOClass.isAssignableFrom(ResearchArea.class)) {
            ProtocolResearchAreaService service = (ProtocolResearchAreaService) KraServiceLocator
                    .getService("protocolResearchAreaService");
            service.addProtocolResearchArea(protocolDocument.getProtocol(), (Collection<ResearchArea>) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            (new ProtocolDocumentRule()).processProtocolResearchAreaBusinessRules(protocolDocument);
        }
        
        
    }
    
    
    /**
     * @see org.kuali.kra.irb.ProtocolAction#processMultipleLookupResults(org.kuali.kra.irb.ProtocolDocument, java.lang.Class,
     *      java.util.Collection)
     */
/*    @Override
    protected <T extends ResearchArea> void processMultipleLookupResults(ProtocolDocument protocolDocument, Class<T> lookupResultsBOClass,
            Collection<T> selectedBOs) {

    }*/

    /**
     * 
     * This method adds an <code>ProtocolParticipant</code> business object to the list of <code>ProtocolParticipants</code>
     * business objects It gets called upon add action on the Participant Types sub-panel of the Protocol panel
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolParticipant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolParticipant newProtocolParticipant = protocolForm.getProtocolHelper().getNewProtocolParticipant();
        List<ProtocolParticipant> protocolParticipants = protocolForm.getProtocolDocument().getProtocol().getProtocolParticipants();

        if (applyRules(new AddProtocolParticipantEvent(protocolForm.getDocument(), newProtocolParticipant, protocolParticipants))) {
            getProtocolParticipantService().addProtocolParticipant(protocolForm.getProtocolDocument().getProtocol(), newProtocolParticipant);
            protocolForm.getProtocolHelper().setNewProtocolParticipant(new ProtocolParticipant());          
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method deletes an <code>ProtocolParticipant</code> business object from the list of <code>ProtocolParticipants</code>
     * business objects It gets called upon delete action on the Participant Types sub-panel of the Protocol panel
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolParticipant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getDocument().getProtocol().getProtocolParticipants().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is hook to KNS, it adds ProtocolReference. Method is called in protocolAdditonalInformation.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     
    public ActionForward addProtocolReference(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolReference newProtocolReference = protocolForm.getNewProtocolReference();

        if (applyRules(new AddProtocolReferenceEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), newProtocolReference))) {

            ProtocolReferenceService service = KraServiceLocator.getService(ProtocolReferenceService.class);

            service.addProtocolReference(protocolForm.getDocument().getProtocol(), newProtocolReference);

            protocolForm.setNewProtocolReference(new ProtocolReference());

        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }*/
    
    public ActionForward addProtocolReferenceBean(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolReferenceBean bean = protocolForm.getNewProtocolReferenceBean();
        
        if (applyRules(new AddProtocolReferenceEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), bean))) {
            ProtocolReferenceType type = this.getBusinessObjectService().findBySinglePrimaryKey(ProtocolReferenceType.class, bean.getProtocolReferenceTypeCode());
            
            ProtocolReference ref = new ProtocolReference(bean, protocolForm.getProtocolDocument().getProtocol(), type);
            
            ProtocolReferenceService service = KraServiceLocator.getService(ProtocolReferenceService.class);

            service.addProtocolReference(protocolForm.getDocument().getProtocol(), ref);
            
            //protocolForm.getProtocolDocument().getProtocol().getProtocolReferences().add(ref);
            protocolForm.setNewProtocolReferenceBean(new ProtocolReferenceBean());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    /**
     * This method is hook to KNS, it deletes selected ProtocolReference from the UI list. Method is called in
     * protocolAdditonalInformation.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolReference(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;

        protocolForm.getDocument().getProtocol().getProtocolReferences().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is hook to KNS, it deletes selected ProtocolResearchArea from the UI list. Method is called in
     * protocolAdditonalInformation.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolResearchArea(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getDocument().getProtocol().getProtocolResearchAreas().remove(getLineToDelete(request));
        // finally do validation and error reporting for inactive research areas
        (new ProtocolDocumentRule()).processProtocolResearchAreaBusinessRules(protocolForm.getDocument());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolLocationService to perform the action - Add Protocol Location. Method is called in
     * protocolLocations.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolLocation newProtocolLocation = protocolForm.getProtocolHelper().getNewProtocolLocation();

        if (applyRules(new AddProtocolLocationEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), newProtocolLocation))) {
            getProtocolLocationService().addProtocolLocation(protocolForm.getDocument().getProtocol(), newProtocolLocation);
            protocolForm.getProtocolHelper().setNewProtocolLocation(new ProtocolLocation());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolLocationService to perform the action - Delete Protocol Location. Method is called in
     * protocolLocations.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getDocument().getProtocol().getProtocolLocations().remove(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolLocationService to perform the action - Clear Protocol Location address. Method is called in
     * protocolLocations.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearProtocolLocationAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolLocationService().clearProtocolLocationAddress(protocolForm.getDocument().getProtocol(),
                getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is to get protocol participant service
     * 
     * @return ProtocolPersonnelService
     */
    private ProtocolParticipantService getProtocolParticipantService() {
        return (ProtocolParticipantService) KraServiceLocator.getService("protocolParticipantTypeService");
    }

    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolLocationService
     */
    private ProtocolLocationService getProtocolLocationService() {
        return (ProtocolLocationService) KraServiceLocator.getService("protocolLocationService");
    }

    /**
     * This method is linked to ProtocolFundingService to perform the action - Add Protocol Funding Source. Method is called in
     * protocolFundingSources.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolFundingSource(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        ProtocolFundingSource fundingSource = protocolForm.getProtocolHelper().getNewFundingSource();
        List<ProtocolFundingSource> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
        AddProtocolFundingSourceEvent event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, protocolDocument,
            fundingSource, protocolFundingSources);

        protocolForm.getProtocolHelper().syncFundingSources(protocolDocument.getProtocol());

        if (applyRules(event)) {
            protocolDocument.getProtocol().getProtocolFundingSources().add(protocolForm.getProtocolHelper().getNewFundingSource());
            protocolForm.getProtocolHelper().setNewFundingSource(new ProtocolFundingSource());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is linked to ProtocolFundingSourceService to Delete a ProtocolFundingSource. Method is called in
     * protocolFundingSources.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolFundingSource(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROTOCOL_FUNDING_SOURCE_KEY,
                KeyConstants.QUESTION_PROTOCOL_FUNDING_SOURCE_DELETE_CONFIRMATION), CONFIRM_DELETE_PROTOCOL_FUNDING_SOURCE_KEY, "");
    }

    /**
     * This method is linked to ProtocolFundingSourceService to Delete a ProtocolFundingSource. Method is called in
     * protocolFundingSources.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteProtocolFundingSource(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROTOCOL_FUNDING_SOURCE_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolDocument protocolDocument = protocolForm.getDocument();
            
            ProtocolFundingSource protocolFundingSource = protocolDocument.getProtocol().getProtocolFundingSources().remove(getLineToDelete(request));
            protocolForm.getProtocolHelper().getDeletedProtocolFundingSources().add(protocolFundingSource);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolFundingSourceService to View a ProtocolFundingSource. Method is called in
     * protocolFundingSources.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewProtocolFundingSource(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;

        // Note that if the getSelectedLine doesn't find the line number in the new window's request attributes,
        // so we'll get it from the parameter list instead
        String line = request.getParameter("line");
        int lineNumber = Integer.parseInt(line);

        ProtocolFundingSource protocolFundingSource = protocolForm.getDocument().getProtocol().getProtocolFundingSources().get(
                lineNumber);

        String viewFundingSourceUrl = getProtocolFundingSourceService()
                .getViewProtocolFundingSourceUrl(protocolFundingSource, this);

        if (StringUtils.isNotEmpty(viewFundingSourceUrl)) {
            return new ActionForward(viewFundingSourceUrl, true);
        }
        else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }

    /**
     * Exposing this to be used in ProtocolFundingSource Service so we can avoid stacking funding source conditional logic in the
     * action
     * 
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#buildForwardUrl(java.lang.Long)
     */
    @Override
    public String buildForwardUrl(Long routeHeaderId) {
        return super.buildForwardUrl(routeHeaderId);
    }


    /**
     * 
     * Takes care of forwarding to the lookup action.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performFundingSourceLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        
        ActionForward returnAction = null;

        ProtocolForm protocolForm = (ProtocolForm) form;

        String fundingSourceTypeCode = protocolForm.getProtocolHelper().getNewFundingSource().getFundingSourceTypeCode();

        LookupProtocolFundingSourceEvent event = new LookupProtocolFundingSourceEvent(
            Constants.EMPTY_STRING, ((ProtocolForm) form).getDocument(), fundingSourceTypeCode, ProtocolEventBase.ErrorType.HARDERROR);

        if (applyRules(event)) {
            Entry<String, String> entry = getProtocolFundingSourceService().getLookupParameters(fundingSourceTypeCode);

            String boClassName = entry.getKey();
            String fieldConversions = entry.getValue();
            String fullParameter = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
            String updatedParameter = getProtocolFundingSourceService().updateLookupParameter(fullParameter, boClassName, fieldConversions);

            request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, updatedParameter);
            returnAction = super.performLookup(mapping, form, request, response);

            protocolForm.getProtocolHelper().setEditProtocolFundingSourceName(false);
        } else {
            returnAction = mapping.findForward(MAPPING_BASIC);
        }

        return returnAction;
    }


    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolFundingSourceService
     */
    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        return (ProtocolFundingSourceService) KraServiceLocator.getService(ProtocolFundingSourceService.class);
    }

    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        
        preSaveProtocol(form);        
    }

    private void preSaveProtocol(ActionForm form)  throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        List<ProtocolFundingSource> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
        List<ProtocolFundingSource> deletedProtocolFundingSources = protocolForm.getProtocolHelper().getDeletedProtocolFundingSources();
        protocolForm.getProtocolHelper().setNewProtocolFundingSources(protocolForm.getProtocolHelper().findNewFundingSources());
        setDeletedFundingSource(form);
        protocolForm.getProtocolHelper().prepareRequiredFieldsForSave();
        protocolForm.getProtocolHelper().createInitialProtocolAction();
        
        if (protocolDocument.getProtocol().isNew()) {
            if (applyRules(new SaveProtocolFundingSourceLinkEvent(protocolDocument, protocolFundingSources, deletedProtocolFundingSources))) {
                protocolForm.getProtocolHelper().syncSpecialReviewsWithFundingSources();
            }
        }
        
    }
    
    private void setDeletedFundingSource(ActionForm form) {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
       protocolForm.setDeletedProtocolFundingSources(new ArrayList<ProtocolFundingSource> ());
        for (ProtocolFundingSource fundingSource : protocolForm.getProtocolHelper().getDeletedProtocolFundingSources()) {
            if (fundingSource.getProtocolFundingSourceId() != null) {
                protocolForm.getDeletedProtocolFundingSources().add(fundingSource);
            }
        }
   }
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        preSaveProtocol(form);        
        ActionForward forward = super.saveOnClose(mapping, form, request, response);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            fundingSourceNotification(form);
        }
        return forward;
    }
    
    public ActionForward performProtocolAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        super.docHandler(mapping, form, request, response);
        
        return super.protocolActions(mapping, form, request, response);
    }

    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        super.postSave(mapping, form, request, response);
        fundingSourceNotification(form);

    }   

    private void fundingSourceNotification(ActionForm form) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        for (ProtocolFundingSource fundingSource : protocolForm.getProtocolHelper().getNewProtocolFundingSources()) {
            String fundingType = "'" + fundingSource.getFundingSourceType().getDescription() + "': " + fundingSource.getFundingSourceNumber();
            FundingSourceNotificationRenderer renderer = new FundingSourceNotificationRenderer(protocol, fundingType, "linked to");
            IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.FUNDING_SOURCE, "Funding Source", renderer);
            getKcNotificationService().sendNotification(context);

        }
        for (ProtocolFundingSource fundingSource : protocolForm.getDeletedProtocolFundingSources()) {
            if (fundingSource.getProtocolFundingSourceId() != null) {
                String fundingType = "'" + fundingSource.getFundingSourceType().getDescription() + "': " + fundingSource.getFundingSourceNumber();
                FundingSourceNotificationRenderer renderer = new FundingSourceNotificationRenderer(protocol, fundingType, "removed from");
                IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.FUNDING_SOURCE, "Funding Source", renderer);
                getKcNotificationService().sendNotification(context);
            }

        }
    }
    
    private KcNotificationService getKcNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }
}