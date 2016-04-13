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
        documentList.add(new ConcreteKeyValue(KcKimAttributes.AWARD, "Award"));
        documentList.add(new ConcreteKeyValue(KimConstants.AttributeConstants.DOCUMENT_NUMBER, "Document Number"));
        
        return documentList;
    }

}
