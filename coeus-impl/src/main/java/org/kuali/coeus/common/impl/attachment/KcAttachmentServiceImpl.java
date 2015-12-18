/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.file.FileMeta;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
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

	@Resource(name="KcFileMimeTypeIcons")
    private Map<String, String> KcFileMimeTypeIcons;

    private static final String REPLACEMENT_CHARACTER = "_";
    //Exclude everything but numbers, alphabets, dots, hyphens and underscores
    private static final String REGEX_TITLE_FILENAME_PATTERN = "([^0-9a-zA-Z\\.\\-_])";
    private static final String REGEX_TITLE_SPECIAL_CHARACTER_PATTERN = "([^\\x00-\\x7F])";
    
    /**
     * Currently determining the icon based only on the mime type and using the default icon
     * if a mime type is not mapped in mimeTypeIcons. The full attachment is being passed here
     * so more advanced file type detection can be implemented if necessary.
     */
    @Override
    public String getFileTypeIcon(String type) {
        String iconPath = getMimeTypeIcons().get(type);
        if (iconPath == null) {
            return KcFileMimeTypeIcons.get(DEFAULT_ICON);
        } else {
            return iconPath;
        }
    }

    protected Map<String, String> getMimeTypeIcons() {
        return KcFileMimeTypeIcons;
    }

    public void setMimeTypeIcons(Map<String, String> mimeTypeIcons) {
        this.KcFileMimeTypeIcons = mimeTypeIcons;
    }

    protected String getDefaultIcon() {
        return KcFileMimeTypeIcons.get(DEFAULT_ICON);
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

    public String formatFileSizeString(Long size) {
        DecimalFormat format = new DecimalFormat("0.#");

        if (size >= 1000000000) {
            return format.format((((double)size) / 1000000000)) + " GB";
        } else if (size >= 1000000) {
            return format.format((((double)size) / 1000000)) + " MB";
        } else {
            return format.format((((double)size) / 1000)) + " KB";
        }
    }

    @Override
    public boolean validPDFFile(FileMeta fileInQuestion, ErrorReporter errorReporter, String errorPrefix) {
        if (fileInQuestion.getName() == null) {
        	errorReporter.reportError(errorPrefix, KeyConstants.ERROR_ATTACHMENT_FILE_REQURIED);
        } else if (!Constants.PDF_REPORT_CONTENT_TYPE.equals(fileInQuestion.getContentType())) {
           errorReporter.reportWarning(errorPrefix, KeyConstants.INVALID_FILE_TYPE,
                    fileInQuestion.getName(), Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return true;
    }

    @Override
    public boolean doesNewFileExist(FormFile file){
        return file != null && StringUtils.isNotBlank(file.getFileName());
    }
}
