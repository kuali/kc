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
package org.kuali.coeus.common.impl.auth.docperm;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccess;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

public class DocumentAccessRule extends MaintenanceDocumentRuleBase {

    private DataObjectService dataObjectService;
    private GlobalVariableService globalVariableService;

    private static final String UNIQUE_DOC_ACCESS_ENTRY = "error.unique.doc.access.entry";

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = super.processCustomRouteDocumentBusinessRules(document);

        final DocumentAccess docAccess = (DocumentAccess) document.getNewMaintainableObject().getDataObject();

        if ((StringUtils.isNotBlank(docAccess.getDocumentNumber()) && StringUtils.isNotBlank(docAccess.getPrincipalId())
                && StringUtils.isNotBlank(docAccess.getRoleName()) && StringUtils.isNotBlank(docAccess.getNamespaceCode()))
                && !KRADConstants.MAINTENANCE_DELETE_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction())) {

            final List<DocumentAccess> existingDocAccesses = getDataObjectService().findMatching(DocumentAccess.class,
                    QueryByCriteria.Builder.fromPredicates(
                        equal("documentNumber", docAccess.getDocumentNumber()),
                        equal("principalId", docAccess.getPrincipalId()),
                        equal("roleName", docAccess.getRoleName()),
                        equal("namespaceCode", docAccess.getNamespaceCode())
                    )).getResults();
            if (!existingDocAccesses.isEmpty()) {
                final DocumentAccess existingDocAccess = existingDocAccesses.get(0);
                if ((existingDocAccess.getDocumentNumber().equals(docAccess.getDocumentNumber()) && existingDocAccess.getPrincipalId().equals(docAccess.getPrincipalId())
                        && existingDocAccess.getRoleName().equals(docAccess.getRoleName()) && existingDocAccess.getNamespaceCode().equals(docAccess.getNamespaceCode()))
                        && !existingDocAccess.getId().equals(docAccess.getId())) {
                    getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.documentNumber",
                            UNIQUE_DOC_ACCESS_ENTRY, "");
                    valid = false;
                }
            }
        }

        return valid;
    }

    public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }

        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }

        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
