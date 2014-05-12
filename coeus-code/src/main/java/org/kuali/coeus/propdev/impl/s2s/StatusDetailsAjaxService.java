package org.kuali.coeus.propdev.impl.s2s;

public interface StatusDetailsAjaxService {

    /**
     *
     * This method is used to get the application status details.
     *
     * @param ggTrackingId
     *            grants gov tracking id for the application.
     * @param proposalNumber
     *            Proposal number.
     */
    String getStatusDetails(String ggTrackingId, String proposalNumber);
}
