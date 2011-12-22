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
package org.kuali.kra.personmasschange.rule.event;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.rule.PerformPersonMassChangeRule;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.kns.document.Document;

public class PerformPersonMassChangeEvent extends KraDocumentEventBaseExtension {
    
    private PersonMassChange personMassChange;

    public PerformPersonMassChangeEvent(Document document, PersonMassChange personMassChange) {
        super("performing person mass change", Constants.EMPTY_STRING, document);
        
        this.personMassChange = personMassChange;
    }

    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }

    @Override
    public BusinessRuleInterface<PerformPersonMassChangeEvent> getRule() {
        return new PerformPersonMassChangeRule();
    }

}