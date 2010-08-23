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
package org.kuali.kra.irb.actions.undo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;

public class UndoLastActionBean implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private static final String[] NOT_UNDOABLE_ACTIONS = {ProtocolActionType.PROTOCOL_CREATED, ProtocolActionType.SUBMIT_TO_IRB, ProtocolActionType.RENEWAL_CREATED, ProtocolActionType.AMENDMENT_CREATED, ProtocolActionType.EXPIRED, ProtocolActionType.WITHDRAWN, ProtocolActionType.APPROVED, ProtocolActionType.ADMINISTRATIVE_CORRECTION, ProtocolActionType.DEFERRED};
    private static final String AMEND = "A";
    private static final String RENEW = "R";
    
    private String comments;
    private List<ProtocolAction> actionsPerformed;
    private Protocol protocol;
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public List<ProtocolAction> getActionsPerformed() {
        return actionsPerformed;
    }

    public void setActionsPerformed(List<ProtocolAction> actionsPerformed) {
        this.actionsPerformed = actionsPerformed;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public static boolean isActionUndoable(String actionTypeCode) {
        for(int i=0; i <NOT_UNDOABLE_ACTIONS.length; i++) {
            if(actionTypeCode.equalsIgnoreCase(NOT_UNDOABLE_ACTIONS[i])) {
                return false;
            }
        }
        return true;
    }
    
    public ProtocolAction getPrevToLastPerformedAction() {
        Collections.sort(actionsPerformed, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return actionsPerformed.size() > 1 ? actionsPerformed.get(1) : null;
    }
    
    public ProtocolAction getLastPerformedAction() {
        Collections.sort(actionsPerformed, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return actionsPerformed.size() > 0 ? actionsPerformed.get(0) : null;
    }
    
    private boolean isActionProtocolApproval(ProtocolAction action, String protocolNumber) {
        String protocolNumberUpper = protocolNumber.toUpperCase();
        boolean amendmentOrRenewal = protocolNumberUpper.contains(AMEND) || protocolNumberUpper.contains(RENEW);
        return ProtocolActionType.APPROVED.equals(action.getProtocolActionTypeCode()) && !amendmentOrRenewal;
    }
    
    private boolean isProtocolDeleted(Protocol protocol) {
        return ProtocolStatus.DELETED.equals(protocol.getProtocolStatusCode());
    }
    
    public boolean canUndoLastAction() {
        ProtocolAction action = getLastPerformedAction();
        if(action != null){
            return isActionUndoable(action.getProtocolActionTypeCode()) || isActionProtocolApproval(action, action.getProtocolNumber()) || isProtocolDeleted(getProtocol());
        }
        return false;
    }
}
