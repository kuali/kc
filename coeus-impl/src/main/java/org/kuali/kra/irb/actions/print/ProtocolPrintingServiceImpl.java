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
package org.kuali.kra.irb.actions.print;

import org.kuali.kra.protocol.actions.print.ProtocolPrintHelper;
import org.kuali.kra.protocol.actions.print.ProtocolPrintType;
import org.kuali.kra.protocol.actions.print.ProtocolPrintingServiceImplBase;

import java.util.HashMap;

/**
 * 
 * This class to implement the getProtocolPrintable.
 */
public class ProtocolPrintingServiceImpl extends ProtocolPrintingServiceImplBase implements ProtocolPrintingService {

    public enum IRBPrintTypes {
        PROTOCOL_SUMMARY_VIEW_REPORT("ProtocolSummary.xsl", "ProtocolSummary", "Protocol_Summary_Report.pdf"), 
        PROTOCOL_FULL_PROTOCOL_REPORT("ProtocolSummary.xsl","FullProtocolReport", "Full_Protocol_Report.pdf"),
        PROTOCOL_PROTOCOL_HISTORY_REPORT("ProtocolHistoryReport.xsl","ProtocolHistoryReport", "Protocol_History_Report.pdf"), 
        PROTOCOL_REVIEW_COMMENTS_REPORT("ReviewCommentsReport.xsl","ProtocolReviewComments", "Protocol_Review_Comments_Report.pdf");

        private final String template;
        private final String reportName;
        private final String fileName;

        IRBPrintTypes(String template,String reportName, String fileName) {
            this.template = template;
            this.reportName = reportName;
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
        public String getTemplate() {
            return template;
        }
        public String getReportName() {
            return reportName;
        }
    }
    
    @Override
    protected HashMap<ProtocolPrintType, ProtocolPrintHelper> getProtocolPrintParameterHook() {
        HashMap<ProtocolPrintType, ProtocolPrintHelper> printParameter = new HashMap<ProtocolPrintType, ProtocolPrintHelper>(); 
        for (ProtocolPrintType protocolPrintType : ProtocolPrintType.values()) {
            ProtocolPrintHelper printHelper = null;
            switch(protocolPrintType) {
                case PROTOCOL_FULL_PROTOCOL_REPORT :
                    printHelper = new ProtocolPrintHelper(IRBPrintTypes.PROTOCOL_FULL_PROTOCOL_REPORT.getTemplate(),
                            IRBPrintTypes.PROTOCOL_FULL_PROTOCOL_REPORT.getReportName(), 
                            IRBPrintTypes.PROTOCOL_FULL_PROTOCOL_REPORT.getFileName());
                    break;
                case PROTOCOL_PROTOCOL_HISTORY_REPORT :
                    printHelper = new ProtocolPrintHelper(IRBPrintTypes.PROTOCOL_PROTOCOL_HISTORY_REPORT.getTemplate(),
                            IRBPrintTypes.PROTOCOL_PROTOCOL_HISTORY_REPORT.getReportName(), 
                            IRBPrintTypes.PROTOCOL_PROTOCOL_HISTORY_REPORT.getFileName());
                    break;
                case PROTOCOL_REVIEW_COMMENTS_REPORT :
                    printHelper = new ProtocolPrintHelper(IRBPrintTypes.PROTOCOL_REVIEW_COMMENTS_REPORT.getTemplate(),
                            IRBPrintTypes.PROTOCOL_REVIEW_COMMENTS_REPORT.getReportName(), 
                            IRBPrintTypes.PROTOCOL_REVIEW_COMMENTS_REPORT.getFileName());
                    break;
                case PROTOCOL_SUMMARY_VIEW_REPORT :
                    printHelper = new ProtocolPrintHelper(IRBPrintTypes.PROTOCOL_SUMMARY_VIEW_REPORT.getTemplate(),
                            IRBPrintTypes.PROTOCOL_SUMMARY_VIEW_REPORT.getReportName(), 
                            IRBPrintTypes.PROTOCOL_SUMMARY_VIEW_REPORT.getFileName());
                    break;
                default :
                    throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            printParameter.put(protocolPrintType, printHelper);
        }
        return printParameter;
    }
    
}
