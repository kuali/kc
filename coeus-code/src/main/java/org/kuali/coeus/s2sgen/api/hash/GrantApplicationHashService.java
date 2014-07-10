package org.kuali.coeus.s2sgen.api.hash;

/**
 * Service to compute hashes for s2s.
 */
public interface GrantApplicationHashService {

    /**
     * Computes a hash for a grant application in string form.  xml cannot be blank.
     * @param xml the grant application xml.  cannot be blank
     * @return the hash
     */
    String computeGrantFormsHash(String xml);

    /**
     * Computes a hash for an attachment in byte array form.  attachment cannot be null or empty.
     * @param attachment the attachment.  cannot be null or empty
     * @return the hash
     */
    String computeAttachmentHash(byte[] attachment);
}
