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
package org.kuali.kra.coi.auth;


import org.apache.commons.lang3.ObjectUtils;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.role.RoleService;

public class CoiDeleteUpdateNoteAuthorizer extends CoiDeleteUpdateNotesAttachmentsAuthorizerBase {

    private RoleService roleService;
    private IdentityService identityService;

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        CoiDisclosureDeleteUpdateNoteTask deleteUpdateTask = (CoiDisclosureDeleteUpdateNoteTask) task;
        CoiDisclosureNotepad note = deleteUpdateTask.getNote();
        String noteCreator = note.getUpdateUser();
        if (note.getOriginalCoiDisclosureId() != null 
                && !ObjectUtils.equals(note.getOriginalCoiDisclosureId(), task.getCoiDisclosure().getCoiDisclosureId())) {
            return false;
        } else {
            return isAuthorized(userId, task, noteCreator);
        }
    }
  
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
    public IdentityService getIdentityService() {
        return identityService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

   
}
