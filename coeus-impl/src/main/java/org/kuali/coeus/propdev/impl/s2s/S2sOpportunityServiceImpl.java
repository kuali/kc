package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.S2sOpportunityContract;
import org.kuali.coeus.propdev.api.s2s.S2sOpportunityService;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@Service("s2sOpportunityService")
public class S2sOpportunityServiceImpl implements S2sOpportunityService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public S2sOpportunityContract findS2SOpportunityByProposalNumber(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)){
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        return dataObjectService.find(S2sOpportunity.class, proposalNumber);
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
