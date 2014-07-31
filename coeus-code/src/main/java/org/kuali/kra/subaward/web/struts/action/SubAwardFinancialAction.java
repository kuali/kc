/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.rice.krad.rules.rule.event.SaveDocumentEvent;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

public class SubAwardFinancialAction extends SubAwardAction{
    
    private static final String LINE_NUMBER = "line";
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";

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
        KcServiceLocator.getService(SubAwardService.class).getAmountInfo(subAwardForm.getSubAwardDocument().getSubAward());
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
        if (getKualiRuleService().applyRules(new SaveDocumentEvent("document", subAward.getSubAwardDocument()))) {
            this.save(mapping, subAwardForm, request, response);
            response.sendRedirect("kr/maintenance.do?businessObjectClassName=" + SubAwardAmountReleased.class.getName() + "&methodToCall=start" +
                    "&subAwardId=" + subAward.getSubAwardId() + "&subAwardCode=" + subAward.getSubAwardCode() + 
                    "&sequenceNumber=" + subAward.getSequenceNumber());
        }
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
                    getValidHeaderString(subAwardAmountInfo.getFileName()), getValidHeaderString(subAwardAmountInfo.getType()), response);
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
