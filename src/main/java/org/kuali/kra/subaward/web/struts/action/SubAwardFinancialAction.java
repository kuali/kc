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

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.rice.krad.util.KRADConstants;

public class SubAwardFinancialAction extends SubAwardAction{
    
    private static final String LINE_NUMBER = "line";
    private static final String CONFIRM_EFFECTIVE_DATE = "confirmEffectiveDate";
    private static final String NO_CONFIRM_EFFECTIVE_DATE = "noConfirmEffectiveDate";
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, ServletRequest request, ServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping,
    ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardAmountInfo amountInfo = subAwardForm.getNewSubAwardAmountInfo();
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        if (new SubAwardDocumentRule().
        processSaveSubAwardAmountInfoBusinessRule(
       subAward, amountInfo)) {

            ActionForward  forward = super.save(
            mapping, form, request, response);
            return forward;
        } else {
            return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);

        }
    }

       public ActionForward reload(ActionMapping mapping,
      ActionForm form, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        ActionForward forward = super.reload(mapping, form, request, response);
        return forward;
    }

    /**.
     * This method is for addAmountInfo
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return
     * @throws Exception
     */
    public ActionForward addAmountInfo(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardAmountInfo amountInfo =
        subAwardForm.getNewSubAwardAmountInfo();
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        if (new SubAwardDocumentRule().
        processAddSubAwardAmountInfoBusinessRules(amountInfo, subAward)) {
            addAmountInfoToSubAward(subAwardForm.getSubAwardDocument().
            getSubAward(), amountInfo);
            subAwardForm.setNewSubAwardAmountInfo(new SubAwardAmountInfo());
        }
        subAward = KraServiceLocator.getService(
        SubAwardService.class).getAmountInfo(subAwardForm.
        getSubAwardDocument().getSubAward());
        subAwardForm.getSubAwardDocument().setSubAward(subAward);
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
     }
    /**.
     * This method is for addAmountInfoToSubAward
     * @param subAward the SubAward
     * @param amountInfo the SubAwardAmountInfo
     * @return boolean
     */
    boolean addAmountInfoToSubAward(SubAward subAward,SubAwardAmountInfo amountInfo){
        amountInfo.setSubAward(subAward);
        amountInfo.populateAttachment();
        return subAward.getSubAwardAmountInfoList().add(amountInfo);
    }
    /**.
     * This method is for deleteAmountInfo
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteAmountInfo(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response)throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardAmountInfo subAwardAmountInfo =
        subAwardDocument.getSubAward().getSubAwardAmountInfoList().
        get(selectedLineNumber);
        if (subAwardAmountInfo.getSubAwardId() != null) {
            subAwardAmountInfo.setFileName(null);
            subAwardAmountInfo.setDocument(null);
            this.getBusinessObjectService().save(subAwardAmountInfo);
        } else {
            subAwardAmountInfo.setDocument(null);
            subAwardAmountInfo.setFileName(null);
        }

        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }
    /**.
     * This method is for addAmountReleased
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addAmountReleased(ActionMapping mapping,
    		ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        response.sendRedirect("kr/maintenance.do?businessObjectClassName=org.kuali.kra.subaward.bo.SubAwardAmountReleased&methodToCall=start" +
                "&subAwardId=" + subAward.getSubAwardId() + "&subAwardCode=" + subAward.getSubAwardCode() + 
                "&sequenceNumber=" + subAward.getSequenceNumber());

        return null;
    }
    
    /**.
     * Open amount released, or invoice
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openAmountReleased(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        Integer invoiceIdx = null;
        if (request.getParameter("line") != null) {
            invoiceIdx = Integer.parseInt(request.getParameter("line"));
        } else {
            invoiceIdx = getInvoiceIndex(request);
        }
        SubAwardAmountReleased invoice = subAward.getSubAwardAmountReleasedList().get(invoiceIdx);
        String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
        response.sendRedirect(String.format(DOC_HANDLER_URL_PATTERN, workflowUrl, invoice.getDocumentNumber()));

        return null;
    }    
    /**.
     * This method is for confirmEffectiveDate
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward confirmEffectiveDate(
    ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardAmountReleased subAwardAmountReleased =
        	subAwardForm.getNewSubAwardAmountReleased();
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        if (new SubAwardDocumentRule().
        processAddSubAwardAmountReleasedBusinessRules(
        subAwardAmountReleased, subAward)) { 
            addAmountReleasedToSubAward(subAwardForm.
            getSubAwardDocument().getSubAward(), subAwardAmountReleased);
            subAwardForm.setNewSubAwardAmountReleased(new SubAwardAmountReleased());
        }
        subAward = KraServiceLocator.getService(SubAwardService.class).getAmountInfo(subAwardForm.getSubAwardDocument().getSubAward());
        subAwardForm.getSubAwardDocument().setSubAward(subAward);
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE); 
    }
    public ActionForward noConfirmEffectiveDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm=(SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        subAward = KraServiceLocator.getService(SubAwardService.class).getAmountInfo(subAwardForm.getSubAwardDocument().getSubAward());
        subAwardForm.getSubAwardDocument().setSubAward(subAward);
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }
    boolean addAmountReleasedToSubAward(SubAward subAward,SubAwardAmountReleased subAwardAmountReleased){
        subAwardAmountReleased.setSubAward(subAward);  
        subAwardAmountReleased.populateAttachment();
        return subAward.getSubAwardAmountReleasedList().add(subAwardAmountReleased);
    }
    public ActionForward deleteAmountReleased(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm)form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardAmountReleased  subAwardAmountReleased =
        	subAwardDocument.getSubAward().getSubAwardAmountReleasedList().
        	get(selectedLineNumber);
        if (subAwardAmountReleased.getSubAward() != null) {
            subAwardAmountReleased.setDocument(null);
            subAwardAmountReleased.setFileName(null);
            this.getBusinessObjectService().save(subAwardAmountReleased);
        } else {
            subAwardAmountReleased.setDocument(null);
            subAwardAmountReleased.setFileName(null);
        }
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }

    public ActionForward downloadHistoryOfChangesAttachment(
    	ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0
         : Integer.parseInt(line);
        SubAwardAmountInfo subAwardAmountInfo =
         subAwardDocument.getSubAward().
         getSubAwardAmountInfoList().get(lineNumber);
        if (subAwardAmountInfo.getDocument() != null) {
            this.streamToResponse(subAwardAmountInfo.getDocument(), 
                    getValidHeaderString(subAwardAmountInfo.getFileName()), getValidHeaderString(subAwardAmountInfo.getContentType()), response);
        }
        return null;
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
           SubAwardAmountInfo subAwardAmountInfo =
           subAwardDocument.getSubAward().getSubAwardAmountInfoList().
            get(getSelectedLine(request));
           subAwardAmountInfo.populateAttachment();
           if (subAwardAmountInfo.getSubAwardId() != null) {
               getBusinessObjectService().save(subAwardAmountInfo);
           }
           return mapping.findForward(MAPPING_BASIC);
       }
       /**.
     * This method is for downloadInvoiceAttachment
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */

    public ActionForward downloadInvoiceAttachment(ActionMapping mapping,
     ActionForm form, HttpServletRequest request,
               HttpServletResponse response) throws Exception {
           SubAwardForm subAwardForm = (SubAwardForm) form;
           SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
           Integer invoiceIndex = getInvoiceIndex(request);
           SubAwardAmountReleased subAwardAmountReleased = subAwardDocument.getSubAward().getSubAwardAmountReleasedList().get(invoiceIndex);
           if (subAwardAmountReleased.getDocument() != null) {
               this.streamToResponse(subAwardAmountReleased.getData(), 
                       getValidHeaderString(subAwardAmountReleased.getName()), getValidHeaderString(subAwardAmountReleased.getType()), response);               
           }
           return null;
       }
    
    protected Integer getInvoiceIndex(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            return Integer.parseInt(StringUtils.substringBetween(parameterName, ".invoiceIndex", "."));
        }
        return null;
    }

       /**.
     * This method is for replaceInvoiceAttachment
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward replaceInvoiceAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
               HttpServletResponse response) throws Exception {
           SubAwardForm subAwardForm = (SubAwardForm) form;
           SubAwardDocument subAwardDocument =
           subAwardForm.getSubAwardDocument();
           SubAwardAmountReleased subAwardAmountReleased = subAwardDocument.getSubAward().
               getSubAwardAmountReleasedList().get(getSelectedLine(request));
           subAwardAmountReleased.populateAttachment();
           if (subAwardAmountReleased.getSubAward() != null) {
               getBusinessObjectService().save(subAwardAmountReleased);
           }
           return mapping.findForward(MAPPING_BASIC);
       }
}
