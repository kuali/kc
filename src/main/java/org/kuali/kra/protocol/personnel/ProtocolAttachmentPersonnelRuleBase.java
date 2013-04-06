/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.protocol.personnel;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentTypeBase;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;


public abstract class ProtocolAttachmentPersonnelRuleBase extends ResearchDocumentRuleBase implements AddProtocolAttachmentPersonnelRule {
    protected static final String OTHER_TYPE_CODE = "9";

    protected static final String PROPERTY_NAME_NEW_ATTACHMENT_TYPE = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].typeCode";
    protected static final String PROPERTY_NAME_NEW_ATTACHMENT_DESCRIPTION = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].description";
    protected static final String PROPERTY_NAME_NEW_ATTACHMENT_FILE = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].newFile";

    
    /**
     * 
     * @see org.kuali.kra.protocol.personnel.AddProtocolAttachmentPersonnelRule#processAddProtocolAttachmentPersonnelRules(org.kuali.kra.protocol.personnel.AddProtocolAttachmentPersonnelEvent)
     */
    public boolean processAddProtocolAttachmentPersonnelRules(AddProtocolAttachmentPersonnelEvent event) {
        boolean isValid = true;
        
        String typePropertyName = String.format(PROPERTY_NAME_NEW_ATTACHMENT_TYPE, event.getPersonIndex());
        String descriptionPropertyName = String.format(PROPERTY_NAME_NEW_ATTACHMENT_DESCRIPTION, event.getPersonIndex());
        String filePropertyName = String.format(PROPERTY_NAME_NEW_ATTACHMENT_FILE, event.getPersonIndex());

        ProtocolAttachmentPersonnelBase newAttachment = event.getProtocolAttachmentPersonnel();
        ProtocolPersonBase person = ((ProtocolDocumentBase) event.getDocument()).getProtocol().getProtocolPerson(event.getPersonIndex());
        isValid &= validType(newAttachment.getTypeCode(), newAttachment.getGroupCode(), typePropertyName);
        isValid &= duplicateType(newAttachment.getTypeCode(), person, typePropertyName);
        isValid &= validDescription(newAttachment.getDescription(), newAttachment.getTypeCode(), descriptionPropertyName);
        isValid &= validFile(newAttachment.getNewFile(), filePropertyName);
        
        return isValid;
    }

    protected boolean validType(String typeCode, String groupCode, String propertyName) {
        assert !StringUtils.isEmpty(groupCode);
        
        for (ProtocolAttachmentTypeBase type : getProtocolAttachmentService().getTypesForGroup(groupCode)) {
            if (StringUtils.equals(type.getCode(), typeCode)) {
                return true;
            }
        }
        
        GlobalVariables.getMessageMap().putError(propertyName, 
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_INVALID_TYPE);
        return false;
    }
    
    protected boolean duplicateType(String typeCode, ProtocolPersonBase person,  String propertyName) {
        for (ProtocolAttachmentPersonnelBase attachment : person.getAttachmentPersonnels()) {
            if (StringUtils.equals(attachment.getTypeCode(), typeCode)) {
                GlobalVariables.getMessageMap().putError(propertyName, 
                        KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_DUPLICATE_TYPE);
                return false;
            }
        }
        
        return true;
    }

    protected boolean validDescription(String description, String typeCode, String propertyName) {
        if (StringUtils.isEmpty(description)) {
            GlobalVariables.getMessageMap().putError(propertyName, 
                    KeyConstants.ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_INVALID_DESCRIPTION);
            return false;
        }

        return true;
    }

    protected boolean validFile(FormFile file, String propertyName) {
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
    protected abstract ProtocolAttachmentService getProtocolAttachmentService();
}
