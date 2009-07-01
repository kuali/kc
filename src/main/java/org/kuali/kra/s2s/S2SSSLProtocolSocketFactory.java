package org.kuali.kra.s2s;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.log4j.Logger;
import org.kuali.kra.s2s.util.PropertyFileReader;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * This class is used to create socket,method createSocket(String host, int port, InetAddress localAddress, int localPort,
 * HttpConnectionParams params) is implemented which loads the key store and trust store
 */
public class S2SSSLProtocolSocketFactory implements ProtocolSocketFactory {

    private static final Logger LOG = Logger.getLogger(S2SSSLProtocolSocketFactory.class);
    private static final String KEYSTORE_LOCATION = "keyStoreLocation";
    private static final String KEYSTORE_PASSWORD = "keyStorePassword";
    private static final String TRUSTSTORE_LOCATION = "trustStoreLocation";
    private static final String TRUSTSTORE_PASSWORD = "trustStorePassword";
    private SSLContext sslcontext = null;
    private String alias = null;
    private boolean mulitCampusEnabled = false;

    /**
     * 
     * Constructs a S2SSSLProtocolSocketFactory.java and initialize alias and mulitCampusEnabled
     * 
     * @param dunsNumber
     * @param mulitCampusEnabled
     */
    public S2SSSLProtocolSocketFactory(String dunsNumber, boolean mulitCampusEnabled) {
        super();
        this.alias = dunsNumber;
        this.mulitCampusEnabled = mulitCampusEnabled;
    }

    /**
     * 
     * Constructs a S2SSSLProtocolSocketFactory.java.
     */
    public S2SSSLProtocolSocketFactory() {
        super();
    }

    /**
     * 
     * This method is to create default SSLContext
     * 
     * @return context
     */
    private static SSLContext createS2SSSLContext() {
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            return context;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new HttpClientError(e.toString());
        }
    }

    /**
     * 
     * This method to get SSLContext
     * 
     * @return sslcontext
     */
    private SSLContext getSSLContext() {
        if (this.sslcontext == null) {
            this.sslcontext = createS2SSSLContext();
        }
        return this.sslcontext;
    }

    /**
     * Gets a new socket connection to the given host. 
     * 
     * @param host - the host name/IP
     * @param port - the port on the host 
     * @return Socket a new socket 
     * 
     * @throws IOException - if an I/O error occurs while creating the socket 
     * @throws UnknownHostException - if the IP address of the host cannot be determined
     */
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port);
    }

    /**
     * Gets a new socket connection to the given host. 
     * 
     * @param host - the host name/IP
     * @param port - the port on the host
     * @param localAddress - the local host name/IP to bind the socket to
     * @param localPort - the port on the local machine  
     * @return Socket a new socket 
     * 
     * @throws IOException - if an I/O error occurs while creating the socket 
     * @throws UnknownHostException - if the IP address of the host cannot be determined
     */
    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort) throws IOException,
            UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port, localAddress, localPort);
    }

    /**
     * Gets a new socket connection to the given host. 
     * 
     * @param host - the host name/IP
     * @param port - the port on the host
     * @param localAddress - the local host name/IP to bind the socket to
     * @param localPort - the port on the local machine
     * @param params HTTP connection parameters  
     * @return Socket a new socket 
     * 
     * @throws IOException - if an I/O error occurs while creating the socket 
     * @throws UnknownHostException - if the IP address of the host cannot be determined
     */
    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params)
            throws IOException, UnknownHostException, ConnectTimeoutException {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, KEYSTORE_LOCATION)),
                    PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, KEYSTORE_PASSWORD).toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            if (alias != null && mulitCampusEnabled) {
                KeyStore keyStoreAlias = KeyStore.getInstance("JKS");
                Certificate certificate = keyStore.getCertificate(alias);
                Key key = keyStore.getKey(alias, PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, KEYSTORE_PASSWORD)
                        .toCharArray());
                Certificate[] certificates = { certificate };
                keyStoreAlias.load(null, null);
                keyStoreAlias.setKeyEntry(alias, key, PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, KEYSTORE_PASSWORD)
                        .toCharArray(), certificates);
                keyManagerFactory.init(keyStoreAlias, PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, KEYSTORE_PASSWORD)
                        .toCharArray());
            }
            else {
                keyManagerFactory.init(keyStore, PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, KEYSTORE_PASSWORD)
                        .toCharArray());
            }
            KeyStore trustStore = KeyStore.getInstance("JKS");
            trustStore.load(new FileInputStream(PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, TRUSTSTORE_LOCATION)),
                    PropertyFileReader.getProperty(S2SConstants.S2S_PROPERTY, TRUSTSTORE_PASSWORD).toCharArray());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            Socket socket = sslContext.getSocketFactory().createSocket(host, port);
            return socket;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}
