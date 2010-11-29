/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.genericactions;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * Encapsulates the event that the user performs a generic action.
 */
public class ProtocolGenericActionEvent extends KraDocumentEventBaseExtension {
    
    private ProtocolGenericActionBean protocolGenericActionBean;

    /**
     * Constructs a ProtocolGenericActionEvent.
     * @param document the document to validate
     * @param protocolGenericActionBean the bean that keeps the comments and dates
     */
    public ProtocolGenericActionEvent(ProtocolDocument document, ProtocolGenericActionBean protocolGenericActionBean) {
        super("Performing generic action on document " + getDocumentId(document), protocolGenericActionBean.getErrorPropertyKey(), document);
        
        this.protocolGenericActionBean = protocolGenericActionBean;
    }
    
    public ProtocolGenericActionBean getProtocolGenericActionBean() {
        return protocolGenericActionBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new ProtocolGenericActionRule();
    }

}