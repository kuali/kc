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
package org.kuali.kra.award.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class Tests the methods in AwardAction.java
 */

public class AwardActionTest {
    
    AwardAction awardAction;
    public static final String MOCK_FORWARD_STRING = "FORWARD_STRING";
    public static final String MOCK_DOC_ID_REQUEST_PARAMETER = "21";
    public static final String MOCK_EXPECTED_RESULT_STRING = "FORWARD_STRING?docId=21";
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardAction = new AwardAction();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardAction = null;
    }
    
    /**
     * 
     * This test tests the AwardAction.buildForwardStringForActionListCommand method.
     * @throws Exception
     */
    @Test
    public void testBuildForwardStringForActionListCommand() throws Exception {        
        Assert.assertEquals(
                awardAction.buildForwardStringForActionListCommand(MOCK_FORWARD_STRING, 
                               MOCK_DOC_ID_REQUEST_PARAMETER),MOCK_EXPECTED_RESULT_STRING);     
    }

}
