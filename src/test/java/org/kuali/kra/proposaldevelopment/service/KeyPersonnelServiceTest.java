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
package org.kuali.kra.proposaldevelopment.service;

import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.logging.FormattedLogger.info;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.impl.KeyPersonnelServiceImpl;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * Class intended to exercise testing of units of functionality within the
 * <code>{@link KeyPersonnelService}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.9 $
 */
public class KeyPersonnelServiceTest extends KcUnitTestBase {
    private ProposalDevelopmentDocument document;
    private ProposalDevelopmentDocument blankDocument;
    private DocumentService documentService = null;
    private ParameterService parameterService;
    
    protected static final String NIH_SPONSOR_CODE = "000340";
    protected static final String NON_NIH_SPONSOR_CODE = "000500";
    
    protected static final String COI_ROLE_ID = "COI";
    protected static final String NIH_COI_PARAM = "personrole.nih.coi";
    protected static final String NIH_MPI_PARAM = "personrole.nih.coi.mpi";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KRADServiceLocatorWeb.getDocumentService();
        parameterService = KraServiceLocator.getService(ParameterService.class);
        document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        blankDocument =(ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
    }
    
   
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void populateDocument() {
        getKeyPersonnelService().populateDocument(blankDocument);
        assertTrue(blankDocument.getDevelopmentProposal().getInvestigatorCreditTypes().size() > 0);
    }
    
//    @Test 
//    public void createProposalPersonFromNullPersonId() {
//        try {
//            getKeyPersonnelService().createProposalPersonFromPersonId(null);
//        } catch(IllegalArgumentException iae) {
//            assertTrue(iae.getMessage().equalsIgnoreCase("the personId is null or empty"));
//        }
//        
//    }
//
//    @Test 
//    public void createProposalPersonFromNullRolodexId() {
//        assertNull(getKeyPersonnelService().createProposalPersonFromRolodexId(null));
//    }


    
    /**
     * Verify the proposal person is given the lead unit of the document if the person is an investigator, 
     * initial credit splits exist and are setup properly
     */
    @Test
    public void populateProposalPerson_Investigator() {
        ProposalPerson person = new ProposalPerson();
        document.getDevelopmentProposal().setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        
        getKeyPersonnelService().populateProposalPerson(person, document);
        getKeyPersonnelService().assignLeadUnit(person, document.getDevelopmentProposal().getOwnedByUnitNumber());
        boolean personHasLeadUnit = false;
        for (ProposalPersonUnit unit : person.getUnits()) {
            personHasLeadUnit |= (unit.isLeadUnit() && unit.getUnitNumber().equals("000001")); 
        }
        assertTrue(personHasLeadUnit);
        assertTrue(person.isInvestigator());
    }

    /**
     * Verify the proposal person is given the lead unit of the document if the person is an investigator, 
     * initial credit splits exist and are setup properly
     * 
     */
    @Test
    public void populateProposalPerson_KeyPerson() {
        ProposalPerson person = new ProposalPerson();
        document.getDevelopmentProposal().setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId("KP");
        assertNull(person.getHomeUnit());
        assertFalse(person.isInvestigator());
    }
    
    /**
     * Test credit split totals are created properly initially
     */
    @Test
    public void calculateCreditSplitTotals_Default() {
        ProposalPerson person = new ProposalPerson();
        document.getDevelopmentProposal().setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        
        getKeyPersonnelService().populateProposalPerson(person, document);
        document.getDevelopmentProposal().addProposalPerson(person);
        
        Map<String, Map<String,KualiDecimal>> totals = getKeyPersonnelService().calculateCreditSplitTotals(document);
        for(String key : totals.keySet()) {
            info("Key = %s", key);
        }
    }
    
    @Test
    public void testPersonnelRoleDescCoi() {
        document.getDevelopmentProposal().setSponsorCode(NON_NIH_SPONSOR_CODE);
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId(COI_ROLE_ID);
        //setting to true as it should be ignored for non-nih sponsors
        person.setMultiplePi(true);
        person.setDevelopmentProposal(document.getDevelopmentProposal());
        assertEquals(getBusinessObjectService().findBySinglePrimaryKey(ProposalPersonRole.class, COI_ROLE_ID).getRoleDescription(), 
                getKeyPersonnelService().getPersonnelRoleDesc(person));        
    }
    
    
    @Test
    public void testPersonnelRoleDescCoiNih() {
        document.getDevelopmentProposal().setSponsorCode(NIH_SPONSOR_CODE);
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId(COI_ROLE_ID);
        person.setDevelopmentProposal(document.getDevelopmentProposal());
        assertEquals(parameterService.getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, NIH_COI_PARAM), 
                getKeyPersonnelService().getPersonnelRoleDesc(person));        
    }
    
    @Test
    public void testPersonnelRoleDescMpi() {
        document.getDevelopmentProposal().setSponsorCode(NIH_SPONSOR_CODE);
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId(COI_ROLE_ID);
        person.setMultiplePi(true);
        person.setDevelopmentProposal(document.getDevelopmentProposal());
        assertEquals(parameterService.getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, NIH_MPI_PARAM), 
                getKeyPersonnelService().getPersonnelRoleDesc(person));        
    }
    
    
    /**
     * Locate the <code>{@link KeyPersonnelService}</code>
     * 
     * @return KeyPersonnelService
     * @see KraTestBase#getService(Class)
     */
    private KeyPersonnelServiceImpl getKeyPersonnelService() {
        return (KeyPersonnelServiceImpl) KraServiceLocator.getService(KeyPersonnelService.class);
    }

}
