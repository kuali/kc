/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.document.authorization;

import java.util.Map;

import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.workflow.service.KualiWorkflowDocument;

public class BudgetDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    /**
     * @see org.kuali.core.authorization.DocumentAuthorizer#getEditMode(org.kuali.core.document.Document,
     *      org.kuali.core.bo.user.KualiUser)
     */
    @Override
    public Map getEditMode(Document d, UniversalUser u) {
        Map editModeMap = super.getEditMode(d, u);
        // For now if they can access they can edit (even in Final mode) - will change when roles come along.
        if (hasInitiateAuthorization(d, u)) {
            editModeMap.remove(AuthorizationConstants.EditMode.VIEW_ONLY);
            editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, "TRUE");
        }
        
        return editModeMap;
    }
    
    /**
     * Adds settings for transactional-document-specific flags.
     * 
     * @see org.kuali.core.document.authorization.DocumentAuthorizer#getDocumentActionFlags(Document, UniversalUser)
     */
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
        DocumentActionFlags flags = super.getDocumentActionFlags(document, user);
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        boolean hasInitiateAuthorization = hasInitiateAuthorization(document, user);
        
        // Allow finalized budgets to be edited
        if (workflowDocument.stateIsFinal()) {
            flags.setCanSave(hasInitiateAuthorization);
        }
        
        return flags;
    }

}
