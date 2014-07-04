package org.kuali.coeus.propdev.api.location;

import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.propdev.api.core.NumberedProposal;

import java.util.List;

public interface ProposalSiteContract extends NumberedProposal {

    Integer getSiteNumber();

    String getLocationName();

    Integer getLocationTypeCode();

    OrganizationContract getOrganization();

    RolodexContract getRolodex();

    List<? extends CongressionalDistrictContract> getCongressionalDistricts();

    String getFirstCongressionalDistrictName();
}
