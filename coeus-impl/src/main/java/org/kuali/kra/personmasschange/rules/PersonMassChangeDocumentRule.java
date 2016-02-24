/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.personmasschange.rules;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;
import org.kuali.kra.personmasschange.rule.event.PerformPersonMassChangeEvent;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.document.Document;

public class PersonMassChangeDocumentRule extends KcTransactionalDocumentRuleBase {
    
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
    private boolean processRules(KcDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }
    
}
