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
package org.kuali.kra.institutionalproposal;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContact;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class InstitutionalProposalVersioningTest extends KcIntegrationTestBase {
    
    private VersioningService versioningService;
    
    @Test
    public void testVersionInstitutionalProposal() throws VersionException, WorkflowException {
        
        InstitutionalProposal institutionalProposal = new InstitutionalProposal();
        institutionalProposal.setProposalId(new Long(1));
        institutionalProposal.setSubcontractFlag(false);
        Assert.assertTrue(institutionalProposal.getSequenceNumber().equals(1));
        
        InstitutionalProposalCostShare ipCostShare = new InstitutionalProposalCostShare();
        ipCostShare.setProposalCostShareId(new Long(1));
        institutionalProposal.add(ipCostShare);
        Assert.assertTrue(institutionalProposal.getInstitutionalProposalCostShares().get(0).getSequenceNumber().equals(1));
        
        InstitutionalProposalUnrecoveredFandA ipUnrecoveredFandA = new InstitutionalProposalUnrecoveredFandA();
        ipUnrecoveredFandA.setProposalUnrecoveredFandAId(new Long(1));
        institutionalProposal.add(ipUnrecoveredFandA);
        Assert.assertTrue(institutionalProposal.getInstitutionalProposalUnrecoveredFandAs().get(0).getSequenceNumber().equals(1));
        
        InstitutionalProposalCustomData ipCustomData = new InstitutionalProposalCustomData();
        ipCustomData.setCustomAttributeId(new Long(1));
        ipCustomData.setInstitutionalProposal(institutionalProposal);
        institutionalProposal.getInstitutionalProposalCustomDataList().add(ipCustomData);
        Assert.assertTrue(institutionalProposal.getInstitutionalProposalCustomDataList().get(0).getSequenceNumber().equals(1));
        
        InstitutionalProposalNotepad ipNotepad = new InstitutionalProposalNotepad();
        ipNotepad.setProposalNotepadId(new Long(1));
        institutionalProposal.add(ipNotepad);
        Assert.assertTrue(institutionalProposal.getInstitutionalProposalNotepads().get(0).getSequenceNumber().equals(1));
        
        ScienceKeyword scienceKeyword = new ScienceKeyword();
        institutionalProposal.addKeyword(scienceKeyword);
        institutionalProposal.getKeywords().get(0).setProposalScienceKeywordId(new Long(1));
        Assert.assertTrue(institutionalProposal.getInstitutionalProposalScienceKeywords().get(0).getSequenceNumber().equals(1));
        
        InstitutionalProposalUnitContact ipUnitContact = new InstitutionalProposalUnitContact();
        ipUnitContact.setInstitutionalProposalContactId(new Long(1));
        ipUnitContact.setInstitutionalProposal(institutionalProposal);
        institutionalProposal.getInstitutionalProposalUnitContacts().add(ipUnitContact);
        Assert.assertTrue(institutionalProposal.getInstitutionalProposalUnitContacts().get(0).getSequenceNumber().equals(1));
        
        InstitutionalProposalSpecialReview ipSpecialReview = new InstitutionalProposalSpecialReview();
        ipSpecialReview.setProposalSpecialReviewId(new Long(1));
        InstitutionalProposalSpecialReviewExemption ipSpecialReviewExemption = ipSpecialReview.createSpecialReviewExemption("1");
        ipSpecialReviewExemption.setProposalSpecialReviewExemptionId(new Long(1));
        ipSpecialReview.getSpecialReviewExemptions().add(ipSpecialReviewExemption);
        institutionalProposal.addSpecialReview(ipSpecialReview);
        Assert.assertTrue(institutionalProposal.getSpecialReviews().get(0).getSequenceNumber().equals(1));
        
        InstitutionalProposal newIpVersion = getVersioningService().createNewVersion(institutionalProposal);
        
        Assert.assertNull(newIpVersion.getProposalId());
        Assert.assertTrue(newIpVersion.getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalCostShares().get(0).getProposalCostShareId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalCostShares().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalCostShares().get(0).getProposalCostShareId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalCostShares().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalUnrecoveredFandAs().get(0).getProposalUnrecoveredFandAId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalUnrecoveredFandAs().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalCustomDataList().get(0).getProposalCustomDataId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalCustomDataList().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalNotepads().get(0).getProposalNotepadId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalNotepads().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalNotepads().get(0).getProposalNotepadId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalNotepads().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getInstitutionalProposalNotepads().get(0).getProposalNotepadId());
        Assert.assertTrue(newIpVersion.getInstitutionalProposalNotepads().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getKeywords().get(0).getProposalScienceKeywordId());
        Assert.assertTrue(newIpVersion.getKeywords().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getSpecialReviews().get(0).getProposalSpecialReviewId());
        Assert.assertTrue(newIpVersion.getSpecialReviews().get(0).getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpVersion.getSpecialReviews().get(0).getProposalSpecialReviewId());
        Assert.assertNull(newIpVersion.getSpecialReviews().get(0).getSpecialReviewExemptions().get(0).getProposalSpecialReviewExemptionId());
        
    }
    
    private VersioningService getVersioningService() {
        if (versioningService == null) {
            versioningService = KcServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }

}
