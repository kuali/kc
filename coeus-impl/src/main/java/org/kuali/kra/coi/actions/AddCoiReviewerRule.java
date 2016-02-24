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
