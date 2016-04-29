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
package org.kuali.kra.iacuc.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.iacuc.*;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.notification.IacucProtocolFundingSourceNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.protocol.funding.*;
import org.kuali.kra.iacuc.protocol.location.AddIacucProtocolLocationEvent;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocation;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationService;
import org.kuali.kra.iacuc.protocol.reference.*;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolEventBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolProposalDevelopmentDocumentService;
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

public class IacucProtocolProtocolAction extends IacucProtocolAction {
    
     
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;

        if ("close".equals(protocolForm.getMethodToCall()) || protocolForm.getMethodToCall() == null) {
            // If we're closing, we can just leave right here.
            return mapping.findForward(KRADConstants.MAPPING_PORTAL);
        }

        final String correctionMode = request.getParameter(Constants.CORRECTION_MODE);
        if (correctionMode != null && Boolean.parseBoolean(correctionMode)) {
            protocolForm.getProtocolDocument().getProtocol().setCorrectionMode(Boolean.TRUE);
        }

        protocolForm.getProtocolHelper().prepareView();

        return actionForward;
    }

    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProtocolFormBase protocolform = (ProtocolFormBase) form;

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
        if (lookupResultsBOClass.isAssignableFrom(IacucResearchArea.class)) {
            IacucProtocolResearchAreaService service = KcServiceLocator.getService("iacucProtocolResearchAreaService");
            service.addProtocolResearchArea(protocolDocument.getProtocol(), (Collection<ResearchAreaBase>) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            (new IacucProtocolDocumentRule()).processProtocolResearchAreaBusinessRules(protocolDocument);
        }
        
        
    }
 
    

    public ActionForward addProtocolReferenceBean(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm iacucProtocolForm = (IacucProtocolForm) form;
        IacucProtocolReferenceBean iacucProtocolReferenceBean = (IacucProtocolReferenceBean)iacucProtocolForm.getNewProtocolReferenceBean();
        IacucProtocolDocument iacucProtocolDoc = iacucProtocolForm.getIacucProtocolDocument(); 
        
        
        if (applyRules(new AddIacucProtocolReferenceEvent(Constants.EMPTY_STRING, iacucProtocolDoc, iacucProtocolReferenceBean))) {
            IacucProtocolReferenceType type = this.getBusinessObjectService().findBySinglePrimaryKey(IacucProtocolReferenceType.class, iacucProtocolReferenceBean.getProtocolReferenceTypeCode());
            
            IacucProtocolReference ref = new IacucProtocolReference(iacucProtocolReferenceBean, iacucProtocolDoc.getIacucProtocol(), type);
            
            IacucProtocolReferenceService service = KcServiceLocator.getService(IacucProtocolReferenceService.class);

            service.addProtocolReference(iacucProtocolDoc.getIacucProtocol(), ref);
            
            iacucProtocolForm.setNewProtocolReferenceBean(new IacucProtocolReferenceBean());
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;

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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        protocolForm.getProtocolDocument().getProtocol().getProtocolResearchAreas().remove(getLineToDelete(request));
        // finally do validation and error reporting for inactive research areas
        (new IacucProtocolDocumentRule()).processProtocolResearchAreaBusinessRules(protocolForm.getProtocolDocument());
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
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolLocation newProtocolLocation = (IacucProtocolLocation) protocolForm.getProtocolHelper().getNewProtocolLocation();

        if (applyRules(new AddIacucProtocolLocationEvent(Constants.EMPTY_STRING, protocolForm.getIacucProtocolDocument(), newProtocolLocation))) {
            getProtocolLocationService().addProtocolLocation(protocolForm.getProtocolDocument().getProtocol(), newProtocolLocation);
            protocolForm.getProtocolHelper().setNewProtocolLocation(new IacucProtocolLocation());
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        getProtocolLocationService().clearProtocolLocationAddress(protocolForm.getProtocolDocument().getProtocol(),
                getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolLocationService
     */
    private IacucProtocolLocationService getProtocolLocationService() {
        return KcServiceLocator.getService("iacucProtocolLocationService");
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocolFundingSource fundingSource = (IacucProtocolFundingSource) protocolForm.getProtocolHelper().getNewFundingSource();
        List<ProtocolFundingSourceBase> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
        AddIacucProtocolFundingSourceEvent event = new AddIacucProtocolFundingSourceEvent(Constants.EMPTY_STRING, protocolDocument,
            fundingSource, protocolFundingSources);

        protocolForm.getProtocolHelper().syncFundingSources(protocolDocument.getProtocol());

        if (applyRules(event)) {
            protocolDocument.getProtocol().getProtocolFundingSources().add(protocolForm.getProtocolHelper().getNewFundingSource());
            protocolForm.getProtocolHelper().setNewFundingSource(new IacucProtocolFundingSource());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is linked to ProtocolFundingSourceService to Delete a ProtocolFundingSourceBase. Method is called in
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
     * This method is linked to ProtocolFundingSourceService to Delete a ProtocolFundingSourceBase. Method is called in
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
            ProtocolFormBase protocolForm = (ProtocolFormBase) form;
            ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
            
            ProtocolFundingSourceBase protocolFundingSource = protocolDocument.getProtocol().getProtocolFundingSources().remove(getLineToDelete(request));
            protocolForm.getProtocolHelper().getDeletedProtocolFundingSources().add(protocolFundingSource);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolFundingSourceService to View a ProtocolFundingSourceBase. Method is called in
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;

        // Note that if the getSelectedLine doesn't find the line number in the new window's request attributes,
        // so we'll get it from the parameter list instead
        String line = request.getParameter("line");
        int lineNumber;
        try{
            lineNumber = Integer.parseInt(line);
        }
        catch(Exception e) {
            lineNumber = getLineToDelete(request);
        }

        ProtocolFundingSourceBase protocolFundingSource = protocolForm.getProtocolDocument().getProtocol().getProtocolFundingSources().get(
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
     * Exposing this to be used in ProtocolFundingSourceBase Service so we can avoid stacking funding source conditional logic in the
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

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;

        String fundingSourceTypeCode = protocolForm.getProtocolHelper().getNewFundingSource().getFundingSourceTypeCode();

        LookupIacucProtocolFundingSourceEvent event = new LookupIacucProtocolFundingSourceEvent(
            Constants.EMPTY_STRING, (IacucProtocolDocument) protocolForm.getDocument(), fundingSourceTypeCode, ProtocolEventBase.ErrorType.HARDERROR);

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
            returnAction = mapping.findForward(Constants.MAPPING_BASIC);
        }

        return returnAction;
    }


    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolFundingSourceService
     */
    private IacucProtocolFundingSourceService getProtocolFundingSourceService() {
        return KcServiceLocator.getService(IacucProtocolFundingSourceService.class);
    }

    /**
     * This method is to return Proposal Development Document service
     * 
     * @return ProtocolProposalDevelopmentDocumentService
     */
    private IacucProtocolProposalDevelopmentDocumentService getProtocolProposalDevelopmentDocumentService() {
        return KcServiceLocator.getService(IacucProtocolProposalDevelopmentDocumentService.class);
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
        if ( protocolForm.getProtocolHelper().isProtocolProposalDevelopmentLinkingEnabled())
        {
            ProtocolProposalDevelopmentDocumentService service = getProtocolProposalDevelopmentDocumentService(); 
            ProposalDevelopmentDocument proposalDevelopmentDocument = service.createProposalDevelopmentDocument(protocolForm);
            if (proposalDevelopmentDocument != null )
            {
                DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        
                IacucProtocolFundingSourceServiceImpl protocolFundingSourceServiceImpl = (IacucProtocolFundingSourceServiceImpl) getProtocolFundingSourceService();
                IacucProtocolFundingSource proposalProtocolFundingSource = (IacucProtocolFundingSource) protocolFundingSourceServiceImpl.updateProtocolFundingSource(FundingSourceType.PROPOSAL_DEVELOPMENT, developmentProposal.getProposalNumber(), developmentProposal.getSponsorName());
                proposalProtocolFundingSource.setProtocol(protocolDocument.getProtocol());
               
                List<ProtocolFundingSourceBase> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
                AddIacucProtocolFundingSourceEvent event = 
                        new AddIacucProtocolFundingSourceEvent(Constants.EMPTY_STRING, protocolDocument, proposalProtocolFundingSource, protocolFundingSources);
                
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
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();     
        List<ProtocolFundingSourceBase> protocolFundingSources = protocolDocument.getProtocol().getProtocolFundingSources();
        List<ProtocolFundingSourceBase> deletedProtocolFundingSources = protocolForm.getProtocolHelper().getDeletedProtocolFundingSources();
        protocolForm.getProtocolHelper().setNewProtocolFundingSources(protocolForm.getProtocolHelper().findNewFundingSources());
        setDeletedFundingSource(form);
        
        
        protocolForm.getProtocolHelper().prepareRequiredFieldsForSave();

      
        protocolForm.getProtocolHelper().createInitialProtocolAction();
        
        if (protocolDocument.getProtocol().isNew()) {
            if (applyRules(new SaveIacucProtocolFundingSourceLinkEvent((IacucProtocolDocument) protocolDocument, protocolFundingSources, deletedProtocolFundingSources))) {
                protocolForm.getProtocolHelper().syncSpecialReviewsWithFundingSources();
            }
        }
        
    }
    
    
    
         
    private void setDeletedFundingSource(ActionForm form) {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
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

    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        super.postSave(mapping, form, request, response);     
        fundingSourceNotification(form);

    }   

    private void fundingSourceNotification(ActionForm form) {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        for (ProtocolFundingSourceBase fundingSource : protocolForm.getProtocolHelper().getNewProtocolFundingSources()) {
            String fundingType = "'" + fundingSource.getFundingSourceType().getDescription() + "': " + fundingSource.getFundingSourceNumber();
            IacucProtocolFundingSourceNotificationRenderer renderer = new IacucProtocolFundingSourceNotificationRenderer(protocol, fundingType, "linked to");
            IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.FUNDING_SOURCE, "Funding Source", renderer);
            getKcNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
        }
        for (ProtocolFundingSourceBase fundingSource : protocolForm.getDeletedProtocolFundingSources()) {
            if (fundingSource.getProtocolFundingSourceId() != null) {
                String fundingType = "'" + fundingSource.getFundingSourceType().getDescription() + "': " + fundingSource.getFundingSourceNumber();
                IacucProtocolFundingSourceNotificationRenderer renderer = new IacucProtocolFundingSourceNotificationRenderer(protocol, fundingType, "removed from");
                IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.FUNDING_SOURCE, "Funding Source", renderer);
                getKcNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
            }

        }
    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        IacucProtocolForm protocolForm = (IacucProtocolForm)form;
        protocolForm.getCustomDataHelper().prepareCustomData();
        return mapping.findForward("iacucCustomData");
    }
    
    public ActionForward iacucSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return super.specialReview(mapping, form, request, response);
    }

    public ActionForward speciesAndGroups(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        ((IacucProtocolForm) form).getIacucProtocolSpeciesHelper().prepareView();
        return mapping.findForward("iacucSpeciesAndGroups");
    }
    
    public ActionForward procedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        ((IacucProtocolForm) form).getIacucProtocolProceduresHelper().prepareView();
        return mapping.findForward("iacucProtocolProcedures");
    }

    private KcNotificationService getKcNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }
}
