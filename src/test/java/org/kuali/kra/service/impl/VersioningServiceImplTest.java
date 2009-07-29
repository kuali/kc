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
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsEqual.*;
import org.kuali.kra.SeparatelySequenceableAssociate;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.versioningartifacts.OwnerAssociate;
import org.kuali.kra.service.impl.versioningartifacts.SelfReferenceAssociate;
import org.kuali.kra.service.impl.versioningartifacts.SelfReferenceOwner;
import org.kuali.kra.service.impl.versioningartifacts.SepSequenceComplexArtifacts;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateAttachmentBO;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateAttachmentBO2;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateChild;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateChild2;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateGrandChild;
import org.kuali.kra.service.impl.versioningartifacts.SequenceAssociateGrandChild2;
import org.kuali.kra.service.impl.versioningartifacts.SequenceOwnerImpl;
import org.kuali.kra.service.impl.versioningartifacts.SimpleAssociate;
import org.kuali.kra.service.impl.versioningartifacts.SimpleAssociate2;
import org.kuali.kra.service.impl.versioningartifacts.SepSequenceComplexArtifacts.*;


/**
 * This tests the versioning service
 */
public class VersioningServiceImplTest {
    
    private static final int NUMBER_OF_GRANDCHILD_ASSOCIATES = 5;
    private static final int NUMBER_OF_CHILD_ASSOCIATES = 3;
    private static final int NUMBER_OF_CHILD_ATTACHMENT_ASSOCIATES = 4;
    private VersioningService service;
    private SequenceOwnerImpl originalVersion;
    private OwnerAssociate originalOwnerAssociate;

    @Before
    public void setUp() {
        service = new VersioningServiceImpl();
        populateSequenceableArtifacts();
    }
    
    @After
    public void tearDown() {
        service = null;
        originalVersion = null;
    }
    
