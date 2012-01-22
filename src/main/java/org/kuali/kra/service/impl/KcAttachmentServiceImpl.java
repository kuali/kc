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
package org.kuali.kra.service.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kuali.kra.bo.KcAttachment;
import org.kuali.kra.service.KcAttachmentService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * KC Attachment Service Implementation.
 */
public class KcAttachmentServiceImpl implements KcAttachmentService {
    
    private Map<String, String> mimeTypeIcons;
    private String defaultIcon;
    private String invalidCharacters;
   
    private static final String REPLACEMENT_CHARACTER = "_";
    //Exclude everything but numbers, alphabets, dots, hyphens and underscores
    private static final String REGEX_TITLE_FILENAME_PATTERN = "([^0-9a-zA-Z\\.\\-_])";
    
    /**
     * Currently determining the icon based only on the mime type and using the default icon
     * if a mime type is not mapped in mimeTypeIcons. The full attachment is being passed here
     * so more advanced file type detection can be implemented if necessary.
     * @see org.kuali.kra.service.KcAttachmentService#getFileTypeIcon(org.kuali.kra.bo.KcAttachment)
     */
    public String getFileTypeIcon(KcAttachment attachment) {
        String iconPath = getMimeTypeIcons().get(attachment.getType());
        if (iconPath == null) {
            return getDefaultIcon();
        } else {
            return iconPath;
        }
    }

    protected Map<String, String> getMimeTypeIcons() {
        return mimeTypeIcons;
    }

    public void setMimeTypeIcons(Map<String, String> mimeTypeIcons) {
        this.mimeTypeIcons = mimeTypeIcons;
    }

    protected String getDefaultIcon() {
        return defaultIcon;
    }

    public void setDefaultIcon(String defaultIcon) {
        this.defaultIcon = defaultIcon;
    }
    
    /**
     * This method checks to see if string has invalid characters in it.
     * @see org.kuali.kra.service.KcAttachmentService#hasInvalidCharacters(java.lang.String)
     */
    public boolean hasInvalidCharacters(String text) {
       
        if (ObjectUtils.isNotNull(text)) {
            
            Pattern pattern = Pattern.compile(REGEX_TITLE_FILENAME_PATTERN);
            Matcher matcher = pattern.matcher(text);
            // Not null and invalid chars found 
            if (matcher.find()) {
                setInvalidCharacters(matcher.group(1));
                return true;
            }
        }
        // if text is null, return false. Null checks
        //for file names are done in other places and description
        // text can be null.
        return false;    
    }
    
    public String getInvalidCharacters() {
        return invalidCharacters;
    }

    public void setInvalidCharacters(String invalidCharacters) {
        this.invalidCharacters = invalidCharacters;
    }

    /**
     * This method checks string for invalid characters and replaces with underscores.
     * @see org.kuali.kra.service.KcAttachmentService#checkAndReplaceInvalidCharacters(java.lang.String)
     */
    public String checkAndReplaceInvalidCharacters(String text) {
     
        String cleanText = text;
        if (ObjectUtils.isNotNull(text)) {
            String regex = REGEX_TITLE_FILENAME_PATTERN;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            cleanText = matcher.replaceAll(REPLACEMENT_CHARACTER);
        }
        return cleanText;
    }

}
