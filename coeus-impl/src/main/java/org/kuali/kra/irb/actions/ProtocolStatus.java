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
package org.kuali.kra.irb.actions;

import org.kuali.kra.protocol.actions.ProtocolStatusBase;

public class ProtocolStatus extends ProtocolStatusBase {


    private static final long serialVersionUID = -5947289991209122441L;

    public static final String IN_PROGRESS = "100";

    public static final String SUBMITTED_TO_IRB = "101";

    public static final String SPECIFIC_MINOR_REVISIONS_REQUIRED = "102";

    public static final String DEFERRED = "103";

    public static final String SUBSTANTIVE_REVISIONS_REQUIRED = "104";

    public static final String AMENDMENT_IN_PROGRESS = "105";

    public static final String RENEWAL_IN_PROGRESS = "106";
    
    public static final String RETURN_TO_PI = "107";

    public static final String ACTIVE_OPEN_TO_ENROLLMENT = "200";

    public static final String ACTIVE_CLOSED_TO_ENROLLMENT = "201";

    public static final String ACTIVE_DATA_ANALYSIS_ONLY = "202";

    public static final String EXEMPT = "203";

    public static final String CLOSED_ADMINISTRATIVELY = "300";

    public static final String CLOSED_BY_INVESTIGATOR = "301";

    public static final String SUSPENDED_BY_PI = "302";

    public static final String DELETED = "303";

    public static final String WITHDRAWN = "304";

    public static final String EXPIRED = "305";

    public static final String DISAPPROVED = "306";

    public static final String TERMINATED_BY_IRB = "307";

    public static final String SUSPENDED_BY_IRB = "308";

    public static final String IRB_REVIEW_NOT_REQUIRED = "310";

    public static final String SUSPENDED_BY_DSMB = "311";

    public static final String AMENDMENT_MERGED = "400";

    public static final String RENEWAL_MERGED = "401";
    
    public static final String RECALLED_IN_ROUTING = "402";
}
