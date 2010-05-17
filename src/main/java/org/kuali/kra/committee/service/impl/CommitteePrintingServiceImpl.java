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
package org.kuali.kra.committee.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.kuali.kra.committee.print.CommitteeBatchCorrespondencePrint;
import org.kuali.kra.committee.print.CommitteeFutureScheduledMeetingsPrint;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.print.CommitteeRosterPrint;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * 
 * This class implements the CommitteePrintService.
 */
public class CommitteePrintingServiceImpl extends PrintingServiceImpl implements CommitteePrintingService {

    private static final String ERROR_MESSAGE = "Unknown report type specified";
    
    private CommitteeBatchCorrespondencePrint committeeBatchCorrespondencePrint;
    private CommitteeRosterPrint committeeRosterPrint;
    private CommitteeFutureScheduledMeetingsPrint committeeFutureScheduledMeetingsPrint;

    /**
     * {@inheritDoc}
     */
    public AbstractPrint getCommitteePrintable(CommitteeReportType reportType) {
        AbstractPrint printable = null;
        
        switch(reportType) {
            case BATCH_CORRESPONDENCE :
                printable = getCommitteeBatchCorrespondencePrint();
                break;
            case ROSTER :
                printable = getCommitteeRosterPrint();
                break;
            case FUTURE_SCHEDULED_MEETINGS :
                printable = getCommitteeFutureScheduledMeetingsPrint();
                break;
            default :
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        
        return printable;
    }
    
    @Override
    public AttachmentDataSource print(Printable printableArtifact) throws PrintingException {
        AttachmentDataSource attachmentDataSource = super.print(printableArtifact);

        // TODO: cniesen - Make file name nicer
        String fileName = "Committee Roster - " + Constants.PDF_FILE_EXTENSION;
        try {
            attachmentDataSource.setFileName(URLEncoder.encode(fileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            attachmentDataSource.setFileName(fileName);
        }
        attachmentDataSource.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);

        return attachmentDataSource;
    }
    
    public CommitteeBatchCorrespondencePrint getCommitteeBatchCorrespondencePrint() {
        return committeeBatchCorrespondencePrint;
    }

    public void setCommitteeBatchCorrespondencePrint(CommitteeBatchCorrespondencePrint committeeBatchCorrespondencePrint) {
        this.committeeBatchCorrespondencePrint = committeeBatchCorrespondencePrint;
    }

    public CommitteeRosterPrint getCommitteeRosterPrint() {
        return committeeRosterPrint;
    }

    public void setCommitteeRosterPrint(CommitteeRosterPrint committeeRosterPrint) {
        this.committeeRosterPrint = committeeRosterPrint;
    }

    public CommitteeFutureScheduledMeetingsPrint getCommitteeFutureScheduledMeetingsPrint() {
        return committeeFutureScheduledMeetingsPrint;
    }

    public void setCommitteeFutureScheduledMeetingsPrint(CommitteeFutureScheduledMeetingsPrint committeeFutureScheduledMeetingsPrint) {
        this.committeeFutureScheduledMeetingsPrint = committeeFutureScheduledMeetingsPrint;
    }

}
