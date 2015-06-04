package org.kuali.coeus.common.api.budget.rates;

public class RateClassTypeDto {

    private String code;
    private String description;
    private Integer sortId;
    private Boolean prefixActivityType;
    
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
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public Boolean getPrefixActivityType() {
		return prefixActivityType;
	}
	public void setPrefixActivityType(Boolean prefixActivityType) {
		this.prefixActivityType = prefixActivityType;
	}
}
