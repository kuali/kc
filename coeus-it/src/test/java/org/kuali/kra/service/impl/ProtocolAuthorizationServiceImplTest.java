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
package org.kuali.kra.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolAuthorizationService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProtocolAuthorizationServiceImplTest extends KcIntegrationTestBase {

    private KcAuthorizationService kraAuthService;
    private ProtocolAuthorizationService protocolAuthorizationService;
    private IdentityService identityManagementService;
    private ProposalDevelopmentService proposalDevelopmentService;

    @Before
    public void setUp() throws Exception {
        kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
        protocolAuthorizationService =  KcServiceLocator.getService(ProtocolAuthorizationService.class);
        proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        identityManagementService = KcServiceLocator.getService(IdentityService.class);
    }

    private void initializeProposalUsers(ProposalDevelopmentDocument doc) {
        // Assign the creator of the proposal to the AGGREGATOR role.
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthService.addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, doc);
    }

    private ProposalDevelopmentDocument createProposal(String documentDescription, String leadUnitNumber) throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument("ProposalDevelopmentDocument");

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

        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        initializeProposalUsers(document);

        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);

        return savedDocument;
    }

    /**
     * Test the getAllRolePersons() service method.
     */
    @Test
    public void testGetAllRolePersons() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-8", "000001");

        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addDocumentLevelRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, doc);
        kraAuthService.addDocumentLevelRole(userChew.getPrincipalId(), RoleConstants.BUDGET_CREATOR, doc);

        PrincipalContract userMajors = identityManagementService.getPrincipalByPrincipalName("majors");
        kraAuthService.addDocumentLevelRole(userMajors.getPrincipalId(), RoleConstants.VIEWER, doc);

        PrincipalContract userWoods = identityManagementService.getPrincipalByPrincipalName("woods");
        kraAuthService.addDocumentLevelRole(userWoods.getPrincipalId(), RoleConstants.AGGREGATOR, doc);

        List<RolePersons> rolePersonsList = protocolAuthorizationService.getAllRolePersons(doc);
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
}
