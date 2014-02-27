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
package org.kuali.kra.iacuc.actions.request;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.iacuc.IacucProtocolDocument;

/**
 * This event is generated whenever a user makes a request to 
 * close the protocol, suspend it, close enrollment, re-open enrollment,
 * or request data analysis.
 */
@SuppressWarnings("unchecked")
public class IacucProtocolRequestEvent<T extends KcBusinessRule> extends KcDocumentEventBaseExtension {

    private IacucProtocolRequestBean requestBean;
    private String propertyKey;

    public IacucProtocolRequestEvent(IacucProtocolDocument document, String propertyKey, IacucProtocolRequestBean requestBean) {
        super("Iacuc Protocol Request", "", document);
        this.propertyKey = propertyKey;
        this.requestBean = requestBean;
    }
    
    public IacucProtocolDocument getProtocolDocument() {
        return (IacucProtocolDocument) getDocument();
    }
    
    public String getPropertyKey() {
        return propertyKey;
    }
    
    public IacucProtocolRequestBean getRequestBean() {
        return requestBean;
    }

    @Override
    public KcBusinessRule getRule() {
        return new IacucProtocolRequestRule();
    }
}
