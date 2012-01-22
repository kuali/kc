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
package org.kuali.kra.irb.correspondence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

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
    public boolean processAddDefaultProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceType correspondenceType,
            ProtocolCorrespondenceTemplate newCorrespondenceTemplate, int index) throws IOException {
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
    public boolean processAddProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceType correspondenceType,
            ProtocolCorrespondenceTemplate newCorrespondenceTemplate, int index) throws IOException {
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
    public boolean processReplaceProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceType correspondenceType,
            ProtocolCorrespondenceTemplate newCorrespondenceTemplate, int typeIndex, int templateIndex) throws IOException {
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
    public boolean processSaveProtocolCorrespondenceTemplateRules(List<ProtocolCorrespondenceType> protocolCorrespondenceTypes) throws IOException {
        boolean valid = true;
        for (ProtocolCorrespondenceType protocolCorrespondenceType : protocolCorrespondenceTypes) {
            int typeIndex = protocolCorrespondenceTypes.indexOf(protocolCorrespondenceType);
            List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates = protocolCorrespondenceType.getCommitteeProtocolCorrespondenceTemplates();

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
    private boolean hasInvalidCommittee(List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates, int typeIndex) {
        boolean hasInvalidCommittee = false;
        for (ProtocolCorrespondenceTemplate protocolCorrespondenceTemplate : protocolCorrespondenceTemplates) {
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
    private boolean hasDuplicateCommittee(List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates, int typeIndex) {
        List<ProtocolCorrespondenceTemplate> tmpTemplates = new ArrayList<ProtocolCorrespondenceTemplate>();
        for (ProtocolCorrespondenceTemplate protocolCorrespondenceTemplate : protocolCorrespondenceTemplates) {
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
    private boolean duplicateCommittee(List<ProtocolCorrespondenceTemplate> correspondenceTemplates, String committeeId, String propertyName) {
        boolean duplicate = false;
        for (ProtocolCorrespondenceTemplate correspondenceTemplate : correspondenceTemplates) {
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
            		&& !contentType.equals(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_2)) {
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
    private boolean validTemplates(ProtocolCorrespondenceType protocolCorrespondenceType, int typeIndex) throws IOException {
        boolean isValid = true;
        
        ProtocolCorrespondenceTemplate defaultTemplate = protocolCorrespondenceType.getDefaultProtocolCorrespondenceTemplate();
        if (defaultTemplate != null) {
        	if ((defaultTemplate.getCorrespondenceTemplate().length == 0) 
        			|| StringUtils.isBlank(defaultTemplate.getFileName())) { 
                String filePropertyName = String.format(PROPERTY_NAME_NEW_DEFAULT_TEMPLATE_FILE, typeIndex);
                GlobalVariables.getMessageMap().putError(filePropertyName, KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE);
                isValid = false;
        	}
        }
      

        List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates = protocolCorrespondenceType.getCommitteeProtocolCorrespondenceTemplates();  
        for (ProtocolCorrespondenceTemplate protocolCorrespondenceTemplate : protocolCorrespondenceTemplates) {
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
