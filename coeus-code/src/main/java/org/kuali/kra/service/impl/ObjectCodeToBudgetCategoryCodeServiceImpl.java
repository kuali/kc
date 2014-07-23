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
package org.kuali.kra.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.kra.service.ObjectCodeToBudgetCategoryCodeService;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("objectCodeToBudgetCategoryCodeService")
public class ObjectCodeToBudgetCategoryCodeServiceImpl implements ObjectCodeToBudgetCategoryCodeService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public String getBudgetCategoryCodeForCostElment(String objectCode) {
        String budgetCategoryCode = null;

        if (StringUtils.isNotEmpty(objectCode)) {
            CostElement costElement = dataObjectService.find(CostElement.class, objectCode);
            if (costElement != null) {
                budgetCategoryCode = costElement.getBudgetCategoryCode();
            }
        }

        return budgetCategoryCode;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
