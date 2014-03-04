/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class AddCoiReviewerRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddCoiReviewerEvent> {
    
    private transient KcPersonService kcPersonService;
    
    public AddCoiReviewerRule() {
        kcPersonService = KcServiceLocator.getService(KcPersonService.class);
    }

    @Override
    public boolean processRules(AddCoiReviewerEvent event) {
        return validateRequired(event) && validateNonDuplicate(event);
    }
    
    private boolean validateRequired(AddCoiReviewerEvent event) {
        boolean valid = false;
        if (StringUtils.isNotBlank(event.getCoiUserRole().getUserId())
                && getKcPersonService().getKcPersonByUserName(event.getCoiUserRole().getUserId()) != null) {
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

    protected KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
