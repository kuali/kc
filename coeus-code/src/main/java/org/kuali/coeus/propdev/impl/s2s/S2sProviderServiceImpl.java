package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.S2sProviderContract;
import org.kuali.coeus.propdev.api.s2s.S2sProviderService;
import org.kuali.coeus.propdev.impl.s2s.S2sProvider;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("s2sProviderService")
public class S2sProviderServiceImpl implements S2sProviderService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public S2sProviderContract findS2SProviderByCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("the code is blank");
        }

        return dataObjectService.find(S2sProvider.class, code);
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
