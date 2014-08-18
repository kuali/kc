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
package org.kuali.kra.protocol.actions.undo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class UndoLastActionBeanHelperBase implements Serializable {
    
    protected static final String AMEND = "A";
    protected static final String RENEW = "R";
    protected static final String AMEND_COMMENT = "Amendment-";
    protected static final String RENEW_COMMENT = "Renewal-";
    
    protected abstract String[] getNotUndoableActions();
    
    protected abstract String getApprovedActionTypeCodeHook();
    
    protected abstract String getDeletedProtocolStatusHook();
    
    public boolean canUndoLastAction(ProtocolBase protocol) {
        ProtocolActionBase action = getLastPerformedAction(protocol.getProtocolActions());
        if(action != null){
            // filter out protocol merged from renewal/amendment
            if (StringUtils.isBlank(action.getComments()) || !(action.getProtocolActionTypeCode().equals(getApprovedActionTypeCodeHook())
                    && (action.getComments().startsWith(RENEW_COMMENT) || action.getComments().startsWith(AMEND_COMMENT)))) {
                return isActionUndoable(action.getProtocolActionTypeCode()) || isActionProtocolApproval(action, action.getProtocolNumber()) || isProtocolDeleted(protocol);
            }
        }
        return false;
    }
    
    public ProtocolActionBase getLastPerformedAction(List<ProtocolActionBase> actionsPerformed) {
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
    
    protected void sortActions(List<ProtocolActionBase> actionsPerformed) {
        Collections.sort(actionsPerformed, new Comparator<ProtocolActionBase>() {
            public int compare(ProtocolActionBase action1, ProtocolActionBase action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
    }
    
    protected boolean isActionProtocolApproval(ProtocolActionBase action, String protocolNumber) {
        String protocolNumberUpper = protocolNumber.toUpperCase();
        boolean amendmentOrRenewal = protocolNumberUpper.contains(AMEND) || protocolNumberUpper.contains(RENEW);
        return getApprovedActionTypeCodeHook().equals(action.getProtocolActionTypeCode()) && !amendmentOrRenewal;
    }
    
    protected boolean isProtocolDeleted(ProtocolBase protocol) {
        return getDeletedProtocolStatusHook().equals(protocol.getProtocolStatusCode());
    }
}
