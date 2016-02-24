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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.impl.custom.attr.CustomAttributeDocumentMaintainableImpl;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class CustomAttributeDocumentMaintainableImplTest extends KcIntegrationTestBase {
    
    Collection<CoeusModule> modules;
    CustomAttributeDocumentMaintainableImpl maintainable;
    
    private static final String IMPLEMENTED_MODS = "1,2,3,7,8";
    private static final String NON_IMPLEMENTED_MODS = "4,5,6,9,11";

    @Before
    public void setUp() throws Exception {
        modules = KcServiceLocator.getService(BusinessObjectService.class).findAll(CoeusModule.class);
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
