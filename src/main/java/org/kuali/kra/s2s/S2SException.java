/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s;

/**
 * This class is used to handle general Exceptions
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class S2SException extends java.lang.Exception {

    private String errorMessage;
    private int errorID;
    private int messageType;

    /**
     * Creates new <code>KraException</code> without detail message.
     */

    public S2SException() {
        super();
    }

    /**
     * 
     * Creates new <code>KraException</code> with detail message.
     * 
     * @param ex
     */
    public S2SException(Exception ex) {
        super(ex);
    }

    /**
     * Constructs an <code>KraException</code> with the specified detail message.
     * 
     * @param msg errorMessage the detail message.
     */

    public S2SException(String msg) {
        super(msg);
        this.errorMessage = msg;
    }

    public S2SException(String msg, int messageType) {
        super();
        this.errorMessage = msg;
        this.messageType = messageType;
    }

    /**
     * Get Error Message
     * 
     * @returns String Error Message
     */

    public String getMessage() {
        return errorMessage;
    }

    /**
     * Set Error Message
     * 
     * @param msg String Error Message
     */
    public void setMessage(String msg) {
        this.errorMessage = msg;
    }

    /**
     * This Method is used to get the User Assigned Message.
     * 
     * @return String user defined erronous message
     */
    public String getUserMessage() {
        return errorMessage;
    }

    /**
     * This method is used to get the Error ID.
     * 
     * @return int Error ID.
     */
    public int getErrorId() {
        int index = 0;
        if (errorMessage != null) {
            index = errorMessage.indexOf("exceptionCode");
            if (index != -1) {
                try {
                    errorID = Integer.parseInt(errorMessage
                            .substring((index + "exceptionCode".length() + 1), errorMessage.length()));
                }
                catch (java.lang.NumberFormatException ex) {
                }
            }
        }
        return errorID;
    }


    /**
     * Getter for property messageType.
     * 
     * @return Value of property messageType.
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * Setter for property messageType.
     * 
     * @param messageType New value of property errorType.
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

}
