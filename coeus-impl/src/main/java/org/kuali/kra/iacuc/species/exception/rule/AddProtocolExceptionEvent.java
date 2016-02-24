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
package org.kuali.kra.iacuc.species.exception.rule;

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class hooks Rule to Event in KNS
 */
public class AddProtocolExceptionEvent extends ProtocolExceptionEventBase {

    /**
     * 
     * Constructs a AddProtocolExceptionEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param iacucProtocolException
     */
    public AddProtocolExceptionEvent(String errorPathPrefix, IacucProtocolDocument document, IacucProtocolException iacucProtocolException) {
        super("adding ProtocolException to document " + getDocumentId(document), errorPathPrefix, document, iacucProtocolException);
    }
    
    /**
     * 
     * Constructs a AddProtocolExceptionEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param iacucProtocolException
     */
    public AddProtocolExceptionEvent(String errorPathPrefix, Document document, IacucProtocolException iacucProtocolException) {
        this(errorPathPrefix, (IacucProtocolDocument)document, iacucProtocolException);
    } 
    
    @Override
    public Class getRuleInterfaceClass() {
        return AddProtocolExceptionRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolExceptionRule)rule).processAddProtocolExceptionBusinessRules(this);
    }


}
