/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.personmasschange.document.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

public class PersonMassChangeDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> editModes) {
        Set<String> newEditModes = new HashSet<String>();
        newEditModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
        return newEditModes;
    }

    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }

    @Override
    public boolean canOpen(Document document, Person user) {
        return true;
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
