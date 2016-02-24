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
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentDocumentStatus;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardDocumentRule;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.notesandattachments.notes.AwardNoteAddEvent;
import org.kuali.kra.award.notesandattachments.notes.AwardNoteEventBase.ErrorType;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepadBean;
import org.kuali.kra.award.rule.event.AddAwardAttachmentEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.award.service.impl.AwardCommentServiceImpl;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * This class represents the Struts Action for Notes &amp; Attachments page(AwardNotesAndAttachments.jsp)
 */
public class AwardNotesAndAttachmentsAction extends AwardAction {

    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_DELETE_ATTACHMENT = "confirmDeleteAttachment";
    private static final String CONFIRM_DELETE_ATTACHMENT_KEY = "confirmDeleteAttachmentKey";
    private static final String CONFIRM_VOID_ATTACHMENT = "confirmVoidAttachment";
    
    private static final String CONFIRM_VOID_ATTACHMENT_KEY = "confirmVoidAttachmentKey";
    private static final String EMPTY_STRING = "";
    public static final String AWARD_ATTACHMENT_PREFIX = "document.awardList[0].awardAttachments[%d]";

    private AwardNotepadBean awardNotepadBean;
    
    private AwardCommentServiceImpl awardCommentServiceImpl;
    

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
     * @param deleteAttachment
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
    
    public ActionForward modifyAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardDocument awardDocument = ((AwardForm) form).getAwardDocument();
        awardDocument.getAwardList().get(0).getAwardAttachments().get(getSelectedLine(request)).setModifyAttachment(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward voidAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return confirm(buildVoidAttachmentConfirmationQuestion(mapping, form, request, response
        ), CONFIRM_VOID_ATTACHMENT, "");
    }
    
    public ActionForward applyModifyAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardDocument awardDocument = ((AwardForm) form).getAwardDocument();
        int selectedLineIndex = getSelectedLine(request);
        AwardAttachment editAwardAttachment = awardDocument.getAward().getAwardAttachments().get(selectedLineIndex);
        if (new AwardDocumentRule().processApplyModifiedAttachmentRule(new AddAwardAttachmentEvent("",
                String.format(AWARD_ATTACHMENT_PREFIX,selectedLineIndex),awardDocument,editAwardAttachment))) {
            editAwardAttachment.setModifyAttachment(false);
            updateTimestampIfModified(editAwardAttachment);
            getBusinessObjectService().save(editAwardAttachment);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected void updateTimestampIfModified(AwardAttachment editAwardAttachment) {
        if (hasAwardAttachmentBeenModified(editAwardAttachment)) {
            editAwardAttachment.setLastUpdateTimestamp(new Timestamp(new Date().getTime()));
            editAwardAttachment.setLastUpdateUser(getGlobalVariableService().getUserSession().getPrincipalName());
        }
    }

    private boolean hasAwardAttachmentBeenModified(AwardAttachment awardAttachment) {
       AwardAttachment dbAwardAttachment =  getBusinessObjectService().findBySinglePrimaryKey(AwardAttachment.class, awardAttachment.getAwardAttachmentId());
        return !dbAwardAttachment.getTypeCode().equals(awardAttachment.getTypeCode()) ||
                !dbAwardAttachment.getDescription().equals(awardAttachment.getDescription());
    }

    private StrutsConfirmation buildVoidAttachmentConfirmationQuestion(ActionMapping mapping, ActionForm form,
                                                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
       return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_VOID_ATTACHMENT_KEY,
               KeyConstants.QUESTION_VOID_ATTACHMENT,"","");
   }
    
    public ActionForward confirmVoidAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardDocument awardDocument = ((AwardForm) form).getAwardDocument();
        int selectedLineIndex = getSelectedLine(request);
        awardDocument.getAward().getAwardAttachments().get(selectedLineIndex).setDocumentStatusCode(AttachmentDocumentStatus.VOID.getCode());
    	getBusinessObjectService().save(awardDocument.getAward().getAwardAttachments().get(selectedLineIndex));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected GlobalVariableService getGlobalVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
    }
}
