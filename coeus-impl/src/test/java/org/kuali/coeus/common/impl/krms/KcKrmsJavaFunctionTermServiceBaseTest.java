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
package org.kuali.coeus.common.impl.krms;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

import java.util.ArrayList;
import java.util.List;

public class KcKrmsJavaFunctionTermServiceBaseTest {

    public static final String NIH_SPONSOR_CODE = "000340";
    public static final String TEST_SPONSOR_CODE = "000100";

    class MockIP extends InstitutionalProposal {
        @Override
        protected void calculateFiscalMonthAndYearFields() {

        }
    }

    class MockAward extends Award {
        String versionNameFieldValue = "3";
        @Override
        public String getVersionNameFieldValue() {
            return versionNameFieldValue;
        }
        public void setVersionNameFieldValue(String value) {
            this.versionNameFieldValue = value;
        }

    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    public void testHasPropertyChangedThisVersion() {
        class MockKrmsBase extends KcKrmsJavaFunctionTermServiceBase {
            @Override
            protected List<VersionHistory> getVersionHistories(SequenceOwner<?> currentVersion, String versionNumber) {
                return getVersionHistoriesSet1();
            }
        }

        MockAward award = new MockAward();
        award.setSequenceNumber(4);
        award.setSponsorCode("000340");
        MockKrmsBase krmsTermService = new MockKrmsBase();
        Assert.assertTrue(krmsTermService.hasPropertyChangedThisVersion(award, "sponsorCode"));
        exception.expect(RuntimeException.class);
        Assert.assertFalse(krmsTermService.hasPropertyChangedThisVersion(award, "sponso"));
        award.setPreAwardAuthorizedAmount(ScaleTwoDecimal.ZERO);
        Assert.assertTrue(krmsTermService.hasPropertyChangedThisVersion(award, "preAwardAuthorizedAmount"));
        award.setPreAwardAuthorizedAmount(null);
        Assert.assertTrue(krmsTermService.hasPropertyChangedThisVersion(award, "preAwardAuthorizedAmount"));
        award.setPreAwardInstitutionalAuthorizedAmount(ScaleTwoDecimal.ONE_HUNDRED);
        Assert.assertTrue(krmsTermService.hasPropertyChangedThisVersion(award, "preAwardInstitutionalAuthorizedAmount"));
        award.setPreAwardInstitutionalAuthorizedAmount(null);
        Assert.assertFalse(krmsTermService.hasPropertyChangedThisVersion(award, "preAwardInstitutionalAuthorizedAmount"));
        Assert.assertFalse(krmsTermService.hasPropertyChangedThisVersion(null, null));

        class MockKrmsBase1 extends KcKrmsJavaFunctionTermServiceBase {
            @Override
            protected SequenceOwner<?> getLastActiveVersion(SequenceOwner<?> currentVersion) {
                return null;
            }
        }
        MockKrmsBase1 krmsTermService2 = new MockKrmsBase1();
        Assert.assertFalse(krmsTermService2.hasPropertyChangedThisVersion(award, "preAwardInstitutionalAuthorizedAmount"));

    }

    private List<VersionHistory> getVersionHistoriesSet1() {
        List<VersionHistory> versionHistories = new ArrayList<>();
        VersionHistory versionHistory1 = new VersionHistory();
        versionHistory1.setSequenceOwnerSequenceNumber(1);
        versionHistory1.setStatus(VersionStatus.ARCHIVED );
        MockAward award1 = new MockAward();
        award1.setTitle("Award1");
        award1.setSponsorCode("000340");
        award1.setPreAwardAuthorizedAmount(ScaleTwoDecimal.ONE_HUNDRED);
        versionHistory1.setSequenceOwner(award1);

        VersionHistory versionHistory2 = new VersionHistory();
        versionHistory2.setSequenceOwnerSequenceNumber(2);
        MockAward award2 = new MockAward();
        award2.setTitle("Award2");
        award2.setSponsorCode("000001");
        versionHistory2.setSequenceOwner(award2);
        versionHistory2.setStatus(VersionStatus.CANCELED);

        VersionHistory versionHistory3 = new VersionHistory();
        versionHistory3.setSequenceOwnerSequenceNumber(3);
        MockAward award3 = new MockAward();
        award3.setTitle("Award3");
        award3.setSponsorCode("000001");
        award3.setPreAwardAuthorizedAmount(ScaleTwoDecimal.ONE_HUNDRED);
        award3.setPreAwardInstitutionalAuthorizedAmount(null);
        versionHistory3.setSequenceOwner(award3);
        versionHistory3.setStatus(VersionStatus.ACTIVE);

        versionHistories.add(versionHistory1);
        versionHistories.add(versionHistory2);
        versionHistories.add(versionHistory3);
        return versionHistories;
    }

