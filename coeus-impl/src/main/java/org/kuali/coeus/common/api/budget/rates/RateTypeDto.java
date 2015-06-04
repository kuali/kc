package org.kuali.coeus.common.api.budget.rates;

public class RateTypeDto {

    private String rateClassCode;
    private String rateTypeCode;
    private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
