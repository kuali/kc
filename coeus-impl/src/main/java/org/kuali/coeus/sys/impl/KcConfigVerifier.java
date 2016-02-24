/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component("kcConfigVerifier")
public class KcConfigVerifier implements InitializingBean {

    private static final Log LOG = LogFactory.getLog(KcConfigVerifier.class);

    private static final String ORACLE_PLATFORM_NM = "Oracle";
    private static final String ORACLE_CLASS_NAME = "org.eclipse.persistence.platform.database.oracle.Oracle11Platform";
    private static final String KC_CONFIG_VERIFIER_HARD_ERROR_CFG_NM = "kc.config.verifier.hard.error";
    private static final String SERVER_DATASOURCE_PLATFORM_CFG_NM = "server.datasource.platform";
    private static final String DATASOURCE_PLATFORM_CFG_NM = "datasource.platform";
    private static final String MISSING_LIB_MESSAGE_FOR_ORACLE = "Oracle platform detected but org.eclipse.persistence:org.eclipse.persistence.oracle:jar is not found on the classpath";
    private static final String INCLUDED_LIB_MESSAGE_FOR_NON_ORACLE = "Non-Oracle platform detected but org.eclipse.persistence:org.eclipse.persistence.oracle:jar is found on the classpath";
    private static final String TOMCAT_SERVER_CLASS_NAME = "org.apache.catalina.Server";
    private static final String TOMCAT_INSTRUMENTATION_CLASS_LOADER_CLASS_NAME = "org.springframework.instrument.classloading.tomcat.TomcatInstrumentableClassLoader";
    private static final String INSTRUMENTATION_AGENT_CLASS_NAME = "org.springframework.instrument.InstrumentationSavingAgent";
    private static final String GET_INSTRUMENTATION_METHOD = "getInstrumentation";

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public void afterPropertiesSet() throws Exception {
        verifyOracleConfiguration();
        verifyInstrumentationConfiguration();
    }

    protected void verifyOracleConfiguration() {
        if (oraclePlatform() && !oracleJpaLibraryAvailable()) {
            if (hardError()) {
                throw new RuntimeException(MISSING_LIB_MESSAGE_FOR_ORACLE);
            } else {
                LOG.fatal(MISSING_LIB_MESSAGE_FOR_ORACLE);
            }
        } else if (!oraclePlatform() && oracleJpaLibraryAvailable()) {
            LOG.warn(INCLUDED_LIB_MESSAGE_FOR_NON_ORACLE);
        }
    }

    /**
     * Verifies that the instrumentation configuration is set up correctly.  EclipseLink requires
     * some form of instrumentation for features like lazy-loading.  Without proper instrumentation,
     * subtle hard-to-detect errors may occur.
     */
    protected void verifyInstrumentationConfiguration() {

        if (tomcatInstrumentingClassLoaderAvailable() && genericInstrumentationAgentAvailable()) {
            LOG.warn("Both the Spring Tomcat Instrumenting ClassLoader and the Spring Instrumentation Agent are on the classpath but only one is needed.");
        }

        if (runningOnTomcat()) {
            if (!tomcatInstrumentingClassLoaderAvailable() && !genericInstrumentationAgentAvailable()) {
                if (hardError()) {
                    throw new RuntimeException("Neither the Spring Tomcat Instrumenting ClassLoader or the Spring Instrumentation Agent are on the classpath and one is needed.");
                }
            } else if (tomcatInstrumentingClassLoaderAvailable() && !tomcatInstrumentingClassLoaderConfigured()) {
                if (hardError()) {
                    throw new RuntimeException("The Spring Tomcat Instrumenting ClassLoader is on the classpath but is not properly configured in the context.xml file.");
                }
            } else if (genericInstrumentationAgentAvailable() && !genericInstrumentationAgentConfigured()) {
                if (hardError()) {
                    throw new RuntimeException("The Spring Instrumentation Agent is on the classpath but is not properly configured as a jvm javaagent.");
                }
            }
        } else {
            if (tomcatInstrumentingClassLoaderAvailable()) {
                LOG.warn("The Spring Tomcat Instrumenting ClassLoader is on the classpath but the Tomcat Application Server is not detected.");
            }

            if (!genericInstrumentationAgentAvailable()) {
                if (hardError()) {
                    throw new RuntimeException("The Spring Instrumentation Agent is not on the classpath but is needed.");
                }
            } else if (genericInstrumentationAgentAvailable() && !genericInstrumentationAgentConfigured()) {
                if (hardError()) {
                    throw new RuntimeException("The Spring Instrumentation Agent is on the classpath but is not properly configured as a jvm javaagent.");
                }
            }
        }
    }

    /**
     * Checks for the tomcat server class which would only be available if running on tomcat.
     */
    protected boolean runningOnTomcat() {
        try {
            Class.forName(TOMCAT_SERVER_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Checks if the Spring Instrumenting ClassLoader for Tomcat is available on the classpath.
     */
    protected boolean tomcatInstrumentingClassLoaderAvailable() {
        try {
            Class.forName(TOMCAT_INSTRUMENTATION_CLASS_LOADER_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Checks if the Spring Instrumenting ClassLoader is in the ClassLoader chain. If so, this means
     * that it is properly configured.
     */
    protected boolean tomcatInstrumentingClassLoaderConfigured() {
        //generally this means that the tomcat context.xml file has tomcat ClassLoader configured.  If not then
        //something really strange is going on
        ClassLoader cl = this.getClass().getClassLoader();
        while (cl != null) {
            if (TOMCAT_INSTRUMENTATION_CLASS_LOADER_CLASS_NAME.equals(cl.getClass().getName())) {
                return true;
            }
            cl = cl.getParent();
        }
        return false;
    }

    /**
     * Checks if the Spring Instrumentation Agent is available on the classpath.
     */
    protected boolean genericInstrumentationAgentAvailable() {
        try {
            Class.forName(INSTRUMENTATION_AGENT_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Checks if the Spring Instrumentation Agent has a non-null Instrumentation instance.  If so, this means
     * that it is properly configured.
     */
    protected boolean genericInstrumentationAgentConfigured() {
        try {
            Class<?> instrumentationSavingAgentClass = Class.forName(INSTRUMENTATION_AGENT_CLASS_NAME);
            Method getInstrumentation = instrumentationSavingAgentClass.getMethod(GET_INSTRUMENTATION_METHOD);
            return getInstrumentation.invoke(instrumentationSavingAgentClass) != null;
        } catch (ClassNotFoundException|InvocationTargetException|NoSuchMethodException|IllegalAccessException e) {
            return false;
        }
    }

    protected boolean oracleJpaLibraryAvailable() {
        try {
            Class.forName(ORACLE_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
           return false;
        } catch (NoClassDefFoundError e) {
            //class is available but it cannot be initialized because of missing oracle drivers
            return true;
        }

    }

    protected boolean oraclePlatform() {
        final String serverPlatform = configurationService.getPropertyValueAsString(SERVER_DATASOURCE_PLATFORM_CFG_NM);
        final String clientPlatform = configurationService.getPropertyValueAsString(DATASOURCE_PLATFORM_CFG_NM);

        return (serverPlatform != null && serverPlatform.contains(ORACLE_PLATFORM_NM)) || (clientPlatform != null && clientPlatform.contains(ORACLE_PLATFORM_NM));
    }

    protected boolean hardError() {
        return configurationService.getPropertyValueAsBoolean(KC_CONFIG_VERIFIER_HARD_ERROR_CFG_NM);
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
