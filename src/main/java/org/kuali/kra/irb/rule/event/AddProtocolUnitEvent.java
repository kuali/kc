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
package org.kuali.kra.irb.rule.event;

import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolUnitRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class represents the AddProtocolPersonnelEvent
 */
public class AddProtocolUnitEvent extends ProtocolUnitEventBase {
    
    public AddProtocolUnitEvent(String errorPathPrefix, ProtocolDocument document, 
            ProtocolUnit protocolUnit, int personIndex) {
        super("adding ProtocolUnit to document " + getDocumentId(document), errorPathPrefix, document, 
                protocolUnit, personIndex);
    }

    public AddProtocolUnitEvent(String errorPathPrefix, Document document, 
            ProtocolUnit protocolUnit, int personIndex) {
        this(errorPathPrefix, (ProtocolDocument) document, protocolUnit, personIndex);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddProtocolUnitRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolUnitRule) rule).processAddProtocolUnitBusinessRules(this);
    }

}
