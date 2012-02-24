/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class CoiDispositionStatusDocumentRule extends KraMaintenanceDocumentRuleBase {
    private BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }


    /**
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#isDocumentValidForSave(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    public boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean result = super.isDocumentValidForSave(document);
        final CoiDispositionStatus coiDispositionStatus = (CoiDispositionStatus) document.getNewMaintainableObject().getDataObject();
        System.out.println("action " + document.getNewMaintainableObject().getMaintenanceAction());
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
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
   
}
