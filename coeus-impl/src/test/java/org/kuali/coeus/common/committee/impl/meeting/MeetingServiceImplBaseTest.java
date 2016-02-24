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
package org.kuali.coeus.common.committee.impl.meeting;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MeetingServiceImplBaseTest {
    public static final String ACTIVE = "ACTIVE";
    private MeetingServiceImplBase meetingServiceImplBase;
    private CommitteeMembership committeeMembership;

    @Before
    public void setUpService() {
        meetingServiceImplBase = new MeetingServiceImplBase() {
            @Override
            protected Class<? extends ScheduleAgendaBase> getScheduleAgendaBOClassHook() {
                return null;
            }

            @Override
            protected Class<? extends CommScheduleMinuteDocBase> getCommScheduleMinuteDocBOClassHook() {
                return null;
            }

            @Override
            protected Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
                return null;
            }

            @Override
            protected Class<? extends ProtocolContingencyBase> getProtocolContingencyBOClassHook() {
                return null;
            }

            @Override
            protected OtherPresentBeanBase getNewOtherPresentBeanInstanceHook() {
                return null;
            }

            @Override
            protected CommitteeScheduleMinuteBase getNewCommitteeScheduleMinuteInstanceHook() {
                return null;
            }

            @Override
            protected CommitteeScheduleAttendanceBase getNewCommitteeScheduleAttendanceInstanceHook() {
                return null;
            }
        };
    }

    @Before
    public void setUpDate() {
        committeeMembership = new CommitteeMembership();
        committeeMembership.setTermStartDate(DateUtils.newDate(2013, 0, 1));
        committeeMembership.setTermEndDate(DateUtils.newDate(2015, 11, 31));
        committeeMembership.setMembershipRoles(new ArrayList<>());
        committeeMembership.getMembershipRoles().add(createCommitteeMembershipRole(ACTIVE,
                DateUtils.newDate(2015, 0, 1), DateUtils.newDate(2015, 11, 31)));
        committeeMembership.getMembershipRoles().add(createCommitteeMembershipRole(CommitteeMembershipRole.INACTIVE_ROLE,
                DateUtils.newDate(2014, 0, 1), DateUtils.newDate(2014, 11, 31)));
        committeeMembership.getMembershipRoles().add(createCommitteeMembershipRole(ACTIVE,
                DateUtils.newDate(2013, 0, 1), DateUtils.newDate(2013, 11, 31)));

    }

    protected CommitteeMembershipRole createCommitteeMembershipRole(String memberShipRoleCode, Date startDate, Date endDate) {
        CommitteeMembershipRole previousActiveMembershipRole = new CommitteeMembershipRole();
        previousActiveMembershipRole.setMembershipRoleCode(memberShipRoleCode);
        previousActiveMembershipRole.setStartDate(startDate);
        previousActiveMembershipRole.setEndDate(endDate);
        return previousActiveMembershipRole;
    }

    @Test
    public void test_isActiveMembership_scheduledDateCurrentActiveMemberShipDates() {
        assertTrue("Membership should be active when scheduled date falls in the current active membership period",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2015, 1, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDatePreviousActiveMemberShipDates() {
        assertTrue("Membership should be active when scheduled date falls in a previous active member period",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2013, 1, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDateDuringInactiveMemberShip() {
        assertFalse("Membership should be inactive when scheduled date falls in an inactive membership period",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2014, 5, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDateBeforeTermStartDate() {
        assertFalse("Membership should be inactive when scheduled date is before the term start date",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2010, 1, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDateAfterTermEndDate() {
        assertFalse("Membership should be inactive when scheduled date is after the term end date",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2016, 1, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDateEqualsTermStartDate() {
        assertFalse("Membership should be inactive when scheduled date is on the term start date",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2013, 0, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDateEqualsTermEndDate() {
        assertFalse("Membership should be inactive when scheduled date is on the term end date",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2015, 11, 31)));
    }

    @Test
        public void test_isActiveMembership_scheduledDateEqualActiveMemberShipStartDate() {
        assertFalse("Membership should be inactive when scheduled date is on an active membership start date",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2015, 0, 1)));
    }

    @Test
    public void test_isActiveMembership_scheduledDateEqualActiveMemberShipEndDate() {
        assertFalse("Membership should be inactive when scheduled date is on an active membership end date",
                meetingServiceImplBase.isActiveMembership(committeeMembership, DateUtils.newDate(2013, 11, 31)));
    }

}
