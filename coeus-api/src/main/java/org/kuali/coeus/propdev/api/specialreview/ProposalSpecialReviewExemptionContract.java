package org.kuali.coeus.propdev.api.specialreview;

import org.kuali.coeus.common.api.compliance.exemption.ExemptionTypeContract;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface ProposalSpecialReviewExemptionContract extends IdentifiableNumeric {
    ExemptionTypeContract getExemptionType();
}
