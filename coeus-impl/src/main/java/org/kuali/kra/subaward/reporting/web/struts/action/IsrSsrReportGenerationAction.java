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
package org.kuali.kra.subaward.reporting.web.struts.action;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.kra.subaward.reporting.printing.service.SubAwardPrintingService;
import org.kuali.kra.subaward.reporting.web.struts.form.IsrSsrReportGenerationForm;
import org.kuali.rice.kns.web.struts.action.KualiAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IsrSsrReportGenerationAction extends KualiAction {

    private static final String SF_295_REPORT = "SF295";
    private static final String AWARD_NO = "awardNo";

    private transient AwardService awardService;
    private transient ErrorReporter errorReporter;
    private transient SubAwardPrintingService subAwardPrintingService;

    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_CLOSE);
    }
    
    /**
     *  method for report button.
     */
    public ActionForward printReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        IsrSsrReportGenerationForm isrSsrReportGenerationForm = (IsrSsrReportGenerationForm) form;
        if (StringUtils.isNotBlank(isrSsrReportGenerationForm.getAwardNo()) && StringUtils.isNotBlank(isrSsrReportGenerationForm.getReportType()) && StringUtils.isNotBlank(isrSsrReportGenerationForm.getReportName())) {
            final List<Award> awards = getAwardService().findAwardsForAwardNumber((isrSsrReportGenerationForm.getAwardNo()));
            if (CollectionUtils.isNotEmpty(awards)) {
                final Award award = awards.get(0);

                final Map<String, Object> reportParameters = new HashMap<String, Object>();
                reportParameters.put("printType", isrSsrReportGenerationForm.getReportName());
                reportParameters.put("reportType", isrSsrReportGenerationForm.getReportType());
                reportParameters.put("awardNumber", isrSsrReportGenerationForm.getAwardNo());

                final AttachmentDataSource dataStream;
                if (isrSsrReportGenerationForm.getReportName().equals(SF_295_REPORT)) {
                    dataStream = getSubAwardPrintingService().printSubAwardReport(award, SubAwardPrintType.SUB_AWARD_SF_295_PRINT_TYPE, reportParameters);
                } else {
                    dataStream = getSubAwardPrintingService().printSubAwardReport(award, SubAwardPrintType.SUB_AWARD_SF_294_PRINT_TYPE, reportParameters);
                }
                PrintingUtils.streamToResponse(dataStream, response);
            } else {
                getErrorReporter().reportError(AWARD_NO, KeyConstants.ERROR_AWARD_NOT_FOUND, "");
            }
        } else {
            getErrorReporter().reportError(AWARD_NO, KeyConstants.REPORT_INPUT_PARAMETER_MISSING, "");
        }
        return  mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    protected void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    protected ErrorReporter getErrorReporter() {
        if (errorReporter == null) {
            errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        }

        return errorReporter;
    }

    protected void setErrorReporter(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }

    protected SubAwardPrintingService getSubAwardPrintingService() {
        if (subAwardPrintingService == null) {
            subAwardPrintingService = KcServiceLocator.getService(SubAwardPrintingService.class);
        }
        return subAwardPrintingService;
    }

    protected void setSubAwardPrintingService(SubAwardPrintingService subAwardPrintingService) {
        this.subAwardPrintingService = subAwardPrintingService;
    }
}
