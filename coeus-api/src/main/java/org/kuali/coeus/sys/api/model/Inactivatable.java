package org.kuali.coeus.sys.api.model;

/**
 * This interface can be used to identify an object which has an active
 * indicator.
 */
public interface Inactivatable {

    /**
     * The active indicator for an object.
     *
     * @return true if active false if not.
     */
    boolean isActive();
}