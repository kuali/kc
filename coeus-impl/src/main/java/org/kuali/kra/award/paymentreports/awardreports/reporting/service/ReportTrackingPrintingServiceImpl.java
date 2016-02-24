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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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

 public AttachmentDataSource printAwardReportTracking(
             List<Printable> printableArtifactList) throws PrintingException {
             AttachmentDataSource attachmentDataSource =  getPrintingService().print(printableArtifactList);
         String fileName = "ReportTrackingPrint" + Constants.PDF_FILE_EXTENSION;
         try {
             attachmentDataSource.setName(URLEncoder.encode(fileName,"UTF-8"));
         } catch (UnsupportedEncodingException e) {
             attachmentDataSource.setName(fileName);
         }
         attachmentDataSource.setType(Constants.PDF_REPORT_CONTENT_TYPE);

         return attachmentDataSource;
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
