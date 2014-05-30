/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.calculator.ValidCalcType;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.Map;

public class ValidCalcTypeMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    private final BusinessObjectService boService;
    

    public ValidCalcTypeMaintenanceDocumentRule() {
        this(KcServiceLocator.getService(BusinessObjectService.class));
    }
    

    /**
     * Sets the BusinessObjectService.  Useful for unit testing.
     * @param boService the BusinessObjectService
     */
    ValidCalcTypeMaintenanceDocumentRule(final BusinessObjectService boService) {
        this.boService = boService;
    }
    
    @Override
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return this.checkExistence(document);
    }
    
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return this.checkExistence(document);
    }

    /**
     * 
     * This method to check the rateclasscode/ratetypecode does exist in rate type table.
     * also check rateclasstype and dependentrateclasstype.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkExistence(final MaintenanceDocument maintenanceDocument) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        
        final ValidCalcType validCalcType = (ValidCalcType) maintenanceDocument.getNewMaintainableObject().getDataObject();

        boolean valid = validateRateTypeCode(validCalcType.getRateClassCode(), validCalcType.getRateTypeCode());
        valid &= validateRateClassType(validCalcType.getRateClassType());
        valid &= validateDependantRateClassType(validCalcType.getDependentRateClassType());

        return valid;
    }
    
    /**
     * Validates the Rate Class Type.
     * @param rateClassType the Rate Class Type
     * @return true if valid false if not
     */
    private boolean validateRateClassType(final String rateClassType) {
        
        final Map<String, String> pkMap = new HashMap<String, String>();
        pkMap.put("code", rateClassType);
        return checkExistenceFromTable(RateClassType.class, pkMap, "code", "Rate Class Type");
    }
    
    /**
     * Validates the Dependent Rate Class Type.
     * @param dependantRateClassType the Dependent Rate Class Type
     * @return true if valid false if not
     */
    private boolean validateDependantRateClassType(final String dependantRateClassType) {

        if (StringUtils.isNotBlank(dependantRateClassType)) {
            final Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("code", dependantRateClassType);
            return checkExistenceFromTable(RateClassType.class,pkMap, "dependentRateClassType", "Dependent Rate Class Type");
        }
        return true;
    }
    
    /**
     * Validates the Rate Type Code.
     * @param rateClassCode the Rate Class Code
     * @param rateTypeCode the Rate Type Code
     * @return true if valid false if not
     */
    private boolean validateRateTypeCode(final String rateClassCode, final String rateTypeCode) {
        
        boolean valid = true;
        if (StringUtils.isNotBlank(rateClassCode) && StringUtils.isNotBlank(rateTypeCode)) {
            final Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("rateClassCode", rateClassCode);
            pkMap.put("rateTypeCode", rateTypeCode);
            final RateType rateType = (RateType) this.boService.findByPrimaryKey(RateType.class, pkMap);
            
            if (rateType == null ) {
                final MessageMap errorMap = GlobalVariables.getMessageMap();
                errorMap.putError("document.newMaintainableObject.rateTypeCode", KeyConstants.ERROR_RATE_TYPE_NOT_EXIST,
                        new String[] {rateClassCode, rateTypeCode });
                valid = false;
            }
        }
        return valid;
    }
}
