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
package org.kuali.kra.iacuc.test;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolUnit;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;

public final class IacucProtocolFactory {

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Iacuc Protocol Document";
    private static final String PROTOCOL_NUMBER = "0906000001";
    private static final String PROTOCOL_TYPE_CODE_STR = "1";//test of option "Standard";
    private static final String PROTOCOL_TITLE_STR = "New protocol test";
    private static final String PRINCIPAL_INVESTIGATOR_ID = "10000000001";
    private static final String PRINCIPAL_INVESTIGATOR_NAME = "Terry Durkin";
    private static final String PRINCIPAL_INVESTIGATOR_UNIT = "BL-BL";
    private static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    private static final String REFERENCE_UNIT = "unit";
    private static final String LAY_STATEMENT_1 = "A lay statement1";


    private IacucProtocolFactory() {
        throw new UnsupportedOperationException("do not call");
    }

    public static IacucProtocolDocument createProtocolDocument() throws WorkflowException {
        return createProtocolDocument(null);
    }
    

    public static IacucProtocolDocument createProtocolDocument(String protocolNumber) throws WorkflowException {
        return createProtocolDocument(protocolNumber, 0);
    }
    

    public static IacucProtocolDocument createProtocolDocument(String protocolNumber, Integer sequenceNumber) throws WorkflowException {
        DocumentService documentService = KRADServiceLocatorWeb.getDocumentService();
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument) documentService.getNewDocument(IacucProtocolDocument.class);
        setProtocolRequiredFields(protocolDocument, protocolNumber);

        protocolDocument.getProtocol().setSequenceNumber(sequenceNumber);
        
        String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        if(!kraAuthorizationService.hasDocumentLevelRole(principalId, RoleConstants.IACUC_PROTOCOL_AGGREGATOR, protocolDocument.getProtocol())) {
            kraAuthorizationService.addDocumentLevelRole(principalId, RoleConstants.IACUC_PROTOCOL_AGGREGATOR, protocolDocument.getProtocol());
        }
        if(!kraAuthorizationService.hasDocumentLevelRole(principalId, RoleConstants.IACUC_PROTOCOL_APPROVER, protocolDocument.getProtocol())) {
            kraAuthorizationService.addDocumentLevelRole(principalId, RoleConstants.IACUC_PROTOCOL_APPROVER, protocolDocument.getProtocol());
        }

        return (IacucProtocolDocument) documentService.saveDocument(protocolDocument);
    }

    public static void setProtocolRequiredFields(IacucProtocolDocument document, String protocolNumber) {
        IacucProtocol protocol = document.getProtocol();
        document.getDocumentHeader().setDocumentDescription(DEFAULT_DOCUMENT_DESCRIPTION);
        document.setDocumentNextvalues(new ArrayList<>());
        protocol.setProtocolDocument(document);
        protocol.setProtocolTypeCode(PROTOCOL_TYPE_CODE_STR);
        protocol.setTitle(PROTOCOL_TITLE_STR);
        protocol.setProtocolNumber(protocolNumber == null ? PROTOCOL_NUMBER : protocolNumber);
        protocol.setSequenceNumber(0);

        protocol.setLayStatement1(LAY_STATEMENT_1);

        IacucProtocolPerson protocolPerson = getProtocolPerson(PRINCIPAL_INVESTIGATOR_ID, PRINCIPAL_INVESTIGATOR_NAME, PRINCIPAL_INVESTIGATOR_ROLE, protocolNumber);
        
        IacucProtocolUnit protocolUnit = new IacucProtocolUnit();
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

    public static IacucProtocolPerson getProtocolPerson(String personId, String personName, String personRole, String protocolNumber) {
        IacucProtocolPerson protocolPerson = new IacucProtocolPerson();
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
