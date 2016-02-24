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

        if (StringUtils.isBlank(right)) {
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
