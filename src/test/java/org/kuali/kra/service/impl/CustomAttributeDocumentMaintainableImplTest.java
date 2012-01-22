/*
 * Copyright 2005-2010 The Kuali Foundation
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

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class CustomAttributeDocumentMaintainableImplTest extends KcUnitTestBase {
    
    Collection<CoeusModule> modules;
    CustomAttributeDocumentMaintainableImpl maintainable;
    
    private static final String IMPLEMENTED_MODS = "1,2,3,7,8";
    private static final String NON_IMPLEMENTED_MODS = "4,5,6,9,11";

    @Before
    public void setUp() throws Exception {
        modules = KraServiceLocator.getService(BusinessObjectService.class).findAll(CoeusModule.class);
        maintainable = new CustomAttributeDocumentMaintainableImpl();
    }

    @After
    public void tearDown() throws Exception {
        modules = null;
        maintainable = null;
    }

    @Test
    public void testConvertModuleNumberToDocumentTypeCode() {
        assertEquals(10, modules.size());
        for(CoeusModule module : modules) {
            if (IMPLEMENTED_MODS.contains(module.getModuleCode())) {
                String results = maintainable.convertModuleNumberToDocumentTypeCode(Integer.parseInt(module.getModuleCode()));
                
                switch (Integer.parseInt(module.getModuleCode())) {
                    case CoeusModule.AWARD_MODULE_CODE_INT: {
                        assertEquals(AwardDocument.DOCUMENT_TYPE_CODE, results);
                        break;
                    }
                    case CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE_INT: {
                        assertEquals(InstitutionalProposalDocument.DOCUMENT_TYPE_CODE, results);
                        break;
                    }
                    case CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE_INT: {
                        assertEquals(ProposalDevelopmentDocument.DOCUMENT_TYPE_CODE, results);
                        break;
                    }
                    case CoeusModule.IRB_MODULE_CODE_INT: {
                        assertEquals(ProtocolDocument.DOCUMENT_TYPE_CODE, results);
                        break;
                    }
                    case CoeusModule.COI_DISCLOSURE_MODULE_CODE_INT: {
                        assertEquals(CoiDisclosureDocument.DOCUMENT_TYPE_CODE, results);
                        break;
                    }
                    default: {
                        assertTrue("should never get here, Current Module Number: " + module.getModuleCode(), false);
                    }
                }
                
            } else if (NON_IMPLEMENTED_MODS.contains(module.getModuleCode())) {
                try{
                    String results = maintainable.convertModuleNumberToDocumentTypeCode(Integer.parseInt(module.getModuleCode()));
                    assertTrue("Current Module Number: " + module.getModuleCode(), false);
                } catch (IllegalArgumentException iae) {
                    assertTrue("Current Module Number: " + module.getModuleCode(), true);
                } catch (Exception e) {
                    assertTrue("Current Module Number: " + module.getModuleCode(), false);
                }
            } else {
                //an unexpected code found
                assertTrue(false);
            }
        }
    }

}
