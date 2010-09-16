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

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class...
 */
public class ProtocolReviewNotRequiredServiceTest extends KcUnitTestBase {
    
    private ProtocolReviewNotRequiredServiceImpl protocolReviewNotRequiredServiceImpl;
    private ProtocolReviewNotRequiredService protocolReviewNotRequiredService;
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private ProtocolReviewNotRequiredBean actionBean;
    private ProtocolDocument protocolDocument;

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        protocolReviewNotRequiredServiceImpl = new ProtocolReviewNotRequiredServiceImpl();
        protocolReviewNotRequiredService = KraServiceLocator.getService(ProtocolReviewNotRequiredService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolActionService = KraServiceLocator.getService(ProtocolActionService.class);
        actionBean = createValidProtocolReviewNotRequiredBean();
        protocolDocument = ProtocolFactory.createProtocolDocument("10101010");
        
    }

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        protocolReviewNotRequiredServiceImpl = null;
        protocolReviewNotRequiredService = null;
        businessObjectService = null;
        protocolActionService = null;
        actionBean = null;
        protocolDocument = null;
    }
    
    private ProtocolReviewNotRequiredBean createValidProtocolReviewNotRequiredBean() {
        ProtocolReviewNotRequiredBean bean = new ProtocolReviewNotRequiredBean();
        bean.setComments("really cool comments");
        bean.setActionDate(new Date(System.currentTimeMillis()));
        bean.setDecisionDate(new Date(System.currentTimeMillis()));
        return bean;
    }

    /**
     * Test method for {@link org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredServiceImpl#setBusinessObjectService(org.kuali.rice.kns.service.BusinessObjectService)}.
     */
    @Test
    public void testSetBusinessObjectService() {
        protocolReviewNotRequiredServiceImpl.setBusinessObjectService(businessObjectService);
        assertEquals(businessObjectService.getClass(), protocolReviewNotRequiredServiceImpl.getBusinessObjectService().getClass());
    }

    /**
     * Test method for {@link org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredServiceImpl#setprotocolActionService(org.kuali.kra.irb.actions.submit.ProtocolActionService)}.
     */
    @Test
    public void testSetprotocolActionService() {
        protocolReviewNotRequiredServiceImpl.setProtocolActionService(protocolActionService);
        assertEquals(protocolActionService.getClass(), protocolReviewNotRequiredServiceImpl.getProtocolActionService().getClass());
    }

    /**
     * Test method for {@link org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredServiceImpl#reviewNotRequired(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean)}.
     */
    @Test
    public void testReviewNotRequired() {
        protocolReviewNotRequiredService.reviewNotRequired(protocolDocument, actionBean);
        assertEquals(ProtocolStatus.IRB_REVIEW_NOT_REQUIRED, protocolDocument.getProtocol().getProtocolStatusCode());
    }

}
