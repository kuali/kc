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
package org.kuali.kra.external.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FinancialObjectCodeMappingDocumentRule extends KcMaintenanceDocumentRuleBase {
    
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
                pkMap1.put("code", ((FinancialObjectCodeMapping) newMapping).getActivityTypeCode());
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
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
   
}
