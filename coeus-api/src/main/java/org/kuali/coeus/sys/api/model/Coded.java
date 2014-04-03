package org.kuali.coeus.sys.api.model;

/**
 * Represents an object which has a code value.
 */
public interface Coded {

    /**
     * The code value for this object.  In general a code value cannot be null or a blank string.
     *
     * @return the code value for this object.
     */
    String getCode();

}
