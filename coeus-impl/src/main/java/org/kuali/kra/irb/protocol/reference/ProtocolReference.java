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
package org.kuali.kra.irb.protocol.reference;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBase;

import java.text.ParseException;


public class ProtocolReference extends ProtocolReferenceBase {


    private static final long serialVersionUID = 7610203950849323256L;
    
    /**
     * 
     * Constructs a ProtocolReferenceBase.java.
     * @param bean
     * @param protocol
     * @param protocolReferenceType
     * @throws ParseException
     */
    public ProtocolReference(ProtocolReferenceBean bean, Protocol protocol, ProtocolReferenceType protocolReferenceType) throws ParseException {
        super(bean, protocol, protocolReferenceType);
    }
    

    /**
	 * 
	 * Constructs a ProtocolReference.java.
	 */
    public ProtocolReference() {
    }
}
