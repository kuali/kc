package org.kuali.kra.external.awardpayment;

import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.external.service.KcDtoServiceBase;

public class AwardMethodOfPaymentDtoService extends KcDtoServiceBase<AwardMethodOfPaymentDTO, AwardMethodOfPayment> {

	@Override
	public AwardMethodOfPaymentDTO buildDto(AwardMethodOfPayment methodOfPayment) {
		AwardMethodOfPaymentDTO dto = new AwardMethodOfPaymentDTO();
    	if (methodOfPayment != null) {
    		dto.setMethodOfPaymentCode(methodOfPayment.getMethodOfPaymentCode());
    		dto.setDescription(methodOfPayment.getDescription());
	    	return dto;
    	} else {
    		return null;
    	}
	}
}
