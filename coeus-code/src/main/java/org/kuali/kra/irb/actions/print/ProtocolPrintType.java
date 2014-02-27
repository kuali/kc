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

import java.util.ArrayList;
import java.util.List;

public enum ProtocolPrintType {
    
    PROTOCOL_SUMMARY_VIEW_REPORT("Summary View Report", "ProtocolSummary.xsl", "ProtocolSummary"), 
    PROTOCOL_FULL_PROTOCOL_REPORT("Full Protocol Report", "ProtocolSummary.xsl","FullProtocolReport"),
    PROTOCOL_PROTOCOL_HISTORY_REPORT("Protocol History Report", "ProtocolHistoryReport.xsl","ProtocolHistoryReport"), 
    PROTOCOL_REVIEW_COMMENTS_REPORT("Review Comments Report", "ReviewCommentsReport.xsl","ProtocolReviewComments");


    
    private final String protocolPrintType;
    private final String template;
    private final String reportName;

    ProtocolPrintType(String protocolPrintType, String template,String reportName) {
        this.protocolPrintType = protocolPrintType;
        this.template = template;
        this.reportName = reportName;
    }

    public String getProtocolPrintType() {
        return protocolPrintType;
    }
    public String getTemplate() {
        return template;
    }

    public static List<String>  getReportTypes() {
        List<String> reportTypes = new ArrayList<String>();
        for (ProtocolPrintType printType : values()) {
            reportTypes.add(printType.getProtocolPrintType());
        }
        return reportTypes;
        
    }

    /**
     * Gets the reportName attribute. 
     * @return Returns the reportName.
     */
    public String getReportName() {
        return reportName;
    }
}
