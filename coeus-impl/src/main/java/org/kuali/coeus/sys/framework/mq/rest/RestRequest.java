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
package org.kuali.coeus.sys.framework.mq.rest;

import java.io.Serializable;
import java.util.*;

/**
 * Encapsulates a REST request.  At the very least a destination and a method must be provided.
 * When setting the params, please be sure to use Serializable types.
 */
public final class RestRequest implements Serializable {
    private HttpMethod method;
    private String destination;
    private Map<String, List<String>> params;
    private String body;
    private Map<String, List<String>> headers;

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Map<String, List<String>> getParams() {
        return params;
    }

    public void setParams(Map<String, List<String>> params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestRequest)) return false;

        RestRequest that = (RestRequest) o;

        if (method != that.method) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return !(headers != null ? !headers.equals(that.headers) : that.headers != null);

    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestRequest{" +
                "method=" + method +
                ", destination='" + destination + '\'' +
                ", params=" + params +
                ", body='" + body + '\'' +
                ", headers=" + headers +
                '}';
    }
}
