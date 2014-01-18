package org.kuali.kra.test.infrastructure;

import java.lang.reflect.Method;

public interface KcIntegrationTestMethodAware {
	public void setTestMethod(Method method);
}
