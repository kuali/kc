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
package org.kuali.coeus.common.budget.impl.rate;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.rate.AbstractInstituteRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

/**
 * Rule class for the InstituteRateMaintenanceDocument.
 */
public class InstituteRateMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
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

    @Override
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        this.logDocInfo(document);
        
        boolean valid = this.rule.validateRateTypeAndRateClass((AbstractInstituteRate) document.getDocumentDataObject());
        valid &= checkExistence((AbstractInstituteRate) document.getNewMaintainableObject().getDataObject());
        
        return valid;
    }
    
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        this.logDocInfo(document);
        
        boolean valid = this.rule.validateRateTypeAndRateClass((AbstractInstituteRate) document.getDocumentDataObject());
        valid &= checkExistence((AbstractInstituteRate) document.getNewMaintainableObject().getDataObject());
        
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
            RateType rateType = (RateType) KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(RateType.class, pkMap);
            if (rateType == null ) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.rateTypeCode", KeyConstants.ERROR_RATE_TYPE_NOT_EXIST,
                        new String[] {newInstituteRate.getRateClassCode(), newInstituteRate.getRateTypeCode() });
                valid = false;
            }
        }


        Map<String, String> pkMap = new HashMap<String, String>();
        pkMap.put("unitNumber", newInstituteRate.getUnitNumber());
        valid&=checkExistenceFromTable(Unit.class,pkMap,"unitNumber", "Unit Number");

        if (newInstituteRate instanceof InstituteRate) {
            Map<String, String> pkMap1 = new HashMap<String, String>();
            pkMap1.put("code", ((InstituteRate)newInstituteRate).getActivityTypeCode());
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
