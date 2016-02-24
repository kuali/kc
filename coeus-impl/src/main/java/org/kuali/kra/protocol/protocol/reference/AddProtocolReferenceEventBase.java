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
package org.kuali.kra.protocol.protocol.reference;

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class hooks Rule to Event in KNS
 */
public abstract class AddProtocolReferenceEventBase extends ProtocolReferenceEventBase {

    /**
     * 
     * Constructs a AddProtocolReferenceEventBase.java.
     * @param errorPathPrefix
     * @param document
     * @param protocolReferenceBean
     */
    public AddProtocolReferenceEventBase(String errorPathPrefix, ProtocolDocumentBase document, ProtocolReferenceBeanBase protocolReferenceBean) {
        super("adding ProtocolReferenceBase to document " + getDocumentId(document), errorPathPrefix, document, protocolReferenceBean);
    }
    
    /**
     * 
     * Constructs a AddProtocolReferenceEventBase.java.
     * @param errorPathPrefix
     * @param document
     * @param protocolReferenceBean
     */
    public AddProtocolReferenceEventBase(String errorPathPrefix, Document document, ProtocolReferenceBeanBase protocolReferenceBean) {
        this(errorPathPrefix, (ProtocolDocumentBase)document, protocolReferenceBean);
    } 
    
    @Override
    public Class getRuleInterfaceClass() {
        return AddProtocolReferenceRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolReferenceRule)rule).processAddProtocolReferenceBusinessRules(this);
    }

}
