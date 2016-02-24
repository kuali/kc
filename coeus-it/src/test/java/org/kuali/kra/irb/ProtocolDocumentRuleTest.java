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
package org.kuali.kra.irb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class ProtocolDocumentRuleTest extends ProtocolRuleTestBase {

    private static final String PROTOCOL_LUN_FORM_ELEMENT="protocolHelper.leadUnitNumber";
    private static final String ERROR_PROPERTY_ORGANIZATION_ID = "protocolHelper.newProtocolLocation.organizationId"; 
    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.protocolList[0].protocolResearchAreas.inactive";
    private static final String SEPERATOR = ".";
    
    private ProtocolDocumentRule rule = null;
    private DictionaryValidationService dictionaryValidationService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolDocumentRule();
        dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        dictionaryValidationService = null;
        super.tearDown();
    }

    @Test
    public void testRequiredBusinessRuleOK() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        
        MessageMap messageMap = GlobalVariables.getMessageMap();
        messageMap.addToErrorPath(KcTransactionalDocumentRuleBase.DOCUMENT_ERROR_PATH);
        dictionaryValidationService.validateDocumentAndUpdatableReferencesRecursively(document, 10, true, true);
        assertTrue(messageMap.hasNoErrors());
        messageMap.removeFromErrorPath(KcTransactionalDocumentRuleBase.DOCUMENT_ERROR_PATH);
        
        assertTrue(rule.processLeadUnitBusinessRules(document));
    }
    
    @Test
    public void testMissingRequiredFields() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        //setProtocolRequiredFields(document);
        document.getDocumentHeader().setDocumentDescription(DEFAULT_DOCUMENT_DESCRIPTION);
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(ProtocolDocumentRule.DOCUMENT_ERROR_PATH);
        dictionaryValidationService.validateDocumentAndUpdatableReferencesRecursively(document, 10,true,true);
        assertEquals(3, errorMap.getErrorMessages().size());
        assertError("document.protocolList[0].leadUnitNumber", RiceKeyConstants.ERROR_REQUIRED);
        assertError("document.protocolList[0].title", RiceKeyConstants.ERROR_REQUIRED);
        assertError("document.protocolList[0].principalInvestigatorId", RiceKeyConstants.ERROR_REQUIRED);
        
    }

    @Test
    public void testInvalidLU() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().setLeadUnitNumber("bogus");
        assertFalse(rule.processLeadUnitBusinessRules(document));
        assertError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID);
    }

    @Test
    public void testProcessProtocolLocationBusinessRules() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().getProtocolLocations().clear();
        assertFalse(rule.processProtocolLocationBusinessRules(document));
        assertError(ERROR_PROPERTY_ORGANIZATION_ID, KeyConstants.ERROR_PROTOCOL_LOCATION_SHOULD_EXIST);
    }
    
    /**
     * This method tests the logic for validating that all research areas associated with a protocol are active.
     * Specifically it tests 3 different cases: 
     *      1. Protocol has no research areas -- should give no error as rule is satisfied trivially.
     *      2. Protocol has research areas and all are active -- should give no error
     *      3. Protocol has research areas and some are inactive -- should give a single error with the error-property correctly encoding the
     *              indices of the inactive areas.
     */
    @Test
    public void testProcessProtocolResearchAreaBusinessRules() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        // check case 1
        assertTrue(rule.processProtocolResearchAreaBusinessRules(document));
        
        // check case 2
        ProtocolResearchArea dummyPRA0 = new ProtocolResearchArea();
        ResearchArea dummyRA0 = new ResearchArea();
        dummyRA0.setActive(true);
        dummyPRA0.setResearchAreas(dummyRA0);
        
        ProtocolResearchArea dummyPRA1 = new ProtocolResearchArea();
        ResearchArea dummyRA1 = new ResearchArea();
        dummyRA1.setActive(true);
        dummyPRA1.setResearchAreas(dummyRA1);
        
        ProtocolResearchArea dummyPRA2 = new ProtocolResearchArea();
        ResearchArea dummyRA2 = new ResearchArea();
        dummyRA2.setActive(true);
        dummyPRA2.setResearchAreas(dummyRA2);
        
        ProtocolResearchArea dummyPRA3 = new ProtocolResearchArea();
        ResearchArea dummyRA3 = new ResearchArea();
        dummyRA3.setActive(true);
        dummyPRA3.setResearchAreas(dummyRA3);
        
        List<ProtocolResearchAreaBase> pras = new ArrayList<ProtocolResearchAreaBase>();
        pras.add(dummyPRA0);
        pras.add(dummyPRA1);
        pras.add(dummyPRA2);
        pras.add(dummyPRA3);
        
        document.getProtocol().setProtocolResearchAreas(pras);
        
        assertTrue(document.getProtocol().getProtocolResearchAreas(0).getResearchAreas().isActive());
        assertTrue(document.getProtocol().getProtocolResearchAreas(1).getResearchAreas().isActive());
        assertTrue(document.getProtocol().getProtocolResearchAreas(2).getResearchAreas().isActive());
        assertTrue(document.getProtocol().getProtocolResearchAreas(3).getResearchAreas().isActive());
        
        assertTrue(rule.processProtocolResearchAreaBusinessRules(document));
        
        // check case 3
        assertTrue(document.getProtocol().getProtocolResearchAreas(0).getResearchAreas().isActive());
        
        dummyRA1.setActive(false);
        assertFalse(document.getProtocol().getProtocolResearchAreas(1).getResearchAreas().isActive());
        
        assertTrue(document.getProtocol().getProtocolResearchAreas(2).getResearchAreas().isActive());
        
        dummyRA3.setActive(false);
        assertFalse(document.getProtocol().getProtocolResearchAreas(3).getResearchAreas().isActive());
        
        assertFalse(rule.processProtocolResearchAreaBusinessRules(document));
        String errorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + "1.3.";
        assertError(errorPropertyKey, KeyConstants.ERROR_PROTOCOL_RESEARCH_AREA_INACTIVE);
    }

}
