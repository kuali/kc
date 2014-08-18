/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.framework.attachment;

import org.kuali.coeus.sys.api.model.KcFile;

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
     * This method checks for invalid characters in strings and replaces
     * them with underscores.
     * @param text
     * @return
     */
    String checkAndReplaceInvalidCharacters(String text);
    
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
}
