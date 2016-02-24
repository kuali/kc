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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireAuthorizationService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * This the document authorizer class of question maintenance.
 * Based on user permission and document routing status; the documentactions set is created.
 */
public class QuestionMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {

    /**
     * Create the documentActons based on user permission and document routing status.
     * 
     * @param document
     * @param user
     * @param documentActions - existing document actions (are ignored and wiped out)
     * @return documentActions
     */
    public Set<String> getDocumentActions(Document document, Person user, Set<String> documentActions) {
        return getDocumentActions(document);
    }

    /**
     * Create the documentActons based on user permission and document routing status.
     * 
     * @param document
     * @return documentActions
     */
    protected Set<String> getDocumentActions(Document document) {
        Set<String> documentActions = new HashSet<String>();
        if (getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.MODIFY_QUESTION)
                && (document.getDocumentHeader().getWorkflowDocument().getStatus().getCode().equals(
                        KewApiConstants.ROUTE_HEADER_INITIATED_CD) || document.getDocumentHeader().getWorkflowDocument()
                        .getStatus().getCode().equals(KewApiConstants.ROUTE_HEADER_SAVED_CD))) {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_ROUTE);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_SAVE);
            if (document.getDocumentHeader().getWorkflowDocument().getStatus().getCode().equals(
                    KewApiConstants.ROUTE_HEADER_SAVED_CD)) {
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            }
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CANCEL);
        } else if (getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.MODIFY_QUESTION)
                && (document.getDocumentHeader().getWorkflowDocument().getStatus().getCode()
                        .equals(KewApiConstants.ROUTE_HEADER_FINAL_CD))) {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
        } else if (getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.VIEW_QUESTION)
                || getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.MODIFY_QUESTION)) {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalId(), "Edit/View", "Question");
        }

        return documentActions;
    }

    private QuestionnaireAuthorizationService getQuestionnaireAuthorizationService() {
        return KcServiceLocator.getService(QuestionnaireAuthorizationService.class);
    }
}
