package org.kuali.coeus.sys.api.model;

import java.util.Date;

/**
 * This interface can be used to identify a user and timestamp regarding when the object was initially created
 * (usually in regards to object persistence).
 */
public interface RecordedCreate {

    /**
     * The date and time of when the object was last inserted
     * @return the date or null if the object is new
     */
    Date getCreateTimestamp();

    /**
     * The user who last inserted the object
     * @return the user or null if the object is new
     */
    String getCreateUser();

}
