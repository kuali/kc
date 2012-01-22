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
package org.kuali.kra.irb.actions.copy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolNotepad;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.ProtocolNumberService;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

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
public class ProtocolCopyServiceImpl implements ProtocolCopyService {
    
    private static final String PROTOCOL_CREATED = "Protocol created";
    
    private DocumentService documentService;
    private SystemAuthorizationService systemAuthorizationService;
    private KraAuthorizationService kraAuthorizationService;
    private ProtocolNumberService protocolNumberService;
    private SequenceAccessorService sequenceAccessorService;

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
     * Set the Kra Authorization Service.
     * @param kralAuthorizationService
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Set the Protocol Number Service
     * @param protocolNumberService the Protocol Number Service
     */
    public void setProtocolNumberService(ProtocolNumberService protocolNumberService) {
        this.protocolNumberService = protocolNumberService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.copy.ProtocolCopyService#copyProtocol(org.kuali.kra.irb.ProtocolDocument)
     */
    public ProtocolDocument copyProtocol(ProtocolDocument srcDoc) throws Exception {
        return copyProtocol(srcDoc, protocolNumberService.generateProtocolNumber(), false);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.copy.ProtocolCopyService#copyProtocol(org.kuali.kra.irb.ProtocolDocument, java.lang.String)
     */
    public ProtocolDocument copyProtocol(ProtocolDocument srcDoc, String protocolNumber, boolean isAmendmentRenewal) throws Exception {
        ProtocolDocument newDoc = createNewProtocol(srcDoc, protocolNumber, isAmendmentRenewal);
        
        documentService.saveDocument(newDoc);
            
        // Can't initialize authorization until a protocol is saved
        // and we have a new protocol number.
            
        initializeAuthorization(srcDoc, newDoc);

        return newDoc;
    }
    
    /**
     * Create a new protocol based upon a source protocol.  Only copy over the
     * properties necessary for the initial creation of the protocol.  This will
     * give us the protocol number to use when copying over the remainder of the
     * protocol.
     * "Copy" may be a pure copy or copy for amendment & renewal creation.
     * if for amendment/renewal, then notes & attachments will be copied.
     * @param srcDoc
     * @return
     * @throws Exception
     */
    protected ProtocolDocument createNewProtocol(ProtocolDocument srcDoc, String protocolNumber, boolean isAmendmentRenewal) throws Exception {
        DocumentService docService = KRADServiceLocatorWeb.getDocumentService();
        ProtocolDocument newDoc = (ProtocolDocument) docService.getNewDocument(srcDoc.getClass());
            
        newDoc.getProtocol().setProtocolNumber(protocolNumber);
        newDoc.getProtocol().setSequenceNumber(0);
        
        copyOverviewProperties(srcDoc, newDoc);
        copyRequiredProperties(srcDoc, newDoc);
        copyAdditionalProperties(srcDoc, newDoc);
        copyProtocolLists(srcDoc, newDoc);
        if (isAmendmentRenewal && !srcDoc.getProtocol().isAmendment() && !srcDoc.getProtocol().isAmendment()) {
            removeDeletedAttachment(newDoc.getProtocol());
            
        }
        newDoc.getProtocol().setProtocolNumber(protocolNumber);
        copyCustomDataAttributeValues(srcDoc, newDoc);
        
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = 
              new org.kuali.kra.irb.actions.ProtocolAction(newDoc.getProtocol(), null, ProtocolActionType.PROTOCOL_CREATED);
        protocolAction.setComments(PROTOCOL_CREATED);
        newDoc.getProtocol().getProtocolActions().add(protocolAction);
        
        newDoc.getDocumentHeader().setDocumentTemplateNumber(srcDoc.getDocumentNumber());
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
        newDoc.getProtocol().setProtocolId(nextProtocolId);
        if (!isAmendmentRenewal) {
            newDoc.getProtocol().setAttachmentProtocols(new ArrayList<ProtocolAttachmentProtocol>());
            newDoc.getProtocol().setNotepads(new ArrayList<ProtocolNotepad>());
            if (newDoc.getProtocol().getProtocolPersons() != null) {
                for (ProtocolPerson person : newDoc.getProtocol().getProtocolPersons()) {
                    person.setAttachmentPersonnels(new ArrayList<ProtocolAttachmentPersonnel>());
                }
            }
        } else {
            initPersonId(newDoc.getProtocol());
        }
        documentService.saveDocument(newDoc); 
        if (isAmendmentRenewal) {
            // :TODO : verify if really need this 
            refreshAttachmentsPersonnels(newDoc.getProtocol());
        }
        
        return newDoc;
    }

    private void removeDeletedAttachment(Protocol protocol) {
        List<Integer> documentIds = new ArrayList<Integer>();
        List<ProtocolAttachmentProtocol> attachments = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("3".equals(attachment.getDocumentStatusCode())) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        if (!documentIds.isEmpty()) {
            for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
                attachment.setProtocol(protocol);
                if (!documentIds.contains(attachment.getDocumentId())) {
                    attachments.add(attachment);
                }
            }
            protocol.setAttachmentProtocols(attachments);
        }
        
    }


    /**
     * This method initializes the personId of the person and person attachments for the newly created protocol.
     * @param protocol
     */
    private void initPersonId(Protocol protocol) {
        for (ProtocolPerson person : protocol.getProtocolPersons()) {
            Integer nextPersonId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID").intValue();
            person.setProtocolPersonId(nextPersonId);
            for (ProtocolAttachmentPersonnel attachment : person.getAttachmentPersonnels()) {
                attachment.setPersonId(person.getProtocolPersonId());
            }
        }
    }
    
    protected void refreshAttachmentsPersonnels(Protocol protocol) {
        if (protocol.getProtocolPersons() != null) {
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                person.refreshReferenceObject("attachmentPersonnels");
            }
        }

    }
                
