/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.permissions.impl.rule.event;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles;
import org.kuali.coeus.common.permissions.impl.rule.PermissionsRule;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * The EditPermissionsProposalRolesEvent is generated when the roles for a
 * user are to be modified via the Edit Roles web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class EditUserPermissionsRolesEvent extends KcDocumentEventBase {
    
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
    
    @Override
    public void validate() {
        super.validate();
        if (this.editRoles == null) {
            throw new IllegalArgumentException("invalid (null) edit roles");
        }
    }
    
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

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processEditPermissionsUserRolesBusinessRules(this.getDocument(), 
                                                                                     this.users, this.editRoles);
    }
}
