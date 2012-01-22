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


/**
 * This class provides the implementation for printing Protocol Full Protocol Report. It
 * generates XML that conforms with Full Protocol Report XSD, fetches XSL style-sheets
 * applicable to this XML, returns XML and XSL for any consumer that would use
 * this XML and XSls for any purpose like report generation, PDF streaming etc.
 * 
 */
public class ProtocolFullProtocolPrint extends ProtocolReportPrint {

    private static final long serialVersionUID = -2812548564327706244L;

    /**
     * 
     * @see org.kuali.kra.irb.actions.print.ProtocolReportPrint#getProtocolPrintType()
     */
    public String getProtocolPrintType() {
        return ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT.getProtocolPrintType();
    }

}

