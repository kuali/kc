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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.rule.event.AddProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.rule.event.AddProtocolReferenceEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;
import org.kuali.kra.irb.service.ProtocolFundingSourceService;
import org.kuali.kra.irb.service.ProtocolLocationService;
import org.kuali.kra.irb.service.ProtocolParticipantService;
import org.kuali.kra.irb.service.ProtocolProtocolService;
import org.kuali.kra.irb.service.ProtocolReferenceService;
import org.kuali.kra.irb.service.ProtocolResearchAreaService;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The ProtocolProtocolAction corresponds to the Protocol tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class ProtocolProtocolAction extends ProtocolAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolProtocolAction.class);
    
    /**
     * @see org.kuali.kra.irb.web.struts.action.ProtocolAction#isValidSave(org.kuali.kra.irb.web.struts.form.ProtocolForm)
     */
    @Override
    protected boolean isValidSave(ProtocolForm protocolForm) {    
        boolean rulePassed = true;
        protocolForm.getProtocolHelper().prepareRequiredFieldsForSave();
        rulePassed &= applyRules(new SaveProtocolLocationEvent(Constants.EMPTY_STRING,protocolForm.getProtocolDocument()));
        rulePassed &= applyRules(new SaveProtocolRequiredFieldsEvent(protocolForm.getProtocolDocument()));
        return rulePassed;
    }

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        // Following is for protocol lookup - edit protocol 
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate") && StringUtils.isNotBlank(request.getParameter(Constants.PROPERTY_PROTOCOL_NUMBER))) {
            getProtocolProtocolService().loadProtocolForEdit(((ProtocolForm)form).getProtocolDocument(), request.getParameter(Constants.PROPERTY_PROTOCOL_NUMBER));
        }
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        ((ProtocolForm)form).getPersonnelHelper().prepareView();
        ((ProtocolForm)form).getPermissionsHelper().prepareView();
        ((ProtocolForm)form).getCustomDataHelper().prepareView();
        
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
        ProtocolParticipant newProtocolParticipant = protocolForm.getParticipantsHelper().getNewProtocolParticipant();
        
        if(applyRules(new AddProtocolParticipantEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolParticipant))) {
            getProtocolParticipantService().addProtocolParticipant(protocolForm.getProtocolDocument().getProtocol(), newProtocolParticipant);
            protocolForm.getParticipantsHelper().setNewProtocolParticipant(new ProtocolParticipant());
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
        protocolForm.getProtocolDocument().getProtocol().getProtocolParticipants().remove(getLineToDelete(request));        

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
      
            ProtocolReferenceService service = KraServiceLocator.getService(ProtocolReferenceService.class);
            
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
        
        protocolForm.getProtocolDocument().getProtocol().getProtocolReferences().remove(getLineToDelete(request));  
   
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
        
        protocolForm.getProtocolDocument().getProtocol().getProtocolResearchAreas().remove(getLineToDelete(request));
   
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
        ProtocolLocation newProtocolLocation = protocolForm.getProtocolHelper().getNewProtocolLocation();
        
        if(applyRules(new AddProtocolLocationEvent(Constants.EMPTY_STRING,protocolForm.getProtocolDocument(),newProtocolLocation))) {
            getProtocolLocationService().addProtocolLocation(protocolForm.getProtocolDocument().getProtocol(), newProtocolLocation);
            protocolForm.getProtocolHelper().setNewProtocolLocation(new ProtocolLocation());
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
        protocolForm.getProtocolDocument().getProtocol().getProtocolLocations().remove(getLineToDelete(request));
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
     * This method is to get protocol participant service
     * @return ProtocolPersonnelService
     */
    private ProtocolParticipantService getProtocolParticipantService() {
        return(ProtocolParticipantService)KraServiceLocator.getService("protocolParticipantTypeService");
    }

    /**
     * This method is to get protocol location service
     * @return ProtocolLocationService
     */
    private ProtocolLocationService getProtocolLocationService() {
        return (ProtocolLocationService)KraServiceLocator.getService("protocolLocationService");
    }

    
    /**
     * This method is linked to ProtocolFundingService to perform the action - Add Protocol Funding Source. 
     * Method is called in protocolFundingSources.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolFundingSource(ActionMapping mapping, 
                                                  ActionForm form, 
                                                  HttpServletRequest request, 
                                                  HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        AddProtocolFundingSourceEvent event = 
            new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING,protocolForm.getProtocolDocument());
        
        if(applyRules(event)) {
            ((ProtocolForm)form).getProtocolHelper().syncFundingSources(protocolForm.getProtocolDocument().getProtocol());
            getProtocolFundingSourceService().addProtocolFundingSource(protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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
    public ActionForward deleteProtocolFundingSource(ActionMapping mapping, 
                                                     ActionForm form, 
                                                     HttpServletRequest request, 
                                                     HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolFundingSourceService().deleteProtocolFundingSource(protocolForm.getProtocolDocument().getProtocol(), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is to get protocol location service
     * @return ProtocolLocationService
     */
    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        
        ProtocolFundingSourceService protocolFundingSourceService = 
            (ProtocolFundingSourceService) KraServiceLocator.getService("protocolFundingSourceService");
        
        return protocolFundingSourceService;
    }
    
    private ProtocolProtocolService getProtocolProtocolService() {
        
        return (ProtocolProtocolService) KraServiceLocator.getService("protocolProtocolService");
        
    }
 
    
}
