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
    private Map<String, Collection<String>> params;
    private String body;

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

    public Map<String, Collection<String>> getParams() {
        return params;
    }

    public void setParams(Map<String, Collection<String>> params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestRequest)) return false;

        RestRequest that = (RestRequest) o;

        if (method != that.method) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        return !(body != null ? !body.equals(that.body) : that.body != null);

    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestRequest{" +
                "method=" + method +
                ", destination='" + destination + '\'' +
                ", params=" + params +
                ", body='" + body + '\'' +
                '}';
    }
}
