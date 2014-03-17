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
package org.kuali.kra.protocol.personnel;

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class represents the AddProtocolPersonnelEventBase
 */
public class AddProtocolUnitEvent extends ProtocolUnitEventBase {
    
    public AddProtocolUnitEvent(String errorPathPrefix, ProtocolDocumentBase document, 
            ProtocolUnitBase protocolUnit, int personIndex) {
        super("adding ProtocolUnitBase to document " + getDocumentId(document), errorPathPrefix, document, 
                protocolUnit, personIndex);
    }

    public AddProtocolUnitEvent(String errorPathPrefix, Document document, 
            ProtocolUnitBase protocolUnit, int personIndex) {
        this(errorPathPrefix, (ProtocolDocumentBase) document, protocolUnit, personIndex);
    }
    
    @Override
    public Class getRuleInterfaceClass() {
        return AddProtocolUnitRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolUnitRule) rule).processAddProtocolUnitBusinessRules(this);
    }

}
