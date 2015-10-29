package org.kuali.coeus.common.impl.org;

import java.util.Collection;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.summary.SearchResults;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codiform.moo.Moo;
import com.codiform.moo.curry.Translate;

@Controller("organizationSummaryController")
public class OrganizationSummaryController {
	
	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;

	@RequestMapping(value="/v1/organizationSummary")
	public @ResponseBody OrganizationResults getOrganizationSummary() {
		Moo moo = new Moo();
		SearchResults<Organization> organizationSearchResults = new SearchResults<>();
		organizationSearchResults.setResults(getAllOrganizations());
		organizationSearchResults.setTotalResults(organizationSearchResults.getResults().size());
		return Translate.to(OrganizationResults.class).from(organizationSearchResults);
	}
	
	Collection<Organization> getAllOrganizations() {
		return getBusinessObjectService().findAll(Organization.class);
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}
}
