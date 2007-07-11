package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CarrierType extends KraPersistableBusinessObjectBase {

	private Integer carrierTypeCode;
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCarrierTypeCode() {
		return carrierTypeCode;
	}
	public void setCarrierTypeCode(Integer carrierTypeCode) {
		this.carrierTypeCode = carrierTypeCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("carrierTypeCode", this.getCarrierTypeCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
