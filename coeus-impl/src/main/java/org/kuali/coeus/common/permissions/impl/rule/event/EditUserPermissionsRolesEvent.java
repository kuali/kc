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
