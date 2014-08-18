/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.coeus.common.permissions.impl;

public final class PermissionableKeys {

    private PermissionableKeys () {
        throw new UnsupportedOperationException("do not call");
    }

    public static final String PROPOSAL_KEY = "proposal";
    public static final String AWARD_KEY = "award";
    public static final String TIME_AND_MONEY_KEY = "timeandmoney";
    
    //these keys dont seem to be used now
    public static final String AWARD_BUDGET_KEY = "awardbudget";
    public static final String PROPOSAL_BUDGET_KEY = "proposalbudget";

    public static final String PROTOCOL_KEY = "protocol";
    public static final String IACUC_PROTOCOL_KEY = "iacuc";
    public static final String COMMITTEE_KEY = "committee";
    public static final String COMMITTEE_SCHEDULE_KEY="committeeSchedule";
    public static final String PROTOCOL_ONLINE_REVIEW_KEY="protocolOnlineReview";
    public static final String IACUC_PROTOCOL_ONLINE_REVIEW_KEY="iacucOnlineReview";

    public static final String NEGOTIATION_KEY = "negotiation";
    public static final String COI_DISCLOSURE_KEY = "coiDisclosure";

    public static final String SPONSOR_HIREARCHY_KEY = "sponsorhirearchy";

    public static final String SUBAWARD_KEY = "SubAwardDocument";
}
