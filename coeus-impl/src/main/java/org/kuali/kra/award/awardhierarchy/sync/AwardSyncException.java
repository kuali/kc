/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.awardhierarchy.sync;

/**
 * Exception helpers throw to indicate a warning or exception. Warnings should be reported, but ignored otherwise.
 */
public class AwardSyncException extends RuntimeException {
    private static final long serialVersionUID = -5525034768210252309L;

    private final boolean success;
    private final String statusMessage;
    
    public AwardSyncException(String statusMessage, boolean success) {
        this.statusMessage = statusMessage;
        this.success = success;
    }
    
    public boolean isSuccess() {
        return success;
    } 

    public String getStatusMessage() {
        return statusMessage;
    }
}
