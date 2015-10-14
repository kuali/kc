package org.kuali.coeus.sys.impl.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class SSLVerficationBypassHttpRequestFactory extends SimpleClientHttpRequestFactory {
	
	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
		if (connection instanceof HttpsURLConnection) {
			((HttpsURLConnection) connection).setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			((HttpsURLConnection) connection).setSSLSocketFactory(trustSelfSignedSSLContext().getSocketFactory());
		}
		super.prepareConnection(connection, httpMethod);
	}
	
	final private SSLContext trustSelfSignedSSLContext() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLContext.setDefault(ctx);
            return ctx;
		} catch (NoSuchAlgorithmException|KeyManagementException e) {
			throw new RuntimeException(e);
		}
	}
}
