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
package org.kuali.kra.coi.notification;

/**
 * 
 * This class defines the replacement parameters available for the COI module
 */
public class CoiReplacementParameters {

    public static final String DOCUMENT_NUMBER = "{DOCUMENT_NUMBER}";
    public static final String SEQUENCE_NUMBER = "{SEQUENCE_NUMBER}";
    public static final String DISCLOSURE_ID = "{DISCLOSURE_ID}";
    public static final String DISCLOSURE_TYPE = "{DISCLOSURE_TYPE}";
    public static final String DISCLOSURE_NUMBER = "{DISCLOSURE_NUMBER}";
    public static final String DISCLOSURE_REPORTER = "{DISCLOSURE_REPORTER}";
    public static final String DISCLOSURE_STATUS = "{DISCLOSURE_STATUS}";
    
    public static final String[] REPLACEMENT_PARAMETERS = new String[] { DOCUMENT_NUMBER,
                                                                         SEQUENCE_NUMBER,
                                                                         DISCLOSURE_ID,
                                                                         DISCLOSURE_TYPE,
                                                                         DISCLOSURE_NUMBER,
                                                                         DISCLOSURE_REPORTER,
                                                                         DISCLOSURE_STATUS,
                                                                       };
}
