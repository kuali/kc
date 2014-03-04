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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.undo.UndoLastActionServiceImplBase;

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
            Map<String, String> fieldValues = new HashMap<String, String>();
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
