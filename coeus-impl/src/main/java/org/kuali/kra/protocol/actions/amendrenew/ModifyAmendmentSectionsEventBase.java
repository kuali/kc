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
package org.kuali.kra.protocol.actions.amendrenew;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * When amendment sections are modified, this event is generated.
 */
@SuppressWarnings("unchecked")
public abstract class ModifyAmendmentSectionsEventBase extends KcDocumentEventBaseExtension {

    private ProtocolAmendmentBean amendmentBean;
    private String propertyName;
    private boolean amendment;

    public ModifyAmendmentSectionsEventBase(ProtocolDocumentBase document, String propertyName, ProtocolAmendmentBean amendmentBean) {
        super("Modify Amendment Sections", "", document);
        this.propertyName = propertyName;
        this.amendmentBean = amendmentBean;
        this.amendment = getProtocolDocument().getProtocol().isAmendment();
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolAmendmentBean getAmendmentBean() {
        return amendmentBean;
    }
    
    public boolean isAmendment() {
        return amendment;
    }

    @Override
    public abstract KcBusinessRule getRule();

}
