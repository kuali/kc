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
package org.kuali.kra.iacuc.actions.assignCmt;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Timestamp;

public class IacucProtocolAssignCmtServiceImpl implements IacucProtocolAssignCmtService {

    private BusinessObjectService businessObjectService;
    private CommitteeServiceBase committeeService;
    private static final String NEXT_ACTION_ID_KEY = "actionId";


    public void assignToCommittee(ProtocolBase protocol, IacucProtocolAssignCmtBean actionBean) throws Exception {
        ProtocolSubmissionBase submission = findSubmission(protocol);
        String prevSubmissionStatusCode = null;
        if (submission != null) {
            prevSubmissionStatusCode = submission.getSubmissionStatusCode();
            submission.setSubmissionStatusCode(IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
            setCommittee(submission, actionBean.getCommitteeId());
        }
        
        addNewAction(protocol, actionBean, prevSubmissionStatusCode);
        
        getBusinessObjectService().save(protocol);
    }

    public boolean setCommittee(ProtocolSubmissionBase submission, String committeeId) {
        CommitteeBase committee = committeeService.getCommitteeById(committeeId);
        if (committee == null) {
            submission.setCommitteeId(null);
            submission.setCommitteeIdFk(null);
            submission.setCommittee(null);
            return false;
        }
        else {
            submission.setCommitteeId(committee.getCommitteeId());
            submission.setCommitteeIdFk(committee.getId());
            submission.setCommittee(committee);
            return true;
        }
    }
    
    protected void addNewAction(ProtocolBase protocol, IacucProtocolAssignCmtBean actionBean, String prevSubmissionStatusCode) {
        
        ProtocolActionBase lastAction = protocol.getLastProtocolAction();
        ProtocolActionBase newAction = new IacucProtocolAction();
        // deep copy will replace the last action with the new one after save
        newAction.setComments("AssignedToCommittee");
        newAction.setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        newAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setProtocolActionTypeCode(IacucProtocolActionType.ASSIGNED_TO_COMMITTEE);
        ProtocolSubmissionBase submission = protocol.getProtocolSubmission();
        if(submission != null) {
            newAction.setSubmissionIdFk(submission.getSubmissionId());
            newAction.setSubmissionNumber(submission.getSubmissionNumber());
        }
        newAction.setProtocolNumber(protocol.getProtocolNumber());
        newAction.setProtocolId(protocol.getProtocolId());
        newAction.setSequenceNumber(protocol.getSequenceNumber());
        newAction.setPrevSubmissionStatusCode(prevSubmissionStatusCode);
        protocol.getProtocolActions().add(newAction);

    }
    
    public String getAssignedCommitteeId(ProtocolBase protocol) {
        String retVal = null;
        ProtocolSubmissionBase submission = findSubmission(protocol);
        if (submission != null && 
            (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) ||
             (StringUtils.equals(protocol.getProtocolStatusCode(), IacucProtocolStatus.TABLED) &&
              StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.TABLED)))) {
            
            retVal = submission.getCommitteeId();
        }
        return retVal;
    }
    
    public String getAssignedScheduleId(ProtocolBase protocol) {
        String retVal = null;
        ProtocolSubmissionBase submission = findSubmission(protocol);
        if (submission != null && StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)  ||
            (StringUtils.equals(protocol.getProtocolStatusCode(), IacucProtocolStatus.TABLED) &&
             StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.TABLED))) {
            
            retVal = submission.getScheduleId();
        }
        return retVal;
    }
    
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
    }
    
    public CommitteeServiceBase getCommitteeService() {
        return committeeService;
    }
    
    public ProtocolSubmissionBase getLastSubmission(ProtocolBase protocol) {
        return findSubmission(protocol);
    }
    
    
    protected ProtocolSubmissionBase findSubmission(ProtocolBase protocol) {
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmissionBase protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.PENDING) || 
                StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) || 
                (StringUtils.equals(protocol.getProtocolStatusCode(), IacucProtocolStatus.TABLED) &&
                  StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.TABLED))) {
                protocolSubmission = submission;
            }
        }
        return protocolSubmission;

    }
    
     public BusinessObjectService getBusinessObjectService() {
         return businessObjectService;
     }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
     
     
     

}
