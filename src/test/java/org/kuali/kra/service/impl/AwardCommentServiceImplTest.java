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
import org.kuali.kra.bo.CommentType;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class tests <code>AwardCommentService</code>
 */

@RunWith(JMock.class)
public class AwardCommentServiceImplTest {

    final Collection<CommentType> commentTypeList = new ArrayList<CommentType>();
    AwardCommentServiceImpl awardCommentServiceImpl;
    final Map<String, Object> queryMap = new HashMap<String, Object>();
    private String AWARD_COMMENT_SCREEN_FLAG = "awardCommentScreenFlag";
    private String SOME_COMMENTS = "some comments";
    private String SOME_OTHER_COMMENTS = "some other comments";
    private String ONE = "1";
    private String TWO = "2";

    private Mockery context = new JUnit4Mockery();

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCommentServiceImpl = new AwardCommentServiceImpl();
        queryMap.put(AWARD_COMMENT_SCREEN_FLAG, CommentType.SCREENFLAG_TRUE);
        CommentType commentType1 = new CommentType();
        CommentType commentType2 = new CommentType();
        commentType1.setAwardCommentScreenFlag(true);
        commentType1.setDescription(SOME_COMMENTS);
        commentType1.setCommentTypeCode(ONE);
        commentType2.setAwardCommentScreenFlag(true);
        commentType2.setDescription(SOME_OTHER_COMMENTS);
        commentType2.setCommentTypeCode(TWO);
        
        commentTypeList.add(commentType1);
        commentTypeList.add(commentType2);

    }

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCommentServiceImpl = null;
    }

    /**
     * Tests method retrieveCommentTypesToAwardFormForPanelHeaderDisplay on AwardCommentServiceImpl returns the
     * correct values.
     */
    @Test
    public void testRetrieveCommentTypesToAwardFormForPanelHeaderDisplay() {
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE;
        MOCKED_BUSINESS_OBJECT_SERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKED_BUSINESS_OBJECT_SERVICE).findMatching(CommentType.class, queryMap); 
            will(returnValue(commentTypeList));
        }});
        awardCommentServiceImpl.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        Assert.assertTrue(awardCommentServiceImpl.retrieveCommentTypes().size() == 2);
    }
}
