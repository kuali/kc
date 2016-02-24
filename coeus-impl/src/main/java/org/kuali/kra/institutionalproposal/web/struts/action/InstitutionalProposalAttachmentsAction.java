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
package org.kuali.kra.institutionalproposal.web.struts.action;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentDocumentStatus;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;

import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.attachments.InstitutionalProposalAttachment;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddAttachmentRuleEvent;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddAttachmentRuleImpl;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;


public class InstitutionalProposalAttachmentsAction extends InstitutionalProposalAction {

    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_DELETE_ATTACHMENT = "confirmDeleteAttachment";
    private static final String CONFIRM_VOID_ATTACHMENT = "confirmVoidAttachment";
    private static final String CONFIRM_DELETE_ATTACHMENT_KEY = "confirmDeleteAttachmentKey";
    private static final String CONFIRM_VOID_ATTACHMENT_KEY = "confirmVoidAttachmentKey";
    public static final String INSTITUTIONAL_PROPOSAL = "institutionalProposal";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);

        return forward;
    }

    public ActionForward addAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm instProposalForm = ((InstitutionalProposalForm)form);
        InstitutionalProposalAttachment instProposalAttachment = instProposalForm.getInstitutionalProposalAttachmentBean().getNewAttachment();
        InstitutionalProposalDocument instProposal = instProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposalAddAttachmentRuleEvent event = new InstitutionalProposalAddAttachmentRuleEvent(INSTITUTIONAL_PROPOSAL, instProposal, instProposalAttachment);
        if(new InstitutionalProposalAddAttachmentRuleImpl().processAddInstitutionalProposalAttachmentBusinessRules(event)){
            instProposalForm.getInstitutionalProposalAttachmentBean().addNewInstitutionalProposalAttachment();
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward viewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm InstitutionalProposalForm = (InstitutionalProposalForm) form;
        final int selection = this.getSelectedLine(request);
        final InstitutionalProposalAttachment attachment = InstitutionalProposalForm.getInstitutionalProposalAttachmentBean().retrieveExistingAttachment(selection);

        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        this.streamToResponse(attachment.getData(), getValidHeaderString(attachment.getFileName()), getValidHeaderString(attachment.getContentType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }

    public ActionForward modifyAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm InstitutionalProposalForm = (InstitutionalProposalForm) form;
        final int selection = this.getSelectedLine(request);
        InstitutionalProposalDocument instProposalDocument=(InstitutionalProposalDocument) InstitutionalProposalForm.getDocument();
        instProposalDocument.getInstitutionalProposalList().get(0).getInstProposalAttachments().get(selection).setModifyAttachment(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward applyModifyAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        int selectedLineIndex = getSelectedLine(request);
        InstitutionalProposalAttachment instProposalAttachment = ((InstitutionalProposalForm) form).getInstitutionalProposalAttachmentBean().getNewAttachment();
    	String errorPath = INSTITUTIONAL_PROPOSAL;
        InstitutionalProposalAddAttachmentRuleEvent event = new InstitutionalProposalAddAttachmentRuleEvent(errorPath, institutionalProposalDocument, instProposalAttachment);
        if(new InstitutionalProposalAddAttachmentRuleImpl().processSaveInstitutionalProposalAttachment(event, selectedLineIndex)){
            InstitutionalProposalAttachment editAttachment = institutionalProposalDocument.getInstitutionalProposal().getInstProposalAttachments().get(selectedLineIndex);
            editAttachment.setModifyAttachment(false);
            if (hasAttachmentBeenModified(editAttachment)) {
                editAttachment.setLastUpdateTimestamp(new Timestamp(new Date().getTime()));
                editAttachment.setLastUpdateUser(KcServiceLocator.getService(GlobalVariableService.class).getUserSession().getPrincipalName());
            }
        	getBusinessObjectService().save(editAttachment);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private boolean hasAttachmentBeenModified(InstitutionalProposalAttachment attachment) {
        InstitutionalProposalAttachment dbAttachment = getBusinessObjectService().findBySinglePrimaryKey(InstitutionalProposalAttachment.class,attachment.getProposalAttachmentId());
        return !dbAttachment.getFileDataId().equals(attachment.getFileDataId()) ||
                !dbAttachment.getAttachmentTypeCode().equals(attachment.getAttachmentTypeCode()) ||
                !dbAttachment.getComments().equals(attachment.getComments()) ||
                !dbAttachment.getAttachmentTitle().equals(attachment.getAttachmentTitle());
    }

    public ActionForward voidAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return confirm(buildVoidAttachmentConfirmationQuestion(mapping, form, request, response), CONFIRM_VOID_ATTACHMENT, "");
   }

    public ActionForward deleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int delAttachment = getLineToDelete(request);

        return confirm(buildDeleteAttachmentConfirmationQuestion(mapping, form, request, response,
                delAttachment), CONFIRM_DELETE_ATTACHMENT, "");
    }

   private StrutsConfirmation buildDeleteAttachmentConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, int deleteAttachment) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposalAttachment attachment = institutionalProposalDocument.getInstitutionalProposal().getInstProposalAttachments().get(deleteAttachment);

        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_ATTACHMENT_KEY,
                KeyConstants.QUESTION_DELETE_ATTACHMENT, "Institutional Proposal Attachment", attachment.getAttachmentTitle());
    }

   private StrutsConfirmation buildVoidAttachmentConfirmationQuestion(ActionMapping mapping, ActionForm form,
                                                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
       return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_VOID_ATTACHMENT_KEY,
               KeyConstants.QUESTION_VOID_ATTACHMENT,"","");
   }

   public ActionForward confirmDeleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
           HttpServletResponse response) throws Exception {
	   InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
       InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
       int delAttachment = getLineToDelete(request);
       getBusinessObjectService().delete(institutionalProposalDocument.getInstitutionalProposal().getInstProposalAttachments().get(delAttachment));
       institutionalProposalDocument.getInstitutionalProposal().getInstProposalAttachments().remove(delAttachment);
       return mapping.findForward(Constants.MAPPING_BASIC);
   }

   public ActionForward confirmVoidAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
           HttpServletResponse response) throws Exception {
	   InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
       InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
       int selectedLineIndex = getSelectedLine(request);
       institutionalProposalDocument.getInstitutionalProposal().getInstProposalAttachments().get(selectedLineIndex).setDocumentStatusCode(AttachmentDocumentStatus.VOID.getCode());
       getBusinessObjectService().save(institutionalProposalDocument.getInstitutionalProposal().getInstProposalAttachments().get(selectedLineIndex));
       return mapping.findForward(Constants.MAPPING_BASIC);
   }
}
