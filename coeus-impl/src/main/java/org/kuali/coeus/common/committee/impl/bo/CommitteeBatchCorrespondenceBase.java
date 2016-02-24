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
package org.kuali.coeus.common.committee.impl.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class implements the CommitteeBatchCorrespondenceBase business object.
 */
public abstract class CommitteeBatchCorrespondenceBase extends KcPersistableBusinessObjectBase implements Comparable<CommitteeBatchCorrespondenceBase> {

    private static final long serialVersionUID = 1L;

    private String committeeBatchCorrespondenceId;

    private String committeeId;

    private String batchCorrespondenceTypeCode;

    private Timestamp batchRunDate;

    private Date timeWindowStart;

    private Date timeWindowEnd;

    private List<CommitteeBatchCorrespondenceDetailBase> committeeBatchCorrespondenceDetails;

    private BatchCorrespondenceBase batchCorrespondence;

    private CommitteeBase committee;

    private transient int finalActionCounter;

    private transient DateTimeService dateTimeService;


    public CommitteeBatchCorrespondenceBase() {
        setCommitteeBatchCorrespondenceDetails(new ArrayList<CommitteeBatchCorrespondenceDetailBase>());
    }

    /**
     * Constructs a CommitteeBatchCorrespondenceBase.java for a new request.
     * (The committeeBatchCorrespondenceId is set to the next SEQ_COMMITTEE_ID sequence number and 
     *  the batchRunDate is set to the current date.)
     * @param batchCorrespondenceTypeCode 
     * @param committeeId
     * @param startDate
     * @param endDate
     */
    public CommitteeBatchCorrespondenceBase(String batchCorrespondenceTypeCode, String committeeId, Date startDate, Date endDate) {
        this();
        setCommitteeBatchCorrespondenceId(KcServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_COMMITTEE_ID", getClass()).toString());
        setCommitteeId(committeeId);
        setBatchCorrespondenceTypeCode(batchCorrespondenceTypeCode);
        setBatchRunDate(getDateTimeService().getCurrentTimestamp());
        setTimeWindowStart(startDate);
        setTimeWindowEnd(endDate);
        setFinalActionCounter(0);
    }

    public String getCommitteeBatchCorrespondenceId() {
        return committeeBatchCorrespondenceId;
    }

    public void setCommitteeBatchCorrespondenceId(String committeeBatchCorrespondenceId) {
        this.committeeBatchCorrespondenceId = committeeBatchCorrespondenceId;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public Timestamp getBatchRunDate() {
        return batchRunDate;
    }

    public void setBatchRunDate(Timestamp batchRunDate) {
        this.batchRunDate = batchRunDate;
    }

    public String getFormattedBatchRunDate() {
        return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN).format(batchRunDate);
    }

    public String getFormattedBatchRunTime() {
        return new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT_PATTERN).format(batchRunDate);
    }

    public Date getTimeWindowStart() {
        return timeWindowStart;
    }

    public void setTimeWindowStart(Date timeWindowStart) {
        this.timeWindowStart = timeWindowStart;
    }

    public String getFormattedTimeWindowStart() {
        return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN).format(timeWindowStart);
    }

    public Date getTimeWindowEnd() {
        return timeWindowEnd;
    }

    public void setTimeWindowEnd(Date timeWindowEnd) {
        this.timeWindowEnd = timeWindowEnd;
    }

    public String getFormattedTimeWindowEnd() {
        return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN).format(timeWindowEnd);
    }

    public List<CommitteeBatchCorrespondenceDetailBase> getCommitteeBatchCorrespondenceDetails() {
        return committeeBatchCorrespondenceDetails;
    }

    public void setCommitteeBatchCorrespondenceDetails(List<CommitteeBatchCorrespondenceDetailBase> committeeBatchCorrespondenceDetails) {
        this.committeeBatchCorrespondenceDetails = committeeBatchCorrespondenceDetails;
    }

    public BatchCorrespondenceBase getBatchCorrespondence() {
        return batchCorrespondence;
    }

    public void setBatchCorrespondence(BatchCorrespondenceBase batchCorrespondence) {
        this.batchCorrespondence = batchCorrespondence;
    }

    public CommitteeBase getCommittee() {
        return committee;
    }

    public void setCommittee(CommitteeBase committee) {
        this.committee = committee;
    }

    public int compareTo(CommitteeBatchCorrespondenceBase arg) {
        int timeWindowStartDiff = this.getTimeWindowStart().compareTo(arg.getTimeWindowStart());
        if (timeWindowStartDiff != 0) {
            return timeWindowStartDiff;
        }
        int timeWindowEndDiff = this.getTimeWindowEnd().compareTo(arg.getTimeWindowEnd());
        if (timeWindowEndDiff != 0) {
            return timeWindowEndDiff;
        }
        int batchRunDateDiff = this.getBatchRunDate().compareTo(arg.getBatchRunDate());
        if (batchRunDateDiff != 0) {
            return batchRunDateDiff;
        }
        return this.getCommitteeBatchCorrespondenceId().compareTo(arg.getCommitteeBatchCorrespondenceId());
    }

    public int getFinalActionCounter() {
        return finalActionCounter;
    }

    public void setFinalActionCounter(int finalActionCounter) {
        this.finalActionCounter = finalActionCounter;
    }

    private DateTimeService getDateTimeService() {
        if (this.dateTimeService == null) {
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        }
        return this.dateTimeService;
    }
}
