/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
