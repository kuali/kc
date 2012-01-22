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
package org.kuali.kra.irb.actions.notification;

import java.io.IOException;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is business rule for notification template.
 */
public class ProtocolNotificationTemplateRule {

    /**
     * 
     * This method verifies the protocol notification template on replace.
     * 
     * @param correspondenceType
     * @param newNotificationTemplate
     * @param index
     * @return true if the validation is successful, false otherwise
     * @throws IOException
     */
    public boolean processReplaceProtocolNotificationTemplateRules(ProtocolNotificationTemplate notificationTemplate, int idx)
            throws IOException {
        boolean valid = true;

        valid &= validFile(notificationTemplate.getTemplateFile(), "notificationTemplates[" + idx + "]");

        return valid;
    }


    /**
     * 
     * This method checks that a valid template file has been specified on adds.
     * 
     * @param file
     * @param propertyName
     * @return true if the file is valid, false otherwise
     * @throws IOException
     */
    private boolean validFile(FormFile file, String propertyName) throws IOException {
        boolean isValid = true;

        byte[] fileData = file.getFileData();
        // Check that file is not empty
        if ((fileData == null) || (fileData.length == 0)) {
            // empty file
            GlobalVariables.getMessageMap().putError(propertyName, KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE);
            isValid = false;
        }

        if (isValid) {
            // Check that file is of the correct type
            String contentType = file.getContentType();
            if (!contentType.equals(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1)
                    && !contentType.equals(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_2)) {
                // wrong file type
                GlobalVariables.getMessageMap()
                        .putError(propertyName, KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE_TYPE);
                isValid = false;
            }
        }

        return isValid;
    }


}
