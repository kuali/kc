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
package org.kuali.coeus.common.committee.impl.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.kim.bo.KcKimAttributes;


public class CommitteeNotificationRoleQualifierServiceImpl implements CommonCommitteeNotificationRoleQualifierService {

    private CommitteeBase committee;
    private CommitteeScheduleBase committeeSchedule;
    
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

    public CommitteeBase getCommittee() {
        return committee;
    }

    public void setCommittee(CommitteeBase committee) {
        this.committee = committee;
    }

    public CommitteeScheduleBase getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeScheduleBase committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

}
