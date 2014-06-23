package org.kuali.coeus.propdev.api.specialreview;

import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

import java.util.List;

public interface ProposalSpecialReviewContract extends NumberedProposal, IdentifiableNumeric {

    String getHierarchyProposalNumber();

    boolean isHiddenInHierarchy();

    List<? extends ProposalSpecialReviewExemptionContract> getSpecialReviewExemptions();
}
