/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service.impl;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Test the Kra Authorization Service Impl.  Mocks are used
 * to isolate the service from KIM.  Well-defined data  is placed 
 * into Mock KIM services.  The Kra Authorization Service methods
 * are then invoked and the responses are checked against the expected
 * results.
 */
public class KraAuthorizationServiceImplTest extends KcUnitTestBase {

    private KraAuthorizationService kraAuthService;
    private IdentityService identityManagementService;
    private RoleService roleManagementService;
    private ProposalDevelopmentService proposalDevelopmentService;
    
    /**
     * Create the mock services and insert them into the kra auth service.
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        identityManagementService = KraServiceLocator.getService(IdentityService.class);
        roleManagementService = KraServiceLocator.getService(RoleService.class);
    }
    
    /**
     * Test the getUsernames() service method.
     */
    @Test
    public void testGetUsernames() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-1", "000001");
        List<String> usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 1);
    }
    
    /**
     * Test the addRole() service method.
     */
    @Test
    public void testAddRole() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-2", "000001");
        PrincipalContract userMajors = identityManagementService.getPrincipalByPrincipalName("majors");
        kraAuthService.addRole(userMajors.getPrincipalId(), RoleConstants.AGGREGATOR, doc);
        List<String> usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 2);
        assertTrue(usernames.contains("majors"));
    }

    /**
     * Test the addRole() service method.
     */
    @Test
    public void testAddNarrativeWriterRole() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-3", "000001");
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, doc);
        List<String> usernames = kraAuthService.getUserNames(doc, RoleConstants.NARRATIVE_WRITER);
        assertTrue(usernames.size() == 1);
        assertTrue(usernames.contains("chew"));
    }

    @Test
    public void testRemoveRole() throws Exception {
        ProposalDevelopmentDocument  currentDoc = createProposal("Proposal-3", "000001");
        kraAuthService.removeRole(GlobalVariables.getUserSession().getPrincipalId(), RoleConstants.AGGREGATOR, currentDoc);
        List<String> names = kraAuthService.getUserNames(currentDoc, RoleConstants.AGGREGATOR);
        assertTrue(names.size() == 0);  
    }
    
    @Test
    public void testRemoveNarrativeWriterRole() throws Exception {
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        ProposalDevelopmentDocument  currentDoc = createProposal("Proposal-3", "000001");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, currentDoc);
        List<String> usernames = kraAuthService.getUserNames(currentDoc, RoleConstants.NARRATIVE_WRITER);
        assertTrue(usernames.size() == 1);
        assertTrue(usernames.contains("chew"));
        
        kraAuthService.removeRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, currentDoc);
        List<String> names = kraAuthService.getUserNames(currentDoc, RoleConstants.NARRATIVE_WRITER);
        assertTrue(names.size() == 0);
    }
    
    /**
     * Test the hasPermission() service method.
     */
    @Test
    public void testHasPermission() throws Exception {
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        ProposalDevelopmentDocument doc = createProposal("Proposal-4", "000001");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, doc);
        assertTrue(kraAuthService.hasPermission(userChew.getPrincipalId(), doc, PermissionConstants.MODIFY_NARRATIVE));
        assertFalse(kraAuthService.hasPermission(userChew.getPrincipalId(), doc, PermissionConstants.MODIFY_BUDGET));
    }
    
    /**
     * Test the hasRole() service method.
     */
    @Test
    public void testHasRole() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-5", "000001");
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.BUDGET_CREATOR, doc);
        //assertTrue(kraAuthService.hasRole(GlobalVariables.getUserSession().getPrincipalId(), doc, RoleConstants.AGGREGATOR));
        assertTrue(kraAuthService.hasRole(userChew.getPrincipalId(), doc, RoleConstants.BUDGET_CREATOR));
    }
    
    /**
     * Test the getRoles() service method.
     */
    @Test
    public void testGetRoles() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-6", "000001");
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, doc);
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.BUDGET_CREATOR, doc);
        List<String> roles = kraAuthService.getRoles(userChew.getPrincipalId(), doc);
        assertTrue(roles.size() == 2);
        assertTrue(roles.contains(RoleConstants.NARRATIVE_WRITER));
        assertTrue(roles.contains(RoleConstants.BUDGET_CREATOR));
    }
    
    /**
     * Test the getPersonsInRole() service method.
     */
    @Test
    public void testGetPersonsInRole() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-7", "000001");
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.AGGREGATOR, doc);
        List<KcPerson> persons = kraAuthService.getPersonsInRole(doc, RoleConstants.AGGREGATOR);
        assertEquals(2, persons.size());
    }
    
    /**
     * Test the getAllRolePersons() service method.
     */
    @Test
    public void testGetAllRolePersons() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-8", "000001");
       
        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, doc);
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.BUDGET_CREATOR, doc);

        PrincipalContract userMajors = identityManagementService.getPrincipalByPrincipalName("majors");
        kraAuthService.addRole(userMajors.getPrincipalId(), RoleConstants.VIEWER, doc);

        PrincipalContract userWoods = identityManagementService.getPrincipalByPrincipalName("woods");
        kraAuthService.addRole(userWoods.getPrincipalId(), RoleConstants.AGGREGATOR, doc);
        
        List<RolePersons> rolePersonsList = kraAuthService.getAllRolePersons(doc);
        assertEquals(5, rolePersonsList.size());
        for (RolePersons rolePersons : rolePersonsList){
            if(rolePersons.getAggregator()!= null )
            {
                List<String> aggregators= rolePersons.getAggregator();
                assertEquals(2, aggregators.size());
                assertTrue(aggregators.contains("woods"));
                assertTrue(aggregators.contains("quickstart"));
            } else if(rolePersons.getViewer()!= null){
                List<String> viewer=rolePersons.getViewer();
                assertEquals(1, viewer.size());
                assertTrue(viewer.contains("majors"));
            } else if(rolePersons.getBudgetcreator()!= null){
                List<String> budgetCreator = rolePersons.getBudgetcreator();
                assertEquals(1, budgetCreator.size());
                assertTrue(budgetCreator.contains("chew"));
            } else if(rolePersons.getNarrativewriter()!= null){
                List<String> narrativeWriter = rolePersons.getNarrativewriter();
                assertEquals(1, narrativeWriter.size());
                assertTrue(narrativeWriter.contains("chew"));
            }
            
            
        }
        
    }   
                
    private void initializeProposalUsers(ProposalDevelopmentDocument doc) {
        // Assign the creator of the proposal to the AGGREGATOR role.
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, doc);
        //ProposalRoleTemplateService proposalRoleTemplateService = KraServiceLocator.getService(ProposalRoleTemplateService.class);
        //proposalRoleTemplateService.addUsers(doc);
    } 
    
    private ProposalDevelopmentDocument createProposalWithSecondRole(String documentDescription, String leadUnit, String secondRole, String roleRecipientId) throws Exception {
        ProposalDevelopmentDocument doc = createProposal(documentDescription, leadUnit);
        kraAuthService.addRole(roleRecipientId, secondRole, doc);
        return doc;
    }

    /**
     * Create a proposal development document.  For testing
     * purposes, we only need its proposal number to be set.
     * @param nbr
     * @return
     */
    private ProposalDevelopmentDocument createProposal(String documentDescription, String leadUnitNumber) throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) getDocumentService().getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        document.getDocumentHeader().setDocumentDescription(documentDescription);
        document.getDevelopmentProposal().setSponsorCode("000162");
        document.getDevelopmentProposal().setTitle("project title");
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode("1");
        document.getDevelopmentProposal().setProposalTypeCode("1");
        document.getDevelopmentProposal().setOwnedByUnitNumber(leadUnitNumber);
        document.getDevelopmentProposal().setPrimeSponsorCode("000120");

        proposalDevelopmentService.initializeUnitOrganizationLocation(document);
        proposalDevelopmentService.initializeProposalSiteNumbers(document);
        
        getDocumentService().saveDocument(document);
        initializeProposalUsers(document);
        
        getDocumentService().saveDocument(document);
        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);

        return savedDocument;
    }
}
