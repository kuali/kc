/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.request;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * This event is generated whenever a user makes a request to 
 * close the protocol, suspend it, close enrollment, re-open enrollment,
 * or request data analysis.
 */
@SuppressWarnings("unchecked")
public class ProtocolRequestEvent<T extends BusinessRuleInterface> extends KraDocumentEventBaseExtension {

    private ProtocolRequestBean requestBean;
    private String propertyKey;

    public ProtocolRequestEvent(ProtocolDocument document, String propertyKey, ProtocolRequestBean requestBean) {
        super("Protocol Request", "", document);
        this.propertyKey = propertyKey;
        this.requestBean = requestBean;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyKey() {
        return propertyKey;
    }
    
    public ProtocolRequestBean getRequestBean() {
        return requestBean;
    }

    @Override
    public BusinessRuleInterface getRule() {
        return new ProtocolRequestRule();
    }
}
