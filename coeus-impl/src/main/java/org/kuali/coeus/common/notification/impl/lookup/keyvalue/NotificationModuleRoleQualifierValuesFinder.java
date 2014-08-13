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
package org.kuali.coeus.common.notification.impl.lookup.keyvalue;

import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the values finder for the role qualifiers field in notifications
 */
public class NotificationModuleRoleQualifierValuesFinder extends UifKeyValuesFinderBase {


    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> documentList = new ArrayList<KeyValue>();
        documentList.add(new ConcreteKeyValue(KcKimAttributes.UNIT_NUMBER, "Unit Number"));
        documentList.add(new ConcreteKeyValue("protocolLeadUnitNumber", "Unit Number (Online Review)"));
        documentList.add(new ConcreteKeyValue(KcKimAttributes.PROTOCOL, "Protocol Number"));
        documentList.add(new ConcreteKeyValue(KcKimAttributes.SUBUNITS, "Descend Heirarchy"));
        documentList.add(new ConcreteKeyValue("submissionId", "Submission Id"));
        documentList.add(new ConcreteKeyValue("protocolOnlineReviewId", "Protocol Online Review Id"));
        documentList.add(new ConcreteKeyValue("negotiation", "Negotiation Id"));
        documentList.add(new ConcreteKeyValue("coiDisclosureId", "Disclosure Id"));
        documentList.add(new ConcreteKeyValue(KcKimAttributes.PROPOSAL, "Proposal"));
        documentList.add(new ConcreteKeyValue(KimConstants.AttributeConstants.DOCUMENT_NUMBER, "Document Number"));
        
        return documentList;
    }

}