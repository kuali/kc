/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;


/**
 * Class intended to exercise testing of units of functionality within the
 * <code>{@link KeyPersonnelService}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.9 $
 */
public class KeyPersonnelServiceTest extends KraTestBase {
    private ProposalDevelopmentDocument document;
    private ProposalDevelopmentDocument blankDocument;
    private DocumentService documentService = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
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
    
    @Test 
    public void createProposalPersonFromNullPersonId() {
        assertNull(getKeyPersonnelService().createProposalPersonFromPersonId(null));
        
    }

    @Test 
    public void createProposalPersonFromNullRolodexId() {
        assertNull(getKeyPersonnelService().createProposalPersonFromRolodexId(null));
    }


    
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
    
    
    /**
     * Locate the <code>{@link KeyPersonnelService}</code>
     * 
     * @return KeyPersonnelService
     * @see KraTestBase#getService(Class)
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }

}
