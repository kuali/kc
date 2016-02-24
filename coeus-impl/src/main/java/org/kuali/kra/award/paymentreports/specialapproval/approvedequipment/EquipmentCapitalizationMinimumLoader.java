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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.core.api.config.ConfigurationException;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.io.Serializable;

/**
 * This class provides support to the AwardApprovedEquipmentRule 
 */
public class EquipmentCapitalizationMinimumLoader implements Serializable {
    static final String FEDERAL_CAPITALIZATION_MIN_PARM_NAME = "federalCapitalizationMinimum";
    static final String FEDERAL_REQUIREMENT = "Federal";
    static final String INSTITUTE_CAPITALIZATION_MIN_PARM_NAME = "institutionCapitalizationMinimum";
    static final String INSTITUTION_REQUIREMENT = "Institution";
    
    private static final String CONFIG_PARM_MISSING_MSG = "Configuration parameter %s is missing";
    
    private static final String CONFIG_PARM_NOT_NUMERIC_MSG = "Configuration parameter %s=%s is not numeric";
    private ParameterService parameterService;
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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


    protected double getFederalCapitalizationMinimum() {
        return getValueFromParameter(FEDERAL_CAPITALIZATION_MIN_PARM_NAME);
    }
    

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
            String msg = String.format(CONFIG_PARM_NOT_NUMERIC_MSG, parmName, parmValue);
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
