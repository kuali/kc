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
import java.util.List;
import java.util.Properties;

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
    private TaskAuthorizationService taskAuthorizationService;


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
     * Also, disable inquiry link for committee name.
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
        } else {
            return super.getInquiryUrl(inqBo, inqPropertyName);
        }
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

}
