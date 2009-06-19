/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.protocol.reference;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class is implementation of <code>AddProtocolReferenceRule</code> interface. Impl makes sure necessary rules are satisfied 
 * before object can be used.
 */
public class ProtocolReferenceRule extends ResearchDocumentRuleBase implements AddProtocolReferenceRule {

    /**
     * @see org.kuali.kra.irb.protocol.reference.AddProtocolReferenceRule#processAddProtocolReferenceBusinessRules(org.kuali.kra.irb.protocol.reference.AddProtocolReferenceEvent)
     */
    public boolean processAddProtocolReferenceBusinessRules(AddProtocolReferenceEvent addProtocolReferenceEvent) {
        
        boolean rulePassed = true;
        
        if(null == addProtocolReferenceEvent.getProtocolReference().getProtocolReferenceTypeCode()) {
            rulePassed = false;
            reportError("newProtocolReference"+".protocolReferenceTypeCode", KeyConstants.ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCETYPECODE, "Type");
        }
         
        if(StringUtils.isBlank(addProtocolReferenceEvent.getProtocolReference().getReferenceKey())) {
            rulePassed = false;
            reportError("newProtocolReference"+".referenceKey", KeyConstants.ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCEKEY, "Other Identifier");
        }
            
        return rulePassed;
    }

}
