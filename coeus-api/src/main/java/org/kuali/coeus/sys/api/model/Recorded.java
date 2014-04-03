package org.kuali.coeus.sys.api.model;

import java.util.Date;

/**
 * This interface can be used to identify a user and timestamp regarding when the object was last
 * modified (usually in regards to object persistence).
 */
public interface Recorded {

    /**
     * The date and time of when the object was last inserted or updated
     * @return the date or null if the object is new
     */
    public Date getUpdateTimestamp();

    /**
     * The user who last inserted or updated the object
     * @return the user or null if the object is new
     */
    public String getUpdateUser();

}
