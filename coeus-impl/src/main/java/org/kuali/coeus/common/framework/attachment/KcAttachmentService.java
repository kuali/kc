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

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.file.FileMeta;

/**
 * KC Attachment Service.
 */
public interface KcAttachmentService {

    /**
     * Based on the mime type provided determine and return a path to the icon for the file.
     * @param type
     * @return
     */
    String getFileTypeIcon(String type);
   
    
    /**
     * This method returns the invalid characters in the file name.
     * @return
     */
    String getInvalidCharacters(String text);
    
    /**
     * This method check the Special characters in the string.
     * @return
     */
    boolean getSpecialCharacter(String text);
   
        
    /**
     * This method checks for special characters in strings and replaces
     * them with underscores.
     * @param text
     * @return
     */
    String checkAndReplaceSpecialCharacters(String text);

    /**
     * This method formatted the attachment file size string
     * @param size
     * @return
     */
    public String formatFileSizeString(Long size);

    /**
     * This method checks to see if the attachment is of type PDF
     * @param fileInQuestion
     * @param errorReporterService
     * @param errorPrefix
     * @return boolean
     */
    public boolean validPDFFile(FileMeta fileInQuestion, ErrorReporter errorReporterService, String errorPrefix);

    boolean doesNewFileExist(FormFile file);
}
