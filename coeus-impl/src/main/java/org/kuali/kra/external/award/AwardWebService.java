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
package org.kuali.kra.external.award;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.kra.infrastructure.Constants;

@WebService(name = "awardWebService", targetNamespace = Constants.FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AwardWebService {

	public AwardDTO getAward(@WebParam(name = "awardId") Long awardId);
	
	public List<AwardDTO> getMatchingAwards(@WebParam(name = "searchCriteria") AwardFieldValuesDto fieldValues);
	
	public List<AwardDTO> searchAwards(@WebParam(name = "searchDto") AwardSearchCriteriaDto searchDto);

	public AwardBillingUpdateStatusDto updateAwardBillingStatus(@WebParam(name = "searchDto") AwardFieldValuesDto searchDto,
																@WebParam(name = "billingUpdate") AwardBillingUpdateDto updateDto);
}
