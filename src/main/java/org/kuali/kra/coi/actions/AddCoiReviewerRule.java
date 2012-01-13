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
package org.kuali.kra.coi.actions;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class AddCoiReviewerRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddCoiReviewerEvent>{

    @Override
    public boolean processRules(AddCoiReviewerEvent event) {
        return validateRequired(event) && validateNonDuplicate(event);
    }
    
    private boolean validateRequired(AddCoiReviewerEvent event) {
        boolean valid = false;
        if (StringUtils.isNotBlank(event.getCoiUserRole().getUserId())) {
            valid = true;
        } else {
            GlobalVariables.getMessageMap().putError("disclosureActionHelper.newCoiUserRole.userId", 
                    KeyConstants.ERROR_REQUIRED, new String[] { "User Name" });  
        }
        
        return valid;
    }

    private boolean validateNonDuplicate(AddCoiReviewerEvent event) {
        boolean valid = true;
        if (CollectionUtils.isNotEmpty(event.getCoiDisclosure().getCoiUserRoles())) {
            for (CoiUserRole userRole : event.getCoiDisclosure().getCoiUserRoles()) {
                if (StringUtils.equalsIgnoreCase(userRole.getUserId(), event.getCoiUserRole().getUserId())) {
                    valid = false;
                    GlobalVariables.getMessageMap().putError("disclosureActionHelper.newCoiUserRole.userId", 
                            "error.duplicate.entry", new String[] { "User Name" });                    
                    break;
                }
            }
        }
        return valid;
    }
}
