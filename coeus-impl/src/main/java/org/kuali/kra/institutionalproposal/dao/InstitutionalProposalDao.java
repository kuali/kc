package org.kuali.kra.institutionalproposal.dao;

import org.kuali.kra.award.home.Award;

/**
 * Data access for institutional proposals
 */
public interface InstitutionalProposalDao {
    /**
     * Retrieves the proposal id for the given award
     * @param award the award to find the proposal id for
     * @return the proposal id, or null if nothing can be found
     */
    public Long getProposalId(Award award);
}
