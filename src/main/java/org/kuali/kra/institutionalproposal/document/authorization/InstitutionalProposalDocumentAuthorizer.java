/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.document.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * This class is the Institutional Proposal Document Authorizer.  It determines the edit modes and
 * document actions for all institutional proposal documents.
 */
public class InstitutionalProposalDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> oldEditModes) {
        Set<String> editModes = new HashSet<String>();
        if (super.canEdit(document)) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
        } else {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
        }
        return editModes;
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        return true;
    }
}
