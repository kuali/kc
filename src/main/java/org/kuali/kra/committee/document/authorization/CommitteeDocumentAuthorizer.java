/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.document.authorization;

import java.util.HashMap;
import java.util.Map;

import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;

/**
 * The document authorizer controls access to the Committee as well as
 * other document associated authorizations.
 */
public class CommitteeDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#getEditMode(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @SuppressWarnings("unchecked")
    public Map getEditMode(Document doc, UniversalUser user) {
        
        /*
         * NOTE: Currently full access is allowed for the initial development work.
         * This will need to be changed when authorization is added.
         */
        Map editModeMap = new HashMap();
        editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
    
        return editModeMap;
    }
}
