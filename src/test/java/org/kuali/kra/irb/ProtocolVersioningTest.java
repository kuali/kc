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
package org.kuali.kra.irb;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentFile;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Integration test for protocol versioning.  Does not test attachment versioning.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE.sql", delimiter = ";")
}))
public class ProtocolVersioningTest extends KraTestBase {
    
    private BusinessObjectService bos;
    private DocumentService documentService;
    private VersioningService versioningService;
    private ProtocolDocument ver1;
    
    /**
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Override
    public void setUp() throws Exception {
       super.setUp();
       GlobalVariables.setUserSession(new UserSession("quickstart"));
       GlobalVariables.setErrorMap(new ErrorMap());
       GlobalVariables.setAuditErrorMap(new HashMap());
       locateServices();
       ver1 = ProtocolFactory.createProtocolDocument();
    }
    
    /**
     * Creates a new version of a protocol.
     * Confirms that sequence number is incremented the protocol number is the same, the protocol id is different.
     * 
     * @throws Exception if bad happens
     */
    @Test
    public void test_basic_versioning() throws Exception {
        assertIsVersioned(ver1, createAndSaveVersion(ver1));
    }
    
    /**
     * Creates a new version of a protocol that has one associate.  Makes sure that associate is versioned.
     * 
     * @throws Exception if bad happens
     */
    @Test @Ignore("Protocol doesn't have any BO strutures to test this yet")
    public void test_one_associate_versioning() throws Exception {
        //there is currently no ProtocolAssociates that are 1:1 w/ Protocol
        //when there are please fill this in
        
        
        //assertIsVersioned(ver1, createAndSaveVersion(ver1));
    }
    
    /**
     * Creates a new version of a protocol that has many associates in a Collection.  Makes sure that associates are versioned.
     * 
     * @throws Exception if bad happens
     */
    @Test
    public void test_many_associates_versioning() throws Exception {
        List<ProtocolLocation> locations = new ArrayList<ProtocolLocation>();
        
        ProtocolLocation location = new ProtocolLocation();
        location.setProtocol(ver1.getProtocol());
        location.setOrganizationId("000001");
        location.setProtocolOrganizationTypeCode("1");
        locations.add(location);
        
        ProtocolLocation location2 = new ProtocolLocation();
        location2.setProtocol(ver1.getProtocol());
        location2.setOrganizationId("000002");
        location2.setProtocolOrganizationTypeCode("2");
        locations.add(location2);
        
        ver1.getProtocol().setProtocolLocations(locations);
        documentService.saveDocument(ver1);
        
        ProtocolDocument ver2 = createAndSaveVersion(ver1);
        
        assertIsVersioned(ver1, ver2);
        assertIsVersioned(ver1.getProtocol().getProtocolLocations(), ver2.getProtocol().getProtocolLocations());
    }
    
    /**
     * Creates a new version of a protocol that has many separately sequenced associates in a Collection.
     * Makes sure that associates are NOT versioned.
     * 
     * @throws Exception if bad happens
     */
    @Test
    public void test_many_separate_sequenced_associates_version_protocol_only() throws Exception {
        ver1.getProtocol().addAttachmentsByType(createAttachment1());
        ver1.getProtocol().addAttachmentsByType(createAttachment2());
        documentService.saveDocument(ver1);
        
        ProtocolDocument ver2 = createAndSaveVersion(ver1);
        assertIsVersioned(ver1, ver2);
        
        for (int i = 0; i < ver1.getProtocol().getAttachmentProtocols().size(); i++) {
            assertNotVersioned(ver1.getProtocol().getAttachmentProtocols().get(i).getFile(), ver2.getProtocol().getAttachmentProtocols().get(i).getFile());
        }
    }
    
    /**
     * This test will version a separately sequenced BO.  Since the versioning framework does not
     * automatically trigger a new version of the Owner(s) of the associate but rather expects a new
     * version to be created this is not tested.  This test only makes sure that a separately sequenced
     * BO is properly sequenced.
     * 
     * @throws Exception if bad happens
     */
    @Test
    public void test_separate_sequenced_associate_version() throws Exception {
        ProtocolAttachmentProtocol attachment1 = createAttachment1();
        ver1.getProtocol().addAttachmentsByType(attachment1);
        documentService.saveDocument(ver1);
        
        ProtocolDocument ver2 = createAndSaveVersion(ver1);
        assertIsVersioned(ver1, ver2);
        ProtocolAttachmentFile oldVersion = attachment1.getFile();
        ProtocolAttachmentFile newVersion = versioningService.versionAssociate(oldVersion);
        attachment1.setFile(newVersion);
        
        Assert.assertThat(ver2.getProtocol().getAttachmentProtocols().size(), is(1));
        assertIsVersioned(oldVersion, newVersion);
    }
    
