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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class contains the validation rules for protocol correspondence templates.
 */
public class ProtocolCorrespondenceTemplateRule {

    private static final String PROPERTY_NAME_NEW_DEFAULT_TEMPLATE_FILE = "newDefaultCorrespondenceTemplates[%1$s].templateFile";
    private static final String PROPERTY_NAME_NEW_COMMITTEE_ID = "newCorrespondenceTemplates[%1$s].committeeId";
    private static final String PROPERTY_NAME_NEW_TEMPLATE_FILE = "newCorrespondenceTemplates[%1$s].templateFile";
    private static final String PROPERTY_NAME_REPLACE_TEMPLATE_FILE = "replaceCorrespondenceTemplates[%1$s].list[%2$s].templateFile";
    private static final String PROPERTY_NAME_COMMITTEE_ID = "correspondenceTypes[%1$s].protocolCorrespondenceTemplates[%2$s].committeeId";
    private static final String PROPERTY_NAME_TEMPLATE_FILE = "correspondenceTypes[%1$s].protocolCorrespondenceTemplates[%2$s].templateFile";
    
    /**
     * 
     * This method verifies the default protocol correspondence template on add.
     * @param correspondenceType
     * @param newCorrespondenceTemplate
     * @param index
     * @return true if the validation is successful, false otherwise
     * @throws IOException 
     */
    public boolean processAddDefaultProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceTypeBase correspondenceType,
            ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate, int index) throws IOException {
        boolean valid = true;
        
        String filePropertyName = String.format(PROPERTY_NAME_NEW_DEFAULT_TEMPLATE_FILE, index);
        
        valid &= validFile(newCorrespondenceTemplate.getTemplateFile(), filePropertyName);

        return valid;
    }

    /**
     * 
     * This method verifies the protocol correspondence template on add.
     * @param correspondenceType
     * @param newCorrespondenceTemplate
     * @param index
     * @return true if the validation is successful, false otherwise
     * @throws IOException 
     */
    public boolean processAddProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceTypeBase correspondenceType,
            ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate, int index) throws IOException {
        boolean valid = true;
        
        String committeePropertyName = String.format(PROPERTY_NAME_NEW_COMMITTEE_ID, index);
        String filePropertyName = String.format(PROPERTY_NAME_NEW_TEMPLATE_FILE, index);
        
        valid &= committeeSpecified(newCorrespondenceTemplate.getCommitteeId(), committeePropertyName);
        valid &= !duplicateCommittee(correspondenceType.getCommitteeProtocolCorrespondenceTemplates(), newCorrespondenceTemplate.getCommitteeId(), 
                committeePropertyName);
        valid &= validFile(newCorrespondenceTemplate.getTemplateFile(), filePropertyName);

        return valid;
    }

    /**
     * 
     * This method verifies the protocol correspondence template on replace.
     * @param correspondenceType
     * @param newCorrespondenceTemplate
     * @param index
     * @return true if the validation is successful, false otherwise
     * @throws IOException 
     */
    public boolean processReplaceProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceTypeBase correspondenceType,
            ProtocolCorrespondenceTemplateBase newCorrespondenceTemplate, int typeIndex, int templateIndex) throws IOException {
        boolean valid = true;

        String committeePropertyName = String.format(PROPERTY_NAME_COMMITTEE_ID, typeIndex, templateIndex);
        String filePropertyName = String.format(PROPERTY_NAME_REPLACE_TEMPLATE_FILE, typeIndex, templateIndex);
        
        valid &= committeeSpecified(newCorrespondenceTemplate.getCommitteeId(), committeePropertyName);
        valid &= validFile(newCorrespondenceTemplate.getTemplateFile(), filePropertyName);

        return valid;
    }

    /**
     * 
     * This method verifies all protocol correspondence templates at save.
     * @param protocolCorrespondenceTypes
     * @return true if the validation is successful, false otherwise
     * @throws IOException 
     */
    public boolean processSaveProtocolCorrespondenceTemplateRules(List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes) throws IOException {
        boolean valid = true;
        for (ProtocolCorrespondenceTypeBase protocolCorrespondenceType : protocolCorrespondenceTypes) {
            int typeIndex = protocolCorrespondenceTypes.indexOf(protocolCorrespondenceType);
            List<ProtocolCorrespondenceTemplateBase> protocolCorrespondenceTemplates = protocolCorrespondenceType.getCommitteeProtocolCorrespondenceTemplates();

            valid &= !hasInvalidCommittee(protocolCorrespondenceTemplates, typeIndex);
            valid &= !hasDuplicateCommittee(protocolCorrespondenceTemplates, typeIndex);
            valid &= validTemplates(protocolCorrespondenceType, typeIndex);
        }
        return valid; 
    }
    
    /**
     * 
     * This method verifies that the protocol correspondence templates have a committee specified.
     * All missing committees are flagged as an error.
     * @param protocolCorrespondenceTemplates
     * @param typeIndex
     * @return
     */
    private boolean hasInvalidCommittee(List<ProtocolCorrespondenceTemplateBase> protocolCorrespondenceTemplates, int typeIndex) {
        boolean hasInvalidCommittee = false;
        for (ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate : protocolCorrespondenceTemplates) {
            int templateIndex = protocolCorrespondenceTemplates.indexOf(protocolCorrespondenceTemplate);
            String propertyName = String.format(PROPERTY_NAME_COMMITTEE_ID, typeIndex, templateIndex);
            hasInvalidCommittee |= !committeeSpecified(protocolCorrespondenceTemplate.getCommitteeId(), propertyName);
        }
        return hasInvalidCommittee;
    }
    
    /**
     * 
     * This method verifies that the protocol correspondence templates do not contain duplicates.
     * Only the first duplicate is being flagged as an error.
     * @param protocolCorrespondenceTemplates
     * @param typeIndex, the index of the protocol correspondence type (used for error messages)
     * @return true if duplicates exists, false otherwise
     */
    private boolean hasDuplicateCommittee(List<ProtocolCorrespondenceTemplateBase> protocolCorrespondenceTemplates, int typeIndex) {
        List<ProtocolCorrespondenceTemplateBase> tmpTemplates = new ArrayList<ProtocolCorrespondenceTemplateBase>();
        for (ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate : protocolCorrespondenceTemplates) {
            int templateIndex = protocolCorrespondenceTemplates.indexOf(protocolCorrespondenceTemplate);
            String propertyName = String.format(PROPERTY_NAME_COMMITTEE_ID, typeIndex, templateIndex);

            if (duplicateCommittee(tmpTemplates, protocolCorrespondenceTemplate.getCommitteeId(), propertyName)) {
                return true;
            }
            
            tmpTemplates.add(protocolCorrespondenceTemplate);
        }
        
        return false;
    }

    /**
     * This method checks if a committee has been specified.
     * @param committeeId
     * @param propertyName - the property that is being verified (used for error message).
     * @return true if a committee has been specified, false otherwise.
     */
    private boolean committeeSpecified(String committeeId, String propertyName) {
        if (StringUtils.isBlank(committeeId)) {
            GlobalVariables.getMessageMap().putError(propertyName, 
                    KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method checks if a template is already specified for the committee.
     * @param correspondenceTemplates - the correspondence templates against which we are checking.
     * @param committeeId - the committee whose existence is to be checked.
     * @param propertyName - the property that is being verified (used for error message).
     * @return true if the committee has a template defined, false otherwise.
     */
    private boolean duplicateCommittee(List<ProtocolCorrespondenceTemplateBase> correspondenceTemplates, String committeeId, String propertyName) {
        boolean duplicate = false;
        for (ProtocolCorrespondenceTemplateBase correspondenceTemplate : correspondenceTemplates) {
            if (correspondenceTemplate.getCommitteeId().equals(committeeId)) {
                GlobalVariables.getMessageMap().putError(propertyName, 
                        KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_DUPLICATE);
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
    
    /**
     * 
     * This method checks that a valid template file has been specified on adds.
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
            GlobalVariables.getMessageMap().putError(propertyName, 
                    KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE);
            isValid = false;
        }
    
        if (isValid) {
            // Check that file is of the correct type
            String contentType = file.getContentType();
            if (!contentType.equals(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1) 
            		&& !contentType.equals(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_2)
            		&& !contentType.equals(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_3)) {
                // wrong file type
                GlobalVariables.getMessageMap().putError(propertyName, 
                        KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE_TYPE);
                isValid = false;
            }
        }
    
        return isValid;
    }
    
    /**
     * 
     * This method checks that template data of all templates are valid
     * @param protocolCorrespondenceTemplates
     * @param typeIndex
     * @return true if all files are valid of the templates, false otherwise
     * @throws IOException
     */
    private boolean validTemplates(ProtocolCorrespondenceTypeBase protocolCorrespondenceType, int typeIndex) throws IOException {
        boolean isValid = true;
        
        ProtocolCorrespondenceTemplateBase defaultTemplate = protocolCorrespondenceType.getDefaultProtocolCorrespondenceTemplate();
        if (defaultTemplate != null) {
        	if ((defaultTemplate.getCorrespondenceTemplate().length == 0) 
        			|| StringUtils.isBlank(defaultTemplate.getFileName())) { 
                String filePropertyName = String.format(PROPERTY_NAME_NEW_DEFAULT_TEMPLATE_FILE, typeIndex);
                GlobalVariables.getMessageMap().putError(filePropertyName, KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE);
                isValid = false;
        	}
        }
      

        List<ProtocolCorrespondenceTemplateBase> protocolCorrespondenceTemplates = protocolCorrespondenceType.getCommitteeProtocolCorrespondenceTemplates();  
        for (ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate : protocolCorrespondenceTemplates) {
            if ((protocolCorrespondenceTemplate.getCorrespondenceTemplate() == null)
                    || (protocolCorrespondenceTemplate.getCorrespondenceTemplate().length == 0)
                    || StringUtils.isBlank(protocolCorrespondenceTemplate.getFileName())) {
                int templateIndex = protocolCorrespondenceTemplates.indexOf(protocolCorrespondenceTemplate);
                String filePropertyName = String.format(PROPERTY_NAME_TEMPLATE_FILE, typeIndex, templateIndex); 
                GlobalVariables.getMessageMap().putError(filePropertyName, KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE);
                isValid = false;
            }

        }
        return isValid;
    }

}
