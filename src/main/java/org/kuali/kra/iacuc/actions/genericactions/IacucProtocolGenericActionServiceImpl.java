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
package org.kuali.kra.iacuc.actions.genericactions;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondence;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;

/**
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public class IacucProtocolGenericActionServiceImpl extends ProtocolGenericActionServiceImpl implements IacucProtocolGenericActionService {
    
    
    /**{@inheritDoc}**/
    public void disapprove(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_DISAPPROVED, IacucProtocolStatus.DISAPPROVED);
        performDisapprove(protocol);
    }
    
    /**{@inheritDoc}**/
    public void expire(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.EXPIRED, IacucProtocolStatus.EXPIRED);
    }
    
    /**{@inheritDoc}**/
    public ProtocolDocument returnForSMR(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED, IacucProtocolStatus.MINOR_REVISIONS_REQUIRED);
        return getReturnedVersionedDocument(protocol);
    }
    
    /**{@inheritDoc}**/
    public ProtocolDocument returnForSRR(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED, IacucProtocolStatus.MAJOR_REVISIONS_REQUIRED);
        return getReturnedVersionedDocument(protocol);
    }
    
    /**{@inheritDoc}**/
    public ProtocolDocument returnToPI(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.RETURNED_TO_PI, IacucProtocolStatus.RETURN_TO_PI);
        return getReturnedVersionedDocument(protocol);
    }    
    
    /**{@inheritDoc}**/
    public void suspend(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.SUSPENDED, IacucProtocolStatus.SUSPENDED);
    }
        
    /**{@inheritDoc}**/
    public void terminate(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.TERMINATED, IacucProtocolStatus.TERMINATED);
    }
    
    /**{@inheritDoc}**/
    public void iacucAcknowledgement(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.IACUC_ACKNOWLEDGEMENT, protocol.getProtocolStatusCode());
    }   

    /**{@inheritDoc}**/
    public void iacucHold(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.HOLD, IacucProtocolStatus.ACTIVE_ON_HOLD);
    }     
    
    /**{@inheritDoc}**/
    public void iacucLiftHold(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        //find the last lift notification protocol action.
        ProtocolAction selectedPa = null;
        for (ProtocolAction pa : protocol.getProtocolActions()) {
            System.err.println("pa.getProtocolActionTypeCode(): " + pa.getProtocolActionTypeCode());
            if (StringUtils.equalsIgnoreCase(pa.getProtocolActionTypeCode(), IacucProtocolActionType.HOLD) 
                    && (selectedPa == null || pa.getSequenceNumber() > selectedPa.getSequenceNumber())) {
                selectedPa = pa;
            }
            
        }
        performGenericAction(protocol, actionBean, IacucProtocolActionType.LIFT_HOLD, selectedPa.getPrevProtocolStatusCode());
    }    

    /**{@inheritDoc}**/
    public void iacucDeactivate(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, IacucProtocolActionType.DEACTIVATED, IacucProtocolStatus.DEACTIVATED);
    }

    @Override
    protected ProtocolAction getNewProtocolActionInstanceHook(Protocol protocol, ProtocolSubmission submission, String protocolActionType) {
        return new IacucProtocolAction( (IacucProtocol) protocol, (IacucProtocolSubmission) submission, protocolActionType);
    }

    @Override
    protected ProtocolActionsCorrespondence getNewProtocolActionsCorrespondenceHook(String protocolActionType) {
        return new IacucProtocolActionsCorrespondence(protocolActionType);
    }
    
}