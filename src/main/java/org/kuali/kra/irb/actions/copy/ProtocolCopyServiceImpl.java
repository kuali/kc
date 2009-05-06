/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.copy;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.service.ProtocolAuthorizationService;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The Protocol Copy Service creates a new Protocol Document
 * based upon a current document.
 * 
 * The service uses the following steps in order to copy a protocol:
 * <ol>
 * <li>The Document Service is used to create a new Protocol
 *     Document.  By having a new document, its initiator and timestamp
 *     are set correctly and all workflow information is in its initial
 *     state, e.g.  there are no adhoc routes.
 * </li>
 * <li>The Document Overview, Required, and Additional properties 
 *     are copied from the original protocol to the new one.
 * </li>
 * <li>The new protocol document is saved to the database so that we
 *     can obtain its ProtocolId and ProtocolNumber.
 * </li>
 * <li>The list properties are moved from the original protocol to
 *     the new protocol and their primary keys are initialized along with
 *     their values for ProtocolId and ProtocolNumber.
 * </li>
 * <li>The new document is saved a second time to the database.
 * </li>
 * </ul>
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
class ProtocolCopyServiceImpl implements ProtocolCopyService {
    
    private DocumentService documentService;
    private SystemAuthorizationService systemAuthorizationService;
    private ProtocolAuthorizationService protocolAuthorizationService;
    
