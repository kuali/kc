/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.protocol.actions.ProtocolAction;

/**
 * 
 * This class manages all the attributes needed to maintain a protocol action.
 */
public class IacucProtocolAction extends ProtocolAction {
    
    private static final long serialVersionUID = -4895673225969021493L;

    private boolean createdSubmission;
    
    public IacucProtocolAction() {
    }

    public IacucProtocolAction(IacucProtocol protocol, IacucProtocolSubmission protocolSubmission, String protocolActionTypeCode) {
        super(protocol, protocolSubmission, protocolActionTypeCode);
    }
        
    public IacucProtocolAction(IacucProtocol protocol, String protocolActionTypeCode) {
        super(protocol, protocolActionTypeCode);
    }

    protected String getCoeusModule() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    public boolean isCreatedSubmission() {
        return createdSubmission;
    }

    public void setCreatedSubmission(boolean createdSubmission) {
        this.createdSubmission = createdSubmission;
    }
    
    
}
