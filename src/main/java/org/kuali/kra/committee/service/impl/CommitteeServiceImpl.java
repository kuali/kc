/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.committee.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.service.CommitteeService;

/**
 * The Committee Service implementation.
 */
public class CommitteeServiceImpl implements CommitteeService {

    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getCommitteeById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Committee getCommitteeById(String committeeId) {
        Committee committee = null;
        if (committeeId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("committeeId", committeeId);
            Collection<Committee> committees = businessObjectService.findMatching(Committee.class, fieldValues);
            if (committees.size() > 0) {
                /*
                 * There is a database unique constraint that prevents more than
                 * committee from having the same committee ID.  Therefore, the
                 * returned collection will always have zero or one entry.
                 */
                committee = committees.iterator().next();
            }
        }
        return committee;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#addResearchAreas(org.kuali.kra.committee.bo.Committee, java.util.Collection)
     */
    public void addResearchAreas(Committee committee, Collection<ResearchArea> researchAreas) {
        for (ResearchArea researchArea : researchAreas) {
            if (!hasResearchArea(committee, researchArea)) {
                addCommitteeResearchArea(committee, researchArea);
            }
        }
    }
    
    /**
     * Does the committee already have this research area?
     * @param committee
     * @param researchArea
     * @return true if the committee has the research area; otherwise false
     */
    private boolean hasResearchArea(Committee committee, ResearchArea researchArea) {
        for (CommitteeResearchArea committeeResearchArea : committee.getCommitteeResearchAreas()) {
            if (StringUtils.equals(committeeResearchArea.getResearchAreaCode(), researchArea.getResearchAreaCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a research area to the committee.
     * @param committee
     * @param researchArea
     */
    private void addCommitteeResearchArea(Committee committee, ResearchArea researchArea) {
        CommitteeResearchArea committeeResearchArea = new CommitteeResearchArea();
        committeeResearchArea.setCommittee(committee);
        committeeResearchArea.setCommitteeId(committee.getId());
        committeeResearchArea.setResearchArea(researchArea);
        committeeResearchArea.setResearchAreaCode(researchArea.getResearchAreaCode());
        committee.getCommitteeResearchAreas().add(committeeResearchArea);
    }
}
