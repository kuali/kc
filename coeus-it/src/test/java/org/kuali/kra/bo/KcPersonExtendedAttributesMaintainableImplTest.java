/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.bo;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.impl.person.attr.KcPersonExtendedAttributesMaintainableImpl;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
import static org.junit.Assert.*;
public class KcPersonExtendedAttributesMaintainableImplTest extends MaintenanceRuleTestBase {
    private static final int NUMBER_SECTIONS = 4;
    private KcPersonExtendedAttributesMaintainableImpl kcPersonEAMaintainableImpl = null;
   
    @Before
    public void setUp() throws Exception {
        kcPersonEAMaintainableImpl = new KcPersonExtendedAttributesMaintainableImpl();
        
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        kcPersonEAMaintainableImpl = null;
    }

    @Test
    public void testGetSections() throws Exception {
        KcPersonExtendedAttributes kcPersonEA= new KcPersonExtendedAttributes();
        kcPersonEA.setPersonId("10000000060");   
        MaintenanceDocument kcPesonExtendedAttribuesDocument = newMaintDoc(kcPersonEA);
        kcPersonEAMaintainableImpl.setBusinessObject(kcPersonEA);
        kcPersonEAMaintainableImpl.setBoClass(KcPersonExtendedAttributes.class);
        kcPersonEAMaintainableImpl.setMaintenanceAction("new");
        kcPersonEAMaintainableImpl.setDocumentNumber(kcPesonExtendedAttribuesDocument.getDocumentNumber());
        List<Section> sections = kcPersonEAMaintainableImpl.getSections(kcPesonExtendedAttribuesDocument, kcPersonEAMaintainableImpl);
        assertEquals(NUMBER_SECTIONS, sections.size());
        
        for(Section section : sections) {
            for(Row row :section.getRows()) {
                for(Field field : row.getFields()) {
                    if(StringUtils.isNotEmpty(field.getPropertyName()) && field.getPropertyName().equalsIgnoreCase("personId")) {
                    assertFieldProperties(field, "principalId", Person.class.getName());
                    }
                }
            }
        }
            
    }

    private void assertFieldProperties(Field field, String keyName, String className) {
        assertEquals(field.getFieldConversions(), keyName + ":" + field.getPropertyName());
        assertTrue(field.isFieldDirectInquiryEnabled());
        assertEquals(field.getInquiryParameters(), field.getPropertyName() + ":" + keyName);
        assertEquals(field.getQuickFinderClassNameImpl(), className);
    }

}
  