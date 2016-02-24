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
package org.kuali.coeus.common.impl.sponsor.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("sponsorHierarchyService")
public class SponsorHierarchyServiceImpl implements SponsorHierarchyService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public boolean isSponsorInHierarchy(String sponsorCode, String hierarchyName, int level, String levelName) {
        if (StringUtils.isBlank(sponsorCode)) {
            throw new IllegalArgumentException("The sponsorCode cannot be blank");
        }

        if (StringUtils.isBlank(hierarchyName)) {
            throw new IllegalArgumentException("The hierarchyName cannot be blank");
        }

        if (level < 1 || level > 10) {
            throw new IllegalArgumentException("The level must be between 1 and 10 inclusive");
        }

        if (StringUtils.isBlank(levelName)) {
            throw new IllegalArgumentException("The levelName cannot be blank");
        }

        final Map<String, String> valueMap = new HashMap<String, String>();
        valueMap.put("sponsorCode", sponsorCode);
        valueMap.put("hierarchyName", hierarchyName);
        valueMap.put("level" + level, levelName);
        return businessObjectService.countMatching(SponsorHierarchy.class, valueMap) > 0;
    }

    @Override
    public boolean isSponsorInHierarchy(String sponsorCode, String hierarchyName) {
        if (StringUtils.isBlank(sponsorCode)) {
            throw new IllegalArgumentException("The sponsorCode cannot be blank");
        }

        if (StringUtils.isBlank(hierarchyName)) {
            throw new IllegalArgumentException("The hierarchyName cannot be blank");
        }

        final Map<String, String> valueMap = new HashMap<String, String>();
        valueMap.put("sponsorCode", sponsorCode);
        valueMap.put("hierarchyName", hierarchyName);
        return businessObjectService.countMatching(SponsorHierarchy.class, valueMap) > 0;
    }

    @Override
    public boolean isSponsorNihMultiplePi(String sponsorCode) {
        return isSponsorInHierarchy(sponsorCode, SPONSOR_HIERARCHY_NIH_MULT_PI);
    }

    @Override
    public boolean isSponsorNihOsc(String sponsorCode) {
        return isSponsorInHierarchy(sponsorCode, SPONSOR_HIERARCHY_NIH_OSC);
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
