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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashSet;
import java.util.Set;

public class UnitMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {

    private static final String UNIT = "Unit";
    
    private KcPersonService kcPersonService;
    private UnitAuthorizationService unitAuthorizationService;
    
    @Override
    public Set<String> getDocumentActions(Document document, Person user, Set<String> documentActions) {
        Set<String> actions = getDocumentActions(document);
        actions = super.getDocumentActions(document, user, actions);
        return actions;
    }

    protected Set<String> getDocumentActions(Document document) {
        Set<String> documentActions = new HashSet<String>();
        
        String personId = getKcPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPerson().getPrincipalId()).getPersonId();
        boolean hasModifyPermission = getUnitAuthorizationService().hasPermission(personId, "KC-UNT", PermissionConstants.MODIFY_UNIT);
        boolean hasAddPermission = getUnitAuthorizationService().hasPermission(personId, "KC-UNT", PermissionConstants.ADD_UNIT);

        if (hasModifyPermission) {
            documentActions = getDocumentActionsWithModifyPermission(document);
        } else if (hasAddPermission) {
            documentActions = getDocumentActionsWithAddPermission(document);
        } else {
            documentActions = getDocumentActionsWithViewPermission(document);
        }
        return documentActions;
    }
    
    private Set<String> getDocumentActionsWithModifyPermission(Document document) {
        Set<String> documentActions = new HashSet<String>();
        if (document.getDocumentHeader().getWorkflowDocument().isInitiated() || document.getDocumentHeader().getWorkflowDocument().isSaved()) {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_SAVE);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CANCEL);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_ROUTE);
        } else {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
        }
        return documentActions;
    }
    
    private Set<String> getDocumentActionsWithAddPermission(Document document) {
        Set<String> documentActions = new HashSet<String>();
        String maintenanceAction = ((MaintenanceDocumentBase) document).getNewMaintainableObject().getMaintenanceAction();
        if (document.getDocumentHeader().getWorkflowDocument().isInitiated()) {
            if (maintenanceAction.equals(KRADConstants.MAINTENANCE_COPY_ACTION)) {
                throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Copy", UNIT);
            } else {
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_SAVE);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_CANCEL);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_ROUTE);
            }
        } else {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
        }
        return documentActions;
    }

    private Set<String> getDocumentActionsWithViewPermission(Document document) {
        Set<String> documentActions = new HashSet<String>();
        String maintenanceAction = ((MaintenanceDocumentBase) document).getNewMaintainableObject().getMaintenanceAction();
        if (document.getDocumentHeader().getWorkflowDocument().isInitiated()) {
            if (maintenanceAction.equals(KRADConstants.MAINTENANCE_COPY_ACTION)) {
                throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Copy", UNIT);
            } else if (maintenanceAction.equals(KRADConstants.MAINTENANCE_NEW_ACTION)) {
                throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Create", UNIT);
            } else {
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_RELOAD);
                documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
            }
        } else {
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KRADConstants.KUALI_ACTION_CAN_CLOSE);
        }
        return documentActions;
    }
    
    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public UnitAuthorizationService getUnitAuthorizationService() {
        if (unitAuthorizationService == null) {
            unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
        }
        return unitAuthorizationService;
    }
    
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

}