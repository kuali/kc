package org.kuali.coeus.propdev.impl.s2s;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.S2sApplicationContract;
import org.kuali.coeus.propdev.api.s2s.S2sApplicationService;
import org.kuali.coeus.propdev.impl.s2s.S2sApplication;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("s2sApplicationService")
public class S2sApplicationServiceImpl implements S2sApplicationService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public S2sApplicationContract findS2sApplicationByProposalNumber(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)){
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        return dataObjectService.find(S2sApplication.class, proposalNumber);
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
