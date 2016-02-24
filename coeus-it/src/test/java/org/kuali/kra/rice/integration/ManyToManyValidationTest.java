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
package org.kuali.kra.rice.integration;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.hamcrest.core.Is.is;

/**
 * This is an integration test that makes sure the {@link DictionaryValidationService DictionaryValidationService} does not
 * fail validating BO relationships that are many-to-many.
 */
public class ManyToManyValidationTest extends KcIntegrationTestBase { //KraWebTestBase {

    private DictionaryValidationService dictionaryValidationService;
    private ProtocolDocument doc;
    private Mockery mockery = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    /**
     * Create a ProtocolDocument used for testing.
     * @throws Exception if bad happens
     */
    @Before
    public void setupDocument() throws Exception { 
        this.doc = (ProtocolDocument) KcServiceLocator.getService(DocumentService.class).getNewDocument(ProtocolDocument.class);
        
        final Protocol protocol = ProtocolTestUtil.getProtocol(this.mockery);
        this.doc.setProtocol(protocol);
        ProtocolFactory.setProtocolRequiredFields(this.doc, null);
        protocol.setProtocolId(1L);
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol(protocol);
        protocol.addAttachmentsByType(attachment);
    }
    
    /**
     * looks up the services used for testing.
     */
    @Before
    public void setupServices() {
        this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
    }
    
    /**
     * Tests document validation with many-to-many relationships
     */
    @Test
    public void testManyToManyDocumentValidation()  {
        this.dictionaryValidationService.validateDocumentAndUpdatableReferencesRecursively(doc, 5, true);

        final String badPattern = "protocolList[0].attachmentProtocols[0].sequenceOwners[0].attachmentProtocols[0].*";
        
        //containsKeyMatchingPattern is not doing regex matching so '.' do not have to be escaped
        Assert.assertThat(GlobalVariables.getMessageMap().toString(), GlobalVariables.getMessageMap().containsKeyMatchingPattern(badPattern), is(false));
    }
    
}
