/*
 * Copyright 2006-2008 The Kuali Foundation
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.web.struts.action.ProtocolAction;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;

/**
 * This class represents the Struts Action for Notes & Attachments page(ProtocolNoteAndAttachment.jsp).
 */
public class ProtocolNoteAndAttachmentAction extends ProtocolAction {    
    
    private static final String NOT_FOUND_SELECTION = "the attachment was not found for selection ";
    private static final String UNSUPPORTED_ATTACHMENT_TYPE = "unsupported attachment type ";
    
    private static final Log LOG = LogFactory.getLog(ProtocolNoteAndAttachmentAction.class);
    
    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    
    /** 
     * Refreshes the document.
     * {@inheritDoc}
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        //FIXME: may want to only refresh the items that can change...
        ((ProtocolForm) form).getProtocolDocument().refresh();
        
        return forward;
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
        ((ProtocolForm) form).getNotesAndAttachmentsHelper().addNewProtocolAttachmentProtocol();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Method called when adding an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ((ProtocolForm) form).getNotesAndAttachmentsHelper().addNewProtocolAttachmentPersonnel();
        
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
     * Method called when viewing an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward viewAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {  
        return this.viewAttachment(mapping, (ProtocolForm) form, request, response, ProtocolAttachmentPersonnel.class);
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
        return this.deleteAttachment(mapping, (ProtocolForm) form, request, response, ProtocolAttachmentPersonnel.class);
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
        final boolean deleted;
        
        if (ProtocolAttachmentProtocol.class.equals(attachmentType)) {
            deleted = form.getNotesAndAttachmentsHelper().deleteExistingAttachmentProtocol(selection);
        } else if (ProtocolAttachmentPersonnel.class.equals(attachmentType)) {
            deleted = form.getNotesAndAttachmentsHelper().deleteExistingAttachmentPersonnel(selection);
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_ATTACHMENT_TYPE + attachmentType);
        }
        
        if (!deleted) {
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
        
        final ProtocolAttachmentBase attachment;
        
        if (ProtocolAttachmentProtocol.class.equals(attachmentType)) {
            attachment = form.getNotesAndAttachmentsHelper().retrieveExistingAttachmentProtocol(selection); 
        } else if (ProtocolAttachmentPersonnel.class.equals(attachmentType)) {
            attachment = form.getNotesAndAttachmentsHelper().retrieveExistingAttachmentPersonnel(selection); 
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_ATTACHMENT_TYPE + attachmentType);
        }
        
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final ProtocolAttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), file.getName(), file.getType(), response);
        
        return RESPONSE_ALREADY_HANDLED;
    }
}
