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
package org.kuali.kra.irb.protocol;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static org.junit.Assert.*;
public class ProtocolVersionServiceTest extends KcIntegrationTestBase {
    
    private static final String DOCUMENT_STATUS_CODE_COMPLETE = "2";

	private static final String PROTOCOL_NUMBER = "1021000009";
    
    private ProtocolVersionService protocolVersionService;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolVersionService = KcServiceLocator.getService(ProtocolVersionService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }
    
    @Test
    public void testVersioning() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolDocument newProtocolDocument = (ProtocolDocument) protocolVersionService.versionProtocolDocument(protocolDocument);
        assertNotNull(newProtocolDocument);
        assertTrue(!StringUtils.equals(protocolDocument.getDocumentNumber(), newProtocolDocument.getDocumentNumber()));
        assertTrue((protocolDocument.getProtocol().getSequenceNumber() + 1 == newProtocolDocument.getProtocol().getSequenceNumber()));
        assertFalse(protocolDocument.getProtocol().isActive());
        assertTrue(newProtocolDocument.getProtocol().isActive());
        
        List<DocumentNextvalue> nextValues = newProtocolDocument.getDocumentNextvalues();
        for (DocumentNextvalue nextValue : nextValues) {
            assertEquals(nextValue.getDocumentKey(), newProtocolDocument.getDocumentNumber());
        }
    }
    
    @Test 
    public void testGetProtocolVersion() throws Exception {
        Protocol protocol = (Protocol) protocolVersionService.getProtocolVersion(PROTOCOL_NUMBER, 1);
        assertNull(protocol);
        
        ProtocolDocument protocolDocument1 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER);
        ProtocolDocument protocolDocument2 = (ProtocolDocument) protocolVersionService.versionProtocolDocument(protocolDocument1);
        ProtocolDocument protocolDocument3 = (ProtocolDocument) protocolVersionService.versionProtocolDocument(protocolDocument2);
        
        protocol = (Protocol) protocolVersionService.getProtocolVersion(PROTOCOL_NUMBER, 2);
        assertNotNull(protocol);
        assertEquals(new Integer(2), protocol.getSequenceNumber());
    }
    
    @Test
    public void test_versioningWithAttachments() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        protocolDocument.getProtocol().addAttachmentsByType(createAttachment(1, "1", DOCUMENT_STATUS_CODE_COMPLETE, "1", 123L));
        protocolDocument.getProtocol().addAttachmentsByType(createAttachment(1, DOCUMENT_STATUS_CODE_COMPLETE, DOCUMENT_STATUS_CODE_COMPLETE, DOCUMENT_STATUS_CODE_COMPLETE, 456L));
        ProtocolDocument newProtocolDocument = (ProtocolDocument) protocolVersionService.versionProtocolDocument(protocolDocument);
        assertEquals(DOCUMENT_STATUS_CODE_COMPLETE, protocolDocument.getProtocol().getAttachmentProtocols().get(0).getDocumentStatusCode());
        assertEquals(DOCUMENT_STATUS_CODE_COMPLETE, protocolDocument.getProtocol().getAttachmentProtocols().get(1).getDocumentStatusCode());
        assertEquals(DOCUMENT_STATUS_CODE_COMPLETE, newProtocolDocument.getProtocol().getAttachmentProtocols().get(0).getDocumentStatusCode());
        assertEquals(DOCUMENT_STATUS_CODE_COMPLETE, newProtocolDocument.getProtocol().getAttachmentProtocols().get(1).getDocumentStatusCode());
    }
    
    private ProtocolAttachmentProtocol createAttachment(Integer documentId, String typeCode, String statusCode, String documentStatusCode, Long fileId) {
    	ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
    	attachment.setTypeCode(typeCode);
    	attachment.setDocumentId(documentId);
    	attachment.setStatusCode(statusCode);
    	attachment.setDocumentStatusCode(documentStatusCode);
    	attachment.setAttachmentVersion(1);
    	attachment.setDescription("Testing");
    	AttachmentFile file = new AttachmentFile();
    	file.setId(fileId);
    	file.setData(new byte[]{ 1, 2 });
    	file.setName("testfile.txt");
    	file.setType("text");
    	attachment.setFile(file);
    	attachment.setFileId(fileId);
    	return attachment;
    	
    }
}
