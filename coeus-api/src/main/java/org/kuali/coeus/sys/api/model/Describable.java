package org.kuali.coeus.sys.api.model;

/**
 * Represents an object which has a description.
 */
public interface Describable {

    /**
     * The description for this object.  In general a description can be any characters as well as null or blank.
     *
     * @return the description for this object.
     */
    String getDescription();
}
