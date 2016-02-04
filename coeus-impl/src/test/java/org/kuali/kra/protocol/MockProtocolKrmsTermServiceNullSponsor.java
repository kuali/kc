package org.kuali.kra.protocol;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;

public class MockProtocolKrmsTermServiceNullSponsor extends MockProtocolKrmsTermService {

    @Override
    protected Sponsor getSponsorByFundingSourceNumber(ProtocolFundingSourceBase fundingSource) {
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorTypeCode(null);
        sponsor.setSponsorType(null);
        return sponsor;
    }
}
