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
package org.kuali.kra.iacuc.noteattachment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

public class IacucProtocolNoteAndAttachmentAction extends IacucProtocolAction {

    private static final String CONFIRM_NO_DELETE = "";
    private static final String NOT_FOUND_SELECTION = "the attachment was not found for selection ";
    private static final String INVALID_ATTACHMENT = "this attachment version is invalid ";    

    private static final Log LOG = LogFactory.getLog(IacucProtocolNoteAndAttachmentAction.class);
    
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
            //may want to tell the user the selection was invalid.
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
     * @param attachmentType the attachment type.
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
}
