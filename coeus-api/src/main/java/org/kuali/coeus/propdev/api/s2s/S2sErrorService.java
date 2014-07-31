package org.kuali.coeus.propdev.api.s2s;

public interface S2sErrorService {

    /**
     * Finds an s2s error by key.  The key cannot be blank.
     * @param key the key.  cannot be null.
     * @return an s2s error or null if not found.
     * @throws java.lang.IllegalArgumentException if the key is blank
     */
    S2sErrorContract findS2sErrorByKey(String key);
}
