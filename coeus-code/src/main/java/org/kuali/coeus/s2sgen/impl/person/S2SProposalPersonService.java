package org.kuali.coeus.s2sgen.impl.person;

import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;

import org.kuali.coeus.s2sgen.impl.citizenship.CitizenshipType;

import java.util.List;

public interface S2SProposalPersonService {

    /**
     *
     * This method limits the number of key persons to n, returns list of key
     * persons, first n in case firstN is true, or all other than first n, in
     * case of firstN being false
     *
     * @param keyPersons
     *            list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     * @param firstN
     *            value that determines whether the returned list should contain
     *            first n persons or the rest of persons
     * @param n
     *            number of key persons that are considered as not extra persons
     * @return list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     */
    List<ProposalPersonContract> getNKeyPersons(List<? extends ProposalPersonContract> keyPersons,
                                                       boolean firstN, int n);

    /**
     *
     * This method is to get PrincipalInvestigator from person list
     *
     * @param pdDoc
     *            Proposal development document.
     * @return ProposalPerson PrincipalInvestigator for the proposal.
     */
    ProposalPersonContract getPrincipalInvestigator(
            ProposalDevelopmentDocumentContract pdDoc);

    /**
     * Finds all the co-investigators associated with the provided pdDoc.
     * @param pdDoc
     * @return List of Co-Investigators {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}.
     */

    List<ProposalPersonContract> getCoInvestigators(ProposalDevelopmentDocumentContract pdDoc);

    /**
     * Finds all the key Person associated with the provided pdDoc.
     * @param pdDoc
     * @return List of Key Persons {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}.
     */

    List<ProposalPersonContract> getKeyPersons (ProposalDevelopmentDocumentContract pdDoc);

    /**
     *
     * This method is used to get the citizenship from either warehouse or from person custom element
     * @param proposalPerson
     * @return
     */
    CitizenshipType getCitizenship(ProposalPersonContract proposalPerson);
}
