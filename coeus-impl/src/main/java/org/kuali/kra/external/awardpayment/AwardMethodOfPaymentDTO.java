package org.kuali.kra.external.awardpayment;

import java.io.Serializable;

public class AwardMethodOfPaymentDTO implements Serializable {

	private static final long serialVersionUID = 492600544085327507L;
	
	private String methodOfPaymentCode;
    private String description;
    
    public AwardMethodOfPaymentDTO() { }
        
	public String getMethodOfPaymentCode() {
		return methodOfPaymentCode;
	}
	public void setMethodOfPaymentCode(String methodOfPaymentCode) {
		this.methodOfPaymentCode = methodOfPaymentCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
