/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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


    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public void afterPropertiesSet() throws Exception {
        verifyOracleConfiguration();
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
