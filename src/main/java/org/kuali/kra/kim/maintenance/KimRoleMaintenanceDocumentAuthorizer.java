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
package org.kuali.kra.kim.maintenance;

import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentRestrictions;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentRestrictionsBase;

/**
 * When a role is initially created, its name, role type, and descend flag can be
 * set.  Once created, those values cannot be modified.
 */
public class KimRoleMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {

    /**
     * @see org.kuali.core.document.authorization.MaintenanceDocumentAuthorizerBase#getFieldAuthorizations(org.kuali.core.document.MaintenanceDocument, org.kuali.core.bo.user.UniversalUser)
     */
    //@Override
    public MaintenanceDocumentRestrictions getFieldAuthorizations(MaintenanceDocument document, UniversalUser user) {

        MaintenanceDocumentRestrictions auths = new MaintenanceDocumentRestrictionsBase(); // TODO Hook up in Spring
        KimRole role = (KimRole) document.getNewMaintainableObject().getBusinessObject();

        // The ID of a role is null only for creation.  Therefore, if it isn't null,
        // then it is a modification and we must set some properties to be read-only.
        
        if (role.getId() != null) {
            auths.addReadOnlyField("name");
            auths.addReadOnlyField("roleTypeCode");
            auths.addReadOnlyField("descend");
        }

        return auths;
    }
}
