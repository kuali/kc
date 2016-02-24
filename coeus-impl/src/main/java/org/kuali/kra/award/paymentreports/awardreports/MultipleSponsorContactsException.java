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
package org.kuali.kra.award.paymentreports.awardreports;

/**
 * 
 * This class throws a MultipleSponsorContactsException in case more than one sponsor contacts is present 
 */
public class MultipleSponsorContactsException extends RuntimeException {


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
