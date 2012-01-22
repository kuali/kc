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
package org.kuali.kra.award.awardhierarchy;

import java.util.ArrayList;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class AwardHierarchyBeanTest {
    
    private static final String ROOT_AWARD_NUMBER = "100001-00001";
    private static final String UNKNOWN_TARGET_NODE_AWARD_NUMBER = "123456-78901";
    
    private AwardHierarchyBean bean;
    private AwardHierarchyServiceImpl service;
    private Award rootAward;
    
    private Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() throws Exception {
        service = new AwardHierarchyServiceImpl();
        service.setBusinessObjectService(getMockBusinessObjectService());
        service.setDocumentService(getMockDocumentService());
        rootAward = new Award();
        rootAward.setAwardNumber(ROOT_AWARD_NUMBER);
        createAwardHierarchyBean();
    }

    @After
    public void tearDown() {
        bean = null;
    }

    @Test
    public void testSaveHierarchyChanges_NoError() {
        Assert.assertTrue(bean.saveHierarchyChanges());
    }

    @Test(expected=MissingHierarchyException.class)
    public void testCreatingNewChildAwardBasedOnAnotherAwardInHierarchy_BadInput() {
        String targetAwardNumber = bean.getRootNode().getAwardNumber();
        String awardNumberOfAwardToBeUsedAsTemplate = UNKNOWN_TARGET_NODE_AWARD_NUMBER;
        bean.createNewChildAwardBasedOnAnotherAwardInHierarchy(targetAwardNumber, awardNumberOfAwardToBeUsedAsTemplate);
    }

    @Test
    public void testGettingNodeForCurrentAward() { 
        Assert.assertEquals(rootAward.getAwardNumber(), bean.getCurrentAwardHierarchyNode().getAwardNumber());
    }

    private void createAwardHierarchyBean() {
        bean = new AwardHierarchyBeanForUnitTest(null, service);
        bean.rootNodes.put(rootAward.getAwardNumber(), AwardHierarchy.createRootNode(rootAward));
    }

    private class AwardHierarchyBeanForUnitTest extends AwardHierarchyBean {
        Award award;
        AwardHierarchyBeanForUnitTest(AwardForm awardForm, AwardHierarchyService service) { super(awardForm, service); }

        @Override
        Award getAward() { // since we can't create AwardDocument in unit test, override this method to provide Award
            return award != null ? award : rootAward;
        }

    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);

        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = ServiceHelper.getInstance().buildCriteriaMap("documentDescription", AwardDocument.PLACEHOLDER_DOC_DESCRIPTION);
            one(service).findMatching(DocumentHeader.class, fieldValues);
            will(returnValue(new ArrayList<DocumentHeader>()));
            
            Map<String, Object> primaryKeys = ServiceHelper.getInstance().buildCriteriaMap(new String[]{"awardNumber", "active"}, new Object[]{ROOT_AWARD_NUMBER, Boolean.TRUE});
            allowing(service).findByPrimaryKey(AwardHierarchy.class, primaryKeys);
            will(returnValue(null));
        }});
        
        return service;
    }
    
    private DocumentService getMockDocumentService() throws WorkflowException {
        final DocumentService service = context.mock(DocumentService.class);
        
        context.checking(new Expectations() {{
            one(service).getNewDocument(AwardDocument.class);
            will(returnValue(null));
        }});
        
        return service;
    }

}