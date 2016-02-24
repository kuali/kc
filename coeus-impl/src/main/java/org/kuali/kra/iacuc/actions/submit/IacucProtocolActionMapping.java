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
package org.kuali.kra.iacuc.actions.submit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.submit.ProtocolActionMappingBase;

/*
 * This class is for the condition attributes of of the protocol action.
 * i.e., the condition of protocol status, submissionstatus, action type code etc.
 */
public class IacucProtocolActionMapping extends ProtocolActionMappingBase {
    
    private static final Map<String, String> ACTION_TYPE_SUBMISSION_TYPE_MAP;
    static {
        final Map<String, String> codeMap = new HashMap<String, String>();              
        ACTION_TYPE_SUBMISSION_TYPE_MAP = Collections.unmodifiableMap(codeMap);
    }


    public IacucProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super(actionTypeCode, submissionStatusCode, submissionTypeCode, protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
    }
 
    
    /**
     * Check if there are any pending submissions for this protocol
     *  whose submission type is not the matching submission type in ACTION_TYPE_SUBMISSION_TYPE_MAP.
     */
    public boolean getSubmissionCount() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
 
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        
        Map<String, Object> negativeFieldValues = new HashMap<String, Object>();        
        negativeFieldValues.put("submissionTypeCode", ACTION_TYPE_SUBMISSION_TYPE_MAP.get(actionTypeCode));
        
        return businessObjectService.countMatching(IacucProtocolSubmission.class, positiveFieldValues, negativeFieldValues) == 0;    
    }
    
    /**
     * 
     * This method Checks if there are any pending submissions for this protocol
     * @return
     */
    public boolean getSubmissionCountCond2() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        
        return businessObjectService.countMatching(IacucProtocolSubmission.class, positiveFieldValues) == 0;
    }
    
    
//TODO: IACUC work needed here to determine additional request types    
    /**
     * 
     * This method check to see if there is pending submission with one of the following submission type
     * 107 - Request to deactivate
     * 108 - Request to lift hold
     * 109 --Request to close (should be here eventually...)
     * 110 --Request For Suspension   (ditto)
     * 108 --Request for Termination  (ditto)
     * @return
     */
    public boolean getSubmissionCountCond4() {
        
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        positiveFieldValues.put("submissionTypeCode", Arrays.asList(new String[] {
                IacucProtocolSubmissionType.REQUEST_TO_DEACTIVATE, IacucProtocolSubmissionType.REQUEST_TO_LIFT_HOLD}));
        
        return businessObjectService.countMatching(IacucProtocolSubmission.class, positiveFieldValues) == 0;

    }
        
    
    /*
     * Coeus called submission status of 100/101/102 as pending submission
     */
    private List<String> getPendingSubmissionStatusCodes() {
        List<String> submissionStatusCodes = new ArrayList<String>();
        submissionStatusCodes.add(IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionStatusCodes.add(IacucProtocolSubmissionStatus.IN_AGENDA); 
        submissionStatusCodes.add(IacucProtocolSubmissionStatus.PENDING);
        return submissionStatusCodes;

    }
    
   /**
     * check if there are any other pending submissions.
     * Basically, check the matching protocol submission with the highest submission# does not have
     * status of (100,101,102)
     */
    public boolean getSubmissionCountCond5() {                
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        List<IacucProtocolSubmission> submissions = (List<IacucProtocolSubmission>)businessObjectService.findMatchingOrderBy(IacucProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return submissions.isEmpty() || !getPendingSubmissionStatusCodes().contains(submissions.get(0).getSubmissionStatusCode());
        
    }
    

    /*
     * look for any submissions of the type "Request xxxx"
     */
    public boolean isAnySubmissionRequests() {
        List <String> submissionTypes = Arrays.asList(new String[] {IacucProtocolSubmissionType.REQUEST_SUSPEND,
                                                                    IacucProtocolSubmissionType.REQUEST_TO_LIFT_HOLD,
                                                                    IacucProtocolSubmissionType.REQUEST_TO_DEACTIVATE});
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", IacucProtocolSubmissionStatus.PENDING);
        positiveFieldValues.put("submissionTypeCode", submissionTypes);
        List<IacucProtocolSubmission> submissions = (List<IacucProtocolSubmission>)businessObjectService.findMatchingOrderBy(IacucProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return submissions.size() > 0;
    }

    /**
     * 
     * This method Check if protocol has a submission which is in statuscode (100,101,102, 201, 202)  
     * @return
     */
    public boolean getSubmissionCountForWithdraw() {              
        List <String> statusCodes = Arrays.asList(new String[] {"101","102","103"});
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        List<IacucProtocolSubmission> submissions = (List<IacucProtocolSubmission>)businessObjectService.findMatchingOrderBy(IacucProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return !submissions.isEmpty() && statusCodes.contains(submissions.get(0).getSubmissionStatusCode());
        
    }

    public String toString() {
        return "IacucProtocolActionMapping = (submissionStatusCode = " + submissionStatusCode +
                                              ", submissionTypeCode = " + submissionTypeCode +
                                              ", protocolReviewTypeCode = " + protocolReviewTypeCode + 
                                              ", actionTypeCode = " + actionTypeCode +
                                              ", protocolStatusCode = " + protocolStatusCode +
                                              ", scheduleId = " + scheduleId +
                                              ", protocol id = " + protocol.getProtocolId() +
                                              ", submissionNumber = " + submissionNumber +
                                              ", allowed = " + allowed + ")";
    }
    
    
    
    // Check if submission status is "Submitted to Committee" if committee selected, or "Pending" if committee not selected.
    public boolean getSubmissionStatusForAdminAction() {
        boolean retVal;
        if(StringUtils.isNotBlank(this.protocol.getProtocolSubmission().getCommitteeId())) {
            retVal = StringUtils.equals(this.submissionStatusCode, IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) ||
                     StringUtils.equals(this.submissionStatusCode, IacucProtocolSubmissionStatus.IN_AGENDA) ||
                     StringUtils.equals(this.submissionStatusCode, IacucProtocolSubmissionStatus.TABLED);
        }
        else {
            retVal = StringUtils.equals(this.submissionStatusCode, IacucProtocolSubmissionStatus.PENDING);
        }
        
        return retVal;
    }


    @Override
    protected Class<? extends CommitteeScheduleMinuteBase> getCommitteeScheduleMinuteBOClassHook() {
        return IacucCommitteeScheduleMinute.class;
    }
    
    
}
