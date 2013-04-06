/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.actions.abandon;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.abandon.ProtocolAbandonServiceImplBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;

/**
 * 
 * This class to implement the detail of abandon protocol
 */
public class ProtocolAbandonServiceImpl extends ProtocolAbandonServiceImplBase implements ProtocolAbandonService {

    protected String getActionType() {
        return ProtocolActionType.ABANDON_PROTOCOL;
    }
  
    @Override
    public ProtocolActionBase getNewActionHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol)protocol, null, getActionType());

    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolCorrespondenceHook(String actionType) {
        return new ProtocolGenericCorrespondence(actionType);
    }
}
