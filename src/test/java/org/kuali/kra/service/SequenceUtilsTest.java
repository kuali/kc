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
package org.kuali.kra.service;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.service.impl.SequenceUtils;
import org.kuali.kra.service.impl.versioningartifacts.SimpleSequenceOwner;

/**
 * 
 * Hoping to catch more of the errors that can happen with
 * SequenceUtils looping through members and 
 * specifically dealing with Generics that are encountered
 */
public class SequenceUtilsTest implements Serializable {
    
    MockSequenceOwner owner;
    MockGenericsAssociate<SequenceAssociate> testAssociate1;
    MockGenericsAssociate<NonGenericAssociate> testAssociate2;
    NonGenericAssociate testAssociate3;
    NonGenericAssociate testAssociate4;
    NonGenericAssociate testAssociate5;

    @Before
    public void setUp() throws Exception {
        MockSequenceOwner owner = new MockSequenceOwner();
        MockGenericsAssociate<SequenceAssociate> gen1 = new MockGenericsAssociate<SequenceAssociate>();
        owner.setGenericAssociate(gen1);
        testAssociate1 = new MockGenericsAssociate<SequenceAssociate>();
        gen1.getAssociateCollection().add(testAssociate1);
    }
    
    @Test
    public void testInheritedGenericList() throws VersionException {
        MockSequenceOwner owner = new MockSequenceOwner();
        for (int i = 0; i < 3; i++) {
            owner.getGenericList().add(getNewGenericAssociate());
        }
        SequenceUtils myUtils = new SequenceUtils();
        MockSequenceOwner sequencedOwner = myUtils.sequence(owner);
        for (NonGenericAssociate associate1 : sequencedOwner.getGenericList()) {
            for (NonGenericAssociate associate2 : associate1.getAssociateCollection()) {
                assertTrue(associate2.getStateReset());
            }
            assertTrue(associate1.getStateReset());
        }
    }
    
    @Test
    public void testInheritedGenericField() throws VersionException {
        MockSequenceOwner owner = new MockSequenceOwner();
        for (int i = 0; i < 3; i++) {
            owner.getGenericList().add(getNewGenericAssociate());
            owner.getGenericList().get(i).setGenericAssociate(getNewGenericAssociate());
        }
        SequenceUtils myUtils = new SequenceUtils();
        MockSequenceOwner sequencedOwner = myUtils.sequence(owner);
        for (NonGenericAssociate associate1 : sequencedOwner.getGenericList()) {
            for (NonGenericAssociate associate2 : associate1.getAssociateCollection()) {
                assertTrue(associate2.getStateReset());
            }
            assertTrue(associate1.getStateReset());
            assertTrue(associate1.getGenericAssociate().getStateReset());
            assertFalse(associate1.getChildNonVersionedField().getStateReset());
            assertFalse(associate1.getNonVersionedField().getStateReset());
        }
    }
    
    private NonGenericAssociate getNewGenericAssociate() {
        NonGenericAssociate newAssociate = new NonGenericAssociate();
        //fill associateCollection(generic list with a few nongenericitems)
        for (int i = 0; i < 3; i++) {
            newAssociate.getAssociateCollection().add(new NonGenericAssociate());
        }
        for (int i = 0; i < 3; i++) {
            newAssociate.getNonGenericCollection().add(new NonGenericAssociate());
        }
        newAssociate.setGenericAssociate(new NonGenericAssociate());
        newAssociate.setNonGenericAssociate(new NonGenericAssociate());
        newAssociate.setNonVersionedField(new NonGenericAssociate());
        newAssociate.setChildNonVersionedField(new NonGenericAssociate());
        return newAssociate;
    }
    

    
    public class MockSequenceOwner extends SimpleSequenceOwner implements Serializable {
        private MockGenericsAssociate genericAssociate;
        private NonGenericAssociate nonGenericAssociate;
        private List nonGenericList = new ArrayList();
        private List<NonGenericAssociate> genericList = new ArrayList<NonGenericAssociate>();

        public NonGenericAssociate getNonGenericAssociate() {
            return nonGenericAssociate;
        }
        public void setNonGenericAssociate(NonGenericAssociate nonGenericAssociate) {
            this.nonGenericAssociate = nonGenericAssociate;
        }
        public List getNonGenericList() {
            return nonGenericList;
        }
        public void setNonGenericList(List nonGenericList) {
            this.nonGenericList = nonGenericList;
        }
        public MockGenericsAssociate getGenericAssociate() {
            return genericAssociate;
        }
        public void setGenericAssociate(MockGenericsAssociate genericAssociate) {
            this.genericAssociate = genericAssociate;
        }
        public List<NonGenericAssociate> getGenericList() {
            return genericList;
        }
        public void setGenericList(List<NonGenericAssociate> genericList) {
            this.genericList = genericList;
        }
    }
    
    public class MockGenericsAssociate<T> implements SequenceAssociate<MockSequenceOwner>, Serializable {

        //private field with no getter, should be ignored
        private MockSequenceOwner mockOwner;
        private Boolean stateReset = false;
        
        private List<T> associateCollection = new ArrayList<T>();
        private T genericAssociate;
        
        @SkipVersioning
        private T nonVersionedField;
        
        public MockSequenceOwner getSequenceOwner() {
            return mockOwner;
        }

        public void setSequenceOwner(MockSequenceOwner newlyVersionedOwner) {
            this.mockOwner = newlyVersionedOwner;
        }

        public Integer getSequenceNumber() {
            return mockOwner.getSequenceNumber();
        }

        public void resetPersistenceState() {
            stateReset = true;
        }

        public Boolean getStateReset() {
            return stateReset;
        }

        public void setStateReset(Boolean stateReset) {
            this.stateReset = stateReset;
        }

        public List<T> getAssociateCollection() {
            return associateCollection;
        }

        public void setAssociateCollection(List<T> associateCollection) {
            this.associateCollection = associateCollection;
        }

        public T getGenericAssociate() {
            return genericAssociate;
        }

        public void setGenericAssociate(T genericAssociate) {
            this.genericAssociate = genericAssociate;
        }

        public T getNonVersionedField() {
            return nonVersionedField;
        }

        public void setNonVersionedField(T nonVersionedField) {
            this.nonVersionedField = nonVersionedField;
        }  
    }
    
    public class NonGenericAssociate extends MockGenericsAssociate<NonGenericAssociate> implements Serializable{
        private List nonGenericCollection = new ArrayList();
        private NonGenericAssociate nonGenericAssociate;
        
        @SkipVersioning
        private NonGenericAssociate childNonVersionedField;
        
        public List getNonGenericCollection() {
            return nonGenericCollection;
        }
        public void setNonGenericCollection(List nonGenericCollection) {
            this.nonGenericCollection = nonGenericCollection;
        }
        public NonGenericAssociate getChildNonVersionedField() {
            return childNonVersionedField;
        }
        public void setChildNonVersionedField(NonGenericAssociate childNonVersionedField) {
            this.childNonVersionedField = childNonVersionedField;
        }
        public NonGenericAssociate getNonGenericAssociate() {
            return nonGenericAssociate;
        }
        public void setNonGenericAssociate(NonGenericAssociate nonGenericAssociate) {
            this.nonGenericAssociate = nonGenericAssociate;
        }
    }
}
