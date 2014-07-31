package org.kuali.coeus.propdev.impl.s2s;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Collections;
import java.util.List;

public class S2SErrorRule extends MaintenanceDocumentRuleBase {

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
