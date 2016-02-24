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
package org.kuali.kra.coi;

/**
 * 
 * This class holds the action types for notifications and possible drools use.  This
 * is intended as a placeholder until a more complete solution is implemented
 */
public class CoiActionType {

    //These numbers are arbitrary, just don't duplicate
    public static final String ASSIGN_REVIEWER = "205";
    public static final String CERTIFIED_EVENT = "214";
    public static final String APPROVED_EVENT = "204";
    public static final String DISAPPROVED_EVENT = "304";
    public static final String REVIEW_COMPLETE_EVENT = "805";
    
    public static final String FE_CREATED_EVENT = "845";
    public static final String FE_MODIFIED_EVENT = "846";
}
