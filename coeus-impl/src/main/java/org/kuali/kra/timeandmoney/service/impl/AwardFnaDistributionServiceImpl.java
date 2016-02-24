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
