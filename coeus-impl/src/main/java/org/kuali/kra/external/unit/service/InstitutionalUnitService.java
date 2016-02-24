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
package org.kuali.kra.external.unit.service;

import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.unit.UnitDTO;
import org.kuali.kra.infrastructure.Constants;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * This is the external service that is published to the service bus for
 * accessing Institutional Unit information stored in the Kuali Coeus system.
 * 
 * @author Kuali Coeus Development Team
 */
@WebService(name = "institutionalUnitService", targetNamespace = Constants.FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface InstitutionalUnitService {
    
    /**
     * Get the Unit record corresponding to the given unit number.
     * 
     * @param unitNumber
     * @return UnitDTO
     */
    UnitDTO getUnit( @WebParam(name="unitNumber") String unitNumber);
    
    /**
     * Lookup Units according to the given search criteria.
     * 
     * @param criteria Key-value pair map of search criteria.
     * @return List&lt;UnitDTO&gt;
     */
    List<UnitDTO> lookupUnits( @WebParam(name="searchCriteria") List<HashMapElement> criteria);
    
    /**
     * Retrieve the parent units of the given unit number.  The list will be in 
     * the order of ancestry (parent at index 0, grandparent at index 1, etc).
     * 
     * @param unitNumber
     * @return List&lt;String&gt;
     */
    List<String> getParentUnits( @WebParam(name="unitNumber") String unitNumber);
}
