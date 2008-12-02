/*
 * Copyright 2006-2008 The Kuali Foundation
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

package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is the ValidRates business object.
 */
public class ValidRates extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2959106106569652167L;
    private Integer validRatesId;
    private KualiDecimal onCampusRate;
    private KualiDecimal offCampusRate;
    private boolean rateClassType;
    private String adjustmentKey;


    /**
     * 
     * Constructs a ValidRates.java.
     */
    public ValidRates() {

    }

    /**
     * 
     * This method...
     * @return
     */
    public Integer getValidRatesId() {
        return validRatesId;
    }

    /**
     * 
     * This method...
     * @param validRatesId
     */
    public void setValidRatesId(Integer validRatesId) {
        this.validRatesId = validRatesId;
    }

    /**
     * 
     * This method...
     * @return
     */
    public KualiDecimal getOnCampusRate() {
        return onCampusRate;
    }

    /**
     * 
     * This method...
     * @param onCampusRate
     */
    public void setOnCampusRate(KualiDecimal onCampusRate) {
        this.onCampusRate = onCampusRate;
    }

    /**
     * 
     * This method...
     * @return
     */
    public KualiDecimal getOffCampusRate() {
        return offCampusRate;
    }

    /**
     * 
     * This method...
     * @param offCampusRate
     */
    public void setOffCampusRate(KualiDecimal offCampusRate) {
        this.offCampusRate = offCampusRate;
    }

    /**
     * 
     * This method...
     * @return
     */
    public boolean getRateClassType() {
        return rateClassType;
    }

    /**
     * 
     * This method...
     * @param rateClassType
     */
    public void setRateClassType(boolean rateClassType) {
        this.rateClassType = rateClassType;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getAdjustmentKey() {
        return adjustmentKey;
    }

    /**
     * 
     * This method...
     * @param adjustmentKey
     */
    public void setAdjustmentKey(String adjustmentKey) {
        this.adjustmentKey = adjustmentKey;
    }

    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("validRatesId", getValidRatesId());
        hashMap.put("onCampusRate", getOnCampusRate());
        hashMap.put("offCampusRate", getOffCampusRate());
        hashMap.put("rateClassType", getRateClassType());
        hashMap.put("adjustmentKey", getAdjustmentKey());
        return hashMap;
    }

}
