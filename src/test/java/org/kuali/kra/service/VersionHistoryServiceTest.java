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
package org.kuali.kra.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.service.impl.VersionHistoryServiceImpl;
import org.kuali.kra.service.impl.versioningartifacts.SequenceOwnerImpl;
import org.kuali.kra.service.impl.versioningartifacts.SimpleSequenceOwner;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

public class VersionHistoryServiceTest {
    private static final String SEQUENCE_OWNER_NAME_A = "Owner A";
    private static final String SEQUENCE_OWNER_NAME_B = "Owner B";
    
    private VersionHistoryService service;
    private SequenceOwner<SequenceOwnerImpl> sequenceOwner1;
    private SequenceOwner<SimpleSequenceOwner> sequenceOwner2;
    private SequenceOwner<SequenceOwnerImpl> sequenceOwner3;
    private SequenceOwner<SequenceOwnerImpl> sequenceOwner4;
    private SequenceOwner<SimpleSequenceOwner> sequenceOwner5;
    
    @Before
    public void setUp() {
        sequenceOwner1 = createSequenceOwner(1001);
        sequenceOwner2 = createSimpleSequenceOwner(2001);
        sequenceOwner3 = createSequenceOwner(1002);
        sequenceOwner4 = createSequenceOwner(1003);
        sequenceOwner5 = createSimpleSequenceOwner(2002);
        
        VersionHistoryServiceImpl impl = new VersionHistoryServiceImpl();
        impl.setBusinessObjectService(new MockBusinessObjectService());
        service = impl;
    }

    @After
    public void tearDown() {
        sequenceOwner1 = sequenceOwner3 = sequenceOwner4 = null;
        sequenceOwner2 = sequenceOwner5 = null;
    }
    
    @Test
    public void testFindingNewVersionHistory_OneAdded() {
        createAndCheckNewActiveVersion(sequenceOwner1, "jtester");
        checkOwner(SEQUENCE_OWNER_NAME_A, SequenceOwnerImpl.class, 1, 1001, "jtester");
    }
    
    @Test
    public void testVersionHistory_MultipleMixedTypesAdded() {
        createAndCheckNewActiveVersion(sequenceOwner1, "user1");
        createAndCheckNewActiveVersion(sequenceOwner2, "user2");
        createAndCheckNewActiveVersion(sequenceOwner3, "user3");
        createAndCheckNewActiveVersion(sequenceOwner4, "user4");
        createAndCheckNewActiveVersion(sequenceOwner5, "user5");
        
        checkOwner(SEQUENCE_OWNER_NAME_A, SequenceOwnerImpl.class, 3, 1003, "user4");
        checkOwner(SEQUENCE_OWNER_NAME_B, SimpleSequenceOwner.class, 2, 2002, "user5");
    }
    private void checkOwner(String ownerName, Class<? extends SequenceOwner<?>> ownerType, int expectedNumberOfVersionsForName, 
                                 int expectedActiveSequenceNumber, String userNameForActiveVersion) {
        
        VersionHistory foundHistory = verifyFindActiveVersion(ownerName, ownerType, expectedActiveSequenceNumber, userNameForActiveVersion);
        verifyLoadHistories(ownerName, ownerType, expectedNumberOfVersionsForName, foundHistory, expectedActiveSequenceNumber);
    }
    
    private VersionHistory createAndCheckNewActiveVersion(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, String userName) {
        VersionHistory history = service.createVersionHistory(sequenceOwner, VersionStatus.ACTIVE, userName);
        Assert.assertEquals(VersionStatus.ACTIVE, history.getStatus());
        return history;
    }

    private SequenceOwner<SequenceOwnerImpl> createSequenceOwner(int sequenceNumber) {
        SequenceOwnerImpl owner = new SequenceOwnerImpl();
        owner.setName(SEQUENCE_OWNER_NAME_A);
        owner.setSequenceNumber(sequenceNumber);
        return owner;
    }

    private SequenceOwner<SimpleSequenceOwner> createSimpleSequenceOwner(int sequenceNumber) {
        SimpleSequenceOwner owner = new SimpleSequenceOwner();
        owner.setName(SEQUENCE_OWNER_NAME_B);
        owner.setSequenceNumber(sequenceNumber);
        return owner;
    }
    
    private VersionHistory verifyFindActiveVersion(String ownerName, Class<? extends SequenceOwner<?>> ownerType, int expectedActiveSequenceNumber, 
                                                    String userNameForActiveVersion) {
        VersionHistory foundHistory = service.findActiveVersion(ownerType, ownerName);
        Assert.assertNotNull("Found history object was null", foundHistory);
        Assert.assertEquals(expectedActiveSequenceNumber, foundHistory.getSequenceOwner().getSequenceNumber().intValue());
        Assert.assertEquals(userNameForActiveVersion, foundHistory.getUserId());
        Assert.assertTrue("Version not active", foundHistory.isActiveVersion());
        return foundHistory;
    }
    
