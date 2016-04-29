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
package org.kuali.kra.protocol.actions.undo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolSpecialVersion;
import org.kuali.kra.protocol.actions.ProtocolActionBase;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class UndoLastActionBeanHelperBase implements Serializable {

    protected static final String AMEND_COMMENT = ProtocolSpecialVersion.AMENDMENT.getDescription() +  "-";
    protected static final String RENEW_COMMENT = ProtocolSpecialVersion.RENEWAL.getDescription() + "-";
    protected static final String FYI_COMMENT = ProtocolSpecialVersion.FYI.getDescription() + "-";
    
    protected abstract String[] getNotUndoableActions();
    
    protected abstract String getApprovedActionTypeCodeHook();
    
    protected abstract String getDeletedProtocolStatusHook();
    
    public boolean canUndoLastAction(ProtocolBase protocol) {
        ProtocolActionBase action = getLastPerformedAction(protocol.getProtocolActions());
        if(action != null){
            // filter out protocol merged from renewal/amendment/fyi
            if (StringUtils.isBlank(action.getComments()) || !(action.getProtocolActionTypeCode().equals(getApprovedActionTypeCodeHook())
                    && (action.getComments().startsWith(RENEW_COMMENT) || action.getComments().startsWith(AMEND_COMMENT) || action.getComments().startsWith(FYI_COMMENT)))) {
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
        boolean amendmentOrRenewal = protocolNumberUpper.contains(ProtocolSpecialVersion.AMENDMENT.getCode()) || protocolNumberUpper.contains(ProtocolSpecialVersion.RENEWAL.getCode()) || protocolNumberUpper.contains(ProtocolSpecialVersion.FYI.getCode());
        return getApprovedActionTypeCodeHook().equals(action.getProtocolActionTypeCode()) && !amendmentOrRenewal;
    }
    
    protected boolean isProtocolDeleted(ProtocolBase protocol) {
        return getDeletedProtocolStatusHook().equals(protocol.getProtocolStatusCode());
    }
}
