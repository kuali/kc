/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;

public class ProposalDevelopmentPersonnelAttachmentRuleTest extends ProposalDevelopmentRuleTestBase {

    private static final String NEW_PROP_PERSON_BIO = "newPropPersonBio";
    private static final String EMPTY_STRING = "";
    private ProposalDevelopmentPersonnelAttachmentRule rule = null;
    private List<PropPerDocType> documentTypes;
    private BusinessObjectService bos;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentPersonnelAttachmentRule();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        documentTypes = (List)bos.findAll(PropPerDocType.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        documentTypes=null;
        super.tearDown();
    }

    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertNotNull(documentTypes);
        assertTrue(documentTypes.size()>1);
        
        ProposalPersonBiography newProposalPersonBiography = new ProposalPersonBiography();
        newProposalPersonBiography.setDocumentTypeCode(documentTypes.get(1).getDocumentTypeCode());
        newProposalPersonBiography.setDescription("description");
        newProposalPersonBiography.setFileName("test.dat");
        newProposalPersonBiography.setProposalPersonNumber(new Integer(2));
        AddPersonnelAttachmentEvent addPersonnelAttachmentEvent = new AddPersonnelAttachmentEvent(EMPTY_STRING,document,newProposalPersonBiography);
        assertTrue(rule.processAddPersonnelAttachmentBusinessRules(addPersonnelAttachmentEvent));
    }
    
    /**
     * Test adding a personnel attachment with an unspecified document type code.
     * This corresponds to a empty string type code, i.e. the user didn't
     * select a document type.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedDocumentType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        ProposalPersonBiography newProposalPersonBiography = new ProposalPersonBiography();
        //newProposalPersonBiography.setDocumentTypeCode(documentTypes.get(1).getDocumentTypeCode());
        newProposalPersonBiography.setDescription("description");
        newProposalPersonBiography.setFileName("test.dat");
        newProposalPersonBiography.setProposalPersonNumber(new Integer(2));
        AddPersonnelAttachmentEvent addPersonnelAttachmentEvent = new AddPersonnelAttachmentEvent(EMPTY_STRING,document,newProposalPersonBiography);
        assertFalse(rule.processAddPersonnelAttachmentBusinessRules(addPersonnelAttachmentEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROP_PERSON_BIO+".documentTypeCode");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
    }
    
    /**
     * Test adding a personnel attachment with an unspecified person.
     * This corresponds to a empty person number, i.e. the user didn't
     * select a person.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedperson() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        ProposalPersonBiography newProposalPersonBiography = new ProposalPersonBiography();
        newProposalPersonBiography.setDocumentTypeCode(documentTypes.get(1).getDocumentTypeCode());
        newProposalPersonBiography.setDescription("description");
        newProposalPersonBiography.setFileName("test.dat");
       // newProposalPersonBiography.setProposalPersonNumber(new Integer(2));
        AddPersonnelAttachmentEvent addPersonnelAttachmentEvent = new AddPersonnelAttachmentEvent(EMPTY_STRING,document,newProposalPersonBiography);
        assertFalse(rule.processAddPersonnelAttachmentBusinessRules(addPersonnelAttachmentEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROP_PERSON_BIO+".proposalPersonNumber");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED);
    }
    

    /**
     * 
     * This method is to test that filename is required when attachment type and description are entered.
     * @throws Exception
     */
    @Test
    public void testFileNameRequired() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertNotNull(documentTypes);
        assertTrue(documentTypes.size()>1);
        
        ProposalPersonBiography newProposalPersonBiography = new ProposalPersonBiography();
        newProposalPersonBiography.setDocumentTypeCode(documentTypes.get(1).getDocumentTypeCode());
        newProposalPersonBiography.setDescription("description");
        //newProposalPersonBiography.setFileName("test.dat");
        newProposalPersonBiography.setProposalPersonNumber(new Integer(2));
        AddPersonnelAttachmentEvent addPersonnelAttachmentEvent = new AddPersonnelAttachmentEvent(EMPTY_STRING,document,newProposalPersonBiography);
        assertFalse(rule.processAddPersonnelAttachmentBusinessRules(addPersonnelAttachmentEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROP_PERSON_BIO+".personnelAttachmentFile");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME);

    }


}
