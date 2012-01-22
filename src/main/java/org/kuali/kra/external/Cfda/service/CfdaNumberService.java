/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.external.Cfda.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.Cfda.CfdaDTO;

/**
 * This is the external service that is published to the service bus for
 * accessing effort reporting information stored in the Kuali Coeus system.
 * 
 * @author Kuali Coeus Development Team
 */
@WebService(name = "CfdaNumberService", targetNamespace = "KC")
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


