package org.kuali.kra.award.external.award;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.kra.external.HashMapElement;
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