    private void verifyLoadHistories(String ownerName, Class<? extends SequenceOwner<?>> ownerType, int expectedNumberOfVersionsForName, 
                                        VersionHistory foundHistory, int expectedActiveSequenceNumber) {
        List<VersionHistory> histories = service.loadVersionHistory(ownerType, ownerName);
        Assert.assertEquals(expectedNumberOfVersionsForName, histories.size());
        for(VersionHistory vh: histories) {
            SequenceOwner<?> owner = vh.getSequenceOwner();
            if(owner.getSequenceNumber() == expectedActiveSequenceNumber) {
                Assert.assertEquals(VersionStatus.ACTIVE, vh.getStatus());
            } else {
                Assert.assertEquals(VersionStatus.ARCHIVED, vh.getStatus());
            }
        }
    }
    
    /**
     * This class mocks out the BusinessObjectService
     */
    private class MockBusinessObjectService implements BusinessObjectService {
        private Map<Long, VersionHistory> mockHistory = new TreeMap<Long, VersionHistory>();
        
        public Collection findAll(Class clazz) {
            return mockHistory.values();
        }
        
        public void save(PersistableBusinessObject bo) {
            VersionHistory versionHistory = (VersionHistory) bo;
            if(versionHistory.getVersionHistoryId() == null) {
                versionHistory.setVersionHistoryId(System.currentTimeMillis() + versionHistory.hashCode());
            }
            mockHistory.put(versionHistory.getVersionHistoryId(), versionHistory);
        }

        public PersistableBusinessObject findByPrimaryKey(Class clazz, Map primaryKeys) { return null; }
        
        public Collection findMatching(Class clazz, Map fieldValues) {
            Collection collection;
            if(clazz.equals(VersionHistory.class)) {
                collection = handleVersionHistoryClass(fieldValues, (String) fieldValues.get(VersionHistoryServiceImpl.SEQUENCE_OWNER_REFERENCE_VERSION_NAME));
            } else {
                collection = handleSequenceOwnerImplClass(clazz, "name");
            }
            
            return collection;
        }

        /**
         * This method...
         * @param clazz
         * @param fieldValues
         * @param collection
         * @param versionName
         */
        private Collection handleSequenceOwnerImplClass(Class clazz, String versionNameField) {
            Collection collection = new ArrayList();
            String className = clazz.getName();
            for(Long id: mockHistory.keySet()) {
                VersionHistory vh = mockHistory.get(id);
                SequenceOwner sequenceOwner = vh.getSequenceOwner();
                String versionName = (String) ObjectUtils.getPropertyValue(sequenceOwner, sequenceOwner.getVersionNameField());
                if(vh.getSequenceOwnerClassName().equals(className) && vh.getSequenceOwnerVersionNameValue().equals(versionName)) {
                    collection.add(sequenceOwner);
                }
            }
            return collection;
        }

        /**
         * This method...
         * @param fieldValues
         * @param collection
         * @param versionName
         */
        private Collection handleVersionHistoryClass(Map fieldValues, String versionName) {
            Collection collection = new ArrayList();
            String className = (String) fieldValues.get(VersionHistoryServiceImpl.SEQUENCE_OWNER_CLASS_NAME_FIELD);
            for(Long id: mockHistory.keySet()) {
                VersionHistory vh = mockHistory.get(id);
                boolean conditionsForInclusion = vh.getSequenceOwnerClassName().equals(className) && vh.getSequenceOwnerVersionNameValue().equals(versionName);
                if(fieldValues.containsKey(VersionHistoryServiceImpl.VERSION_STATUS_FIELD)) {
                    conditionsForInclusion &= fieldValues.get(VersionHistoryServiceImpl.VERSION_STATUS_FIELD).equals(vh.getStatusForOjb());
                }
                if(conditionsForInclusion) {
                    collection.add(vh);
                }
            }
            return collection;
        }
        
        @SuppressWarnings("unchecked")
        public Collection findMatchingOrderBy(Class clazz, Map fieldValues, String sortField, boolean sortAscending) { return null; }
        public BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName) { return null; }
        public void linkAndSave(PersistableBusinessObject bo) { }
        public void linkAndSave(List<PersistableBusinessObject> businessObjects) { }
        public void linkUserFields(PersistableBusinessObject bo) { }
        public void linkUserFields(List<PersistableBusinessObject> bos) { }
        public PersistableBusinessObject retrieve(PersistableBusinessObject object) { return null; }
        
        public void save(List businessObjects) {
            for(Object bo: businessObjects) {
                save((PersistableBusinessObject) bo);
            }
        }
        
        
        public int countMatching(Class clazz, Map fieldValues) { return 0; }
        public int countMatching(Class clazz, Map positiveFieldValues, Map negativeFieldValues) { return 0;}
        public void delete(PersistableBusinessObject bo) {}
        public void delete(List<? extends PersistableBusinessObject> boList) { }

        public void deleteMatching(Class clazz, Map fieldValues) {}        
    } 
}
