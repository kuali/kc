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
package org.kuali.kra.subaward.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

public class SubAwardAction extends KraTransactionalDocumentActionBase{
    
    private transient SubAwardService subAwardService;
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        SubAwardForm subAwardForm = (SubAwardForm)form;
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        
        
        return actionForward;
    }
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        ActionForward forward;
        forward = handleDocument(mapping, form, request, response, subAwardForm);
        SubAwardDocument subAwardDocument = (SubAwardDocument) subAwardForm.getDocument();
        subAwardForm.initializeFormOrDocumentBasedOnCommand();
        return forward;
    }
    
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param subAwardForm
     * @return
     * @throws Exception
     */
    ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, SubAwardForm subAwardForm) throws Exception {

        ActionForward forward = null;
        
        String command = subAwardForm.getCommand();
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            loadDocumentInForm(request, subAwardForm);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }
        return forward;
    }
    /**
    *
    * loadDocumentInForm
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */    
    protected void loadDocumentInForm(HttpServletRequest request, SubAwardForm awardForm)
    throws WorkflowException {
        String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
        SubAwardDocument retrievedDocument = (SubAwardDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
        awardForm.setDocument(retrievedDocument);
        request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
    }
    
    /**
    *
    * This method builds the string for the ActionForward
    * @param forwardPath
    * @param docIdRequestParameter
    * @return
    */
   public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
       StringBuilder sb = new StringBuilder();
       sb.append(forwardPath);
       sb.append("?");
       sb.append(KNSConstants.PARAMETER_DOC_ID);
       sb.append("=");
       sb.append(docIdRequestParameter);
       return sb.toString();
   }
   
    protected void loadDocument(KualiDocumentFormBase kualiForm) throws WorkflowException {
        super.loadDocument(kualiForm);
        SubAward subAward = ((SubAwardForm) kualiForm).getSubAwardDocument().getSubAward();
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        SubAwardForm subAwardForm = (SubAwardForm) form;
        
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        
        if(new SubAwardDocumentRule().processAddSubAwardBusinessRules(subAward)){
            return super.save(mapping, form, request, response);
        }
        else{
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    /**
    *
    * This method gets called upon navigation to subaward tab.
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
   public ActionForward subAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

       return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
   }
    
   
    /**
     *
     * This method gets called upon navigation to fundingSource tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward fundingSource(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

        return mapping.findForward(Constants.MAPPING_FUNDING_SOURCE_PAGE);
    }
    
    /**
    *
    * This method gets called upon navigation to amountInfo tab.
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
   public ActionForward amountInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

       return mapping.findForward(Constants.MAPPING_AMOUNT_INFO_PAGE);
   }
   /**
   *
   * This method gets called upon navigation to amountReleased tab.
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward amountReleased(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

      return mapping.findForward(Constants.MAPPING_AMOUNT_RELEASED_PAGE);
  }
  
  /**
  *
  * This method gets called upon navigation to Contacts tab.
  * @param mapping
  * @param form
  * @param request
  * @param response
  * @return
  */
 public ActionForward contacts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

     return mapping.findForward(Constants.MAPPING_CONTACTS_PAGE);
 }
 
 /**
 *
 * This method gets called upon navigation to closeouts tab.
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 */
public ActionForward closeouts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

    return mapping.findForward(Constants.MAPPING_CLOSEOUT_PAGE);
}
/**
*
* This method gets called upon navigation to custom data tab.
* @param mapping
* @param form
* @param request
* @param response
* @return
*/

public ActionForward customData(ActionMapping mapping, ActionForm form
        , HttpServletRequest request, HttpServletResponse response) {
    SubAwardForm subAwardForm = (SubAwardForm) form;
    return subAwardForm.getCustomDataHelper().subAwardCustomData(mapping, form, request, response);
}

/**
*
* This method gets called upon navigation to subaward action tab.
* @param mapping
* @param form
* @param request
* @param response
* @return
*/

public ActionForward subAwardActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    
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
@SuppressWarnings("unchecked")
protected KeywordsService getKeywordService(){
    return KraServiceLocator.getService(KeywordsService.class);
}


public SubAwardService getSubAwardService() {
    if (subAwardService == null) {
        subAwardService = KraServiceLocator.getService(SubAwardService.class);
    }
    return subAwardService;
}

public void setSubAwardService(SubAwardService subAwardService) {
    this.subAwardService = subAwardService;
}

}
