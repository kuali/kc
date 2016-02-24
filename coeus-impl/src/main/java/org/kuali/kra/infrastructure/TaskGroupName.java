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
package org.kuali.kra.infrastructure;

/**
 * Tasks are placed into groups corresponding to the object they
 * relate to.  For example, the "saveProposal" task corresponds to
 * the "proposal" group.  This file contains the constants for the
 * names of the Task Groups.  The group names must correspond to the 
 * values in the SpringBeans.xml.
 */
public interface TaskGroupName {

    public static final String AWARD_BUDGET = "awardBudget";
    public static final String PROTOCOL = "protocol";
    public static final String IACUC_PROTOCOL = "iacucProtocol";
    public static final String COMMITTEE = "committee";
    public static final String AWARD = "award";
    public static final String TIME_AND_MONEY = "timeAndMoney";
    public static final String PROTOCOL_ONLINEREVIEW = "protocolOnlineReview";
    public static final String NEGOTIATION = "negotiation";
    public static final String COIDISCLOSURE = "coiDisclosure";
    public static final String SUBAWARD = "subAward";
    public static final String IACUC_PROTOCOL_ONLINEREVIEW = "iacucProtocolOnlineReview";
    public static final String IACUC_COMMITTEE = "iacucCommittee";

}
