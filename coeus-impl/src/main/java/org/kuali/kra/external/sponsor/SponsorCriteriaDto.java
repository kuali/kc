package org.kuali.kra.external.sponsor;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sponsorCriteriaDto", propOrder = {
    "sponsorCode",
    "customerNumber"
})
public class SponsorCriteriaDto implements Serializable {

	private static final long serialVersionUID = -2460442390785148763L;

	private String sponsorCode;
	private String customerNumber;
	
	public String getSponsorCode() {
		return sponsorCode;
	}
	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
}
