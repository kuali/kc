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
package org.kuali.kra.award.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

/**
 * 
 * This class tests methods in Award.java class
 */
public class AwardCommentFactoryTest extends KcIntegrationTestBase {
    
    private AwardCommentFactory awardCommentFactory;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCommentFactory = new AwardCommentFactory();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCommentFactory = null;
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
