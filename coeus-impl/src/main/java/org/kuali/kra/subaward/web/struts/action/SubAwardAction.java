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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.validation.AuditHelper.ValidationState;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAttachmentType;
import org.kuali.kra.subaward.bo.SubAwardAttachments;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.customdata.SubAwardCustomData;
import org.kuali.kra.subaward.bo.SubAwardTemplateInfo;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.notification.SubAwardNotificationContext;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.kra.subaward.reporting.printing.service.SubAwardPrintingService;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.kra.subaward.templateAttachments.SubAwardAttachmentFormBean;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is ActionClass for SubAward...
 */
public class SubAwardAction extends KcTransactionalDocumentActionBase {

    public static final String DISABLE_ATTACHMENT_REMOVAL = "disableAttachmentRemoval";
    private transient SubAwardService subAwardService;
    private static final Log LOG = LogFactory.getLog(SubAwardAction.class);
    private static final String SUBAWARD_AGREEMENT = "fdpAgreement";
    private static final String DOCUMENT_ROUTE_QUESTION="DocRoute";

    /**
     * @see org.kuali.kra.web.struts.action.
     * KraTransactionalDocumentActionBase#execute(
     * org.apache.struts.action.ActionMapping,
     * org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     *  javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    	HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;
        if (subAwardForm.getSubAward() != null) {
            SubAward subAward = 
                KcServiceLocator.getService(SubAwardService.class).getAmountInfo(subAwardForm.getSubAward());
        }
        
        ActionForward actionForward = super.
        execute(mapping, form, request, response);
        if (GlobalVariables.getAuditErrorMap().isEmpty()) {
            KcServiceLocator.getService(AuditHelper.class).auditConditionally((SubAwardForm) form);
        }

        if(subAwardForm.getSubAwardDocument().getSubAwardList() != null) {
            for(SubAward subAwardList:subAwardForm.getSubAwardDocument().getSubAwardList()) {
                List<SubAwardAttachments> subAwardAttachmentsList = subAwardList.getSubAwardAttachments();
                if(subAwardAttachmentsList != null && !subAwardAttachmentsList.isEmpty()) {
                     for(SubAwardAttachments subAwardAttachments:subAwardAttachmentsList) {
                            if(subAwardAttachments.getFileName() != null) {
                                String printAttachmentTypeInclusion=KcServiceLocator.getService(ParameterService.class).getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_PRINT_ATTACHMENT_TYPE_INCLUSION);
                                String[] attachmentTypeCode=printAttachmentTypeInclusion.split("\\,");
                                for (int typeCode = 0; typeCode < attachmentTypeCode.length; typeCode++) {
                                    if (StringUtils.equals(subAwardAttachments.getSubAwardAttachmentTypeCode(),attachmentTypeCode[typeCode])) {
                                        String[] fileNameSplit = subAwardAttachments.getFileName().toString().split("\\.pdf");
                                        SubAwardPrintingService printService = KcServiceLocator.getService(SubAwardPrintingService.class);
                                        if (printService.isPdf(subAwardAttachments.getAttachmentContent())) {
                                            subAwardAttachments.setFileNameSplit(fileNameSplit[0]);
                                        }
                                    }
                                }
                             }
                            SubAwardAttachmentType subAwardAttachmentTypeValue =  KcServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(SubAwardAttachmentType.class, subAwardAttachments.getSubAwardAttachmentTypeCode());
                            subAwardAttachments.setTypeAttachment(subAwardAttachmentTypeValue);
                     }
                }
            }
        }

        return actionForward;
    }
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(
     * org.apache.struts.action.ActionMapping,
     *  org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     *  javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        ActionForward forward;
        forward = handleDocument(
        mapping, form, request, response, subAwardForm);
        SubAwardDocument subAwardDocument =
        (SubAwardDocument) subAwardForm.getDocument();
        subAwardForm.initializeFormOrDocumentBasedOnCommand();
        SubAward subAward = KcServiceLocator.getService(
                SubAwardService.class).getAmountInfo(subAwardDocument.getSubAward());
        subAwardForm.getSubAwardDocument().setSubAward(subAward);



        return forward;
    }

    /**.
     * this method is for handleDocument
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @param subAwardForm the SubAwardForm
     * @return ActionForward
     * @throws Exception
     */
    ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, SubAwardForm subAwardForm) throws Exception {

        ActionForward forward = null;

        String command = subAwardForm.getCommand();
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            loadDocumentInForm(request, subAwardForm);
        } else if (Constants.MAPPING_SUBAWARD_ACTION_PAGE.equals(command)) {
            loadDocumentInForm(request, subAwardForm);
            forward = subAwardActions(mapping, subAwardForm, request, response);
        } else if (Constants.MAPPING_FINANCIAL_PAGE.equals(command)) {
            loadDocumentInForm(request, subAwardForm);
            forward = mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }
        return forward;
    }
    /**.
    *
    * loadDocumentInForm
     * @param request the Request
     * @param subAwardForm the SubAwardForm
    * @throws WorkflowException
    *
    */
    protected void loadDocumentInForm(HttpServletRequest request,
    SubAwardForm subAwardForm)
    throws WorkflowException {
        String docIdRequestParameter =
        request.getParameter(KRADConstants.PARAMETER_DOC_ID);
        SubAwardDocument retrievedDocument = (SubAwardDocument)
        KRADServiceLocatorWeb.getDocumentService().
        getByDocumentHeaderId(docIdRequestParameter);
        subAwardForm.setDocument(retrievedDocument);
        request.setAttribute(KRADConstants.PARAMETER_DOC_ID,
                docIdRequestParameter);
    }

    /**.
    *
    * This method builds the string for the ActionForward
    * @param forwardPath
    * @param docIdRequestParameter
    * @return String
    */
   public String buildForwardStringForActionListCommand(String forwardPath,
		 String docIdRequestParameter) {
       StringBuilder sb = new StringBuilder();
       sb.append(forwardPath);
       sb.append("?");
       sb.append(KRADConstants.PARAMETER_DOC_ID);
       sb.append("=");
       sb.append(docIdRequestParameter);
       return sb.toString();
   }

    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#loadDocument(
     * org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    protected void loadDocument(KualiDocumentFormBase kualiForm)
    throws WorkflowException {
        super.loadDocument(kualiForm);
        SubAward subAward = ((SubAwardForm) kualiForm).
        getSubAwardDocument().getSubAward();
    }

    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase
     * #save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;

        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        checkSubAwardCode(subAward);
        checkSubAwardTemplateCode(subAward);
        String userId = GlobalVariables.getUserSession().getPrincipalName();
        if (subAward.getSubAwardId() == null) {
            getVersionHistoryService().updateVersionHistory(subAward, VersionStatus.PENDING, userId);
        }
        if (new SubAwardDocumentRule().processAddSubAwardBusinessRules(subAward) && new SubAwardDocumentRule().processAddSubAwardTemplateInfoBusinessRules(subAward)) {
            ActionForward forward = super.save(mapping, form, request, response);
            getSubAwardService().updateSubAwardSequenceStatus(subAward, VersionStatus.PENDING);
            return forward;
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }

    /**
    *
    * This method gets called upon navigation to subaward tab.
   * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
    * @return ActionForward
    */
   public ActionForward subAward(ActionMapping mapping,
 ActionForm form, HttpServletRequest request, HttpServletResponse response) {

       return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
   }

    /**
     *
     * This method gets called upon navigation to fundingSource tab.
    * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return
     */
    public ActionForward fundingSource(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) {

        return mapping.findForward(Constants.MAPPING_FUNDING_SOURCE_PAGE);
    }

    /**
    *
    * This method gets called upon navigation to amountInfo tab.
 * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
    * @return ActionForward
    */
   public ActionForward financial(ActionMapping mapping, ActionForm form,
		  HttpServletRequest request, HttpServletResponse response) {

       return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
   }
   /**
   *
   * This method gets called upon navigation to template information tab.
* @param mapping the ActionMapping
    * @param form the ActionForm
    * @param request the Request
    * @param response the Response
   * @return ActionForward
   */
   public ActionForward templateInformation(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) {
        setDisableRemovalAttachmentIndicator(((SubAwardForm) form).getSubAwardAttachmentFormBean());
        return mapping.findForward(Constants.MAPPING_TEMPLATE_PAGE);
    }


    protected void setDisableRemovalAttachmentIndicator(SubAwardAttachmentFormBean subAwardAttachmentForm) {
        if (subAwardAttachmentForm != null) {
            subAwardAttachmentForm.setDisableAttachmentRemovalIndicator(getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                    ParameterConstants.DOCUMENT_COMPONENT, DISABLE_ATTACHMENT_REMOVAL));
        }
    }
   /**
   *
   * This method gets called upon navigation to amountReleased tab.
  * @param mapping the ActionMapping
  * @param form the ActionForm
  * @param request the Request
  * @param response the Response
   * @return ActionForward
   */
  public ActionForward amountReleased(
		ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) {

      return mapping.findForward(Constants.MAPPING_AMOUNT_RELEASED_PAGE);
  }
  
  /**
  *
  * This method gets called upon navigation to Contacts tab.
   * @param mapping the ActionMapping
  * @param form the ActionForm
  * @param request the Request
  * @param response the Response
   * @return ActionForward
  */
 public ActionForward contacts(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) {

     return mapping.findForward(Constants.MAPPING_CONTACTS_PAGE);
 }

 /**.
 *
 * This method gets called upon navigation to closeouts tab.
   * @param mapping the ActionMapping
  * @param form the ActionForm
  * @param request the Request
  * @param response the Response
   * @return ActionForward
 */
