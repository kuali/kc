/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.rule.InstituteRateRateTypeRateClassRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;

/**
 * Rule class for the InstituteRateMaintenanceDocument.
 */
public class InstituteRateMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    
    private final InstituteRateRateTypeRateClassRule rule;
    
    /**
     * Constructs an InstituteRateMaintenanceDocumentRule setting the used rule to the defaul implementation.
     * Since rules are not Spring Beans it is directly being new'd up in this ctor.
     */
    public InstituteRateMaintenanceDocumentRule() {
        this(new InstituteRateRateTypeRateClassRuleImpl());
    }
    
    /**
     * Constructs an InstituteRateMaintenanceDocumentRule setting the used rule
     * @param rule the InstituteRateRateTypeRateClassRule
     * @throws NullPointerException if the rule is null
     */
    InstituteRateMaintenanceDocumentRule(InstituteRateRateTypeRateClassRule rule) {
        
        if (rule == null) {
            throw new NullPointerException("the rule is null");
        }
        
        this.rule = rule;
    }

    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        this.logDocInfo(document);
        
        boolean valid = this.rule.validateRateTypeAndRateClass((AbstractInstituteRate) document.getDocumentBusinessObject());
        valid &= checkExistence((AbstractInstituteRate) document.getNewMaintainableObject().getBusinessObject());
        
        return valid;
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        this.logDocInfo(document);
        
        boolean valid = this.rule.validateRateTypeAndRateClass((AbstractInstituteRate) document.getDocumentBusinessObject());
        valid &= checkExistence((AbstractInstituteRate) document.getNewMaintainableObject().getBusinessObject());
        
        return valid;
    }

    /**
     * 
     * This method to check the rateclasscode/ratetypecode does exist in rate type table.
     * also check unitnumber. activitytype for instituterate only.
     * @param newInstituteRate
     * @return
     */
    private boolean checkExistence(AbstractInstituteRate newInstituteRate) {

        boolean valid = true;

        if (StringUtils.isNotBlank(newInstituteRate.getRateClassCode()) && StringUtils.isNotBlank(newInstituteRate.getRateTypeCode())) {
            Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("rateClassCode", newInstituteRate.getRateClassCode());
            pkMap.put("rateTypeCode", newInstituteRate.getRateTypeCode());
            RateType rateType = (RateType)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(RateType.class, pkMap);
            if (rateType == null ) {
                GlobalVariables.getErrorMap().putError("document.newMaintainableObject.rateTypeCode", KeyConstants.ERROR_RATE_TYPE_NOT_EXIST,
                        new String[] {newInstituteRate.getRateClassCode(), newInstituteRate.getRateTypeCode() });
                valid = false;
            }
        }


        Map<String, String> pkMap = new HashMap<String, String>();
        pkMap.put("unitNumber", newInstituteRate.getUnitNumber());
        valid&=checkExistenceFromTable(Unit.class,pkMap,"unitNumber", "Unit Number");

        if (newInstituteRate instanceof InstituteRate) {
            Map<String, String> pkMap1 = new HashMap<String, String>();
            pkMap1.put("activityTypeCode", ((InstituteRate)newInstituteRate).getActivityTypeCode());
            valid&=checkExistenceFromTable(ActivityType.class,pkMap1,"activityTypeCode", "Activity Type");
        }
        
        return valid;

    }

    /**
     * Logs document info for the MaintenanceDocument
     * @param maintenanceDocument
     */
    private void logDocInfo(final MaintenanceDocument maintenanceDocument) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
    }
}
