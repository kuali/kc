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
package org.kuali.kra.iacuc.actions.assignCmt;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.Committee;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.rice.krad.service.BusinessObjectService;

public class IacucProtocolAssignCmtServiceImpl implements IacucProtocolAssignCmtService {

    private BusinessObjectService businessObjectService;
    private CommonCommitteeService committeeService;
    private static final String NEXT_ACTION_ID_KEY = "actionId";


    public void assignToCommittee(Protocol protocol, IacucProtocolAssignCmtBean actionBean) throws Exception {
        ProtocolSubmission submission = findSubmission(protocol);
        if (submission != null) {
            
            submission.setSubmissionStatusCode(IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
            setCommittee(submission, actionBean.getCommitteeId());
        }
        
        addNewAction(protocol, actionBean);
        
        getBusinessObjectService().save(protocol);
    }

    public boolean setCommittee(ProtocolSubmission submission, String committeeId) {
        Committee committee = committeeService.getCommitteeById(committeeId);
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
    
    protected void addNewAction(Protocol protocol, IacucProtocolAssignCmtBean actionBean) {
        
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        ProtocolAction newAction = new IacucProtocolAction();
        // deep copy will replace the last action with the new one after save
       // ProtocolAction newAction = (ProtocolAction)ObjectUtils.deepCopy(protocol.getLastProtocolAction());
        newAction.setComments("AssignedToCommittee");
        newAction.setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        newAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setProtocolActionTypeCode(IacucProtocolActionType.NOTIFIED_COMMITTEE);
        newAction.setSubmissionIdFk(lastAction.getSubmissionIdFk());
        newAction.setSubmissionNumber(lastAction.getSubmissionNumber());
        newAction.setProtocolNumber(protocol.getProtocolNumber());
        newAction.setProtocolId(protocol.getProtocolId());
        newAction.setSequenceNumber(protocol.getSequenceNumber());
        protocol.getProtocolActions().add(newAction);

    }
    
    public void setCommitteeService(CommonCommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    
    public CommonCommitteeService getCommitteeService() {
        return committeeService;
    }
    
    protected ProtocolSubmission findSubmission(Protocol protocol) {
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.PENDING) ||
                    StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
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
