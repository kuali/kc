/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.amendrenew;

import java.sql.Date;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Protocol Amendment/Renewal Service Implementation.
 */
@Transactional
public class ProtocolAmendRenewServiceImpl implements ProtocolAmendRenewService {

    private static final String AMEND_ID = "A";
    private static final String RENEW_ID = "R";
    private static final int DIGIT_COUNT = 3;
    private static final String AMEND_NEXT_VALUE = "nextAmendValue";
    private static final String RENEW_NEXT_VALUE = "nextRenewValue";
    
    private BusinessObjectService businessObjectService;
    private ProtocolCopyService protocolCopyService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the Protocol Copy Service.
     * @param protocolCopyService
     */
    public void setProtocolCopyService(ProtocolCopyService protocolCopyService) {
        this.protocolCopyService = protocolCopyService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#createAmendment(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean)
     */
    public String createAmendment(ProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        ProtocolDocument amendProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolAmendmentNumber(protocolDocument));
        
        ProtocolAction protocolAction = createCreateAmendmentProtocolAction(protocolDocument.getProtocol());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, amendProtocolDocument, amendmentBean);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#createRenewal(org.kuali.kra.irb.ProtocolDocument)
     */
    public String createRenewal(ProtocolDocument protocolDocument) throws Exception {
        ProtocolDocument renewProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolRenewalNumber(protocolDocument));
        
        ProtocolAction protocolAction = createCreateRenewalProtocolAction(protocolDocument.getProtocol());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        ProtocolAmendRenewal protocolAmendRenewal = createAmendmentRenewal(protocolDocument, renewProtocolDocument, null);
        renewProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        
        businessObjectService.save(protocolDocument);
        businessObjectService.save(renewProtocolDocument);
        
