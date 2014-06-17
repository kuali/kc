package org.kuali.coeus.propdev.api.specialreview;

import org.kuali.coeus.propdev.api.core.NumberedProposal;

import java.util.List;

public interface ProposalSpecialReviewContract extends NumberedProposal {

    Long getProposalSpecialReviewId();

    String getHierarchyProposalNumber();

    boolean isHiddenInHierarchy();

    List<? extends ProposalSpecialReviewExemptionContract> getSpecialReviewExemptions();
}
