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
  
