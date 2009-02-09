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

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.SeparatelySequenceableAssociate;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateAttachmentBO;
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
    private static final int NUMBER_OF_CHILD_ATTACHMENT_ASSOCIATES = 4;
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
        Assert.assertNull(newVersion.getSequenceOwnerId());
    }
    
    @Test
    public void testVersioning_1to1_Association() throws Exception {
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion);
        SequenceAssociate associate = newVersion.getAssociate();
        Assert.assertEquals(newVersion, associate.getSequenceOwner());
        Assert.assertEquals(newVersion.getSequenceNumber(), associate.getSequenceNumber());
        Assert.assertNull(newVersion.getAssociate().getSimpleAssociateId());
    }
    
    @Test
    public void testVersioning_1toM_Association() throws Exception {
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion);
        
        for(SequenceAssociateChild child: newVersion.getChildren()) {
            Assert.assertEquals(newVersion, child.getOwner());
            Assert.assertEquals(newVersion.getSequenceNumber(), child.getSequenceNumber());
            Assert.assertNull(child.getChildId());
            for(SequenceAssociateGrandChild grandChild: child.getChildren()) {
                Assert.assertEquals(newVersion, grandChild.getOwner());
                Assert.assertEquals(newVersion.getSequenceNumber(), grandChild.getSequenceNumber());
                Assert.assertNull(grandChild.getGrandChildId());
            }
        }
    }
    
    @Test
    public void testVersioning_MtoN_Association_SingleAssociateUpdated() throws Exception {
        SequenceAssociateAttachmentBO attachment = originalVersion.getAttachments().get(0);
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion); 
        SequenceAssociateAttachmentBO newAttachmentVersion = (SequenceAssociateAttachmentBO) service.versionAssociate(newVersion, attachment);                                            
        newVersion.add(newAttachmentVersion);
        
        checkAttachmentCollectionSizeAfterVersioning(newVersion, 1);
        checkIdentifierResetOnNewAttachments(newVersion, 1);
        checkIdentifierNotResetOnOldAttachmentVersions(newVersion, 1);
    }
    
    @Test
    public void testVersioning_MtoN_Association_MultipleAssociatesUpdated() throws Exception {
        SequenceOwnerImpl newVersion = (SequenceOwnerImpl) service.createNewVersion(originalVersion); 
        List<? extends SeparatelySequenceableAssociate> newAssociates = service.versionAssociates(newVersion, originalVersion.getAttachments());
        for(SeparatelySequenceableAssociate newAssociate: newAssociates) {
            newVersion.add((SequenceAssociateAttachmentBO) newAssociate);
        }
        
        int numberOfAttachgmentsVersioned = originalVersion.getAttachments().size();
        checkAttachmentCollectionSizeAfterVersioning(newVersion, numberOfAttachgmentsVersioned);
        checkIdentifierResetOnNewAttachments(newVersion, numberOfAttachgmentsVersioned);
        checkIdentifierNotResetOnOldAttachmentVersions(newVersion, numberOfAttachgmentsVersioned);
    }

    private void checkIdentifierResetOnNewAttachments(SequenceOwnerImpl newVersion, int numberOfAttachmentsVersioned) {
        int lastIndex = newVersion.getAttachments().size() - numberOfAttachmentsVersioned;
        for(int i = 0; i < numberOfAttachmentsVersioned; i++) {
            Assert.assertNull(newVersion.getAttachments().get(lastIndex + i).getAttachmentId());
        }
    }
    
    private void checkIdentifierNotResetOnOldAttachmentVersions(SequenceOwnerImpl newVersion, int numberOfAttachmentsVersioned) {
        List<SequenceAssociateAttachmentBO> attachments = newVersion.getAttachments();
        int lastIndex = attachments.size() - numberOfAttachmentsVersioned;
        for(int i = 0; i < lastIndex; i++) {
            Assert.assertNotNull(attachments.get(i).getAttachmentId());
        }
    }
    
    private void checkAttachmentCollectionSizeAfterVersioning(SequenceOwnerImpl newVersion, int numberOfAttachmentsVersioned) {
        int actualSize = newVersion.getAttachments().size();
        int expectedNewSize = originalVersion.getAttachments().size() + numberOfAttachmentsVersioned;
        Assert.assertEquals(expectedNewSize, actualSize);
    }
    
    private SequenceOwnerImpl populateSequenceableArtifacts() {
        SequenceOwnerImpl owner = new SequenceOwnerImpl();
        addAssociate(owner);
        addCollectionOfOneToManyAssociates(owner);
        addCollectionOfManyToManyAssociates(owner);
        return owner;
    }

    private void addCollectionOfOneToManyAssociates(SequenceOwnerImpl owner) {
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
    
    private void addCollectionOfManyToManyAssociates(SequenceOwnerImpl owner) {
        for(int i = 1; i <= NUMBER_OF_CHILD_ATTACHMENT_ASSOCIATES; i++) {
            SequenceAssociateAttachmentBO attch = new SequenceAssociateAttachmentBO(String.format("Attachment %d", i));
            owner.add(attch);
        }
    }

    private void addAssociate(SequenceOwnerImpl owner) {
        SimpleAssociate associate = new SimpleAssociate("Associate1");
        owner.setAssociate(associate);
        associate.setOwner(owner);
    }
}