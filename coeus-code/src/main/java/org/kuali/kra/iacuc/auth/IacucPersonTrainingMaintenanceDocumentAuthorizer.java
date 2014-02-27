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
package org.kuali.kra.iacuc.auth;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.krad.util.KRADConstants;

public class IacucPersonTrainingMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {
    private static final long serialVersionUID = -2238428296264361269L;
    public static final String PERMISSION_MAINTAIN_PERSON_TRAINING = "Maintain IACUC Person Training";
    public static final String NAME_SPACE = "KC-IACUC";
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        Map<String, String> permissionDetails = new HashMap<String, String>();
        permissionDetails.put(KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME, documentTypeName);
        permissionDetails.put(KcKimAttributes.UNIT_NUMBER, "*");
        boolean retVal =  getPermissionService().isAuthorized(user.getPrincipalId(), NAME_SPACE, PERMISSION_MAINTAIN_PERSON_TRAINING, permissionDetails);
        return retVal;
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public boolean canMaintain(Object dataObject, Person user) {
        Map<String, String> permissionDetails = new HashMap<String, String>(2);
        permissionDetails.put(KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME,
                getDocumentDictionaryService().getMaintenanceDocumentTypeName(
                        dataObject.getClass()));
        permissionDetails.put(KRADConstants.MAINTENANCE_ACTN, NAME_SPACE);
        return !permissionExistsByTemplate(NAME_SPACE,
                KimConstants.PermissionTemplateNames.INITIATE_DOCUMENT,
                permissionDetails)
                || isAuthorizedByTemplate(
                        dataObject,
                        NAME_SPACE,
                        KimConstants.PermissionTemplateNames.INITIATE_DOCUMENT,
                        user.getPrincipalId(), permissionDetails, null);
    }
}
