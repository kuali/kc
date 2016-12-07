package org.kuali.kra.lookup.keyvalue;


import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("nsfCodeYearConstrainedFinder")
public class NsfCodeYearConstrainedFinder extends UifKeyValuesFinderBase {

    private static final String YEAR = "year";
    private static final String NSF_CODE_YEAR = "NSF_CODE_YEAR";

    @Autowired
    @Qualifier("businessObjectService")
    private transient BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("parameterService")
    private transient ParameterService parameterService;

    @Override
    public List<KeyValue> getKeyValues() {
        return Stream.concat(Stream.of(new ConcreteKeyValue("", "select")),getBusinessObjectService()
                .findMatching(NsfCode.class, Collections.singletonMap(YEAR, getNsfCodeYear())).stream()
                .map(nsfCode -> new ConcreteKeyValue(nsfCode.getNsfSequenceNumber().toString(), nsfCode.getDescription())))
                .collect(Collectors.toList());
    }

    private Integer getNsfCodeYear() {
        return Integer.valueOf(getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_GEN, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, NSF_CODE_YEAR));
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }

        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }

        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
