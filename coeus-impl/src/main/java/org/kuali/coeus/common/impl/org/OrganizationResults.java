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
