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
package org.kuali.kra.iacuc.committee.print.service.impl;

import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.committee.impl.print.ScheduleTemplatePrintBase;
import org.kuali.coeus.common.committee.impl.print.TemplatePrintBase;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.impl.print.PrintingServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.committee.print.*;
import org.kuali.kra.iacuc.committee.print.service.IacucCommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * This class implements the CommitteePrintService.
 */
public class IacucCommitteePrintingServiceImpl extends PrintingServiceImpl implements IacucCommitteePrintingService {

    private static final String ERROR_MESSAGE = "Unknown report type specified";
    
    private IacucCommitteeTemplatePrint committeeTemplatePrint;
    private ScheduleTemplatePrintBase scheduleTemplatePrint;
    private IacucProtocolCorrespondenceTemplatePrint protocolCorrespondenceTemplatePrint;
    private IacucProtocolBatchCorrespondencePrint protocolBatchCorrespondencePrint;
    private IacucCommitteeRosterPrint committeeRosterPrint;
    private IacucCommitteeFutureScheduledMeetingsPrint committeeFutureScheduledMeetingsPrint;

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

    public IacucCommitteeTemplatePrint getCommitteeTemplatePrint() {
        return committeeTemplatePrint;
    }

    public void setCommitteeTemplatePrint(IacucCommitteeTemplatePrint committeeTemplatePrint) {
        this.committeeTemplatePrint = committeeTemplatePrint;
    }

    public ScheduleTemplatePrintBase getScheduleTemplatePrint() {
        /**
         * For some reason, spring doesn't always properly set scheduleTemplatePrint, so this correct that condition.
         */
        if (scheduleTemplatePrint == null) {
            scheduleTemplatePrint = KcServiceLocator.getService(IacucScheduleTemplatePrint.class);
        }
        return scheduleTemplatePrint;
    }

    public void setScheduleTemplatePrint(ScheduleTemplatePrintBase scheduleTemplatePrint) {
        this.scheduleTemplatePrint = scheduleTemplatePrint;
    }

    public IacucProtocolCorrespondenceTemplatePrint getProtocolCorrespondenceTemplatePrint() {
        return protocolCorrespondenceTemplatePrint;
    }

    public void setProtocolCorrespondenceTemplatePrint(IacucProtocolCorrespondenceTemplatePrint protocolCorrespondenceTemplatePrint) {
        this.protocolCorrespondenceTemplatePrint = protocolCorrespondenceTemplatePrint;
    }

    public IacucCommitteeRosterPrint getCommitteeRosterPrint() {
        return committeeRosterPrint;
    }

    public void setCommitteeRosterPrint(IacucCommitteeRosterPrint committeeRosterPrint) {
        this.committeeRosterPrint = committeeRosterPrint;
    }

    public IacucCommitteeFutureScheduledMeetingsPrint getCommitteeFutureScheduledMeetingsPrint() {
        return committeeFutureScheduledMeetingsPrint;
    }

    public void setCommitteeFutureScheduledMeetingsPrint(IacucCommitteeFutureScheduledMeetingsPrint committeeFutureScheduledMeetingsPrint) {
        this.committeeFutureScheduledMeetingsPrint = committeeFutureScheduledMeetingsPrint;
    }

    /**
     * Sets the protocolBatchCorrespondencePrint attribute value.
     * @param protocolBatchCorrespondencePrint The protocolBatchCorrespondencePrint to set.
     */
    public void setProtocolBatchCorrespondencePrint(IacucProtocolBatchCorrespondencePrint protocolBatchCorrespondencePrint) {
        this.protocolBatchCorrespondencePrint = protocolBatchCorrespondencePrint;
    }

    /**
     * Gets the protocolBatchCorrespondencePrint attribute. 
     * @return Returns the protocolBatchCorrespondencePrint.
     */
    public IacucProtocolBatchCorrespondencePrint getProtocolBatchCorrespondencePrint() {
        return protocolBatchCorrespondencePrint;
    }

}
