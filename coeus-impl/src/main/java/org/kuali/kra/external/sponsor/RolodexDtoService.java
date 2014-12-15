package org.kuali.kra.external.sponsor;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.kra.external.service.KcDtoServiceBase;

public class RolodexDtoService extends KcDtoServiceBase<RolodexDTO, Rolodex> {

	@Override
	public RolodexDTO buildDto(Rolodex rolodex) {
		RolodexDTO dto = new RolodexDTO();
		dto.setRolodexId(rolodex.getRolodexId());
		dto.setAddressLine1(rolodex.getAddressLine1());
		dto.setAddressLine2(rolodex.getAddressLine2());
		dto.setAddressLine3(rolodex.getAddressLine3());
		dto.setCity(rolodex.getCity());
		dto.setCountryCode(rolodex.getCountryCode());
		dto.setEmailAddress(rolodex.getEmailAddress());
		dto.setFaxNumber(rolodex.getFaxNumber());
		dto.setFirstName(rolodex.getFirstName());
		dto.setLastName(rolodex.getLastName());
		dto.setMiddleName(rolodex.getMiddleName());
		dto.setFullName(rolodex.getFullName());
		dto.setPhoneNumber(rolodex.getPhoneNumber());
		dto.setPostalCode(rolodex.getPostalCode());
		dto.setState(rolodex.getState());
		dto.setOrganizationName(rolodex.getOrganization());
		dto.setActive(rolodex.isActive());
		return dto;
	}

}
