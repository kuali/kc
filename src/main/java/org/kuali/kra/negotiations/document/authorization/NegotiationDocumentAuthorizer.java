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
package org.kuali.kra.negotiations.document.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * 
 * This class handles the authorization for the Negotiation Document.
 */
public class NegotiationDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    /**
     * 
     * Constructs a NegotiationDocumentAuthorizer.java.
     */
    public NegotiationDocumentAuthorizer() {
        super();
    }

    
    /**
     * 
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        String userId = user.getPrincipalId();
        NegotiationDocument negotiationDocument = (NegotiationDocument) document;
        if (negotiationDocument.getNegotiation().getNegotiationId() == null) {
            if (canCreateNegotiation(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }  else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } else {
            if (canExecuteNegotiationTask(userId, negotiationDocument, TaskName.MODIFY_COMMITTEE)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else if (canExecuteNegotiationTask(userId, negotiationDocument, TaskName.VIEW_COMMITTEE)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        }
        return editModes;
    }
    
    private boolean canCreateNegotiation(Person user) {
        return true;
    }
    
    
    
    /**
     * 
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        // TODO Auto-generated method stub
        return true;
    }
    
    /**
     * 
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        // TODO Auto-generated method stub
        return true;
    }
    
    private boolean canExecuteNegotiationTask(String userId, NegotiationDocument doc, String taskName) {
        return true;
    }

}
