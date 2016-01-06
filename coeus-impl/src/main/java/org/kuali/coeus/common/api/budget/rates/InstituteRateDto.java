/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.api.budget.rates;

import java.util.Date;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import com.codiform.moo.annotation.Property;

public class InstituteRateDto {

	private Long id;
    private String fiscalYear;
    private Boolean onOffCampusFlag;
    private String rateClassCode;
    private String rateTypeCode;
    @Property(translate = true)
    private Date startDate;
    private ScaleTwoDecimal instituteRate;
    private String unitNumber;
	private String activityTypeCode;
	
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
	public String getRateTypeCode() {
		return rateTypeCode;
	}
	public void setRateTypeCode(String rateTypeCode) {
		this.rateTypeCode = rateTypeCode;
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
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getActivityTypeCode() {
		return activityTypeCode;
	}
	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
