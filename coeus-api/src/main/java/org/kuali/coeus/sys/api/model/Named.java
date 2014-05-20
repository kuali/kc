package org.kuali.coeus.sys.api.model;

/**
 * Represents an object which has a name.
 */
public interface Named {
    /**
     * The name for this object.  In general a name can be alpha numeric characters and is generally not null or blank.
     *
     * @return the name for this object.
     */
    String getName();
}
