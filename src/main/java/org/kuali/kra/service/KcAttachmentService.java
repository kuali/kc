/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service;

import org.kuali.kra.bo.KcAttachment;

/**
 * KC Attachment Service.
 */
public interface KcAttachmentService {

    /**
     * Based on the attachment provided determine and return a path to the icon for the file.
     * @param attachment
     * @return
     */
    String getFileTypeIcon(KcAttachment attachment);
   
    
    /**
     * This method returns the invalid characters in the file name.
     * @return
     */
    String getInvalidCharacters();
   
    /**
     * This method checks strings for invalid characters.
     * @param text
     * @return
     */
    boolean hasInvalidCharacters(String text);
        
    /**
     * This method checks for invalid characters in strings and replaces
     * them with underscores.
     * @param text
     * @return
     */
    String checkAndReplaceInvalidCharacters(String text);
}
