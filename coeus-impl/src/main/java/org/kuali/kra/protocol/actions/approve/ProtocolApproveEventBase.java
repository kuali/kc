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
