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
package org.kuali.kra.protocol.actions.copy;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolNotepadBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.protocol.ProtocolNumberServiceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * The ProtocolBase Copy Service creates a new ProtocolBase Document
 * based upon a current document.
 * 
 * The service uses the following steps in order to copy a protocol:
 * <ol>
 * <li>The Document Service is used to create a new ProtocolBase
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
public abstract class ProtocolCopyServiceImplBase<GenericProtocolDocument extends ProtocolDocumentBase> implements ProtocolCopyService<GenericProtocolDocument> {
    
    private static final String PROTOCOL_CREATED = "ProtocolBase created";
    private static final String CUSTOM_ATTRIBUTE = "customAttribute";

    private DocumentService documentService;
    private SystemAuthorizationService systemAuthorizationService;
    private KcAuthorizationService kraAuthorizationService;
    private KcPersonService kcPersonService;
    private SequenceAccessorService sequenceAccessorService;
    private RoleService roleService;
    
    @Override
    public GenericProtocolDocument copyProtocol(GenericProtocolDocument srcDoc) throws Exception {
        return copyProtocol(srcDoc, getProtocolNumberServiceHook().generateProtocolNumber(), false);
    }
    
    @Override
    public GenericProtocolDocument copyProtocol(GenericProtocolDocument srcDoc, String protocolNumber, boolean isAmendmentRenewal) throws Exception {
        GenericProtocolDocument newDoc = createNewProtocol(srcDoc, protocolNumber, isAmendmentRenewal);        
        initializeAuthorization(srcDoc, newDoc);

        return newDoc;
    }
    
    /**
     * Create a new protocol based upon a source protocol.  Only copy over the
     * properties necessary for the initial creation of the protocol.  This will
     * give us the protocol number to use when copying over the remainder of the
     * protocol.
     * "Copy" may be a pure copy or copy for amendment &amp; renewal creation.
     * if for amendment/renewal, then notes &amp; attachments will be copied.
     */
    protected GenericProtocolDocument createNewProtocol(GenericProtocolDocument srcDoc, String protocolNumber, boolean isAmendmentRenewal) throws Exception {

        @SuppressWarnings("unchecked")
        GenericProtocolDocument newDoc = (GenericProtocolDocument) getDocumentService().getNewDocument(srcDoc.getClass());
            
        newDoc.getProtocol().setProtocolNumber(protocolNumber);
        newDoc.getProtocol().setSequenceNumber(0);

        newDoc.getDocumentHeader().setDocumentTemplateNumber(srcDoc.getDocumentNumber());
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber(getSequenceNumberNameHook(), newDoc.getProtocol().getClass());
        newDoc.getProtocol().setProtocolId(nextProtocolId);
        
        copyOverviewProperties(srcDoc, newDoc);
        copyRequiredProperties(srcDoc, newDoc);
        copyAdditionalProperties(srcDoc, newDoc);
        copyProtocolLists(srcDoc, newDoc);
        if (isAmendmentRenewal && !srcDoc.getProtocol().isAmendment()) {
            removeDeletedAttachment(newDoc.getProtocol());
            
        }
        copyCustomDataAttributeValues(srcDoc, newDoc);
        
        org.kuali.kra.protocol.actions.ProtocolActionBase protocolAction = getProtocolActionNewInstanceHook(newDoc.getProtocol(), null, getProtocolActionProtocolCreatedCodeHook());
        
        protocolAction.setComments(PROTOCOL_CREATED);
        newDoc.getProtocol().getProtocolActions().add(protocolAction);

        if (!isAmendmentRenewal) {
            newDoc.getProtocol().setAttachmentProtocols(new ArrayList<>());
            newDoc.getProtocol().setNotepads(new ArrayList<>());
            if (newDoc.getProtocol().getProtocolPersons() != null) {
                for (ProtocolPersonBase person : newDoc.getProtocol().getProtocolPersons()) {
                    person.setAttachmentPersonnels(new ArrayList<>());
                }
            }
        } else {
            initPersonAttachments(newDoc.getProtocol());
        }
        
        return newDoc;
    }

