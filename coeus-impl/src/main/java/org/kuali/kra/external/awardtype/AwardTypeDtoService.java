package org.kuali.kra.external.awardtype;

import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.external.service.KcDtoServiceBase;

public class AwardTypeDtoService extends KcDtoServiceBase<AwardTypeDTO, AwardType> {

	@Override
	public AwardTypeDTO buildDto(AwardType awardType) {
		if (awardType != null) {
			AwardTypeDTO dto = new AwardTypeDTO();
			dto.setAwardTypeCode(awardType.getCode());
			dto.setDescription(awardType.getDescription());
			return dto;
		} else {
			return null;
		}	}

}
