/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 */
public interface NarrativeService {
    public void addNarrative(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative);
    public void deleteProposalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete);
    public void populatePersonNameForNarrativeUserRights(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative);
    public void replaceAttachment(Narrative narrative);
    public void populateNarrativeRightsForLoggedinUser(ProposalDevelopmentDocument proposaldevelopmentDocument);
    public void deleteInstitutionalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete);
    public void populateDummyUserRoles(ProposalDevelopmentDocument proposalDevelopmentDocument);
    public void addInstituteAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative);
    
    /**
     * Delete a person from all of the narratives.  When a user is removed from the Permissions
     * page, that user must also be removed from the narratives.
     * @param username the name of the user
     * @param proposalDevelopmentDocument the Proposal Development Document
     */
    public void deletePerson(String username, ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    /**
     * Re-adjust the narrative rights for a user.  If the user has lost some
     * permissions regarding narratives, his/her narrative rights may need to
     * be down-graded.
     * @param username the name of the user
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @param roleNames the roles the user is in
     */
    public void readjustRights(String username, ProposalDevelopmentDocument proposalDevelopmentDocument, List<String> roleNames);
    
    /**
     * Add a person to all of the Narratives in a proposal.  When a new user is granted
     * access to a proposal via the Permissions page, that user must be added to all of
     * the narratives with the appropriate default narrative right based upon their permissions.
     * @param username the name of the user
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @param roleName the initial proposal role of the user
     */
    public void addPerson(String username, ProposalDevelopmentDocument proposalDevelopmentDocument, String roleName);
}
