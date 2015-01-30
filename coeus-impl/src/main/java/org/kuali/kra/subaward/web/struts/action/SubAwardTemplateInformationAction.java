/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
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
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
       ActionForward forward = super.execute(mapping, form, request, response);
         return forward;
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
           subAwardAttachments.getFile().setName(subAwardAttachments.getFileName());
           if (subAwardAttachments.getSubAwardId() != null) {
               getBusinessObjectService().save(subAwardAttachments);
               subAwardAttachments.getFile().setName(subAwardAttachments.getNewFile().getFileName());
               subAwardAttachments.getFile().setData(subAwardAttachments.getNewFile().getFileData());
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

}
