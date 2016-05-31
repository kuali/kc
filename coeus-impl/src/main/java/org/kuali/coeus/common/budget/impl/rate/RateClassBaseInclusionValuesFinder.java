package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.criteria.OrderByField;
import org.kuali.rice.core.api.criteria.OrderDirection;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

public class RateClassBaseInclusionValuesFinder extends UifKeyValuesFinderBase {

    public static final String DESCRIPTION = "description";
    public static final String BASE_COST = "Base Cost";
    public static final String BASE_COST_CODE = "0";
    private transient DataObjectService dataObjectService;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = getRateClasses().stream().map(rateClass -> new ConcreteKeyValue(rateClass.getCode(), rateClass.getDescription())
                                    ).collect(Collectors.toList());

        keyValues.add(0, new ConcreteKeyValue(BASE_COST_CODE, BASE_COST));
        return keyValues;

    }

    private List<RateClass> getRateClasses(){
        List<OrderByField> orderByFields = new ArrayList<>();
        orderByFields.add(OrderByField.Builder.create(DESCRIPTION, OrderDirection.ASCENDING).build());
        return getDataObjectService().findMatching(RateClass.class, QueryByCriteria.Builder.create().setOrderByFields(orderByFields).build()).getResults();
    }

    public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

}
