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
package org.kuali.kra.committee.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;

/**
 * 
 * This class is to create action links and inquiry url for committeeschedule lookup. Also, converts the search criteria to a couple of reference
 * object field.
 */
@SuppressWarnings("serial")
public class CommitteeScheduleLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    private static final String COMMITTEE_ID = "committeeId";
    private static final String  READ_ONLY = "readOnly";
    private TaskAuthorizationService taskAuthorizationService;

    /**
     * set up the criteria map properly to point to reference or collections.
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        Map<String, String> convertedFieldValues = new HashMap<String, String>();
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (COMMITTEE_ID.equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("committee.committeeId", entry.getValue());
            } else if ("committeeName".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("committee.committeeName", entry.getValue());
            } else if ("protocolNumber".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("protocolSubmissions.protocolNumber", entry.getValue());
            } else if ("committeeTypeCode".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("committee.committeeTypeCode", entry.getValue());
            } else {
                convertedFieldValues.put(entry.getKey(), entry.getValue());
            }
        }
        return super.getSearchResults(convertedFieldValues);
    }

    /**
     * Add edit/view action links based on user's permission
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (canModifySchedule((CommitteeSchedule) businessObject)) {
            htmlDataList.add(getLink((CommitteeSchedule) businessObject, true));
            htmlDataList.add(getLink((CommitteeSchedule) businessObject, false));
        } else if (canViewSchedule((CommitteeSchedule) businessObject)) {
            htmlDataList.add(getLink((CommitteeSchedule) businessObject, false));
        }
        return htmlDataList;
    }

    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    /*
     * This is a utility method to set up url to edit or view meeting schedule
     */
    private AnchorHtmlData getLink(CommitteeSchedule committeeSchedule, boolean isEdit) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "start");
        parameters.put("scheduleId", committeeSchedule.getId().toString());
        if (isEdit) {
            htmlData.setDisplayText("edit");
            parameters.put(READ_ONLY, "false");
        } else {
            htmlData.setDisplayText("view");
            parameters.put(READ_ONLY, "true");
        }
        String href = UrlFactory.parameterizeUrl("../meetingManagement.do", parameters);

        htmlData.setHref(href);
        return htmlData;

    }

    /**
     * This method is for committeeId that does not have inquiry created by lookup frame work.
     * 
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject,
     *      java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (COMMITTEE_ID.equals(propertyName)) {
            inqBo = ((CommitteeSchedule) bo).getCommittee();
        }
        return super.getInquiryUrl(inqBo, inqPropertyName);
    }

    private boolean canModifySchedule(CommitteeSchedule committeeSchedule) {
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_SCHEDULE, committeeSchedule.getCommittee());
        return taskAuthorizationService.isAuthorized(getUserIdentifier(), task);
    }

    private boolean canViewSchedule(CommitteeSchedule committeeSchedule) {
        CommitteeTask task = new CommitteeTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getCommittee());
        return taskAuthorizationService.isAuthorized(getUserIdentifier(), task);
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

}
