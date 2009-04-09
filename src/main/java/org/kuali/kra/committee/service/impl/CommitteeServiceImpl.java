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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * The Committee Service implementation.
 */
public class CommitteeServiceImpl implements CommitteeService {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    private BusinessObjectService businessObjectService;

    /**
     * Set the Business Object Service.
     * 
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
                 * There is a database unique constraint that prevents more than committee from having the same committee ID.
                 * Therefore, the returned collection will always have zero or one entry.
                 */
                committee = committees.iterator().next();
            }
        }
        return committee;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#addResearchAreas(org.kuali.kra.committee.bo.Committee,
     *      java.util.Collection)
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
     * 
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
     * 
     * @param committee
     * @param researchArea
     */
    private void addCommitteeResearchArea(Committee committee, ResearchArea researchArea) {
        CommitteeResearchArea committeeResearchArea = new CommitteeResearchArea();
        committeeResearchArea.setCommittee(committee);
        committeeResearchArea.setCommitteeIdFk(committee.getId());
        committeeResearchArea.setCommitteeId(committee.getCommitteeId());
        committeeResearchArea.setResearchArea(researchArea);
        committeeResearchArea.setResearchAreaCode(researchArea.getResearchAreaCode());
        committee.getCommitteeResearchAreas().add(committeeResearchArea);
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getValidCommitteeDates(java.lang.String)
     */
    public List<KeyLabelPair> getValidCommitteeDates(String committeeId) {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        Committee committee = getCommitteeById(committeeId);
        if (committee != null) {
            List<CommitteeSchedule> schedules = committee.getCommitteeSchedules();
            for (CommitteeSchedule schedule : schedules) {
                if (isOkayToScheduleReview(committee, schedule)) {
                    keyValues.add(new KeyLabelPair(schedule.getScheduleId(), getDescription(schedule)));
                }
            }
        }
        return keyValues;
    }

    /**
     * Is it OK to schedule a review for the given committee and schedule?
     * @param committee
     * @param schedule
     * @return
     */
    private boolean isOkayToScheduleReview(Committee committee, CommitteeSchedule schedule) {
        Date now = new Date(System.currentTimeMillis());
        return true;

    }

    /**
     * Get the date/place/time description that will be displayed in 
     * the drop-down menu for the user.
     * @param schedule
     * @return
     */
    private String getDescription(CommitteeSchedule schedule) {
        Date date = schedule.getScheduledDate();
        long time = schedule.getActualTime().getTime() - date.getTime();
        Date t = new Date(time);
        return dateFormat.format(date) + " " + schedule.getPlace() + " " + timeFormat.format(schedule.getActualTime());
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getValidCommitteeDatesForAjax(java.lang.String)
     */
    public String getValidCommitteeDatesForAjax(String committeeId) {
        StringBuffer ajaxList = new StringBuffer();
        List<KeyLabelPair> dates = getValidCommitteeDates(committeeId);
        for (KeyLabelPair date : dates) {
            ajaxList.append(date.getKey() + "," + date.getLabel() + ",");
        }
        return ajaxList.substring(0, ajaxList.length() - 1);
    }
}
