package org.kuali.coeus.sys.framework.validation;

import java.util.List;
import java.util.Map;

import org.kuali.rice.krad.util.ErrorMessage;

public interface ErrorHandlingUtilService {
	
	public Map<String, List<String>> extractErrorMessages();
	
	public Map<String, List<String>> resolveErrorMessages(Map<String, List<ErrorMessage>> errorMap);

	public String resolveErrorMessage(ErrorMessage errorMessage);
	
	public String resolveErrorKey(String errorKey, String...params);
}
