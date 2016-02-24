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
package org.kuali.kra.service.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.award.service.impl.AwardCommentServiceImpl;
import org.kuali.kra.bo.CommentType;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};


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
