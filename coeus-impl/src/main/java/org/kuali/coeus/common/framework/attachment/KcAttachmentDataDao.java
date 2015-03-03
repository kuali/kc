package org.kuali.coeus.common.framework.attachment;

public interface KcAttachmentDataDao {
	
	/**
	 * Using the ID provided, retrieves the given file data stored in the file_data table.
	 * @param id
	 * @return
	 */
    public byte[] getData(String id);
    
    /**
     * Stores the data and returns a new randomly generated ID value that can be used to retrieve the
     * data. If the BO calling this previously stored data, pass in the previous ID and it will be deleted
     * before saving the new data.
     * @param data
     * @param previousId optional, used to delete any previously stored data before persisting new data.
     * @return
     */
    public String saveData(byte[] data, String previousId);
    
    /**
     * Removes the record from file_data table by id.
     * @param id
     */
    public void removeData(String id);
}
