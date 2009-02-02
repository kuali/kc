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
package org.kuali.kra.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.Sequenceable;

/**
 * This lass tests the versioning service
 */
public class VersioningServiceImplTest {
    
    public class Foo implements Serializable {
        private static final long serialVersionUID = -593740365386885990L;
        private String name;
        private Bar bar;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Bar getBar() {
            return bar;
        }
        public void setBar(Bar bar) {
            this.bar = bar;
        }
        
    }
    
    public class Bar implements Serializable {
        private static final long serialVersionUID = 6283947599624347760L;
        private String name;
        private Foo foo;
        
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Foo getFoo() {
            return foo;
        }
        public void setFoo(Foo foo) {
            this.foo = foo;
        }
        
    }
    
    @Test
    public void deepCopyTest() throws Exception {
        Foo foo = new Foo();
        foo.setName("Foo1");
        Bar bar = new Bar();
        bar.setName("Bar1");
        foo.setBar(bar);
        bar.setFoo(foo);
        //Foo newFoo = (Foo) ObjectUtils.deepCopy(foo);
    }
    
//    @Test
//    public void testVersioning() throws Exception {
//        VersioningService service = new VersioningServiceImpl();
//        Owner original = new Owner();
//        original.add(new Child("Child1"));
//        original.add(new Child("Child2"));
//        original.add(new Child("Child3"));
//        
//        Owner newVersion = (Owner) service.createNewVersion(original);
//        Assert.assertEquals(original.getSequenceNumber() + 1, newVersion.getSequenceNumber().intValue());
//        
//    }
    
    /**
     * This test artifact represents a top level owner; i.e. Award
     */
    public class Owner implements Sequenceable {
        private static final long serialVersionUID = 3354366183120742932L;
        
        private String name;
        private Integer sequenceNumber;
        private List<Child> children;
        
        public Owner() {
            this.name = "Owner";
            sequenceNumber = 1;
            children = new ArrayList<Child>();
        }
        
        public void add(Child child) {
            children.add(child);
            child.setOwner(this);
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSequenceNumber() {
            return sequenceNumber;
        }

        public void setSequenceOwner(Sequenceable owner) {
            sequenceNumber++;
        }
    }
    
    /**
     * This test artifact represents a top level owner; i.e. Award
     */
    public class Child implements Sequenceable {
        private static final long serialVersionUID = 3354366183120742932L;
        
        private String name;
        private Owner owner;
        private Integer sequenceNumber;
        
        public Child(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
            this.sequenceNumber = owner.getSequenceNumber();
        }

        public Integer getSequenceNumber() {
            return sequenceNumber;
        }

        public void setSequenceNumber(Integer sequenceNumber) {
            // do nothing here
        }

        public void setSequenceOwner(Sequenceable owner) {
            setOwner((Owner)owner);
        }
    }
}
