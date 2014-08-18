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
package org.kuali.kra.protocol.actions.genericactions;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * Encapsulates the event that the user performs a generic action.
 */
public abstract class ProtocolGenericActionEventBase extends KcDocumentEventBaseExtension {
    
    private ProtocolGenericActionBean protocolGenericActionBean;

    /**
     * Constructs a ProtocolGenericActionEventBase.
     * @param document the document to validate
     * @param protocolGenericActionBean the bean that keeps the comments and dates
     */
    public ProtocolGenericActionEventBase(ProtocolDocumentBase document, ProtocolGenericActionBean protocolGenericActionBean) {
        super("Performing generic action on document " + getDocumentId(document), protocolGenericActionBean.getErrorPropertyKey(), document);
        
        this.protocolGenericActionBean = protocolGenericActionBean;
    }
    
    public ProtocolGenericActionBean getProtocolGenericActionBean() {
        return protocolGenericActionBean;
    }

}
