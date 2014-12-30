package org.kuali.kra.external.frequency;

import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.external.service.KcDtoServiceBase;

public class FrequencyDtoService extends KcDtoServiceBase<FrequencyDto, Frequency> {

	@Override
	public FrequencyDto buildDto(Frequency dataObject) {
		if (dataObject != null) {
			FrequencyDto dto = new FrequencyDto();
			dto.setFrequencyCode(dataObject.getFrequencyCode());
			dto.setDescription(dataObject.getDescription());
			return dto;
		} else {
			return null;
		}
	}


}
