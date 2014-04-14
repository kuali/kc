package org.kuali.kra.s2s.depend;

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

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
