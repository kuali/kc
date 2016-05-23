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
package org.kuali.coeus.common.impl.org;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudMapBasedRestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codiform.moo.curry.Translate;

public class OrganizationController extends SimpleCrudMapBasedRestController<Organization> {
	
	@RequestMapping(params="summary", method=RequestMethod.GET)
	public @ResponseBody OrganizationResults getOrganizationSummary() {
		assertMethodSupported(RequestMethod.GET);
		assertUserHasReadAccess();

		final SearchResults<Organization> organizationSearchResults = new SearchResults<>();
		organizationSearchResults.setResults(getAllFromDataStore());
		if (organizationSearchResults.getResults() == null || organizationSearchResults.getResults().size() == 0) {
			throw new ResourceNotFoundException("not found");
		}
		organizationSearchResults.setTotalResults(organizationSearchResults.getResults().size());
		return Translate.to(OrganizationResults.class).from(organizationSearchResults);
	}
}
