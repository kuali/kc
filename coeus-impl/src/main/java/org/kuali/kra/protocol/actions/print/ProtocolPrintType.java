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
package org.kuali.kra.protocol.actions.print;

import java.util.ArrayList;
import java.util.List;


public enum ProtocolPrintType {
    
    PROTOCOL_SUMMARY_VIEW_REPORT("Summary View Report"), 
    PROTOCOL_FULL_PROTOCOL_REPORT("Full ProtocolBase Report"),
    PROTOCOL_PROTOCOL_HISTORY_REPORT("ProtocolBase History Report"), 
    PROTOCOL_REVIEW_COMMENTS_REPORT("Review Comments Report");

    private String protocolPrintType;

    
    ProtocolPrintType(String protocolPrintType) {
        
    }
    
    public String getProtocolPrintType() {
        return protocolPrintType;
    }

    public static List<String>  getReportTypes() {
        List<String> reportTypes = new ArrayList<String>();
        for (ProtocolPrintType printType : values()) {
            reportTypes.add(printType.getProtocolPrintType());
        }
        return reportTypes;
        
    }

}
