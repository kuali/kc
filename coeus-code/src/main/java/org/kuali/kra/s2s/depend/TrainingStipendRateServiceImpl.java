package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.kuali.kra.budget.rates.TrainingStipendRate;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("trainingStipendRateService")
public class TrainingStipendRateServiceImpl implements TrainingStipendRateService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<TrainingStipendRate> findAllTrainingStipendRates() {
        return ListUtils.emptyIfNull((List<TrainingStipendRate>) businessObjectService.findAll(TrainingStipendRate.class));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
