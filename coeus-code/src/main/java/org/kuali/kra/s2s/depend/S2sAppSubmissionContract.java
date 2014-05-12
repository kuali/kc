package org.kuali.kra.s2s.depend;

import java.util.Date;

public interface S2sAppSubmissionContract {
    String getProposalNumber();

    Integer getSubmissionNumber();

    String getAgencyTrackingId();

    String getComments();

    String getGgTrackingId();

    Date getLastModifiedDate();

    Date getLastNotifiedDate();

    Date getReceivedDate();

    String getStatus();

    S2sApplicationContract getS2sApplication();
}
