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
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This interface defines public services made available by the Institutional Proposal module.
 */
public interface InstitutionalProposalService {
    
    /**
     * Creates a new Institutional Proposal with data copied from the given development proposal and budget.
     * 
     * @param developmentProposal DevelopmentProposal
     * @param budget Budget
     * @return String The new proposal number
     */
    String createInstitutionalProposal(DevelopmentProposal developmentProposal, Budget budget);
    
    /**
     * Creates a new version of the Institutional Proposal corresponding to the given proposal number, 
     * with data copied from the given development proposal and budget.
     * 
     * @param proposalNumber String
     * @param developmentProposal DevelopmentProposal
     * @param budget Budget
     * @return String The new version number
     */
    String createInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget);
    
    /**
     * Return an Institutional Proposal, if one exists.
     * 
     * @param proposalId String
     * @return InstitutionalProposal, or null if none is found.
     */
    InstitutionalProposal getInstitutionalProposal(String proposalId);
    
    /**
     * Return the PENDING version of an Institutional Proposal, if one exists.
     * Note, PENDING here refers to the Version Status, NOT the Proposal Status of the Institutional Proposal.
     * 
     * @param proposalNumber String
     * @return InstitutionalProposal, or null if a PENDING version is not found.
     * @see org.kuali.coeus.common.framework.version.VersionStatus
     */
    InstitutionalProposal getPendingInstitutionalProposalVersion(String proposalNumber);
    
    /**
     * Return the ACTIVE version of an Institutional Proposal, if one exists.
     * Note, ACTIVE here refers to the Version Status, NOT the Proposal Status of the Institutional Proposal.
     * 
     * @param proposalNumber String
     * @return InstitutionalProposal, or null if a ACTIVE version is not found.
     * @see org.kuali.coeus.common.framework.version.VersionStatus
     */
    InstitutionalProposal getActiveInstitutionalProposalVersion(String proposalNumber);
    
    /**
     * Designate one or more Institutional Proposals as Funded by an Award.
     * 
     * If the given Proposal has a Proposal Status of Pending, a new Final version of the Proposal
     * will be created with a Proposal Status of Funded.
     * 
     * If the current Active version is already Funded, it will be left alone.
     * 
     * @param proposalNumbers The proposals to update.
     * @return List<InstitutionalProposal> The new Funded versions.
     */
    List<InstitutionalProposal> fundInstitutionalProposals(Set<String> proposalNumbers);
    
    /**
     * Designate the given Proposals as no longer funded by the given Award.
     * 
     * If the given Award was the only funding Award for a Proposal, a new Final version of the Proposal
     * will be created with a Proposal Status of Pending.
     * 
     * If the Proposal has other funding Awards, it will be left alone.  It will also be left alone
     * if it is funded by the active version of the given award number (this is a functional requirement).
     * 
     * @param proposalNumbers The proposals to update.
     * @param awardNumber The Award that is de-funding the proposal.
     * @param awardSequence The sequence number of the Award.
     * @return List<InstitutionalProposal> The new Pending versions.
     */
    List<InstitutionalProposal> defundInstitutionalProposals(Set<String> proposalNumbers, String awardNumber, Integer awardSequence);
    
    /**
     * Get all versions of a proposal.
     * @param proposalNumber
     * @return
     */
    List<InstitutionalProposal> getProposalsForProposalNumber(String proposalNumber);
    
    /**
     * Return all Development Proposals linked to any version of the institutional proposal identified by proposalNumber.
     * @param proposalNumber
     * @return
     */
    List<DevelopmentProposal> getAllLinkedDevelopmentProposals(String proposalNumber); 
    
    String getNextInstitutionalProposalNumber();
    
    /**
     * Return a list of valid proposal status codes for award funding as defined by the 'validFundingProposalStatusCodes' parm.
     * @return
     */
    Collection<String> getValidFundingProposalStatusCodes();
    
    /**
     * This method is to create a new version of an existing Institute Proposal
     * @param currentInstitutionalProposal
     * @param currentInstitutionalProposalDocument
     * @return
     * @throws VersionException
     * @throws WorkflowException
     * @throws IOException
     */
    InstitutionalProposalDocument createAndSaveNewVersion(InstitutionalProposal currentInstitutionalProposal, InstitutionalProposalDocument currentInstitutionalProposalDocument) throws VersionException, 
    WorkflowException, IOException;

    /**
     * Retrieves the proposal id for the given award
     * @param award the award to find the proposal id for
     * @return the proposal id, or null if nothing can be found
     */
    public Long getProposalId(Award award);
}
