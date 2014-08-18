/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * 
 * This class is the ValidRates business object.
 */
public class ValidRates extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 2959106106569652167L;

    private Integer validRatesId;

    private ScaleTwoDecimal onCampusRate;

    private ScaleTwoDecimal offCampusRate;

    private String rateClassType;

    private String adjustmentKey;

    private String icrRateCode;


    public ValidRates() {
    }

    public Integer getValidRatesId() {
        return validRatesId;
    }

    public String getIcrRateCode() {
        return icrRateCode;
    }

    public void setIcrRateCode(String icrRateCode) {
        this.icrRateCode = icrRateCode;
    }

    public void setValidRatesId(Integer validRatesId) {
        this.validRatesId = validRatesId;
    }

    public ScaleTwoDecimal getOnCampusRate() {
        return onCampusRate;
    }

    public void setOnCampusRate(ScaleTwoDecimal onCampusRate) {
        this.onCampusRate = onCampusRate;
    }

    public ScaleTwoDecimal getOffCampusRate() {
        return offCampusRate;
    }

    public void setOffCampusRate(ScaleTwoDecimal offCampusRate) {
        this.offCampusRate = offCampusRate;
    }

    public String getRateClassType() {
        return rateClassType;
    }

    public void setRateClassType(String rateClassType) {
        this.rateClassType = rateClassType;
    }

    public String getAdjustmentKey() {
        return adjustmentKey;
    }

    public void setAdjustmentKey(String adjustmentKey) {
        this.adjustmentKey = adjustmentKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentKey == null) ? 0 : adjustmentKey.hashCode());
        result = prime * result + ((offCampusRate == null) ? 0 : offCampusRate.hashCode());
        result = prime * result + ((onCampusRate == null) ? 0 : onCampusRate.hashCode());
        result = prime * result + ((rateClassType == null) ? 0 : rateClassType.hashCode());
        result = prime * result + ((validRatesId == null) ? 0 : validRatesId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ValidRates other = (ValidRates) obj;
        if (adjustmentKey == null) {
            if (other.adjustmentKey != null) return false;
        } else if (!adjustmentKey.equals(other.adjustmentKey)) return false;
        if (offCampusRate == null) {
            if (other.offCampusRate != null) return false;
        } else if (!offCampusRate.equals(other.offCampusRate)) return false;
        if (onCampusRate == null) {
            if (other.onCampusRate != null) return false;
        } else if (!onCampusRate.equals(other.onCampusRate)) return false;
        if (rateClassType != other.rateClassType) return false;
        if (validRatesId == null) {
            if (other.validRatesId != null) return false;
        } else if (!validRatesId.equals(other.validRatesId)) return false;
        return true;
    }
}
