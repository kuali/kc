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
package org.kuali.kra.protocol.actions.undo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;

public abstract class UndoLastActionBeanHelper implements Serializable {
    
    protected static final String AMEND = "A";
    protected static final String RENEW = "R";
    protected static final String AMEND_COMMENT = "Amendment-";
    protected static final String RENEW_COMMENT = "Renewal-";
    
    protected abstract String[] getNotUndoableActions();
    
    protected abstract String getApprovedActionTypeCodeHook();
    
    protected abstract String getDeletedProtocolStatusHook();
    
    public boolean canUndoLastAction(Protocol protocol) {
        ProtocolAction action = getLastPerformedAction(protocol.getProtocolActions());
        if(action != null){
            // filter out protocol merged from renewal/amendment
            if (StringUtils.isBlank(action.getComments()) || !(action.getProtocolActionTypeCode().equals(getApprovedActionTypeCodeHook())
                    && (action.getComments().startsWith(RENEW_COMMENT) || action.getComments().startsWith(AMEND_COMMENT)))) {
                return isActionUndoable(action.getProtocolActionTypeCode()) || isActionProtocolApproval(action, action.getProtocolNumber()) || isProtocolDeleted(protocol);
            }
        }
        return false;
    }
    
    public ProtocolAction getLastPerformedAction(List<ProtocolAction> actionsPerformed) {
        sortActions(actionsPerformed);
        return actionsPerformed.size() > 0 ? actionsPerformed.get(0) : null;
    }    

    protected boolean isActionUndoable(String actionTypeCode) {
        for(int i=0; i < getNotUndoableActions().length; i++) {
            if(actionTypeCode.equalsIgnoreCase(getNotUndoableActions()[i])) {
                return false;
            }
        }
        return true;
    }
    
    protected ProtocolAction getPrevToLastPerformedAction(List<ProtocolAction> actionsPerformed) {
        sortActions(actionsPerformed);
        return actionsPerformed.size() > 1 ? actionsPerformed.get(1) : null;
    }
    
    protected void sortActions(List<ProtocolAction> actionsPerformed) {
        Collections.sort(actionsPerformed, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
    }
    
    protected boolean isActionProtocolApproval(ProtocolAction action, String protocolNumber) {
        String protocolNumberUpper = protocolNumber.toUpperCase();
        boolean amendmentOrRenewal = protocolNumberUpper.contains(AMEND) || protocolNumberUpper.contains(RENEW);
        return getApprovedActionTypeCodeHook().equals(action.getProtocolActionTypeCode()) && !amendmentOrRenewal;
    }
    
    protected boolean isProtocolDeleted(Protocol protocol) {
        return getDeletedProtocolStatusHook().equals(protocol.getProtocolStatusCode());
    }
}
