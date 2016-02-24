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
package org.kuali.coeus.sys.framework.controller.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <httpSample t="4031" lt="843" ts="1211383064375" s="true" lb="Load Portal Page" rc="200" rm="OK" tn="20 Users, 5 Iterations 1-1" dt="text" by="38007" ng="20" na="20">
 */
public class PerformanceMeasurementFilter implements Filter {
    private FilterConfig filterConfig;
    
    private Calendar _performanceLogCalendar;
    
    public void destroy() {
        setPerformanceLogCalendar(null);
        filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final long startTime = System.currentTimeMillis();
        PerformanceFilterResponse filterResponse = new PerformanceFilterResponse((HttpServletResponse) response);
        chain.doFilter(request, filterResponse);        
        try {
            processResponse(request, filterResponse, startTime);
        } catch(Throwable t) {
            Log logger = LogFactory.getLog(PerformanceMeasurementFilter.class);
            logger.error(t.getMessage(), t);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        
        setPerformanceLogCalendar(getDateOnlyCalendar());
        
    }
    
    private String getPerformanceLogFileName() {
        Calendar todayCalendar = getDateOnlyCalendar();
        if(isNewFileNeeded(todayCalendar)) {            
            setPerformanceLogCalendar(todayCalendar);
        }
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return new StringBuilder("Real-time Performance Log ").append(dateFormatter.format(getPerformanceLogCalendar().getTime())).append(".xml").toString();
    }

    private boolean isNewFileNeeded(Calendar todayCalendar) {
        return todayCalendar.get(Calendar.DATE) != getPerformanceLogCalendar().get(Calendar.DATE);
    }

    private Calendar getDateOnlyCalendar() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    
    private synchronized Calendar getPerformanceLogCalendar() {
        return _performanceLogCalendar;
    }
    
    private void logSample(HttpSample sample, String outputDirectory) {
        try {
            File file = new File(outputDirectory, getPerformanceLogFileName());
            if(!file.exists()) {
                createNewFile(file);
            }
            
            insertLine(file, sample);
                       
        } catch(Exception e) {
            Log logger = LogFactory.getLog(PerformanceMeasurementFilter.class);
            logger.warn(e.getMessage(), e);
        }
    }
    
    private void processResponse(ServletRequest request, PerformanceFilterResponse response, final long startTime) {
        final int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        final String outputDirectory = filterConfig.getServletContext().getInitParameter("org.kuali.kra.perftest.REPORT_DIRECTORY");
        final HttpSample httpSample = new HttpSample((HttpServletRequest) request, response, outputDirectory, startTime, elapsedTime);
        
        Thread t = new Thread(new Runnable() {
           public void run() {               
               logSample(httpSample, outputDirectory);
           }
        });
        
        t.start();
    }
    
    private synchronized void setPerformanceLogCalendar(Calendar performanceLogCalendar) {
        this._performanceLogCalendar = performanceLogCalendar;
    }
    
    private void createNewFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
        writer.write("<!-- Performance Log File: ");
        writer.write(file.getName());
        writer.write(" -->\n\n");
        writer.write("<httpSamples>");
        writer.write("\n</httpSamples>");
        writer.flush();
        writer.close();
    }
    
    private void insertLine(File file, HttpSample httpSample) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.skipBytes((int)(file.length() - "\n</httpSamples>".getBytes().length));
            randomAccessFile.write("\n".getBytes());
            randomAccessFile.write(httpSample.toXML().getBytes());
            randomAccessFile.write("\n".getBytes());
            randomAccessFile.write("</httpSamples>".getBytes());            
        } finally {
            if(randomAccessFile != null) { randomAccessFile.close(); }
        }
    }

    private static class PerformanceFilterResponse extends HttpServletResponseWrapper {

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

    private class HttpSample {
        private int responseContentLength;
        private String dataType;
        private String sampleName;
        private String returnMessage;
        private int returnCode;
        private String label;
        private boolean success;
        private long requestTimeStamp;
        private int latency;
        private int elapsedTime;
        
        public HttpSample(HttpServletRequest request, PerformanceFilterResponse response, String outputDirectory, long startTime, int elapsedTime) {
            init(request, response, startTime, elapsedTime);
        }        
        
        public int getResponseContentLength() {
            return responseContentLength;
        }

        public String getDataType() {
            return dataType;
        }

        public String getSampleName() {
            return sampleName;
        }

        public String getReturnMessage() {
            return returnMessage;
        }

        public int getReturnCode() {
            return returnCode;
        }

        public String getLabel() {
            return label;
        }

        public boolean isSuccess() {
            return success;
        }

        public long getRequestTimeStamp() {
            return requestTimeStamp;
        }

        public int getLatency() {
            return latency;
        }

        public int getElapsedTime() {
            return elapsedTime;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder("<httpSample");
            addAttribute(sb, "t", elapsedTime);
            addAttribute(sb, "lt", latency);
            addAttribute(sb, "ts" , requestTimeStamp);
            addAttribute(sb, "s", success);
            addAttribute(sb, "lb", label);
            addAttribute(sb, "rc", returnCode);
            addAttribute(sb, "rm", returnMessage);
            addAttribute(sb, "tn", sampleName);
            addAttribute(sb, "dt", dataType);
            addAttribute(sb, "by", responseContentLength);
            sb.append(" />");
            return sb.toString();
        }
        
        private void addAttribute(StringBuilder sb, String attributeName, Object attributeValue) {
            sb.append(" ");
            sb.append(attributeName);
            sb.append("=\"");
            sb.append(attributeValue);
            sb.append("\"");
        }
        
        @SuppressWarnings("unchecked")
        private String getRequestLabel(HttpServletRequest request) {
            StringBuilder sb = new StringBuilder();
            sb.append(request.getServletPath().substring(1));
            String methodToCall = request.getParameter("methodToCall");
            if(methodToCall != null) {
                addMethodToCall(sb, methodToCall);
            } else {
                Enumeration<String> nameEnum = request.getParameterNames();
                while(nameEnum.hasMoreElements()) {
                    String parmName = nameEnum.nextElement(); 
                    if(parmName.startsWith("methodToCall.")) {
                        addMethodToCall(sb, parmName.substring("methodToCall.".length()));
                        break;
                    }
                }
            }
            return sb.toString();
        }

        private void addMethodToCall(StringBuilder sb, String methodToCall) {
            sb.append(";methodToCall=");
            sb.append(methodToCall);
        }
        
        private void init(HttpServletRequest request, PerformanceFilterResponse response, long startTime, int elapsedTime) {
            responseContentLength = response.getContentLength();
            dataType = response.getContentType() != null ? response.getContentType().substring(0, response.getContentType().indexOf("/")) : "text";
            sampleName = "Real-time Performance Sample";
            returnCode = response.getStatusCode();
            returnMessage = response.getMessage();
            label = getRequestLabel(request);
            success = returnCode == 0 || returnCode == 200 || (returnCode > 200 && returnCode <= 399);
            
            requestTimeStamp = startTime;
            this.elapsedTime = elapsedTime;
            latency = elapsedTime;
        }
    }
}
