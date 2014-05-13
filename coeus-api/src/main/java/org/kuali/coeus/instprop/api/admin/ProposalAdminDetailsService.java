package org.kuali.coeus.instprop.api.admin;


import java.util.List;

public interface ProposalAdminDetailsService {

    /**
     * This method retrieves Proposal Admin Details by a Development Proposal's proposal number.
     * If no matching Proposal Admin Details are found an empty list is returned.
     * The proposal number cannot be blank.
     *
     * @param proposalNumber the development proposal's proposal number.  cannot be blank.
     * @return a list of  Proposal Admin Details or an empty list not found.
     * @throws java.lang.IllegalArgumentException if the proposalNumber is blank
     */
    List<? extends ProposalAdminDetailsContract> findProposalAdminDetailsByPropDevNumber(String proposalNumber);
}
