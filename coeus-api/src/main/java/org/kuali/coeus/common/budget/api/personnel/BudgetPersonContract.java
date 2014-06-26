package org.kuali.coeus.common.budget.api.personnel;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;

public interface BudgetPersonContract extends IdentifiableBudget, HierarchicalProposal {

    Date getEffectiveDate();

    Boolean getNonEmployeeFlag();

    String getPersonId();

    Integer getRolodexId();

    String getTbnId();

    ScaleTwoDecimal getCalculationBase();

    String getPersonName();

    Integer getPersonSequenceNumber();

    Date getSalaryAnniversaryDate();

    AppointmentTypeContract getAppointmentType();

    JobCodeContract getJobCodeRef();
}
