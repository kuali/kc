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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class ValidCeRateTypeMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    

    public ValidCeRateTypeMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkRateTypeExist(document);
    }
    
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkRateTypeExist(document);
    }

    /**
     * 
     * This method to check the rateclasscode/ratetypecode does exist in rate type table.
     * also check costelement.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkRateTypeExist(MaintenanceDocument maintenanceDocument) {

        boolean valid = true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        ValidCeRateType validCeRateType = (ValidCeRateType) maintenanceDocument.getNewMaintainableObject().getDataObject();

        if (StringUtils.isNotBlank(validCeRateType.getRateClassCode()) && StringUtils.isNotBlank(validCeRateType.getRateTypeCode())) {
            Map pkMap = new HashMap();
            pkMap.put("rateClassCode", validCeRateType.getRateClassCode());
            pkMap.put("rateTypeCode", validCeRateType.getRateTypeCode());
            RateType rateType = (RateType) KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(RateType.class, pkMap);
            if (rateType == null ) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.rateTypeCode", KeyConstants.ERROR_RATE_TYPE_NOT_EXIST,
                        new String[] { validCeRateType.getRateClassCode(), validCeRateType.getRateTypeCode() });
                valid = false;
            }
        }


        Map pkMap = new HashMap();
        pkMap.put("costElement", validCeRateType.getCostElement());
        valid&=checkExistenceFromTable(CostElement.class,pkMap,"costElement", "Cost Element");


        return valid;

    }


}
