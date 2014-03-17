/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.protocol.reference;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBase;

import java.text.ParseException;

public class IacucProtocolReference extends ProtocolReferenceBase {
    

    private static final long serialVersionUID = -5606862862070468479L;


    public IacucProtocolReference(IacucProtocolReferenceBean bean, IacucProtocol protocol, IacucProtocolReferenceType type) throws ParseException {
        super(bean, protocol, type);                
    }


    public IacucProtocolReference() {
        super();
    }

    
}
