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
package org.kuali.kra.protocol.onlinereview.lookup;

public interface ProtocolOnlineReviewLookupConstants {

    public static class Property {
        public static final String REVIEWER_EMPLOYEE = "lookupReviewerPersonId";
        public static final String REVIEWER_NONEMPLOYEE = "lookupReviewerRolodexId";
        public static final String PROTOCOL_ID="protocolId";
        public static final String PROTOCOL_NUMBER="lookupProtocolNumber";
        public static final String SUBMISSION_ID="submissionIdFk";
        public static final String DATE_DUE="dateDue";
        public static final String DATE_REQUESTED="dateRequested";
    }
}
