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
package org.kuali.kra.irb.actions.noreview;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class ProtocolReviewNotRequiredServiceTest extends KcIntegrationTestBase {
    
    private static final String COMMENTS = "really cool comments";
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date DECISION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolReviewNotRequiredServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};

    @Before
    public void setUp() throws Exception {

        service = new ProtocolReviewNotRequiredServiceImpl();
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setBusinessObjectService(getMockBusinessObjectService());
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        
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