    @Test
    public void testSkipVersioning_1to1_Association_TopLevelOwner() throws Exception {
        SequenceAssociate<?> originalAssociate = originalVersion.getSkipAssociate();
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion);
        SequenceAssociate<?> newAssociate = newVersion.getSkipAssociate();
        Assert.assertTrue(originalAssociate != newAssociate);
        Assert.assertNotNull(newVersion.getSkipAssociate().getSimpleAssociateId());
        Assert.assertEquals(originalVersion.getSkipAssociate().getSimpleAssociateId(), newVersion.getSkipAssociate().getSimpleAssociateId());
    }
    
    @Test
    public void testVersioning_1to1_Association_OwnerAssociate_CheckDownTheGraph() throws Exception {
        OwnerAssociate originalOwnerAssociate = originalVersion.getOwnerAssociate();
        OwnerAssociate newVersion = service.createNewVersion(originalOwnerAssociate);
        
        Assert.assertEquals(originalOwnerAssociate.getOwnerSequenceNumber(), newVersion.getOwnerSequenceNumber());
        Assert.assertEquals(originalOwnerAssociate.getSequenceNumber().intValue() + 1, newVersion.getSequenceNumber().intValue());
        
        Assert.assertEquals(originalOwnerAssociate.getSequenceOwner(), newVersion.getSequenceOwner());
        Assert.assertEquals(originalVersion.getSequenceNumber(), newVersion.getSequenceOwner().getSequenceNumber());
        Assert.assertEquals(originalVersion.getAssociate().getSequenceNumber(), originalVersion.getSequenceNumber());
        
        SimpleAssociate2 associate = newVersion.getAssociate();
        Assert.assertEquals(newVersion, associate.getSequenceOwner());
        Assert.assertEquals(newVersion.getSequenceNumber(), associate.getSequenceNumber());
        Assert.assertNull(newVersion.getAssociate().getSimpleAssociateId());
    }
    
    @Test
    public void testVersioning_1to1_Association_OwnerAssociate_CheckUpTheGraph() throws Exception {
        OwnerAssociate newVersion = service.createNewVersion(originalVersion.getOwnerAssociate());
        Assert.assertEquals(originalVersion, newVersion.getOwner());
        Assert.assertEquals(originalVersion.getSequenceNumber(), newVersion.getOwnerSequenceNumber());
        
        SimpleAssociate associate = newVersion.getOwner().getAssociate();
        Assert.assertEquals(originalVersion, associate.getSequenceOwner());
        Assert.assertEquals(originalVersion.getSequenceNumber(), associate.getSequenceNumber());
        Assert.assertEquals(originalVersion.getSequenceNumber(), associate.getSequenceNumber());
        Assert.assertNotNull(originalVersion.getAssociate().getSimpleAssociateId());
    }
    
    @Test
    public void testVersioning_1to1_Association_TopLevelOwner() throws Exception {
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion);
        SequenceAssociate<?> associate = newVersion.getAssociate();
        Assert.assertEquals(newVersion, associate.getSequenceOwner());
        Assert.assertEquals(newVersion.getSequenceNumber(), associate.getSequenceNumber());
        Assert.assertNull(newVersion.getAssociate().getSimpleAssociateId());        
    }
    
    @Test
    public void testVersioning_1toM_Association_OwnerAssociate() throws Exception {
        OwnerAssociate newOwnerAssociateVersion = service.createNewVersion(originalVersion.getOwnerAssociate());
        originalVersion.setOwnerAssociate(newOwnerAssociateVersion);
        verifyTopLevelAssociationsNotSequenced(newOwnerAssociateVersion);
        verifyOwnerAssociateSequencing(newOwnerAssociateVersion);
    }
    
    @Test
    public void testVersioning_1toM_Association_TopLevelOwner() throws Exception {
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion);
        
        verifyTopLevelAssociationSequencing(newVersion);
        verifyEffectOnOwnerAssociationAssociationsWhenSequencingTopLevel(newVersion);
    }

    @Test
    public void testVersioning_IndependentlySequencedOwnerAssociate_versioningOfTopSequenceOwner() throws Exception {
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion);
        OwnerAssociate newVersionOwnerAssociate = newVersion.getOwnerAssociate();
        verifyOwnerAssociateWasSequencedWithOwner(newVersion, newVersionOwnerAssociate);        
        verifyNewVersionOwnerAssociateSequenceNumberNotChanged(newVersionOwnerAssociate);        
        verifyNewVersionOwnerAssociateAssociateSeqNoNotChanged(newVersionOwnerAssociate);        
    }
    
    @Test
    public void testVersioning_MtoN_Association_MultipleAssociatesUpdated_TopLevelOwner() throws Exception {
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion); 
        List<SequenceAssociateAttachmentBO> newAssociates = service.versionAssociates(originalVersion.getAttachments());
        for(SequenceAssociateAttachmentBO newAssociate: newAssociates) {
            newVersion.add(newAssociate);
        }
        
        int numberOfAttachgmentsVersioned = originalVersion.getAttachments().size();
        checkAttachmentCollectionSizeAfterVersioning(newVersion, numberOfAttachgmentsVersioned);
        checkNewAttachmentsSequenced(newVersion.getAttachments(), numberOfAttachgmentsVersioned);
        checkOldAttachmentsNotSequenced(originalVersion.getAttachments(), numberOfAttachgmentsVersioned);
        checkIdentifierResetOnNewAttachments(newVersion, numberOfAttachgmentsVersioned);
        checkIdentifierNotResetOnOldAttachmentVersions(newVersion, numberOfAttachgmentsVersioned);
        
        verifyEffectOnOwnerAssociationAssociationsWhenSequencingTopLevel(newVersion);
    } 
    
    @Test
    public void testVersioning_MtoN_Association_MultipleAssociatesUpdated_OwnerAssociate() throws Exception {
        OwnerAssociate newOwnerAssociate = service.createNewVersion(originalOwnerAssociate); 
        List<? extends SeparatelySequenceableAssociate> newAssociates = service.versionAssociates(originalOwnerAssociate.getAttachments());
        for(SeparatelySequenceableAssociate newAssociate: newAssociates) {
            newOwnerAssociate.add((SequenceAssociateAttachmentBO2) newAssociate);
        }
        
        int numberOfAttachgmentsVersioned = originalOwnerAssociate.getAttachments().size();
        checkAttachmentCollectionSizeAfterVersioning(newOwnerAssociate, numberOfAttachgmentsVersioned);
        checkNewAttachmentsSequenced(newOwnerAssociate.getAttachments(), numberOfAttachgmentsVersioned);
        checkOldAttachmentsNotSequenced(originalOwnerAssociate.getAttachments(), numberOfAttachgmentsVersioned);
        checkIdentifierResetOnNewAttachments(newOwnerAssociate, numberOfAttachgmentsVersioned);
        checkIdentifierNotResetOnOldAttachmentVersions(newOwnerAssociate, numberOfAttachgmentsVersioned);
        
        verifyTopLevelAssociationsNotSequenced(newOwnerAssociate);
    }
    
    

    @Test
    public void testVersioning_MtoN_Association_SingleAssociateUpdated() throws Exception {
        SequenceAssociateAttachmentBO attachment = originalVersion.getAttachments().get(0);
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion); 
        SeparatelySequenceableAssociate newAttachmentVersion = service.versionAssociate(attachment);                                            
        newVersion.add((SequenceAssociateAttachmentBO) newAttachmentVersion);
        
        checkAttachmentCollectionSizeAfterVersioning(newVersion, 1);
        checkIdentifierResetOnNewAttachments(newVersion, 1);
        checkIdentifierNotResetOnOldAttachmentVersions(newVersion, 1);
    }
    
    @Test
    public void testVersioning_Owner() throws Exception {
        SequenceOwnerImpl newVersion = service.createNewVersion(originalVersion);
        Assert.assertEquals(originalVersion.getSequenceNumber() + 1, newVersion.getSequenceNumber().intValue());
        Assert.assertNull(newVersion.getSequenceOwnerId());        
    }
    
    /**
     * This test purposefully has no asserts.  It is designed to ensure that the versioning framework
     * does not create an stackoverflow when versioning associates that have collections with a reference
     * to themself - truly an edge case.
     * @throws Exception
     */
    @Test
    public void testVersioning_already_versioned_collection() throws Exception {
        SelfReferenceOwner owner = new SelfReferenceOwner();
        SelfReferenceAssociate associate = new SelfReferenceAssociate();
        associate.selfs.add(associate);
        owner.associates.add(associate);        
    }
    
    /** this test makes sure that versioning happens correctly w/ a specific Object structure.
     * 
     * @see SepSequenceComplexArtifacts for more information on the structure.
     * @throws Exception if bad happens
     */
    @Test
    public void testVersioning_separately_sequenceable_multiple_owners() throws Exception {
        AttachmentOwner owner1 = createAnOwnerWithAttachments();
        AttachmentOwner newOwner1 = service.createNewVersion(owner1);
        
        for (AttachmentMetaData attachment : newOwner1.getAttachments()) {
            attachment.setData(service.versionAssociate(attachment.getData()));
        }
        
        Assert.assertThat(newOwner1.getAttachments().size(), equalTo(owner1.getAttachments().size()));
        for (AttachmentMetaData attachment : newOwner1.getAttachments()) {
            Assert.assertThat(attachment.getData().getId(), nullValue());
            Assert.assertThat(attachment.getData().getSequenceNumber(), is(1));
        }
    }
    
    private AttachmentOwner createAnOwnerWithAttachments() {
        AttachmentOwner owner1 = new AttachmentOwner();
        createAttachmentAndAddToOwner(owner1);
        createAttachmentAndAddToOwner(owner1);
        
        return owner1;
    }
    
    private void createAttachmentAndAddToOwner(AttachmentOwner owner1) {
        AttachmentLargeData data1 = new AttachmentLargeData();
        AttachmentMetaDataAssoc assoc1 = new AttachmentMetaDataAssoc(owner1);
        AttachmentMetaData attachment1 = new AttachmentMetaData(owner1, assoc1, data1);
        owner1.addAttachments(attachment1);
    }

    private void checkNewAttachmentsSequenced(List<? extends SeparatelySequenceableAssociate> attachments, int numberOfAttachmentsVersioned) {
        int startIndex = attachments.size() - numberOfAttachmentsVersioned;
        for(int i = startIndex; startIndex < attachments.size(); startIndex++) {
            Assert.assertThat(attachments.get(i).getSequenceNumber(), is(1));
        }
    }

    private void checkOldAttachmentsNotSequenced(List<? extends SeparatelySequenceableAssociate> attachments, int numberOfAttachmentsVersioned) {
        int lastIndex = numberOfAttachmentsVersioned;
        for(int i = 0; i < lastIndex; i++) {
            Assert.assertThat(attachments.get(i).getSequenceNumber(), is(0));
        }
    }
    
    /**
     * This method...
     * @param ownerAssociate
     */
    private void addAssociate(OwnerAssociate ownerAssociate) {
        SimpleAssociate2 associateToOwnerAssociate = new SimpleAssociate2("OwnerAssociate's Associate");
        ownerAssociate.setAssociate(associateToOwnerAssociate);
        associateToOwnerAssociate.setOwner(ownerAssociate);
    }

    private void addAssociate(SequenceOwnerImpl owner) {
        SimpleAssociate associate = new SimpleAssociate("Associate1");
        owner.setAssociate(associate);
        associate.setOwner(owner);
    }
    
    private void addSkipAssociate(SequenceOwnerImpl owner) {
        SimpleAssociate associate = new SimpleAssociate("SkipAssociate");
        owner.setSkipAssociate(associate);
        associate.setOwner(owner);
    }
    
    private void addCollectionOfManyToManyAssociates(OwnerAssociate owner) {
        for(int i = 1; i <= NUMBER_OF_CHILD_ATTACHMENT_ASSOCIATES; i++) {
            SequenceAssociateAttachmentBO2 attch = new SequenceAssociateAttachmentBO2(String.format("Attachment2 %d", i));
            owner.add(attch);
        }
    }

    private void addCollectionOfManyToManyAssociates(SequenceOwnerImpl owner) {
        for(int i = 1; i <= NUMBER_OF_CHILD_ATTACHMENT_ASSOCIATES; i++) {
            SequenceAssociateAttachmentBO attch = new SequenceAssociateAttachmentBO(String.format("Attachment %d", i));
            owner.add(attch);
        }
    }

    private void addCollectionOfOneToManyAssociates(OwnerAssociate owner) {
        for(int i = 1; i <= NUMBER_OF_CHILD_ASSOCIATES; i++) {
            SequenceAssociateChild2 child = new SequenceAssociateChild2(String.format("Child2 %d", i));
            owner.add(child);
            for(int j = 1; j <= NUMBER_OF_GRANDCHILD_ASSOCIATES; j++) {
                String name = String.format("GrandChild2 %d.%d", i, j);
                SequenceAssociateGrandChild2 grandChild = new SequenceAssociateGrandChild2(name);
                grandChild.setOwner(owner);
                child.add(grandChild);
            }
        }
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
    
    private void addCollectionOfOneToManySkipAssociates(SequenceOwnerImpl owner) {
        for(int i = 1; i <= NUMBER_OF_CHILD_ASSOCIATES; i++) {
            SequenceAssociateChild child = new SequenceAssociateChild(String.format("Child %d", i));
            owner.addSkipChild(child);            
        }
    }
    
    private void checkAttachmentCollectionSizeAfterVersioning(SequenceOwnerImpl newVersion, int numberOfAttachmentsVersioned) {
        int actualSize = newVersion.getAttachments().size();
        int expectedNewSize = originalVersion.getAttachments().size() + numberOfAttachmentsVersioned;
        Assert.assertEquals(expectedNewSize, actualSize);
    }
    
    private void checkAttachmentCollectionSizeAfterVersioning(OwnerAssociate newVersion, int numberOfAttachmentsVersioned) {
        int actualSize = newVersion.getAttachments().size();
        int expectedNewSize = originalOwnerAssociate.getAttachments().size() + numberOfAttachmentsVersioned;
        Assert.assertEquals(expectedNewSize, actualSize);
    }
    
    private void checkIdentifierNotResetOnOldAttachmentVersions(SequenceOwnerImpl newVersion, int numberOfAttachmentsVersioned) {
        List<SequenceAssociateAttachmentBO> attachments = newVersion.getAttachments();
        int lastIndex = attachments.size() - numberOfAttachmentsVersioned;
        for(int i = 0; i < lastIndex; i++) {
            Assert.assertNotNull(attachments.get(i).getAttachmentId());
        }
    }
    
    private void checkIdentifierNotResetOnOldAttachmentVersions(OwnerAssociate newVersion, int numberOfAttachmentsVersioned) {
        List<SequenceAssociateAttachmentBO2> attachments = newVersion.getAttachments();
        int lastIndex = attachments.size() - numberOfAttachmentsVersioned;
        for(int i = 0; i < lastIndex; i++) {
            Assert.assertNotNull(attachments.get(i).getAttachmentId());
        }
    }
    
    private void checkIdentifierResetOnNewAttachments(SequenceOwnerImpl newVersion, int numberOfAttachmentsVersioned) {
        int lastIndex = newVersion.getAttachments().size() - numberOfAttachmentsVersioned;
        for(int i = 0; i < numberOfAttachmentsVersioned; i++) {
            Assert.assertNull(newVersion.getAttachments().get(lastIndex + i).getAttachmentId());
        }
    }
    
    private void checkIdentifierResetOnNewAttachments(OwnerAssociate newVersion, int numberOfAttachmentsVersioned) {
        int lastIndex = newVersion.getAttachments().size() - numberOfAttachmentsVersioned;
        for(int i = 0; i < numberOfAttachmentsVersioned; i++) {
            Assert.assertNull(newVersion.getAttachments().get(lastIndex + i).getAttachmentId());
        }
    }
    
    /*
     * This method populates the OwnerAssociate
     */
    private OwnerAssociate populateOwnerAssociate() {
        OwnerAssociate ownerAssociate = new OwnerAssociate("OwnerAssociate");
        originalVersion.setOwnerAssociate(ownerAssociate);
        ownerAssociate.setOwner(originalVersion);
        addAssociate(ownerAssociate);
        addCollectionOfOneToManyAssociates(ownerAssociate);
        addCollectionOfManyToManyAssociates(ownerAssociate);
        return ownerAssociate;
    }
    
    private void populateSequenceableArtifacts() {
        originalVersion = populateTopLevelOwner();
        originalOwnerAssociate = populateOwnerAssociate();
    }

    /**
     * @return
     */
    private SequenceOwnerImpl populateTopLevelOwner() {
        SequenceOwnerImpl owner = new SequenceOwnerImpl();
        addAssociate(owner);
        addSkipAssociate(owner);
        addCollectionOfOneToManyAssociates(owner);
        addCollectionOfOneToManySkipAssociates(owner);
        addCollectionOfManyToManyAssociates(owner);
        return owner;
    }
    
    /**
     * @param topOwner
     */
    private void verifyEffectOnOwnerAssociationAssociationsWhenSequencingTopLevel(SequenceOwnerImpl topOwner) {
        OwnerAssociate originalOwnerAssociate = originalVersion.getOwnerAssociate();
        OwnerAssociate newOwnerAssociate = topOwner.getOwnerAssociate();
        Assert.assertEquals(topOwner.getSequenceNumber(), newOwnerAssociate.getOwnerSequenceNumber()); 
        Assert.assertEquals(originalOwnerAssociate.getSequenceNumber(), newOwnerAssociate.getSequenceNumber());
        
        for(SequenceAssociateChild2 child: newOwnerAssociate.getChildren()) {
            Assert.assertEquals(newOwnerAssociate, child.getOwner());
            Assert.assertEquals(newOwnerAssociate.getSequenceNumber(), child.getSequenceNumber());
            Assert.assertNotNull(child.getChildId());
            for(SequenceAssociateGrandChild2 grandChild: child.getChildren()) {
                Assert.assertEquals(newOwnerAssociate, grandChild.getOwner());
                Assert.assertEquals(newOwnerAssociate.getSequenceNumber(), grandChild.getSequenceNumber());
                Assert.assertNotNull(grandChild.getGrandChildId());
            }
        }
    }

    /**
     * 
     * @param newVersionOwnerAssociate
     * @param oldVersionOwnerAssociate
     */
    private void verifyNewVersionOwnerAssociateAssociateSeqNoNotChanged(OwnerAssociate newVersionOwnerAssociate) {
        OwnerAssociate oldVersionOwnerAssociate = originalVersion.getOwnerAssociate();
        SimpleAssociate2 newVersionOwnerAssociateAssociate = newVersionOwnerAssociate.getAssociate();
        SimpleAssociate2 oldVersionOwnerAssociateAssociate = oldVersionOwnerAssociate.getAssociate();
        Assert.assertEquals(oldVersionOwnerAssociateAssociate.getSequenceNumber(), newVersionOwnerAssociateAssociate.getSequenceNumber());
    }
    
    /**
     * 
     * @param newVersionOwnerAssociate
     * @return
     */
    private OwnerAssociate verifyNewVersionOwnerAssociateSequenceNumberNotChanged(OwnerAssociate newVersionOwnerAssociate) {
        OwnerAssociate oldVersionOwnerAssociate = originalVersion.getOwnerAssociate();
        Assert.assertEquals(oldVersionOwnerAssociate, oldVersionOwnerAssociate.getAssociate().getOwner());
        Assert.assertEquals(oldVersionOwnerAssociate.getSequenceNumber(), newVersionOwnerAssociate.getSequenceNumber());
        return oldVersionOwnerAssociate;
    }
    
    private void verifyOwnerAssociateSequencing(OwnerAssociate ownerAssociate) {
        Assert.assertEquals(originalVersion.getSequenceNumber(), ownerAssociate.getOwnerSequenceNumber());
        
        for(SequenceAssociateChild2 child: ownerAssociate.getChildren()) {
            Assert.assertEquals(ownerAssociate, child.getOwner());
            Assert.assertEquals(ownerAssociate.getSequenceNumber(), child.getSequenceNumber());
            Assert.assertNull(child.getChildId());
            for(SequenceAssociateGrandChild2 grandChild: child.getChildren()) {
                Assert.assertEquals(ownerAssociate, grandChild.getOwner());
                Assert.assertEquals(ownerAssociate.getSequenceNumber(), grandChild.getSequenceNumber());
                Assert.assertNull(grandChild.getGrandChildId());
            }
        }
    }

    /**
     * 
     * @param newVersion
     * @param newVersionOwnerAssociate
     */
    private void verifyOwnerAssociateWasSequencedWithOwner(SequenceOwnerImpl newVersion, OwnerAssociate newVersionOwnerAssociate) {
        Assert.assertEquals(newVersion, newVersionOwnerAssociate.getOwner());
        Assert.assertEquals(newVersion.getSequenceNumber(), newVersionOwnerAssociate.getOwnerSequenceNumber());
    }

    /**
     * @param owner
     */
    private void verifyTopLevelAssociationSequencing(SequenceOwnerImpl owner) {
        checkAssociatesThatShouldBeVersioned(owner);
        checkAssociatesThatShouldNotBeVersioned(owner);
    }

    /**
     * @param owner
     */
    private void checkAssociatesThatShouldNotBeVersioned(SequenceOwnerImpl owner) {
        for(SequenceAssociateChild child: owner.getSkipChildren()) {
            Assert.assertNotNull(child.getChildId());            
        }
    }

    /**
     * This method...
     * @param owner
     */
    private void checkAssociatesThatShouldBeVersioned(SequenceOwnerImpl owner) {
        for(SequenceAssociateChild child: owner.getChildren()) {
            Assert.assertEquals(owner, child.getOwner());
            Assert.assertEquals(owner.getSequenceNumber(), child.getSequenceNumber());
            Assert.assertNull(child.getChildId());
            for(SequenceAssociateGrandChild grandChild: child.getChildren()) {
                Assert.assertEquals(owner, grandChild.getOwner());
                Assert.assertEquals(owner.getSequenceNumber(), grandChild.getSequenceNumber());
                Assert.assertNull(grandChild.getGrandChildId());
            }
        }
    }
    
    /**
     * This method...
     * @param owner
     */
    private void verifyTopLevelAssociationsNotSequenced(OwnerAssociate ownerAssociate) {
        SequenceOwnerImpl owner = ownerAssociate.getSequenceOwner();
        for(SequenceAssociateChild child: owner.getChildren()) {
            Assert.assertEquals(owner, child.getOwner());
            Assert.assertEquals(owner.getSequenceNumber(), child.getSequenceNumber());
            Assert.assertNotNull(child.getChildId());
            for(SequenceAssociateGrandChild grandChild: child.getChildren()) {
                Assert.assertEquals(owner, grandChild.getOwner());
                Assert.assertEquals(owner.getSequenceNumber(), grandChild.getSequenceNumber());
                Assert.assertNotNull(grandChild.getGrandChildId());
            }
        }
    }
}