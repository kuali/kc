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
package org.kuali.kra.rice.integration;

import static org.hamcrest.core.Is.is;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This is an integration test that makes sure the {@link DictionaryValidationService DictionaryValidationService} does not
 * fail validating BO relationships that are many-to-many.
 */
public class ManyToManyValidationTest extends KraWebTestBase {

    private DictionaryValidationService dictionaryValidationService;
    private ProtocolDocument doc;
    private Mockery mockery = new JUnit4Mockery(); 
    
    /**
     * Create a ProtocolDocument used for testing.
     * @throws Exception if bad happens
     */
    @Before
    public void setupDocument() throws Exception { 
        this.doc = (ProtocolDocument) KraServiceLocator.getService(DocumentService.class).getNewDocument(ProtocolDocument.class);
        
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
        this.dictionaryValidationService = KraServiceLocator.getService(DictionaryValidationService.class);     
    }
    
    /**
     * Tests document validation with many-to-many relationships
     */
    @Test
    public void testManyToManyDocumentValidation()  {
        this.dictionaryValidationService.validateDocumentAndUpdatableReferencesRecursively(doc, 5, true);

        final String badPattern = "protocolList[0].attachmentProtocols[0].sequenceOwners[0].attachmentProtocols[0].*";
        
        //containsKeyMatchingPattern is not doing regex matching so '.' do not have to be escaped
        Assert.assertThat(GlobalVariables.getErrorMap().toString(), GlobalVariables.getErrorMap().containsKeyMatchingPattern(badPattern), is(false));
    }
    
}