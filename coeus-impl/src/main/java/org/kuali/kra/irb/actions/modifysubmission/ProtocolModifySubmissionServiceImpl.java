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
package org.kuali.kra.irb.actions.modifysubmission;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.proccessbillable.ProtocolProccessBillableService;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class implements the required functions to change and persist a protocol submission.
 */
public class ProtocolModifySubmissionServiceImpl extends ProtocolProccessBillableService implements ProtocolModifySubmissionService  {
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;

    @Override
    public void modifySubmission(ProtocolDocument protocolDocument, ProtocolModifySubmissionBean bean) throws Exception {
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
                newItem.setProtocol((Protocol) submission.getProtocol());
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
                newItem.setProtocol((Protocol) submission.getProtocol());
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
