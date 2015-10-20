package org.kuali.coeus.sys.impl.rest;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private Log LOG = LogFactory.getLog(LoggingRequestInterceptor.class);
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		traceRequest(request, body);
		
		ClientHttpResponse response = execution.execute(request, body);
		
		traceResponse(request, response);
		
		return response;
		
	}
	
	protected void traceRequest(HttpRequest request, byte[] body) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("URI : " + request.getURI());
			LOG.debug("Method : " + request.getMethod());
			LOG.debug("Headers : " + request.getHeaders().toString());
			LOG.debug("Body : " + new String(body));
		}
	}
	
	protected void traceResponse(HttpRequest request, ClientHttpResponse response) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Response status code : " + response.getStatusCode());
				LOG.debug("Response status text : " + response.getStatusText());
			}
		} catch (IOException e) {
			LOG.warn("Error performing debug logging of response", e);
		}
	}

}
