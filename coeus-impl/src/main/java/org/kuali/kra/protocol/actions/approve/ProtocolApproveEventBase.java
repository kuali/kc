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
package org.kuali.kra.protocol.actions.approve;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * Encapsulates the event that the protocol Administrator approves a protocol.
 */
public abstract class ProtocolApproveEventBase extends KcDocumentEventBaseExtension {
        
    private ProtocolApproveBean protocolApproveBean;
    
    /**
     * Constructs a ProtocolApproveEventBase.
     * @param document the document to validate
     * @param protocolApproveBean the bean that keeps the comments and dates
     */
    public ProtocolApproveEventBase(ProtocolDocumentBase document, ProtocolApproveBean protocolApproveBean) {
        super("Approving document " + getDocumentId(document), protocolApproveBean.getErrorPropertyKey(), document);
        
        this.protocolApproveBean = protocolApproveBean;
    }
    
    public ProtocolApproveBean getProtocolApproveBean() {
        return protocolApproveBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProtocolApproveRuleBase getRule() {
        return getNewProtocolApproveRuleInstanceHook();
    }

    protected abstract ProtocolApproveRuleBase getNewProtocolApproveRuleInstanceHook();
    
}
