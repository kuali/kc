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
package org.kuali.kra.coi.personfinancialentity;

import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * 
 * This class is and event class when save FE
 */
public class SaveFinancialEntityEvent  extends KraDocumentEventBaseExtension {
    
    private String propertyName;
    private PersonFinIntDisclosure personFinIntDisclosure;

    /**
     * 
     * Constructs a SaveFinancialEntityEvent.java.
     * @param propertyName
     * @param personFinIntDisclosure
     */
    public SaveFinancialEntityEvent(String propertyName, PersonFinIntDisclosure personFinIntDisclosure) {
        super("Save financial entity", "", null);
        this.propertyName = propertyName;
        this.personFinIntDisclosure = personFinIntDisclosure;
        
    }
        
    public String getPropertyName() {
        return propertyName;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new SaveFinancialEntityRule();
    }

    public PersonFinIntDisclosure getPersonFinIntDisclosure() {
        return personFinIntDisclosure;
    }

    public void setPersonFinIntDisclosure(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
    }

}
