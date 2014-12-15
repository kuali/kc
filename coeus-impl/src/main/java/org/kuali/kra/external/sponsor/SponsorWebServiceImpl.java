package org.kuali.kra.external.sponsor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SponsorWebServiceImpl implements SponsorWebService {

	private BusinessObjectService businessObjectService;
	private KcDtoService<SponsorDTO, Sponsor> sponsorDtoService;
	
	public SponsorDTO getSponsor(String sponsorCode) {
		if (StringUtils.isNotBlank(sponsorCode)) {
			Sponsor sponsor = getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, sponsorCode);
			if (sponsor != null) {
				SponsorDTO result = sponsorDtoService.buildDto(sponsor);
				return result;
			}
		}
		return null;
	}

	public List<SponsorDTO> getMatchingSponsors(SponsorCriteriaDto searchCriteria) {
		List<SponsorDTO> results = new ArrayList<SponsorDTO>();
		Collection<Sponsor> sponsors;
		if (ObjectUtils.isNull(searchCriteria) ||
				(StringUtils.isEmpty(searchCriteria.getSponsorCode())
				&& StringUtils.isEmpty(searchCriteria.getCustomerNumber()))) {
			sponsors = getBusinessObjectService().findAll(Sponsor.class);
		} else if (StringUtils.isNotEmpty(searchCriteria.getSponsorCode())) {
			sponsors = new ArrayList<Sponsor>();
			sponsors.add(getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, searchCriteria.getSponsorCode()));
		} else {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("customerNumber", searchCriteria.getCustomerNumber());
			sponsors = getBusinessObjectService().findMatching(Sponsor.class, values);
		}
		if (sponsors != null && !sponsors.isEmpty()) {
			for (Sponsor sponsor : sponsors) {
				results.add(sponsorDtoService.buildDto(sponsor));
			}
		}
		return results;
	}

	protected BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	@Autowired
	@Qualifier("businessObjectService")
	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public KcDtoService<SponsorDTO, Sponsor> getSponsorDtoService() {
		return sponsorDtoService;
	}

	public void setSponsorDtoService(
			KcDtoService<SponsorDTO, Sponsor> sponsorDtoService) {
		this.sponsorDtoService = sponsorDtoService;
	}

}
