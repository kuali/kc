package org.kuali.coeus.propdev.api.s2s;

import java.util.List;

public interface UserAttachedFormService {

    /**
     * This method find a S2S User Attached Form by a proposal number and namespace.  There is supposed to be only one
     * form with the same proposal number and namespace combination.  If more that one is found the first one is
     * returned.  If no matching forms are found null is returned.
     *
     * @param proposalNumber  the proposal number.  Cannot be blank
     * @param namespace the namespace.  Cannot be blank.
     * @return a S2S User Attached Form or null.
     * @throws java.lang.IllegalArgumentException if either proposalNumber or namespace is blank.
     */
    S2sUserAttachedFormContract findFormByProposalNumberAndNamespace(String proposalNumber, String namespace);

    /**
     * This method find a S2S User Attached Form name by a proposal number and namespace.  There is supposed to be only one
     * form with the same proposal number and namespace combination.  If more that one is found the first name is
     * returned.  If no matching forms are found null is returned.
     *
     * @param proposalNumber  the proposal number.  Cannot be blank
     * @param namespace the namespace.  Cannot be blank.
     * @return a S2S User Attached Form name or null.
     * @throws java.lang.IllegalArgumentException if either proposalNumber or namespace is blank.
     */
    String findFormNameByProposalNumberAndNamespace(String proposalNumber, String namespace);

    /**
     * This method find all the user attached form namespaces for a given proposal number.  This list should not contain
     * duplicates but in theory could.  This method will return an empty collection if no namespaces are found.
     *
     * @param proposalNumber  the proposal number.  Cannot be blank
     * @return a list of namespaces or an empty list.
     * @throws java.lang.IllegalArgumentException if either proposalNumber.
     */
    List<String> findFormNamespaces(String proposalNumber);

    /**
     * This method finds a user attached form file from a user attached form.  The selectedForm cannot be null.
     *
     * @param selectedForm the user attached form or null
     * @return the user attached form file or null
     * @throws java.lang.IllegalArgumentException if the selectedForm is null
     */
    S2sUserAttachedFormFileContract findUserAttachedFormFile(S2sUserAttachedFormContract selectedForm);

    /**
     * This method finds a user attached attachment form file from a user attached form attachment.
     * The selectedFormAtt cannot be null.
     *
     * @param selectedFormAtt the user attached form attachment or null
     * @return the user attached form attachment file or null
     * @throws java.lang.IllegalArgumentException if the selectedFormAtt is null
     */
    S2sUserAttachedFormAttFileContract findUserAttachedFormAttFile(S2sUserAttachedFormAttContract selectedFormAtt);
}
