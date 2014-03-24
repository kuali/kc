/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.service;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;

public interface InstitutionalProposalVersioningService {
    
    // InstitutionalProposal createNewInstitutionalProposalVersion(InstitutionalProposal currentProposalVersion) throws VersionException;
    
    IntellectualPropertyReview createNewIntellectualPropertyReviewVersion(IntellectualPropertyReview intellectualPropertyReview) throws VersionException;

    void updateInstitutionalProposalVersionStatus(InstitutionalProposal proposalToUpdate, VersionStatus versionStatus);
    
    InstitutionalProposal getPendingInstitutionalProposalVersion(String proposalNumber);
    
    InstitutionalProposal getActiveInstitutionalProposalVersion(String proposalNumber);
}
