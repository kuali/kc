package org.kuali.kra.external.awardpayment;

import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.external.service.KcDtoServiceBase;

public class AwardBasisOfPaymentDtoService extends KcDtoServiceBase<AwardBasisOfPaymentDTO, AwardBasisOfPayment> {

	@Override
	public AwardBasisOfPaymentDTO buildDto(AwardBasisOfPayment basisOfPayment) {
    	AwardBasisOfPaymentDTO dto = new AwardBasisOfPaymentDTO();
    	if (basisOfPayment != null) {
	    	dto.setBasisOfPaymentCode(basisOfPayment.getBasisOfPaymentCode());
	    	dto.setDescription(basisOfPayment.getDescription());
	    	return dto;
    	} else {
    		return null;
    	}
	}
}
