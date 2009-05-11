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
package org.kuali.kra.irb.actions.submit.mocks;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Mock business object service that is used by ProtocolSubmitActionServiceTest.
 */
public class MockBusinessObjectService implements BusinessObjectService {

    private boolean first = true;
    private ProtocolSubmission protocolSubmission;
    
    public MockBusinessObjectService(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public int countMatching(Class clazz, Map fieldValues) {
        return 0;
    }

    public int countMatching(Class clazz, Map positiveFieldValues, Map negativeFieldValues) {
        return 0;
    }

    public void delete(PersistableBusinessObject bo) {
        
    }

    public void delete(List<? extends PersistableBusinessObject> boList) {
        
    }

    public void deleteMatching(Class clazz, Map fieldValues) {
        
    }

    public Collection findAll(Class clazz) {
        return null;
    }

    public PersistableBusinessObject findByPrimaryKey(Class clazz, Map primaryKeys) {
        return null;
    }

    public Collection findMatching(Class clazz, Map fieldValues) {
        return null;
    }

    public Collection findMatchingOrderBy(Class clazz, Map fieldValues, String sortField, boolean sortAscending) {
        return null;
    }

    public BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName) {
        return null;
    }

    public void linkAndSave(PersistableBusinessObject bo) {
        
    }

    public void linkAndSave(List<PersistableBusinessObject> businessObjects) {
        
    }

    public void linkUserFields(PersistableBusinessObject bo) {
        
    }

    public void linkUserFields(List<PersistableBusinessObject> bos) {
        
    }

    public PersistableBusinessObject retrieve(PersistableBusinessObject object) {
        return null;
    }

    public void save(PersistableBusinessObject bo) {
        if (first) {
            first = false;
            ProtocolSubmission ps = (ProtocolSubmission) bo;
            testEquality(protocolSubmission, ps);
        }
    }

    public void save(List businessObjects) {
        
    }
    
    private void testEquality(ProtocolSubmission expected, ProtocolSubmission actual) {
        assertEquals(expected.getProtocolId(), actual.getProtocolId());
        assertEquals(expected.getProtocolNumber(), actual.getProtocolNumber());
        assertEquals(expected.getSequenceNumber(), actual.getSequenceNumber());
        assertEquals(expected.getSubmissionNumber(), actual.getSubmissionNumber());
        assertEquals(expected.getSubmissionTypeCode(), actual.getSubmissionTypeCode());
        assertEquals(expected.getSubmissionTypeQualifierCode(), actual.getSubmissionTypeQualifierCode());
        assertEquals(expected.getProtocolReviewTypeCode(), actual.getProtocolReviewTypeCode());
        assertEquals(expected.getSubmissionStatusCode(), actual.getSubmissionStatusCode());
        assertEquals(expected.getCommitteeId(), actual.getCommitteeId());
        assertEquals(expected.getScheduleId(), actual.getScheduleId());
        assertEquals(expected.getProtocolReviewers().size(), actual.getProtocolReviewers().size());
        for (int i = 0; i < expected.getProtocolReviewers().size(); i++) {
            testEquality(expected.getProtocolReviewers().get(i), actual.getProtocolReviewers().get(i));
        }
        assertEquals(expected.getExemptStudiesCheckList().size(), actual.getExemptStudiesCheckList().size());
        for (int i = 0; i < expected.getExemptStudiesCheckList().size(); i++) {
            testEquality(expected.getExemptStudiesCheckList().get(i), actual.getExemptStudiesCheckList().get(i));
        }
        assertEquals(expected.getExpeditedReviewCheckList().size(), actual.getExpeditedReviewCheckList().size());
        for (int i = 0; i < expected.getExpeditedReviewCheckList().size(); i++) {
            testEquality(expected.getExpeditedReviewCheckList().get(i), actual.getExpeditedReviewCheckList().get(i));
        }
    }

    private void testEquality(ProtocolExpeditedReviewCheckListItem expected, ProtocolExpeditedReviewCheckListItem actual) {
        assertEquals(expected.getProtocolExpeditedCheckListId(), actual.getProtocolExpeditedCheckListId());
        assertEquals(expected.getProtocolId(), actual.getProtocolId());
        assertEquals(expected.getProtocolNumber(), actual.getProtocolNumber());
        assertEquals(expected.getSequenceNumber(), actual.getSequenceNumber());
        assertEquals(expected.getSubmissionNumber(), actual.getSubmissionNumber());
        assertEquals(expected.getExpeditedReviewCheckListCode(), actual.getExpeditedReviewCheckListCode());  
    }

    private void testEquality(ProtocolExemptStudiesCheckListItem expected, ProtocolExemptStudiesCheckListItem actual) {
        assertEquals(expected.getProtocolExemptCheckListId(), actual.getProtocolExemptCheckListId());
        assertEquals(expected.getProtocolId(), actual.getProtocolId());
        assertEquals(expected.getProtocolNumber(), actual.getProtocolNumber());
        assertEquals(expected.getSequenceNumber(), actual.getSequenceNumber());
        assertEquals(expected.getSubmissionNumber(), actual.getSubmissionNumber());
        assertEquals(expected.getExemptStudiesCheckListCode(), actual.getExemptStudiesCheckListCode());
    }

    private void testEquality(ProtocolReviewer expected, ProtocolReviewer actual) {
        assertEquals(expected.getProtocolReviewerId(), actual.getProtocolReviewerId());
        assertEquals(expected.getProtocolId(), actual.getProtocolId());
        assertEquals(expected.getProtocolNumber(), actual.getProtocolNumber());
        assertEquals(expected.getSequenceNumber(), actual.getSequenceNumber());
        assertEquals(expected.getSubmissionNumber(), actual.getSubmissionNumber());
        assertEquals(expected.getPersonId(), actual.getPersonId());
        assertEquals(expected.getNonEmployeeFlag(), actual.getNonEmployeeFlag());
        assertEquals(expected.getReviewerTypeCode(), actual.getReviewerTypeCode());
    }
}
