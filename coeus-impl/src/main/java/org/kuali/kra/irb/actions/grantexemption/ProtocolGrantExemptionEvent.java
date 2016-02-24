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
package org.kuali.kra.irb.actions.grantexemption;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.irb.ProtocolDocument;

/**
 * Encapsulates the event that the IRB Administrator grants an exemption on a protocol.
 */
public class ProtocolGrantExemptionEvent extends KcDocumentEventBaseExtension {
        
    private ProtocolGrantExemptionBean protocolGrantExemptionBean;
    
    /**
     * Constructs a ProtocolApproveEvent.
     * @param document the document to validate
     * @param protocolGrantExemptionBean the bean that keeps the comments and dates
     */
    public ProtocolGrantExemptionEvent(ProtocolDocument document, ProtocolGrantExemptionBean protocolGrantExemptionBean) {
        super("Granting exemption on document " + getDocumentId(document), protocolGrantExemptionBean.getErrorPropertyKey(), document);
        
        this.protocolGrantExemptionBean = protocolGrantExemptionBean;
    }
    
    public ProtocolGrantExemptionBean getProtocolGrantExemptionBean() {
        return protocolGrantExemptionBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new ProtocolGrantExemptionRule();
    }
    
}
