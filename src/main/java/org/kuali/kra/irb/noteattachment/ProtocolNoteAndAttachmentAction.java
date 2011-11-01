/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.noteattachment;

import java.util.List;

import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.print.ProtocolPrintingService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.util.watermark.WatermarkConstants;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;

/**
 * This class represents the Struts Action for Notes & Attachments page(ProtocolNoteAndAttachment.jsp).
 */
public class ProtocolNoteAndAttachmentAction extends ProtocolAction {    

    private static final String CONFIRM_NO_DELETE = "";
    private static final String NOT_FOUND_SELECTION = "the attachment was not found for selection ";
    private static final String INVALID_ATTACHMENT = "this attachment version is invalid ";
    
    
    private static final Log LOG = LogFactory.getLog(ProtocolNoteAndAttachmentAction.class);
    
    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String ATTACHMNENT_PATH = "document.protocolList[0].attachmentProtocols[";
    
    /** 
     * prepares the view.
     * {@inheritDoc}
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        ((ProtocolForm) form).getNotesAttachmentsHelper().prepareView();
        return forward;
    }
    
    /**
     * This method will execute logic related to capturing new attachments, versioning, etc.
     * {@inheritDoc}
     */
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((ProtocolForm) form).getNotesAttachmentsHelper().processSave();
        ((ProtocolForm) form).getNotesAttachmentsHelper().fixReloadedAttachments(request.getParameterMap());
        /*
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement().toString();
            //refreshButtonClicked${itrStatus.index}
            String fieldNameStarter = "protocolRefreshButtonClicked";
            if (name.indexOf(fieldNameStarter) > -1){
                //we have a refresh button checker field
                String fieldValue = request.getParameterValues(name)[0];
                if("T".equals(fieldValue)) {
                    //a refresh button has been clicked, now we just need to update the appropriate attachment status code
                    int numericVal = Integer.valueOf(name.substring(fieldNameStarter.length()));
                    ((ProtocolForm) form).getProtocolDocument().getProtocol().getAttachmentProtocols().get(numericVal).setDocumentStatusCode("2");
                }
            }
        }*/
    }
    /**
     * Method called when adding an attachment protocol.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ((ProtocolForm) form).getNotesAttachmentsHelper().addNewProtocolAttachmentProtocol();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Method called when viewing an attachment protocol.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward viewAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.viewAttachment(mapping, (ProtocolForm) form, request, response, ProtocolAttachmentProtocol.class);
    }
    
    /**
     * Method called when deleting an attachment protocol.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward deleteAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int selection = this.getSelectedLine(request);
        ProtocolAttachmentBase attachment = ((ProtocolForm) form).getNotesAttachmentsHelper().retrieveExistingAttachmentByType(
                selection, ProtocolAttachmentProtocol.class);
        if (isValidContactData(attachment, ATTACHMNENT_PATH + selection + "]")) {
            return confirmDeleteAttachment(mapping, (ProtocolForm) form, request, response, ProtocolAttachmentProtocol.class);
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    /*
     * add this check, so to prevent the situation that data is not editable after deletion.
     */
    private boolean isValidContactData(ProtocolAttachmentBase attachment, String errorPath) {
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(errorPath);
        getDictionaryValidationService().validateBusinessObject(attachment);
        errorMap.removeFromErrorPath(errorPath);
        return errorMap.hasNoErrors();
    
    }

    protected DictionaryValidationService getDictionaryValidationService() {
            return KNSServiceLocator.getDictionaryValidationService();
    }

    /**
     * Method called when confirming the deletion an attachment protocol.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward confirmDeleteAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.deleteAttachment(mapping, (ProtocolForm) form, request, response, ProtocolAttachmentProtocol.class);
    }
    
    /**
     * Method called when deleting an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward deleteAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return confirmDeleteAttachment(mapping, (ProtocolForm) form, request, response, ProtocolAttachmentPersonnel.class);
    }
    
    /**
     * Finds the attachment selected the by client which is really just an index.
     * Then deletes the selected attachment based on the passed-in attachmentType.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @param attachmentType the attachment type.
     * @return an action forward.
     * @throws IllegalArgumentException if the attachmentType is not supported
     * @throws Exception if there is a problem executing the request.
     */
    private ActionForward confirmDeleteAttachment(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
            HttpServletResponse response, Class<? extends ProtocolAttachmentBase> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        final ProtocolAttachmentBase attachment = form.getNotesAttachmentsHelper().retrieveExistingAttachmentByType(selection, attachmentType);
       
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final String confirmMethod = form.getNotesAttachmentsHelper().retrieveConfirmMethodByType(attachmentType);
        final StrutsConfirmation confirm 
        = buildParameterizedConfirmationQuestion(mapping, form, request, response, confirmMethod, 
                KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION, attachment.getAttachmentDescription(), attachment.getFile().getName());
        
        return confirm(confirm, confirmMethod, CONFIRM_NO_DELETE);
    }
    
    /**
     * Finds the attachment selected the by client which is really just an index.
     * Then deletes the selected attachment based on the passed-in attachmentType.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @param attachmentType the attachment type.
     * @return an action forward.
     * @throws IllegalArgumentException if the attachmentType is not supported
     * @throws Exception if there is a problem executing the request.
     */
    private ActionForward deleteAttachment(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
            HttpServletResponse response, Class<? extends ProtocolAttachmentBase> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        
        if (!form.getNotesAttachmentsHelper().deleteExistingAttachmentByType(selection, attachmentType)) {
            LOG.info(NOT_FOUND_SELECTION + selection);
            //may want to tell the user the selection was invalid.
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Finds the attachment selected the by client which is really just an index.
     * Then writes the selected attachment to the response based on the passed-in attachmentType.
     * If the attachment is found this method returns {@code null} otherwise it returns a forward for
     * {@link Constants#MAPPING_BASIC MAPPING_BASIC}
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @param attachmentType the attachment type.
     * @return an action forward.
     * @throws IllegalArgumentException if the attachmentType is not supported
     * @throws Exception if there is a problem executing the request.
     */
    private ActionForward viewAttachment(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
            HttpServletResponse response, Class<? extends ProtocolAttachmentBase> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        final ProtocolAttachmentBase attachment = form.getNotesAttachmentsHelper().retrieveExistingAttachmentByType(selection, attachmentType);
               
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final AttachmentFile file = attachment.getFile();
        byte[] attachmentFile =null;
        String attachmentFileType=file.getType().replace("\"", "");
        if(attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
            attachmentFile=getProtocolAttachmentFile(form,attachment);
            if(attachmentFile!=null){
                 this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);}
            return RESPONSE_ALREADY_HANDLED;
        }        
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        
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
                    for (ProtocolAttachmentProtocol protocolAttachment : protocolAttachmentList) {
                        if(attachmentDocumentId.equals(protocolAttachment.getDocumentId())){
                            if(getProtocolAttachmentService().isNewAttachmentVersion(protocolAttachment)){
                                attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                            }else{
                                attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getInvalidWatermark());
                                LOG.info(INVALID_ATTACHMENT + attachmentDocumentId);
                            }
                        }
                    }
                }else{
                    attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                }
            }
        }
        catch (Exception e) {
            LOG.error("Exception Occured in ProtocolNoteAndAttachmentAction. : ",e);    
        }        
        return attachmentFile;
    }
    
    
    /**
     * Quotes a string that follows RFC 822 and is valid to include in an http header.
     * 
     * <p>
     * This really should be a part of {@link org.kuali.rice.kns.util.WebUtils WebUtils}.
     * <p>
     * 
     * For example: without this method, file names with spaces will not show up to the client correctly.
     * 
     * <p>
     * This method is not doing a Base64 encode just a quoted printable character otherwise we would have
     * to set the encoding type on the header.
     * <p>
     * 
     * @param s the original string
     * @return the modified header string
     */
    protected static String getValidHeaderString(String s) {
        return MimeUtility.quote(s, HeaderTokenizer.MIME);
    }
    
    /**
     * Method called when adding a protocol note.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ((ProtocolForm) form).getNotesAttachmentsHelper().addNewNote();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * attachmentPersonnels is updated thru 'protocol'.  so use this to sync attachmentpersonnels under protocolperson
     * @see org.kuali.kra.irb.ProtocolAction#postSave(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.postSave(mapping, form, request, response);
        if (!((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().isEmpty()) {
            getBusinessObjectService().delete(((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete());
            ((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().clear();
            }
        for (ProtocolPerson person : ((ProtocolForm) form).getProtocolDocument().getProtocol().getProtocolPersons()) {
            person.refreshReferenceObject("attachmentPersonnels");
        }
        for (ProtocolAttachmentProtocol attachment : ((ProtocolForm) form).getProtocolDocument().getProtocol().getAttachmentProtocols()) {
            // for some reason, change and save, this list is not updated under attachment.protocol.attachmentprotocols
            attachment.getProtocol().refreshReferenceObject("attachmentProtocols");
        }
        // don't allow edit of saved notes
        for (ProtocolNotepad notepad : ((ProtocolForm) form).getProtocolDocument().getProtocol().getNotepads()) {
            notepad.setEditable(false);
        }
    }

    /**
     * Method called when updating the attachment filter
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward updateAttachmentFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        
        ((ProtocolForm) form).getNotesAttachmentsHelper().addNewProtocolAttachmentFilter();

        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    
    /**
     * This method is to get protocol printing service.
     * 
     */
    private ProtocolPrintingService getProtocolPrintingService() {
        return KraServiceLocator.getService(ProtocolPrintingService.class);
    }
    
    /**
     * 
     * This method is to get Protocol Attachment Service.
     */    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        return KraServiceLocator.getService(ProtocolAttachmentService.class);
    }
    
    /**
     * This method is to get Watermark Service. 
     */
    private WatermarkService getWatermarkService() {
        return  KraServiceLocator.getService(WatermarkService.class);  
    }
}
