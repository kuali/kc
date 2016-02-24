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

import java.util.Collection;

import org.kuali.coeus.common.framework.org.OrganizationSummaryDto;
import org.kuali.coeus.sys.framework.rest.ResponseResults;

import com.codiform.moo.annotation.CollectionProperty;

public final class OrganizationResults extends ResponseResults {
	@CollectionProperty(source="results", itemClass=OrganizationSummaryDto.class)
	private Collection<OrganizationSummaryDto> organizations;

	public Collection<OrganizationSummaryDto> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Collection<OrganizationSummaryDto> organizations) {
		this.organizations = organizations;
	}
}
