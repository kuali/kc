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
package org.kuali.kra.coi.disclosure;

import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashMap;
import java.util.Map;

public class CoiDispositionStatusDocumentRule extends KcMaintenanceDocumentRuleBase {
    private BusinessObjectService businessObjectService;

    @Override

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }


    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    @Override
    public boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean result = super.isDocumentValidForSave(document);
        final CoiDispositionStatus coiDispositionStatus = (CoiDispositionStatus) document.getNewMaintainableObject().getDataObject();
        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)) {
            final CoiDispositionStatus oldDocument = (CoiDispositionStatus) document.getOldMaintainableObject().getDataObject();
            if (isNotDuplicate(coiDispositionStatus) && isValidCoiDisclosureStatus(coiDispositionStatus)) {
                result = true;
            } else {
                result = false;
            }

        }   else {
            result = true;
        }
        return result;
    }

    
    protected boolean isNotDuplicate(CoiDispositionStatus coiDispositionStatus) {
        boolean notDuplicate = true;
        String coiDispositionCode = coiDispositionStatus.getCoiDispositionCode().toString();
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("coiDispositionCode", coiDispositionCode);
        if (exists(CoiDispositionStatus.class, criteria)) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.coiDispositionCode", KeyConstants.ERROR_COI_DUPLICATE_DISPOSITION_CODE,
                    new String[] {coiDispositionCode});
            notDuplicate &= false;
        }
        
        
        String description = coiDispositionStatus.getDescription();
        Map<String, String> descriptionCriteria = new HashMap<String, String>();
        descriptionCriteria.put("description", description);
        if(exists(CoiDispositionStatus.class, descriptionCriteria)) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.description", KeyConstants.ERROR_COI_DUPLICATE_DISPOSITION_DESCRIPTION, 
                    new String[] {description});
            notDuplicate &= false;
        }
        return notDuplicate;
    }
   
    private boolean isValidCoiDisclosureStatus(CoiDispositionStatus coiDispositionStatus) {
        String coiDisclosureStatusCode = coiDispositionStatus.getCoiDisclosureStatusCode();
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("coiDisclosureStatusCode", coiDisclosureStatusCode);
        if (!exists(CoiDisclosureStatus.class, criteria)) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.coiDisclosureStatusCode", KeyConstants.ERROR_COI_INVALID_DISCLOSURE_STATUS_CODE,
                    new String[] {coiDispositionStatus.getCoiDisclosureStatusCode()});
            return false;
        }
        return true;
    }
    
    protected boolean exists(Class<?> clazz, Map<String, String> criteria) {
        int count = getBusinessObjectService().countMatching(clazz, criteria);
        return count == 0 ? false : true;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
   
}
