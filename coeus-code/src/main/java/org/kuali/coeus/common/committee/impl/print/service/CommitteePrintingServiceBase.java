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