public ActionForward closeouts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

    return mapping.findForward(Constants.MAPPING_CLOSEOUT_PAGE);
}
/**
*
* This method gets called upon navigation to custom data tab.
  * @param mapping the ActionMapping
  * @param form the ActionForm
  * @param request the Request
  * @param response the Response
   * @return ActionForward
*/

public ActionForward customData(ActionMapping mapping, ActionForm form
        , HttpServletRequest request, HttpServletResponse response) {
    SubAwardForm subAwardForm = (SubAwardForm) form;
    subAwardForm.getCustomDataHelper().prepareCustomData();
    return mapping.findForward(Constants.MAPPING_CUSTOM_DATA);
}

/**
*
* This method gets called upon navigation to subaward action tab.
  * @param mapping the ActionMapping
  * @param form the ActionForm
  * @param request the Request
  * @param response the Response
   * @return ActionForward
*/

public ActionForward subAwardActions(ActionMapping mapping,
ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    return mapping.findForward(Constants.MAPPING_SUBAWARD_ACTION_PAGE);
}

/**
 * @return
 */
protected VersionHistoryService getVersionHistoryService() {
    return KcServiceLocator.getService(VersionHistoryService.class);
}

/**.
*
* This method gets called upon getting SubAwardService
  * @param
  * @return subAwardService
*/
public SubAwardService getSubAwardService() {
    if (subAwardService == null) {
        subAwardService = KcServiceLocator.getService(SubAwardService.class);
    }
    return subAwardService;
}
/**.
 * This method sets the subAwardService
 * @return subAwardService
 * @param subAward
 */
