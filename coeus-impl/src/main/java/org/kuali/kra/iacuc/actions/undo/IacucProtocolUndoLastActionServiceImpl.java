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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeBatchCorrespondenceDetail;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.undo.UndoLastActionServiceImplBase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolUndoLastActionServiceImpl extends UndoLastActionServiceImplBase implements IacucProtocolUndoLastActionService {

    @Override
    protected String getAmendmentInProgressStatusHook() {
        return IacucProtocolStatus.AMENDMENT_IN_PROGRESS;
    }

    @Override
    protected String getInProgressStatusHook() {
        return IacucProtocolStatus.IN_PROGRESS;
    }

    @Override
    protected String getRenewalInProgressStatusHook() {
        return IacucProtocolStatus.RENEWAL_IN_PROGRESS;
    }

    @Override
    protected String getFyiInProgressStatusHook() {
        return IacucProtocolStatus.FYI_IN_PROGRESS;
    }

    @Override
    protected boolean isApprovedActionTypeCode(String actionTypeCode) {
        return StringUtils.equals(actionTypeCode, IacucProtocolActionType.IACUC_APPROVED) 
            || StringUtils.equals(actionTypeCode, IacucProtocolActionType.DESIGNATED_REVIEW_APPROVAL);
    }

    @Override
    protected boolean isRevisionsRequiredActionTypeCode(String actionTypeCode) {
        return StringUtils.equals(actionTypeCode, IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED) 
            || StringUtils.equals(actionTypeCode, IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED);
    }

    @Override
    protected void removeAttachedCorrespondences(ProtocolActionBase protocolAction) {
        if(protocolAction != null) {
           getBusinessObjectService().deleteMatching(IacucCommitteeBatchCorrespondenceDetail.class, Collections.singletonMap("protocolActionId", protocolAction.getProtocolActionId().toString()));

            final Map<String, String> fieldValues = new HashMap<>();
            fieldValues.put("actionIdFk", protocolAction.getProtocolActionId().toString());
            fieldValues.put("protocolNumber", protocolAction.getProtocolNumber());
            fieldValues.put("sequenceNumber", protocolAction.getSequenceNumber().toString());
            
            getBusinessObjectService().deleteMatching(IacucProtocolCorrespondence.class, fieldValues);
        }
    }
    
    protected ProtocolBase getOldProtocol(ProtocolBase protocol) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocol.getProtocolNumber());
        fieldValues.put("sequenceNumber", protocol.getSequenceNumber() - 1);
        return ((List<IacucProtocol>) getBusinessObjectService().findMatching(IacucProtocol.class, fieldValues)).get(0);
   }
    

}
