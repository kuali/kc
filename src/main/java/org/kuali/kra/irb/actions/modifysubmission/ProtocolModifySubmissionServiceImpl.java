/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.modifysubmission;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.proccessbillable.ProtocolProccessBillableService;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * 
 * This class implements the required functions to change and persist a protocol submission.
 */
public class ProtocolModifySubmissionServiceImpl extends ProtocolProccessBillableService implements ProtocolModifySubmissionService  {
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionService#modifySubmisison(org.kuali.kra.irb.actions.submit.ProtocolSubmission, org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean)
     */
    public void modifySubmisison(ProtocolDocument protocolDocument, ProtocolModifySubmissionBean bean) throws Exception {
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        submission.setSubmissionTypeCode(bean.getSubmissionTypeCode());
        submission.setSubmissionTypeQualifierCode(bean.getSubmissionQualifierTypeCode());
        this.proccessBillable(protocolDocument.getProtocol(), bean.isBillable());
        
        String existingReviewType = submission.getProtocolReviewTypeCode();
        String newReviewType = bean.getProtocolReviewTypeCode();
        
        if (existingReviewType.equals(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE) 
                || existingReviewType.equals(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE)) {
            //remove existingCheckBoxes if old review type is EXPEDITED_REVIEW_TYPE_CODE or EXEMPT_STUDIES_REVIEW_TYPE_CODE
            deleteExistingCheckBoxes(submission);
        }
        if (!existingReviewType.equals(newReviewType)) {
            //if new Review Type is different than old review type, get the proper review type from DB and add to submission, 
            //and update code
            proccessNewReviewType(submission, newReviewType);
        }
        if (newReviewType.equals(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE)) {
            //if new review type is EXPEDITED_REVIEW_TYPE_CODE proccess those check boxes
            proccessExpeditedReviewCheckBoxes(submission, bean);
        } else if (newReviewType.equals(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE)) {
            //if new review type is EXEMPT_STUDIES_REVIEW_TYPE_CODE, proccess those check boxes
            proccessExemptStudiesCheckBoxes(submission, bean);
        }
        
        documentService.saveDocument(protocolDocument);
    }
    
    protected void deleteExistingCheckBoxes(ProtocolSubmission submission) {
        this.businessObjectService.delete(submission.getExemptStudiesCheckList());
        this.businessObjectService.delete(submission.getExpeditedReviewCheckList());
        submission.getExemptStudiesCheckList().clear();
        submission.getExpeditedReviewCheckList().clear();
    }
    
    protected void proccessNewReviewType(ProtocolSubmission submission, String newReviewType) {
        Map fieldValues = new HashMap();
        fieldValues.put("PROTOCOL_REVIEW_TYPE_CODE", newReviewType);
        ProtocolReviewType newType = (ProtocolReviewType) this.businessObjectService.findByPrimaryKey(ProtocolReviewType.class, fieldValues);
        submission.setProtocolReviewType(newType);
        submission.setProtocolReviewTypeCode(newType.getReviewTypeCode());
    }
    
    protected void proccessExemptStudiesCheckBoxes(ProtocolSubmission submission, ProtocolModifySubmissionBean bean) {
        for (ExemptStudiesCheckListItem beanItem : bean.getExemptStudiesCheckList()) {
            if (beanItem.getChecked()) {
                ProtocolExemptStudiesCheckListItem newItem = new ProtocolExemptStudiesCheckListItem();
                newItem.setExemptStudiesCheckListCode(beanItem.getExemptStudiesCheckListCode());
                newItem.setProtocol(submission.getProtocol());
                newItem.setProtocolId(submission.getProtocolId());
                newItem.setProtocolNumber(submission.getProtocolNumber());
                newItem.setProtocolSubmission(submission);
                newItem.setSubmissionIdFk(submission.getSubmissionId());
                newItem.setSubmissionNumber(submission.getSubmissionNumber());
                newItem.setSequenceNumber(submission.getSequenceNumber());
                submission.getExemptStudiesCheckList().add(newItem);
            }
        }
    }
    
    protected void proccessExpeditedReviewCheckBoxes(ProtocolSubmission submission, ProtocolModifySubmissionBean bean) {
        for (ExpeditedReviewCheckListItem beanItem : bean.getExpeditedReviewCheckList()) {
            if (beanItem.getChecked()) {
                ProtocolExpeditedReviewCheckListItem newItem = new ProtocolExpeditedReviewCheckListItem();
                newItem.setExpeditedReviewCheckListCode(beanItem.getExpeditedReviewCheckListCode());
                newItem.setProtocol(submission.getProtocol());
                newItem.setProtocolId(submission.getProtocolId());
                newItem.setProtocolNumber(submission.getProtocolNumber());
                newItem.setProtocolSubmission(submission);
                newItem.setSubmissionIdFk(submission.getSubmissionId());
                newItem.setSubmissionNumber(submission.getSubmissionNumber());
                newItem.setSequenceNumber(submission.getSequenceNumber());
                submission.getExpeditedReviewCheckList().add(newItem);
            }
        }
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
