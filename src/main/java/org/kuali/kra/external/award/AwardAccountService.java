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
package org.kuali.kra.external.award;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "awardAccountService", targetNamespace = "KC")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AwardAccountService {

    
    /**
     * This method returns all the awards linked to a financial account number
     * and the chart code
     * @param financialAccountNumber
     * @return
     */    
    List<AwardAccountDTO> getAwardAccounts(
                                          @WebParam(name= "financialAccountNumber") 
                                          String financialAccountNumber,
                                          @WebParam(name="chartOfAccounts")
                                          String chartOfAccounts);
    
}
