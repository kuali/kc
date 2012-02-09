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
package org.kuali.kra.common.notification.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * Defines the values finder for the role qualifiers field in notifications
 */
public class NotificationModuleRoleQualifierValuesFinder extends KeyValuesBase {
    

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> documentList = new ArrayList<KeyValue>();
        documentList.add(new ConcreteKeyValue(KcKimAttributes.UNIT_NUMBER, "Unit Number"));
        documentList.add(new ConcreteKeyValue("protocolLeadUnitNumber", "Unit Number (Online Review)"));
        documentList.add(new ConcreteKeyValue(KcKimAttributes.PROTOCOL, "Protocol Number"));
        documentList.add(new ConcreteKeyValue(KcKimAttributes.SUBUNITS, "Descend Heirarchy"));
        documentList.add(new ConcreteKeyValue("submissionId", "Submission Id"));
        documentList.add(new ConcreteKeyValue("protocolOnlineReviewId", "Protocol Online Review Id"));
        documentList.add(new ConcreteKeyValue("negotiation", "Negotiation Id"));
        documentList.add(new ConcreteKeyValue("disclosure", "Disclosure Id"));
        documentList.add(new ConcreteKeyValue(KcKimAttributes.PROPOSAL, "Proposal"));
        
        return documentList;
    }

}