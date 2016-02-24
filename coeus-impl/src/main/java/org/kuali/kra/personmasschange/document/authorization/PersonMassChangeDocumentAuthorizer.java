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
package org.kuali.kra.personmasschange.document.authorization;

import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.*;

public class PersonMassChangeDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> editModes) {
        Set<String> newEditModes = new HashSet<String>();
        newEditModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
        return newEditModes;
    }

    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        String nameSpaceCode = KRADConstants.KUALI_RICE_SYSTEM_NAMESPACE;
        Map<String, String> permissionDetails = new HashMap<String, String>();
        permissionDetails.put(KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME, documentTypeName);
        return getPermissionService().isAuthorizedByTemplate(user.getPrincipalId(), nameSpaceCode,
                KimConstants.PermissionTemplateNames.INITIATE_DOCUMENT, permissionDetails,
                Collections.<String, String>emptyMap());
    }

    @Override
    public boolean canOpen(Document document, Person user) {
        return canInitiate(document.getDocumentHeader().getWorkflowDocument().getDocumentTypeName(), user);
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }


}
