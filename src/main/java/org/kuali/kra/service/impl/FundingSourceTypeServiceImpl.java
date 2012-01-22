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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.rice.krad.service.BusinessObjectService;

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


    /**
     * 
     * @see org.kuali.kra.service.FundingSourceTypeService#getFundingSourceType(java.lang.String)
     */
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
