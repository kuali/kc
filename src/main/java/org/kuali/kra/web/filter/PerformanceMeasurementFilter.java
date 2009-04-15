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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * <httpSample t="4031" lt="843" ts="1211383064375" s="true" lb="Load Portal Page" rc="200" rm="OK" tn="20 Users, 5 Iterations 1-1" dt="text" by="38007" ng="20" na="20">
 */
public class PerformanceMeasurementFilter implements Filter {
    private FilterConfig filterConfig;
    
    private Calendar _performanceLogCalendar;
    private DateFormat dateFormatter;
    
    public void destroy() {
        setPerformanceLogCalendar(null);
        dateFormatter = null;
        filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final long startTime = System.currentTimeMillis();
        PerformanceFilterResponse filterResponse = new PerformanceFilterResponse((HttpServletResponse) response); 
        chain.doFilter(request, filterResponse);        
        try {
            processResponse(request, filterResponse, startTime);
        } catch(Throwable t) {
            Logger logger = Logger.getLogger(PerformanceMeasurementFilter.class);
            logger.error(t.getMessage(), t);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        
        setPerformanceLogCalendar(getDateOnlyCalendar());
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    private String getPerformanceLogFileName() {
        Calendar todayCalendar = getDateOnlyCalendar();
        if(isNewFileNeeded(todayCalendar)) {            
            setPerformanceLogCalendar(todayCalendar);
        }
        
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
            Logger logger = Logger.getLogger(PerformanceMeasurementFilter.class);
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
