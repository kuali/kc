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
package org.kuali.kra.irb.actions.amendrenew;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * Rule Event for creating renewal without amendment.
 * This class...
 */
public class CreateRenewalEvent <T extends BusinessRuleInterface> extends KraDocumentEventBaseExtension {

    private String renewalSummary;
    private String propertyName;

    public CreateRenewalEvent(ProtocolDocument document, String propertyName, String renewalSummary) {
        super("Create Renewal", "", document);
        this.propertyName = propertyName;
        this.renewalSummary = renewalSummary;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public String getRenewalSummary() {
        return renewalSummary;
    }

    @Override
    public BusinessRuleInterface getRule() {
        return new CreateRenewalRule();
    }
}

