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
package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.rate.AbstractInstituteRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteLaRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class does not directly extend a rule class in order to break up the rule logic into smaller classes.
 * This class will be used by a rule class.
 * 
 * @see InstituteRateRateTypeRateClassRule for details
 */
public final class InstituteRateRateTypeRateClassRuleImpl implements InstituteRateRateTypeRateClassRule {
    
    private static final String RATE_TYPE_CODE_PROPERTY_NAME = "document.newMaintainableObject.rateTypeCode";
    private static final String RATE_CLASS_CODE_PROPERTY_NAME = "document.newMaintainableObject.rateClassCode";
    private static final String RATE_TYPE_CODE_FIELD_NAME = "rateTypeCode";
    private static final String RATE_CLASS_CODE_FIELD_NAME = "rateClassCode";
    private static final String INSTITUTE_RATE_CLASS_TYPES_PARAM = "instituteRateClassTypes";
    private static final String INSTITUTE_LA_RATE_CLASS_TYPES_PARAM = "instituteLaRateClassTypes";

    private final BusinessObjectService boService;
    private final ParameterService parameterService;
    
    /**
     * Constructs an InstituteRateRateTypeRateClassRule setting the used services using the
     * {@link org.kuali.coeus.sys.framework.service.KcServiceLocator KcServiceLocator}.
     */
    public InstituteRateRateTypeRateClassRuleImpl() {
        this(KcServiceLocator.getService(BusinessObjectService.class), KcServiceLocator.getService(ParameterService.class));
    }
    
    /**
     * Constructs an InstituteRateRateTypeRateClassRule setting the used services. This ctor allows for easier unit testing.
     * 
     * @param boService the BusinessObjectService
     * @param parameterService the ParameterService
     * @throws NullPointerException if boService or configService is null
     */
    public InstituteRateRateTypeRateClassRuleImpl(final BusinessObjectService boService, final ParameterService parameterService) {
        
        if (boService == null) {
            throw new NullPointerException("the boService is null");
        }
        
        if (parameterService == null) {
            throw new NullPointerException("the parameterService is null");
        }
        
        this.boService = boService;
        this.parameterService = parameterService;
    }
    
    @Override
    public boolean validateRateTypeAndRateClass(final AbstractInstituteRate rate) {
        
        if (rate == null) {
            throw new NullPointerException("the rate is null");
        }
        
        boolean valid = this.checkCorrectRateClass(rate);
        valid &= this.checkCorrectRateType(rate);
        
        return valid;
    }
    
    /**
     * Checks whether a valid rate class was selected based on the rate type.  For example
     * if the rate type is a LA rate type then a rate class code of an LA rate must be selected
     * to be valid.
     * 
     * <p>
     * If the rate class code has not been set (null) then the validation is not executed and true
     * is returned.
     * </p>
     * 
     * @param rate the rate
     * @return true if valid
     */
    private boolean checkCorrectRateClass(final AbstractInstituteRate rate) {
        assert rate != null : "the rate is null";
        
        if (rate.getRateClassCode() == null) {
            return true;
        }
        
        final Collection<String> validClassTypes  = this.getValidRateClassTypes(rate);
        final Collection<RateType> rateTypes = getRateTypesFromRateClassCode(rate.getRateClassCode());
        final boolean valid = validateRateClassType(validClassTypes, rateTypes);
       
        if (!valid) {
            GlobalVariables.getMessageMap().putError(RATE_CLASS_CODE_PROPERTY_NAME,
                KeyConstants.ERROR_RATE_CLASS_NOT_VALID_FOR_TYPE, rate.getRateClassCode());
        }
        
        return valid;
    }
    
