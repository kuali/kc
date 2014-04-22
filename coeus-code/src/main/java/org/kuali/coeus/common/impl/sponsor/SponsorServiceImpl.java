package org.kuali.coeus.common.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("sponsorService")
public class SponsorServiceImpl implements SponsorService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public SponsorContract getSponsor(String sponsorCode) {
        if (StringUtils.isBlank(sponsorCode)) {
            throw new IllegalArgumentException("sponsorCode is blank");
        }

        return dataObjectService.find(Sponsor.class, sponsorCode);
    }

    @Override
    public String getSponsorName(String sponsorCode) {
        final SponsorContract sponsor = getSponsor(sponsorCode);
        return sponsor != null ? sponsor.getSponsorName() : null;
    }

    @Override
    public boolean isValidSponsor(SponsorContract sponsor) {
        return sponsor != null && sponsor.isActive();
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
