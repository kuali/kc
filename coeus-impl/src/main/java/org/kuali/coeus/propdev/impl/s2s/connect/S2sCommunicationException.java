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
package org.kuali.coeus.propdev.impl.s2s.connect;

import org.kuali.kra.infrastructure.KeyConstants;

public class S2sCommunicationException extends RuntimeException {

    private String errorMessage;
    private String errorKey = KeyConstants.ERROR_S2S_UNKNOWN;
    private String tabErrorKey;
    private int messageType;
    private String[] params;

    /**
     * Creates new <code>KraException</code> without detail message.
     */

    public S2sCommunicationException() {
        super();
    }

    /**
     *
     * Creates new <code>KraException</code> with detail message.
     *
     * @param ex
     */
    public S2sCommunicationException(Exception ex) {
        super(ex);
        this.errorMessage = ex.getMessage();
    }

    /**
     *
     * Creates new <code>KraException</code> with detail message.
     *
     * @param ex
     */
    public S2sCommunicationException(String message, Exception ex) {
        super(message,ex);
    }
    /**
     * Constructs an <code>KraException</code> with the specified detail message.
     *
     * @param msg errorMessage the detail message.
     */

    public S2sCommunicationException(String msg) {
        super(msg);
        this.errorMessage = msg;
    }

    public S2sCommunicationException(String errorKey, String msg) {
        super(msg);
        this.errorMessage = msg;
        this.errorKey = errorKey;
    }
    public S2sCommunicationException(String errorKey, String msg, String... params) {
        super(msg);
        this.errorMessage = msg;
        this.errorKey = errorKey;
        this.params = params;
    }
    public S2sCommunicationException(String msg, int messageType) {
        super();
        this.errorMessage = msg;
        this.messageType = messageType;
    }

    /**
     * Get Error Message
     *
     * @return String Error Message
     */

    public String getMessage() {
        return errorMessage;
    }

    /**
     *
     * This method returns message as first element followed by all params
     * @return
     */
    public String[] getMessageWithParams() {
        String[] messageWithParams = new String[getParams().length+1];
        messageWithParams[0]=errorMessage;
        for (int i = 1; i < messageWithParams.length; i++) {
            messageWithParams[i]=params[i-1];
        }
        return messageWithParams;
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

    /**
     * Gets the errorKey attribute.
     * @return Returns the errorKey.
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Sets the errorKey attribute value.
     * @param errorKey The errorKey to set.
     */
    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    /**
     * Gets the params attribute.
     * @return Returns the params.
     */
    public String[] getParams() {
        return params;
    }

    /**
     * Sets the params attribute value.
     * @param params The params to set.
     */
    public void setParams(String[] params) {
        this.params = params;
    }

    /**
     * Gets the tabErrorKey attribute.
     * @return Returns the tabErrorKey.
     */
    public String getTabErrorKey() {
        return tabErrorKey;
    }

    /**
     * Sets the tabErrorKey attribute value.
     * @param tabErrorKey The tabErrorKey to set.
     */
    public void setTabErrorKey(String tabErrorKey) {
        this.tabErrorKey = tabErrorKey;
    }
}
