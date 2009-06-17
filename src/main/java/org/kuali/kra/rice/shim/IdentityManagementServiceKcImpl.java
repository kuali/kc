/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.rice.shim;

import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kim.service.impl.IdentityManagementServiceImpl;

/**
 * This is an implementation that lets you do anything if you're logged in as 'quickstart.'
 * Used by the KC Document Authorizers temporarily, until we put all of our AuthZ data
 * in the new KIM tables.
 */
public class IdentityManagementServiceKcImpl extends IdentityManagementServiceImpl implements IdentityManagementService {
    
    @Override
    public boolean hasPermission(String principalId, String namespaceCode, String permissionName, AttributeSet permissionDetails) {
        return true;
    }
    
    @Override
    public boolean isAuthorized( String principalId, String namespaceCode, String permissionName, AttributeSet permissionDetails, AttributeSet qualification ) {
        return true;
    }

    @Override
    public boolean hasPermissionByTemplateName(String principalId, String namespaceCode, String permissionTemplateName, AttributeSet permissionDetails) {
        return true;
    }
    
    @Override
    public boolean isAuthorizedByTemplateName(String principalId, String namespaceCode, String permissionTemplateName, AttributeSet permissionDetails, AttributeSet qualification ) {
        return true;
    }
}
