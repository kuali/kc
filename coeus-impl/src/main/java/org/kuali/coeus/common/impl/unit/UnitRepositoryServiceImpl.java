package org.kuali.coeus.common.impl.unit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.api.unit.UnitRepositoryService;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("unitRepositoryService")
public class UnitRepositoryServiceImpl implements UnitRepositoryService {

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Override
    public UnitContract findUnitByUnitNumber(String unitNumber) {
        if (StringUtils.isBlank(unitNumber)) {
            throw new IllegalArgumentException("unitNumber in blank");
        }

        return unitService.getUnit(unitNumber);
    }

    @Override
    public UnitContract findTopUnit() {
        return unitService.getTopUnit();
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
}
