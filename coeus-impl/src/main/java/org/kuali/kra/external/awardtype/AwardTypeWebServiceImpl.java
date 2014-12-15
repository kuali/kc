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
