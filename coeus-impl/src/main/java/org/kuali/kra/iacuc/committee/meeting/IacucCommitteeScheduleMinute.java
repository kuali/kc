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
