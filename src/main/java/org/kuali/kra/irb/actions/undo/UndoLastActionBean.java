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

import org.kuali.kra.irb.actions.ProtocolActionType;

public class UndoLastActionBean implements Serializable {
    private static final String[] NOT_UNDOABLE_ACTIONS = {ProtocolActionType.PROTOCOL_CREATED, ProtocolActionType.SUBMIT_TO_IRB, ProtocolActionType.RENEWAL_CREATED, ProtocolActionType.AMENDMENT_CREATED, ProtocolActionType.EXPIRED, ProtocolActionType.WITHDRAWN, ProtocolActionType.APPROVED, ProtocolActionType.ADMINISTRATIVE_CORRECTION};
    
    private String comments;
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public static boolean isActionUndoable(String actionTypeCode) {
        for(int i=0; i <NOT_UNDOABLE_ACTIONS.length; i++) {
            if(actionTypeCode.equalsIgnoreCase(NOT_UNDOABLE_ACTIONS[i])) {
                return false;
            }
        }
        return true;
    }
}
