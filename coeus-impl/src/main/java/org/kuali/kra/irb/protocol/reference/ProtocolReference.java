/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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