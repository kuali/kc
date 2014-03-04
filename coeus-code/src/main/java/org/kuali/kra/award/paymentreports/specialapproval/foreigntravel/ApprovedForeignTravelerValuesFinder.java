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
