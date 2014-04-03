package org.kuali.coeus.sys.api.model;

/**
 * This interface can be used to identify an object which has a unique
 * identifier.
 */
public interface IdentifiableNumeric {

    /**
     * The unique identifier for an object.  This can be null unless the
     * object has not been given an id yet.
     *
     * @return the id
     */
    Long getId();
}
