package org.kuali.coeus.common.budget.api.personnel;

import org.kuali.coeus.common.budget.api.personnel.JobCodeContract;
import org.kuali.coeus.sys.api.model.Identifiable;

public interface TbnPersonContract extends Identifiable {
    String getPersonName();
    JobCodeContract getJobCodeReference();
}
