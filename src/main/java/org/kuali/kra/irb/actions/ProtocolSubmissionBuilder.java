/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import java.sql.Timestamp;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolSubmissionBuilder {

    private static final String NEXT_SUBMISSION_NUMBER_KEY = "submissionNumber";
    
    private ProtocolSubmission protocolSubmission;
    
    public ProtocolSubmissionBuilder(Protocol protocol, String submissionTypeCode) {
        protocolSubmission = new ProtocolSubmission();
        protocolSubmission.setProtocol(protocol);
        protocolSubmission.setProtocolId(protocol.getProtocolId());
        protocolSubmission.setProtocolNumber(protocol.getProtocolNumber());
        protocolSubmission.setSequenceNumber(protocol.getSequenceNumber());
        protocolSubmission.setSubmissionNumber(protocol.getNextValue(NEXT_SUBMISSION_NUMBER_KEY));
        protocolSubmission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        protocolSubmission.setSubmissionTypeCode(submissionTypeCode);
        protocolSubmission.setSubmissionStatusCode("100");  // this will need to be changed in future development
    }
    
    public ProtocolSubmission create() {
        getBusinessObjectService().save(protocolSubmission);
        protocolSubmission.getProtocol().getProtocolSubmissions().add(protocolSubmission);
        return protocolSubmission;
    }
    
    public void setSubmissionTypeQualifierCode(String submissionTypeQualifierCode) {
        protocolSubmission.setSubmissionTypeQualifierCode(submissionTypeQualifierCode);
    }
    
    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        protocolSubmission.setProtocolReviewTypeCode(protocolReviewTypeCode);
    }
    
    public boolean setCommittee(String committeeId) {
        Committee committee = getCommitteeService().getCommitteeById(committeeId);
        if (committee != null) {
            protocolSubmission.setCommitteeId(committee.getCommitteeId());
            protocolSubmission.setCommitteeIdFk(committee.getId());
            protocolSubmission.setCommittee(committee);
            return true;
        }
        return false;
    }
    
    public boolean setSchedule(String committeeId, String scheduleId) {
        if (setCommittee(committeeId)) {
            CommitteeSchedule schedule = getCommitteeService().getCommitteeSchedule(protocolSubmission.getCommittee(), scheduleId);
            if (schedule != null) {
                protocolSubmission.setScheduleId(schedule.getScheduleId());
                protocolSubmission.setScheduleIdFk(schedule.getId());
                protocolSubmission.setCommitteeSchedule(schedule);
                return true;
            }
        }
        return false;
    }
    
    public void addReviewer(String personId, String reviewerTypeCode, boolean nonEmployeeFlag) {
        protocolSubmission.getProtocolReviewers().add(createProtocolReviewer(personId, reviewerTypeCode, nonEmployeeFlag));
    }
    
    private ProtocolReviewer createProtocolReviewer(String personId, String reviewerTypeCode, boolean nonEmployeeFlag) {
        ProtocolReviewer protocolReviewer = new ProtocolReviewer();
        protocolReviewer.setProtocolId(protocolSubmission.getProtocolId());
        protocolReviewer.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        protocolReviewer.setProtocolNumber(protocolSubmission.getProtocolNumber());
        protocolReviewer.setSequenceNumber(protocolSubmission.getSequenceNumber());
        protocolReviewer.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        protocolReviewer.setPersonId(personId);
        protocolReviewer.setReviewerTypeCode(reviewerTypeCode);
        protocolReviewer.setNonEmployeeFlag(nonEmployeeFlag);
        return protocolReviewer;
    }
    
    public void addExemptStudiesCheckListItem(String exemptStudiesCheckListCode) {
        protocolSubmission.getExemptStudiesCheckList().add(createProtocolExemptStudiesCheckListItem(exemptStudiesCheckListCode));
    }
    
    private ProtocolExemptStudiesCheckListItem createProtocolExemptStudiesCheckListItem(String exemptStudiesCheckListCode) {
        ProtocolExemptStudiesCheckListItem chkLstItem = new ProtocolExemptStudiesCheckListItem();
        chkLstItem.setProtocolId(protocolSubmission.getProtocolId());
        chkLstItem.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        chkLstItem.setProtocolNumber(protocolSubmission.getProtocolNumber());
        chkLstItem.setSequenceNumber(protocolSubmission.getSequenceNumber());
        chkLstItem.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        chkLstItem.setExemptStudiesCheckListCode(exemptStudiesCheckListCode);
        return chkLstItem;
    }
    
    public void addExpeditedReviewCheckListItem(String expeditedReviewCheckListCode) {
        protocolSubmission.getExpeditedReviewCheckList().add(createProtocolExpeditedReviewCheckListItem(expeditedReviewCheckListCode));
    }
    
    private ProtocolExpeditedReviewCheckListItem createProtocolExpeditedReviewCheckListItem(String expeditedReviewCheckListCode) {
        ProtocolExpeditedReviewCheckListItem chkLstItem = new ProtocolExpeditedReviewCheckListItem();
        chkLstItem.setProtocolId(protocolSubmission.getProtocolId());
        chkLstItem.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        chkLstItem.setProtocolNumber(protocolSubmission.getProtocolNumber());
        chkLstItem.setSequenceNumber(protocolSubmission.getSequenceNumber());
        chkLstItem.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        chkLstItem.setExpeditedReviewCheckListCode(expeditedReviewCheckListCode);
        return chkLstItem;
    }
    
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
}
