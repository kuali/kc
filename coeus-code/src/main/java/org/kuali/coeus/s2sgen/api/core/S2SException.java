/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.coeus.s2sgen.api.core;

/**
 * This class is for exceptions that occur in the s2s subsystem.
 */
public class S2SException extends RuntimeException {

    public static final String ERROR_S2S_UNKNOWN = "error.s2s.unknown";

    private String errorMessage;
    private String errorKey = ERROR_S2S_UNKNOWN;
    private int messageType;
    private String[] params;
    private String tabErrorKey;

    public S2SException() {
        super();
    }

    public S2SException(Exception ex) {
        super(ex);
        this.errorMessage = ex.getMessage();
    }

    public S2SException(String message,Exception ex) {
        super(message,ex);
    }

    public S2SException(String msg) {
        super(msg);
        this.errorMessage = msg;
    }

    public S2SException(String errorKey,String msg) {
        super(msg);
        this.errorMessage = msg;
        this.errorKey = errorKey;
    }
    public S2SException(String errorKey,String msg,String... params) {
        super(msg);
        this.errorMessage = msg;
        this.errorKey = errorKey;
        this.params = params;
    }
    public S2SException(String msg, int messageType) {
        super(msg);
        this.errorMessage = msg;
        this.messageType = messageType;
    }

    public String getMessage() {
        return errorMessage;
    }

    /**
     * This method returns message as first element followed by all params
     */
    public String[] getMessageWithParams() {
        String[] messageWithParams = new String[getParams().length+1];
        messageWithParams[0]=errorMessage;
        for (int i = 1; i < messageWithParams.length; i++) {
            messageWithParams[i]=params[i-1];
        }
        return messageWithParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String getTabErrorKey() {
        return tabErrorKey;
    }

    public void setTabErrorKey(String tabErrorKey) {
        this.tabErrorKey = tabErrorKey;
    }
}
