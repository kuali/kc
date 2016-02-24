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
package org.kuali.coeus.common.budget.framework.rate;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.api.rate.TrainingStipendRateContract;

import java.sql.Date;

public class TrainingStipendRate extends KcPersistableBusinessObjectBase implements TrainingStipendRateContract {

    private Long id;

    private String careerLevel;

    private int experienceLevel;

    private ScaleTwoDecimal stipendRate;

    private Date effectiveDate;

    private String description;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String level) {
        this.careerLevel = level;
    }

    @Override
    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int level) {
        this.experienceLevel = level;
    }

    @Override
    public ScaleTwoDecimal getStipendRate() {
        return stipendRate;
    }

    public void setStipendRate(ScaleTwoDecimal rate) {
        this.stipendRate = rate;
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
