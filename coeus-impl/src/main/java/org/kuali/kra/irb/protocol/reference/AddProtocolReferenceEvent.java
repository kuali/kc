/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.protocol.reference;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.protocol.protocol.reference.AddProtocolReferenceEventBase;
import org.kuali.rice.krad.document.Document;

/**
 * This class hooks Rule to Event in KNS
 */
public class AddProtocolReferenceEvent extends AddProtocolReferenceEventBase {

    
    /**
     * 
     * Constructs a AddProtocolReferenceEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param protocolReferenceBean
     */
    public AddProtocolReferenceEvent(String errorPathPrefix, ProtocolDocument document, ProtocolReferenceBean protocolReferenceBean) {
        super(errorPathPrefix, document, protocolReferenceBean);
    }
    
    /**
     * 
     * Constructs a AddProtocolReferenceEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param protocolReferenceBean
     */
    public AddProtocolReferenceEvent(String errorPathPrefix, Document document, ProtocolReferenceBean protocolReferenceBean) {
        this(errorPathPrefix, (ProtocolDocument)document, protocolReferenceBean);
    } 
}
