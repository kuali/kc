package org.kuali.coeus.propdev.impl.s2s.connect;

import java.security.KeyStore;

public interface S2SCertificateReader {
    KeyStore getKeyStore() throws S2sCommunicationException;
    KeyStore getTrustStore() throws S2sCommunicationException;
    String getKeyStoreLocation();
    String getKeyStorePassword();
    String getTrustStoreLocation();
    String getTrustStorePassword();
    String getJksType();
}