    @Test
    public void testCheckPropertyValueForAnyPreviousVersion() {

        class MockKrmsBase extends KcKrmsJavaFunctionTermServiceBase {
            @Override
            protected List<VersionHistory> getVersionHistories(SequenceOwner<?> currentVersion, String versionNumber) {
                List<VersionHistory> versionHistories = new ArrayList<>();
                VersionHistory versionHistory1 = new VersionHistory();
                versionHistory1.setSequenceOwnerSequenceNumber(1);
                versionHistory1.setStatus(VersionStatus.ARCHIVED );
                MockAward award1 = new MockAward();
                award1.setSponsorCode("000340");
                award1.setPreAwardAuthorizedAmount(ScaleTwoDecimal.ONE_HUNDRED);
                versionHistory1.setSequenceOwner(award1);
                VersionHistory versionHistory2 = new VersionHistory();
                versionHistory2.setSequenceOwnerSequenceNumber(2);
                MockAward award2 = new MockAward();
                award2.setSponsorCode("000001");
                versionHistory2.setSequenceOwner(award2);
                versionHistory2.setStatus(VersionStatus.CANCELED);

                versionHistories.add(versionHistory1);
                versionHistories.add(versionHistory2);
                return versionHistories;
            }
        }

        MockAward award = new MockAward();
        award.setSequenceNumber(3);
        MockKrmsBase krmsTermService = new MockKrmsBase();
        Assert.assertFalse(krmsTermService.checkPropertyValueForAnyPreviousVersion(award, "sponsorCode", "000001"));
        Assert.assertTrue(krmsTermService.checkPropertyValueForAnyPreviousVersion(award, "sponsorCode", "000340"));

        Assert.assertFalse(krmsTermService.checkPropertyValueForAnyPreviousVersion(award, "sponso", "000340"));
        Assert.assertTrue(krmsTermService.checkPropertyValueForAnyPreviousVersion(award, "preAwardAuthorizedAmount", "100.000"));
        award.setSequenceNumber(1);
        Assert.assertFalse(krmsTermService.checkPropertyValueForAnyPreviousVersion(award, "sponsorCode", "000340"));
        award.setSequenceNumber(3);
        award.setVersionNameFieldValue(null);
        Assert.assertFalse(krmsTermService.checkPropertyValueForAnyPreviousVersion(award, "sponsorCode", "000340"));

    }

    @Test
    public void testGetLastActiveVersion() {
        class MockKrmsBase extends KcKrmsJavaFunctionTermServiceBase {
            @Override
            protected List<VersionHistory> getVersionHistories(SequenceOwner<?> currentVersion, String versionNumber) {
                List<VersionHistory> versionHistories = new ArrayList<>();
                VersionHistory versionHistory1 = new VersionHistory();
                versionHistory1.setSequenceOwnerSequenceNumber(1);
                versionHistory1.setStatus(VersionStatus.ARCHIVED );
                MockAward award1 = new MockAward();
                award1.setTitle("Award1");
                award1.setSponsorCode("000340");
                award1.setPreAwardAuthorizedAmount(ScaleTwoDecimal.ONE_HUNDRED);
                versionHistory1.setSequenceOwner(award1);

                VersionHistory versionHistory2 = new VersionHistory();
                versionHistory2.setSequenceOwnerSequenceNumber(2);
                MockAward award2 = new MockAward();
                award2.setTitle("Award2");
                award2.setSponsorCode("000001");
                versionHistory2.setSequenceOwner(award2);
                versionHistory2.setStatus(VersionStatus.CANCELED);

                VersionHistory versionHistory3 = new VersionHistory();
                versionHistory3.setSequenceOwnerSequenceNumber(3);
                MockAward award3 = new MockAward();
                award3.setTitle("Award3");
                award3.setSponsorCode("000001");
                versionHistory3.setSequenceOwner(award3);
                versionHistory3.setStatus(VersionStatus.ACTIVE);

                versionHistories.add(versionHistory1);
                versionHistories.add(versionHistory2);
                versionHistories.add(versionHistory3);
                return versionHistories;
            }
        }

        MockKrmsBase krmsTermService = new MockKrmsBase();
        MockAward award = new MockAward();
        award.setSequenceNumber(4);
        Award lastActiveVersion = (Award) krmsTermService.getLastActiveVersion(award);
        Assert.assertTrue(lastActiveVersion.getTitle().equalsIgnoreCase("Award3"));
    }

