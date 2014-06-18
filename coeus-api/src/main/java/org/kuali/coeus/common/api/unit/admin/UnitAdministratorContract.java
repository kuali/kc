package org.kuali.coeus.common.api.unit.admin;

public interface UnitAdministratorContract {

    String getPersonId();

    String getUnitNumber();

    UnitAdministratorTypeContract getUnitAdministratorType();
}
