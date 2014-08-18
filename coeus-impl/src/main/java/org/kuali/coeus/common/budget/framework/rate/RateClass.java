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
package org.kuali.coeus.common.budget.framework.rate;

import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

@Entity
@Table(name = "RATE_CLASS")
public class RateClass extends KcPersistableBusinessObjectBase implements RateClassContract {

    @Id
    @Column(name = "RATE_CLASS_CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATE_CLASS_TYPE")
    private String rateClassTypeCode;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_TYPE", referencedColumnName = "RATE_CLASS_TYPE", insertable = false, updatable = false)
    private RateClassType rateClassType;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRateClassTypeCode() {
        return rateClassTypeCode;
    }

    public void setRateClassTypeCode(String rateClassTypeCode) {
        this.rateClassTypeCode = rateClassTypeCode;
    }

    @Override
    public RateClassType getRateClassType() {
        return rateClassType;
    }

    public void setRateClassType(RateClassType rateClassType) {
        this.rateClassType = rateClassType;
    }
}