public void setSubAwardService(SubAwardService subAwardService) {
    this.subAwardService = subAwardService;
}
/**
 * This method sets an subAwardCode on an subAward if
 * the subAwardCode hasn't been initialized yet.
 * @param subAward
 */
protected void checkSubAwardCode(SubAward subAward){
    if (subAward.getSubAwardCode() == null) {
        String subAwardCode = getSubAwardService().getNextSubAwardCode();
        subAward.setSubAwardCode(subAwardCode);
    }
    for (SubAwardCustomData customData : subAward.getSubAwardCustomDataList()) {
        customData.setSubAward(subAward);
    }
}

protected void checkSubAwardTemplateCode(SubAward subAward){
    for (SubAwardTemplateInfo subAwardTemplateInfo : subAward.getSubAwardTemplateInfo()) {
        if (subAward.getSubAwardCode()!=null && subAwardTemplateInfo.getSubAwardCode() == null) { 
            String subAwardCode = subAward.getSubAwardCode();
            Integer subAwardSequenceNumber = subAward.getSequenceNumber();
            subAwardTemplateInfo.setSubAwardCode(subAwardCode);
            subAwardTemplateInfo.setSequenceNumber(subAwardSequenceNumber);
        }
    }
}

@Override
public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    SubAwardForm subAwardForm = (SubAwardForm) form;
    subAwardForm.setAuditActivated(false);
    ValidationState status = KcServiceLocator.getService(AuditHelper.class).isValidSubmission(subAwardForm, true);
    Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
    Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
    String methodToCall = ((KualiForm) form).getMethodToCall();
    
    if (status == ValidationState.OK) {
        sendNotification(mapping, subAwardForm, SubAward.NOTIFICATION_TYPE_SUBMIT, "Submit SubAward");
        return super.route(mapping, form, request, response);
    } else {
        if (status == ValidationState.WARNING) {
            if(question == null){
                return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION, "Validation Warning Exists. Are you sure want to submit to workflow routing.", KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
            } else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                sendNotification(mapping, subAwardForm, SubAward.NOTIFICATION_TYPE_SUBMIT, "Submit SubAward");
                return super.route(mapping, form, request, response);
            } else {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }    
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().
            putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION, new String[] {});
            subAwardForm.setAuditActivated(true);   
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
}

