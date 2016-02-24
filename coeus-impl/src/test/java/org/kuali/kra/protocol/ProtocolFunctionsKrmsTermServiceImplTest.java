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
package org.kuali.kra.protocol;

import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.irb.IrbJavaFunctionKrmsTermServiceImpl;
import org.kuali.kra.irb.protocol.participant.ParticipantType;
import junit.framework.Assert;
import org.junit.Test;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;

import java.util.Calendar;

public class ProtocolFunctionsKrmsTermServiceImplTest {

    @Test
    public void testHasProtocolContainsAmendRenewModule() {
        MockProtocolKrmsTermService mockProtocolKrmsTermService = new MockProtocolKrmsTermService();
        MockProtocol protocol = new MockProtocol();
        protocol.setProtocolNumber("A001");
        protocol.setProtocolAmendRenewal(new ProtocolAmendRenewal());
        ProtocolAmendRenewModule module = new ProtocolAmendRenewModule();
        module.setProtocolModuleTypeCode(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        protocol.getProtocolAmendRenewal().getModules().add(module);
        ProtocolModule protocolModule = new ProtocolModule();
        module.setProtocolModule(protocolModule);
        Assert.assertTrue(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.ADD_MODIFY_ATTACHMENTS));
        Assert.assertFalse(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.SPECIAL_REVIEW));
        protocolModule.setDescription(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        module.setProtocolModule(protocolModule);
        module.setProtocolModuleTypeCode("001");
        mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        Assert.assertTrue(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.ADD_MODIFY_ATTACHMENTS));
        protocolModule.setDescription(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        module.setProtocolModule(protocolModule);
        module.setProtocolModuleTypeCode("001");
        Assert.assertFalse(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.SPECIAL_REVIEW));
        ProtocolAmendRenewModule module2 = new ProtocolAmendRenewModule();
        module2.setProtocolModuleTypeCode("0");
        protocol.getProtocolAmendRenewal().getModules().add(module2);
        Assert.assertTrue(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, "0"));
        protocolModule = new ProtocolModule();
        protocolModule.setDescription(ProtocolModule.PROTOCOL_ORGANIZATIONS);
        module2.setProtocolModule(protocolModule);
        Assert.assertTrue(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.PROTOCOL_ORGANIZATIONS));
        Assert.assertFalse(mockProtocolKrmsTermService.hasProtocolContainsAmendRenewModule(protocol, ProtocolModule.SUBJECTS));

    }

    @Test
    public void testProtocolParticipantTypeCount() {
        IrbJavaFunctionKrmsTermServiceImpl mockProtocolKrmsTermService = new IrbJavaFunctionKrmsTermServiceImpl();
        MockProtocol protocol = new MockProtocol();
        ProtocolParticipant participant1 = new ProtocolParticipant();
        participant1.setParticipantCount(1);
        participant1.setParticipantTypeCode("1");
        ProtocolParticipant participant2 = new ProtocolParticipant();
        participant2.setParticipantCount(1);
        participant2.setParticipantTypeCode("1");
        ProtocolParticipant participant3 = new ProtocolParticipant();
        participant3.setParticipantCount(1);
        participant3.setParticipantTypeCode("2");
        ProtocolParticipant participant4 = new ProtocolParticipant();
        participant4.setParticipantCount(1);
        ParticipantType participantType = new ParticipantType();
        participantType.setDescription("1");
        participant4.setParticipantType(participantType);
        protocol.getProtocolParticipants().add(participant1);
        protocol.getProtocolParticipants().add(participant2);
        protocol.getProtocolParticipants().add(participant3);
        protocol.getProtocolParticipants().add(participant4);
        Assert.assertTrue(mockProtocolKrmsTermService.getProtocolParticipantTypeCount(protocol, "1") == 3);
        Assert.assertTrue(mockProtocolKrmsTermService.getProtocolParticipantTypeCount(protocol, "5") == 0);
        protocol.getProtocolParticipants().clear();
        Assert.assertTrue(mockProtocolKrmsTermService.getProtocolParticipantTypeCount(protocol, "1") == 0);
        ProtocolParticipant participant5 = new ProtocolParticipant();
        participant5.setParticipantCount(null);
        participant5.setParticipantTypeCode("1");
        protocol.getProtocolParticipants().add(participant5);
        Assert.assertTrue(mockProtocolKrmsTermService.getProtocolParticipantTypeCount(protocol, "1") == 0);

    }

    @Test
    public void testHasProtocolContainsSponsorType() {
        MockProtocolKrmsTermService mockProtocolKrmsTermService = new MockProtocolKrmsTermService();
        MockProtocol protocol = new MockProtocol();
        ProtocolFundingSourceMock fundingSource = new ProtocolFundingSourceMock();
        fundingSource.setFundingSourceTypeCode(FundingSourceType.SPONSOR);

        protocol.getProtocolFundingSources().add(fundingSource);
        Assert.assertTrue(mockProtocolKrmsTermService.hasProtocolContainsSponsorType(protocol, "3"));
        Assert.assertFalse(mockProtocolKrmsTermService.hasProtocolContainsSponsorType(protocol, "4"));
        MockProtocolKrmsTermServiceNullSponsor mockProtocolKrmsTermServiceNullSponsor =
                new MockProtocolKrmsTermServiceNullSponsor();
        Assert.assertFalse(mockProtocolKrmsTermServiceNullSponsor.hasProtocolContainsSponsorType(protocol, "4"));

        protocol.getProtocolFundingSources().clear();
        ProtocolFundingSourceMock fundingSource2 = new ProtocolFundingSourceMock();
        fundingSource2.setFundingSourceTypeCode(FundingSourceType.AWARD);
        protocol.getProtocolFundingSources().add(fundingSource2);
        Assert.assertFalse(mockProtocolKrmsTermService.hasProtocolContainsSponsorType(protocol, "3"));

    }

    @Test
    public void testHasBaseProtocolHasLastApprovalDate() {
        MockProtocol protocol = new MockProtocol();
        protocol.setProtocolNumber("2A");
        MockProtocolKrmsTermService mockProtocolKrmsTermService = new MockProtocolKrmsTermService();
        Assert.assertTrue(mockProtocolKrmsTermService.hasBaseProtocolHasLastApprovalDate(protocol));
        protocol = new MockProtocol();
        protocol.setProtocolNumber("1A");
        Assert.assertFalse(mockProtocolKrmsTermService.hasBaseProtocolHasLastApprovalDate(protocol));
        protocol = new MockProtocol();
        protocol.setProtocolNumber("2");
        Calendar cal = Calendar.getInstance();
        protocol.setLastApprovalDate(getDate(2014, cal.JULY, 1));
        Assert.assertTrue(mockProtocolKrmsTermService.hasBaseProtocolHasLastApprovalDate(protocol));

    }

    private java.sql.Date getDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month);
        cal.set(cal.DATE, day);
        cal.set(cal.HOUR_OF_DAY, 0);
        cal.set(cal.MINUTE, 0);
        cal.set(cal.SECOND, 0);
        cal.set(cal.MILLISECOND, 0);
        return new java.sql.Date(cal.getTime().getTime());
    }

}
