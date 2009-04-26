/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Commawardy License, Version 2.0 (the "License");
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
package org.kuali.kra.award.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.service.AwardService;
import org.kuali.rice.kns.service.BusinessObjectService;

/** {@inheritDoc} */
public class AwardServiceImpl implements AwardService {
    
    private BusinessObjectService businessObjectService;

    /** {@inheritDoc} */
    public Award getAward(String awardId) {
        Award award = null;
        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(awardId)) {
            primaryKeys.put("awardId", awardId);
            award = (Award)businessObjectService.findByPrimaryKey(Award.class, primaryKeys);
        }
        return award;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
}
