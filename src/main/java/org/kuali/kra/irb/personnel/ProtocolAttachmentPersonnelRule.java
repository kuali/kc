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
package org.kuali.kra.irb.personnel;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentType;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;


public class ProtocolAttachmentPersonnelRule extends ResearchDocumentRuleBase implements AddProtocolAttachmentPersonnelRule {
    private static final String OTHER_TYPE_CODE = "9";

    private static final String PROPERTY_NAME_NEW_ATTACHMENT_TYPE = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].typeCode";
    private static final String PROPERTY_NAME_NEW_ATTACHMENT_DESCRIPTION = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].description";
    private static final String PROPERTY_NAME_NEW_ATTACHMENT_FILE = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].newFile";

    
    /**
     * 
     * @see org.kuali.kra.irb.personnel.AddProtocolAttachmentPersonnelRule#processAddProtocolAttachmentPersonnelRules(org.kuali.kra.irb.personnel.AddProtocolAttachmentPersonnelEvent)
     */
    public boolean processAddProtocolAttachmentPersonnelRules(AddProtocolAttachmentPersonnelEvent event) {
        boolean isValid = true;
        
        String typePropertyName = String.format(PROPERTY_NAME_NEW_ATTACHMENT_TYPE, event.getPersonIndex());
        String descriptionPropertyName = String.format(PROPERTY_NAME_NEW_ATTACHMENT_DESCRIPTION, event.getPersonIndex());
        String filePropertyName = String.format(PROPERTY_NAME_NEW_ATTACHMENT_FILE, event.getPersonIndex());

        ProtocolAttachmentPersonnel newAttachment = event.getProtocolAttachmentPersonnel();
        ProtocolPerson person = ((ProtocolDocument) event.getDocument()).getProtocol().getProtocolPerson(event.getPersonIndex());
        isValid &= validType(newAttachment.getTypeCode(), newAttachment.getGroupCode(), typePropertyName);
        isValid &= duplicateType(newAttachment.getTypeCode(), person, typePropertyName);
        isValid &= validDescription(newAttachment.getDescription(), newAttachment.getTypeCode(), descriptionPropertyName);
        isValid &= validFile(newAttachment.getNewFile(), filePropertyName);
        
        return isValid;
    }

    private boolean validType(String typeCode, String groupCode, String propertyName) {
        assert !StringUtils.isEmpty(groupCode);
        
        for (ProtocolAttachmentType type : getProtocolAttachmentService().getTypesForGroup(groupCode)) {
            if (StringUtils.equals(type.getCode(), typeCode)) {
                return true;
            }
        }
        
        GlobalVariables.getMessageMap().putError(propertyName, 
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_INVALID_TYPE);
        return false;
    }
    
    private boolean duplicateType(String typeCode, ProtocolPerson person,  String propertyName) {
        for (ProtocolAttachmentPersonnel attachment : person.getAttachmentPersonnels()) {
            if (StringUtils.equals(attachment.getTypeCode(), typeCode)) {
                GlobalVariables.getMessageMap().putError(propertyName, 
                        KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_DUPLICATE_TYPE);
                return false;
            }
        }
        
        return true;
    }

    private boolean validDescription(String description, String typeCode, String propertyName) {
        if (StringUtils.isEmpty(description)) {
            GlobalVariables.getMessageMap().putError(propertyName, 
                    KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_INVALID_DESCRIPTION);
            return false;
        }

        return true;
    }

    private boolean validFile(FormFile file, String propertyName) {
        byte[] fileData;
        try {
            fileData = file.getFileData();
        }
        catch (IOException e) {
            e.printStackTrace();
            GlobalVariables.getMessageMap().putError(propertyName, 
                    KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_EMPTY_FILE);
            return false;
        }
        
        // Check that file is not empty
        if ((fileData == null) || (fileData.length == 0)) {
            // empty file
            GlobalVariables.getMessageMap().putError(propertyName, 
                    KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_EMPTY_FILE);
            return false;
        }

        return true;
    }
    
    /**
     * This method is to get protocol attachment service
     * @return ProtocolAttachmentService
     */
    private ProtocolAttachmentService getProtocolAttachmentService() {
        return KraServiceLocator.getService(ProtocolAttachmentService.class);
    }
}
