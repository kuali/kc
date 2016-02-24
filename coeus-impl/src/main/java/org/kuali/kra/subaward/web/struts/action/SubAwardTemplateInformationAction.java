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
package org.kuali.kra.subaward.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentDocumentStatus;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAttachments;
import org.kuali.kra.subaward.bo.SubAwardReports;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.kra.subaward.subawardrule.events.AddSubAwardAttachmentEvent;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.rice.krad.service.KualiRuleService;

public class SubAwardTemplateInformationAction extends SubAwardAction {

    private static final String EMPTY_STRING = "";
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_DELETE_ATTACHMENT_KEY = "confirmDeleteAttachmentKey";
    private static final String CONFIRM_DELETE_ATTACHMENT = "confirmDeleteAttachment";
    private static final String CONFIRM_VOID_ATTACHMENT = "confirmVoidAttachment";
    private static final String CONFIRM_VOID_ATTACHMENT_KEY = "confirmVoidAttachmentKey";
    public static final String SUB_AWARD_ATTACHMENT_ERROR_PATH = "document.subAwardList[0].subAwardAttachments[%d]";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
       ActionForward forward = super.execute(mapping, form, request, response);
         return forward;
     }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        ActionForward forward = super.save(mapping, form, request, response);
        setModifyAttachments(subAward);
        return forward;
       
    }

    protected void setModifyAttachments(SubAward subAward) {
        if (getGlobableVariableService().getMessageMap().getErrorMessages().isEmpty()) {
            subAward.getSubAwardAttachments().stream().forEach(subAwardAttachments -> subAwardAttachments.setModifyAttachment(false));
        }
    }

    @Override
    protected KualiRuleService getKualiRuleService() {
        return KcServiceLocator.getService(KualiRuleService.class);
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
        SubAwardAttachments subAwardAttachment = ((SubAwardForm)form).getSubAwardAttachmentFormBean().getNewAttachment();
        SubAwardForm subAwardForm = ((SubAwardForm)form);
         SubAwardDocument subAward = subAwardForm.getSubAwardDocument();
         if(getKualiRuleService().applyRules(new AddSubAwardAttachmentEvent(EMPTY_STRING, "", subAward, subAwardAttachment ))){
             subAwardAttachment.populateAttachment();
             ((SubAwardForm) form).getSubAwardAttachmentFormBean().addNewAwardAttachment();
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
        SubAwardForm subAwardForm = (SubAwardForm) form;
        final int selection = this.getSelectedLine(request);
        
        final SubAwardAttachments attachment = subAwardForm.getSubAwardAttachmentFormBean().retrieveExistingAttachment(selection);
        
        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        this.streamToResponse(attachment, response);
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
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        
        SubAwardAttachments attachment = subAwardDocument.getSubAward().getSubAwardAttachments().get(deleteAttachment);
        
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_ATTACHMENT_KEY,
                KeyConstants.QUESTION_DELETE_ATTACHMENT, "Subaward Attachment", attachment.getNewFile().getFileName());
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
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int delAttachment = getLineToDelete(request);
        
        subAwardDocument.getSubAward().getSubAwardAttachments().remove(delAttachment);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**.
     * This method is for replaceHistoryOfChangesAttachment
      * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward replaceHistoryOfChangesAttachment(
        ActionMapping mapping, ActionForm form, HttpServletRequest request,
               HttpServletResponse response) throws Exception {
           SubAwardForm subAwardForm = (SubAwardForm) form;
           SubAwardDocument subAwardDocument =
           subAwardForm.getSubAwardDocument();
           SubAwardAttachments subAwardAttachments =
           subAwardDocument.getSubAward().getSubAwardAttachments().get(getSelectedLine(request));
           subAwardAttachments.populateAttachment();
           if (subAwardAttachments.getSubAwardId() != null) {
               getBusinessObjectService().save(subAwardAttachments);
           }
           return mapping.findForward(MAPPING_BASIC);
       }
    /**.
     * This method is for adding reports
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
      public ActionForward addReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            SubAwardReports subAwardReports = ((SubAwardForm)form).getSubAwardAttachmentFormBean().getNewReport();
            SubAwardForm subAwardForm = ((SubAwardForm)form);
            SubAwardDocument subAward = subAwardForm.getSubAwardDocument();
             if(new SubAwardDocumentRule().
                     processsAddSubawardReportRule(subAwardReports)){
                 
                 ((SubAwardForm) form).getSubAwardAttachmentFormBean().addNewReport();
           }
            return mapping.findForward(Constants.MAPPING_BASIC);
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
      public ActionForward deleteReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
              HttpServletResponse response) throws Exception {
          SubAwardForm subAwardForm = (SubAwardForm) form;
          SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
          int selectedLineNumber = getSelectedLine(request);
          SubAwardReports subAwardReports =
          subAwardDocument.getSubAward().
          getSubAwardReportList().get(selectedLineNumber);
          subAwardDocument.getSubAward().
          getSubAwardReportList().remove(selectedLineNumber);
          return mapping.findForward(Constants.MAPPING_BASIC);
      }

    public ActionForward modifyAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        SubAwardDocument subAwardDocument = ((SubAwardForm) form).getSubAwardDocument();
        int selectedLineIndex = getSelectedLine(request);
        subAwardDocument.getSubAwardList().get(0).getSubAwardAttachments().get(selectedLineIndex).setModifyAttachment(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward voidAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        return confirm(buildVoidAttachmentConfirmationQuestion(mapping, form, request, response,
                getSelectedLine(request)), CONFIRM_VOID_ATTACHMENT, "");
    }

    public ActionForward applyModifyAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        SubAwardDocument subAwardDocument = ((SubAwardForm) form).getSubAwardDocument();
        int selectedLineIndex = getSelectedLine(request);
        SubAwardAttachments subAwardAttachments = subAwardDocument.getSubAward().getSubAwardAttachments().get(selectedLineIndex);
        boolean valid = new SubAwardDocumentRule().processApplySubawardAttachmentModificationRule(new AddSubAwardAttachmentEvent(",", String.format(SUB_AWARD_ATTACHMENT_ERROR_PATH, selectedLineIndex), subAwardDocument, subAwardAttachments));
        if (valid) {
            subAwardAttachments.setModifyAttachment(false);
            updateTimestampIfModified(subAwardAttachments);
            getBusinessObjectService().save(subAwardAttachments);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected void updateTimestampIfModified(SubAwardAttachments subAwardAttachments) {
        if (hasSubAwardAttachmentBeenModified(subAwardAttachments)) {
            subAwardAttachments.setLastUpdateTimestamp(new Timestamp(new Date().getTime()));
            subAwardAttachments.setLastUpdateUser(getGlobableVariableService().getUserSession().getPrincipalName());
        }
    }


    private boolean hasSubAwardAttachmentBeenModified(SubAwardAttachments subAwardAttachments) {
        SubAwardAttachments dbSubAwardAttachments = getBusinessObjectService().findBySinglePrimaryKey(SubAwardAttachments.class, subAwardAttachments.getAttachmentId());
        return !dbSubAwardAttachments.getSubAwardAttachmentTypeCode().equals(subAwardAttachments.getSubAwardAttachmentTypeCode()) ||
                !dbSubAwardAttachments.getDescription().equals(subAwardAttachments.getDescription());
    }

    private StrutsConfirmation buildVoidAttachmentConfirmationQuestion(ActionMapping mapping, ActionForm form,
                                                                       HttpServletRequest request, HttpServletResponse response, int voidAttachment) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_VOID_ATTACHMENT_KEY,
                KeyConstants.QUESTION_VOID_ATTACHMENT, "", "");
    }

    public ActionForward confirmVoidAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        SubAwardDocument subAwardDocument = ((SubAwardForm) form).getSubAwardDocument();
        int selectedLineIndex = getSelectedLine(request);
        subAwardDocument.getSubAward().getSubAwardAttachments().get(selectedLineIndex).setDocumentStatusCode(AttachmentDocumentStatus.VOID.getCode());
        getBusinessObjectService().save(subAwardDocument.getSubAward().getSubAwardAttachments().get(selectedLineIndex));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected GlobalVariableService getGlobableVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
    }

}
