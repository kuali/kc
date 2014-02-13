/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeStatus;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * 
 * This class is to test the rules to add institutional attachment
 */
public class ProposalDevelopmentInstituteAttachmentRuleTest extends ProposalDevelopmentRuleTestBase {

    private static final String NEW_INSTITUTE_ATTACHMENT = "newInstituteAttachment";
    private static final String EMPTY_STRING = "";
    private static final String ALLOW_MULTIPLE = "allowMultiple";
    private static final String NO = "N";
    private static final String SYSTEM_GENERATED = "systemGenerated";
    private static final String NARRATIVE_TYPE_GROUP = "narrativeTypeGroup";
    private static final String STRING_151_CHARS_LONG = "Lorem ipsum dolor sit amet consectetur adipiscing elit. Suspendisse quis tortor. Morbi sollicitudin leo a faucibus aliquet nisl velit vulputate sed awk.";
    private ProposalDevelopmentInstituteAttachmentRule rule = null;
    private List<NarrativeType> narrativeTypes;
    private List<NarrativeStatus> narrativeStatuses;
    private BusinessObjectService bos;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentInstituteAttachmentRule();
        bos = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String,String> qMap = new HashMap<String,String>();
        qMap.put(SYSTEM_GENERATED, NO);
        qMap.put(NARRATIVE_TYPE_GROUP, Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE);
        narrativeTypes = (List)bos.findMatchingOrderBy(NarrativeType.class, qMap, ALLOW_MULTIPLE, true);
        narrativeStatuses = (List)bos.findAll(NarrativeStatus.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        narrativeTypes=null;
        narrativeStatuses=null;
        super.tearDown();
    }

    /**
     * 
     * This method to test narrative types and status are OK
     * @throws Exception
     */
    @Test
    public void testNarrativeTypesAndStatuses() throws Exception {
        
        assertNotNull(narrativeTypes);
        assertNotNull(narrativeStatuses);
        assertTrue(narrativeTypes.size()>1);
        assertTrue(narrativeStatuses.size()>1);
    }
    
    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(1).getNarrativeTypeCode());
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        newNarrative.setFileName("test.dat");
        AddInstituteAttachmentEvent addInstituteAttachmentEvent = new AddInstituteAttachmentEvent(EMPTY_STRING,document,newNarrative);
        assertTrue(rule.processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent));
    }

    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testDescriptionTooLong() throws Exception {
        
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(1).getNarrativeTypeCode());
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        newNarrative.setFileName("test.dat");
        newNarrative.setModuleTitle(STRING_151_CHARS_LONG);
        AddInstituteAttachmentEvent addInstituteAttachmentEvent = new AddInstituteAttachmentEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent));

        List errors = GlobalVariables.getMessageMap().getMessages(NEW_INSTITUTE_ATTACHMENT+".moduleTitle");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_MAX_LENGTH);
}

    /**
     * Test adding an institutional attachment with an unspecified attachment type code.
     * This corresponds to a empty string type code, i.e. the user didn't
     * select an attachment type.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedNarrativeType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(EMPTY_STRING);
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        newNarrative.setFileName("test.dat");
        AddInstituteAttachmentEvent addInstituteAttachmentEvent = new AddInstituteAttachmentEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent));
        
        List errors = GlobalVariables.getMessageMap().getMessages(NEW_INSTITUTE_ATTACHMENT+".institutionalAttachmentTypeCode");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
    }
    
    /**
     * Test adding a institutional attachment with an unspecified narrative ststus.
     * This corresponds to a empty string ststus code
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedNarrativeStatus() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        newNarrative.setModuleStatusCode(EMPTY_STRING);
        newNarrative.setFileName("test.dat");
        AddInstituteAttachmentEvent addInstituteAttachmentEvent = new AddInstituteAttachmentEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent));
        
        List errors = GlobalVariables.getMessageMap().getMessages(NEW_INSTITUTE_ATTACHMENT+".moduleStatusCode");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
    }
    
    /**
     * Test adding a institutional attachment with a duplicate type code, i.e. the
     * document already has an institutional attachment with that type code.
     * 
     * @throws Exception
     */
    @Test
    public void testDuplicateNarrativeType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        
        Narrative narrative = new Narrative();
        Narrative newNarrative = new Narrative();
        narrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        document.getDevelopmentProposal().getInstituteAttachments().add(narrative);
        AddInstituteAttachmentEvent addInstituteAttachmentEvent = new AddInstituteAttachmentEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent));
        
        List errors = GlobalVariables.getMessageMap().getMessages(NEW_INSTITUTE_ATTACHMENT+".institutionalAttachmentTypeCode");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_NARRATIVE_TYPE_DUPLICATE);
    }

    /**
     * 
     * This method is to test that filename is required when institutional attachment type and description are entered.
     * @throws Exception
     */
    @Test
    public void testFileNameRequired() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        
        Narrative narrative = new Narrative();
        narrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        narrative.setModuleTitle("description field");
        AddInstituteAttachmentEvent addInstituteAttachmentEvent = new AddInstituteAttachmentEvent(EMPTY_STRING,document,narrative);
        assertFalse(rule.processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent));
        
        List errors = GlobalVariables.getMessageMap().getMessages(NEW_INSTITUTE_ATTACHMENT+".narrativeFile");
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME);

    }

}
