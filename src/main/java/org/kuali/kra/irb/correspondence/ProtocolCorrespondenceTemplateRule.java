/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class contains the validation rules for protocol correspondence templates.
 */
public class ProtocolCorrespondenceTemplateRule {

    /**
     * 
     * This method verifies the protocol correspondence template on add.
     * @param correspondenceType
     * @param newCorrespondenceTemplate
     * @param index
     * @return true if the validation is successful, false otherwise
     */
    public boolean processAddProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceType correspondenceType,
            ProtocolCorrespondenceTemplate newCorrespondenceTemplate, int index) {
        boolean valid = true;
        
        String propertyName = "newCorrespondenceTemplates[" + index + "].committeeId";
        
        valid &= committeeSpecified(newCorrespondenceTemplate.getCommitteeId(), propertyName);
        valid &= !duplicateCommittee(correspondenceType.getProtocolCorrespondenceTemplates(), newCorrespondenceTemplate.getCommitteeId(), propertyName);

        return valid;
    }

    /**
     * 
     * This method verifies all protocol correspondence templates at save.
     * @param protocolCorrespondenceTypes
     * @return true if the validation is successful, false otherwise
     */
    public boolean processSaveProtocolCorrespondenceTemplateRules(List<ProtocolCorrespondenceType> protocolCorrespondenceTypes) {
        boolean valid = true;
        for (ProtocolCorrespondenceType protocolCorrespondenceType : protocolCorrespondenceTypes) {
            int typeIndex = protocolCorrespondenceTypes.indexOf(protocolCorrespondenceType);
            List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates = protocolCorrespondenceType.getProtocolCorrespondenceTemplates();

            valid &= !hasInvalidCommittee(protocolCorrespondenceTemplates, typeIndex);
            valid &= !hasDuplicateCommittee(protocolCorrespondenceTemplates, typeIndex);
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
            String propertyName = "correspondenceTypes[" + typeIndex + "].protocolCorrespondenceTemplates[" + templateIndex 
                + "].committeeId";
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
            String propertyName = "correspondenceTypes[" + typeIndex + "].protocolCorrespondenceTemplates[" + templateIndex 
                + "].committeeId";

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
     * @param index - the index of the correspondence type (used for display of the error message).
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
     * This method checks if the committee has a template specified.
     * @param correspondenceTemplates - the correspondence templates against which we are checking.
     * @param committeeId - the committee whose existence is to be checked.
     * @param index - the index of the correspondence type (used for display of the error message).
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
}
