package org.kuali.coeus.sys.api.model;

/**
 * Simple interface to provide common file methods.
 */
public interface KcFile {

    /**
     * Returns file name of the file.
     * @return name
     */
    String getName();
    /**
     * Returns the mime type of the file.
     * @return type
     */
    String getType();
    /**
     * Returns the data of this file.
     * @return data
     */
    byte[] getData();
}