    /**
     * Set the Document Service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the System Authorization Service.
     * @param systemAuthorizationService
     */
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }
    
    /**
     * Set the Protocol Authorization Service.
     * @param protocolAuthorizationService
     */
    public void setProtocolAuthorizationService(ProtocolAuthorizationService protocolAuthorizationService) {
        this.protocolAuthorizationService = protocolAuthorizationService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.copy.ProtocolCopyService#copyProtocol(org.kuali.kra.irb.document.ProtocolDocument)
     */
    public String copyProtocol(ProtocolDocument srcDoc) throws Exception {
       
        ProtocolDocument newDoc = createNewProtocol(srcDoc);
        copyProtocolLists(srcDoc, newDoc);
         
        documentService.saveDocument(newDoc);
            
        // Can't initialize authorization until a protocol is saved
        // and we have a new protocol number.
            
        initializeAuthorization(srcDoc, newDoc);

        return newDoc.getDocumentNumber();
    }
    
    /**
     * Create a new protocol based upon a source protocol.  Only copy over the
     * properties necessary for the initial creation of the protocol.  This will
     * give us the protocol number to use when copying over the remainder of the
     * protocol.
     * @param srcDoc
     * @return
     * @throws Exception
     */
    private ProtocolDocument createNewProtocol(ProtocolDocument srcDoc) throws Exception {
        DocumentService docService = KNSServiceLocator.getDocumentService();
        ProtocolDocument newDoc = (ProtocolDocument) docService.getNewDocument(srcDoc.getClass());
            
        copyOverviewProperties(srcDoc, newDoc);
        copyRequiredProperties(srcDoc, newDoc);
        copyAdditionalProperties(srcDoc, newDoc);
        
        newDoc.getDocumentHeader().setDocumentTemplateNumber(srcDoc.getDocumentNumber());
        docService.saveDocument(newDoc);
        
        return newDoc;
    }
    
    /**
     * Copies the document overview properties.  These properties are the
     * Description, Explanation, and Organization Document Number.
     * 
     * @param src the source protocol document, i.e. the original.
     * @param dest the destination protocol document, i.e. the new document.
     */
    private void copyOverviewProperties(ProtocolDocument src, ProtocolDocument dest) {
        DocumentHeader srcHdr = src.getDocumentHeader();
        DocumentHeader destHdr = dest.getDocumentHeader();
        
        destHdr.setDocumentDescription(srcHdr.getDocumentDescription());
        destHdr.setExplanation(srcHdr.getExplanation());
        destHdr.setOrganizationDocumentNumber(srcHdr.getOrganizationDocumentNumber());
    }
    
    /**
     * Copy over the required properties so we can do an initial save of the document
     * in order to obtain a ProtocolId and ProtocolNumber.
     * @param srcDoc
     * @param destDoc
     */
    private void copyRequiredProperties(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        destDoc.getDocumentHeader().setDocumentDescription(srcDoc.getDocumentHeader().getDocumentDescription());
        destDoc.getProtocol().setProtocolTypeCode(srcDoc.getProtocol().getProtocolTypeCode());
        destDoc.getProtocol().setTitle(srcDoc.getProtocol().getTitle());
        destDoc.getProtocol().setLeadUnitNumber(srcDoc.getProtocol().getLeadUnitNumber());
        destDoc.getProtocol().setPrincipalInvestigatorId(srcDoc.getProtocol().getPrincipalInvestigatorId());
        destDoc.getProtocol().setLeadUnitForValidation(srcDoc.getProtocol().getLeadUnitForValidation());
    }
    
    /**
     * Copy over the additional properties.
     * @param srcDoc
     * @param destDoc
     */
    private void copyAdditionalProperties(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        destDoc.getProtocol().setFdaApplicationNumber(srcDoc.getProtocol().getFdaApplicationNumber());
        destDoc.getProtocol().setBillable(srcDoc.getProtocol().isBillable());
        destDoc.getProtocol().setReferenceNumber1(srcDoc.getProtocol().getReferenceNumber1());
        destDoc.getProtocol().setReferenceNumber2(srcDoc.getProtocol().getReferenceNumber2());
        destDoc.getProtocol().setDescription(srcDoc.getProtocol().getDescription());
    }
    
    /**
     * Initialize the Authorizations for a new protocol.  The initiator/creator
     * is assigned the Aggregator role.  We also add in all of the users and their
     * roles.  But if we encounter the initiator again, we ignore him/her.  For example,
     * the initiator may be a Viewer for the protocol being copied.  He/she should not
     * be a Viewer again for the new protocol since they are now have the Aggregator roe.
     * @param doc the protocol document
     */
    private void initializeAuthorization(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        UniversalUser user = new UniversalUser (GlobalVariables.getUserSession().getPerson());
        String userName = user.getPersonUserIdentifier();
        protocolAuthorizationService.addRole(userName, RoleConstants.PROTOCOL_AGGREGATOR, destDoc.getProtocol());
   
        Collection<KimRole> roles = systemAuthorizationService.getRoles(RoleConstants.PROTOCOL_ROLE_TYPE);
        for (KimRole role : roles) {
            List<Person> persons = protocolAuthorizationService.getPersonsInRole(srcDoc.getProtocol(), role.getName());
            for (Person person : persons) {
                if (!StringUtils.equals(person.getUserName(), userName)) {
                    protocolAuthorizationService.addRole(person.getUserName(), role.getName(), destDoc.getProtocol());
                }
            }
        }
    }
    
    /**
     * Copy the BO lists from the original protocol to the new protocol.
     * The init() method for each BO entry will be invoked to reset its
     * primary key (id), ProtocolId, and ProtocolNumber.
     * @param srcDoc
     * @param destDoc
     */
    private void copyProtocolLists(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        destDoc.getProtocol().setProtocolRiskLevels(srcDoc.getProtocol().getProtocolRiskLevels());
        destDoc.getProtocol().setProtocolParticipants(srcDoc.getProtocol().getProtocolParticipants());
        destDoc.getProtocol().setProtocolResearchAreas(srcDoc.getProtocol().getProtocolResearchAreas());
        destDoc.getProtocol().setProtocolReferences(srcDoc.getProtocol().getProtocolReferences());
        destDoc.getProtocol().setProtocolLocations(srcDoc.getProtocol().getProtocolLocations());
        destDoc.getProtocol().setProtocolFundingSources(srcDoc.getProtocol().getProtocolFundingSources());
        destDoc.getProtocol().setProtocolPersons(srcDoc.getProtocol().getProtocolPersons());
        destDoc.getProtocol().setSpecialReviews(srcDoc.getProtocol().getSpecialReviews());
    }
}