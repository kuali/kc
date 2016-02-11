package org.kuali.coeus.common.impl.org;

import java.util.Collection;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codiform.moo.curry.Translate;

@Controller("organizationController")
public class OrganizationController extends RestController {
	
	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;

	@Deprecated
	@RequestMapping(value="/v1/organizationSummary")
	public @ResponseBody OrganizationResults getOldOrganizationSummary() {
		return getOrganizationSummary();
	}
	
	@RequestMapping(value="/api/v1/organizations", params="summary", method=RequestMethod.GET)
	public @ResponseBody OrganizationResults getOrganizationSummary() {
		SearchResults<Organization> organizationSearchResults = new SearchResults<>();
		organizationSearchResults.setResults(getAllOrganizations());
		if (organizationSearchResults.getResults() == null || organizationSearchResults.getResults().size() == 0) {
			throw new ResourceNotFoundException("not found");
		}
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
