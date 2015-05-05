/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.docperm.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Test the business rules for Proposal Permissions.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentPermissionsRuleTest extends ProposalDevelopmentRuleTestBase {

    private ProposalDevelopmentPermissionsRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentPermissionsRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a valid addition of a user.
     *  
     * @throws Exception
     */
    @Test
    public void testAddOK() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        ProposalUserRoles proposalUser = createProposalUserRoles("majors");
        assertTrue(rule.processAddProposalUserBusinessRules(document, proposalUserRolesList, proposalUser));
    }
    
    /**
     * Test adding an unknown/invalid user.
     *  
     * @throws Exception
     */
    @Test
    public void testAddInvalidUser() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        ProposalUserRoles proposalUser = createProposalUserRoles("xxx");
        assertFalse(rule.processAddProposalUserBusinessRules(document, proposalUserRolesList, proposalUser));
        assertError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".username", KeyConstants.ERROR_UNKNOWN_USERNAME);
    }
    
    /**
     * Test adding a duplicate user.
     *  
     * @throws Exception
     */
    @Test
    public void testAddDuplicate() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        ProposalUserRoles proposalUser = createProposalUserRoles("quickstart");
        assertFalse(rule.processAddProposalUserBusinessRules(document, proposalUserRolesList, proposalUser));
        assertError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".username", KeyConstants.ERROR_DUPLICATE_PROPOSAL_USER);
    }
    
    /**
     * Test a valid deletion.
     *  
     * @throws Exception
     */
    @Test
    public void testDeleteOK() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        assertTrue(rule.processDeleteProposalUserBusinessRules(document, proposalUserRolesList, 1));
    }
    
    /**
     * Test a deletion of a user who is the last aggregator.  
     *  
     * @throws Exception
     */
    @Test
    public void testDeleteLastAggregator() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        assertFalse(rule.processDeleteProposalUserBusinessRules(document, proposalUserRolesList, 0));
        assertError(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_LAST_AGGREGATOR);
    }
    
    /**
     * Test a valid edit.
     *  
     * @throws Exception
     */
    @Test
    public void testEditOK() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        ProposalUserRoles editRoles = createProposalUserRoles("chew");
        editRoles.addRoleName(RoleConstants.AGGREGATOR_DOCUMENT_LEVEL);
        assertTrue(rule.processEditProposalUserRolesBusinessRules(document, proposalUserRolesList, editRoles));
    }

    /**
     * Try removing the Aggregator role from the last user to have that role.
     *  
     * @throws Exception
     */
    @Test
    public void testEditLastAggregator() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        List<ProposalUserRoles> proposalUserRolesList = getProposalUserRoles();
        ProposalUserRoles editRoles = proposalUserRolesList.get(0);
        editRoles.getRoleNames().clear();
        editRoles.getRoleNames().add("Viewer");
        assertFalse(rule.processEditProposalUserRolesBusinessRules(document, proposalUserRolesList, editRoles));
        assertError(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_LAST_AGGREGATOR);
    }
    
    /**
     * Create a list of proposal users.
     * @return
     */
    private List<ProposalUserRoles> getProposalUserRoles() {
        List<ProposalUserRoles> proposalUserRolesList = new ArrayList<ProposalUserRoles>();
        
        ProposalUserRoles userRoles = new ProposalUserRoles();
        userRoles.setUsername("quickstart");
        userRoles.addRoleName("Aggregator Document Level");
        proposalUserRolesList.add(userRoles);
        
        userRoles = new ProposalUserRoles();
        userRoles.setUsername("chew");
        userRoles.addRoleName("Viewer");
        proposalUserRolesList.add(userRoles);
        
        return proposalUserRolesList;
    }
    
    /**
     * Create a proposal user to be added.
     * @param username
     * @return
     */
    private ProposalUserRoles createProposalUserRoles(String username) {
        ProposalUserRoles proposalUser = new ProposalUserRoles();
        proposalUser.setUsername(username);
        proposalUser.getRoleNames().add("Aggregator Document Level");
        return proposalUser;
    }
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * propery key and error key.
     * @param propertyKey
     * @param errorKey
     */
    private void assertError(String propertyKey, String errorKey) {
        MessageMap messages = GlobalVariables.getMessageMap();

        List errors = messages.getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }
}
