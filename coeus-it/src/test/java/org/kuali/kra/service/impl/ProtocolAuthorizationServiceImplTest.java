package org.kuali.kra.service.impl;

import org.junit.Before;
import org.junit.Ignore;
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
        kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, doc);
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
    @Test @Ignore("KCINFR-982")
    public void testGetAllRolePersons() throws Exception {
        ProposalDevelopmentDocument doc = createProposal("Proposal-8", "000001");

        PrincipalContract userChew = identityManagementService.getPrincipalByPrincipalName("chew");
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.NARRATIVE_WRITER, doc);
        kraAuthService.addRole(userChew.getPrincipalId(), RoleConstants.BUDGET_CREATOR, doc);

        PrincipalContract userMajors = identityManagementService.getPrincipalByPrincipalName("majors");
        kraAuthService.addRole(userMajors.getPrincipalId(), RoleConstants.VIEWER, doc);

        PrincipalContract userWoods = identityManagementService.getPrincipalByPrincipalName("woods");
        kraAuthService.addRole(userWoods.getPrincipalId(), RoleConstants.AGGREGATOR, doc);

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
