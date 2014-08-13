/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
