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
package org.kuali.kra.external.awardtype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AwardTypeWebServiceImpl implements AwardTypeWebService {

	private BusinessObjectService businessObjectService;
	private KcDtoService<AwardTypeDTO, AwardType> awardTypeDtoService;
	
	@Override
	public AwardTypeDTO getAwardType(@WebParam(name = "awardTypeCode") Integer awardTypeCode) {
		return awardTypeDtoService.buildDto(getBusinessObjectService().findBySinglePrimaryKey(AwardType.class, awardTypeCode));
	}

	@Override
	public List<AwardTypeDTO> findMatching(@WebParam(name="searchCriteria") List<HashMapElement> searchCriteria) {
		Map<String, String> values = new HashMap<String, String>();
		for (HashMapElement element : searchCriteria) {
			values.put(element.getKey(), element.getValue());
		}
		return getAwardTypeDTO(getBusinessObjectService().findMatching(AwardType.class, values));
	}
	
	protected List<AwardTypeDTO> getAwardTypeDTO(Collection<AwardType> awardTypes) {
		List<AwardTypeDTO> results = new ArrayList<AwardTypeDTO>();
		if (awardTypes != null) {
			for (AwardType awardType : awardTypes) {
				results.add(awardTypeDtoService.buildDto(awardType));
			}
		}
		return results;
	}

	protected BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public KcDtoService<AwardTypeDTO, AwardType> getAwardTypeDtoService() {
		return awardTypeDtoService;
	}

	public void setAwardTypeDtoService(
			KcDtoService<AwardTypeDTO, AwardType> awardTypeDtoService) {
		this.awardTypeDtoService = awardTypeDtoService;
	}

}
