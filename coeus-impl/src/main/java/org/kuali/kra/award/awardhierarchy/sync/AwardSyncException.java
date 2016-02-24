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
