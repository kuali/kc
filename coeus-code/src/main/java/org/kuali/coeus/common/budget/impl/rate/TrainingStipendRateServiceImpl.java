package org.kuali.coeus.common.budget.impl.rate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.coeus.common.budget.api.rate.TrainingStipendRateContract;
import org.kuali.coeus.common.budget.api.rate.TrainingStipendRateService;
import org.kuali.coeus.common.budget.framework.rate.TrainingStipendRate;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("trainingStipendRateService")
public class TrainingStipendRateServiceImpl implements TrainingStipendRateService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public TrainingStipendRateContract findClosestMatchTrainingStipendRate(Date effectiveDate, String careerLevel, int experienceLevel) {
        if (effectiveDate == null) {
            throw new IllegalArgumentException("effectiveDate is null");
        }

        if (StringUtils.isBlank(careerLevel)) {
            throw new IllegalArgumentException("careerLevel is null");
        }

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        final Collection<TrainingStipendRate> rates = CollectionUtils.emptyIfNull(businessObjectService.findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false));
        for (TrainingStipendRate rate : rates) {
            if (rate.getEffectiveDate() != null) {
                final LocalDate limit = LocalDate.fromDateFields(effectiveDate);
                final LocalDate rateDate = LocalDate.fromDateFields(rate.getEffectiveDate());
                if (rateDate.isBefore(limit) || rateDate.isEqual(limit)) {
                    return rate;
                }
            }
        }
        return null;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
