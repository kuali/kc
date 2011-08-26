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
package org.kuali.kra.irb.actions.print;

import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * 
 * This class to implement the getProtocolPrintable.
 */
public class ProtocolPrintingServiceImpl extends PrintingServiceImpl implements ProtocolPrintingService {

    private static final String ERROR_MESSAGE = "Unknown report type specified";
    private ProtocolFullProtocolPrint protocolFullProtocolPrint;
    private ProtocolHistoryPrint protocolHistoryPrint;
    private ProtocolReviewCommentsPrint protocolReviewCommentsPrint;
    private ProtocolSummaryViewPrint protocolSummaryViewPrint;

    private String reportName;
    /**
     * 
     * @see org.kuali.kra.irb.actions.print.ProtocolPrintingService#getProtocolPrintable(org.kuali.kra.irb.actions.print.ProtocolPrintType)
     */
    public Printable getProtocolPrintable(ProtocolPrintType reportType) {
        Printable printable = null;
        
        switch(reportType) {
            case PROTOCOL_FULL_PROTOCOL_REPORT :
                printable = getProtocolFullProtocolPrint();
                break;
            case PROTOCOL_PROTOCOL_HISTORY_REPORT :
                printable = getProtocolHistoryPrint();
                break;
            case PROTOCOL_REVIEW_COMMENTS_REPORT :
                printable = getProtocolReviewCommentsPrint();
                break;
            case PROTOCOL_SUMMARY_VIEW_REPORT :
                printable = getProtocolSummaryViewPrint();
                break;
            default :
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return printable;
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.print.ProtocolPrintingService#getProtocolPrintArtifacts(org.kuali.kra.irb.Protocol)
     */
     public Printable getProtocolPrintArtifacts(Protocol protocol) { 
         
         ProtocolPrintType printType = ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT;
         AbstractPrint printable = (AbstractPrint)getProtocolPrintable(printType);
         printable.setPrintableBusinessObject(protocol);
         return printable;
     }
        
    public ProtocolFullProtocolPrint getProtocolFullProtocolPrint() {
        return protocolFullProtocolPrint;
    }

    public void setProtocolFullProtocolPrint(ProtocolFullProtocolPrint protocolFullProtocolPrint) {
        this.protocolFullProtocolPrint = protocolFullProtocolPrint;
    }

    public ProtocolHistoryPrint getProtocolHistoryPrint() {
        return protocolHistoryPrint;
    }

    public void setProtocolHistoryPrint(ProtocolHistoryPrint protocolHistoryPrint) {
        this.protocolHistoryPrint = protocolHistoryPrint;
    }

    public ProtocolReviewCommentsPrint getProtocolReviewCommentsPrint() {
        return protocolReviewCommentsPrint;
    }

    public void setProtocolReviewCommentsPrint(ProtocolReviewCommentsPrint protocolReviewCommentsPrint) {
        this.protocolReviewCommentsPrint = protocolReviewCommentsPrint;
    }

    public ProtocolSummaryViewPrint getProtocolSummaryViewPrint() {
        return protocolSummaryViewPrint;
    }

    public void setProtocolSummaryViewPrint(ProtocolSummaryViewPrint protocolSummaryViewPrint) {
        this.protocolSummaryViewPrint = protocolSummaryViewPrint;
    }
    
    @Override
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the reportName attribute value.
     * @param reportName The reportName to set.
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public AttachmentDataSource print(String reportName, List<Printable> printableArtifactList) throws PrintingException {
        setReportName(reportName);
        return super.print(printableArtifactList);
    }
    
}
