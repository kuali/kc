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
package org.kuali.kra.meeting;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.rice.kim.api.role.RoleService;

import java.util.Collection;

/**
 * 
 * This is BO class for committee schedule minute. 
 */
public class CommitteeScheduleMinute extends CommitteeScheduleMinuteBase<CommitteeScheduleMinute, CommitteeSchedule> {

    private static final long serialVersionUID = -2294619582524055884L;
    
    @SkipVersioning
    private transient String committeeIdFromSubmission;

    @Override
    protected boolean isAdministrator(String principalId) {
        RoleService roleService = KcServiceLocator.getService(RoleService.class);
        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR, null);
        return ids.contains(principalId);
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewBOClassHook() {
        return ProtocolOnlineReview.class;
    }
    
    public void setCommitteeIdFromSubmission(ProtocolSubmission protocolSubmission) {
        this.committeeIdFromSubmission = protocolSubmission.getCommitteeId();
    }

    public String getCommitteeIdFromSubmission() {
        return committeeIdFromSubmission;
    }
    
    public CommitteeScheduleMinute(String minuteEntryTypeCode) {
        super(minuteEntryTypeCode);
    }

    public CommitteeScheduleMinute() {
        super();
    }

}
