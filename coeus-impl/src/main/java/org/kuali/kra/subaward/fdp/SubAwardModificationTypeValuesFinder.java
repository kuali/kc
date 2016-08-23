package org.kuali.kra.subaward.fdp;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

public class SubAwardModificationTypeValuesFinder extends UifKeyValuesFinderBase {

    private BusinessObjectService businessObjectService;

    @Override
    public List<KeyValue> getKeyValues() {
        return Stream.concat(
        	Stream.of(new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue())),
        	getBusinessObjectService().findMatching(SubAwardModificationType.class, Collections.singletonMap("active", Boolean.TRUE)).stream()
        	.map(type -> new ConcreteKeyValue(type.getCode(), type.getDescription()))
        	.sorted())
        .collect(Collectors.toList());
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
