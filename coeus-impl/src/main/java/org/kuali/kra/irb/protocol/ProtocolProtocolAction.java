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
package org.kuali.kra.irb.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.*;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.FundingSourceNotificationRenderer;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.protocol.funding.*;
import org.kuali.kra.irb.protocol.location.AddProtocolLocationEvent;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.participant.AddProtocolParticipantEvent;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipantService;
import org.kuali.kra.irb.protocol.reference.*;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

/**
 * The ProtocolProtocolAction corresponds to the Protocol tab (web page). It is responsible for handling all user requests from that
 * tab (web page).
 */
public class ProtocolProtocolAction extends ProtocolAction {

    private static final Log LOG = LogFactory.getLog(ProtocolProtocolAction.class);

    private static final String CONFIRM_DELETE_PROTOCOL_FUNDING_SOURCE_KEY = "confirmDeleteProtocolFundingSource";

    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);

        // Following is for protocol lookup - edit protocol
        ProtocolForm protocolForm = (ProtocolForm) form;
        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);

        if (StringUtils.isNotBlank(commandParam) && commandParam.equals(KewApiConstants.DOCSEARCH_COMMAND)
                && StringUtils.isNotBlank(request.getParameter("submissionId"))) {
            // protocolsubmission lookup
            for (ProtocolSubmissionBase protocolSubmission : protocolForm.getProtocolDocument().getProtocol().getProtocolSubmissions()) {
                if (StringUtils.isNotBlank(request.getParameter("submissionId"))) {
                    protocolForm.getProtocolDocument().getProtocol().setNotifyIrbSubmissionId(Long.parseLong(request.getParameter("submissionId")));
                }
                if (request.getParameter("submissionId").equals(protocolSubmission.getSubmissionId().toString())) {
                    protocolForm.getProtocolDocument().getProtocol().setProtocolSubmission(protocolSubmission);
                    break;
                }
            }
        }

        if ("close".equals(protocolForm.getMethodToCall()) || protocolForm.getMethodToCall() == null) {
            // If we're closing, we can just leave right here.
            return mapping.findForward(KRADConstants.MAPPING_PORTAL);
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
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docId);
            protocolform.setDocument(retrievedDocument);
        }

        return super.headerTab(mapping, form, request, response);
    }

    @Override
    protected <T extends BusinessObject> void processMultipleLookupResults(ProtocolDocumentBase protocolDocument,
            Class<T> lookupResultsBOClass, Collection<T> selectedBOs) {
        if (lookupResultsBOClass.isAssignableFrom(ResearchArea.class)) {
            ProtocolResearchAreaService service = KcServiceLocator.getService(ProtocolResearchAreaService.class);
            service.addProtocolResearchArea(protocolDocument.getProtocol(), (Collection<ResearchAreaBase>) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            (new ProtocolDocumentRule()).processProtocolResearchAreaBusinessRules((ProtocolDocument) protocolDocument);
        }
        
        
    }
    
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
        ProtocolParticipant newProtocolParticipant = ((ProtocolHelper) protocolForm.getProtocolHelper()).getNewProtocolParticipant();
        List<ProtocolParticipant> protocolParticipants = ((Protocol) protocolForm.getProtocolDocument().getProtocol()).getProtocolParticipants();

        if (applyRules(new AddProtocolParticipantEvent((ProtocolDocument) protocolForm.getProtocolDocument(), newProtocolParticipant, protocolParticipants))) {
            getProtocolParticipantService().addProtocolParticipant((Protocol) protocolForm.getProtocolDocument().getProtocol(), newProtocolParticipant);
            ((ProtocolHelper) protocolForm.getProtocolHelper()).setNewProtocolParticipant(new ProtocolParticipant());          
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
        ((Protocol) protocolForm.getProtocolDocument().getProtocol()).getProtocolParticipants().remove(getLineToDelete(request));

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

        if (applyRules(new AddProtocolReferenceEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolReference))) {

            ProtocolReferenceService service = KcServiceLocator.getService(ProtocolReferenceService.class);

            service.addProtocolReference(protocolForm.getProtocolDocument().getProtocol(), newProtocolReference);

            protocolForm.setNewProtocolReference(new ProtocolReference());

        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }*/
    
    public ActionForward addProtocolReferenceBean(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolReferenceBean bean = (ProtocolReferenceBean) protocolForm.getNewProtocolReferenceBean();
        
        if (applyRules(new AddProtocolReferenceEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), bean))) {
            ProtocolReferenceType type = this.getBusinessObjectService().findBySinglePrimaryKey(ProtocolReferenceType.class, bean.getProtocolReferenceTypeCode());
            
            ProtocolReference ref = new ProtocolReference(bean, (Protocol) protocolForm.getProtocolDocument().getProtocol(), type);
            
            ProtocolReferenceService service = KcServiceLocator.getService(ProtocolReferenceService.class);

            service.addProtocolReference(protocolForm.getProtocolDocument().getProtocol(), ref);
            
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

        protocolForm.getProtocolDocument().getProtocol().getProtocolReferences().remove(getLineToDelete(request));

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
        protocolForm.getProtocolDocument().getProtocol().getProtocolResearchAreas().remove(getLineToDelete(request));
        // finally do validation and error reporting for inactive research areas
        (new ProtocolDocumentRule()).processProtocolResearchAreaBusinessRules((ProtocolDocument) protocolForm.getProtocolDocument());
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
        ProtocolLocation newProtocolLocation = (ProtocolLocation) protocolForm.getProtocolHelper().getNewProtocolLocation();

        if (applyRules(new AddProtocolLocationEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolLocation))) {
            getProtocolLocationService().addProtocolLocation(protocolForm.getProtocolDocument().getProtocol(), newProtocolLocation);
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
        protocolForm.getProtocolDocument().getProtocol().getProtocolLocations().remove(getLineToDelete(request));
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
        getProtocolLocationService().clearProtocolLocationAddress(protocolForm.getProtocolDocument().getProtocol(),
                getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is to get protocol participant service
     * 
     * @return ProtocolPersonnelService
     */
    private ProtocolParticipantService getProtocolParticipantService() {
        return (ProtocolParticipantService) KcServiceLocator.getService("protocolParticipantTypeService");
    }

    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolLocationService
     */
    private ProtocolLocationService getProtocolLocationService() {
        return (ProtocolLocationService) KcServiceLocator.getService("protocolLocationService");
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
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolForm.getProtocolHelper().getNewFundingSource();
        List<ProtocolFundingSourceBase> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
        AddProtocolFundingSourceEvent event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, protocolDocument,
            fundingSource, (List)protocolFundingSources);

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
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROTOCOL_FUNDING_SOURCE_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
            
            ProtocolFundingSource protocolFundingSource = (ProtocolFundingSource) protocolDocument.getProtocol().getProtocolFundingSources().remove(getLineToDelete(request));
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

        ProtocolFundingSource protocolFundingSource = (ProtocolFundingSource) protocolForm.getProtocolDocument().getProtocol().getProtocolFundingSources().get(
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
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#buildForwardUrl(java.lang.Long)
     */
    @Override
    public String buildForwardUrl(String routeHeaderId) {
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
            String fullParameter = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
            String updatedParameter = getProtocolFundingSourceService().updateLookupParameter(fullParameter, boClassName, fieldConversions);

            request.setAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE, updatedParameter);
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
        return (ProtocolFundingSourceService) KcServiceLocator.getService(ProtocolFundingSourceService.class);
    }


    /**
     * This method is to return Proposal Development Document service
     * 
     * @return ProtocolProposalDevelopmentDocumentService
     */
    private ProtocolProposalDevelopmentDocumentService getProtocolProposalDevelopmentDocumentService() {
        return (ProtocolProposalDevelopmentDocumentService) KcServiceLocator.getService(ProtocolProposalDevelopmentDocumentService.class);
    }

    /**
     * This method is linked to ProtocolFundingService to perform the action - Create Proposal Development. Method is called in
     * protocolFundingSources.tag
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createProposalDevelopment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        
        if ( protocolForm.getProtocolHelper().isProtocolProposalDevelopmentLinkingEnabled())
        {
            ProtocolProposalDevelopmentDocumentService service = getProtocolProposalDevelopmentDocumentService(); 
            ProposalDevelopmentDocument proposalDevelopmentDocument = service.createProposalDevelopmentDocument(protocolForm);
            if (proposalDevelopmentDocument != null )
            {
                DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        
                ProtocolFundingSourceServiceImpl protocolFundingSourceServiceImpl = (ProtocolFundingSourceServiceImpl) getProtocolFundingSourceService(); 
                ProtocolFundingSource proposalProtocolFundingSource = (ProtocolFundingSource) protocolFundingSourceServiceImpl.updateProtocolFundingSource(FundingSourceType.PROPOSAL_DEVELOPMENT, developmentProposal.getProposalNumber(), developmentProposal.getSponsorName());
                proposalProtocolFundingSource.setProtocol(protocolDocument.getProtocol());
               
                List<ProtocolFundingSourceBase> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
                AddProtocolFundingSourceEvent event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, protocolDocument,
                        proposalProtocolFundingSource, (List)protocolFundingSources);
                
                if (applyRules(event)) {
                    protocolDocument.getProtocol().getProtocolFundingSources().add(proposalProtocolFundingSource);
                }
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        
        preSaveProtocol(form);        
    }

    private void preSaveProtocol(ActionForm form)  throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        List<ProtocolFundingSourceBase> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
        List<ProtocolFundingSourceBase> deletedProtocolFundingSources = protocolForm.getProtocolHelper().getDeletedProtocolFundingSources();
        protocolForm.getProtocolHelper().setNewProtocolFundingSources(protocolForm.getProtocolHelper().findNewFundingSources());
        setDeletedFundingSource(form);
        protocolForm.getProtocolHelper().prepareRequiredFieldsForSave();
        protocolForm.getProtocolHelper().createInitialProtocolAction();
        
        if (protocolDocument.getProtocol().isNew()) {
            if (applyRules(new SaveProtocolFundingSourceLinkEvent(protocolDocument, (List)protocolFundingSources, (List)deletedProtocolFundingSources))) {
                protocolForm.getProtocolHelper().syncSpecialReviewsWithFundingSources();
            }
        }
        
    }
    
    private void setDeletedFundingSource(ActionForm form) {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
       protocolForm.setDeletedProtocolFundingSources(new ArrayList<ProtocolFundingSourceBase> ());
        for (ProtocolFundingSourceBase fundingSource : protocolForm.getProtocolHelper().getDeletedProtocolFundingSources()) {
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
        super.postSave(mapping, form, request, response);
        fundingSourceNotification(form);

    }   

    private void fundingSourceNotification(ActionForm form) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        boolean fundingSourceGenerated = false;
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        for (ProtocolFundingSourceBase fundingSource : protocolForm.getProtocolHelper().getNewProtocolFundingSources()) {
            String fundingType = "'" + fundingSource.getFundingSourceType().getDescription() + "': " + fundingSource.getFundingSourceNumber();
            FundingSourceNotificationRenderer renderer = new FundingSourceNotificationRenderer(protocol, fundingType, "linked to");
            IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.FUNDING_SOURCE, "Funding Source", renderer);
            getKcNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
            fundingSourceGenerated = true;
        }
        for (ProtocolFundingSourceBase fundingSource : protocolForm.getDeletedProtocolFundingSources()) {
            if (fundingSource.getProtocolFundingSourceId() != null) {
                String fundingType = "'" + fundingSource.getFundingSourceType().getDescription() + "': " + fundingSource.getFundingSourceNumber();
                FundingSourceNotificationRenderer renderer = new FundingSourceNotificationRenderer(protocol, fundingType, "removed from");
                IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.FUNDING_SOURCE, "Funding Source", renderer);
                getKcNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
                fundingSourceGenerated = true;
            }
        }
        if(fundingSourceGenerated) {
            try {
                getProtocolActionRequestService().generateFundingSource(protocolForm);
            }catch(Exception ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.sendRedirect(buildForwardUrl(((ProtocolForm) form).getDocId()));
        return null;
    }

    private KcNotificationService getKcNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }
}
