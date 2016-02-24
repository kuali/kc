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
package org.kuali.coeus.common.committee.impl.print.service;

import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.List;

/**
 * This class provides the means for printing reports related to CommitteeBase. It has the
 * capability to provide a PDF document of various reports related to CommitteeBase like 
 * Roster and Future Scheduled Meetings.
 */
public interface CommitteePrintingServiceBase {
    
    /**
     * 
     * This method gets the specific implementation for printing a committee report based
     * on the report type.
     * @param printType
     * @return printable
     */
    AbstractPrint getCommitteePrintable(CommitteeReportType reportType, String committeeId);
    
    /**
     * This method generates the required report and returns the PDF stream as
     * {@link AttachmentDataSource}.
     * 
     * @param printableArtifact the specific implementation for printing the report.
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException if any errors occur during report generation
     */
    AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException;

}
