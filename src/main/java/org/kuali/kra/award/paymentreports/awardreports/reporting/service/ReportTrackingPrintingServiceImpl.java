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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.print.AwardBudgetHierarchyPrint;
import org.kuali.kra.award.printing.print.AwardBudgetHistoryTransactionPrint;
import org.kuali.kra.award.printing.print.AwardDeltaPrint;
import org.kuali.kra.award.printing.print.AwardNoticePrint;
import org.kuali.kra.award.printing.print.AwardTemplatePrint;
import org.kuali.kra.award.printing.print.MoneyAndEndDatesHistoryPrint;

import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.print.CoiReportType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class is the implementation of {@link ReportTrackingPrintingService}. It has
 * capability to print any reports related to Award like Delta Report, Award
 * Notice etc.
 * 
 * @author
 * 
 */

public class ReportTrackingPrintingServiceImpl implements ReportTrackingPrintingService{

    private PrintingService printingService;
     private BusinessObjectService businessObjectService;
     private ReportTrackingPrint reportTrackingPrint;

    /**
     * This method generates the required report and returns the PDF stream as
     * {@link AttachmentDataSource}. It first identifies the report type to be
     * printed, then fetches the required report generator. The report generator
     * generates XML which is then passed to {@link PrintingService} for
     * transforming into PDF.
     * 
     * @param printableBO
     *            Award data using which report is generated
     * @param reportName
     *            report to be generated
     * @param reportParameters
     *            {@link Map} of parameters required for report generation
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException
     *             if any errors occur during report generation
     * 
     */
     public AttachmentDataSource printReportTracking(KraPersistableBusinessObjectBase printableBusinessObject, 
             String reportName, Map<String, Object> reportParameters) throws PrintingException {
         AttachmentDataSource source = null;
         AwardReportTracking printable = null;
         printable = getReportTrackingPrint();
         printable.setPrintableBusinessObject(printableBusinessObject);
         printable.setReportParameters(reportParameters);
         source = getPrintingService().print(printable);
         return source;
     }

 public AttachmentDataSource printAwardReportTracking(
             List<Printable> printableArtifactList) throws PrintingException {
             AttachmentDataSource attachmentDataSource =  getPrintingService().print(printableArtifactList);
         String fileName = "ReportTrackingPrint" + Constants.PDF_FILE_EXTENSION;
         try {
             attachmentDataSource.setFileName(URLEncoder.encode(fileName,"UTF-8"));
         } catch (UnsupportedEncodingException e) {
             attachmentDataSource.setFileName(fileName);
         }
         attachmentDataSource.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);

         return attachmentDataSource;
     }


    protected String getReportName(String reportFileNamePrefix, String reportName) {

        StringBuilder reportFullName = new StringBuilder(reportFileNamePrefix).append(
                "_").append(reportName.replace(' ', '_')).append(
                Constants.PDF_FILE_EXTENSION);
        return reportFullName.toString();
    }
     public AwardReportTracking getReportPrintable(ReportTrackingType reportType,ReportTracking detailResult,AwardReportTracking printable) {
            switch(reportType) {
                case AWARD_REPORT_TRACKING :
                    printable = getReportTrackingPrint();
                    printable.setPrintableBusinessObject(detailResult);
                    break;
                    }
            return printable;
        }
    /**
     * @return the printingService
     */
    public PrintingService getPrintingService() {
        return printingService;
    }

    /**
     * @param printingService
     *            the printingService to set
     */
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    

    public ReportTrackingPrint getReportTrackingPrint() {
        return reportTrackingPrint;
    }

    public void setReportTrackingPrint(ReportTrackingPrint reportTrackingPrint) {
        this.reportTrackingPrint = reportTrackingPrint;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
  
}
