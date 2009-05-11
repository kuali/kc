/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.irb.actions.submit.CheckListServiceImpl;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Test the CheckListService implementation.
 */
@RunWith(JMock.class)
public class CheckListServiceTest {

    private CheckListServiceImpl checkListService;
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() {
        checkListService = new CheckListServiceImpl();
    }
    
    /**
     * Test getting the list of Expedited Reviews.
     */
    @Test
    public void testExpeditedReviews() {
        final Collection<ExpeditedReviewCheckListItem> list = new ArrayList<ExpeditedReviewCheckListItem>();
        list.add(new ExpeditedReviewCheckListItem());
        list.add(new ExpeditedReviewCheckListItem());
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findAll(ExpeditedReviewCheckListItem.class); will(returnValue(list));
        }});
        checkListService.setBusinessObjectService(businessObjectService);
        
        List<ExpeditedReviewCheckListItem> items = checkListService.getExpeditedReviewCheckList();
        assertEquals(list.size(), items.size());
        for (ExpeditedReviewCheckListItem item : items) {
            assertTrue(list.contains(item));
        }
    }
    
    /**
     * Test getting the list of Exempt Studies.
     */
    @Test
    public void testExemptStudies() {
        final Collection<ExemptStudiesCheckListItem> list = new ArrayList<ExemptStudiesCheckListItem>();
        list.add(new ExemptStudiesCheckListItem());
        list.add(new ExemptStudiesCheckListItem());
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findAll(ExemptStudiesCheckListItem.class); will(returnValue(list));
        }});
        checkListService.setBusinessObjectService(businessObjectService);
        
        List<ExemptStudiesCheckListItem> items = checkListService.getExemptStudiesCheckList();
        assertEquals(list.size(), items.size());
        for (ExemptStudiesCheckListItem item : items) {
            assertTrue(list.contains(item));
        }
    }
}
