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
package org.kuali.kra.proposaldevelopment.document.authorization;

import org.kuali.kra.authorization.KcWorkflowDocumentAuthorizer;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class ProposalDevelopmentWorkflowDocumentAuthorizer extends KcWorkflowDocumentAuthorizer {

    @Override
    protected Permissionable getPermissionable(String documentId) {
        try {
            return (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(documentId);
        }
        catch (WorkflowException e) {
            LOG.warn("Unable to load ProposalDevelopmentDocument - " + documentId + " for workflow authorizer", e);
        }
        return null;
    }

}
