/*
 * Copyright 2006 The Kuali Foundation.
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
package org.kuali.kra.rice.shim;

import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This class represents an exception that is thrown when a given user is not authorized to initiate a
 * certain document type.
 */
public class DocumentInitiationAuthorizationException extends RuntimeException {
    private static final long serialVersionUID = -3874239711783179351L;
    
    private String errorMessageKey;
    private String[] messageParameters;

    public DocumentInitiationAuthorizationException(String errorMessageKey, String[] messageParameters) {
       this.errorMessageKey = errorMessageKey;
       this.messageParameters = messageParameters;
    }
    
    public DocumentInitiationAuthorizationException(String[] messageParameters) {
        this.errorMessageKey = RiceKeyConstants.AUTHORIZATION_ERROR_DOCUMENT_WORKGROUP;
        this.messageParameters = messageParameters;
     }

    /**
     * Gets the errorMessageKey attribute. 
     * @return Returns the errorMessageKey.
     */
    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    /**
     * Sets the errorMessageKey attribute value.
     * @param errorMessageKey The errorMessageKey to set.
     */
    public void setErrorMessageKey(String errorMessageKey) {
        this.errorMessageKey = errorMessageKey;
    }

    /**
     * Gets the messageParameters attribute. 
     * @return Returns the messageParameters.
     */
    public String[] getMessageParameters() {
        return messageParameters;
    }

    /**
     * Sets the messageParameters attribute value.
     * @param messageParameters The messageParameters to set.
     */
    public void setMessageParameters(String[] messageParameters) {
        this.messageParameters = messageParameters;
    }
}
