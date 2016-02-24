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
package org.kuali.coeus.propdev.impl.s2s;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Collections;
import java.util.List;

public class S2SErrorRule extends KcMaintenanceDocumentRuleBase {

    private DataObjectService dataObjectService;
    private GlobalVariableService globalVariableService;

    private static final String UNIQUE_S2S_ERROR_KEY = "error.unique.s2s.error.key";

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = super.processCustomRouteDocumentBusinessRules(document);

        final S2sError s2sError = (S2sError) document.getNewMaintainableObject().getDataObject();

        if (StringUtils.isNotBlank(s2sError.getKey())
                && !KRADConstants.MAINTENANCE_DELETE_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction())) {
            final List<S2sError> errors = getDataObjectService().findMatching(S2sError.class,
                    QueryByCriteria.Builder.andAttributes(Collections.singletonMap("key", s2sError.getKey()))
                            .build())
                    .getResults();
            if (!errors.isEmpty()) {
                final S2sError existingError = errors.get(0);
                if (existingError.getKey().equals(s2sError.getKey()) && !existingError.getId().equals(s2sError.getId())) {
                    getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.key",
                            UNIQUE_S2S_ERROR_KEY, "");
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
