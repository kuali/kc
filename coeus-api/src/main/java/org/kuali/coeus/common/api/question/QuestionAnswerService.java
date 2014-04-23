package org.kuali.coeus.common.api.question;


public interface QuestionAnswerService {

    /**
     * This service retrieves the description of an answer.  If the answer id is null, this
     * method will throw an {@link java.lang.IllegalArgumentException}.  This method, only
     * supports retrieving descriptions where {@link #isAnswerDescriptionRetrievalSupported(Long)}
     * returns true. Note: that some answer may not have a description configured.  This can result in
     * a null return value.
     *
     * @param answerId the answer id
     * @return the description of null
     * @throws java.lang.IllegalArgumentException if the answer id is null
     * @throws java.lang.UnsupportedOperationException if description retrieval is not supported
     */
    String getAnswerDescription(Long answerId);

    /**
     * This method returns true if a answer description retrieval is supported for a specific answer.
     * If the answer id is null, this method will throw an {@link java.lang.IllegalArgumentException}.
     *
     * @param answerId the answer id
     * @return true if answer description retrieval is supported
     * @throws java.lang.IllegalArgumentException if the answer id is null
     */
    boolean isAnswerDescriptionRetrievalSupported(Long answerId);
}