    private ProtocolAttachmentProtocol createAttachment1() {
        ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol(ver1.getProtocol());
        attachment.setTypeCode("1");
        attachment.setDocumentId(1);
        attachment.setFile(new ProtocolAttachmentFile("junk.txt", "txt", new byte[]{0, 1, 2, 3, 4, 5}));
        return attachment;
    }
    
    private ProtocolAttachmentProtocol createAttachment2() {
        ProtocolAttachmentProtocol attachment2 = new ProtocolAttachmentProtocol(ver1.getProtocol());
        attachment2.setTypeCode("2");
        attachment2.setDocumentId(1);      
        attachment2.setFile(new ProtocolAttachmentFile("more_junk.java", "java", new byte[]{0}));
        return attachment2;
    }
    
    /**
     * create and save a new version of a protocol doc.
     * 
     * @param oldDoc the old doc
     * @return the new version
     */
    private ProtocolDocument createAndSaveVersion(ProtocolDocument oldDoc) throws Exception {
        Protocol newVersion = versioningService.createNewVersion(oldDoc.getProtocol());
        
        ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
        protocolDocument.getDocumentHeader().setDocumentDescription("A new version");
        protocolDocument.setDocumentNextvalues(new ArrayList<DocumentNextvalue>());
        protocolDocument.setProtocol(newVersion);
            
        documentService.saveDocument(protocolDocument);
        
        
        return protocolDocument;
    }
    
    private void locateServices() {
        documentService = KraServiceLocator.getService(DocumentService.class);
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        versioningService = KraServiceLocator.getService(VersioningService.class);
    }
    
    /**
     * Checks that the ver2 doc is versioned from ver1.
     * @param ver1 the first version
     * @param ver2 the second version
     */
    private void assertIsVersioned(ProtocolDocument ver1, ProtocolDocument ver2) {
        Assert.assertThat(ver2.getProtocol().getSequenceNumber(), is(ver1.getProtocol().getSequenceNumber() + 1));
        Assert.assertThat(ver2.getProtocol().getProtocolNumber(), equalTo(ver1.getProtocol().getProtocolNumber()));
        Assert.assertThat(ver2.getProtocol().getProtocolId(), not(equalTo(ver1.getProtocol().getProtocolId())));
    }
    
    /**
     * Checks that the ver2 associate is versioned from ver1.
     * @param ver1 the first version
     * @param ver2 the second version
     */
    private void assertIsVersioned(ProtocolAssociate ver1, ProtocolAssociate ver2) {
        Assert.assertThat(ver2.getSequenceNumber(), is(ver1.getSequenceNumber() + 1));
        Assert.assertThat(ver2.getProtocolNumber(), equalTo(ver1.getProtocol().getProtocolNumber()));
        Assert.assertThat(ver2.getProtocolId(), not(equalTo(ver1.getProtocolId())));
        
        Assert.assertThat(ver2.getSequenceNumber(), equalTo(ver2.getProtocol().getSequenceNumber()));
        Assert.assertThat(ver2.getProtocolNumber(), equalTo(ver2.getProtocol().getProtocolNumber()));
        Assert.assertThat(ver2.getProtocolId(), equalTo(ver2.getProtocol().getProtocolId()));
    }
    
    /**
     * Checks that the ver2 associate is versioned from ver1.
     * @param ver1 the first version
     * @param ver2 the second version
     */
    private <T extends ProtocolSeparateAssociate> void assertIsVersioned(T ver1, T ver2) {
        Assert.assertThat(ver2.getSequenceNumber(), is(ver1.getSequenceNumber() + 1));
        Assert.assertThat(ver2.getId(), not(equalTo(ver1.getId())));
    }
    
    /**
     * Checks that the ver2 associate is versioned from ver1.
     * @param ver1 the first version
     * @param ver2 the second version
     */
    private <T extends ProtocolSeparateAssociate> void assertNotVersioned(T ver1, T ver2) {
        Assert.assertThat(ver2.getSequenceNumber(), is(ver1.getSequenceNumber()));
        Assert.assertThat(ver2.getId(), equalTo(ver1.getId()));
    }
    
    /**
     * Checks that the a collections ver2 associates is versioned from a collection of ver1s.
     * @param ver1 the first version
     * @param ver2 the second version
     */
    private <T extends ProtocolAssociate> void assertIsVersioned(List<T> ver1, List<T> ver2) {
        Assert.assertThat(ver2.size(), equalTo(ver1.size()));
        
        for (int i = 0; i < ver1.size(); i++) {
            assertIsVersioned(ver1.get(i), ver2.get(i));
        }
    }
}
