package org.kuali.coeus.sys.framework.validation;

import java.util.List;
import java.util.Map;

import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.MessageMap;

public interface ErrorHandlingUtilService {
	
	Map<String, List<String>> extractErrorMessages(MessageMap messageMap);
	
	Map<String, List<String>> resolveErrorMessages(Map<String, List<ErrorMessage>> errorMap);

	String resolveErrorMessage(ErrorMessage errorMessage);
	
	String resolveErrorKey(String errorKey, String...params);
}
