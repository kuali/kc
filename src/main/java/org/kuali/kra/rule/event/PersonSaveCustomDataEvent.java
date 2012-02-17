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
package org.kuali.kra.rule.event;

import java.util.List;

import org.kuali.kra.bo.PersonCustomData;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.PersonSaveCustomDataRule;
import org.kuali.rice.krad.document.Document;

public class PersonSaveCustomDataEvent extends KraDocumentEventBaseExtension {
    
    private List<PersonCustomData> personCustomDataList;

    public PersonSaveCustomDataEvent(String errorPathPrefix, Document document, List<PersonCustomData> personCustomDataList) {
        super("Saving custom attribute to document", errorPathPrefix, document);
        
        this.personCustomDataList = personCustomDataList;
    }

    public List<PersonCustomData> getPersonCustomDataList() {
        return personCustomDataList;
    }

    public void setPersonCustomDataList(List<PersonCustomData> personCustomDataList) {
        this.personCustomDataList = personCustomDataList;
    }

    @Override
    public BusinessRuleInterface<PersonSaveCustomDataEvent> getRule() {
        return new PersonSaveCustomDataRule();
    }

}