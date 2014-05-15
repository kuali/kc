/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.costshare;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("costShareService")
public class CostShareServiceImpl implements CostShareService {
    
    private static final String PARAM_LABEL_NAME = "CostShareProjectPeriodNameLabel"; 
    
    private static final String STANDARD_COST_SHARE_LABEL_FISCAL_YEAR = "Fiscal Year";
    private static final String STANDARD_COST_SHARE_LABEL_PROJECT_PERIOD = "Project Period";

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService; 

    @Override
    public String getCostShareLabel() {
        return this.getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, 
                    Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PARAM_LABEL_NAME);
    }
    
    @Override
    public boolean validateProjectPeriodAsFiscalYear() {
        boolean retVal = StringUtils.equalsIgnoreCase(STANDARD_COST_SHARE_LABEL_FISCAL_YEAR, getCostShareLabel());
        return retVal;
    }
    
    @Override
    public boolean validateProjectPeriodAsProjectPeriod() {
        boolean retVal = StringUtils.equalsIgnoreCase(STANDARD_COST_SHARE_LABEL_PROJECT_PERIOD, getCostShareLabel());
        return retVal;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
