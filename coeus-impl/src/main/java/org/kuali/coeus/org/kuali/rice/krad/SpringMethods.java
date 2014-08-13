package org.kuali.coeus.org.kuali.rice.krad;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;

public class SpringMethods {

	public static Object retrieveSpringBean(String beanId) {
		Object result = KcServiceLocator.getService(beanId);
		return result;
	}
}
