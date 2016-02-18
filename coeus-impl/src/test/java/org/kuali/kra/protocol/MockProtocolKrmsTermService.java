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