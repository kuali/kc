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
package org.kuali.kra.protocol.personnel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentTypeBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.IOException;


public abstract class ProtocolAttachmentPersonnelRuleBase extends KcTransactionalDocumentRuleBase implements AddProtocolAttachmentPersonnelRule {

    private static final Log LOG = LogFactory.getLog(ProtocolAttachmentPersonnelRuleBase.class);

    protected static final String PROPERTY_NAME_NEW_ATTACHMENT_TYPE = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].typeCode";
    protected static final String PROPERTY_NAME_NEW_ATTACHMENT_DESCRIPTION = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].description";
    protected static final String PROPERTY_NAME_NEW_ATTACHMENT_FILE = "personnelHelper.newProtocolAttachmentPersonnels[%1$s].newFile";

    
    @Override
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
            LOG.error(e.getMessage(), e);
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
