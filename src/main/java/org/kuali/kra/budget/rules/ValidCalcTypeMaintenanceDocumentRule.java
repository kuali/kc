/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.rules;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ValidCalcTypeMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    
    /**
     * Constructs a ValidCalcTypeMaintenanceDocumentRule.java.
     */
    public ValidCalcTypeMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }

    /**
     * 
     * This method to check the rateclasscode/ratetypecode does exist in rate type table.
     * also check rateclasstype and dependentrateclasstype.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkExistence(MaintenanceDocument maintenanceDocument) {

        boolean valid = true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        ValidCalcType validCalcType = (ValidCalcType) maintenanceDocument.getNewMaintainableObject().getBusinessObject();

        if (StringUtils.isNotBlank(validCalcType.getRateClassCode()) && StringUtils.isNotBlank(validCalcType.getRateTypeCode())) {
            Map pkMap = new HashMap();
            pkMap.put("rateClassCode", validCalcType.getRateClassCode());
            pkMap.put("rateTypeCode", validCalcType.getRateTypeCode());
            RateType rateType = (RateType)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(RateType.class, pkMap);
            if (rateType == null ) {
                GlobalVariables.getErrorMap().putError("document.newMaintainableObject.rateTypeCode", KeyConstants.ERROR_RATE_TYPE_NOT_EXIST,
                        new String[] { validCalcType.getRateClassCode(), validCalcType.getRateTypeCode() });
                valid = false;
            }
        }


        Map pkMap = new HashMap();
        pkMap.put("rateClassType", validCalcType.getRateClassType());
        valid&=checkExistenceFromTable(RateClassType.class,pkMap,"rateClassType", "Rate Class Type");

        pkMap.put("rateClassType", validCalcType.getDependentRateClassType());
        valid&=checkExistenceFromTable(RateClassType.class,pkMap,"dependentRateClassType", "Dependent Rate Class Type");

        return valid;

    }


}
