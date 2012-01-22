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
package org.kuali.kra.irb.actions.noreview;

import java.sql.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class ProtocolReviewNotRequiredServiceTest extends KcUnitTestBase {
    
    private static final String COMMENTS = "really cool comments";
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date DECISION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolReviewNotRequiredServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new ProtocolReviewNotRequiredServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setBusinessObjectService(getMockBusinessObjectService());
    }

    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        
        super.tearDown();
    }
    
    /**
     * Test method for {@link org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredServiceImpl#reviewNotRequired(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean)}.
     */
    @Test
    public void testReviewNotRequired() throws Exception {
        ProtocolReviewNotRequiredBean protocolReviewNotRequiredBean = getMockProtocolReviewNotRequiredBean();
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("10101010");
        
        service.reviewNotRequired(protocolDocument, protocolReviewNotRequiredBean);
        
        assertEquals(ProtocolStatus.IRB_REVIEW_NOT_REQUIRED, protocolDocument.getProtocol().getProtocolStatusCode());
    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolReviewNotRequiredBean getMockProtocolReviewNotRequiredBean() {
        final ProtocolReviewNotRequiredBean bean = context.mock(ProtocolReviewNotRequiredBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(COMMENTS));
            
            allowing(bean).getActionDate();
            will(returnValue(ACTION_DATE));
            
            allowing(bean).getDecisionDate();
            will(returnValue(DECISION_DATE));
        }});
        
        return bean;
    }

}