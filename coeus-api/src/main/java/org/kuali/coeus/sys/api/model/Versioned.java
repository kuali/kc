package org.kuali.coeus.sys.api.model;

/**
 * This interface can be used to identify a object which has a version number
 * that is used for the purposes of optimistic locking.
 */
public interface Versioned {

	
	/**
     * Returns the version number for this object.  In general, this value should only
	 * be null if the object has not yet been stored to a persistent data store.
	 * This version number is generally used for the purposes of optimistic locking.
	 * 
	 * @return the version number, or null if one has not been assigned yet
	 */
	Long getVersionNumber();
	
}