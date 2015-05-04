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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

public class SubAwardFinancialAction extends SubAwardAction{
    
    private static final String LINE_NUMBER = "line";

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
            String backUrl = URLEncoder.encode(buildActionUrl(subAward.getSubAwardDocument().getDocumentNumber(), Constants.MAPPING_FINANCIAL_PAGE, "SubAwardDocument"), StandardCharsets.UTF_8.name());
            response.sendRedirect("kr/maintenance.do?businessObjectClassName=" + SubAwardAmountReleased.class.getName() + "&methodToCall=start" +
                    "&subAwardId=" + subAward.getSubAwardId() + "&subAwardCode=" + subAward.getSubAwardCode() +
                    "&sequenceNumber=" + subAward.getSequenceNumber() + "&backLocation=" + backUrl);
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
        String backUrl = URLEncoder.encode(buildActionUrl(subAward.getSubAwardDocument().getDocumentNumber(), Constants.MAPPING_FINANCIAL_PAGE, "SubAwardDocument"), StandardCharsets.UTF_8.name());
        response.sendRedirect("kr/maintenance.do?methodToCall=docHandler&docId=" + invoice.getDocumentNumber() + "&command=displayDocSearchView&backLocation=" + backUrl);
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
