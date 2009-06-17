/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.permissions.rule.event;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * The EditPermissionsProposalRolesEvent is generated when the roles for a
 * user are to be modified via the Edit Roles web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class EditUserPermissionsRolesEvent extends KraDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(EditUserPermissionsRolesEvent.class);
    
    private PermissionsUserEditRoles editRoles;
    private List<User> users;
    
    /**
     * Constructs an EditUserPermissionRolesEvent.
     * 
     * @param document the document
     * @param users the current list of users who have roles in the document
     * @param editRoles the new set of roles for a user
     */
    public EditUserPermissionsRolesEvent(Document document, List<User> users, PermissionsUserEditRoles editRoles) {
        super("editing user roles for document " + getDocumentId(document), "", document);
        
        this.users = users;
        this.editRoles = editRoles;
    
        logEvent();
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEventBase#validate()
     */
    public void validate() {
        super.validate();
        if (this.editRoles == null) {
            throw new IllegalArgumentException("invalid (null) edit roles");
        }
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.editRoles == null) {
            logMessage.append("null editRoles");
        }
        else {
            logMessage.append(this.editRoles.toString());
        }

        LOG.debug(logMessage);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processEditPermissionsUserRolesBusinessRules(this.getDocument(), 
                                                                                     this.users, this.editRoles);
    }
}
