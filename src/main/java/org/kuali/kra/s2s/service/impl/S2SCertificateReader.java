/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.S2SUtilService;

public class S2SCertificateReader {
    private static final String KEYSTORE_LOCATION = "s2s.keystore.location";
    private static final String KEYSTORE_PASSWORD = "s2s.keystore.password";
    private static final String TRUSTSTORE_LOCATION = "s2s.truststore.location";
    private static final String TRUSTSTORE_PASSWORD = "s2s.truststore.password";
    private static final String JKS_TYPE = "JKS";
    
    private static KeyStore keyStore = null;
    private static KeyStore trustStore = null;
    
    private static final Log LOG = LogFactory.getLog(S2SCertificateReader.class);
    public static KeyStore getKeyStore() throws S2SException{
        if(keyStore!=null) return keyStore;
        try {
            keyStore = KeyStore.getInstance(JKS_TYPE);
            keyStore.load(new FileInputStream(getS2SUtilService().getProperty(KEYSTORE_LOCATION)),
                    getS2SUtilService().getProperty(KEYSTORE_PASSWORD).toCharArray());
        }catch (KeyStoreException e) {
            keyStore = null;
            LOG.error("Error while creating Keystore with cert " +KEYSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_CREATION,e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            keyStore = null;
            LOG.error("JCE provider doesnt support certificate algorithm "+KEYSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_NO_ALGORITHM,e.getMessage());
        }catch (CertificateException e) {
            keyStore = null;
            LOG.error("Error while creating keystore "+KEYSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_BAD_CERTIFICATE,e.getMessage());
        }catch (FileNotFoundException e) {
            keyStore = null;
            LOG.error("File not found "+KEYSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_NOT_FOUND,e.getMessage());
        }catch (IOException e) {
            keyStore = null;
            LOG.error("IO Exception while reading keystore file "+KEYSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_KEYSTORE_CANNOT_READ,e.getMessage());
        }
        return keyStore;
    }

    private static S2SUtilService getS2SUtilService() {
        return KraServiceLocator.getService(S2SUtilService.class);
    }

    public static KeyStore getTrustStore() throws S2SException{
        if(trustStore!=null)
            return trustStore;
        try {
            trustStore = KeyStore.getInstance(JKS_TYPE);
            trustStore.load(new FileInputStream(getS2SUtilService().getProperty(TRUSTSTORE_LOCATION)),
                    getS2SUtilService().getProperty(TRUSTSTORE_PASSWORD).toCharArray());
        }catch (KeyStoreException e) {
            trustStore = null;
            LOG.error("Error while creating Keystore with cert " +TRUSTSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_CREATION,e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            trustStore = null;
            LOG.error("JCE provider doesnt support certificate algorithm "+TRUSTSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_NO_ALGORITHM,e.getMessage());
        }catch (CertificateException e) {
            trustStore = null;
            LOG.error("Error while creating keystore "+TRUSTSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_BAD_CERTIFICATE,e.getMessage());
        }catch (FileNotFoundException e) {
            trustStore = null;
            LOG.error("File not found "+TRUSTSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_NOT_FOUND,e.getMessage());
        }catch (IOException e) {
            trustStore = null;
            LOG.error("IO Exception while reading keystore file "+TRUSTSTORE_LOCATION, e);
            throw new S2SException(KeyConstants.ERROR_S2S_TRUSTSTORE_CANNOT_READ,e.getMessage());
        }
        return trustStore;
    }

}
