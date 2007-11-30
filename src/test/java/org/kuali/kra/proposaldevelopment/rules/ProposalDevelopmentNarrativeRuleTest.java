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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeStatus;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;

/**
 * Test the business rules for Proposal Narratives.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentNarrativeRuleTest extends ProposalDevelopmentRuleTestBase {

    private static final String DOCUMENT_NARRATIVES = "document.narratives";
    private static final String EMPTY_STRING = "";
    private static final String ALLOW_MULTIPLE = "allowMultiple";
    private static final String NO = "N";
    private static final String SYSTEM_GENERATED = "systemGenerated";
    private ProposalDevelopmentNarrativeRule rule = null;
    private List<NarrativeType> narrativeTypes;
    private List<NarrativeStatus> narrativeStatuses;
    private BusinessObjectService bos;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentNarrativeRule();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String,String> qMap = new HashMap<String,String>();
        qMap.put(SYSTEM_GENERATED, NO);
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
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertNotNull(narrativeTypes);
        assertNotNull(narrativeStatuses);
        assertTrue(narrativeTypes.size()>1);
        assertTrue(narrativeStatuses.size()>1);
        
        Narrative narrative = new Narrative();
        Narrative newNarrative = new Narrative();
        narrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(1).getNarrativeTypeCode());
        narrative.setModuleStatusCode(narrativeStatuses.get(0).getNarrativeStatusCode());
        newNarrative.setModuleStatusCode(narrativeStatuses.get(1).getNarrativeStatusCode());
        newNarrative.setProposalNumber(document.getProposalNumber());
//        document.addNarrative(narrative);
        AddNarrativeEvent addNarrativeEvent = new AddNarrativeEvent(EMPTY_STRING,document,newNarrative);
        assertTrue(rule.processAddNarrativeBusinessRules(addNarrativeEvent));
    }
    
    /**
     * Test adding an narrative with an unspecified narrative type code.
     * This corresponds to a empty string type code, i.e. the user didn't
     * select an narrative type.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedNarrativeType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(EMPTY_STRING);
        newNarrative.setModuleStatusCode(narrativeStatuses.get(0).getNarrativeStatusCode());
        AddNarrativeEvent addNarrativeEvent = new AddNarrativeEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddNarrativeBusinessRules(addNarrativeEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(DOCUMENT_NARRATIVES);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
    }
    
    /**
     * Test adding an narrative with an unspecified narrative type code.
     * This corresponds to a empty string type code, i.e. the user didn't
     * select an narrative type.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedNarrativeStatus() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        newNarrative.setModuleStatusCode(EMPTY_STRING);
        newNarrative.setProposalNumber(document.getProposalNumber());
//        document.addNarrative(newNarrative);
        AddNarrativeEvent addNarrativeEvent = new AddNarrativeEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddNarrativeBusinessRules(addNarrativeEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(DOCUMENT_NARRATIVES);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
    }
    
    /**
     * Test adding an narrative with a duplicate type code, i.e. the
     * document already has an narrative with that type code.
     * 
     * @throws Exception
     */
    @Test
    public void testDuplicateNarrativeType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertNotNull(narrativeTypes);
        assertNotNull(narrativeStatuses);
        assertTrue(narrativeTypes.size()>1);
        assertTrue(narrativeStatuses.size()>1);
        
        Narrative narrative = new Narrative();
        Narrative newNarrative = new Narrative();
        narrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(0).getNarrativeTypeCode());
        narrative.setModuleStatusCode(narrativeStatuses.get(0).getNarrativeStatusCode());
        newNarrative.setModuleStatusCode(narrativeStatuses.get(0).getNarrativeStatusCode());
        newNarrative.setProposalNumber(document.getProposalNumber());
        document.getNarratives().add(narrative);
//        document.addNarrative(narrative);
        AddNarrativeEvent addNarrativeEvent = new AddNarrativeEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddNarrativeBusinessRules(addNarrativeEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(DOCUMENT_NARRATIVES);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_NARRATIVE_TYPE_DUPLICATE);
    }
    /**
     * Test adding an narrative without description for a narrativetype with 
     * allowmultiple flag as false
     * 
     * @throws Exception
     */
    @Test
    public void testEmptyDescriptionForNotAllowMultipleNarrativeType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertNotNull(narrativeTypes);
        assertNotNull(narrativeStatuses);
        assertTrue(narrativeTypes.size()>1);
        assertTrue(narrativeStatuses.size()>1);
        
        Narrative newNarrative = new Narrative();
        newNarrative.setNarrativeTypeCode(narrativeTypes.get(narrativeTypes.size()-1).getNarrativeTypeCode());
        newNarrative.setModuleStatusCode(narrativeStatuses.get(0).getNarrativeStatusCode());
        AddNarrativeEvent addNarrativeEvent = new AddNarrativeEvent(EMPTY_STRING,document,newNarrative);
        assertFalse(rule.processAddNarrativeBusinessRules(addNarrativeEvent));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(DOCUMENT_NARRATIVES);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED);
    }
}
