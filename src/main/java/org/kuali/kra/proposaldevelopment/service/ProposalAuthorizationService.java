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

import org.kuali.kra.bo.Person;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * The Proposal Authorization Service handles access to Proposal Development Documents.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface ProposalAuthorizationService {

    /**
     * Get the list of usernames of people who have the given role with respect to
     * the given Proposal Development Document.
     * @param doc the Proposal Development Document
     * @param roleName the name of the Role
     * @return the list of usernames 
     */
    public List<String> getUserNames(ProposalDevelopmentDocument doc, String roleName);
    
    /**
     * Add a user to a role within a Proposal Development Document.  Standard roles for
     * Proposal Development are Aggregator, Narrative Writer, Budget Creator, and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param doc the Proposal Development Document
     */
    public void addRole(String username, String roleName, ProposalDevelopmentDocument doc);
    
    /**
     * Remove a user from a role within a Proposal Development Document. Standard roles for
     * Proposal Development are Aggregator, Narrative Writer, Budget Creator, and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param doc the Proposal Development Document
     */
    public void removeRole(String username, String roleName, ProposalDevelopmentDocument doc);
    
    /**
     * Does the user have the given permission for the given Proposal Development Document?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, ProposalDevelopmentDocument doc, String permissionName);

    /**
     * Does the user have the given role for the given Proposal Development Document?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param roleName the name of the Role
     * @return true if the user has the role; otherwise false
     */
    public boolean hasRole(String username, ProposalDevelopmentDocument doc, String roleName);
    
    /**
     * Get the roles for this user in the Proposal Development Document.
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @return the list of role names this person has in the proposal
     */
    public List<String> getRoles(String username, ProposalDevelopmentDocument doc);
    
    /**
     * Get the list of persons in a specific role for a given proposal.
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return the list of persons in the role for the proposal
     */
    public List<Person> getPersonsInRole(ProposalDevelopmentDocument doc, String roleName);
}