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
