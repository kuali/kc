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

    public static final String INSTITUTIONAL_PROPOSAL_KEY = "institutionalProposal";
}
