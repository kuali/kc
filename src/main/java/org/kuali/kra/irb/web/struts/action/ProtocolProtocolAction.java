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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.document.Document;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.event.AddProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.rule.event.AddProtocolReferenceEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;
import org.kuali.kra.irb.service.ProtocolLocationService;
import org.kuali.kra.irb.service.ProtocolParticipantService;
import org.kuali.kra.irb.service.ProtocolReferenceService;
import org.kuali.kra.irb.service.ProtocolResearchAreaService;
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

        boolean rulePassed = true;
        // check any business rules
        ProtocolForm protocolForm = (ProtocolForm) form;
        rulePassed &= applyRules(new SaveProtocolLocationEvent(Constants.EMPTY_STRING,protocolForm.getProtocolDocument()));
        rulePassed &= applyRules(new SaveProtocolRequiredFieldsEvent(protocolForm.getProtocolDocument()));
        if (!rulePassed){
            mapping.findForward(Constants.MAPPING_BASIC);
        }
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
        if (lookupResultsBOClass.isAssignableFrom(ResearchArea.class)) {
            ProtocolResearchAreaService service = (ProtocolResearchAreaService)KraServiceLocator.getService("protocolResearchAreaService");
            service.addProtocolResearchArea(protocolDocument.getProtocol(), selectedBOs);
        }
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
     * 
     * This method adds an <code>ProtocolParticipant</code> business object to 
     * the list of <code>ProtocolParticipants</code> business objects
     * It gets called upon add action on the Participant Types sub-panel of the Protocol panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolParticipant(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolParticipant newProtocolParticipant = protocolForm.getNewProtocolParticipant();
        
        if(applyRules(new AddProtocolParticipantEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolParticipant))) {
            ProtocolParticipantService service = (ProtocolParticipantService)KraServiceLocator.getService("protocolParticipantTypeService");
            service.addProtocolParticipant(protocolForm.getProtocolDocument().getProtocol(), newProtocolParticipant);
            protocolForm.setNewProtocolParticipant(new ProtocolParticipant());
        }
              
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
  
    /**
     * 
     * This method deletes an <code>ProtocolParticipant</code> business object from 
     * the list of <code>ProtocolParticipants</code> business objects
     * It gets called upon delete action on the Participant Types sub-panel of the Protocol panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolParticipant(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
//        protocolForm.getProtocolDocument().getProtocol().getProtocolParticipants().remove(getLineToDelete(request));
//        GlobalVariables.getErrorMap().clear();
        
        ProtocolParticipantService service = (ProtocolParticipantService)KraServiceLocator.getService("protocolParticipantTypeService");
        
        service.deleteProtocolParticipant(protocolForm.getProtocolDocument().getProtocol(), getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
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
        
        if(applyRules(new AddProtocolReferenceEvent(Constants.EMPTY_STRING,protocolForm.getProtocolDocument(),newProtocolReference))) {
      
            ProtocolReferenceService service = (ProtocolReferenceService)KraServiceLocator.getService("protocolReferenceTypeService");
            
            service.addProtocolReference(protocolForm.getProtocolDocument().getProtocol(), newProtocolReference);
          
        }
          
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
    
    /**
     * This method is hook to KNS, it deletes selected ProtocolResearchArea from the UI list. Method is called in protocolAdditonalInformation.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolResearchArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        ProtocolResearchAreaService service = (ProtocolResearchAreaService)KraServiceLocator.getService("protocolResearchAreaService");
        
        service.deleteProtocolResearchArea(protocolForm.getProtocolDocument().getProtocol(), getLineToDelete(request));
   
        return mapping.findForward(Constants.MAPPING_BASIC );
    }    

    /**
     * This method is linked to ProtocolLocationService to perform the action - Add Protocol Location. 
     * Method is called in protocolLocations.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolLocation newProtocolLocation = protocolForm.getNewProtocolLocation();
        
        if(applyRules(new AddProtocolLocationEvent(Constants.EMPTY_STRING,protocolForm.getProtocolDocument(),newProtocolLocation))) {
            getProtocolLocationService().addProtocolLocation(protocolForm.getProtocolDocument().getProtocol(), newProtocolLocation);
            protocolForm.setNewProtocolLocation(new ProtocolLocation());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method is linked to ProtocolLocationService to perform the action - Delete Protocol Location. 
     * Method is called in protocolLocations.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolLocationService().deleteProtocolLocation(protocolForm.getProtocolDocument().getProtocol(), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is linked to ProtocolLocationService to perform the action - Clear Protocol Location address. 
     * Method is called in protocolLocations.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearProtocolLocationAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolLocationService().clearProtocolLocationAddress(protocolForm.getProtocolDocument().getProtocol(), getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method is to get protocol location service
     * @return ProtocolLocationService
     */
    private ProtocolLocationService getProtocolLocationService() {
        ProtocolLocationService protocolLocationService = (ProtocolLocationService)KraServiceLocator.getService("protocolLocationService");
        return protocolLocationService;
    }

}
