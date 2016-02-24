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
package org.kuali.kra.committee.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.committee.impl.print.TemplatePrintBase;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.impl.print.PrintingServiceImpl;
import org.kuali.kra.committee.print.*;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

/**
 * 
 * This class implements the CommitteePrintService.
 */
public class CommitteePrintingServiceImpl extends PrintingServiceImpl implements CommitteePrintingService {

    private static final String ERROR_MESSAGE = "Unknown report type specified";
    
    private CommitteeTemplatePrint committeeTemplatePrint;
    private ScheduleTemplatePrint scheduleTemplatePrint;
    private ProtocolCorrespondenceTemplatePrint protocolCorrespondenceTemplatePrint;
    private ProtocolBatchCorrespondencePrint protocolBatchCorrespondencePrint;
    private CommitteeRosterPrint committeeRosterPrint;
    private CommitteeFutureScheduledMeetingsPrint committeeFutureScheduledMeetingsPrint;

    @Override
    public AbstractPrint getCommitteePrintable(CommitteeReportType reportType, String committeeId) {
        AbstractPrint printable = null;
        
        switch(reportType) {
            case COMMITTEE_TEMPLATE :
                printable = getCommitteeTemplatePrint();
                break;
            case SCHEDULE_TEMPLATE :
                printable = getScheduleTemplatePrint();
                break;
            case PROTOCOL_CORRESPONDENCE_TEMPLATE :
                printable = getProtocolCorrespondenceTemplatePrint();
                break;
            case ROSTER :
                printable = getCommitteeRosterPrint();
                break;
            case FUTURE_SCHEDULED_MEETINGS :
                printable = getCommitteeFutureScheduledMeetingsPrint();
                break;
            case PROTOCOL_BATCH_CORRESPONDENCE :
                printable = getProtocolBatchCorrespondencePrint();
                break;
            default :
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        if(printable instanceof TemplatePrintBase) {
            TemplatePrintBase printBase = (TemplatePrintBase)printable;
            printBase.setCommitteeId(committeeId);
        }
        return printable;
    }
    
    @Override
    public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException {
        AttachmentDataSource attachmentDataSource = super.print(printableArtifactList);

        String fileName = "CommitteeReport" + Constants.PDF_FILE_EXTENSION;
        try {
            attachmentDataSource.setName(URLEncoder.encode(fileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            attachmentDataSource.setName(fileName);
        }
        attachmentDataSource.setType(Constants.PDF_REPORT_CONTENT_TYPE);

        return attachmentDataSource;
    }

    public CommitteeTemplatePrint getCommitteeTemplatePrint() {
        return committeeTemplatePrint;
    }

    public void setCommitteeTemplatePrint(CommitteeTemplatePrint committeeTemplatePrint) {
        this.committeeTemplatePrint = committeeTemplatePrint;
    }

    public ScheduleTemplatePrint getScheduleTemplatePrint() {
        return scheduleTemplatePrint;
    }

    public void setScheduleTemplatePrint(ScheduleTemplatePrint scheduleTemplatePrint) {
        this.scheduleTemplatePrint = scheduleTemplatePrint;
    }

    public ProtocolCorrespondenceTemplatePrint getProtocolCorrespondenceTemplatePrint() {
        return protocolCorrespondenceTemplatePrint;
    }

    public void setProtocolCorrespondenceTemplatePrint(ProtocolCorrespondenceTemplatePrint protocolCorrespondenceTemplatePrint) {
        this.protocolCorrespondenceTemplatePrint = protocolCorrespondenceTemplatePrint;
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

    /**
     * Sets the protocolBatchCorrespondencePrint attribute value.
     * @param protocolBatchCorrespondencePrint The protocolBatchCorrespondencePrint to set.
     */
    public void setProtocolBatchCorrespondencePrint(ProtocolBatchCorrespondencePrint protocolBatchCorrespondencePrint) {
        this.protocolBatchCorrespondencePrint = protocolBatchCorrespondencePrint;
    }

    /**
     * Gets the protocolBatchCorrespondencePrint attribute. 
     * @return Returns the protocolBatchCorrespondencePrint.
     */
    public ProtocolBatchCorrespondencePrint getProtocolBatchCorrespondencePrint() {
        return protocolBatchCorrespondencePrint;
    }

}
