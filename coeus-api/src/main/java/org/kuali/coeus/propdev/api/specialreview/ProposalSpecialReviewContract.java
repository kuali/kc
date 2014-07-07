package org.kuali.coeus.propdev.api.specialreview;

import org.kuali.coeus.common.api.compliance.core.SpecialReviewContract;
import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

import java.util.List;

public interface ProposalSpecialReviewContract extends NumberedProposal, IdentifiableNumeric, SpecialReviewContract, HierarchicalProposal {

    List<? extends ProposalSpecialReviewExemptionContract> getSpecialReviewExemptions();
}