    @Test
    public void testDoSponsorAndPrimeSponsorMatch() {

        class MockKrmsBase extends KcKrmsJavaFunctionTermServiceBase {
            @Override
            protected List<VersionHistory> getVersionHistories(SequenceOwner<?> currentVersion, String versionNumber) {
                List<VersionHistory> versionHistories = new ArrayList<>();
                VersionHistory versionHistory1 = new VersionHistory();
                versionHistory1.setSequenceOwnerSequenceNumber(1);
                versionHistory1.setStatus(VersionStatus.ARCHIVED );
                MockAward award1 = new MockAward();
                award1.setSponsorCode("000340");
                award1.setPreAwardAuthorizedAmount(ScaleTwoDecimal.ONE_HUNDRED);
                versionHistory1.setSequenceOwner(award1);
                VersionHistory versionHistory2 = new VersionHistory();
                versionHistory2.setSequenceOwnerSequenceNumber(2);
                MockAward award2 = new MockAward();
                award2.setSponsorCode("000001");
                versionHistory2.setSequenceOwner(award2);
                versionHistory2.setStatus(VersionStatus.CANCELED);

                versionHistories.add(versionHistory1);
                versionHistories.add(versionHistory2);
                return versionHistories;
            }
        }

        Award award = new Award();
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorCode(NIH_SPONSOR_CODE);
        award.setSponsor(sponsor);
        award.setPrimeSponsor(sponsor);
        award.setPrimeSponsorCode(NIH_SPONSOR_CODE);

        MockKrmsBase termService = new MockKrmsBase();
        boolean result = termService.doSponsorAndPrimeSponsorMatch(award);
        Assert.assertTrue(result);

        award.setPrimeSponsorCode(NIH_SPONSOR_CODE);
        award.setSponsorCode(TEST_SPONSOR_CODE);
        result = termService.doSponsorAndPrimeSponsorMatch(award);
        Assert.assertFalse(result);

        award.setPrimeSponsorCode(NIH_SPONSOR_CODE);
        award.setSponsorCode(null);
        result = termService.doSponsorAndPrimeSponsorMatch(award);
        Assert.assertFalse(result);

        DevelopmentProposal developmentProposal = new DevelopmentProposal();
        sponsor = new Sponsor();
        sponsor.setSponsorCode(NIH_SPONSOR_CODE);
        developmentProposal.setSponsor(sponsor);
        developmentProposal.setPrimeSponsor(sponsor);
        developmentProposal.setSponsorCode(NIH_SPONSOR_CODE);
        developmentProposal.setPrimeSponsorCode(NIH_SPONSOR_CODE);

        result = termService.doSponsorAndPrimeSponsorMatch(developmentProposal);
        Assert.assertTrue(result);

        developmentProposal.setPrimeSponsorCode(NIH_SPONSOR_CODE);
        developmentProposal.setSponsorCode(TEST_SPONSOR_CODE);
        result = termService.doSponsorAndPrimeSponsorMatch(developmentProposal);
        Assert.assertFalse(result);

        developmentProposal.setPrimeSponsorCode(NIH_SPONSOR_CODE);
        developmentProposal.setSponsorCode(null);
        result = termService.doSponsorAndPrimeSponsorMatch(developmentProposal);
        Assert.assertFalse(result);

        MockIP ip = new MockIP();
        ip.setSponsorCode(NIH_SPONSOR_CODE);
        ip.setPrimeSponsorCode(NIH_SPONSOR_CODE);

        result = termService.doSponsorAndPrimeSponsorMatch(ip);
        Assert.assertTrue(result);

        ip.setPrimeSponsorCode(NIH_SPONSOR_CODE);
        ip.setSponsorCode(TEST_SPONSOR_CODE);
        result = termService.doSponsorAndPrimeSponsorMatch(ip);
        Assert.assertFalse(result);

        ip.setPrimeSponsorCode(NIH_SPONSOR_CODE);
        ip.setSponsorCode(null);
        result = termService.doSponsorAndPrimeSponsorMatch(ip);
        Assert.assertFalse(result);
    }
}
