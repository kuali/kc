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
package org.kuali.kra.iacuc.species.rule;

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class hooks Rule to Event in KNS
 */
public class AddProtocolSpeciesEvent extends ProtocolSpeciesEventBase {

    /**
     * 
     * Constructs a AddProtocolSpeciesEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param iacucProtocolSpecies
     */
    public AddProtocolSpeciesEvent(String errorPathPrefix, IacucProtocolDocument document, IacucProtocolSpecies iacucProtocolSpecies) {
        super("adding ProtocolSpecies to document " + getDocumentId(document), errorPathPrefix, document, iacucProtocolSpecies);
    }
    
    /**
     * 
     * Constructs a AddProtocolSpeciesEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param iacucProtocolSpecies
     */
    public AddProtocolSpeciesEvent(String errorPathPrefix, Document document, IacucProtocolSpecies iacucProtocolSpecies) {
        this(errorPathPrefix, (IacucProtocolDocument)document, iacucProtocolSpecies);
    } 
    
    @Override
    public Class getRuleInterfaceClass() {
        return AddProtocolSpeciesRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolSpeciesRule)rule).processAddProtocolSpeciesBusinessRules(this);
    }


}
