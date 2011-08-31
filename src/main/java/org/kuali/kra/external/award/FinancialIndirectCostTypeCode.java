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
package org.kuali.kra.external.award;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.rates.RateType;

public class FinancialIndirectCostTypeCode extends KraPersistableBusinessObjectBase {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8568491088737240989L;
    private String rateClassCode;
    private String rateTypeCode;
    private String idcRateTypeCode;

    private RateClass rateClass;
    private RateType rateType;

    public RateClass getRateClass() {
        return rateClass;
    }


    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }


    public RateType getRateType() {
        return rateType;
    }


    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }


    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }


    public String getIdcRateTypeCode() {
        return idcRateTypeCode;
    }


    public void setIdcRateTypeCode(String idcRateTypeCode) {
        this.idcRateTypeCode = idcRateTypeCode;
    }

    public String getRateClassCode() {
        return rateClassCode;
    }


    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }


    public String getRateTypeCode() {
        return rateTypeCode;
    }
    
    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

}
