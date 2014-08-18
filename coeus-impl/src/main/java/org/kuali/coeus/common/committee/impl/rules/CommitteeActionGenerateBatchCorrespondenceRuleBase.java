/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceDetailBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class implements the business rules for submitting a generate batch correspondence request.
 */
public abstract class CommitteeActionGenerateBatchCorrespondenceRuleBase extends KcTransactionalDocumentRuleBase
                                                            implements KcBusinessRule<CommitteeActionGenerateBatchCorrespondenceEventBase> {

    private static final String BATCH_CORRESPONDENCE_TYPE_FIELD = "committeeHelper.generateBatchCorrespondenceTypeCode";
    private static final String START_DATE_FIELD = "committeeHelper.generateStartDate";
    private static final String END_DATE_FIELD = "committeeHelper.generateEndDate";
    private static final String PROTO_CORRESP_TYPE_CODE = "protoCorrespTypeCode";
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";
    
    ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService;

    /**
     * Verify input data and display warning messages if templates are missing.
     * 
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
    public boolean processRules(CommitteeActionGenerateBatchCorrespondenceEventBase event) {
        boolean rulePassed = true;
        boolean dateNull = false;
        
        if (StringUtils.isEmpty(event.getBatchCorrespondenceTypeCode())) {
            reportError(BATCH_CORRESPONDENCE_TYPE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_NOT_SPECIFIED);
            rulePassed = false;
        }
        
        if (event.getStartDate() == null) {
            reportError(START_DATE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_GENERATE_START_DATE_NOT_SPECIFIED);
            dateNull = true;
            rulePassed = false;
        }

        if (event.getEndDate() == null) {
            reportError(END_DATE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_GENERATE_END_DATE_NOT_SPECIFIED);
            dateNull = true;
            rulePassed = false;
        }

        if (!dateNull && event.getEndDate().before(event.getStartDate())) {
            reportError(END_DATE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_GENERATE_END_DATE_BEFORE_START_DATE);
            rulePassed = false;
        }
        if (rulePassed) {
            missingTemplates(event.getBatchCorrespondenceTypeCode(), event.getCommitteeId());
        }

 
        return rulePassed;
    }

    /**
     * Display warning for missing templates.
     */
    private boolean missingTemplates(String batchCorrespondenceTypeCode, String committeeId) {
        List<String> missingTemplates = new ArrayList<String>();
        
        BatchCorrespondenceBase batchCorrespondence = lookupBatchCorrespondence(batchCorrespondenceTypeCode);
            
        for (BatchCorrespondenceDetailBase batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
            if (getProtocolCorrespondenceTemplateService().getProtocolCorrespondenceTemplate(committeeId, 
                    batchCorrespondenceDetail.getProtoCorrespTypeCode()) == null) {
                missingTemplates.add(getProtocolCorrespondenceDescription(batchCorrespondenceDetail.getProtoCorrespTypeCode()));
            }
            
        }
        
        if (!StringUtils.isBlank(batchCorrespondence.getFinalActionCorrespType())) {
            if (getProtocolCorrespondenceTemplateService().getProtocolCorrespondenceTemplate(committeeId, 
                    batchCorrespondence.getFinalActionCorrespType()) == null) {
                missingTemplates.add(getProtocolCorrespondenceDescription(batchCorrespondence.getFinalActionCorrespType()));
            }
        }

        if (missingTemplates.isEmpty()) {
            return false;
        } else {
            reportSoftError(BATCH_CORRESPONDENCE_TYPE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_GENERATE_MISSING_TEMPLATES, missingTemplates.toString());
            return true;
        }
    }
    
    /**
     * 
     * This method looks up the BatchCorrespondenceBase business object via the batchCorrespondenceTypeCode.
     * @param batchCorrespondenceTypeCode
     * @return the BatchCorrespondenceBase business object
     */
    private BatchCorrespondenceBase lookupBatchCorrespondence(String batchCorrespondenceTypeCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceTypeCode);

        return getBusinessObjectService().findByPrimaryKey(getBatchCorrespondenceBOClassHook(), fieldValues);
    }

    protected abstract  Class<? extends BatchCorrespondenceBase> getBatchCorrespondenceBOClassHook();
    
    
    
    private ProtocolCorrespondenceTemplateService getProtocolCorrespondenceTemplateService() {
        if (protocolCorrespondenceTemplateService == null) {
            
            protocolCorrespondenceTemplateService = KcServiceLocator.getService(getProtocolCorrespondenceTemplateServiceClassHook());
        }
        return protocolCorrespondenceTemplateService;
    }
    
    protected abstract Class<? extends ProtocolCorrespondenceTemplateService> getProtocolCorrespondenceTemplateServiceClassHook();
    

    
    /**
     * 
     * This method gets the name of the template
     * @param protocolCorrespondenceTypeCode
     * @return name of the template
     */
    private String getProtocolCorrespondenceDescription(String protocolCorrespondenceTypeCode) {
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put(PROTO_CORRESP_TYPE_CODE, protocolCorrespondenceTypeCode);

        return getBusinessObjectService().findByPrimaryKey(getProtocolCorrespondenceTypeBOClassHook(), primaryKeys).getDescription();  
    }

    protected abstract Class<? extends ProtocolCorrespondenceTypeBase> getProtocolCorrespondenceTypeBOClassHook();
    
    
}
