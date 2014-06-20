package org.kuali.coeus.common.api.unit;

import org.kuali.coeus.common.api.unit.admin.UnitAdministratorContract;
import org.kuali.coeus.sys.api.model.Inactivatable;

import java.util.List;

public interface UnitContract extends Inactivatable {

    String getUnitNumber();

    String getOrganizationId();

    String getUnitName();

    UnitContract getParentUnit();

    List<? extends UnitAdministratorContract> getUnitAdministrators();
}
