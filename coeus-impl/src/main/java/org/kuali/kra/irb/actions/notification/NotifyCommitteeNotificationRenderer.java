/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.actions.notification;

import org.drools.core.util.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Renders additional fields for the Notify IRB notification.
 */
public class NotifyCommitteeNotificationRenderer extends IRBNotificationRenderer {

    private static final long serialVersionUID = -5315801471642797815L;
    
    private String committeeName;
    private String actionComments;
    private Date actionDate;

    /**
     * Constructs a Notify IRB notification renderer.
     * @param protocol
     * @param actionComments
     */
    public NotifyCommitteeNotificationRenderer(Protocol protocol, String committeeName, String actionComments, Date actionDate) {
        super(protocol);
        this.actionDate = actionDate;
        this.actionComments = actionComments;
        this.committeeName = committeeName;
    }
    
    public String getActionComments() {
        return actionComments;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{COMMITTEE_NAME}", getSafeMessage("{COMMITTEE_NAME}", getCommitteeName()));
        params.put("{ACTION_COMMENTS}", (StringUtils.isEmpty(getActionComments()) ? "None" : getActionComments()));
        params.put("{ACTION_DATE}", (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(getActionDate()));
        return params;
    }

}