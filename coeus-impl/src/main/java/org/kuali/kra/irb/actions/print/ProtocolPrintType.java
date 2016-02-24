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
