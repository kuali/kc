/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.framework.attachment;

public interface KcAttachmentDataDao {
	
	/**
	 * Using the ID provided, retrieves the given file data stored in the file_data table.
	 * @param id the id.  If blank will return null
	 * @return the data or null if the id is blank
	 */
    byte[] getData(String id);
    
    /**
     * Stores the data and returns a new randomly generated ID value that can be used to retrieve the
     * data. If the BO calling this previously stored data, pass in the previous ID and it will be deleted
     * before saving the new data.
     * @param data the data.  cannot be null or empty
     * @param previousId optional, used to delete any previously stored data before persisting new data.
     * @return the id for the saved data
     */
    String saveData(byte[] data, String previousId);
    
    /**
     * Removes the record from file_data table by id.
     * @param id the id.  If blank will return null
     */
    void removeData(String id);
}
