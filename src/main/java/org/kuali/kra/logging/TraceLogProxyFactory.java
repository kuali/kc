/*
 * Copyright 2006-2009 The Kuali Foundation.
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
package org.kuali.kra.logging;

import static org.kuali.kra.logging.BufferedLogger.error;
import static org.kuali.kra.logging.BufferedLogger.trace;

import java.lang.reflect.Method;
import java.util.Collection;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.logging.LogFactory;

/**
 * Factory for creating a <i>Proxy</i> for trace logging. There is no easy way to log entry and exit of a method. With this Factory, 
 * a <i>Proxy</i> can be created to log that trace information.
 * 
 */
public class TraceLogProxyFactory {
    private static final String CLASS_NOT_INSTANTIABLE = "Could not create an instance of class %s\n";
    
    
    /**
     * Get a proxy for a class. Only returns a proxy if the <code><T></code> is an instance of {@link Traceable}. If it is not, then
     * a new instance is returned.
     * 
     * @param <T>
     * @param target Class to proxy
     * @return Proxy if <code>clazz</code> is assignable from {@link Traceable}; otherwise, just a new instance.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxyFor(Class<T> target) {
        if (target==null) {
            throw new IllegalArgumentException("Can't proxy a null value");
        }

        T retval = null;
        
        if (Traceable.class.isAssignableFrom(target) && LogFactory.getLog(target).isTraceEnabled()) {
            Class targetClass = target;
            if (Enhancer.isEnhanced(target)) {
                targetClass = target.getSuperclass();
            }
            return (T) Enhancer.create(targetClass, new Class[] { Traceable.class }, new TraceInvocationHandler());
        }
        
        try {
            retval = (T) target.newInstance();
        }
        catch (Exception e) {
            error(CLASS_NOT_INSTANTIABLE, target.getSimpleName());
            e.printStackTrace();
        }
                
        return retval;
    }
    
    /**
     * Used if an object instance exists, and it is desired to be proxied for tracing. This method can only be applied to 
     * instances of {@link Traceable}. If the object's class does not implement {@link Traceable}, this method cannot be called.
     * 
     * @param <T>
     * @param source
     * @return T whatever proxy we want
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxyFor(Traceable<T> target) {
        Class targetClass = target.getClass();
                
        if (Enhancer.isEnhanced(targetClass)) {
            targetClass = target.getClass().getSuperclass();
        }

        if (!Traceable.class.isAssignableFrom(targetClass) || !LogFactory.getLog(targetClass).isTraceEnabled()) {
            return (T) target;
        }

        LogFactory.getLog(TraceLogProxyFactory.class).info("Returning proxy");
        return (T) Enhancer.create(targetClass, new Class[] { Traceable.class }, new TraceInvocationHandler(target));
    }
}

/**
 * Callback class used by cglib's {@link Enhancer} to proxy methods from. Basic functionality of the proxy goes here. 
 * 
 */
class TraceInvocationHandler implements MethodInterceptor {
    private static final String TRACE_START         = "************************* %s#%s TRACE START **************************";
    private static final String TRACE_END           = "************************* %s TRACE END   **************************";
    private static final String LINE                = "*******************************************************************";
    private static final String ENTERING_METH_MSG   = "Entering method %s with parameters:";
    private static final String ENTERING_METH_MSG_2 = "Entering method %s without parameters";
    private static final String METHOD_PARAM_MSG    = "Parameter Type: %s, Value: %s";
    private static final String RETURN_METH_MSG     = "Returning from method %s with Value: %s";
    private static final String RETURN_METH_MSG_2   = "Returning from method %s";
    private Object archetype;
    
    public TraceInvocationHandler() {
        this(null);
    }
    
    public TraceInvocationHandler(Object archetype) {
        this.archetype = archetype;
    }
    
    /**
     * Called by cglib. This is the method that is called by the proxy. All the trace stuff goes in here. 
     *  
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        trace(TRACE_START, method.getDeclaringClass().getSimpleName(), method.getName());
        /*
        String enterMessage = ENTERING_METH_MSG;
        
        if (args.length < 1) {
            enterMessage = ENTERING_METH_MSG_2;
        }
        
        info(enterMessage, method.getName());
        */

        Class[] paramTypes = method.getParameterTypes();
        
        for (int i = 0; i < args.length; i++) {
            Class paramType = paramTypes[i];
            Object arg = args[i];
            
            trace(METHOD_PARAM_MSG, paramType.getSimpleName(), getArgValue(paramType, arg));
        }
        trace(LINE);
        
        Object retval = null;
        
        if (archetype != null) {
            retval = proxy.invoke(archetype, args);
        }
        else {
            retval = proxy.invokeSuper(obj, args);
        }
        
        if (method.getReturnType() != Void.TYPE) {
            trace(RETURN_METH_MSG, method.getName(), retval);
        }
        else {
            trace(RETURN_METH_MSG_2, method.getName());
        }
        trace(LINE);
        trace(TRACE_END, method.getName());
        
        return retval;
    }

    /**
     * Handles getting the argument value. This method may delegate based on the argument type
     * 
     * @param paramType the type of the argument
     * @param arg the argument
     * @return String of the value of the argument to be output for tracing
     * @see #getArrayArgValue(Object[])
     */
    @SuppressWarnings("unchecked")
    private String getArgValue(Class paramType, Object arg) {
        StringBuffer retval = new StringBuffer();
        
        if (paramType.isArray()) {
            retval.append(getArrayArgValue((Object[]) arg));
        }
        else {
            retval.append(arg);
        }
        
        if (Collection.class.isAssignableFrom(paramType)) {
            retval.append("size = ").append(((Collection) arg).size());
        }
        
        return retval.toString();
    }

    /**
     * If the argument was an array, this will create a {@link String} of all the array elements for tracing. This method will recurse back to {@link #getArgValue(Class, Object)}
     * in case there is an array of arrays.
     * 
     * @param arg
     * @return String representation of the array elements
     */
    private String getArrayArgValue(Object[] arg) {
        StringBuffer retval = new StringBuffer("{");
        
        for (Object obj : arg) {
            retval.append(getArgValue(obj.getClass(), obj)).append(",");
        }
        retval.deleteCharAt(retval.length() - 1);
        retval.append("}");
        return retval.toString();
    }
}
