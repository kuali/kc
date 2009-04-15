/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.filter;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class PerformanceFilterResponse extends HttpServletResponseWrapper {

    private static final String CONTENT_LENGTH = "CONTENT-LENGTH";
    private static final int OK_STATUS = 200;
    private static final String OK_MSG = "OK";
    
    private int statusCode;
    private String message;
    private Map<String, String> headers;
    
    public PerformanceFilterResponse(HttpServletResponse response) {
        super(response);
        headers = new TreeMap<String, String>();
    }

    public int getStatusCode() {
        return statusCode == 0 ? OK_STATUS : statusCode;
    }

    public String getMessage() {
        if (message == null) {
            message = (statusCode == OK_STATUS || statusCode == 0) ? OK_MSG : "";
        }
        return message;
    }

    public int getContentLength() {
        try {
            return headers.containsKey(CONTENT_LENGTH) ? Integer.valueOf(headers.get(CONTENT_LENGTH)).intValue() : -1;
        } catch(Exception e) {
            return -1;
        }
    }
    
    @Override
    public void addHeader(String name, String value) {
        super.addHeader(name, value);
        headers.put(name, value);
    }

    @Override
    public void addIntHeader(String name, int value) {
        super.addIntHeader(name, value);
        headers.put(name, String.valueOf(value));
    }

    @Override
    public void setHeader(String name, String value) {
        super.setHeader(name, value);
        headers.put(name, value);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        super.sendError(sc, msg);
        statusCode = sc;
        message = msg;
    }

    @Override
    public void sendError(int sc) throws IOException {
        super.sendError(sc);
        statusCode = sc;
    }

    @Override
    public void setStatus(int sc, String sm) {
        super.setStatus(sc, sm);
        statusCode = sc;
        message = sm;
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
        statusCode = sc;
    }

    @Override
    public void setIntHeader(String name, int value) {
        super.setIntHeader(name, value);
        headers.put(name, String.valueOf(value));
    }
}
