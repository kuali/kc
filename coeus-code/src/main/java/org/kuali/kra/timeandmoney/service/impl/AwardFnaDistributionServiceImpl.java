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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.timeandmoney.service.AwardFnaDistributionService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class AwardFnaDistributionServiceImpl implements AwardFnaDistributionService {
    
    public static final String AWARD_FNA_DISTRIBUTION_PARAMETER_NAME = "AWARD_FNA_DISTRIBUTION";
    public static final String AWARD_FNA_DISTRIBUTION_NAMESPACE = "KC-AWARD";
    public static final String AWARD_FNA_DISTRIBUTION_COMPONENT = "Document";
    private ParameterService parameterService;

    @Override
    public boolean displayAwardFAndADistributionEqualityValidationAsWarning() {
        String parmValue = getFandAParameterValue();
        return isOptional(parmValue);
    }

    @Override
    public boolean displayAwardFAndADistributionEqualityValidationAsError() {
        String parmValue = getFandAParameterValue();
        return isMandatory(parmValue);
    }
    
    @Override
    public boolean disableFAndADistributionEqualityValidation() {
        String parmValue = getFandAParameterValue();
        return isDisabled(parmValue);
    }
    
    protected String getFandAParameterValue() {
        String parmVal = getParameterService().getParameterValueAsString(AWARD_FNA_DISTRIBUTION_NAMESPACE, 
                AWARD_FNA_DISTRIBUTION_COMPONENT, AWARD_FNA_DISTRIBUTION_PARAMETER_NAME);
        return parmVal;
    }
    
        
    protected boolean isDisabled(String parameterValue) {
        return StringUtils.equalsIgnoreCase("D", parameterValue);
    }
    
    protected boolean isMandatory(String parameterValue) {
        return StringUtils.equalsIgnoreCase("M", parameterValue);
    }
    
    protected boolean isOptional(String parameterValue) {
        return StringUtils.equalsIgnoreCase("O", parameterValue);
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}