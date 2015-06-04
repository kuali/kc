package org.kuali.coeus.common.api.budget.rates;

public class RateClassDto {
	
    private String code;
    private String description;
    private String rateClassTypeCode;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
}
