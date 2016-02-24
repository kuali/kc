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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalDevelopmentPersonnelAttachmentRule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.person.attachment.PropPerDocType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.AddPersonnelAttachmentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static org.junit.Assert.*;
public class ProposalDevelopmentPersonnelAttachmentRuleTest extends ProposalDevelopmentRuleTestBase {    
    private static final String EMPTY_STRING = "";
    
    private BusinessObjectService bos;
    private ProposalDevelopmentDocument document;

    private List<PropPerDocType> documentTypes;

    private ProposalPersonBiography newProposalPersonBiography;

    private ProposalDevelopmentPersonnelAttachmentRule rule = null;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentPersonnelAttachmentRule();
        bos = KcServiceLocator.getService(BusinessObjectService.class);
        documentTypes = (List)bos.findAll(PropPerDocType.class);
        document = getNewProposalDevelopmentDocument();
        newProposalPersonBiography = createNewProposalPersonBiography();        
    }

    @After
    public void tearDown() throws Exception {
        newProposalPersonBiography = null;
        document = null;
        rule = null;
        bos = null;
        documentTypes=null;        
        super.tearDown();
    }

    /**
     * 
     * This method is to test that description is provided when attachment type is Other.
     * @throws Exception
     */
    @Test
    public void testDescriptionRequiredForAttachementTypeOther() throws Exception {
        newProposalPersonBiography.setDocumentTypeCode(findOtherDocumentTypeCode());
        newProposalPersonBiography.setDescription(null);

        assertFalse(rule.processAddPersonnelAttachmentBusinessRules(createAddPersonnelAttachmentEvent()));
        
        checkErrorCountAndContent("description", KeyConstants.ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED);
    }

    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        assertNotNull(documentTypes);
        assertTrue(documentTypes.size() > 1);
        assertTrue(rule.processAddPersonnelAttachmentBusinessRules(createAddPersonnelAttachmentEvent()));
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
        newProposalPersonBiography.setDocumentTypeCode(null);
        
        assertFalse(rule.processAddPersonnelAttachmentBusinessRules(createAddPersonnelAttachmentEvent()));
        
        checkErrorCountAndContent("documentTypeCode", KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
    }
    
    /**
     * Test adding a personnel attachment with an unspecified person.
     * This corresponds to a empty person number, i.e. the user didn't
     * select a person.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedPerson() throws Exception {
        newProposalPersonBiography.setProposalPersonNumber(null);
        
        assertFalse(rule.processAddPersonnelAttachmentBusinessRules(createAddPersonnelAttachmentEvent()));
        
        checkErrorCountAndContent("proposalPersonNumberString", KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED);
    }

    /**
     * This method does what its name says
     * @param errorPathContext
     * @param errorKey
     */
    private void checkErrorCountAndContent(String errorPathContext, String errorKey) {
        List errors = GlobalVariables.getMessageMap().getMessages(errorPathContext);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), errorKey);
    }
    
    /**
     * This method does what its name says. We don't use a field configured by setUp() because the event constructor makes a copy of our
     * attachment and we need to modify its state in each test
     * @return
     */
    private AddPersonnelAttachmentEvent createAddPersonnelAttachmentEvent() {
        return new AddPersonnelAttachmentEvent(EMPTY_STRING, document, newProposalPersonBiography);
    }
    
    /**
     * 
     * This method does what its name says
     * @return
     */
    private ProposalPersonBiography createNewProposalPersonBiography() {
        ProposalPersonBiography proposalPersonBiography = new ProposalPersonBiography();
        proposalPersonBiography.setDocumentTypeCode(documentTypes.get(1).getCode());
        proposalPersonBiography.setDescription("description");
        proposalPersonBiography.setName("test.dat");
        proposalPersonBiography.setProposalPersonNumber(new Integer(2));
        return proposalPersonBiography;
    }

    /**
     * 
     * This method does what its name says
     * 
     * @param description
     * @return
     */
    private PropPerDocType findDocumentTypeForDescription(String description) {
        PropPerDocType foundDocType = null;
        
        for(PropPerDocType docType: documentTypes) {
            if(docType.getDescription().equalsIgnoreCase(description)) {
                foundDocType = docType;
                break;
            }
        }
        
        return foundDocType;
    }
    

    private String findOtherDocumentTypeCode() {
        return findDocumentTypeForDescription(ProposalDevelopmentPersonnelAttachmentRule.OTHER_DOCUMENT_TYPE_DESCRIPTION).getCode();
    }
}
