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


/**
 * This class provides the implementation for printing ProtocolBase History Report. It generates XML that conforms with ProtocolBase History Report
 * XSD, fetches XSL style-sheets applicable to this XML, returns XML and XSL for any consumer that would use this XML and XSls for
 * any purpose like report generation, PDF streaming etc.
 * 
 */
public abstract class ProtocolHistoryPrintBase extends ProtocolReportPrintBase {

    private static final long serialVersionUID = 834187306362966953L;

    @Override
    public String getProtocolPrintType() {
        return ProtocolPrintType.PROTOCOL_PROTOCOL_HISTORY_REPORT.getProtocolPrintType();
    }

}