        return renewProtocolDocument.getDocumentNumber();
    }
    
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#createRenewalWithAmendment(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean)
     */
    public String createRenewalWithAmendment(ProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        ProtocolDocument renewProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolRenewalNumber(protocolDocument));
        
        ProtocolAction protocolAction = createCreateRenewalProtocolAction(protocolDocument.getProtocol());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, renewProtocolDocument, amendmentBean);
    }
    
    /**
     * Create an Amendment.  Adds an amendment entry into the database as well as the modules that
     * can be modified with this amendment.
     * @param protocolDocument the original protocol document to be amended
     * @param amendProtocolDocument the amended protocol document
     * @param amendmentBean the amendment bean info
     * @return
     */
    private String createAmendment(ProtocolDocument protocolDocument, ProtocolDocument amendProtocolDocument,
                                   ProtocolAmendmentBean amendmentBean) {

        ProtocolAmendRenewal protocolAmendRenewal = createAmendmentRenewal(protocolDocument, amendProtocolDocument, amendmentBean.getSummary());
        addModules(protocolAmendRenewal, amendmentBean);
        amendProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        
        businessObjectService.save(protocolDocument);
        businessObjectService.save(amendProtocolDocument);
        
        return amendProtocolDocument.getDocumentNumber();
    }

    /**
     * Generate the protocol number for an amendment.  The protocol number for
     * an amendment is the original protocol's number appended with "Axxx" where
     * "xxx" is the next sequence number.  A protocol can have more than one
     * amendment.
     * @param protocolDocument
     * @return
     */
    private String generateProtocolAmendmentNumber(ProtocolDocument protocolDocument) {
        return generateProtocolNumber(protocolDocument, AMEND_ID, AMEND_NEXT_VALUE);
    }
    
    /**
     * Generate the protocol number for an amendment.  The protocol number for
     * an renewal is the original protocol's number appended with "Axxx" where
     * "xxx" is the next sequence number.  A protocol can have more than one
     * amendment.
     * @param protocolDocument
     * @return
     */
    private String generateProtocolRenewalNumber(ProtocolDocument protocolDocument) {
        return generateProtocolNumber(protocolDocument, RENEW_ID, RENEW_NEXT_VALUE);
    }
    
    /**
     * Generate the protocol number for an amendment or renewal.
     * @param protocolDocument
     * @return
     */
    private String generateProtocolNumber(ProtocolDocument protocolDocument, String letter, String nextValueKey) {
        String protocolNumber = protocolDocument.getProtocol().getProtocolNumber();
        Integer nextValue = protocolDocument.getDocumentNextValue(nextValueKey);
        String s = nextValue.toString();
        int length = s.length();
        for (int i = 0; i < DIGIT_COUNT - length; i++) {
            s = "0" + s;
        }
        return protocolNumber + letter + s;
    }
    
    /**
     * Create an Amendment Entry.
     * @param protocolDocument the original protocol document
     * @param amendProtocolDocument the amended protocol document
     * @param amendmentBean the user form containing the summary and modules to be amended
     * @return
     */
    private ProtocolAmendRenewal createAmendmentRenewal(ProtocolDocument protocolDocument, ProtocolDocument amendProtocolDocument, String summary) {
        ProtocolAmendRenewal protocolAmendRenewal = new ProtocolAmendRenewal();
        protocolAmendRenewal.setProtoAmendRenNumber(amendProtocolDocument.getProtocol().getProtocolNumber());
        protocolAmendRenewal.setDateCreated(new Date(System.currentTimeMillis()));
        protocolAmendRenewal.setSummary(summary);
        protocolAmendRenewal.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        protocolAmendRenewal.setProtocolId(amendProtocolDocument.getProtocol().getProtocolId());
        protocolAmendRenewal.setProtocol(amendProtocolDocument.getProtocol());
        protocolAmendRenewal.setSequenceNumber(0);
        return protocolAmendRenewal;
    }

    /**
     * Add the modules to the amendment that were selected by the end user.
     * @param amendmentEntry
     * @param amendmentBean
     */
    private void addModules(ProtocolAmendRenewal amendmentEntry, ProtocolAmendmentBean amendmentBean) {
        if (amendmentBean.getGeneralInfo()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.GENERAL_INFO));
        }
        
        if (amendmentBean.getAddModifyAttachments()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.ADD_MODIFY_ATTACHMENTS));
        }
        
        if (amendmentBean.getAreasOfResearch()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.AREAS_OF_RESEARCH));
        }
        
        if (amendmentBean.getFundingSource()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.FUNDING_SOURCE));
        }
        
        if (amendmentBean.getProtocolOrganizations()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_ORGANIZATIONS));
        }
        
        if (amendmentBean.getProtocolPersonnel()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_PERSONNEL));
        }
        
        if (amendmentBean.getProtocolReferences()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_REFERENCES));
        }
        
        if (amendmentBean.getSubjects()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SUBJECTS));
        }
        
        if (amendmentBean.getSpecialReview()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SPECIAL_REVIEW));
        }
        
        if (amendmentBean.getOthers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.OTHERS));
        }
    }
    
    /**
     * Create a module entry.
     * @param amendmentEntry
     * @param moduleTypeCode
     * @return
     */
    private ProtocolAmendRenewModule createModule(ProtocolAmendRenewal amendmentEntry, String moduleTypeCode) {
        ProtocolAmendRenewModule module = new ProtocolAmendRenewModule();
        module.setProtocolAmendRenewalNumber(amendmentEntry.getProtoAmendRenNumber());
        module.setProtocolAmendRenewal(amendmentEntry);
        module.setProtocolAmendRenewalId(amendmentEntry.getId());
        module.setProtocolNumber(amendmentEntry.getProtocolNumber());
        module.setProtocolModuleTypeCode(moduleTypeCode);
        return module;
    }
    
    /**
     * Create a Protocol Action indicating that an amendment has been created.
     * @param protocol
     * @return a protocol action
     */
    private ProtocolAction createCreateAmendmentProtocolAction(Protocol protocol) {
        return new ProtocolAction(protocol, null, ProtocolActionType.AMENDMENT_CREATED);
    }
    
    /**
     * Create a Protocol Action indicating that a renewal has been created.
     * @param protocol
     * @return a protocol action
     */
    private ProtocolAction createCreateRenewalProtocolAction(Protocol protocol) {
        return new ProtocolAction(protocol, null, ProtocolActionType.RENEWAL_CREATED);
    }
}