    /**
     * Checks whether a valid rate type was selected based on the rate type.  For example
     * if the rate type is a LA rate type then a rate type code of an LA rate must be selected
     * to be valid.
     * 
     * <p>
     * If the rate type code has not been set (null) then the validation is not executed and true
     * is returned.
     * </p>
     * 
     * @param rate the rate
     * @return true if valid
     */
    private boolean checkCorrectRateType(final AbstractInstituteRate rate) {
        assert rate != null : "the rate is null";
        
        if (rate.getRateTypeCode() == null) {
            return true;
        }
        
        final Collection<String> validClassTypes  = this.getValidRateClassTypes(rate);
        final Collection<RateType> rateTypes = this.getRateTypesFromRateTypeCode(rate.getRateTypeCode());
        final boolean valid = validateRateClassType(validClassTypes, rateTypes);
       
        if (!valid) {
            GlobalVariables.getMessageMap().putError(RATE_TYPE_CODE_PROPERTY_NAME,
                KeyConstants.ERROR_RATE_TYPE_NOT_VALID_FOR_TYPE, rate.getRateTypeCode());
        }
        return valid;
    }
    
    /**
     * Validates whether at least one rateType's rate class is in the validClassType collection.
     * If at least one exists then the rate type is valid.
     *   
     * @param validClassTypes the collection of valid class types
     * @param rateTypes the rate types to check
     * @return true is valid false if not.
     */
    private boolean validateRateClassType(final Collection<String> validClassTypes, final Collection<RateType> rateTypes) {
        assert validClassTypes != null : "the validClassTypes is null";
        assert rateTypes != null : "the rateTypes is null";
        
        for (RateType rateType : rateTypes) {
            if (validClassTypes.contains(rateType.getRateClass().getRateClassTypeCode())) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * This method retrieves a collection of rate types from a rate class code.
     * 
     * @param rateClassCode the rate class code.
     * @return a collection of rate types
     */
    private Collection<RateType> getRateTypesFromRateClassCode(final String rateClassCode) {
        assert rateClassCode != null : "the rateClassCode is null";
        
        final Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RATE_CLASS_CODE_FIELD_NAME, rateClassCode);
        
        @SuppressWarnings("unchecked")
        final Collection<RateType> col = this.boService.findMatching(RateType.class, fieldValues);
        return col;
    }
    
    /**
     * This method retrieves a collection of rate types from a rate type code.
     * 
     * @param rateTypeCode the rate type code.
     * @return a collection of rate types
     */
    private Collection<RateType> getRateTypesFromRateTypeCode(final String rateTypeCode) {
        assert rateTypeCode != null : "the rateTypeCode is null";
        
        final Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RATE_TYPE_CODE_FIELD_NAME, rateTypeCode);
        
        @SuppressWarnings("unchecked")
        final Collection<RateType> col = this.boService.findMatching(RateType.class, fieldValues);
        return col;
    }
    
    /**
     * Gets a collection of valid rate class types for an {@link AbstractInstituteRate AbstractInstituteRate}.
     * 
     * @param document the document
     * @return collection of valid rate class types
     * @throws IllegalArgumentException if the document type is not supported by this method.
     */
    private Collection<String> getValidRateClassTypes(final AbstractInstituteRate document) {
        assert document != null : "the document is null";
        
        if (document instanceof InstituteLaRate) {
            return this.getRateClassTypesCollection(INSTITUTE_LA_RATE_CLASS_TYPES_PARAM);
        } else if (document instanceof InstituteRate) {
            return this.getRateClassTypesCollection(INSTITUTE_RATE_CLASS_TYPES_PARAM);
        } else {
            throw new IllegalArgumentException("Incorrect document type: " + document.getClass().getName());
        }
    }
    
    /**
     * This method retrieves a collection of parameter values (rate types)
     * for a given rate type parameter name.
     * @param infoType rate type parameter name
     * @return collection of rate types
     */
    private Collection<String> getRateClassTypesCollection(final String rateType) {
        assert rateType != null : "infoType is null";
        
        return this.parameterService.getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET,
            ParameterConstants.ALL_COMPONENT, rateType);
    }
}
