package org.kuali.kra.s2s.depend;

import org.kuali.kra.budget.rates.TrainingStipendRate;

import java.util.List;

public interface TrainingStipendRateService {

    /**
     * Retrieves all the {@link TrainingStipendRate}s.  Will return an empty
     * List if no items exist.
     * @return a list of {@link TrainingStipendRate}s or an empty list.
     */
    List<TrainingStipendRate> findAllTrainingStipendRates();
}
