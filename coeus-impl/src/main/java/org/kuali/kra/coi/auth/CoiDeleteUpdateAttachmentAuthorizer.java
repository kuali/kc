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
package org.kuali.kra.coi.auth;


import org.apache.commons.lang3.ObjectUtils;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.role.RoleService;

public class CoiDeleteUpdateAttachmentAuthorizer extends CoiDeleteUpdateNotesAttachmentsAuthorizerBase {

    private RoleService roleService;
    private IdentityService identityService;

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        CoiDisclosureDeleteUpdateAttachmentTask deleteUpdateTask = (CoiDisclosureDeleteUpdateAttachmentTask) task;
        CoiDisclosureAttachment attachment = deleteUpdateTask.getAttachment();      
        String attachmentCreator = attachment.getUpdateUser();
        if (attachment.getOriginalCoiDisclosureId() != null 
                && !ObjectUtils.equals(attachment.getOriginalCoiDisclosureId(), task.getCoiDisclosure().getCoiDisclosureId())) {
            return false;
        } else {
            return isAuthorized(userId, task, attachmentCreator);
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
