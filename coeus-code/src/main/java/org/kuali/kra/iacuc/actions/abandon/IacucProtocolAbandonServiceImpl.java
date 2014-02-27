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
package org.kuali.kra.iacuc.actions.abandon;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.abandon.ProtocolAbandonServiceImplBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;

public class IacucProtocolAbandonServiceImpl extends ProtocolAbandonServiceImplBase implements IacucProtocolAbandonService {

    protected String getActionType() {
        return IacucProtocolActionType.IACUC_ABANDON;
    }
  
    @Override
    public ProtocolActionBase getNewActionHook(ProtocolBase protocol) {
        return new IacucProtocolAction((IacucProtocol)protocol, null, getActionType());

    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolCorrespondenceHook(String actionType) {
        return new IacucProtocolActionsCorrespondence(actionType);
    }

}
