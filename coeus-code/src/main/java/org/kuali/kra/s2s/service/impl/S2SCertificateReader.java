/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.S2SUtilService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class S2SCertificateReader {
    private String keyStoreLocation;
    private String keyStorePassword;
    private String trustStoreLocation;
    private String trustStorePassword;
    private String jksType = "JKS";
    
    private KeyStore keyStore = null;
    private KeyStore trustStore = null;
    
    private static final Log LOG = LogFactory.getLog(S2SCertificateReader.class);
    public KeyStore getKeyStore() throws S2SException{
        if(keyStore!=null) return keyStore;
        try {
            keyStore = KeyStore.getInstance(jksType);
            keyStore.load(new FileInputStream(getS2SUtilService().getProperty(keyStoreLocation)),
                    getS2SUtilService().getProperty(keyStorePassword).toCharArray());
        }catch (KeyStoreException e) {
            keyStore = null;
            LOG.error("Error while creating Keystore with cert " +keyStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_CREATION,e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            keyStore = null;
            LOG.error("JCE provider doesnt support certificate algorithm "+keyStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_NO_ALGORITHM,e.getMessage());
        }catch (CertificateException e) {
            keyStore = null;
            LOG.error("Error while creating keystore "+keyStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_BAD_CERTIFICATE,e.getMessage());
        }catch (FileNotFoundException e) {
            keyStore = null;
            LOG.error("File not found "+keyStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_NOT_FOUND,e.getMessage());
        }catch (IOException e) {
            keyStore = null;
            LOG.error("IO Exception while reading keystore file "+keyStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_CANNOT_READ,e.getMessage());
        }
        return keyStore;
    }

    private static S2SUtilService getS2SUtilService() {
        return KcServiceLocator.getService(S2SUtilService.class);
    }

    public KeyStore getTrustStore() throws S2SException{
        if(trustStore!=null)
            return trustStore;
        try {
            trustStore = KeyStore.getInstance(jksType);
            trustStore.load(new FileInputStream(getS2SUtilService().getProperty(trustStoreLocation)),
                    getS2SUtilService().getProperty(trustStorePassword).toCharArray());
        }catch (KeyStoreException e) {
            trustStore = null;
            LOG.error("Error while creating Keystore with cert " +trustStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_CREATION,e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            trustStore = null;
            LOG.error("JCE provider doesnt support certificate algorithm "+trustStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_NO_ALGORITHM,e.getMessage());
        }catch (CertificateException e) {
            trustStore = null;
            LOG.error("Error while creating keystore "+trustStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_BAD_CERTIFICATE,e.getMessage());
        }catch (FileNotFoundException e) {
            trustStore = null;
            LOG.error("File not found "+trustStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_NOT_FOUND,e.getMessage());
        }catch (IOException e) {
            trustStore = null;
            LOG.error("IO Exception while reading keystore file "+trustStoreLocation, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_CANNOT_READ,e.getMessage());
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

}
