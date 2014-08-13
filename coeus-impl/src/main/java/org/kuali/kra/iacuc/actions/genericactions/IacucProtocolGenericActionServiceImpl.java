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
package org.kuali.kra.iacuc.actions.genericactions;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public class IacucProtocolGenericActionServiceImpl extends ProtocolGenericActionServiceImplBase implements IacucProtocolGenericActionService {
    
    
    @Override
    public void disapprove(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_DISAPPROVED, IacucProtocolStatus.DISAPPROVED);
        performDisapprove(protocol);
    }
    
    @Override
    public void expire(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.EXPIRED, IacucProtocolStatus.EXPIRED);
    }
    
    @Override
    public ProtocolDocumentBase returnForSMR(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED, IacucProtocolStatus.MINOR_REVISIONS_REQUIRED);
        return getReturnedVersionedDocument(protocol);
    }
    
    @Override
    public ProtocolDocumentBase returnForSRR(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED, IacucProtocolStatus.MAJOR_REVISIONS_REQUIRED);
        return getReturnedVersionedDocument(protocol);
    }
    
    @Override
    public ProtocolDocumentBase returnToPI(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.RETURNED_TO_PI, IacucProtocolStatus.RETURN_TO_PI);
        return getReturnedVersionedDocument(protocol);
    }    
    
    @Override
    public void suspend(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.SUSPENDED, IacucProtocolStatus.SUSPENDED);
    }
        
    @Override
    public void terminate(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.TERMINATED, IacucProtocolStatus.TERMINATED);
    }
    
    @Override
    public void iacucAcknowledgement(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_ACKNOWLEDGEMENT, protocol.getProtocolStatusCode());
    }   

    @Override
    public void iacucHold(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.HOLD, IacucProtocolStatus.ACTIVE_ON_HOLD);
    }     
    
    @Override
    public void iacucLiftHold(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        //find the last lift notification protocol action.
        ProtocolActionBase selectedPa = null;
        for (ProtocolActionBase pa : protocol.getProtocolActions()) {
            if (StringUtils.equalsIgnoreCase(pa.getProtocolActionTypeCode(), IacucProtocolActionType.HOLD)
                    && (selectedPa == null || pa.getSequenceNumber() > selectedPa.getSequenceNumber())) {
                selectedPa = pa;
            }
            
        }
        performGenericAction(protocol, actionBean, IacucProtocolActionType.LIFT_HOLD, selectedPa.getPrevProtocolStatusCode());
    }    

    @Override
    public void iacucDeactivate(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.DEACTIVATED, IacucProtocolStatus.DEACTIVATED);
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String protocolActionType) {
        return new IacucProtocolAction( (IacucProtocol) protocol, (IacucProtocolSubmission) submission, protocolActionType);
    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondenceHook(String protocolActionType) {
        return new IacucProtocolActionsCorrespondence(protocolActionType);
    }

    @Override
    protected String getProtocolPendingInProgressStatusCodeHook() {
        return IacucProtocolStatus.IN_PROGRESS;
    }

    @Override
    protected String getProtocolSubmissionStatusRejectedInRoutingCodeHook() {
        return IacucProtocolSubmissionStatus.REJECTED_IN_ROUTING;
    }

    @Override
    protected ProtocolActionBase getNewDisapprovedInRoutingProtocolActionInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAction( (IacucProtocol) protocol, (IacucProtocolSubmission) protocol.getProtocolSubmission(), IacucProtocolActionType.REJECTED_IN_ROUTING);
    }

    @Override
    protected String getDisapprovedProtocolStatusCodeHook() {
        return IacucProtocolStatus.DISAPPROVED;
    }

    @Override
    protected String getRecallProtocolActionTypeCodeHook() {
        //not supported action type
        return null;
    }
    
}
