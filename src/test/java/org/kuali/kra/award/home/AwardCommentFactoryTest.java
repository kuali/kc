/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

/**
 * 
 * This class tests methods in Award.java class
 */
public class AwardCommentFactoryTest extends KcUnitTestBase{ 
    private static final int AWARD_COMMENT_FACTORY_ATTRIBUTES_COUNT = 50;
    
    private Award awardBo;
    private AwardCommentFactory awardCommentFactory;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardBo = new Award();
        awardCommentFactory = new AwardCommentFactory();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        awardBo = null;
        awardCommentFactory = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testAwardBoAttributesCount() throws Exception {              
        Assert.assertEquals(AWARD_COMMENT_FACTORY_ATTRIBUTES_COUNT, awardBo.getClass().getFields().length);
    }
    
    /**
     * 
     * This method tests that createCostShareAwardComment creates an AwardComment object and that the
     * commentTypeCode is set to the correct constant value
     * @throws Exception
     */
    @Test
    public void testCreateCostShareComment() throws Exception { 
        AwardComment awardComment = awardCommentFactory.createCostShareComment();
        Assert.assertTrue(awardComment instanceof AwardComment);
        Assert.assertEquals(Constants.COST_SHARE_COMMENT_TYPE_CODE, awardComment.getCommentTypeCode());
    }
    
    /**
     * 
     * This method tests that createFandaRateComment creates an AwardComment object and that the
     * commentTypeCode is set to the correct constant value
     * @throws Exception
     */
    @Test
    public void testCreateFandaRateComment() throws Exception { 
        AwardComment awardComment = awardCommentFactory.createFandaRateComment();
        Assert.assertTrue(awardComment instanceof AwardComment);
        Assert.assertEquals(Constants.FANDA_RATE_COMMENT_TYPE_CODE, awardComment.getCommentTypeCode());
    }
    
    /**
     * 
     * This method tests that createFandaRateComment creates an AwardComment object and that the
     * commentTypeCode is set to the correct constant value
     * @throws Exception
     */
    @Test
    public void testCreatePreAwardSponsorAuthorizationComment() throws Exception { 
        AwardComment awardComment = awardCommentFactory.createPreAwardSponsorAuthorizationComment();
        Assert.assertTrue(awardComment instanceof AwardComment);
        Assert.assertEquals(Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE, awardComment.getCommentTypeCode());
    }
    
    /**
     * 
     * This method tests that createFandaRateComment creates an AwardComment object and that the
     * commentTypeCode is set to the correct constant value
     * @throws Exception
     */
    @Test
    public void testCreatePreAwardInstitutionalAuthorizationComment() throws Exception { 
        AwardComment awardComment = awardCommentFactory.createPreAwardInstitutionalAuthorizationComment();
        Assert.assertTrue(awardComment instanceof AwardComment);
        Assert.assertEquals(Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE, awardComment.getCommentTypeCode());
    }
    
}
