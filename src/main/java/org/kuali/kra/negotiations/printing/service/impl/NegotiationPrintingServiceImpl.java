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
package org.kuali.kra.negotiations.printing.service.impl;

import java.util.Map;

import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.kra.negotiations.printing.print.NegotiationActivityprint;
import org.kuali.kra.negotiations.printing.service.NegotiationPrintingService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * This class is the implementation of {@link AwardPrintingService}. It has
 * capability to print report related to Negotiation 
 * 
 * @author
 * 
 */
public class NegotiationPrintingServiceImpl implements NegotiationPrintingService {
    
    private PrintingService printingService; 
    private NegotiationActivityprint negotiationActivityPrint;
    private String reportFileNamePrefix= "";   

    public PrintingService getPrintingService() {
        return printingService;
    }
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }  
    public NegotiationActivityprint getNegotiationActivityPrint() {
        return negotiationActivityPrint;
    }
    public void setNegotiationActivityPrint(NegotiationActivityprint negotiationActivityPrint) {
        this.negotiationActivityPrint = negotiationActivityPrint;
    }   
    
    /**
     * This method generates the required report and returns the PDF stream as
     * {@link AttachmentDataSource}
     * 
     * @param negotiationDocument
     *            Negotiation data using which report is generated
     * @param reportName
     *            report to be generated
     * @param reportParameters
     *            {@link Map} of parameters required for report generation
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException
     *             if any errors occur during report generation
     */
    public AttachmentDataSource printNegotiationActivityReport(KraPersistableBusinessObjectBase negotiationDocument,
            NegotiationActivityPrintType negotiationReportType, Map<String, Object> reportParameters) throws PrintingException {                   
                
        AttachmentDataSource source = null;
        AbstractPrint printable = null;        
        printable = getNegotiationActivityPrint();               
        Negotiation negotiation=(Negotiation) negotiationDocument;
        reportFileNamePrefix = negotiation.getNegotiationId().toString();
        printable.setPrintableBusinessObject(negotiationDocument);
        printable.setReportParameters(reportParameters);       
        source = getPrintingService().print(printable);
        source.setFileName(getReportName(reportFileNamePrefix, negotiationReportType.getNegotiationActivityPrintType()));
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        return source;   
    }    
    
    protected String getReportName(String reportFileNamePrefix, String reportName) {
        StringBuilder reportFullName = new StringBuilder(reportFileNamePrefix).append(
                "_").append(reportName.replace(' ', '_')).append(
                Constants.PDF_FILE_EXTENSION);
        return reportFullName.toString();
    }    
}
