/*.
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.subaward.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.customdata.SubAwardCustomData;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.notification.SubAwardNotificationContext;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.AuditActionHelper.ValidationState;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * This class is ActionClass for SubAward...
 */
public class SubAwardAction extends KraTransactionalDocumentActionBase{

    private transient SubAwardService subAwardService;

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
                KraServiceLocator.getService(SubAwardService.class).getAmountInfo(subAwardForm.getSubAward());
        }
        
        ActionForward actionForward = super.
        execute(mapping, form, request, response);
        if (KNSGlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((SubAwardForm) form);
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
        SubAward subAward = KraServiceLocator.getService(
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
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#loadDocument(
     * org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    protected void loadDocument(KualiDocumentFormBase kualiForm)
    throws WorkflowException {
        super.loadDocument(kualiForm);
        SubAward subAward = ((SubAwardForm) kualiForm).
        getSubAwardDocument().getSubAward();
    }

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase
     * #save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;

        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        checkSubAwardCode(subAward);
        String userId = GlobalVariables.getUserSession().getPrincipalName();
        if (subAward.getSubAwardId() == null) {
            getVersionHistoryService().updateVersionHistory(subAward, VersionStatus.PENDING, userId);
        }
        if (new SubAwardDocumentRule().processAddSubAwardBusinessRules(subAward)) {
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
    return KraServiceLocator.getService(VersionHistoryService.class);
}

/**
 *
 * This method...
 * @return
 */
protected KeywordsService getKeywordService() {
    return KraServiceLocator.getService(KeywordsService.class);
}

/**.
*
* This method gets called upon getting SubAwardService
  * @param
  * @return subAwardService
*/
public SubAwardService getSubAwardService() {
    if (subAwardService == null) {
        subAwardService = KraServiceLocator.getService(SubAwardService.class);
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

@Override
public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    SubAwardForm subAwardForm = (SubAwardForm) form;
    subAwardForm.setAuditActivated(false);
    ValidationState status = new AuditActionHelper().isValidSubmission(subAwardForm, true);
    if ((status == ValidationState.OK) || (status == ValidationState.WARNING)) {
        super.route(mapping, form, request, response);
        return sendNotification(mapping, subAwardForm, SubAward.NOTIFICATION_TYPE_SUBMIT, "Submit SubAward");
    } else {
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().
        putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION, new String[] {});
        subAwardForm.setAuditActivated(true);   
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}



@Override
public ActionForward blanketApprove(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    SubAwardForm subAwardForm = (SubAwardForm) form;

    subAwardForm.setAuditActivated(false);
    ValidationState status = new AuditActionHelper().
    isValidSubmission(subAwardForm, true);
    if ((status == ValidationState.OK) || (status == ValidationState.WARNING)) {
        super.blanketApprove(mapping, form, request, response);
        return sendNotification(mapping, subAwardForm, SubAward.NOTIFICATION_TYPE_SUBMIT, "Submit SubAward");
    } else {
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().
        putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
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
      ValidationState status = new AuditActionHelper().
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
  protected final boolean applyRules(KualiDocumentEvent event) {
      return KraServiceLocator.getService(KualiRuleService.class).applyRules(event);
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
      return KraServiceLocator.getService(KcNotificationService.class);
  }

}
