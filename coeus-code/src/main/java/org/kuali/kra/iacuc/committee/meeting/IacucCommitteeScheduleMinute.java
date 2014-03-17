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
package org.kuali.kra.iacuc.committee.meeting;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.rice.kim.api.role.RoleService;

import java.util.Collection;

public class IacucCommitteeScheduleMinute extends CommitteeScheduleMinuteBase<IacucCommitteeScheduleMinute, IacucCommitteeSchedule> {


    private static final long serialVersionUID = 1673628066653997531L;

    public IacucCommitteeScheduleMinute(String minuteEntryTypeCode) {
        super(minuteEntryTypeCode);
    }

    public IacucCommitteeScheduleMinute() {
        super();
    }

    @Override
    protected boolean isAdministrator(String principalId) {
        RoleService roleService = KcServiceLocator.getService(RoleService.class);
        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IACUC_ADMINISTRATOR, null);
        return ids.contains(principalId);
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewBOClassHook() {
        return IacucProtocolOnlineReview.class;
    }

}
