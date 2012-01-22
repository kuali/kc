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
package org.kuali.kra.costshare;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * This class...
 */
public class CostShareServiceImpl implements CostShareService {
    
    private static final String PARAM_LABEL_NAME = "CostShareProjectPeriodNameLabel"; 
    
    private static final String STANDARD_COST_SHARE_LABEL_FISCAL_YEAR = "Fiscal Year";
    private static final String STANDARD_COST_SHARE_LABEL_PROJECT_PERIOD = "Project Period";
    
    private ParameterService parameterService; 
    private String label;

    /**
     * @see org.kuali.kra.costshare.CostShareService#getCostShareLabel()
     */
    public String getCostShareLabel(boolean resetSession) {
        if (label == null || resetSession) {
            label = this.getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, 
                    Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PARAM_LABEL_NAME);
        }
        return label;
    }
    
    /**
     * 
     * @see org.kuali.kra.costshare.CostShareService#validateProjectPeriodAsFiscalYear()
     */
    public boolean validateProjectPeriodAsFiscalYear(boolean resetSession) {
        boolean retVal = StringUtils.equalsIgnoreCase(STANDARD_COST_SHARE_LABEL_FISCAL_YEAR, getCostShareLabel(resetSession));
        return retVal;
    }
    
    /**
     * 
     * @see org.kuali.kra.costshare.CostShareService#validateProjectPeriodAsProjectPeriod()
     */
    public boolean validateProjectPeriodAsProjectPeriod(boolean resetSession) {
        boolean retVal = StringUtils.equalsIgnoreCase(STANDARD_COST_SHARE_LABEL_PROJECT_PERIOD, getCostShareLabel(resetSession));
        return retVal;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