@Override
public ActionForward blanketApprove(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    SubAwardForm subAwardForm = (SubAwardForm) form;

    subAwardForm.setAuditActivated(false);
    ValidationState status = KcServiceLocator.getService(AuditHelper.class).
    isValidSubmission(subAwardForm, true);
    if ((status == ValidationState.OK) || (status == ValidationState.WARNING)) {
        sendNotification(mapping, subAwardForm, SubAward.NOTIFICATION_TYPE_SUBMIT, "Submit SubAward");
        return super.blanketApprove(mapping, form, request, response);
    } else {
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().
        putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION, new String[]{});
        subAwardForm.setAuditActivated(true);
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
}

  @Override
  public ActionForward approve(ActionMapping mapping, ActionForm form,
   HttpServletRequest request,
   HttpServletResponse response) throws Exception {
      SubAwardForm subAwardForm = (SubAwardForm) form;
      ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
      ValidationState status = KcServiceLocator.getService(AuditHelper.class).
      isValidSubmission(subAwardForm, true);

      if ((status == ValidationState.OK) || (status == ValidationState.WARNING)) {
          return forward = super.approve(mapping, form, request, response);
      } else {
          GlobalVariables.getMessageMap().clearErrorMessages();
          GlobalVariables.getMessageMap().
          putError("datavalidation", KeyConstants.
          ERROR_WORKFLOW_SUBMISSION,  new String[] {});

          return forward;
      }
  }


  /**
   * This method is for medusa
   * This method is for @throws Exception...
   * @param mapping the ActionMapping
   * @param form the ActionForm
   * @param request the Request
   * @param response the Response
   * @return ActionForward
   * @throws Exception
   */
  public ActionForward medusa(ActionMapping mapping,
          ActionForm form, HttpServletRequest request,
          HttpServletResponse response) throws Exception {
      SubAwardForm subAwardForm = (SubAwardForm) form;
      if (subAwardForm.getDocument().getDocumentNumber() == null) {
          loadDocumentInForm(request, subAwardForm);
      }
      subAwardForm.getMedusaBean().setMedusaViewRadio("0");
      subAwardForm.getMedusaBean().setModuleName("subaward");
      subAwardForm.getMedusaBean().setModuleIdentifier(subAwardForm.getSubAwardDocument().getSubAward().getSubAwardId());
      subAwardForm.getMedusaBean().generateParentNodes();
      return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);
  }

  /**
   * Use the Kuali Rule Service to apply the rules for the given event.
   * @param event the event to process
   * @return true if success; false if there was a validation error
   */
  protected final boolean applyRules(DocumentEvent event) {
      return KcServiceLocator.getService(KualiRuleService.class).applyRules(event);
  }

  public ActionForward sendNotification(ActionMapping mapping, SubAwardForm subAwardForm, 
                                        String notificationType, String notificationString) {
      SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
      SubAwardNotificationContext context = new SubAwardNotificationContext(subAward, notificationType, notificationString, Constants.MAPPING_SUBAWARD_PAGE);
      if (subAwardForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
          subAwardForm.getNotificationHelper().initializeDefaultValues(context);
          return mapping.findForward("notificationEditor");
      } else {
          getNotificationService().sendNotification(context);
          return mapping.findForward(Constants.MAPPING_BASIC);
      }
  }

  protected KcNotificationService getNotificationService() {
      return KcServiceLocator.getService(KcNotificationService.class);
  }
  /**
   * 
   * Handy method to stream the byte array to response object
   * @param attachmentDataSource
   * @param response
   * @throws Exception
   */

 
  /**
   * 
   * This method is called to print forms
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
 public ActionForward printForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
         Map<String, Object> reportParameters = new HashMap<String, Object>();          
         SubAwardForm subAwardForm = (SubAwardForm) form;
         List<SubAwardForms> printFormTemplates = new ArrayList<SubAwardForms>();
         List<SubAwardForms> subAwardFormList = subAwardForm.getSubAwardDocument().getSubAwardList().get(0).getSubAwardForms();
         SubAwardPrintingService printService = KcServiceLocator.getService(SubAwardPrintingService.class);
         printFormTemplates = printService.getSponsorFormTemplates(subAwardForm.getSubAwardPrintAgreement(),subAwardFormList);
              Collection<SubAwardFundingSource> fundingSource = (Collection<SubAwardFundingSource>) KcServiceLocator
                      .getService(BusinessObjectService.class).findAll(SubAwardFundingSource.class);
              if(subAwardForm.getSubAwardPrintAgreement().getFundingSource() != null){
                  for (SubAwardFundingSource subAwardFunding : fundingSource) {
                      if(subAwardForm.getSubAwardPrintAgreement().getFundingSource().equals(subAwardFunding.getSubAwardFundingSourceId().toString())){
                          reportParameters.put("awardNumber",subAwardFunding.getAward().getAwardNumber());
                          reportParameters.put("awardTitle",subAwardFunding.getAward().getParentTitle());
                          reportParameters.put("sponsorAwardNumber",subAwardFunding.getAward().getSponsorAwardNumber());
                          reportParameters.put("sponsorName",subAwardFunding.getAward().getSponsor().getSponsorName());
                          reportParameters.put("cfdaNumber",subAwardFunding.getAward().getCfdaNumber());
                          reportParameters.put("awardID",subAwardFunding.getAward().getAwardId());
                      }
                  }
              }
              SubAwardPrintingService subAwardPrintingService = KcServiceLocator.getService(SubAwardPrintingService.class);
              AttachmentDataSource dataStream ;
              reportParameters.put(SubAwardPrintingService.SELECTED_TEMPLATES, printFormTemplates);
              reportParameters.put("fdpType",subAwardForm.getSubAwardPrintAgreement().getFdpType());
              if(subAwardForm.getSubAwardPrintAgreement().getFdpType().equals(SUBAWARD_AGREEMENT)){
                  dataStream = subAwardPrintingService.printSubAwardFDPReport(subAwardForm.getSubAwardDocument().getSubAward(), SubAwardPrintType.SUB_AWARD_FDP_TEMPLATE, reportParameters);
              } else{
                  dataStream = subAwardPrintingService.printSubAwardFDPReport(subAwardForm.getSubAwardDocument().getSubAward(), SubAwardPrintType.SUB_AWARD_FDP_MODIFICATION, reportParameters);
              }                                           
              streamToResponse(dataStream,response);
      
      return  mapping.findForward(Constants.MAPPING_BASIC);
  }
}
