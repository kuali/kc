package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;


public interface TrainingStipendRateContract extends IdentifiableNumeric, Describable {

    String getCareerLevel();
    int getExperienceLevel();
    ScaleTwoDecimal getStipendRate();
    Date getEffectiveDate();

}
