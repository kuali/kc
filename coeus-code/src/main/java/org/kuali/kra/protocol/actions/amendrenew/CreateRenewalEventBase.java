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
 * Rule Event for creating renewal without amendment.
 */
@SuppressWarnings("unchecked")
public abstract class CreateRenewalEventBase extends KcDocumentEventBaseExtension {

    private String renewalSummary;
    private String propertyName;

    public CreateRenewalEventBase(ProtocolDocumentBase document, String propertyName, String renewalSummary) {
        super("Create Renewal", "", document);
        this.propertyName = propertyName;
        this.renewalSummary = renewalSummary;
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public String getRenewalSummary() {
        return renewalSummary;
    }

    public abstract KcBusinessRule getRule();

}

