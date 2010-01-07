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

import java.util.List;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class contains the validation rules for protocol correspondence templates.
 */
public class ProtocolCorrespondenceTemplateRule {

    /**
     * 
     * This contains the validation rules of a protocol correspondence template.
     * @param correspondenceType
     * @param newCorrespondenceTemplate
     * @param index
     * @return true if the validation is successful, false otherwise
     */
    public boolean processProtocolCorrespondenceTemplateRules(ProtocolCorrespondenceType correspondenceType,
            ProtocolCorrespondenceTemplate newCorrespondenceTemplate, int index) {
        boolean valid = true;
        
        valid &= committeeSpecified(newCorrespondenceTemplate.getCommitteeIdFk(), index);
        valid &= !duplicateCommittee(correspondenceType.getProtocolCorrespondenceTemplates(), newCorrespondenceTemplate.getCommitteeIdFk(), index);

        return valid;
    }

    /**
     * This method checks if a committee has been specified.
     * @param committeeIdFk
     * @param index - the index of the correspondence type (used for display of the error message).
     * @return true if a committee has been specified, false otherwise.
     */
    private boolean committeeSpecified(Long committeeIdFk, int index) {
        if (committeeIdFk == null) {
            GlobalVariables.getMessageMap().putError("newCorrespondenceTemplates[" + index + "].committeeIdFk", 
                    KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method checks if the committee has a template specified.
     * @param correspondenceTemplates - the correspondence templates against which we are checking.
     * @param committeeIdFk - the committee whose existence is to be checked.
     * @param index - the index of the correspondence type (used for display of the error message).
     * @return true if the committee has a template defined, false otherwise.
     */
    private boolean duplicateCommittee(List<ProtocolCorrespondenceTemplate> correspondenceTemplates, Long committeeIdFk, int index) {
        boolean duplicate = false;
        for (ProtocolCorrespondenceTemplate correspondenceTemplate : correspondenceTemplates) {
            if (correspondenceTemplate.getCommitteeIdFk().equals(committeeIdFk)) {
                GlobalVariables.getMessageMap().putError("newCorrespondenceTemplates[" + index + "].committeeIdFk", 
                        KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_DUPLICATE);
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
}
