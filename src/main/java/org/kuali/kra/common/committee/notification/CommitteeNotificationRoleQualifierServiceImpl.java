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
package org.kuali.kra.common.committee.notification;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.kim.bo.KcKimAttributes;


public class CommitteeNotificationRoleQualifierServiceImpl implements CommitteeNotificationRoleQualifierService {

    private CommonCommittee committee;
    private CommonCommitteeSchedule committeeSchedule;
    public static final String COMMON_COMMITTEE_NOTIFICATION_ROLE_QUALIFER_SERVICE_SPRING_NAME = "commonCommitteeNotificationRoleQualifierService";
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService#getRoleQualifierValue(org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier)
     */
    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String qName = qualifier.getQualifier();
        String qVal = null;
        if (StringUtils.equalsIgnoreCase(qName, KcKimAttributes.COMMITTEE)) {
            qVal = committee.getCommitteeId();
        }
        else if (StringUtils.equalsIgnoreCase(qName, KcKimAttributes.COMMITTEESCHEDULE)) {
            qVal = committeeSchedule.getScheduleId();
        }
        return qVal;
    }

    public CommonCommittee getCommittee() {
        return committee;
    }

    public void setCommittee(CommonCommittee committee) {
        this.committee = committee;
    }

    public CommonCommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommonCommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

}
