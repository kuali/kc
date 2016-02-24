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
package org.kuali.kra.service;


import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.impl.version.SequenceUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.service.impl.versioningartifacts.SimpleSequenceOwner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
