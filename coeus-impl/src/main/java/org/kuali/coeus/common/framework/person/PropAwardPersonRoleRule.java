package org.kuali.coeus.common.framework.person;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

public class PropAwardPersonRoleRule extends MaintenanceDocumentRuleBase {
    private DataObjectService dataObjectService;
    private GlobalVariableService globalVariableService;

    private static final String UNIQUE_PERSON_ROLE_ENTRY = "error.unique.person.role.entry";

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = super.processCustomRouteDocumentBusinessRules(document);

        final PropAwardPersonRole propAwardPersonRole = (PropAwardPersonRole) document.getNewMaintainableObject().getDataObject();

        if (StringUtils.isNotBlank(propAwardPersonRole.getCode())
                && StringUtils.isNotBlank(propAwardPersonRole.getSponsorHierarchyName())
                && !KRADConstants.MAINTENANCE_EDIT_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction())) {


            final List<PropAwardPersonRole> existingPropAwardPersonRoles = getDataObjectService().findMatching(PropAwardPersonRole.class,
                    QueryByCriteria.Builder.fromPredicates(
                            equal("code", propAwardPersonRole.getCode()),
                            equal("sponsorHierarchyName", propAwardPersonRole.getSponsorHierarchyName())
                    )).getResults();


            if (!existingPropAwardPersonRoles.isEmpty()) {
                getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.dataObject.code", UNIQUE_PERSON_ROLE_ENTRY, "");
                valid = false;
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
