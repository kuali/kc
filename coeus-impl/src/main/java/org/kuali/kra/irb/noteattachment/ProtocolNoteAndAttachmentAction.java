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
package org.kuali.kra.irb.noteattachment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.watermark.WatermarkConstants;
import org.kuali.coeus.common.framework.print.watermark.WatermarkService;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.print.ProtocolPrintingService;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolNotepadBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents the Struts Action for Notes &amp; Attachments page(ProtocolNoteAndAttachment.jsp).
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
        return this.viewAttachment(mapping, (ProtocolForm) form, request, response);
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
        org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase attachment = ((ProtocolForm) form).getNotesAttachmentsHelper().retrieveExistingAttachmentByType(
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
    private boolean isValidContactData(org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase attachment, String errorPath) {
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(errorPath);
        getDictionaryValidationService().validateBusinessObject(attachment);
        errorMap.removeFromErrorPath(errorPath);
        return errorMap.hasNoErrors();
    
    }

    protected DictionaryValidationService getDictionaryValidationService() {
            return KNSServiceLocator.getKNSDictionaryValidationService();
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
            HttpServletResponse response, Class<? extends org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        final org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase attachment = form.getNotesAttachmentsHelper().retrieveExistingAttachmentByType(selection, attachmentType);
       
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);

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
            HttpServletResponse response, Class<? extends org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        
        if (!form.getNotesAttachmentsHelper().deleteExistingAttachmentByType(selection, attachmentType)) {
            LOG.info(NOT_FOUND_SELECTION + selection);
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
     * @return an action forward.
     * @throws IllegalArgumentException if the attachmentType is not supported
     * @throws Exception if there is a problem executing the request.
     */
    private ActionForward viewAttachment(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        ProtocolAttachmentProtocol attachment = (ProtocolAttachmentProtocol) CollectionUtils.getFromList(selection, form.getProtocolDocument().getProtocol().getAttachmentProtocols());
               
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);

            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final AttachmentFile file = attachment.getFile();
        byte[] attachmentFile =null;
        String attachmentFileType=file.getType().replace("\"", "");
        attachmentFileType=attachmentFileType.replace("\\", "");
        if(attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
            attachmentFile=getProtocolAttachmentFile(form,attachment);
            if(attachmentFile!=null) {
                this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
            }
            else {
                this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    
            }
            return RESPONSE_ALREADY_HANDLED;
        }        
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        
        return RESPONSE_ALREADY_HANDLED;
    }
    
    /**
     * 
     * This method for set the attachment with the watermark which selected  by the client .
     */
    private byte[] getProtocolAttachmentFile(ProtocolForm form, ProtocolAttachmentProtocol attachment){
        
        byte[] attachmentFile =null;
        final AttachmentFile file = attachment.getFile();
        Printable printableArtifacts= getProtocolPrintingService().getProtocolPrintArtifacts(form.getProtocolDocument().getProtocol());
        Protocol protocolCurrent = (Protocol) form.getProtocolDocument().getProtocol();
        int currentProtoSeqNumber= protocolCurrent.getSequenceNumber();
        try {
            if(printableArtifacts.isWatermarkEnabled()){
                int currentAttachmentSequence=attachment.getSequenceNumber();
                String docStatusCode=attachment.getDocumentStatusCode();
                String statusCode=attachment.getStatusCode();
                // TODO perhaps the check for equality of protocol and attachment sequence numbers, below, is now redundant
                if(((getProtocolAttachmentService().isAttachmentActive(attachment))&&(currentProtoSeqNumber == currentAttachmentSequence))||(docStatusCode.equals("1"))){
                    if (ProtocolAttachmentProtocol.COMPLETE_STATUS_CODE.equals(statusCode)) {
                        attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                    }
                }else{
                    attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getInvalidWatermark());
                    LOG.info(INVALID_ATTACHMENT + attachment.getDocumentId());
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
     * Methods called when adding/editing/deleting notes.
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
    
    public ActionForward editNote(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            final int selection = this.getSelectedLine(request);
            ((ProtocolForm) form).getNotesAttachmentsHelper().modifyNote(selection);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

    public ActionForward deleteNote(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return confirmDeleteNote(mapping, (ProtocolForm) form, request, response);
    }

    public ActionForward deleteNoteConfirmed(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        
        if (!((ProtocolForm)form).getNotesAttachmentsHelper().deleteNote(selection)) {
            LOG.info(NOT_FOUND_SELECTION + selection);

        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Finds the attachment selected the by client which is really just an index.
     * Then deletes the selected attachment based on the passed-in attachmentType.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws IllegalArgumentException if the attachmentType is not supported
     * @throws Exception if there is a problem executing the request.
     */
    private ActionForward confirmDeleteNote(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        final String confirmMethod = "deleteNoteConfirmed";
        final StrutsConfirmation confirm = buildParameterizedConfirmationQuestion(mapping, form, request, response, confirmMethod, 
                                                                                  KeyConstants.QUESTION_DELETE_NOTE_CONFIRMATION);
        return confirm(confirm, confirmMethod, CONFIRM_NO_DELETE);
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
        for (ProtocolPersonBase person : ((ProtocolForm) form).getProtocolDocument().getProtocol().getProtocolPersons()) {
            person.refreshReferenceObject("attachmentPersonnels");
        }
        for (ProtocolAttachmentProtocolBase attachment : ((ProtocolForm) form).getProtocolDocument().getProtocol().getAttachmentProtocols()) {
            // for some reason, change and save, this list is not updated under attachment.protocol.attachmentprotocols
            attachment.getProtocol().refreshReferenceObject("attachmentProtocols");
        }
        // don't allow edit of saved notes
        for (ProtocolNotepadBase notepad : ((ProtocolForm) form).getProtocolDocument().getProtocol().getNotepads()) {
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
        return KcServiceLocator.getService(ProtocolPrintingService.class);
    }
    
    /**
     * 
     * This method is to get Protocol Attachment Service.
     */    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        return KcServiceLocator.getService(ProtocolAttachmentService.class);
    }
    
    /**
     * This method is to get Watermark Service. 
     */
    private WatermarkService getWatermarkService() {
        return  KcServiceLocator.getService(WatermarkService.class);
    }
}
