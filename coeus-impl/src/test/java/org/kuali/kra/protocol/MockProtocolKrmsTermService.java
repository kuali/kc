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
package org.kuali.kra.protocol;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.SponsorType;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import java.util.*;

class MockProtocolKrmsTermService extends ProtocolJavaFunctionKrmsTermServiceBase {

    @Override
    public String getRenewalActionTypeCode() {
        return null;
    }

    @Override
    public String getProtocolPersonnelModuleTypeCode() {
        return null;
    }

    @Override
    public String getProtocolOrganizationModuleTypeCode() {
        return null;
    }

    @Override
    public String getNotifySubmissionTypeCode() {
        return null;
    }

    @Override
    protected Sponsor getSponsorByFundingSourceNumber(ProtocolFundingSourceBase fundingSource) {
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorTypeCode("3");
        SponsorType sponsorType = new SponsorType();
        sponsorType.setDescription("3");
        sponsor.setSponsorType(sponsorType);
        return sponsor;
    }

    @Override
    protected ProtocolBase getActiveProtocol(String protocolNumber){
        final MockProtocol protocol = new MockProtocol();
        if(protocolNumber.equalsIgnoreCase("2")) {
            Calendar cal = Calendar.getInstance();
            final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
            protocol.setLastApprovalDate(july2014);
        }
        return protocol;
    }

    private java.sql.Date getDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month);
        cal.set(cal.DATE, day);
        cal.set(cal.HOUR_OF_DAY, 0);
        cal.set(cal.MINUTE, 0);
        cal.set(cal.SECOND, 0);
        cal.set(cal.MILLISECOND, 0);
        return new java.sql.Date(cal.getTime().getTime());
    }
}