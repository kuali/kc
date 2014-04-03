package org.kuali.coeus.budget.api.rate;


import java.util.Date;
import java.util.List;

public interface TrainingStipendRateService {

    /**
     * Retrieves all the {@link TrainingStipendRateContract}s.  Will return an empty. List if no items exist.
     * @return a list of {@link TrainingStipendRateContract}s or an empty list.
     */
    List<? extends TrainingStipendRateContract> findAllTrainingStipendRates();

    /**
     * This method finds the closest matching {@link TrainingStipendRateContract} based on the following rules.
     *
     * The Training Stipend Rate's effective date must be less than or equal to the passed in effective date.
     * The Training Stipend Rate's career level must be equal to the passed in career level.
     * The Training Stipend Rate's experience level must be equal to the passed in experience level.
     *
     * If at the end of this match, multiple Training Stipend Rates match then the one with the latest effective date
     * is returned.  This is considered the "closest match".
     *
     * Note: that this method on considered the date not the time for date comparisons.  Also note that a TrainingStipendRate
     * without an effective date will never be considered a match.
     *
     * @param effectiveDate the effective date of the training stipend rate must be less than or equal to.  Cannot be null.
     * @param careerLevel the career level of the training stipend rate. Cannot be blank.
     * @param experienceLevel the experience level of the training stipend rate
     * @return the closest matching {@link TrainingStipendRateContract} or null if there is no match
     * @throws java.lang.IllegalArgumentException if the effectiveDate is null, if the careerLevel is blank
     */
    TrainingStipendRateContract findClosestMatchTrainingStipendRate(Date effectiveDate, String careerLevel, int experienceLevel);
}