    /**
     * Copies the document overview properties.  These properties are the
     * Description, Explanation, and Organization Document Number.
     * 
     * @param src the source protocol document, i.e. the original.
     * @param dest the destination protocol document, i.e. the new document.
     */
    protected void copyOverviewProperties(ProtocolDocument src, ProtocolDocument dest) {
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
    protected void copyRequiredProperties(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        destDoc.getDocumentHeader().setDocumentDescription(srcDoc.getDocumentHeader().getDocumentDescription());
        destDoc.getProtocol().setProtocolTypeCode(srcDoc.getProtocol().getProtocolTypeCode());
        destDoc.getProtocol().setTitle(srcDoc.getProtocol().getTitle());
        destDoc.getProtocol().setLeadUnitNumber(srcDoc.getProtocol().getLeadUnitNumber());
        destDoc.getProtocol().setPrincipalInvestigatorId(srcDoc.getProtocol().getPrincipalInvestigatorId());
    }
    
    /**
     * Copy over the additional properties.
     * @param srcDoc
     * @param destDoc
     */
    protected void copyAdditionalProperties(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        destDoc.getProtocol().setFdaApplicationNumber(srcDoc.getProtocol().getFdaApplicationNumber());
        destDoc.getProtocol().setReferenceNumber1(srcDoc.getProtocol().getReferenceNumber1());
        destDoc.getProtocol().setReferenceNumber2(srcDoc.getProtocol().getReferenceNumber2());
        destDoc.getProtocol().setDescription(srcDoc.getProtocol().getDescription());
        destDoc.getProtocol().setVulnerableSubjectIndicator(srcDoc.getProtocol().getVulnerableSubjectIndicator());
        destDoc.getProtocol().setCorrespondentIndicator(srcDoc.getProtocol().getCorrespondentIndicator());
        destDoc.getProtocol().setFundingSourceIndicator(srcDoc.getProtocol().getFundingSourceIndicator());
        destDoc.getProtocol().setKeyStudyPersonIndicator(srcDoc.getProtocol().getKeyStudyPersonIndicator());
        destDoc.getProtocol().setRelatedProjectsIndicator(srcDoc.getProtocol().getRelatedProjectsIndicator());
        destDoc.getProtocol().setReferenceIndicator(srcDoc.getProtocol().getReferenceIndicator());
        destDoc.getProtocol().setSpecialReviewIndicator(srcDoc.getProtocol().getSpecialReviewIndicator());
    }
    
    /**
     * Initialize the Authorizations for a new protocol.  The initiator/creator
     * is assigned the Aggregator role.  We also add in all of the users and their
     * roles.  But if we encounter the initiator again, we ignore him/her.  For example,
     * the initiator may be a Viewer for the protocol being copied.  He/she should not
     * be a Viewer again for the new protocol since they are now have the Aggregator roe.
     * @param doc the protocol document
     */
    protected void initializeAuthorization(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthorizationService.addRole(userId, RoleConstants.PROTOCOL_AGGREGATOR, destDoc.getProtocol());
        kraAuthorizationService.addRole(userId, RoleConstants.PROTOCOL_APPROVER, destDoc.getProtocol());
   
        List<Role> roles = systemAuthorizationService.getRoles(RoleConstants.PROTOCOL_ROLE_TYPE);
        for (Role role : roles) {
            List<KcPerson> persons = kraAuthorizationService.getPersonsInRole(srcDoc.getProtocol(), role.getName());
            for (KcPerson person : persons) {
                if (!StringUtils.equals(person.getPersonId(), userId)) {
                    kraAuthorizationService.addRole(person.getPersonId(), role.getName(), destDoc.getProtocol()); 
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
    @SuppressWarnings("unchecked")
    protected void copyProtocolLists(ProtocolDocument srcDoc, ProtocolDocument destDoc) {
        Protocol srcProtocol = srcDoc.getProtocol();
        Protocol destProtocol = destDoc.getProtocol();
        destProtocol.setProtocolRiskLevels((List<ProtocolRiskLevel>) deepCopy(srcProtocol.getProtocolRiskLevels()));
        destProtocol.setProtocolParticipants((List<ProtocolParticipant>) deepCopy(srcProtocol.getProtocolParticipants()));
        destProtocol.setProtocolResearchAreas((List<ProtocolResearchArea>) deepCopy(srcProtocol.getProtocolResearchAreas()));
        destProtocol.setProtocolReferences((List<ProtocolReference>) deepCopy(srcProtocol.getProtocolReferences()));
        destProtocol.setProtocolLocations((List<ProtocolLocation>) deepCopy(srcProtocol.getProtocolLocations()));
        destProtocol.setProtocolFundingSources((List<ProtocolFundingSource>) deepCopy(srcProtocol.getProtocolFundingSources()));
        destProtocol.setProtocolPersons((List<ProtocolPerson>) deepCopy(srcProtocol.getProtocolPersons()));
        destProtocol.setSpecialReviews((List<ProtocolSpecialReview>) deepCopy(srcProtocol.getSpecialReviews()));
        // must make following call to copy exemption codes (transient objects don't get copied)
        destProtocol.cleanupSpecialReviews(srcProtocol);
        destProtocol.setAttachmentProtocols((List<ProtocolAttachmentProtocol>) deepCopy(srcProtocol.getAttachmentProtocols()));
        destProtocol.setNotepads((List<ProtocolNotepad>) deepCopy(srcProtocol.getNotepads()));
    }
   
    protected Object deepCopy(Object obj) {
        if (obj instanceof Serializable) {
            return ObjectUtils.deepCopy((Serializable) obj);
        }
        return obj;
    }
    
    /**
     * Copy the custom attribute values to the new protocol document.
     * @param srcProtocolDocument
     * @param destProtocolDocument
     */
    protected void copyCustomDataAttributeValues(ProtocolDocument srcProtocolDocument, ProtocolDocument destProtocolDocument) {
        destProtocolDocument.initialize();
        for (Entry<String, CustomAttributeDocument> entry : destProtocolDocument.getCustomAttributeDocuments().entrySet()) {
            CustomAttributeDocument cad = srcProtocolDocument.getCustomAttributeDocuments().get(entry.getKey());
            entry.getValue().getCustomAttribute().setValue(cad.getCustomAttribute().getValue());
        }
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
}
