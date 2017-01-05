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
        return IacucProtocolSubmissionStatus.RETURNED_IN_ROUTING;
    }

    @Override
    protected ProtocolActionBase getNewDisapprovedInRoutingProtocolActionInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAction( (IacucProtocol) protocol, (IacucProtocolSubmission) protocol.getProtocolSubmission(), IacucProtocolActionType.RETURNED_IN_ROUTING);
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
