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
package org.kuali.coeus.sys.impl.workflow;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.sys.impl.workflow.action.KcReturnToPreviousNodeAction;
import org.kuali.rice.kew.actions.ReturnToPreviousNodeAction;
import org.kuali.rice.kew.api.exception.InvalidActionTakenException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.impl.WorkflowDocumentServiceImpl;
import org.kuali.rice.kim.api.identity.principal.Principal;

public class KcWorkflowDocumentServiceImpl extends WorkflowDocumentServiceImpl {

    @Override
    public DocumentRouteHeaderValue returnDocumentToPreviousNode(String principalId, DocumentRouteHeaderValue routeHeader, String destinationNodeName, String annotation)
            throws InvalidActionTakenException {

        if (isProposalDevelopmentDocument(routeHeader)) {
            Principal principal = loadPrincipal(principalId);
            ReturnToPreviousNodeAction action = new KcReturnToPreviousNodeAction(routeHeader, principal, annotation, destinationNodeName, true);
            action.performAction();
            return finish(routeHeader);
        } else {
            return super.returnDocumentToPreviousNode(principalId, routeHeader, destinationNodeName, annotation);
        }
    }

    protected boolean isProposalDevelopmentDocument(DocumentRouteHeaderValue routeHeader) {
        return routeHeader.getDocumentType() != null &&
                routeHeader.getDocumentType().getName().equalsIgnoreCase(ProposalDevelopmentConstants.KewConstants.PROPOSAL_DEVELOPMENT_DOCUMENT);
    }
}
