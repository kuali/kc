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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.config.ConfigurationException;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * This class provides support to the AwardApprovedEquipmentRule 
 */
public class EquipmentCapitalizationMinimumLoader implements Serializable {
    public static final String PARM_TYPE_CODE = "D";
    static final String FEDERAL_CAPITALIZATION_MIN_PARM_NAME = "federalCapitalizationMinimum";
    static final String FEDERAL_REQUIREMENT = "Federal";
    static final String INSTITUTE_CAPITALIZATION_MIN_PARM_NAME = "institutionCapitalizationMinimum";
    static final String INSTITUTION_REQUIREMENT = "Institution";
    
    private static final String CONFIG_PARM_MISSING_MSG = "Configuration parameter %s is missing";
    
    private static final String CONFIG_PARM_NOT_NUMERIC_MSG = "Configuration parameter %s is not numeric";
    private ParameterService parameterService;
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
        
    /**
     * This method gets the minimum capitalization requirement
     * @return
     */
    public MinimumCapitalizationInfo getMinimumCapitalization() {
        double federalMinimum = getFederalCapitalizationMinimum();
        double institutionMinimum = getInstitutionCapitalizationMinimum();
        double minimumCapitalization = Math.max(0.0, Math.min(federalMinimum, institutionMinimum));
        String context = institutionMinimum <= federalMinimum ? INSTITUTION_REQUIREMENT : FEDERAL_REQUIREMENT;
        return new MinimumCapitalizationInfo(context, minimumCapitalization, federalMinimum, institutionMinimum);
    }
    
    /**
     * This method fetches a parm value
     * @param parmName
     * @return
     */
    protected String fetchParameterValue(String parmName) {
        return getParameterService().getParameterValueAsString(AwardDocument.class, parmName);
    }

    /**
     * 
     * @return
     */
    protected double getFederalCapitalizationMinimum() {
        return getValueFromParameter(FEDERAL_CAPITALIZATION_MIN_PARM_NAME);
    }
    
    /**
     * 
     * @return
     */
    protected double getInstitutionCapitalizationMinimum() {
        return getValueFromParameter(INSTITUTE_CAPITALIZATION_MIN_PARM_NAME);
    }
    
    /**
     * This method gets value from a configuration parameter
     * @param parmName
     * @return
     */
    double getValueFromParameter(String parmName) {
        String parmValue = fetchParameterValue(parmName);
        validateParmPresent(parmName, parmValue);
        validateParmIsNumber(parmName, parmValue);
        
        return Double.valueOf(parmValue);
    }
    
    /**
     * This method validates the parm is numeric
     * @param parmName
     * @param parmValue
     * @throws ConfigurationException
     */
    void validateParmIsNumber(String parmName, String parmValue) {
        if(!NumberUtils.isNumber(parmValue)) {
            String msg = String.format(CONFIG_PARM_NOT_NUMERIC_MSG, parmName);
            throw new ConfigurationException(msg);
        }
    }

    /**
     * This method validates the parm is present
     * @param parmName
     * @param parmValue
     * @throws ConfigurationException
     */
    void validateParmPresent(String parmName, String parmValue) {
        if(StringUtils.isEmpty(parmValue)) {
            String msg = String.format(CONFIG_PARM_MISSING_MSG, parmName);
            throw new ConfigurationException(msg);
        }
    }
}
