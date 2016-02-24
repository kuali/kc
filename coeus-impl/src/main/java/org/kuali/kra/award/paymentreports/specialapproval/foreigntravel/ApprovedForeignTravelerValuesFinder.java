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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ApprovedForeignTravelerValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    /**
     * Default c'tor
     */
    public ApprovedForeignTravelerValuesFinder() {

    }

    @Override
    public List<KeyValue> getKeyValues() {
        Award award = getAward();
        List<KeyValue> list = new ArrayList<KeyValue>();
        Set<String> listNames = new TreeSet<String>();
        for(AwardPerson person: award.getProjectPersons()) {
            Contactable contact = person.getContact();
            addEligibleTravelerToList(list, listNames, contact);
        }
        for(AwardApprovedForeignTravel trip: award.getApprovedForeignTravelTrips()) {
            Contactable contact = trip.getTraveler();
            addEligibleTravelerToList(list, listNames, contact);
        }
        return list;
    }

    protected Award getAward() {
        return ((AwardDocument) getDocument()).getAward();
    }

    private void addEligibleTravelerToList(List<KeyValue> list, Set<String> listNames, Contactable contact) {
        if(contact != null) {
            String fullName = contact.getFullName();
            if(!listNames.contains(fullName)) {
                list.add(new ConcreteKeyValue(contact.getIdentifier().toString(), fullName));
                listNames.add(fullName);
            }
        }
    }
}
