/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.lookup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.CollectionIncomplete;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

/**
 * 
 * This class is to create action links and inquiry url for committeeschedule lookup.
 */
@SuppressWarnings("serial")
public class CommitteeScheduleLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    private static final String READ_ONLY = "readOnly";
    private static final String COMMITTEE_COMMITTEE_NAME = "committee.committeeName";
    private static final String COMMITTEE_ID = "committeeId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String SCHEDULE_PERSON_ID_LOOKUP = "committee.committeeMemberships.personId";

    private TaskAuthorizationService taskAuthorizationService;

    // these two lists are used as an optimization while deciding if a committee is current
    private List<Long> activeCommitteePKs = new ArrayList<Long>();
    private List<Long> inActiveCommitteePKs = new ArrayList<Long>();


    // helper method that checks that the given committee instance has the highest sequence number of all other
    // committee instances in the database with the same committee id. This method scans the active and inactive
    // committee PK list before querying the database via BOService.
    // (See replacement for this method in ResearchAreaReferencesDaoOjb if efficiency becomes a concern)
    // NOTE: this method modifies the state of this object by updating two instance fields.
    private boolean isCurrentVersion(Committee committee) {
        boolean retValue = false;
        if (this.activeCommitteePKs.contains(committee.getId())) {
            retValue = true;
        }
        else {
            if (!this.inActiveCommitteePKs.contains(committee.getId())) {
                // since its in neither active nor the inactive list we have to query the database
                // get the list of all Committee instances that have the same id as the argument instance,
                // sorted in descending order of their sequence numbers
                Map<String, String> fieldValues = new HashMap<String, String>();
                fieldValues.put(COMMITTEE_ID, committee.getCommitteeId());
                @SuppressWarnings("unchecked")
                List<Committee> committees = (List<Committee>) this.getBusinessObjectService().findMatchingOrderBy(Committee.class, fieldValues, SEQUENCE_NUMBER, false);
                if ( (committees != null) && (!committees.isEmpty()) ) {
                    // check the first element with the argument
                    if(committees.get(0).equals(committee)) {
                        retValue = true;
                    }
                    // update the active list, add the first element PK
                    this.activeCommitteePKs.add(committees.get(0).getId());
                    // update the inactive list, add the remaining elements PK
                    committees.remove(0);
                    for(Committee cmt: committees) {                    
                        this.inActiveCommitteePKs.add(cmt.getId());
                    }
                }                
            }
        }
        return retValue;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<CommitteeSchedule> rawCommitteeSchedules = (List<CommitteeSchedule>) super.getSearchResultsUnbounded(fieldValues);
        List<CommitteeSchedule> finalCommitteeSchedules = new ArrayList<CommitteeSchedule>();
        // go through each of the raw schedules and decide if it should be included in the final listing
        for (CommitteeSchedule schedule : rawCommitteeSchedules) {
            // check if the schedule's committee is the most current version
            if ((schedule.getCommittee() != null) && (isCurrentVersion(schedule.getCommittee()))) {
                // are we looking for all schedules or for a specific user?
                if (StringUtils.isNotBlank(fieldValues.get(SCHEDULE_PERSON_ID_LOOKUP))) {
                    // check if schedule is active for the logged in user and is made available or is modifiable by user
                    if( ( (schedule.isActiveFor(fieldValues.get(SCHEDULE_PERSON_ID_LOOKUP)) && (schedule.isAvailableToReviewers()) ) || (canModifySchedule(schedule)) ) ) {
                        finalCommitteeSchedules.add(schedule);                        
                    }
                }
                else {
                    // we are not filtering by person id, so just add the schedule to the final list
                    finalCommitteeSchedules.add(schedule);
                }
            }
        }
        // if we are looking for schedules for a specific user then 
        // sort the schedules based on schedule date and move the entire block of past schedules to the end
        if (StringUtils.isNotBlank(fieldValues.get(SCHEDULE_PERSON_ID_LOOKUP))) {
            Collections.sort(finalCommitteeSchedules);
            List<CommitteeSchedule> pastCommitteeSchedules = new ArrayList<CommitteeSchedule>();
            for (CommitteeSchedule schedule : finalCommitteeSchedules) {
                if(schedule.isScheduleDateInPast()) {
                    pastCommitteeSchedules.add(schedule);
                }
                else {
                    break;
                }
            }
            // removeAll the past schedules and addAll them back (this will have the effect of moving them from front to the back)
            finalCommitteeSchedules.removeAll(pastCommitteeSchedules);
            Collections.reverse(pastCommitteeSchedules);
            finalCommitteeSchedules.addAll(pastCommitteeSchedules); 
        }
        
        
        // processing return collection based on final result count
        Long matchingResultsCount = new Long(finalCommitteeSchedules.size());
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(CommitteeSchedule.class);
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete<CommitteeSchedule>(finalCommitteeSchedules, new Long(0));
        }
        else {
            return new CollectionIncomplete<CommitteeSchedule>(trimResult(finalCommitteeSchedules, searchResultsLimit),
                matchingResultsCount);
        }
    }


    /**
     * This method trims the search result.
     * 
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    protected List<CommitteeSchedule> trimResult(List<CommitteeSchedule> result, Integer trimSize) {
        List<CommitteeSchedule> trimmedResult = new ArrayList<CommitteeSchedule>();
        for (CommitteeSchedule committeeSchedule : result) {
            if (trimmedResult.size() < trimSize) {
                trimmedResult.add(committeeSchedule);
            }
        }
        return trimmedResult;
    }


    /**
     * Add edit/view action links based on user's permission
     * 
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject,
     *      java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (canModifySchedule((CommitteeSchedule) businessObject)) {
            htmlDataList.add(getLink((CommitteeSchedule) businessObject, true));
            htmlDataList.add(getLink((CommitteeSchedule) businessObject, false));
        }
        else if (canViewSchedule((CommitteeSchedule) businessObject)) {
            htmlDataList.add(getLink((CommitteeSchedule) businessObject, false));
        }
        
        // append past message to action URL list if the schedule date has passed
        
        if( ((CommitteeSchedule) businessObject).isScheduleDateInPast()) {
            AnchorHtmlData htmlData = new AnchorHtmlData();
            htmlData.setDisplayText("(past)");
            htmlDataList.add(htmlData);
        }
        
        return htmlDataList;
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    /*
     * This is a utility method to set up url to edit or view meeting schedule
     */
    protected AnchorHtmlData getLink(CommitteeSchedule committeeSchedule, boolean isEdit) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "start");
        parameters.put("scheduleId", committeeSchedule.getId().toString());
        if (isEdit) {
            htmlData.setDisplayText("edit");
            parameters.put(READ_ONLY, "false");
        }
        else {
            htmlData.setDisplayText("view");
            parameters.put(READ_ONLY, "true");
        }
       
        String href = UrlFactory.parameterizeUrl("../meetingManagement.do", parameters);

        htmlData.setHref(href);
        return htmlData;

    }

    /**
     * This method is for committeeId that does not have inquiry created by lookup frame work. Also, disable inquiry link for
     * committee name.
     * 
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject,
     *      java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (COMMITTEE_COMMITTEE_NAME.equals(propertyName)) {
            return new AnchorHtmlData();
        }
        else {
            return super.getInquiryUrl(inqBo, inqPropertyName);
        }
    }

    protected boolean canModifySchedule(CommitteeSchedule committeeSchedule) {
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_SCHEDULE, committeeSchedule.getCommittee());
        return taskAuthorizationService.isAuthorized(getUserIdentifier(), task);
    }

    protected boolean canViewSchedule(CommitteeSchedule committeeSchedule) {
        CommitteeTask task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getCommittee(), committeeSchedule);
        boolean result = taskAuthorizationService.isAuthorized(getUserIdentifier(), task);
        return result;
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    /**
     * To disable search & inquiry icons for committee.committeeid & committee.committeename
     * 
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if ("committee.committeeId".equals(field.getPropertyName())
                        || COMMITTEE_COMMITTEE_NAME.equals(field.getPropertyName())) {
                    // to disable lookup/inquiry display
                    field.setQuickFinderClassNameImpl(KNSConstants.EMPTY_STRING);
                }
            }
        }
        return rows;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
