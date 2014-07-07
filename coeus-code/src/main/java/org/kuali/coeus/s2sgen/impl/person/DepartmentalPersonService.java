package org.kuali.coeus.s2sgen.impl.person;


import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

public interface DepartmentalPersonService {

    /**
     *
     * This method populates and returns the Departmental Person object for a
     * given proposal document
     *
     * @param pdDoc
     *            Proposal Development Document.
     * @return DepartmentalPerson departmental Person object for a given
     *         proposal document.
     */
    DepartmentalPersonDto getDepartmentalPerson(ProposalDevelopmentDocumentContract pdDoc);

    /**
     *
     * This method is used to get the details of Contact person
     *
     * @param pdDoc(ProposalDevelopmentDocument)
     *            proposal development document.
     *            for which the DepartmentalPerson has to be found.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    DepartmentalPersonDto getContactPerson(ProposalDevelopmentDocumentContract pdDoc) ;
}
