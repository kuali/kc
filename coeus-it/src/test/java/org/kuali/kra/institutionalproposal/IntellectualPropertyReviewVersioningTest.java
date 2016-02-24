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
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReviewActivity;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class IntellectualPropertyReviewVersioningTest extends KcIntegrationTestBase {
    
    private VersioningService versioningService;
    
    @Test
    public void testVersionInstitutionalProposal() throws VersionException, WorkflowException {
        
        IntellectualPropertyReview ipReview = new IntellectualPropertyReview();
        ipReview.setIpReviewId(new Long(1));
        ipReview.setProposalNumber("1");
        ipReview.setSequenceNumber(1);
        
        IntellectualPropertyReviewActivity ipReviewActivity = new IntellectualPropertyReviewActivity();
        ipReviewActivity.setProposalIpReviewActivityId(new Long(1));
        ipReviewActivity.setProposalNumber("1");
        ipReviewActivity.setSequenceNumber(1);
        
        ipReview.getIpReviewActivities().add(ipReviewActivity);
        
        IntellectualPropertyReview newIpReviewVersion = getVersioningService().createNewVersion(ipReview);
        
        Assert.assertNull(newIpReviewVersion.getIpReviewId());
        Assert.assertTrue(newIpReviewVersion.getSequenceNumber().equals(2));
        
        Assert.assertNull(newIpReviewVersion.getIpReviewActivities().get(0).getProposalIpReviewActivityId());
        Assert.assertTrue(newIpReviewVersion.getIpReviewActivities().get(0).getSequenceNumber().equals(2));
    }
    
    private VersioningService getVersioningService() {
        if (versioningService == null) {
            versioningService = KcServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }

}
