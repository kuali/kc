/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.s2s.connect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class S2SCertificateReaderBase implements S2SCertificateReader {

    private static final Log LOG = LogFactory.getLog(S2SCertificateReaderBase.class);

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    private String keyStoreLocation;
    private String keyStorePassword;
    private String trustStoreLocation;
    private String trustStorePassword;
    private String jksType = "JKS";
    
    private KeyStore keyStore = null;
    private KeyStore trustStore = null;

    @Override
    public KeyStore getKeyStore() throws S2sCommunicationException {
        if(keyStore!=null) return keyStore;
        try {
            keyStore = KeyStore.getInstance(jksType);
            keyStore.load(new FileInputStream(getS2SConfigurationService().getValueAsString(keyStoreLocation)),
                    getS2SConfigurationService().getValueAsString(keyStorePassword).toCharArray());
        }catch (KeyStoreException e) {
            keyStore = null;
            LOG.error("Error while creating Keystore with cert " +keyStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_KEYSTORE_CREATION,e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            keyStore = null;
            LOG.error("JCE provider doesnt support certificate algorithm "+keyStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_KEYSTORE_NO_ALGORITHM,e.getMessage());
        }catch (CertificateException e) {
            keyStore = null;
            LOG.error("Error while creating keystore "+keyStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_KEYSTORE_BAD_CERTIFICATE,e.getMessage());
        }catch (FileNotFoundException e) {
            keyStore = null;
            LOG.error("File not found "+keyStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_KEYSTORE_NOT_FOUND,e.getMessage());
        }catch (IOException e) {
            keyStore = null;
            LOG.error("IO Exception while reading keystore file "+keyStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_KEYSTORE_CANNOT_READ,e.getMessage());
        }
        return keyStore;
    }

    @Override
    public KeyStore getTrustStore() throws S2sCommunicationException {
        if(trustStore!=null)
            return trustStore;
        try {
            trustStore = KeyStore.getInstance(jksType);
            trustStore.load(new FileInputStream(getS2SConfigurationService().getValueAsString(trustStoreLocation)),
                    getS2SConfigurationService().getValueAsString(trustStorePassword).toCharArray());
        }catch (KeyStoreException e) {
            trustStore = null;
            LOG.error("Error while creating Keystore with cert " +trustStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_TRUSTSTORE_CREATION,e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            trustStore = null;
            LOG.error("JCE provider doesnt support certificate algorithm "+trustStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_TRUSTSTORE_NO_ALGORITHM,e.getMessage());
        }catch (CertificateException e) {
            trustStore = null;
            LOG.error("Error while creating keystore "+trustStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_TRUSTSTORE_BAD_CERTIFICATE,e.getMessage());
        }catch (FileNotFoundException e) {
            trustStore = null;
            LOG.error("File not found "+trustStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_TRUSTSTORE_NOT_FOUND,e.getMessage());
        }catch (IOException e) {
            trustStore = null;
            LOG.error("IO Exception while reading keystore file "+trustStoreLocation, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_TRUSTSTORE_CANNOT_READ,e.getMessage());
        }
        return trustStore;
    }

    public String getKeyStoreLocation() {
        return keyStoreLocation;
    }

    /**
     * The configuration parameter name that defines the keystore location
     * @param keyStoreLocation
     */
    public void setKeyStoreLocation(String keyStoreLocation) {
        this.keyStoreLocation = keyStoreLocation;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * The configuration parameter name that defines the keystore password
     * @param keyStorePassword
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public String getTrustStoreLocation() {
        return trustStoreLocation;
    }

    /**
     * The configuration parameter that defines the trust store location
     * @param trustStoreLocation
     */
    public void setTrustStoreLocation(String trustStoreLocation) {
        this.trustStoreLocation = trustStoreLocation;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    /**
     * The configuration parameter the defines the trust store password
     * @param trustStorePassword
     */
    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public String getJksType() {
        return jksType;
    }

    public void setJksType(String jksType) {
        this.jksType = jksType;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }
}
