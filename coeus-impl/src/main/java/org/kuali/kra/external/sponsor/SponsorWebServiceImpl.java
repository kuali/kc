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

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.ObjectUtils;

public class SponsorWebServiceImpl implements SponsorWebService {

	private BusinessObjectService businessObjectService;
    private LegacyDataAdapter legacyDataAdapter;
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
            sponsors = legacyDataAdapter.findCollectionBySearchHelper(Sponsor.class, Collections.singletonMap("sponsorCode", searchCriteria.getSponsorCode()), Collections.<String>emptyList(), true, false, 0);
		} else {
			sponsors = legacyDataAdapter.findCollectionBySearchHelper(Sponsor.class, Collections.singletonMap("customerNumber", searchCriteria.getCustomerNumber()), Collections.<String>emptyList(), true, false, 0);
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

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

    public LegacyDataAdapter getLookupService() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

    public KcDtoService<SponsorDTO, Sponsor> getSponsorDtoService() {
		return sponsorDtoService;
	}

	public void setSponsorDtoService(
			KcDtoService<SponsorDTO, Sponsor> sponsorDtoService) {
		this.sponsorDtoService = sponsorDtoService;
	}

}
