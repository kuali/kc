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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.notesandattachments.notes.AwardNoteAddEvent;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepadBean;
import org.kuali.kra.award.notesandattachments.notes.AwardNoteEventBase.ErrorType;
import org.kuali.kra.award.rule.event.AddAwardAttachmentEvent;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.service.impl.AwardCommentServiceImpl;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This class represents the Struts Action for Notes & Attachments page(AwardNotesAndAttachments.jsp)
 */
public class AwardNotesAndAttachmentsAction extends AwardAction {    
   
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_DELETE_ATTACHMENT = "confirmDeleteAttachment";
    private static final String CONFIRM_DELETE_ATTACHMENT_KEY = "confirmDeleteAttachmentKey";
    private static final String EMPTY_STRING = "";
    
    private AwardNotepadBean awardNotepadBean;
    
    private AwardCommentServiceImpl awardCommentServiceImpl;
    
    /**
     * Constructs a InstitutionalProposalHomeAction.java.
     */
    public AwardNotesAndAttachmentsAction() {
        awardNotepadBean = new AwardNotepadBean();
        awardCommentServiceImpl = new AwardCommentServiceImpl();
    }
    
    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("all")
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        
        ActionForward actionForward = super.reload(mapping, form, request, response);
        
        awardForm.getAwardCommentBean().setAwardCommentScreenDisplayTypesOnForm();
        
        return actionForward;        
    }
    
    /**
     * This method is used to add a new Award Notes
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (applyRules(new AwardNoteAddEvent(Constants.EMPTY_STRING, ((AwardForm) form).getDocument(), ((AwardForm) form)
                .getAwardNotepadBean().getNewAwardNotepad(), ErrorType.HARDERROR))) {
            awardNotepadBean.addNote(((AwardForm) form).getAwardNotepadBean());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to update notedPad values
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward updateNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Method called when adding an attachment.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        AwardAttachment awardAttachment = ((AwardForm) form).getAwardAttachmentFormBean().getNewAttachment();
        AwardForm awardForm = ((AwardForm)form);
        AwardDocument award = awardForm.getAwardDocument();
        if(getKualiRuleService().applyRules(new AddAwardAttachmentEvent(EMPTY_STRING, "", award, awardAttachment ))){
            ((AwardForm) form).getAwardAttachmentFormBean().addNewAwardAttachment();
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Method called when viewing an attachment.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward viewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {  
        AwardForm awardForm = (AwardForm) form;
        final int selection = this.getSelectedLine(request);
        final AwardAttachment attachment = awardForm.getAwardAttachmentFormBean().retrieveExistingAttachment(selection);
        
        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        
        return RESPONSE_ALREADY_HANDLED;
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
    public ActionForward deleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int delAttachment = getLineToDelete(request);
        
        return confirm(buildDeleteAttachmentConfirmationQuestion(mapping, form, request, response,
                delAttachment), CONFIRM_DELETE_ATTACHMENT, "");
        //awardDocument.getAward().getAwardAttachments().remove(delAttachment);
    }
    
    /**
     * 
     * This method is to build the confirmation question for deleting Attachments.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteAttachmentConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, int deleteAttachment) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        AwardAttachment attachment = awardDocument.getAward().getAwardAttachments().get(deleteAttachment);
        
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_ATTACHMENT_KEY,
                KeyConstants.QUESTION_DELETE_ATTACHMENT, "Award Attachment", attachment.getFile().getName());
    }
    
    /**
     * This method is used to delete an Award Attachment
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward confirmDeleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int delAttachment = getLineToDelete(request);
        
        awardDocument.getAward().getAwardAttachments().remove(delAttachment);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is for the 'view history' button. It will be forwarded AwardCommentHistory.jsp
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewAwardCommentHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        String awardCommentTypeCode = request.getParameter("awardCommentTypeCode");
        String awardId = request.getParameter("awardId");
        awardForm.setAwardCommentHistoryByType(awardCommentServiceImpl.retrieveCommentHistoryByType(awardCommentTypeCode, awardId));
        return mapping.findForward("basic");
    }  
    
    private ActionForward redirectAwardCommentHistoryForPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String awardCommentTypeCode, String awardId) throws Exception {
        response.sendRedirect("awardCommentViewHistory.do?methodToCall=viewAwardCommentHistory&awardCommentTypeCode=" + awardCommentTypeCode +
                "&awardId=" + awardId);
      
        return null;
    }
   
    /**
     * Gets the awardCommentServiceImpl attribute. 
     * @return Returns the awardCommentServiceImpl.
     */
    public AwardCommentServiceImpl getAwardCommentServiceImpl() {
        return awardCommentServiceImpl;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        String command = request.getParameter("command");
        String awardCommentTypeCode = request.getParameter("awardCommentTypeCode");
        String awardId = request.getParameter("awardId");
        if (StringUtils.isNotBlank(command) && "redirectAwardCommentHistoryForPopup".equals(command)) {
            forward = redirectAwardCommentHistoryForPopup(mapping, form, request, response, awardCommentTypeCode, awardId);
        }
        return forward;
    }

    public ActionForward syncComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        //find specified comment
        String awardCommentIndex = null;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            awardCommentIndex = StringUtils.substringBetween(parameterName, ".awardCommentIdx", ".");
        }
        //AwardComment comment = awardForm.getAwardDocument().getAward().getAwardCommentByType(awardCommentTypeCode, false, false);
        AwardComment comment = awardForm.getAwardDocument().getAward().getAwardComment(Integer.parseInt(awardCommentIndex));
        getAwardSyncCreationService().addAwardSyncChange(awardForm.getAwardDocument().getAward(), 
                new AwardSyncPendingChangeBean(AwardSyncType.ADD_SYNC, comment, "awardComments"));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }   
}
