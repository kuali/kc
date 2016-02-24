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
package org.kuali.coeus.common.committee.impl.notification;

/**
 * 
 * This class defines the replacement parameters available for the IRB coeus module
 */
public class CommitteeReplacementParameters {

    public static final String DOCUMENT_NUMBER = "{DOCUMENT_NUMBER}";
    public static final String SEQUENCE_NUMBER = "{SEQUENCE_NUMBER}";
    public static final String COMMITTEE_NAME = "{COMMITTEE_NAME}";
    public static final String ACTION_TAKEN = "{ACTION_TAKEN}";
    public static final String LAST_ACTION_DATE = "{LAST_ACTION_DATE}";
    public static final String OBJECT_INDEX = "{OBJECT_INDEX}";
    public static final String SCHEDULE_ID = "{SCHEDULE_ID}";
    
    public static final String[] REPLACEMENT_PARAMETERS = new String[] { DOCUMENT_NUMBER,
                                                                         SEQUENCE_NUMBER, 
                                                                         COMMITTEE_NAME,
                                                                         ACTION_TAKEN, 
                                                                         LAST_ACTION_DATE,
                                                                         OBJECT_INDEX,
                                                                         SCHEDULE_ID
                                                                       };
}
