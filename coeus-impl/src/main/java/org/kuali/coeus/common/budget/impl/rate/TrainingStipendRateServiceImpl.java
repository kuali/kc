/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
