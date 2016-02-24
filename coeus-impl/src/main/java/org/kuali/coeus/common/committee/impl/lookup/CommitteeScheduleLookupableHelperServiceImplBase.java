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
package org.kuali.coeus.common.committee.impl.lookup;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;

/**
 * 
 * This class is to create action links and inquiry url for committeeschedule lookup.
 */
@SuppressWarnings({ "serial", "deprecation" })
public abstract class CommitteeScheduleLookupableHelperServiceImplBase<CS extends CommitteeScheduleBase<CS, CMT, ?, ?>,
                                                                   CMT extends CommitteeBase<CMT, ? , CS>,
                                                                   CMTTSK extends CommitteeTaskBase<CMT>,
                                                                   CSTSK extends CommitteeScheduleTaskBase<CMT, CS>>

                                                                   extends KualiLookupableHelperServiceImpl {
    private static final String READ_ONLY = "readOnly";
    private static final String COMMITTEE_COMMITTEE_NAME = "committee.committeeName";
    private static final String COMMITTEE_COMMITTEE_TYPE_CODE = "committee.committeeTypeCode";
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
    protected boolean isCurrentVersion(CMT committee) {
        boolean retValue = false;
        if (this.activeCommitteePKs.contains(committee.getId())) {
            retValue = true;
        }
        else {
            if (!this.inActiveCommitteePKs.contains(committee.getId())) {
                // since its in neither active nor the inactive list we have to query the database
                // get the list of all CommitteeBase instances that have the same id as the argument instance,
                // sorted in descending order of their sequence numbers
                Map<String, String> fieldValues = new HashMap<String, String>();
                fieldValues.put(COMMITTEE_ID, committee.getCommitteeId());

                List<CMT> committees = (List<CMT>) this.getBusinessObjectService().findMatchingOrderBy(getCommonCommitteeBOClassHook(), fieldValues, SEQUENCE_NUMBER, false);
                if ( (committees != null) && (!committees.isEmpty()) ) {
                    // check the first element with the argument
                    if(committees.get(0).equals(committee)) {
                        retValue = true;
                    }
                    // update the active list, add the first element PK
                    this.activeCommitteePKs.add(committees.get(0).getId());
                    // update the inactive list, add the remaining elements PK
                    committees.remove(0);
                    for(CMT cmt: committees) {                    
                        this.inActiveCommitteePKs.add(cmt.getId());
                    }
                }                
            }
        }
        return retValue;
    }


    protected abstract Class<CMT> getCommonCommitteeBOClassHook();


    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        fieldValues.put(COMMITTEE_COMMITTEE_TYPE_CODE, getCommitteeTypeCodeHook());

        List<CS> rawCommitteeSchedules = (List<CS>) super.getSearchResultsUnbounded(fieldValues);
        List<CS> finalCommitteeSchedules = new ArrayList<CS>();
        // go through each of the raw schedules and decide if it should be included in the final listing
        for (CS schedule : rawCommitteeSchedules) {
            // check if the schedule's committee is the most current version
            if ((schedule.getParentCommittee() != null) && (isCurrentVersion(schedule.getParentCommittee()))) {
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
        Collections.sort(finalCommitteeSchedules);
        
        // if we are looking for schedules for a specific user then 
        // sort the schedules based on schedule date and move the entire block of past schedules to the end
        if (StringUtils.isNotBlank(fieldValues.get(SCHEDULE_PERSON_ID_LOOKUP))) {
            List<CS> pastCommitteeSchedules = new ArrayList<CS>();
            for (CS schedule : finalCommitteeSchedules) {
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

        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(getCommitteeScheduleBOClassHook());
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete<CS>(finalCommitteeSchedules, new Long(0));
        }
        else {
            return new CollectionIncomplete<CS>(trimResult(finalCommitteeSchedules, searchResultsLimit),
                matchingResultsCount);
        }
    }


    protected abstract Class<CS> getCommitteeScheduleBOClassHook();


    protected abstract String getCommitteeTypeCodeHook();


    /**
     * This method trims the search result.
     * 
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    protected List<CS> trimResult(List<CS> result, Integer trimSize) {
        List<CS> trimmedResult = new ArrayList<CS>();
        for (CS committeeSchedule : result) {
            if (trimmedResult.size() < trimSize) {
                trimmedResult.add(committeeSchedule);
            }
        }
        return trimmedResult;
    }


    /**
     * Add edit/view action links based on user's permission
     * 
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject,
     *      java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        boolean canModify = canModifySchedule((CS) businessObject);
        boolean canView = canViewSchedule((CS) businessObject);

        if (canModify) {
            htmlDataList.add(getLink((CS) businessObject, true));
            htmlDataList.add(getLink((CS) businessObject, false));
        }
        else if (canView) {
            htmlDataList.add(getLink((CS) businessObject, false));
        }
        
        // append past message to action URL list if the schedule date has passed
        
        if( ((CS) businessObject).isScheduleDateInPast() && (canModify || canView)) {
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
    protected AnchorHtmlData getLink(CS committeeSchedule, boolean isEdit) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "start");
        parameters.put("scheduleId", committeeSchedule.getId().toString());
        if (isEdit) {
            htmlData.setDisplayText("edit");
            parameters.put(READ_ONLY, "false");
        }
        else {
            htmlData.setDisplayText("view");
            parameters.put(READ_ONLY, "true");
        }
        String href = UrlFactory.parameterizeUrl("../" + getMeetingManagementActionIdHook()+ ".do", parameters);
        htmlData.setHref(href);
        return htmlData;

    }
    
    protected abstract String getMeetingManagementActionIdHook();

    /**
     * This method is for committeeId that does not have inquiry created by lookup frame work. Also, disable inquiry link for
     * committee name.
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

    protected boolean canModifySchedule(CS committeeSchedule) {
        CMTTSK task = getNewCommitteeTaskInstanceHook(TaskName.MODIFY_SCHEDULE, committeeSchedule.getParentCommittee());
        return taskAuthorizationService.isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract CMTTSK getNewCommitteeTaskInstanceHook(String taskName, CMT committee);

    
    
    protected boolean canViewSchedule(CS committeeSchedule) {
        CSTSK task =  getNewCommitteeScheduleTaskInstanceHook(TaskName.VIEW_SCHEDULE, committeeSchedule.getParentCommittee(), committeeSchedule);
        boolean result = taskAuthorizationService.isAuthorized(getUserIdentifier(), task);
        return result;
    }
    
    protected abstract CSTSK getNewCommitteeScheduleTaskInstanceHook(String taskName, CMT committee, CS committeeSchedule);

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    /**
     * To disable search &amp; inquiry icons for committee.committeeid &amp; committee.committeename
     * 
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if ("committee.committeeId".equals(field.getPropertyName())
                        || COMMITTEE_COMMITTEE_NAME.equals(field.getPropertyName())) {
                    // to disable lookup/inquiry display
                    field.setQuickFinderClassNameImpl(KRADConstants.EMPTY_STRING);
                }
            }
        }
        return rows;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
