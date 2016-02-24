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

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;

/**
 * Base class for Protocol business rule tests.
 * 
 */
public class ProtocolFactory {

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Document";
    private static final String PROTOCOL_NUMBER = "0906000001";
    private static final String PROTOCOL_TYPE_CODE_STR = "1";//test of option "Standard";
    private static final String PROTOCOL_TITLE_STR = "New protocol test";
    private static final String PRINCIPAL_INVESTIGATOR_ID = "10000000001";
    private static final String PRINCIPAL_INVESTIGATOR_NAME = "Terry Durkin";
    private static final String PRINCIPAL_INVESTIGATOR_UNIT = "BL-BL";
    private static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    private static final String REFERENCE_UNIT = "unit";
   
    /**
     * Get a new Protocol Document.
     * 
     * @return a new Protocol Document.
     * @throws WorkflowException
     */
    public static ProtocolDocument createProtocolDocument() throws WorkflowException {
        return createProtocolDocument(null);
    }
    
    /**
     * Get a new Protocol Document.
     * 
     * @return a new Protocol Document.
     * @throws WorkflowException
     */
    public static ProtocolDocument createProtocolDocument(String protocolNumber) throws WorkflowException {
        return createProtocolDocument(protocolNumber, 0);
    }
    
    /**
     * Get a new Protocol Document.
     * 
     * @return a new Protocol Document.
     * @throws WorkflowException
     */
    public static ProtocolDocument createProtocolDocument(String protocolNumber, Integer sequenceNumber) throws WorkflowException {
        DocumentService documentService = KRADServiceLocatorWeb.getDocumentService();
        ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getNewDocument("ProtocolDocument");
        setProtocolRequiredFields(protocolDocument, protocolNumber);

        protocolDocument.getProtocol().setSequenceNumber(sequenceNumber);
        
        String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        if(!kraAuthorizationService.hasDocumentLevelRole(principalId, RoleConstants.PROTOCOL_AGGREGATOR, protocolDocument.getProtocol())) {
            kraAuthorizationService.addDocumentLevelRole(principalId, RoleConstants.PROTOCOL_AGGREGATOR, protocolDocument.getProtocol());
        }
        if(!kraAuthorizationService.hasDocumentLevelRole(principalId, RoleConstants.PROTOCOL_APPROVER, protocolDocument.getProtocol())) {
            kraAuthorizationService.addDocumentLevelRole(principalId, RoleConstants.PROTOCOL_APPROVER, protocolDocument.getProtocol());
        }

        return (ProtocolDocument) documentService.saveDocument(protocolDocument);
    }


    /**
     * This method is to set required fields for Protocol document
     * @param document
     */
    public static void setProtocolRequiredFields(ProtocolDocument document, String protocolNumber) {
        Protocol protocol = document.getProtocol();
        document.getDocumentHeader().setDocumentDescription(DEFAULT_DOCUMENT_DESCRIPTION);
        document.setDocumentNextvalues(new ArrayList<DocumentNextvalue>());
        protocol.setProtocolDocument(document);
        protocol.setProtocolTypeCode(PROTOCOL_TYPE_CODE_STR);
        protocol.setTitle(PROTOCOL_TITLE_STR);
        protocol.setProtocolNumber(protocolNumber == null ? PROTOCOL_NUMBER : protocolNumber);
        protocol.setSequenceNumber(0);
        
        ProtocolPerson protocolPerson = getProtocolPerson(PRINCIPAL_INVESTIGATOR_ID, PRINCIPAL_INVESTIGATOR_NAME, PRINCIPAL_INVESTIGATOR_ROLE, protocolNumber);
        
        ProtocolUnit protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(PRINCIPAL_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(true);
        protocolUnit.setProtocolNumber(protocolNumber == null ? PROTOCOL_NUMBER : protocolNumber);
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
    public static ProtocolPerson getProtocolPerson(String personId, String personName, String personRole, String protocolNumber) {
        ProtocolPerson protocolPerson = new ProtocolPerson();
        protocolPerson.setPersonId(personId);
        protocolPerson.setPersonName(personName);
        protocolPerson.setProtocolPersonRoleId(personRole);
        protocolPerson.setPreviousPersonRoleId(personRole);
        protocolPerson.setProtocolNumber(protocolNumber == null ? PROTOCOL_NUMBER : protocolNumber);
        protocolPerson.setSequenceNumber(0);
        protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
        return protocolPerson;
    }
}
