/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.common.permissions.rule;

import java.util.List;

import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Defines the Business Rule for processing Permissions.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PermissionsRule extends BusinessRule {
    
    /**
     * Determines the legality of adding a user with a given role to a document.
     * 
     * @param document the document.
     * @param users the current list of users who have a role in the document
     * @param newUser the new user to add to the document
     * @return true if the addition is valid; otherwise false.
     */
    public boolean processAddPermissionsUserBusinessRules(Document document,
                                                          List<User> users,
                                                          PermissionsUser newUser);
    /**
     * Determines the legality of deleting a user from the given document.
     * 
     * @param document the document.
     * @param users list of current users who have a role in the document
     * @param index the index into "users" of the user to delete
     * @return true if the deletion is valid; otherwise false.
     */
    public boolean processDeletePermissionsUserBusinessRules(Document document,
                                                             List<User> users,
                                                             int index);
    
    /**
     * Determines if it is legal to edit the roles for a user.
     * 
     * @param document the document.
     * @param users list of current users who have a role in the document
     * @param editRoles the new set of roles for the user
     * @return true if the role change is valid; otherwise false.
     */
    public boolean processEditPermissionsUserRolesBusinessRules(Document document,
                                                                List<User> users,
                                                                PermissionsUserEditRoles editRoles);
}
