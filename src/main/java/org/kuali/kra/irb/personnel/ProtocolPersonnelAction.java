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
package org.kuali.kra.irb.personnel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.print.ProtocolPrintingService;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.util.watermark.WatermarkConstants;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The ProtocolPersonnelAction corresponds to the Personnel tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class ProtocolPersonnelAction extends ProtocolAction {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolPersonnelAction.class);
    
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL = "confirmDeleteAttachmentPersonnel";
    private static final String CONFIRM_NO_DELETE = "";
    private static final String INVALID_ATTACHMENT = "this attachment version is invalid ";


    private ProtocolAttachmentService protocolAttachmentService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        getProtocolPersonnelService().selectProtocolUnit(getProtocolPersons(form));
        ((ProtocolForm)form).getPersonnelHelper().prepareView();
        
        return actionForward;
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Add Protocol Person. 
     * Method is called in protocolAddPersonnelSection.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolPerson newProtocolPerson = protocolForm.getPersonnelHelper().getNewProtocolPerson();
        Protocol protocol = protocolForm.getDocument().getProtocol();

        // check any business rules
        boolean rulePassed = applyRules(new AddProtocolPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getDocument(),
            newProtocolPerson));
        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPerson(protocol, newProtocolPerson);
            protocolForm.getPersonnelHelper().setNewProtocolPerson(new ProtocolPerson());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Delete Protocol Person.
     * Method is called in ProtocolPersonnel.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        getProtocolPersonnelService().deleteProtocolPerson(protocolDocument.getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is to clear existing protocol person.
     * Method is called in protocolAddPersonnelSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getPersonnelHelper().setNewProtocolPerson(new ProtocolPerson());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * Method called when adding an attachment to a person.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        ProtocolAttachmentPersonnel newAttachmentPersonnel = protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().get(selectedPersonIndex);
        newAttachmentPersonnel.setPersonId(protocolPerson.getProtocolPersonId());
        newAttachmentPersonnel.setProtocolNumber(protocolPerson.getProtocolNumber());
        
        boolean rulePassed =  applyRules(new AddProtocolAttachmentPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), 
                newAttachmentPersonnel, selectedPersonIndex));

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonAttachment(protocolDocument.getProtocol(), newAttachmentPersonnel, selectedPersonIndex);
            protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().set(selectedPersonIndex, new ProtocolAttachmentPersonnel());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Method called when viewing an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward viewPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentBase attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));
        return printAttachmentProtocol(mapping, protocolForm, response, attachment);
    }

    /*
     * This is to view attachment if attachment is selected in print panel.
     */
    private ActionForward printAttachmentProtocol(ActionMapping mapping, ProtocolForm form, HttpServletResponse response, ProtocolAttachmentBase attachment) throws Exception {

        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final AttachmentFile file = attachment.getFile();
        byte[] attachmentFile =null;
        String attachmentFileType=file.getType().replace("\"", "");
        if(attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
            attachmentFile=getProtocolAttachmentFile(form,attachment);
            if(attachmentFile!=null){          
                this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    }
            else{
                this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    }
            return RESPONSE_ALREADY_HANDLED;
        }
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }
    /**
     * 
     * This method for set the attachment with the watermark which selected  by the client .
     * @param protocolForm form
     * @param protocolAttachmentBase attachment
     * @return attachment file
     */
      
    private byte[] getProtocolAttachmentFile(ProtocolForm form,ProtocolAttachmentBase attachment){
        
        byte[] attachmentFile =null;
        final AttachmentFile file = attachment.getFile();
        Printable printableArtifacts= getProtocolPrintingService().getProtocolPrintArtifacts(form.getProtocolDocument().getProtocol());
        
        try {
            if(printableArtifacts.isWatermarkEnabled()){
            Integer attachmentDocumentId =attachment.getDocumentId();
            List<ProtocolAttachmentProtocol> protocolAttachmentList=form.getDocument().getProtocol().getAttachmentProtocols();
            if(protocolAttachmentList.size()>0){
                for (ProtocolAttachmentProtocol personnelAttachment : protocolAttachmentList) {
                    if(attachmentDocumentId.equals(personnelAttachment.getDocumentId())){
                        if(getProtocolAttachmentService().isNewAttachmentVersion(personnelAttachment)){
                            attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                        }else{
                            attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getInvalidWatermark());
                            LOG.info(INVALID_ATTACHMENT + attachmentDocumentId);
                        }
                    }
                }
            }else{
                attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark()); }
            }
        }
        catch (Exception e) {
            LOG.error("Exception Occured in ProtocolNoteAndAttachmentAction. : ",e);    
        }        
        return attachmentFile;
    }
    /**
     * Method called when deleting an attachment from a person.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deletePersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolDocument protocolDocument = ((ProtocolForm) form).getDocument();
        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentPersonnel attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));

        final StrutsConfirmation confirm 
        = buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL, 
                KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION, attachment.getAttachmentDescription(), attachment.getFile().getName());
        
        return confirm(confirm, CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL, CONFIRM_NO_DELETE);
    }
    
    /**
     * Method called when confirming the deletion an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward confirmDeleteAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolDocument protocolDocument = ((ProtocolForm) form).getDocument();
        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentPersonnel attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));

        if (attachment.getFileId() != null && !getProtocolAttachmentService().isSharedFile(attachment)) {
            ((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().add(attachment.getFile());
        }
        protocolPerson.getAttachmentPersonnels().remove(getSelectedLine(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    


    /**
     * This method is linked to ProtocolPersonnelService to perform the action
     * Add ProtocolUnit to Person.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);

        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        
        ProtocolUnit newProtocolPersonUnit = protocolForm.getPersonnelHelper().getNewProtocolPersonUnits().get(selectedPersonIndex);
        boolean rulePassed = applyRules(new AddProtocolUnitEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), 
                newProtocolPersonUnit, selectedPersonIndex));

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonUnit(protocolForm.getPersonnelHelper().getNewProtocolPersonUnits(), protocolPerson, selectedPersonIndex);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is linked to ProtocolPersonnelService to perform the action.
     * Delete ProtocolUnit from Person unit list.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        getProtocolPersonnelService().deleteProtocolPersonUnit(protocolDocument.getProtocol(), selectedPersonIndex, getSelectedLine(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is to update protocol person details view based on modified person role. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateProtocolPersonView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        getProtocolPersonnelService().switchInvestigatorCoInvestigatorRole(protocolDocument.getProtocol().getProtocolPersons());
        getProtocolPersonnelService().syncPersonRoleAndUnit(protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex));
        getProtocolPersonnelService().syncPersonRoleAndAffiliation(protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService)KraServiceLocator.getService("protocolPersonnelService");
    }

    /**
     * This method for Watermark Service. 
     */
    private WatermarkService getWatermarkService() {
        return  KraServiceLocator.getService(WatermarkService.class);  
    }
    
    /**
     * 
     * This method for get Protocol Service.
     * @return
     */
    private ProtocolPrintingService getProtocolPrintingService() {
        return KraServiceLocator.getService(ProtocolPrintingService.class);
    }
    /**
     * This method is to get selected person index.
     * Each person data is displayed in individual panel.
     * Person index is required to identify the person to perform an action.
     * @param request
     * @param document
     * @return
     */
    protected int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocument document) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(StringUtils.substringBetween(parameterName, "protocolPersons[", "]."));
        }
        return selectedPersonIndex;
    }
    
    /**
     * This method is to get list of protocol persons
     * @param form
     * @return
     */
    private List<ProtocolPerson> getProtocolPersons(ActionForm form) {
        return ((ProtocolForm) form).getDocument().getProtocol().getProtocolPersons();
    }

    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.preSave(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getDocument().getProtocol();
        
        for (ProtocolPerson protocolPerson : protocol.getProtocolPersons()) {
            if (protocolPerson.isPrincipalInvestigator() && !protocolPerson.getPersonId().equals(protocol.getPrincipalInvestigatorId())) {
                // reset PI from cached getter
                protocol.setPrincipalInvestigatorId(null);

                if (protocolPerson.getPersonId() != null) {
                    // Assign the PI the AGGREGATOR role.
                    KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
                    kraAuthService.addRole(protocolPerson.getPersonId(), RoleConstants.PROTOCOL_AGGREGATOR, protocol);
                    kraAuthService.addRole(protocolPerson.getPersonId(), RoleConstants.PROTOCOL_APPROVER, protocol);
                    protocolForm.getPermissionsHelper().resetUserStates();
                }
            }
            else if (!protocolPerson.isPrincipalInvestigator() &&
                    (!StringUtils.equals(protocolPerson.getPersonId(),protocol.getPrincipalInvestigatorId()))) {
               
                if (protocolPerson.getPersonId() != null) {
                    // Assign the Other Role To Viewer the AGGREGATOR role.
                    KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
                    kraAuthService.addRole(protocolPerson.getPersonId(), RoleConstants.PROTOCOL_VIEWER, protocol);
                    protocolForm.getPermissionsHelper().resetUserStates();
                }
            }
        }

        Map keyMap = new HashMap();
        keyMap.put("protocolNumber", protocol.getProtocolNumber());
        keyMap.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<ProtocolAttachmentPersonnel> attachments = (List<ProtocolAttachmentPersonnel>)getBusinessObjectService().findMatching(ProtocolAttachmentPersonnel.class, keyMap);
        List<AttachmentFile> filesToDelete = new ArrayList<AttachmentFile>();
        List<Long> attachmentIds = new ArrayList<Long>();
        for (ProtocolAttachmentPersonnel attachment : protocol.getAttachmentPersonnels()) {
            if (attachment.getId() != null) {
                attachmentIds.add(attachment.getId());
            }
        }
        for (ProtocolAttachmentPersonnel attachment : attachments) {
            if (!attachmentIds.contains(attachment.getId()) && !getProtocolAttachmentService().isSharedFile(attachment)) {
                filesToDelete.add(attachment.getFile());
            }
        }
        protocolForm.getNotesAttachmentsHelper().setFilesToDelete(filesToDelete);
    }

    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        if (protocolAttachmentService == null) {
            protocolAttachmentService = KraServiceLocator.getService(ProtocolAttachmentService.class);
        }
        return protocolAttachmentService;
    }
    
    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        super.postSave(mapping, form, request, response);
        if (!((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().isEmpty()) {
            getBusinessObjectService().delete(((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete());
            ((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().clear();
        }
    }
}
