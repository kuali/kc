/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.InstitutionalProposalIpReviewActivity;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;

public class IntellectualPropertyReviewVersioningTest {
    
    private VersioningService versioningService;
    
    @Test
    public void testVersionInstitutionalProposal() throws VersionException, WorkflowException {
        
        IntellectualPropertyReview ipReview = new IntellectualPropertyReview();
        ipReview.setProposalId(new Long(1));
        ipReview.setProposalNumber("1");
        ipReview.setSequenceNumber(1);
        
        InstitutionalProposalIpReviewActivity ipReviewActivity = new InstitutionalProposalIpReviewActivity();
        ipReviewActivity.setProposalIpReviewActivityId(new Long(1));
        ipReviewActivity.setProposalNumber("1");
        ipReviewActivity.setSequenceNumber(1);
        
        ipReview.getIpReviewActivities().add(ipReviewActivity);
        
        IntellectualPropertyReview newIpReviewVersion = getVersioningService().createNewVersion(ipReview);
        
        Assert.assertNull(newIpReviewVersion.getProposalId());
        Assert.assertTrue(newIpReviewVersion.getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpReviewVersion.getIpReviewActivities().get(0).getProposalIpReviewActivityId());
        System.out.println(newIpReviewVersion.getIpReviewActivities().get(0).getSequenceNumber());
        Assert.assertTrue(newIpReviewVersion.getIpReviewActivities().get(0).getSequenceNumber().equals(2));
    }
    
    private VersioningService getVersioningService() {
        if (versioningService == null) {
            versioningService = KraServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }

}
