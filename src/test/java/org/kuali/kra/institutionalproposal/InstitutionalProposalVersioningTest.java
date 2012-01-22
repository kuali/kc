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
package org.kuali.kra.institutionalproposal;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContact;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class InstitutionalProposalVersioningTest extends KcUnitTestBase {
    
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
            versioningService = KraServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }

}
