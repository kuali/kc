package org.kuali.kra.budget.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="RATE_CLASS_TYPE")
public class RateClassType extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="RATE_CLASS_TYPE")
	private String rateClassType;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="SORT_ID")
	private String sortId;
    @Column(name="PREFIX_ACTIVITY_TYPE")
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

