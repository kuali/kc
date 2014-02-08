/*
 * Copyright 2005-2013 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.util.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.ProxyFactoryBean;

import java.lang.reflect.Method;


/**
 * <p>Integrated with Spring for creating a tracing proxy for Spring Services. Makes use of the {@link ProxyFactoryBean} 
 * in order to proxy services.</p>
 * 
 * 
 */
public class MethodLoggingInterceptor implements MethodInterceptor {
    private static final Log LOG = LogFactory.getLog(MethodLoggingInterceptor.class);

    /**
     * Surrounds the method invocation with TRACE-level log messages. This is responsible for the trace messages we see. Covers entering, and exiting of
     * methods. This includes {@link Exception} and {@link Error} instances that might be thrown.
     * 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object methodResult = null;
        String invocationLabel = buildInvocationLabel(invocation);
        try {
            LOG.info("entering " + invocationLabel);

            methodResult = invocation.proceed();
        }
        catch (Throwable invocationException) {
            String exceptionLabel = buildExceptionLabel(invocationException);
            LOG.info("aborting " + invocationLabel + ": throwing " + exceptionLabel);

            throw invocationException;
        }
        LOG.info(new StringBuffer("leaving  ").append(invocationLabel).append(" / took ").append(System.currentTimeMillis() - startTime).append(" ms"));

        return methodResult;
    }

    /**
     * @param invocation MethodInvocation being labeled
     * @return String used to identify this MethodInvocation
     */
    private String buildInvocationLabel(MethodInvocation invocation) {
        StringBuffer invocationLabel = new StringBuffer();

        Method method = invocation.getMethod();
        Class targetClass = invocation.getThis().getClass();
        Class declaringClass = method.getDeclaringClass();

        // {targetClass} declaringClass.method
        if (targetClass != declaringClass) {
            invocationLabel.append("{");
            invocationLabel.append(declaringClass.getName());
            invocationLabel.append("} ");
        }
        invocationLabel.append(targetClass.getName() + "." + method.getName());


        // (paramClass=argValue[,paramClass=argValue...])
        Class[] paramTypes = method.getParameterTypes();
        Object[] argValues = invocation.getArguments();

        invocationLabel.append("(");
        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    invocationLabel.append(",");
                }

                invocationLabel.append(paramTypes[i].getName());
                invocationLabel.append("=");

                // differentiate between literal null and object whose toString returns null
                if (argValues[i] == null) {
                    invocationLabel.append("<literal null>");
                }
                else {
                    invocationLabel.append(argValues[i]);
                }
            }
        }
        invocationLabel.append(")");

        return invocationLabel.toString();
    }


    /**
     * @param e Exception being labeled
     * @return String used to identify this Exception
     */
    private String buildExceptionLabel(Throwable e) {
        String className = e.getClass().getName();

        String exceptionLabel = StringUtils.substringAfterLast(className, ".");
        if (StringUtils.isBlank(exceptionLabel)) {
            exceptionLabel = className;
        }

        return exceptionLabel;
    }}
