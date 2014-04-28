package org.kuali.rice.contrib.krad;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;

public class SpringMethods {

	public static Object retrieveSpringBean(String beanId) {
		Object result = KcServiceLocator.getService(beanId);
		return result;
	}
}
