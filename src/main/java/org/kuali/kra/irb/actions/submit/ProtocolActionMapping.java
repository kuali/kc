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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.kuali.kra.drools.brms.FactBean;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolActionMapping implements FactBean {
    
    private BusinessObjectService businessObjectService;
    
    private ProtocolDao dao;
    
    String submissionStatusCode;
    
    String submissionTypeCode;
    
    String protocolReviewTypeCode;
    
    String actionTypeCode;
    
    String protocolStatusCode;
    
    String scheduleId;
    
    Protocol protocol;
    
    Integer submissionNumber;

    boolean allowed = false;

    public ProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super();
        this.actionTypeCode = actionTypeCode;
        this.submissionStatusCode = submissionStatusCode;        
        this.submissionTypeCode = submissionTypeCode;
        this.protocolReviewTypeCode = protocolReviewTypeCode;
        this.protocolStatusCode = protocolStatusCode;
        this.scheduleId = scheduleId;
        this.submissionNumber = submissionNumber;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDao(ProtocolDao dao) {
        this.dao = dao;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    @SuppressWarnings("unchecked")
    public String getProtocolSubmissionScheduleId() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
        positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
        positiveFieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
        
        List<ProtocolSubmission> list = (List<ProtocolSubmission>) businessObjectService.findMatching(ProtocolSubmission.class, positiveFieldValues);
        String retVal = null;
        if(!list.isEmpty()) {
            ProtocolSubmission ps = list.get(0);
            retVal = ps.getScheduleId();
        }
        return retVal;
    }
    
    public boolean getMinutesCount() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocol.getProtocolNumber());
        fieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
        int count = businessObjectService.countMatching(CommitteeScheduleMinutes.class, fieldValues);
        boolean flag = false;
        if(count > 0)
            flag = true;
        return flag;
    }
        
    public boolean getSubmissionCount() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
        positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<String> ors = new ArrayList<String>();
        ors.add("100");
        ors.add("101"); 
        ors.add("102");
        positiveFieldValues.put("submissionStatusCode", ors);
        
        Map<String, Object> negativeFieldValues = new HashMap<String, Object>();        
        negativeFieldValues.put("submissionTypeCode", submissionTypeCode);
        
        int count = businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues);
        boolean flag = true;
        if(count > 0)
            flag = false;
        return flag;
    }
    
    public boolean getSubmissionCountCond2() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
        positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<String> ors = new ArrayList<String>();
        ors.add("100");
        ors.add("101"); 
        ors.add("102");
        positiveFieldValues.put("submissionStatusCode", ors);
        
        int count = businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues);
        boolean flag = true;
        if(count > 0)
            flag = false;
        return flag;
    }
    
    public boolean getSubmissionCountCond3() {
        int count = dao.getProtocolSubmissionCountFromProtocol(protocol.getProtocolNumber());
        boolean flag = true;
        if(count > 0)
            flag = false;
        return flag;
    }
    
    public boolean getSubmissionCountCond4() {
        
        boolean retVal = true;
        
        submissionTypeCode = "109";
        retVal &= submissionCountHelper();
        
        submissionTypeCode = "110";
        retVal &= submissionCountHelper();
        
        submissionTypeCode = "111";
        retVal &= submissionCountHelper();
        
        submissionTypeCode = "108";
        retVal &= submissionCountHelper();
        
        submissionTypeCode = "113";
        retVal &= submissionCountHelper();
        
        submissionTypeCode = "114";
        retVal &= submissionCountHelper();
        
        return retVal;
    }
    
    private boolean submissionCountHelper() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
        positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<String> ors = new ArrayList<String>();
        ors.add("100");
        ors.add("101"); 
        ors.add("102");
        positiveFieldValues.put("submissionStatusCode", ors);
        
        positiveFieldValues.put("submissionTypeCode", submissionTypeCode);
        
        int count = businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues);
        boolean flag = true;
        if(count > 0)
            flag = false;
        return flag;
    }
    /**
     * select count(protocol_number)
                                    into li_SubmissionCount
                                    from osp$protocol_submission
                                    where protocol_number = as_protocol_number and
                                    sequence_number = as_sequence_number and
                                    submission_status_code in (100, 101, 102) and
                                    submission_number = (select max(a.submission_number)
                                                 from osp$protocol_submission a
                                                 where osp$protocol_submission.protocol_number = a.protocol_number and
                                                 osp$protocol_submission.sequence_number = a.sequence_number);
     * 
     * @return
     */
    
    @SuppressWarnings("unchecked")
    public boolean getSubmissionCountCond5() {                
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
        positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<String> ors = new ArrayList<String>();
        ors.add("100");
        ors.add("101"); 
        ors.add("102");
        positiveFieldValues.put("submissionStatusCode", ors);
        
        List<ProtocolSubmission> list = (List<ProtocolSubmission>)businessObjectService.findMatching(ProtocolSubmission.class, positiveFieldValues);
  
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
       
        for(ProtocolSubmission ps : list) {
            Integer key = ps.getSubmissionNumber();
            Integer val = map.get(key);
            if(null != val) {
                map.put(key, ++val);
            } else {
                map.put(key, 1);
            }
        }
        Integer count = null;
        if(map.isEmpty())
            count = 0;      
        else
            count = map.get(map.lastKey());
        
        boolean flag = true;
        if(count > 0)
            flag = false;
        return flag;
    }
    
    /**
     * This method finds number of reviewers tied to protocol submission. Implementation in lieu of following query 
     *           SELECT count(OSP$PROTOCOL_REVIEWERS.PERSON_ID)
     *           INTO li_PersonCnt
     *           FROM OSP$PROTOCOL_REVIEWERS
     *           WHERE OSP$PROTOCOL_REVIEWERS.PROTOCOL_NUMBER = AS_PROTOCOL_NUMBER
     *           AND OSP$PROTOCOL_REVIEWERS.SUBMISSION_NUMBER = AS_SUBMISSION_NUMBER; 
     * @return
     */
    public boolean getProtocolReviewerCountCond1() {       
        int count = protocol.getProtocolSubmission().getProtocolReviewers().size();
        boolean flag = true;
        if(count == 0)
            flag = false;
        return flag;
    }
    
    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getSubmissionStatusCode() {
        return submissionStatusCode;
    }

    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    
    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }    
    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
