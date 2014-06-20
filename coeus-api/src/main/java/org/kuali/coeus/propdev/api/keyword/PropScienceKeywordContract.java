package org.kuali.coeus.propdev.api.keyword;

import org.kuali.coeus.common.api.keyword.ScienceKeywordContract;
import org.kuali.coeus.propdev.api.core.NumberedProposal;

public interface PropScienceKeywordContract extends NumberedProposal {

    ScienceKeywordContract getScienceKeyword();

    String getHierarchyProposalNumber();

    boolean isHiddenInHierarchy();
}
