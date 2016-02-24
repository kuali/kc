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
package org.kuali.kra.external.Cfda.service;

import org.kuali.kra.external.Cfda.CfdaDTO;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.infrastructure.Constants;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * This is the external service that is published to the service bus for
 * accessing effort reporting information stored in the Kuali Coeus system.
 * 
 * @author Kuali Coeus Development Team
 */
@WebService(name = "CfdaNumberService", targetNamespace = Constants.FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface CfdaNumberService {
    
    /**
     * This method returns the cfda numbers of the awards that are linked to
     * the financial account number and chart.
     * @param financialAccountNumber
     * @param chartOfAccounts
     * @return
     */
    public List<CfdaDTO> getCfdaNumber( @WebParam(name= "financialAccountNumber") 
                                  String financialAccountNumber,
                                  @WebParam(name="chartOfAccounts")
                                  String chartOfAccounts);
    
    /**
     * Lookup units based on the search criteria. 
     * @param criteria
     * @return
     */
    List<CfdaDTO> lookupCfda( @WebParam(name="searchCriteria") List<HashMapElement> criteria);

}


