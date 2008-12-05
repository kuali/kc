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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceKeyConstants;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.document.Document;
import org.kuali.core.lookup.LookupResultsService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.action.KualiDocumentActionBase;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kra.bo.ResearchAreas;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.bo.ProtocolResearchAreas;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.util.RiceConstants;

import edu.iu.uis.eden.clientapp.IDocHandler;

public class ProtocolAction extends KraTransactionalDocumentActionBase {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolAction.class);
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        //Save the list for later use
        List<ProtocolResearchAreas> protocolResearchAreas = ((ProtocolForm)form).getProtocolDocument().getProtocol().getProtocolResearchAreas();
        //Reset list with no values
        ((ProtocolForm)form).getProtocolDocument().getProtocol().setProtocolResearchAreas(new ArrayList<ProtocolResearchAreas>());
        
        //Save only Protocol
        ActionForward af = super.save(mapping, form, request, response);
        
        if(null != protocolResearchAreas && !protocolResearchAreas.isEmpty()) {
            //Set values of 2 fields now
            for(ProtocolResearchAreas protocolResearchArea: protocolResearchAreas) {
                protocolResearchArea.setSequenceNumber(((ProtocolForm)form).getProtocolDocument().getProtocol().getSequenceNumber());
                protocolResearchArea.setProtocolNumber(((ProtocolForm)form).getProtocolDocument().getProtocol().getProtocolNumber());
            }
            //Set list back to document form
            ((ProtocolForm)form).getProtocolDocument().getProtocol().setProtocolResearchAreas(protocolResearchAreas);
            //RE-Save with Additional Information.
            af = super.save(mapping, form, request, response);
        }
        
        return af;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        // setup any Protocol System Parameters that will be needed
        KualiConfigurationService configService = getService(KualiConfigurationService.class);
        ((ProtocolForm)form).getProtocolParameters().put(Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID1, configService.getParameter(Constants.PARAMETER_MODULE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID1));
        ((ProtocolForm)form).getProtocolParameters().put(Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID2, configService.getParameter(Constants.PARAMETER_MODULE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID2));

        
        return actionForward;
    }


    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
                     
        // KNS UI hook for lookup resultset, check to see if we are coming back from a lookup
        if (Constants.MULTIPLE_VALUE.equals(protocolForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = protocolForm.getLookupResultsSequenceNumber();
            
            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                
                Class lookupResultsBOClass = Class.forName(protocolForm.getLookupResultsBOClassName());
                String tmp = GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier();
                LookupResultsService service = KNSServiceLocator.getLookupResultsService();
                Collection<PersistableBusinessObject> rawValues = service.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,tmp);
                
                if (lookupResultsBOClass.isAssignableFrom(ResearchAreas.class)) {
                    for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                        //New ResearchAreas added by user selection
                        ResearchAreas newResearchAreas = (ResearchAreas) iter.next();
                        // ignore / drop duplicates
                        if (!isDuplicateProtocolResearchAreas(newResearchAreas, protocolDocument.getProtocol().getProtocolResearchAreas())) {
                            //Add new ProtocolResearchAreas to list
                            protocolDocument.getProtocol().addProtocolResearchAreas(createInstanceOfProtocolResearchAreas(protocolDocument, newResearchAreas));
                        }
                    }//End of for
                }//End of if
            }
        }
        
        return mapping.findForward("basic");
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
        
        if(null != protocolDocument.getProtocol().getSequenceNumber())
            protocolResearchAreas.setSequenceNumber(protocolDocument.getProtocol().getSequenceNumber());
        
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
    private boolean isDuplicateProtocolResearchAreas(ResearchAreas newResearchAreas, List<ProtocolResearchAreas> protocolResearchAreas) {
        for (ProtocolResearchAreas pra  : protocolResearchAreas) {    
            if (pra.getResearchAreas().equals(newResearchAreas)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.headerTab(mapping, form, request, response);
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

        return mapping.findForward("basic");
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

        return mapping.findForward("basic");
    }
    
    public ActionForward addProtocolParticipant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolParticipant newProtocolParticipant = protocolForm.getNewProtocolParticipant();
        if(getKualiRuleService().applyRules(new AddProtocolParticipantEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolParticipant))) {
            protocolForm.getProtocolDocument().getProtocol().getProtocolParticipants().add(newProtocolParticipant);
            protocolForm.setNewProtocolParticipant(new ProtocolParticipant());
        }
        return mapping.findForward("basic");
    }

//    public ActionForward deleteProtocolParticipant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        protocolForm.getProposalDevelopmentDocument().getPropSpecialReviews().remove(getLineToDelete(request));
//        proposalDevelopmentForm.getDocumentExemptNumbers().remove(getLineToDelete(request));
//        GlobalVariables.getErrorMap().clear();
//
//        return mapping.findForward("basic");
//    }

    // TODO : move this method up?
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
}
