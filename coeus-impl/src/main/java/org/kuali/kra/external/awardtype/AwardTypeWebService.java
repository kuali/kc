package org.kuali.kra.external.awardtype;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.infrastructure.Constants;

@WebService(name = "awardTypeWebService", targetNamespace = Constants.FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AwardTypeWebService {

	public AwardTypeDTO getAwardType(@WebParam(name = "awardTypeCode") Integer awardTypeCode);
	
	public List<AwardTypeDTO> findMatching(@WebParam(name = "searchCriteria") List<HashMapElement> searchCriteria);
}
