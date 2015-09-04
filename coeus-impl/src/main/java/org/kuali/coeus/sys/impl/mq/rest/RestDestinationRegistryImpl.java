package org.kuali.coeus.sys.impl.mq.rest;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("restDestinationRegistry")
public class RestDestinationRegistryImpl implements RestDestinationRegistry {

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public String findUrl(String destination) {
        if (StringUtils.isBlank(destination)) {
            throw new IllegalArgumentException("destination cannot be blank or null");
        }

        return configurationService.getPropertyValueAsString(destination.endsWith(".url") ? destination : (destination + ".url"));
    }

    @Override
    public boolean isEnabled(String destination) {
        if (StringUtils.isBlank(destination)) {
            throw new IllegalArgumentException("destination cannot be blank or null");
        }

        return configurationService.getPropertyValueAsBoolean(destination.endsWith(".enabled") ? destination : (destination + ".enabled"));
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
