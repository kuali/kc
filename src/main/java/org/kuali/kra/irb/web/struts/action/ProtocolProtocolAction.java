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
package org.kuali.kra.irb.web.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.document.Document;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.ResearchAreas;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.service.ProtocolReferenceService;
import org.kuali.kra.irb.bo.ProtocolResearchAreas;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;

import edu.iu.uis.eden.clientapp.IDocHandler;

/**
 * The ProtocolProtocolAction corresponds to the Protocol tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class ProtocolProtocolAction extends ProtocolAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolProtocolAction.class);
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {      
        return super.save(mapping, form, request, response);
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        //Call to prepareView sets Labels Values from configuration service
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * @see org.kuali.kra.irb.web.struts.action.ProtocolAction#processMultipleLookupResults(org.kuali.kra.irb.document.ProtocolDocument, java.lang.Class, java.util.Collection)
     */
    @Override
    protected void processMultipleLookupResults(ProtocolDocument protocolDocument, Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        if (lookupResultsBOClass.isAssignableFrom(ResearchAreas.class)) {
            addResearchAreas(protocolDocument, selectedBOs);
        }
    }
    
    /**
     * When a multi-lookup returns for a set of Research Areas, we must add them to the Protocol Document.
     * Note that we don't add duplicate research areas.
     * NOTE: This should be moved to a service since it is business logic.
     * @param protocolDocument the Protocol Document
     * @param selectedBOs the selected BOs (Research Areas)
     */
    private void addResearchAreas(ProtocolDocument protocolDocument, Collection<PersistableBusinessObject> selectedBOs) {
        for (PersistableBusinessObject bo : selectedBOs) {
            //New ResearchAreas added by user selection
            ResearchAreas newResearchAreas = (ResearchAreas) bo;
            // ignore / drop duplicates
            if (!isDuplicateResearchAreas(newResearchAreas, protocolDocument.getProtocol().getProtocolResearchAreas())) {
                //Add new ProtocolResearchAreas to list
                protocolDocument.getProtocol().addProtocolResearchAreas(createInstanceOfProtocolResearchAreas(protocolDocument, newResearchAreas));
            }
        }
    }
    
    /**
     * This method is private helper method, to create instance of ProtocolResearchAreas and set appropriate values.
     * @param protocolDocument
     * @param researchAreas
     * @return
     */
    private ProtocolResearchAreas createInstanceOfProtocolResearchAreas(ProtocolDocument protocolDocument, ResearchAreas researchAreas) {
        ProtocolResearchAreas protocolResearchAreas = new ProtocolResearchAreas();
        protocolResearchAreas.setProtocol(protocolDocument);                            
        if(null != protocolDocument.getProtocol().getProtocolId())
            protocolResearchAreas.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        
        if(null != protocolDocument.getProtocol().getProtocolNumber())
            protocolResearchAreas.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        else
            protocolResearchAreas.setProtocolNumber("0");
        
        if(null != protocolDocument.getProtocol().getSequenceNumber())
            protocolResearchAreas.setSequenceNumber(protocolDocument.getProtocol().getSequenceNumber());
        else
            protocolResearchAreas.setSequenceNumber(0);
        
        protocolResearchAreas.setResearchAreaCode(researchAreas.getResearchAreaCode());
        protocolResearchAreas.setResearchAreas(researchAreas);
        return protocolResearchAreas;
    }
    
    /**
     * This method is private helper method, to restrict duplicate ProtocolResearchAreas insertion in list.
     * @param newResearchAreaCode
     * @param protocolResearchAreas
     * @return
     */
    private boolean isDuplicateResearchAreas(ResearchAreas newResearchAreas, List<ProtocolResearchAreas> protocolResearchAreas) {
        for (ProtocolResearchAreas pra  : protocolResearchAreas) {    
            if (pra.getResearchAreas().equals(newResearchAreas)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        String command = protocolForm.getCommand();
        
        if (IDocHandler.ACTIONLIST_INLINE_COMMAND.equals(command)) {
             String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
             Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
             protocolForm.setDocument(retrievedDocument);
             request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
             forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
             forward = new ActionForward(forward.getPath()+ "?" + KNSConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else {
             forward = super.docHandler(mapping, form, request, response);
        }

        if (IDocHandler.INITIATE_COMMAND.equals(protocolForm.getCommand())) {
            protocolForm.getProtocolDocument().initialize();
        }else{
            protocolForm.initialize();
        }
        
        return forward;
    }
    
    /**
     * This method is hook to KNS, selects/sets select boolean field to true of ProtocolResearchArea for the UI list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectAllProtocolDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        List<ProtocolResearchAreas> listOfProtocolResearchAreas = protocolDocument.getProtocol().getProtocolResearchAreas();
        
        for (ProtocolResearchAreas protocolResearchAreas: listOfProtocolResearchAreas) {  
            //Transient field set to true
            protocolResearchAreas.setSelectResearchArea(true);
        }

        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is hook to KNS, deletes selected ProtocolResearchArea from the UI list. Method is called in protocolAdditonalInformation.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSelectedProtocolDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        List<ProtocolResearchAreas> listOfProtocolResearchAreas = protocolDocument.getProtocol().getProtocolResearchAreas();
        
        List<ProtocolResearchAreas> newProtocolResearchAreas = new ArrayList<ProtocolResearchAreas>();
        
        for (ProtocolResearchAreas protocolResearchAreas  : listOfProtocolResearchAreas) {
            //Filters out not selected object
            if (!protocolResearchAreas.getSelectResearchArea()) {
                newProtocolResearchAreas.add(protocolResearchAreas);
            }
        }
        protocolDocument.getProtocol().setProtocolResearchAreas(newProtocolResearchAreas);

        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    
//  public ActionForward deleteProtocolParticipant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//      ProtocolForm protocolForm = (ProtocolForm) form;
//      protocolForm.getProposalDevelopmentDocument().getPropSpecialReviews().remove(getLineToDelete(request));
//      proposalDevelopmentForm.getDocumentExemptNumbers().remove(getLineToDelete(request));
//      GlobalVariables.getErrorMap().clear();
//
//      return mapping.findForward("basic");
//  }
    
    public ActionForward addProtocolParticipant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolParticipant newProtocolParticipant = protocolForm.getNewProtocolParticipant();
        if(applyRules(new AddProtocolParticipantEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolParticipant))) {
            protocolForm.getProtocolDocument().getProtocol().getProtocolParticipants().add(newProtocolParticipant);
            protocolForm.setNewProtocolParticipant(new ProtocolParticipant());
        }
        return mapping.findForward("basic");
    }
    
    /**
     * This method is hook to KNS, it adds ProtocolReference. Method is called in protocolAdditonalInformation.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolReference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolReference newProtocolReference = protocolForm.getNewProtocolReference();
        
        if(null == newProtocolReference.getProtocolReferenceTypeCode()) {
            GlobalVariables.getErrorMap().putError("Type", "cannot be null");
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        ProtocolReferenceService service = (ProtocolReferenceService)KraServiceLocator.getService("protocolReferenceTypeService");
        
        service.addProtocolReference(protocolForm.getProtocolDocument().getProtocol(), newProtocolReference);
              
        protocolForm.setNewProtocolReference(new ProtocolReference());
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    

    /**
     * This method is hook to KNS, it deletes selected ProtocolReference from the UI list. Method is called in protocolAdditonalInformation.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolReference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        ProtocolReferenceService service = (ProtocolReferenceService)KraServiceLocator.getService("protocolReferenceTypeService");
        
        service.deleteProtocolReference(protocolForm.getProtocolDocument().getProtocol(), getLineToDelete(request));
   
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
}
