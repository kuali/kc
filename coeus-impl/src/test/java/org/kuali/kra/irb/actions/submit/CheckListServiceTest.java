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
package org.kuali.kra.irb.actions.submit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the CheckListService implementation.
 */
@RunWith(JMock.class)
public class CheckListServiceTest {

    private CheckListServiceImpl checkListService;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
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
