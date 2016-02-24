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
package org.kuali.kra.irb.test;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Base class for Protocol business rule tests.
 * 
 */
public abstract class ProtocolRuleTestBase extends KcIntegrationTestBase {

    protected DocumentService documentService = null;
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Document";
    protected static final String PROTOCOL_TYPE_CODE_STR = "1";//test of option "Standard";
    protected static final String PROTOCOL_TITLE_STR = "New protocol test";
    protected static final String PRINCIPAL_INVESTIGATOR_ID = "10000000001";
    protected static final String PRINCIPAL_INVESTIGATOR_NAME = "Joe Tester";
    protected static final String PRINCIPAL_INVESTIGATOR_UNIT = "IN-CARD";
    protected static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    protected static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    protected static final String REFERENCE_UNIT = "unit";
    

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KRADServiceLocatorWeb.getDocumentService();
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setMessageMap(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
    }
    
    /**
     * Get a new Protocol Document.
     * 
     * @return a new Protocol Document.
     * @throws WorkflowException
     */
    protected ProtocolDocument getNewProtocolDocument() throws WorkflowException {
        return (ProtocolDocument) documentService.getNewDocument("ProtocolDocument");
    }

    /**
     * This method is to set required fields for Protocol document
     * @param document
     */
    protected void setProtocolRequiredFields(ProtocolDocument document) {
        Protocol protocol = document.getProtocol();
        document.getDocumentHeader().setDocumentDescription(DEFAULT_DOCUMENT_DESCRIPTION);
        protocol.setProtocolTypeCode(PROTOCOL_TYPE_CODE_STR);
        protocol.setTitle(PROTOCOL_TITLE_STR);
        
        ProtocolPerson protocolPerson = getProtocolPerson(PRINCIPAL_INVESTIGATOR_ID, PRINCIPAL_INVESTIGATOR_NAME, PRINCIPAL_INVESTIGATOR_ROLE);
        
        ProtocolUnit protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(PRINCIPAL_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(true);
        protocolUnit.setProtocolNumber("0");
        protocolUnit.setSequenceNumber(0);
        protocolUnit.refreshReferenceObject(REFERENCE_UNIT);
        protocolPerson.getProtocolUnits().add(protocolUnit);
        
        protocol.getProtocolPersons().add(protocolPerson);
        protocol.setLeadUnitNumber(PRINCIPAL_INVESTIGATOR_UNIT);
        protocol.setPrincipalInvestigatorId(PRINCIPAL_INVESTIGATOR_ID);
    }
    
    /**
     * This method is to get protocol person details
     * @param personId
     * @param personName
     * @param personRole
     * @return
     */
    protected ProtocolPerson getProtocolPerson(String personId, String personName, String personRole) {
        ProtocolPerson protocolPerson = new ProtocolPerson();
        protocolPerson.setPersonId(personId);
        protocolPerson.setPersonName(personName);
        protocolPerson.setProtocolPersonRoleId(personRole);
        protocolPerson.setPreviousPersonRoleId(personRole);
        protocolPerson.setProtocolNumber("0");
        protocolPerson.setSequenceNumber(0);
        protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
        return protocolPerson; 
    }
    
    /**
     * This method is to get protocol person with role PI
     * @return
     */
    protected ProtocolPerson getPrincipalInvestigator() {
        return getProtocolPerson(PRINCIPAL_INVESTIGATOR_ID, PRINCIPAL_INVESTIGATOR_NAME, PRINCIPAL_INVESTIGATOR_ROLE);
    }

    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        List<ErrorMessage> errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }
}
