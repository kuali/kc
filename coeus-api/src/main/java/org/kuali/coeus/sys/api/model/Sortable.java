package org.kuali.coeus.sys.api.model;

/**
 * Represents an object which is sortable by sortId.
 */
public interface Sortable {

    /**
     * The sort id for this object.  In general a sort id is optional but if set is
     * usually either one or two numbers or letters.
     *
     * @return the sort id for this object.
     */
    Integer getSortId();
}
