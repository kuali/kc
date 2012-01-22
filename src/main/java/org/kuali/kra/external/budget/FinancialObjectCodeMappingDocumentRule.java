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
package org.kuali.kra.external.budget;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;

public class FinancialObjectCodeMappingDocumentRule extends KraMaintenanceDocumentRuleBase {
    
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
        final FinancialObjectCodeMapping mapping = (FinancialObjectCodeMapping) document.getNewMaintainableObject().getDataObject();
        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)) {
            if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
                final FinancialObjectCodeMapping oldDocument = (FinancialObjectCodeMapping) document.getOldMaintainableObject().getDataObject();
                if (!oldDocument.getUnitNumber().equals(mapping.getUnitNumber())) {

                    result &= validateUniqueEntry(mapping);
                    result &= checkExistence(mapping);
                }
            } else {
                result &= validateUniqueEntry(mapping);
                result &= checkExistence(mapping);
            }
        }   else {
            result = true;
        }
        return result;
    }


    private boolean checkExistence(FinancialObjectCodeMapping newMapping) {

        boolean valid = true;
        if (StringUtils.isNotBlank(newMapping.getRateClassCode()) && StringUtils.isNotBlank(newMapping.getRateTypeCode())) {
            Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("rateClassCode", newMapping.getRateClassCode());
            pkMap.put("rateTypeCode", newMapping.getRateTypeCode());
            RateType rateType = (RateType) getBusinessObjectService().findByPrimaryKey(RateType.class, pkMap);
            if (rateType == null) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.rateTypeCode", KeyConstants.ERROR_RATE_TYPE_NOT_EXIST,
                        new String[] {newMapping.getRateClassCode(), newMapping.getRateTypeCode() });
                valid = false;
            }
        }


        Map<String, String> pkMap = new HashMap<String, String>();
        pkMap.put("unitNumber", newMapping.getUnitNumber());
        valid &= checkExistenceFromTable(Unit.class,pkMap,"unitNumber", "Unit Number");
        
        if (StringUtils.isNotEmpty(((FinancialObjectCodeMapping) newMapping).getActivityTypeCode())) {
            if (newMapping instanceof FinancialObjectCodeMapping) {
                Map<String, String> pkMap1 = new HashMap<String, String>();
                pkMap1.put("activityTypeCode", ((FinancialObjectCodeMapping) newMapping).getActivityTypeCode());
                valid &= checkExistenceFromTable(ActivityType.class,pkMap1,"activityTypeCode", "Activity Type");
            }
        }
        return valid;

    }
    
    private boolean validateUniqueEntry(FinancialObjectCodeMapping mapping) {
        String activityTypeCode = mapping.getActivityTypeCode();
        String rateClass = mapping.getRateClassCode();
        String rateType = mapping.getRateTypeCode();
        String unitNumber = mapping.getUnitNumber();
        boolean valid = true;
        if (rateClass != null && StringUtils.isNotBlank(rateClass) 
            && rateType != null && StringUtils.isNotBlank(rateType) 
            && unitNumber != null && StringUtils.isNotBlank(unitNumber)) {
            final Map<String, String> map = new HashMap<String, String>();
          
            map.put("rateClassCode", rateClass);
            map.put("rateTypeCode", rateType);
            map.put("unitNumber", unitNumber);

            final Collection<FinancialObjectCodeMapping> results = getBusinessObjectService().findMatching(FinancialObjectCodeMapping.class, map);

            if (results.size() > 0) {
                
                for (FinancialObjectCodeMapping result : results) {
                    if (StringUtils.equalsIgnoreCase(result.getActivityTypeCode(), activityTypeCode)) {
                        valid = false;
                        final MessageMap errorMap = GlobalVariables.getMessageMap();
                        String error = "ActivityTypeCode: " + activityTypeCode + " RateClassCode: " + rateClass + 
                                       " RateTypeCode: " + rateType + " UnitNumber: " + unitNumber;
                        errorMap.putError("document.newMaintainableObject.financialObjectCode", KeyConstants.FINANCIAL_OBJECT_CODE_MAPPING_EXISTS, error);
                    }
                }
                
            } 
        }
        return valid;
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
   
}
