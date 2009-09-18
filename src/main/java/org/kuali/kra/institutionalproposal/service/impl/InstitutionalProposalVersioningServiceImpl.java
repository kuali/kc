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
package org.kuali.kra.institutionalproposal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InstitutionalProposalVersioningServiceImpl implements InstitutionalProposalVersioningService {
    
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    
//    public InstitutionalProposal createNewInstitutionalProposalVersion(InstitutionalProposal currentProposalVersion)
//    throws VersionException {
//        return versioningService.createNewVersion(currentProposalVersion);
//    }
    
    public IntellectualPropertyReview createNewIntellectualPropertyReviewVersion(IntellectualPropertyReview intellectualPropertyReview)
    throws VersionException {
        return versioningService.createNewVersion(intellectualPropertyReview);
    }
    
    public void updateInstitutionalProposalVersionStatus(InstitutionalProposal proposalToUpdate, VersionStatus versionStatus) {
        if (versionStatus.equals(VersionStatus.ACTIVE)) {
            archiveCurrentActiveProposal(proposalToUpdate.getProposalNumber());
        }
        proposalToUpdate.setProposalSequenceStatus(versionStatus.toString());
        businessObjectService.save(proposalToUpdate);
    }
    
    public InstitutionalProposal getPendingInstitutionalProposalVersion(String proposalNumber) {
        List<InstitutionalProposal> results = findProposalsByStatus(proposalNumber, VersionStatus.PENDING);
        if (!results.isEmpty()) {
            // There should only be one pending version at a time
            return results.get(0);
        }
        return null;
    }
    
    protected void archiveCurrentActiveProposal(String proposalNumber) {
        List<InstitutionalProposal> results = findProposalsByStatus(proposalNumber, VersionStatus.ACTIVE);
        if (!results.isEmpty()) {
            // There should only be one active version at a time
            InstitutionalProposal proposalToArchive = results.get(0);
            proposalToArchive.setProposalSequenceStatus(VersionStatus.ARCHIVED.toString());
            businessObjectService.save(proposalToArchive);
        }
    }
    
    @SuppressWarnings("unchecked")
    protected List<InstitutionalProposal> findProposalsByStatus(String proposalNumber, VersionStatus versionStatus) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put(InstitutionalProposal.PROPOSAL_NUMBER_PROPERTY_STRING, proposalNumber);
        criteria.put(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING, versionStatus.toString());
        List<InstitutionalProposal> results = new ArrayList<InstitutionalProposal>(
                businessObjectService.findMatching(InstitutionalProposal.class, criteria));
        return results;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public VersioningService getVersioningService() {
        return versioningService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    
}
