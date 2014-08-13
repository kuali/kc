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

    public static final String AMENDMENT_MERGED = "400";

    public static final String RENEWAL_MERGED = "401";
    
    public static final String CONTINUATION_IN_PROGRESS = "207";

    public static final String CONTINUATION_MERGED = "402";
    
}
