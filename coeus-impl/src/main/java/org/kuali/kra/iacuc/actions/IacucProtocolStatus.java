/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.protocol.actions.ProtocolStatusBase;

public class IacucProtocolStatus extends ProtocolStatusBase { 
    

    private static final long serialVersionUID = 3741210958637661468L;

    public static final String IN_PROGRESS = "100";
    
    public static final String SUBMITTED_TO_IACUC = "101";
    
    public static final String RETURN_TO_PI = "102";
    
    public static final String TABLED = "103";
    
    public static final String MINOR_REVISIONS_REQUIRED = "104";
    
    public static final String WITHDRAWN = "105";
    
    public static final String MAJOR_REVISIONS_REQUIRED = "107";
    
    public static final String ROUTING_IN_PROGRESS = "108";
    
    public static final String ACTIVE = "200";
    
    public static final String ACTIVE_ON_HOLD = "201";
    
    public static final String ADMINISTRATIVELY_APPROVED = "202";
    
    public static final String ADMINISTRATIVELY_INCOMPLETE = "203";
    
    public static final String IACUC_REVIEW_NOT_REQUIRED = "300";
    
    public static final String DELETED = "301";
    
    public static final String ADMINISTRATIVELY_WITHDRAWN = "302";
    
    public static final String DISAPPROVED = "303";
    
    public static final String SUSPENDED = "304";
    
    public static final String DEACTIVATED = "305";
    
    public static final String ADMINISTRATIVELY_DEACTIVATED = "306";
    
    public static final String TERMINATED = "307";
    
    public static final String EXPIRED = "308";
    
    public static final String ABANDONED = "309";
    
    public static final String AMENDMENT_IN_PROGRESS = "205";
    
    public static final String RENEWAL_IN_PROGRESS = "206";

    public static final String FYI_IN_PROGRESS = "900";

    public static final String AMENDMENT_MERGED = "400";

    public static final String RENEWAL_MERGED = "401";

    public static final String FYI_MERGED = "901";
    
    public static final String CONTINUATION_IN_PROGRESS = "207";

    public static final String CONTINUATION_MERGED = "402";
    
}
