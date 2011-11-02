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
package org.kuali.kra.coi.disclosure;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

// TODO: Note, this is a stub class that must be filled out to allow print.
public class CertifyDisclosureEvent  extends KraDocumentEventBaseExtension {
    
    private CoiDisclosure disclosure;
    private String propertyName;

    public CertifyDisclosureEvent(String propertyName, CoiDisclosure disclosure) {
        super("Disclosure Certification", "", null);
        this.disclosure = disclosure;
        this.propertyName = propertyName;
    }
        
    public String getPropertyName() {
        return propertyName;
    }
    
    public CoiDisclosure getDisclosure() {
        return disclosure;
    }
     
    @SuppressWarnings("rawtypes")
    @Override
    public BusinessRuleInterface getRule() {
        return new CertifyDisclosureRule();
    }


}
