/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.LookupService;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Person;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.KNSServiceLocator;

import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class UnitHierarchyWebTest extends KraWebTestBase {
    
    private LookupService lookupService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        lookupService=KNSServiceLocator.getLookupService();
    }
    
    
    @After
    public void tearDown() throws Exception {
        lookupService=null;
        super.tearDown();
       
    }

    
    @Test
    public void moveUnit() throws IOException
    {
        Map<String, Object> personfieldValues = new HashMap<String, Object>();
        Map<String, Object> la_Rates_fieldValues = new HashMap<String, Object>();
        Map<String, Object> rates_fieldValues = new HashMap<String, Object>();
        String unitNumber="BL-RUGS";
        personfieldValues.put("homeUnit", unitNumber);
        la_Rates_fieldValues.put("unitNumber",unitNumber);
        rates_fieldValues.put("unitNumber", unitNumber);
        
        Collection<Person> beforeperson = lookupService.findCollectionBySearch(Person.class, personfieldValues);
        Collection<InstituteLaRate> beforeLA_Rates = lookupService.findCollectionBySearch(InstituteLaRate.class, la_Rates_fieldValues);
        Collection<InstituteRate> before_Rates = lookupService.findCollectionBySearch(InstituteRate.class, rates_fieldValues);
        
        HtmlPage adminPage = clickOn(getPortalPage(), "Maintenance", "Kuali Portal Index");
        HtmlAnchor unithierarchypage = getAnchorFromPage(adminPage,"Unit Hierarchy");
        assertNotNull(unithierarchypage);
        HtmlPage unithierarchypage1 = (HtmlPage) unithierarchypage.click();
        assertNotNull(unithierarchypage1);
        FrameWindow frame = unithierarchypage1.getFrameByName("iframeportlet");
        assertContains((HtmlPage)frame.getEnclosedPage(), "00001");
        assertContains((HtmlPage)frame.getEnclosedPage(), "BL-BL");
        assertContains((HtmlPage)frame.getEnclosedPage(), "IN-IN");
        assertContains((HtmlPage)frame.getEnclosedPage(), "BL-RUGS");
        HtmlPage unitspage=(HtmlPage)frame.getEnclosedPage();
        HtmlAnchor anchor = (HtmlAnchor)unitspage.getHtmlElementById("editBL-RUGS");
        assertNotNull(anchor);
        HtmlPage unitpage = (HtmlPage) anchor.click();
        assertNotNull(unitpage);
        assertContains(unitpage, "Unit Maintenance Document");
        assertEquals("BL-BL", getFieldValue(unitpage, "document.newMaintainableObject.parentUnitNumber"));
        setFieldValue(unitpage,"document.newMaintainableObject.parentUnitNumber","IN-IN");
        setFieldValue(unitpage,"document.documentHeader.financialDocumentDescription","Moving a unit");
        HtmlPage portalpage = clickOn(unitpage, "methodToCall.blanketApprove", "Kuali Portal Index");
        assertNotNull(portalpage);
        HtmlAnchor unithierarchypage2 = getAnchorFromPage(portalpage,"Unit Hierarchy");
        assertNotNull(unithierarchypage2);
        HtmlPage unithierarchypage3 = (HtmlPage) unithierarchypage2.click();
        assertNotNull(unithierarchypage3);
        FrameWindow frame1 = unithierarchypage3.getFrameByName("iframeportlet");
        assertContains((HtmlPage)frame1.getEnclosedPage(), "00001");
        assertContains((HtmlPage)frame1.getEnclosedPage(), "BL-BL");
        assertContains((HtmlPage)frame1.getEnclosedPage(), "IN-IN");
        assertContains((HtmlPage)frame1.getEnclosedPage(), "BL-RUGS");
        HtmlPage unitspage1=(HtmlPage)frame1.getEnclosedPage();
        HtmlAnchor anchor1 = (HtmlAnchor)unitspage1.getHtmlElementById("editBL-RUGS");
        assertNotNull(anchor1);
        HtmlPage unitpage1 = (HtmlPage) anchor1.click();
        assertNotNull(unitpage1);
        assertEquals("IN-IN", getFieldValue(unitpage1, "document.newMaintainableObject.parentUnitNumber"));
        
        Collection<Person> afterperson = lookupService.findCollectionBySearch(Person.class, personfieldValues);
        Collection<InstituteLaRate> afterLA_Rates = lookupService.findCollectionBySearch(InstituteLaRate.class, la_Rates_fieldValues);
        Collection<InstituteRate> after_Rates = lookupService.findCollectionBySearch(InstituteRate.class, rates_fieldValues);
        assertEquals(beforeperson,afterperson);
        assertEquals(beforeperson.size(),afterperson.size());
        assertEquals(beforeLA_Rates.size(),afterLA_Rates.size());
        assertEquals(before_Rates.size(),after_Rates.size());
        
        //Moving Bck the unit
        
        HtmlAnchor unithierarchypage4 = getAnchorFromPage(adminPage,"Unit Hierarchy");
        assertNotNull(unithierarchypage4);
        HtmlPage unithierarchypage5 = (HtmlPage) unithierarchypage4.click();
        assertNotNull(unithierarchypage5);
        FrameWindow frame2 = unithierarchypage5.getFrameByName("iframeportlet");
        assertContains((HtmlPage)frame2.getEnclosedPage(), "00001");
        assertContains((HtmlPage)frame2.getEnclosedPage(), "BL-BL");
        assertContains((HtmlPage)frame2.getEnclosedPage(), "IN-IN");
        assertContains((HtmlPage)frame2.getEnclosedPage(), "BL-RUGS");
        HtmlPage unitspage2=(HtmlPage)frame2.getEnclosedPage();
        HtmlAnchor anchor2 = (HtmlAnchor)unitspage2.getHtmlElementById("editBL-RUGS");
        assertNotNull(anchor2);
        HtmlPage unitpage3 = (HtmlPage) anchor2.click();
        assertNotNull(unitpage3);
        assertContains(unitpage3, "Unit Maintenance Document");
        assertEquals("IN-IN", getFieldValue(unitpage3, "document.newMaintainableObject.parentUnitNumber"));
        setFieldValue(unitpage3,"document.newMaintainableObject.parentUnitNumber","BL-BL");
        setFieldValue(unitpage3,"document.documentHeader.financialDocumentDescription","Moving back to original place");
        HtmlPage portalpage1 = clickOn(unitpage3, "methodToCall.blanketApprove", "Kuali Portal Index");
        assertNotNull(portalpage1);
        
        
        
        
        
        
        
    }
    
    
    private HtmlAnchor getAnchorFromPage(HtmlPage page, String stringToMatch) {
        List<HtmlAnchor> anchors = page.getAnchors();
        HtmlAnchor target = null;
        for (HtmlAnchor anchor : anchors) {
            if (anchor.getHrefAttribute().contains(stringToMatch)) {
                target = anchor;
            }
        }
        return target;
    }
}
