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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import java.util.List;


public interface LegacyNarrativeService {
    public void populatePersonNameForNarrativeUserRights(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative);
    public void populateNarrativeRightsForLoggedinUser(ProposalDevelopmentDocument proposaldevelopmentDocument);
    
    /**
     * Delete a person from all of the narratives.  When a user is removed from the Permissions
     * page, that user must also be removed from the narratives.
     * @param userId the id of the user
     * @param proposalDevelopmentDocument the Proposal Development Document
     */
    public void deletePerson(String userId, ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    /**
     * Re-adjust the narrative rights for a user.  If the user has lost some
     * permissions regarding narratives, his/her narrative rights may need to
     * be down-graded.
     * @param userId the id of the user
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @param roleNames the roles the user is in
     */
    public void readjustRights(String userId, ProposalDevelopmentDocument proposalDevelopmentDocument, List<String> roleNames);
    
    /**
     * Add a person to all of the Narratives in a proposal.  When a new user is granted
     * access to a proposal via the Permissions page, that user must be added to all of
     * the narratives with the appropriate default narrative right based upon their permissions.
     * @param userId the id of the user
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @param roleNames the initial proposal role of the user
     */
    public void addPerson(String userId, ProposalDevelopmentDocument proposalDevelopmentDocument, List<String> roleNames);
    
    public void prepareNarrative(ProposalDevelopmentDocument document, Narrative narrative);

    public boolean doesProposalHaveNarrativeType(DevelopmentProposal proposal, NarrativeType narrativeType);

    public Integer getNextModuleNumber(ProposalDevelopmentDocument proposaldevelopmentDocument);
    
    public Integer getNextModuleSequenceNumber(ProposalDevelopmentDocument proposaldevelopmentDocument);

    }
