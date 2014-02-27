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