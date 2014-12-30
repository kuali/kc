package org.kuali.kra.external.sponsor;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.infrastructure.Constants;

@WebService(name = "sponsorWebService", targetNamespace = Constants.FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface SponsorWebService {

	public SponsorDTO getSponsor(@WebParam(name = "sponsorCode") String sponsorCode);
	
	public List<SponsorDTO> getMatchingSponsors(@WebParam(name = "searchCriteria") SponsorCriteriaDto searchCriteria);
}
