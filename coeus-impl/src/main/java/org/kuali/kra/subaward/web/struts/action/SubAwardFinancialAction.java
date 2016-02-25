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

    public ActionForward addAmountInfo(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardAmountInfo amountInfo = subAwardForm.getNewSubAwardAmountInfo();
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        if (new SubAwardDocumentRule().processAddSubAwardAmountInfoBusinessRules(amountInfo, subAward)) {
            addAmountInfoToSubAward(subAwardForm.getSubAwardDocument(). getSubAward(), amountInfo);
            subAwardForm.setNewSubAwardAmountInfo(new SubAwardAmountInfo());
        }
        KcServiceLocator.getService(SubAwardService.class).calculateAmountInfo(subAwardForm.getSubAwardDocument().getSubAward());
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
     }

    boolean addAmountInfoToSubAward(SubAward subAward,SubAwardAmountInfo amountInfo){
        amountInfo.setSubAward(subAward);
        amountInfo.populateAttachment();
        subAward.getAllSubAwardAmountInfos().add(amountInfo);
        subAward.getSubAwardAmountInfoList().add(amountInfo);
        saveSubAwardAmountInfo(amountInfo);
        return true;
    }
	protected void saveSubAwardAmountInfo(SubAwardAmountInfo amountInfo) {
		getBusinessObjectService().save(amountInfo);
	}

    public ActionForward deleteAmountInfo(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response)throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        final SubAwardAmountInfo subAwardAmountInfo = 
        		subAwardDocument.getSubAward().getSubAwardAmountInfoList().get(selectedLineNumber);
        subAwardAmountInfo.setFileName(null);
        subAwardAmountInfo.setMimeType(null);
        subAwardAmountInfo.setFileDataId(null);
        saveSubAwardAmountInfo(subAwardAmountInfo);
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }

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

    public ActionForward openAmountReleased(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        final Integer invoiceIdx;
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
        final SubAwardForm subAwardForm = (SubAwardForm) form;
        final SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        final String amountInfoIdStr = request.getParameter(LINE_NUMBER);
        final Integer amountInfoId = amountInfoIdStr == null ? 0
         : Integer.parseInt(amountInfoIdStr);
        SubAwardAmountInfo subAwardAmountInfo = subAwardDocument.getSubAward().getAllSubAwardAmountInfos().stream()
        		.filter(amountInfo -> amountInfoId.equals(amountInfo.getSubAwardAmountInfoId()))
        		.findFirst().orElse(null);
        if (subAwardAmountInfo != null && subAwardAmountInfo.getDocument() != null) {
            this.streamToResponse(subAwardAmountInfo.getDocument(), 
                    getValidHeaderString(subAwardAmountInfo.getFileName()), getValidHeaderString(subAwardAmountInfo.getType()), response);
        }
        return null;
    }

    public ActionForward replaceHistoryOfChangesAttachment(
    	ActionMapping mapping, ActionForm form, HttpServletRequest request,
               HttpServletResponse response) throws Exception {
           SubAwardForm subAwardForm = (SubAwardForm) form;
           SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
           SubAwardAmountInfo subAwardAmountInfo = subAwardDocument.getSubAward().getSubAwardAmountInfoList().get(getSelectedLine(request));
           subAwardAmountInfo.populateAttachment();
           saveSubAwardAmountInfo(subAwardAmountInfo);
           return mapping.findForward(MAPPING_BASIC);
    }

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
