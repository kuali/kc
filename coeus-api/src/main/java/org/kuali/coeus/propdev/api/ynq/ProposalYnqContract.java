package org.kuali.coeus.propdev.api.ynq;

import org.kuali.coeus.common.api.ynq.YnqContract;
import org.kuali.coeus.propdev.api.core.NumberedProposal;

import java.util.Date;

public interface ProposalYnqContract extends NumberedProposal {

    String getAnswer();

    String getExplanation();

    Date getReviewDate();

    YnqContract getYnq();
}
