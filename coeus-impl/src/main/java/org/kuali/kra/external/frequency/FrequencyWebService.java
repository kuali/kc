package org.kuali.kra.external.frequency;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.kra.infrastructure.Constants;

@WebService(name = "frequencyWebService", targetNamespace = Constants.FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface FrequencyWebService {

	public FrequencyDto getFrequency(@WebParam(name = "frequencyCode") String frequencyCode);
	
	public List<FrequencyDto> findMatching(@WebParam(name = "frequencyCode") String frequencyCode, @WebParam(name = "description") String description);
	
	public List<FrequencyDto> findAll();
}
