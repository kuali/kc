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
package org.kuali.kra.irb.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.bo.ProtocolAttachmentBase;
import org.kuali.kra.irb.bo.ProtocolAttachmentFile;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;

/**
 * This class represents the Struts Action for Notes & Attachments page(ProtocolNotesAndAttachments.jsp).
 */
public class ProtocolNotesAndAttachmentsAction extends ProtocolAction {    
    
    private static final Log LOG = LogFactory.getLog(ProtocolNotesAndAttachmentsAction.class);
    
    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    
    public ActionForward addAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getNotesAndAttachmentsHelper().addNewProtocolAttachmentProtocol();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
            
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getNotesAndAttachmentsHelper().addNewProtocolAttachmentPersonnel();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        final int selection = this.getSelectedLine(request);
        ProtocolAttachmentBase attachment = protocolForm.getNotesAndAttachmentsHelper().retrieveExistingAttachmentProtocol(selection);
        
        if (attachment == null) {
            LOG.info("the attachment was not found for selection " + selection);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final ProtocolAttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), file.getName(), file.getType(), response);
        
        return RESPONSE_ALREADY_HANDLED;
    }
    
    public ActionForward deleteAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        final int selection = this.getSelectedLine(request);
        boolean deleted = protocolForm.getNotesAndAttachmentsHelper().deleteExistingAttachmentProtocol(selection);
        
        if (!deleted) {
            LOG.info("the attachment was not deleted for selection " + selection);
            //may want to tell the user the selection was invalid.
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}
