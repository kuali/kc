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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.workflow.LastActionService;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("lastActionService")
public class LastActionServiceImpl implements LastActionService {

    private static final String KR_PRINCIPAL_ID = "1";

    @Autowired
    @Qualifier("kewWorkflowDocumentService")
    private WorkflowDocumentService workflowDocumentService;

    public ActionTaken findLastUserActionTaken(String routeHeaderId) {
        if (StringUtils.isBlank(routeHeaderId)) {
            return null;
        }

        final List<ActionTaken> actionsTaken = workflowDocumentService.getActionsTaken(routeHeaderId);

        if (actionsTaken != null) {
            return actionsTaken.stream()
                    .filter(actionTaken -> actionTaken != null)
                    .filter(actionTaken -> !actionTaken.getPrincipalId().equals(KR_PRINCIPAL_ID))
                    .sorted(Comparator.comparing(ActionTaken::getActionDate).reversed())
                    .findFirst().orElse(null);
        }
        return null;
    }

    public String findLastUserActionTakenPrincipalId(String routeHeaderId) {
        final ActionTaken at = findLastUserActionTaken(routeHeaderId);

        return at != null ? at.getPrincipalId() : null;
    }

    public WorkflowDocumentService getWorkflowDocumentService() {
        return workflowDocumentService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }
}
