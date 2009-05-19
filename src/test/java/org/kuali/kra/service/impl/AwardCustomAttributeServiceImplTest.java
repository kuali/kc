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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSPropertyConstants;

/**
 * This class tests <code>AwardCustomAttributeService</code>
 */

@RunWith(JMock.class)
public class AwardCustomAttributeServiceImplTest {

    final Collection<CustomAttributeDocument> customAttributeDocumentList = 
                                            new ArrayList<CustomAttributeDocument>();
    AwardCustomAttributeServiceImpl awardCustomAttributeServiceImpl;
    final Map<String, Object> queryMap = new HashMap<String, Object>();
    
    private Mockery context = new JUnit4Mockery();
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCustomAttributeServiceImpl = new AwardCustomAttributeServiceImpl();
        queryMap.put(KNSPropertyConstants.DOCUMENT_TYPE_CODE, "AWRD");
        CustomAttributeDocument customAttributeDocument1 = new CustomAttributeDocument();
        CustomAttributeDocument customAttributeDocument2 = new CustomAttributeDocument();
        customAttributeDocument1.setActive(true);
        customAttributeDocument1.setCustomAttributeId(1);
        customAttributeDocument2.setActive(true);
        customAttributeDocument2.setCustomAttributeId(2);
        customAttributeDocumentList.add(customAttributeDocument1);
        customAttributeDocumentList.add(customAttributeDocument2);
        
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        
    }
    
    /**
     * Tests method getDefaultAwardCustomAttributeDocuments on AwardCustomAttributeServiceImpl returns the
     * right values.
     */
    @Test
    public void testGetDefaultAwardCustomAttributeDocuments() {
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE;
        MOCKED_BUSINESS_OBJECT_SERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKED_BUSINESS_OBJECT_SERVICE).findMatching(CustomAttributeDocument.class, queryMap); 
            will(returnValue(customAttributeDocumentList));
        }});
        awardCustomAttributeServiceImpl.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        Assert.assertTrue(awardCustomAttributeServiceImpl.getDefaultAwardCustomAttributeDocuments().size() == 2);
    }
      
}
