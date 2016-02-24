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
