package org.kuali.kra.s2s.depend;

import org.kuali.kra.budget.personnel.TbnPerson;

public interface ToBeNamePersonService {

    /**
     * Finds the To Be Named Person by the unique id.
     *
     * @param id to be named person id.  Cannot be blank.
     * @return the To Be Named Person or null.
     */
    TbnPerson findTbnPersonById(String id);
}
