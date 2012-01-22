/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.rates;

import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class TrainingStipendRate extends KraPersistableBusinessObjectBase {

    private Long rateId;

    private String careerLevel;

    private int experienceLevel;

    private BudgetDecimal stipendRate;

    private Date effectiveDate;

    private String description;

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long id) {
        this.rateId = id;
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String level) {
        this.careerLevel = level;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int level) {
        this.experienceLevel = level;
    }

    public BudgetDecimal getStipendRate() {
        return stipendRate;
    }

    public void setStipendRate(BudgetDecimal rate) {
        this.stipendRate = rate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
