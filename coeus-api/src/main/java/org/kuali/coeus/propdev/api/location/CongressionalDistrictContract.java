package org.kuali.coeus.propdev.api.location;

import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface CongressionalDistrictContract extends IdentifiableNumeric, NumberedProposal {

    Integer getSiteNumber();

    String getCongressionalDistrict();
}
