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
package org.kuali.coeus.common.impl.attachment;

import org.kuali.coeus.common.api.attachment.KcAttachment;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

/**
 * KC Attachment Service Implementation.
 */
@Component("kcAttachmentService")
public class KcAttachmentServiceImpl implements KcAttachmentService {
    
	private static final String DEFAULT_ICON = "default";

	@Resource(name="kcAttachmentMimeTypeIcons")
    private Map<String, String> kcAttachmentMimeTypeIcons;

    private static final String REPLACEMENT_CHARACTER = "_";
    //Exclude everything but numbers, alphabets, dots, hyphens and underscores
    private static final String REGEX_TITLE_FILENAME_PATTERN = "([^0-9a-zA-Z\\.\\-_])";
    private static final String REGEX_TITLE_SPECIAL_CHARACTER_PATTERN = "([^\\x00-\\x7F])";
    
    /**
     * Currently determining the icon based only on the mime type and using the default icon
     * if a mime type is not mapped in mimeTypeIcons. The full attachment is being passed here
     * so more advanced file type detection can be implemented if necessary.
     * @see org.kuali.coeus.common.framework.attachment.KcAttachmentService#getFileTypeIcon(org.kuali.coeus.common.api.attachment.KcAttachment)
     */
    @Override
    public String getFileTypeIcon(String type) {
        String iconPath = getMimeTypeIcons().get(type);
        if (iconPath == null) {
            return kcAttachmentMimeTypeIcons.get(DEFAULT_ICON);
        } else {
            return iconPath;
        }
    }

    protected Map<String, String> getMimeTypeIcons() {
        return kcAttachmentMimeTypeIcons;
    }

    public void setMimeTypeIcons(Map<String, String> mimeTypeIcons) {
        this.kcAttachmentMimeTypeIcons = mimeTypeIcons;
    }

    protected String getDefaultIcon() {
        return kcAttachmentMimeTypeIcons.get(DEFAULT_ICON);
    }

    @Override
    public String getInvalidCharacters(String text) {
        if (ObjectUtils.isNotNull(text)) {
            
            Pattern pattern = Pattern.compile(REGEX_TITLE_FILENAME_PATTERN);
            Matcher matcher = pattern.matcher(text);
            // Not null and invalid chars found 
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        // if text is null, return false. Null checks
        //for file names are done in other places and description
        // text can be null.
        return null;    
    }

    /**
     * This method checks string for invalid characters and replaces with underscores.
     */
    @Override
    public String checkAndReplaceInvalidCharacters(String text) {     
        String cleanText = text;
        if (ObjectUtils.isNotNull(text)) {
            Pattern pattern = Pattern.compile(REGEX_TITLE_FILENAME_PATTERN);
            Matcher matcher = pattern.matcher(text);
            cleanText = matcher.replaceAll(REPLACEMENT_CHARACTER);
            if(cleanText.length() > 50){
               cleanText = cleanText.substring(0, 50);
            }
        }
        return cleanText;
    }

    @Override
    public boolean getSpecialCharacter(String text) {
        if (ObjectUtils.isNotNull(text)) {
            Pattern pattern = Pattern.compile(REGEX_TITLE_SPECIAL_CHARACTER_PATTERN);
            Matcher matcher = pattern.matcher(text);            
            if (matcher.find()) {                
                return true;
            }
        }        
        return false;    
    }

    @Override
    public String checkAndReplaceSpecialCharacters(String text) {     
        String cleanText = text;
        if (ObjectUtils.isNotNull(text)) {
            Pattern pattern = Pattern.compile(REGEX_TITLE_SPECIAL_CHARACTER_PATTERN);
            Matcher matcher = pattern.matcher(text);
            cleanText = matcher.replaceAll(REPLACEMENT_CHARACTER);
        }
        return cleanText;
    }

}
