package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Contactable;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 *
 */
public class ApprovedForeignTravelerValuesFinder extends KeyValuesBase {
    /**
     * Default c'tor
     */
    public ApprovedForeignTravelerValuesFinder() {

    }

    // @Override - bug in JDK 5 fixed in JDK 6. See http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6399361
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
        return ((AwardForm) KNSGlobalVariables.getKualiForm()).getAwardDocument().getAward();
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
