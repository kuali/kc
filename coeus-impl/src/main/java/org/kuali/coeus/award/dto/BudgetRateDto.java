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

package org.kuali.coeus.award.dto;


import com.codiform.moo.annotation.Property;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import java.sql.Date;

public class BudgetRateDto {
    private String activityTypeCode;
    private ScaleTwoDecimal applicableRate;
    private String fiscalYear;
    private Boolean onOffCampusFlag;
    private String rateClassCode;
    @JsonIgnore
    @Property(translate = true)
    private RateClassDto rateClass;
    private String rateTypeCode;
    @JsonIgnore
    @Property(translate = true)
    private RateTypeDto rateType;
    private Date startDate;
    private ScaleTwoDecimal instituteRate;


    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public ScaleTwoDecimal getApplicableRate() {
        return applicableRate;
    }

    public void setApplicableRate(ScaleTwoDecimal applicableRate) {
        this.applicableRate = applicableRate;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }
    @JsonProperty
    public RateClassDto getRateClass() {
        return rateClass;
    }
    @JsonIgnore
    public void setRateClass(RateClassDto rateClass) {
        this.rateClass = rateClass;
    }

    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }
    @JsonProperty
    public RateTypeDto getRateType() {
        return rateType;
    }
    @JsonIgnore
    public void setRateType(RateTypeDto rateType) {
        this.rateType = rateType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ScaleTwoDecimal getInstituteRate() {
        return instituteRate;
    }

    public void setInstituteRate(ScaleTwoDecimal instituteRate) {
        this.instituteRate = instituteRate;
    }
}