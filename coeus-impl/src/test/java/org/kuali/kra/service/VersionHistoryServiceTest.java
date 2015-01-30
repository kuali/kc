/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.impl.version.history.VersionHistoryServiceImpl;
import org.kuali.kra.service.impl.versioningartifacts.SequenceOwnerImpl;
import org.kuali.kra.service.impl.versioningartifacts.SimpleSequenceOwner;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.*;

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
        checkOwner(SEQUENCE_OWNER_NAME_A, SequenceOwnerImpl.class, 1, 1001, "user1");
    }
    private void checkOwner(String ownerName, Class<? extends SequenceOwner<?>> ownerType, int expectedNumberOfVersionsForName, 
                                 int expectedActiveSequenceNumber, String userNameForActiveVersion) {
        
        VersionHistory foundHistory = verifyFindActiveVersion(ownerName, ownerType, expectedActiveSequenceNumber, userNameForActiveVersion);
        verifyLoadHistories(ownerName, ownerType, expectedNumberOfVersionsForName, foundHistory, expectedActiveSequenceNumber);
    }
    
    private VersionHistory createAndCheckNewActiveVersion(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, String userName) {
        VersionHistory history = service.updateVersionHistory(sequenceOwner, VersionStatus.ACTIVE, userName);
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
        
        public PersistableBusinessObject save(PersistableBusinessObject bo) {
            VersionHistory versionHistory = (VersionHistory) bo;
            if(versionHistory.getVersionHistoryId() == null) {
                versionHistory.setVersionHistoryId(System.currentTimeMillis() + versionHistory.hashCode());
            }
            mockHistory.put(versionHistory.getVersionHistoryId(), versionHistory);
            return bo;
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
        public PersistableBusinessObject linkAndSave(PersistableBusinessObject bo) { return bo;}
        public List<? extends PersistableBusinessObject> linkAndSave(List<? extends PersistableBusinessObject> businessObjects) { return businessObjects; }
        public void linkUserFields(Object bo) { }
        public void linkUserFields(List<PersistableBusinessObject> bos) { }
        public PersistableBusinessObject retrieve(Object object) { return null; }
        
        public List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> businessObjects) {
            for(Object bo: businessObjects) {
                save((PersistableBusinessObject) bo);
            }
            
            return businessObjects;
        }
        
        
        public int countMatching(Class clazz, Map fieldValues) { return 0; }
        public int countMatching(Class clazz, Map positiveFieldValues, Map negativeFieldValues) { return 0;}
        public void delete(Object bo) {}
        public void delete(List<? extends PersistableBusinessObject> boList) { }

        public void deleteMatching(Class clazz, Map fieldValues) {}

        public <T extends BusinessObject> Collection<T> findAllOrderBy(Class<T> clazz, String sortField, boolean sortAscending) {
            return null;
        }

        public <T extends BusinessObject> T findBySinglePrimaryKey(Class<T> clazz, Object primaryKey) {
            return null;
        }

        public PersistableBusinessObject manageReadOnly(PersistableBusinessObject bo) {
            return null;
        }  
    } 
}
