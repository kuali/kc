/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.actions.undo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.*;
import org.kuali.kra.protocol.actions.ProtocolActionBase;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UndoLastActionBean extends ProtocolActionBean implements org.kuali.kra.protocol.actions.undo.UndoLastActionBean, Serializable {

    private static final long serialVersionUID = 801139767436741048L;
    
    private static final String[] NOT_UNDOABLE_ACTIONS = {ProtocolActionType.PROTOCOL_CREATED, ProtocolActionType.SUBMIT_TO_IRB, ProtocolActionType.RENEWAL_CREATED, ProtocolActionType.AMENDMENT_CREATED, ProtocolActionType.WITHDRAWN, ProtocolActionType.APPROVED, ProtocolActionType.ADMINISTRATIVE_CORRECTION, ProtocolActionType.DEFERRED};
    private static final String AMEND = "A";
    private static final String RENEW = "R";
    private static final String FYI = "F";
    
    private String comments;
    private List<ProtocolAction> actionsPerformed;
    private Protocol protocol;
    
    /**
     * Constructs a UndoLastActionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public UndoLastActionBean(ActionHelper actionHelper) {
        super(actionHelper);
    }
    
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
        // TODO : why static. then use class instance to access method ?
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
        boolean amendmentOrRenewal = protocolNumberUpper.contains(AMEND) || protocolNumberUpper.contains(RENEW) || protocolNumberUpper.contains(FYI);
        return ProtocolActionType.APPROVED.equals(action.getProtocolActionTypeCode()) && !amendmentOrRenewal;
    }
    
    private boolean isProtocolDeleted(Protocol protocol) {
        return ProtocolStatus.DELETED.equals(protocol.getProtocolStatusCode());
    }
    
    public boolean canUndoLastAction() {
        ProtocolAction action = getLastPerformedAction();
        if(action != null){
            // filter out protocol merged from renewal/amendment
            if (StringUtils.isBlank(action.getComments()) || !((action.getProtocolActionTypeCode().equals(ProtocolActionType.APPROVED) || action.getProtocolActionTypeCode().equals(ProtocolActionType.APPROVED))
                    && (action.getComments().startsWith("Renewal-") || action.getComments().startsWith("Amendment-") || action.getComments().startsWith("FYI-")))) {
                return isActionUndoable(action.getProtocolActionTypeCode()) || isActionProtocolApproval(action, action.getProtocolNumber()) || isProtocolDeleted(getProtocol());
            }
        }
        return false;
    }
    
    public void refreshActionsPerformed() {
        if(null != this.getProtocol()) {
            this.actionsPerformed = (List)this.getProtocol().getProtocolActions();
        }  
    }

    @Override
    public ProtocolActionBase getLastAction() {
        return this.getLastPerformedAction();
    }
}
