/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.auth;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.KcWorkflowDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class
        ProposalDevelopmentWorkflowDocumentAuthorizer extends KcWorkflowDocumentAuthorizerBase {

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
