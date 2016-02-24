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
package org.kuali.kra.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @see org.kuali.kra.service.FundingSourceTypeService
 */
public class FundingSourceTypeServiceImpl implements FundingSourceTypeService {

    private BusinessObjectService businessObjectService;

    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


    @Override
    public FundingSourceType getFundingSourceType(String sourceTypeId) {

        FundingSourceType sourceType = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(sourceTypeId)) {
            primaryKeys.put("fundingSourceTypeCode", sourceTypeId);
            sourceType = (FundingSourceType)businessObjectService.findByPrimaryKey(FundingSourceType.class, primaryKeys);
        }
        return sourceType;
    }

}
