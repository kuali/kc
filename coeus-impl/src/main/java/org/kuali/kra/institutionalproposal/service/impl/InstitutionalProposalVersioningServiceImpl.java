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
package org.kuali.kra.institutionalproposal.service.impl;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
public class InstitutionalProposalVersioningServiceImpl implements InstitutionalProposalVersioningService {
    
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;

    @Override
    public IntellectualPropertyReview createNewIntellectualPropertyReviewVersion(IntellectualPropertyReview intellectualPropertyReview)
    throws VersionException {
        return versioningService.createNewVersion(intellectualPropertyReview);
    }

    @Override
    public void updateInstitutionalProposalVersionStatus(InstitutionalProposal proposalToUpdate, VersionStatus versionStatus) {
        if (versionStatus.equals(VersionStatus.ACTIVE)) {
            archiveCurrentActiveProposal(proposalToUpdate.getProposalNumber());
            proposalToUpdate.activateFundingProposals();
        }
        proposalToUpdate.setProposalSequenceStatus(versionStatus.toString());
        businessObjectService.save(proposalToUpdate);
    }

    @Override
    public InstitutionalProposal getPendingInstitutionalProposalVersion(String proposalNumber) {
        List<InstitutionalProposal> results = findProposalsByStatus(proposalNumber, VersionStatus.PENDING);
        if (!results.isEmpty()) {
            // There should only be one pending version at a time
            return results.get(0);
        }
        return null;
    }

    @Override
    public InstitutionalProposal getActiveInstitutionalProposalVersion(String proposalNumber) {
        List<InstitutionalProposal> results = findProposalsByStatus(proposalNumber, VersionStatus.ACTIVE);
        if (!results.isEmpty()) {
            // There should only be one active version at a time
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
            proposalToArchive.setAllowUpdateTimestampToBeReset(false);
            businessObjectService.save(proposalToArchive);
        }
    }

    protected List<InstitutionalProposal> findProposalsByStatus(String proposalNumber, VersionStatus versionStatus) {
        Map<String, String> criteria = new HashMap<>();
        criteria.put(InstitutionalProposal.PROPOSAL_NUMBER_PROPERTY_STRING, proposalNumber);
        criteria.put(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING, versionStatus.toString());
        return new ArrayList<>(
                businessObjectService.findMatching(InstitutionalProposal.class, criteria));
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