    private void removeDeletedAttachment(ProtocolBase protocol) {
        List<Integer> documentIds = new ArrayList<>();
        List<ProtocolAttachmentProtocolBase> attachments = new ArrayList<>();
        for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("3".equals(attachment.getDocumentStatusCode())) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        if (!documentIds.isEmpty()) {
            for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
                attachment.setProtocol(protocol);
                if (!documentIds.contains(attachment.getDocumentId())) {
                    attachments.add(attachment);
                }
            }
            protocol.setAttachmentProtocols(attachments);
        }
        
    }


    /**
     * This method initializes the personId of the person
     */
    private void initPersonId(ProtocolBase protocol) {
        for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
            Integer nextPersonId = sequenceAccessorService.getNextAvailableSequenceNumber(getSequenceNumberNameHook(), person.getClass()).intValue();
            person.setProtocolPersonId(nextPersonId);
        }
    }

    /**
     * This method initializes person attachments for the newly created protocol.
     */
    private void initPersonAttachments(ProtocolBase protocol) {
        for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
            for (ProtocolAttachmentPersonnelBase attachment : person.getAttachmentPersonnels()) {
                attachment.setPersonId(person.getProtocolPersonId());
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
    protected void copyOverviewProperties(GenericProtocolDocument src, GenericProtocolDocument dest) {
        DocumentHeader srcHdr = src.getDocumentHeader();
        DocumentHeader destHdr = dest.getDocumentHeader();
        
        destHdr.setDocumentDescription(srcHdr.getDocumentDescription());
        destHdr.setExplanation(srcHdr.getExplanation());
        destHdr.setOrganizationDocumentNumber(srcHdr.getOrganizationDocumentNumber());
    }
    
    /**
     * Copy over the required properties so we can do an initial save of the document
     * in order to obtain a ProtocolId and ProtocolNumber.
     */
    protected void copyRequiredProperties(GenericProtocolDocument srcDoc, GenericProtocolDocument destDoc) {
        destDoc.getDocumentHeader().setDocumentDescription(srcDoc.getDocumentHeader().getDocumentDescription());
        destDoc.getProtocol().setProtocolTypeCode(srcDoc.getProtocol().getProtocolTypeCode());
        destDoc.getProtocol().setTitle(srcDoc.getProtocol().getTitle());
        destDoc.getProtocol().setLeadUnitNumber(srcDoc.getProtocol().getLeadUnitNumber());
        destDoc.getProtocol().setPrincipalInvestigatorId(srcDoc.getProtocol().getPrincipalInvestigatorId());
    }
    
    /**
     * Copy over the additional properties.
     */
    protected void copyAdditionalProperties(GenericProtocolDocument srcDoc, GenericProtocolDocument destDoc) {
        destDoc.getProtocol().setFdaApplicationNumber(srcDoc.getProtocol().getFdaApplicationNumber());
        destDoc.getProtocol().setReferenceNumber1(srcDoc.getProtocol().getReferenceNumber1());
        destDoc.getProtocol().setReferenceNumber2(srcDoc.getProtocol().getReferenceNumber2());
        destDoc.getProtocol().setDescription(srcDoc.getProtocol().getDescription());
        
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
     */
    protected void initializeAuthorization(GenericProtocolDocument srcDoc, GenericProtocolDocument destDoc) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthorizationService.addDocumentLevelRole(userId, getProtocolAggregatorHook(), destDoc.getProtocol());
        kraAuthorizationService.addDocumentLevelRole(userId, getProtocolApproverHook(), destDoc.getProtocol());
   
        List<Role> roles = systemAuthorizationService.getRoles(getProtocolRoleTypeHook());
        for (Role role : roles) {
            if (getRoleService().isDerivedRole(role.getId())) {
                continue;
            }
            List<String> users = kraAuthorizationService.getPrincipalsInRole(role.getName(), srcDoc.getProtocol());

            final List<KcPerson> persons = new ArrayList<>();
            for(String uid : users) {
                KcPerson person = kcPersonService.getKcPersonByPersonId(uid);
                if (person != null && person.getActive()) {
                    persons.add(person);
                }
            }

            for (KcPerson person : persons) {
                if (!StringUtils.equals(person.getPersonId(), userId)) {
                    kraAuthorizationService.addDocumentLevelRole(person.getPersonId(), role.getName(), destDoc.getProtocol());
                }
            }
        }
    }
    
    /**
     * Copy the BO lists from the original protocol to the new protocol.
     * The init() method for each BO entry will be invoked to reset its
     * primary key (id), ProtocolId, and ProtocolNumber.
     */
    protected void copyProtocolLists(GenericProtocolDocument srcDoc, GenericProtocolDocument destDoc) {
        ProtocolBase srcProtocol = srcDoc.getProtocol();
        ProtocolBase destProtocol = destDoc.getProtocol();
        
        destProtocol.setProtocolResearchAreas(deepCopy(srcProtocol.getProtocolResearchAreas()));
        destProtocol.setProtocolReferences(deepCopy(srcProtocol.getProtocolReferences()));
        destProtocol.setProtocolLocations(deepCopy(srcProtocol.getProtocolLocations()));
        destProtocol.setProtocolFundingSources(deepCopy(srcProtocol.getProtocolFundingSources()));
        destProtocol.setProtocolPersons(deepCopy(srcProtocol.getProtocolPersons()));
        destProtocol.setSpecialReviews(deepCopy(srcProtocol.getSpecialReviews()));
        // must make following call to copy exemption codes (transient objects don't get copied)
        destProtocol.cleanupSpecialReviews(srcProtocol);
        destProtocol.setAttachmentProtocols(deepCopy(srcProtocol.getAttachmentProtocols()));
        destProtocol.setNotepads(deepCopy(srcProtocol.getNotepads()));
        initPersonId(destProtocol);
    }
   
    protected <T> T deepCopy(T obj) {
        if (obj instanceof Serializable) {
            return (T) ObjectUtils.deepCopy((Serializable)obj);
        }
        return obj;
    }
    
    /**
     * Copy the custom attribute values to the new protocol document.
     */
    protected void copyCustomDataAttributeValues(GenericProtocolDocument srcProtocolDocument, GenericProtocolDocument destProtocolDocument) {
        destProtocolDocument.initialize();
        for (Entry<String, CustomAttributeDocument> entry : destProtocolDocument.getCustomAttributeDocuments().entrySet()) {
            CustomAttributeDocument cad = srcProtocolDocument.getCustomAttributeDocuments().get(entry.getKey());
            if(ObjectUtils.isNotNull(cad)) {
                if(ObjectUtils.isNull(cad.getCustomAttribute())) {
                    cad.refreshReferenceObject(CUSTOM_ATTRIBUTE);
                }
                entry.getValue().getCustomAttribute().setValue(cad.getCustomAttribute().getValue());
            }
        }
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    protected abstract Class<? extends org.kuali.kra.protocol.actions.ProtocolActionBase> getProtocolActionBOClassHook();
    protected abstract String getProtocolActionProtocolCreatedCodeHook();
    protected abstract String getSequenceNumberNameHook();
    protected abstract ProtocolNumberServiceBase getProtocolNumberServiceHook();
    protected abstract ProtocolActionBase getProtocolActionNewInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String protocolActionTypeCode);
    protected abstract String getProtocolAggregatorHook();
    protected abstract String getProtocolApproverHook();
    protected abstract String getProtocolRoleTypeHook();

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }


    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public SystemAuthorizationService getSystemAuthorizationService() {
        return systemAuthorizationService;
    }

    public KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }
}
