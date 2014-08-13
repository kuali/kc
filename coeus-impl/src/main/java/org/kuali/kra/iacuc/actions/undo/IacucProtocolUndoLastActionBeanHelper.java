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
package org.kuali.kra.iacuc.actions.undo;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBeanHelperBase;

import java.io.Serializable;

public class IacucProtocolUndoLastActionBeanHelper extends UndoLastActionBeanHelperBase implements Serializable {

    private static final long serialVersionUID = 792168534895993037L;

    protected String getApprovedActionTypeCodeHook() {
        return IacucProtocolActionType.IACUC_APPROVED;
    }

    protected String getDeletedProtocolStatusHook() {
        return IacucProtocolStatus.DELETED;
    }

    protected static final String[] notUndoableActions = new String[]{IacucProtocolActionType.IACUC_PROTOCOL_CREATED, 
        IacucProtocolActionType.IACUC_DELETED, IacucProtocolActionType.CORRESPONDENCE_GENERATED, 
        IacucProtocolActionType.SUBMITTED_TO_IACUC, IacucProtocolActionType.AMENDMENT_CREATED, 
        IacucProtocolActionType.RENEWAL_CREATED, IacucProtocolActionType.EXPIRED, 
        IacucProtocolActionType.IACUC_WITHDRAWN, IacucProtocolActionType.ADMINISTRATIVELY_WITHDRAWN, 
        IacucProtocolActionType.ADMINISTRATIVE_CORRECTION};

    protected String[] getNotUndoableActions() {
        return notUndoableActions;
    }

}
