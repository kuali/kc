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
package org.kuali.kra.questionnaire.question;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.questionnaire.QuestionnaireAuthorizationService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.kns.util.KNSConstants;

public class QuestionMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {

    public Set<String> getDocumentActions(Document document, Person user, Set<String> documentActions) {
        return getDocumentActions(document);
    }

    protected Set<String> getDocumentActions(Document document) {
        Set<String> documentActions = new HashSet<String>();
        if (KraServiceLocator.getService(QuestionnaireAuthorizationService.class)
                .hasPermission(PermissionConstants.MODIFY_QUESTION)
                && (document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(
                        KEWConstants.ROUTE_HEADER_INITIATED_CD) || document.getDocumentHeader().getWorkflowDocument()
                        .getRouteHeader().getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_SAVED_CD))) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_EDIT);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ROUTE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_SAVE);
            if (document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(
                    KEWConstants.ROUTE_HEADER_SAVED_CD)) {
                documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
            }
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CANCEL);
        } else if (KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(
                PermissionConstants.MODIFY_QUESTION)
                && (document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus()
                        .equals(KEWConstants.ROUTE_HEADER_FINAL_CD))) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
        } else if (KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(
                PermissionConstants.VIEW_QUESTION)
                || KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(
                        PermissionConstants.MODIFY_QUESTION)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
        } else {
            throw new RuntimeException("Don't have permission to edit/view Questionnaire");
        }

        return documentActions;
    }

}
