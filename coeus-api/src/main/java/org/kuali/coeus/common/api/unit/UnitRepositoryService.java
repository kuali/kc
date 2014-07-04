package org.kuali.coeus.common.api.unit;

public interface UnitRepositoryService {

    /**
     * Finds a unit by unit number.  The unit number cannot be blank.
     * @param unitNumber the unit number.  Cannot be blank.
     * @return the unit or null if not found.
     * @throws java.lang.IllegalArgumentException if the unitNumber is blank
     */
    UnitContract findUnitByUnitNumber(String unitNumber);

    /**
     * Finds the top unit in the hierarchy.
     * @return the unit or null if not found.
     */
    UnitContract findTopUnit();
}
