package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("argValueLookupService")
public class ArgValueLookupServiceImpl implements ArgValueLookupService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<ArgValueLookup> findAllArgValueLookups() {
        return ListUtils.emptyIfNull((List < ArgValueLookup >) businessObjectService.findAll(ArgValueLookup.class));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
