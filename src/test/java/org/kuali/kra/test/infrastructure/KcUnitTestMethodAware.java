package org.kuali.kra.test.infrastructure;

import java.lang.reflect.Method;

public interface KcUnitTestMethodAware {
	public void setTestMethod(Method method);
}
