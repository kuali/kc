package org.kuali.coeus.propdev.api.ynq;

import org.kuali.coeus.common.api.ynq.YnqContract;

import java.util.Date;

public interface ProposalYnqContract {
    String getProposalNumber();

    String getAnswer();

    String getExplanation();

    Date getReviewDate();

    YnqContract getYnq();
}
