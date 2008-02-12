/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.calculator;

import java.util.*;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * Holds the Calculation rules and order of calculation.
 * 
 */
public class FormulaMaker {

    private QueryList<ValidCalcType> validCalcTypes;
    private String ebOnLaRateClassCode;
    private String ebOnLARateTypeCode;
    private String vaOnLARateClassCode;
    private String vaOnLARateTypeCode;
    private BusinessObjectService businessObjectService;

    /**
     * Constructor....
     */
    public FormulaMaker() {
        this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        initValidCalcTypes();
        sortCalcTypes();
        setEBAndVAonLAValues();
    } // end FormulaMaker


    private void initValidCalcTypes() {
        List validCalcTypesFromDB = (List)businessObjectService.findAll(ValidCalcType.class);
        this.validCalcTypes = new QueryList<ValidCalcType>(validCalcTypesFromDB);
    }


    /**
     * Does the sorting of Rate Class Types based on dependencies. ie, all the Rate Class Types which is not dependent on any other
     * comes first, followed by all other rate class types.
     */
    public void sortCalcTypes() {
        if (validCalcTypes != null && validCalcTypes.size() > 0) {
            QueryList<ValidCalcType> elTempCalcTypes = new QueryList<ValidCalcType>();
            String depRateClassType = "";
            String rateClassType = "";
            Equals equals;
            QueryList<ValidCalcType> filteredVector;
            for (ValidCalcType validCalcType : validCalcTypes) {
                rateClassType = validCalcType.getRateClassType();
                equals = new Equals("rateClassType", rateClassType);
                // check whether this rate class type already exists in cvTemp, if exists, skip
                if (elTempCalcTypes != null && elTempCalcTypes.size() > 0) {
                    filteredVector = elTempCalcTypes.filter(equals);
                    if (filteredVector != null && filteredVector.size() > 0) {
                        continue;
                    }
                }

                // if dependent seq no. is 0 indicates no dependency, just add it
                if (validCalcType.getDependentSeqNumber() == 0) {
                    elTempCalcTypes.add(validCalcType);
                    // dependency exists for this rate class type
                }
                else {
                    depRateClassType = validCalcType.getDependentRateClassType();
                    if (depRateClassType != null) {
                        /*
                         * Get all other dependent rate class types and then loop & add all before adding this rate class type
                         */
                        QueryList<ValidCalcType> matchedRateClassList = validCalcTypes.filter(equals);
                        for (ValidCalcType matchedRateClass : matchedRateClassList) {
                            depRateClassType = matchedRateClass.getDependentRateClassType();
                            /*
                             * check whether the dependent rate class type already present if not search & add it.
                             */
                            equals = new Equals("rateClassType", depRateClassType);
                            filteredVector = elTempCalcTypes.filter(equals);

                            if (filteredVector != null && filteredVector.size() > 0) {
                                continue;
                            }
                            else {
                                filteredVector = validCalcTypes.filter(equals);
                                if (filteredVector != null && filteredVector.size() > 0) {
                                    elTempCalcTypes.add(filteredVector.get(0));
                                }
                            }
                            elTempCalcTypes.addAll(matchedRateClassList);
                        }
                    }
                }
            }
            validCalcTypes = elTempCalcTypes;
        }
    }


    /**
     * Sets the EBonLA and VAonLA values.
     */
    private void setEBAndVAonLAValues() {
        QueryList<ValidCalcType> tempCalcTypes;
        Equals eqRCType;
        ValidCalcType validCalcType;
        // get the EB on LA RateClassCode & RateTypeCode if any
        eqRCType = new Equals("rateClassType", RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        tempCalcTypes = validCalcTypes.filter(eqRCType);
        if (tempCalcTypes.size() > 0) {
            validCalcType = tempCalcTypes.get(0);
            ebOnLaRateClassCode = validCalcType.getRateClassCode();
            ebOnLARateTypeCode = validCalcType.getRateTypeCode();
        }

        // get the VA on LA RateClassCode & RateTypeCode if any
        eqRCType = new Equals("rateClassType", RateClassType.VACATION.getRateClassType());
        tempCalcTypes = validCalcTypes.filter(eqRCType);
        if (tempCalcTypes.size() > 0) {
            validCalcType = tempCalcTypes.get(0);
            vaOnLARateClassCode = validCalcType.getRateClassCode();
            vaOnLARateTypeCode = validCalcType.getRateTypeCode();
        }
    }

    /**
     * Getter for property cvValidCalcTypes.
     * 
     * @return Value of property cvValidCalcTypes.
     */
    public QueryList<ValidCalcType> getValidCalcTypes() {
        return validCalcTypes;
    }

    /**
     * Setter for property validCalcTypes.
     * 
     * @param validCalcTypes New value of property validCalcTypes.
     */
    public void setValidCalcTypes(QueryList<ValidCalcType> validCalcTypes) {
        this.validCalcTypes = validCalcTypes;
    }

    /**
     * Getter for property EBonLARateClassCode.
     * 
     * @return Value of property EBonLARateClassCode.
     * 
     */
    public String getEBonLARateClassCode() {
        return ebOnLaRateClassCode;
    }

    /**
     * Setter for property EBonLARateClassCode.
     * 
     * @param EBonLARateClassCode New value of property EBonLARateClassCode.
     * 
     */
    public void setEBonLARateClassCode(String EBonLARateClassCode) {
        this.ebOnLaRateClassCode = EBonLARateClassCode;
    }

    /**
     * Getter for property EBonLARateTypeCode.
     * 
     * @return Value of property EBonLARateTypeCode.
     * 
     */
    public String getEBonLARateTypeCode() {
        return ebOnLARateTypeCode;
    }

    /**
     * Setter for property EBonLARateTypeCode.
     * 
     * @param EBonLARateTypeCode New value of property EBonLARateTypeCode.
     * 
     */
    public void setEBonLARateTypeCode(String EBonLARateTypeCode) {
        this.ebOnLARateTypeCode = EBonLARateTypeCode;
    }

    /**
     * Getter for property VAonLARateClassCode.
     * 
     * @return Value of property VAonLARateClassCode.
     * 
     */
    public String getVAonLARateClassCode() {
        return vaOnLARateClassCode;
    }

    /**
     * Setter for property VAonLARateClassCode.
     * 
     * @param VAonLARateClassCode New value of property VAonLARateClassCode.
     * 
     */
    public void setVAonLARateClassCode(String VAonLARateClassCode) {
        this.vaOnLARateClassCode = VAonLARateClassCode;
    }

    /**
     * Getter for property VAonLARateTypeCode.
     * 
     * @return Value of property VAonLARateTypeCode.
     * 
     */
    public String getVAonLARateTypeCode() {
        return vaOnLARateTypeCode;
    }

    /**
     * Setter for property VAonLARateTypeCode.
     * 
     * @param VAonLARateTypeCode New value of property VAonLARateTypeCode.
     * 
     */
    public void setVAonLARateTypeCode(String VAonLARateTypeCode) {
        this.vaOnLARateTypeCode = VAonLARateTypeCode;
    }
} // end FormulaMaker


