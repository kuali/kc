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
package org.kuali.kra.personmasschange.rules;

import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;
import org.kuali.kra.personmasschange.rule.event.PerformPersonMassChangeEvent;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;

public class PersonMassChangeDocumentRule extends ResearchDocumentRuleBase {
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean rulePassed = true;
        
        PersonMassChangeDocument personMassChangeDocument = (PersonMassChangeDocument) document;
        PersonMassChange personMassChange = personMassChangeDocument.getPersonMassChange();
        
        rulePassed &= processRules(new PerformPersonMassChangeEvent(personMassChangeDocument, personMassChange));
        
        return rulePassed;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean rulePassed = true;
        
        PersonMassChangeDocument personMassChangeDocument = (PersonMassChangeDocument) document;
        PersonMassChange personMassChange = personMassChangeDocument.getPersonMassChange();
        
        rulePassed &= processRules(new PerformPersonMassChangeEvent(personMassChangeDocument, personMassChange));

        return rulePassed;
    }
    
    @SuppressWarnings("unchecked")
    private boolean processRules(KraDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }
    
}