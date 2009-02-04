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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateChild;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateGrandChild;
import org.kuali.kra.service.impl.versioningartifacts.SequenceOwnerImpl;
import org.kuali.kra.service.impl.versioningartifacts.SimpleAssociate;

/**
 * This tests the versioning service
 */
public class VersioningServiceImplTest {
    
    private static final int NUMBER_OF_GRANDCHILD_ASSOCIATES = 5;
    private static final int NUMBER_OF_CHILD_ASSOCIATES = 3;
    private VersioningService service;
    private SequenceOwnerImpl originalVersion;

    @Before
    public void setUp() {
        service = new VersioningServiceImpl();
        originalVersion = populateSequenceableArtifacts();
    }
    
    @After
    public void tearDown() {
        service = null;
        originalVersion = null;
    }
    
    @Test
    public void testVersioning_Owner() throws Exception {
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion);
        Assert.assertEquals(originalVersion.getSequenceNumber() + 1, newVersion.getSequenceNumber().intValue());
    }
    
    @Test
    public void testVersioning_1toM_Association() throws Exception {
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion);
        
        for(SequenceAssociateChild child: newVersion.getChildren()) {
            Assert.assertEquals(newVersion, child.getOwner());
            Assert.assertEquals(newVersion.getSequenceNumber(), child.getSequenceNumber());
            for(SequenceAssociateGrandChild grandChild: child.getChildren()) {
                Assert.assertEquals(newVersion, grandChild.getOwner());
                Assert.assertEquals(newVersion.getSequenceNumber(), grandChild.getSequenceNumber());
            }
        }
    }

    @Test
    public void testVersioning_1to1_Association() throws Exception {
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion);
        SequenceAssociate associate = newVersion.getAssociate();
        Assert.assertEquals(newVersion, associate.getSequenceOwner());
        Assert.assertEquals(newVersion.getSequenceNumber(), associate.getSequenceNumber());
        
    }
    
    private SequenceOwnerImpl populateSequenceableArtifacts() {
        SequenceOwnerImpl owner = new SequenceOwnerImpl();
        addAssociate(owner);
        addCollectionOfAssociates(owner);
        return owner;
    }

    private void addCollectionOfAssociates(SequenceOwnerImpl owner) {
        for(int i = 1; i <= NUMBER_OF_CHILD_ASSOCIATES; i++) {
            SequenceAssociateChild child = new SequenceAssociateChild(String.format("Child %d", i));
            owner.add(child);
            for(int j = 1; j <= NUMBER_OF_GRANDCHILD_ASSOCIATES; j++) {
                String name = String.format("GrandChild %d.%d", i, j);
                SequenceAssociateGrandChild grandChild = new SequenceAssociateGrandChild(name);
                grandChild.setOwner(owner);
                child.add(grandChild);
            }
        }
    }

    private void addAssociate(SequenceOwnerImpl owner) {
        SimpleAssociate associate = new SimpleAssociate("Associate1");
        owner.setAssociate(associate);
        associate.setOwner(owner);
    }
}