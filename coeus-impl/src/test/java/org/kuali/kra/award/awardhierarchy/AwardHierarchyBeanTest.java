/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.awardhierarchy;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class AwardHierarchyBeanTest {
    
    private static final String ROOT_AWARD_NUMBER = "100001-00001";
    private static final String UNKNOWN_TARGET_NODE_AWARD_NUMBER = "123456-78901";
    
    private AwardHierarchyBean bean;
    private AwardHierarchyServiceImpl service;
    private Award rootAward;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    @Before
    public void setUp() throws Exception {
        service = new AwardHierarchyServiceImpl() {
        	@Override
        	AwardDocument createPlaceholderDocument() throws WorkflowException {
        		return null;
        	}
        };
        service.setLegacyDataAdapter(getMockBusinessObjectService());
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
        bean.getRootNodes().put(rootAward.getAwardNumber(), AwardHierarchy.createRootNode(rootAward));
    }

    private class AwardHierarchyBeanForUnitTest extends AwardHierarchyBean {
        Award award;
        AwardHierarchyBeanForUnitTest(AwardForm awardForm, AwardHierarchyService service) { super(awardForm, service); }

        @Override
        Award getAward() { // since we can't create AwardDocument in unit test, override this method to provide Award
            return award != null ? award : rootAward;
        }

    }
    
    private LegacyDataAdapter getMockBusinessObjectService() {
        final LegacyDataAdapter service = context.mock(LegacyDataAdapter.class);

        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = Collections.<String,Object>singletonMap("documentDescription", AwardDocument.PLACEHOLDER_DOC_DESCRIPTION);
            one(service).findMatching(DocumentHeader.class, fieldValues);
            will(returnValue(new ArrayList<DocumentHeader>()));

            Map<String, Object> primaryKeys = CollectionUtils.zipMap(new String[]{"awardNumber", "active"}, new Object[]{ROOT_AWARD_NUMBER, Boolean.TRUE});
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
