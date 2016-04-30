package org.kuali.kra.negotiations.keyvalue;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.negotiations.bo.NegotiationActivityType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.List;
import java.util.stream.Collectors;

public class NegotiationActivityTypeValuesFinder extends UifKeyValuesFinderBase {

    private static final String DESCRIPTION = "description";
    private static final boolean SORT_ASCENDING = true;

    private transient KeyValuesService keyValuesService;

    @Override
    public List<KeyValue> getKeyValues() {
        return getKeyValuesService().findAllOrderBy(NegotiationActivityType.class, DESCRIPTION, SORT_ASCENDING).stream()
                .map(type -> new ConcreteKeyValue(type.getId().toString(), type.getDescription()))
                .collect(Collectors.toList());
    }

    public KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService = KcServiceLocator.getService(KeyValuesService.class);
        }

        return keyValuesService;
    }

    public void setKeyValuesService(KeyValuesService keyValuesService) {
        this.keyValuesService = keyValuesService;
    }
}
