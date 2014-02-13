/*
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
package org.kuali.kra.reporting.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.reporting.BirtHelper;
import org.kuali.kra.reporting.bo.BirtParameterBean;
import org.kuali.kra.reporting.bo.CustReportDetails;
import org.kuali.kra.reporting.service.BirtReportService;
import org.kuali.kra.reporting.web.struts.form.ReportGenerationForm;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.service.BusinessObjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;




public class ReportGenerationAction extends ReportGenerationBaseAction {
    
    /**
     * sets report parameters to action form     
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response     
     * @return ActionForward     
     */
    public ActionForward getReportParametersFromDesign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        ReportGenerationForm reportGenerationForm = (ReportGenerationForm) form;
        ArrayList<BirtParameterBean> parameterList = new ArrayList<BirtParameterBean>();       
        if (request.getParameter("reportId") != null) {
            parameterList = KcServiceLocator.getService(BirtReportService.class).getInputParametersFromTemplateFile(
                    request.getParameter("reportId"));
            reportGenerationForm.setReportParameterList(parameterList);
            reportGenerationForm.setReportId(request.getParameter("reportId"));
            reportGenerationForm.setReportName(request.getParameter("reportLabel"));
        }
        return mapping.findForward(MAPPING_BASIC); 
    }
    
    /**
     * prints the selected report     
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response     
     * @return ActionForward     
     */
    public ActionForward printReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        InputStream reportDesignInputStream;
        ByteArrayOutputStream birtFilePrintArrayOutputStream = new ByteArrayOutputStream();
        IReportRunnable iReportRunnableDesign;
        ReportDesignHandle designHandle;  
        int birtCounter = 0;
        String reportId = request.getParameter("custReportDetails.reportLabelDisplay");
        if (reportId.equalsIgnoreCase("0")) {
            (new ErrorReporter()).reportError("custReportDetails.reportLabelDisplay",
                    KeyConstants.INVALID_BIRT_REPORT, "select");
            return mapping.findForward(MAPPING_BASIC);
        }       
        ArrayList<BirtParameterBean> parameterList = new ArrayList<BirtParameterBean>();
        String printReportFormat = Constants.PDF_REPORT_CONTENT_TYPE;
        String printReportNameAndExtension = Constants.PDF_FILE_EXTENSION;
        boolean isValid = Boolean.TRUE;

        ReportGenerationForm reportGenerationForm = (ReportGenerationForm) form;
        reportDesignInputStream = KcServiceLocator.getService(BirtReportService.class).getReportDesignFileStream(reportId);
        iReportRunnableDesign = BirtHelper.getEngine().openReportDesign(reportDesignInputStream);
        designHandle = (ReportDesignHandle) iReportRunnableDesign.getDesignHandle();
        designHandle.getDataSources().add(BirtHelper.getDataSourceHandle());
        designHandle.close();
        iReportRunnableDesign.setDesignHandle(designHandle);
        IRunAndRenderTask reportTask = BirtHelper.getEngine().createRunAndRenderTask(iReportRunnableDesign);
        HashMap parameters = new HashMap();
        parameterList = KcServiceLocator.getService(BirtReportService.class).getInputParametersFromTemplateFile(reportId);
        CustReportDetails reportDetails = KcServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(
                CustReportDetails.class, reportId);
        reportGenerationForm.setReportParameterList(parameterList);
        reportGenerationForm.setReportId(reportId);
        reportGenerationForm.setReportName(reportDetails.getReportLabel());

        for (BirtParameterBean parameterBean : parameterList) {
            if (parameterBean.getDataType() == Constants.DATE_TIME_TYPE) {
                try {
                      Date inputDate = KcServiceLocator.getService(DateTimeService.class).convertToDateTime( request.getParameter("reportParameterList[" + birtCounter + "].inputParameterText"));
                      parameters.put(parameterBean.getName(), inputDate);
                } catch (Exception exception) {
                    (new ErrorReporter()).reportError("reportParameterList[0].inputParameterText",
                            KeyConstants.REPORT_INPUT_PARAMETER_DATE_TYPE, "select");
                    return mapping.findForward(MAPPING_BASIC);
                }
            } else {
                parameters.put(parameterBean.getName(),
                        request.getParameter("reportParameterList[" + birtCounter + "].inputParameterText"));
            }
            birtCounter = birtCounter + 1;
        }
       
        HashMap contextMap = new HashMap();
        reportTask.setAppContext(contextMap);
        reportTask.setParameterValues(parameters);
        isValid = reportTask.validateParameters();
        if (!isValid) {
            (new ErrorReporter()).reportError("reportParameterList[0].inputParameterText",
                    KeyConstants.ERROR_BIRT_REPORT_INPUT_MISSING, "select");
        } else {
            RenderOption renderOption = null;

            if (reportGenerationForm.getReportFormat().equalsIgnoreCase(Constants.REPORT_FORMAT_PDF)) {
                renderOption = new PDFRenderOption();
                printReportFormat = Constants.PDF_REPORT_CONTENT_TYPE;
                printReportNameAndExtension = reportDetails.getReportLabel() + Constants.PDF_FILE_EXTENSION;
                renderOption.setOutputFormat(reportGenerationForm.getReportFormat());
            } else if (reportGenerationForm.getReportFormat().equalsIgnoreCase(Constants.REPORT_FORMAT_HTML)) {
                renderOption = new HTMLRenderOption();
                printReportFormat = Constants.HTML_REPORT_CONTENT_TYPE;
                printReportNameAndExtension = reportDetails.getReportLabel() + Constants.REPORT_FORMAT_HTML_EXTENSION;
                renderOption.setOutputFormat(reportGenerationForm.getReportFormat());
            } else if (reportGenerationForm.getReportFormat().equalsIgnoreCase(Constants.REPORT_FORMAT_EXCEL)) {
                renderOption = new EXCELRenderOption();
                printReportFormat = Constants.EXCEL_REPORT_CONTENT_TYPE;
                printReportNameAndExtension = reportDetails.getReportLabel() + Constants.REPORT_FORMAT_EXCEL_EXTENSION;
                renderOption.setOutputFormat("xls");
            } else {
                printReportFormat = Constants.PDF_REPORT_CONTENT_TYPE;
                renderOption = new PDFRenderOption();
            }

            renderOption.setOutputStream(birtFilePrintArrayOutputStream);
            reportTask.setRenderOption(renderOption);
            reportTask.run();
            WebUtils.saveMimeOutputStreamAsFile(response, printReportFormat, birtFilePrintArrayOutputStream,
                    printReportNameAndExtension);
            
            reportTask.close();
            DesignElementHandle designElementHandle = designHandle.findDataSource(Constants.BIRT_DATA_SOURCE);
            designHandle.getDataSources().dropAndClear(designElementHandle);
            designHandle.clearAllProperties();
        }
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.close(mapping, form, request, response);
    }
    
    public BirtReportService getBirtReportService() {
        return KcServiceLocator.getService(BirtReportService.class);
    }
}
