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
package org.kuali.kra.irb.auth;

import org.kuali.coeus.common.framework.auth.KcWorkflowDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.irb.Protocol;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProtocolWorkflowDocumentAuthorizer extends KcWorkflowDocumentAuthorizerBase {

    @Override
    protected Permissionable getPermissionable(String documentId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("documentNumber", documentId);
        Collection<Protocol> protocols = getBusinessObjectService().findMatching(Protocol.class, values);
        if (protocols != null && !protocols.isEmpty()) {
            return protocols.iterator().next();
        } else {
            return null;
        }
    }

}
