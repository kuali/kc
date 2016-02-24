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
