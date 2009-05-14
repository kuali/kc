/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.awardreports;

/**
 * 
 * This class throws a MultipleSponsorContactsException in case more than one sponsor contacts is present 
 */
public class MultipleSponsorContactsException extends RuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -315196634642722965L;

    /**
     * 
     * Constructs a MultipleSponsorContactsException.java.
     * @param numberOfRecipients
     */
    public MultipleSponsorContactsException(int numberOfRecipients) {
        super("There are " + numberOfRecipients + " number of Sponsor Contacts. There should be only 1. ");
    }
}
