package org.kuali.coeus.sys.impl.vc;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.vc.VersionNumberService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("versionNumberService")
public class VersionNumberServiceImpl implements VersionNumberService {

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public String getVersion() {
        final String v =  configurationService.getPropertyValueAsString("version");
        if (StringUtils.isBlank(v)) {
            throw new IllegalStateException("the version is blank");
        }
        return v;
    }

    @Override
    public boolean lessThan(String version) {
        return comp(version, getVersion()) < 0;
    }

    @Override
    public boolean greaterThan(String version) {
        return comp(version, getVersion()) > 0;
    }

    @Override
    public boolean equalTo(String version) {
        return comp(version, getVersion()) == 0;
    }

    protected int comp(String left, String right) {
        if (StringUtils.isBlank(left)) {
            throw new IllegalArgumentException("left is blank");
        }

        if (StringUtils.isBlank(left)) {
            throw new IllegalArgumentException("right is blank");
        }

        return new SemanticVersion(left).compareTo(new SemanticVersion(right));
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
