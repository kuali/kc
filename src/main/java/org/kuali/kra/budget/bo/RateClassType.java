package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class RateClassType extends KraPersistableBusinessObjectBase {

	private String rateClassType;
	private String description;
	private String sortId;
    private Boolean prefixActivityType;

	public RateClassType(){
		super();
	}

	public String getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(String rateClassType) {
		this.rateClassType = rateClassType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("rateClassType", getRateClassType());
		hashMap.put("description", getDescription());
        hashMap.put("prefixActivityType", getPrefixActivityType());
		return hashMap;
	}

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }
    
    public final Boolean getPrefixActivityType() {
        return prefixActivityType;
    }

    public final void setPrefixActivityType(Boolean prefixActivityType) {
        this.prefixActivityType = prefixActivityType;
    }
    
}
